package com.google;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MeetTheAutomationArchitect {

    private static String searchString = "Automation";
    WebDriver driver;

    @Test
    public void validatePageTitle() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        browseToArticle(driver);
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        waitForInfoWeekToLoad(driver);
        Assert.assertEquals(driver.getTitle(),"Meet the Automation Architect - InformationWeek");
    }

    private void browseToArticle(WebDriver driver) {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys(searchString);
        driver.findElement(By.name("btnK")).click();
        driver.findElement(By.linkText("Images")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Meet the Automation Architect' )]")).click();
    }

    private void waitForInfoWeekToLoad(WebDriver driver) {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(45))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(Exception.class);
        fluentWait.until(presenceOfElementLocated(By.id("article-main")));
    }

    @After()
    public void cleanUp(){
        driver.quit();
    }
}
