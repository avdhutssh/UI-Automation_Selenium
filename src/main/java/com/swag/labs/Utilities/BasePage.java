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

    public WebElement waitForElementPresence(WebElement element) {
        WebElement webElement = null;
        try {
            By locator = getLocatorFromWebElement(element);
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be present: " + e.getMessage());
        }
        return webElement;
    }

    public Boolean waitForElementInvisible(WebElement ele) {
        Boolean webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.invisibilityOf(ele));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be present: " + e.getMessage());
        }
        return webElement;
    }
    
    private By getLocatorFromWebElement(WebElement element) {
        String elementDescription = element.toString();
        String locatorString = elementDescription.substring(elementDescription.indexOf("-> ") + 3);
        String locatorType = locatorString.substring(0, locatorString.indexOf(":"));
        String locatorValue = locatorString.substring(locatorString.indexOf(":") + 1).trim();

        switch (locatorType) {
            case "id":
                return By.id(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "className":
                return By.className(locatorValue);
            case "tagName":
                return By.tagName(locatorValue);
            case "linkText":
                return By.linkText(locatorValue);
            case "partialLinkText":
                return By.partialLinkText(locatorValue);
            case "cssSelector":
                return By.cssSelector(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }

}
