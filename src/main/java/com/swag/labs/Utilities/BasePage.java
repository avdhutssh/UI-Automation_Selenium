package com.swag.labs.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public WebElement waitForElementClickable(WebElement ele) {
        WebElement webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.elementToBeClickable(ele));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be clickable: " + e.getMessage());
        }
        return webElement;
    }

    public WebElement waitForElementVisible(WebElement ele) {
        WebElement webElement = null;
        try {
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be visible: " + e.getMessage());
        }
        return webElement;
    }

    public WebElement waitForElementPresence(By locator) {
        WebElement webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be present: " + e.getMessage());
        }
        return webElement;
    }

    
}
