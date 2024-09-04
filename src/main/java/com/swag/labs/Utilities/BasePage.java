package com.swag.labs.Utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Properties;
import java.util.function.Function;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log;

    protected Properties prop = new ConfigurationUtils().getProperty();

    protected BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.log = log;
    }

    protected WebElement waitForElementClickable(WebElement ele) {
        WebElement webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.elementToBeClickable(ele));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be clickable: " + e.getMessage());
        }
        return webElement;
    }

    protected WebElement waitForElementVisible(WebElement ele) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (TimeoutException e) {
            log.error("Timeout occurred while waiting for element to be visible: " + e.getMessage());
            throw new NoSuchElementException("Element not found: " + ele);
        }
    }
    
    protected WebElement waitForElementPresence(By locator) {
        WebElement webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be present: " + e.getMessage());
        }
        return webElement;
    }

    protected WebElement waitForElementPresence(WebElement element) {
        WebElement webElement = null;
        try {
            By locator = getLocatorFromWebElement(element);
            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be present: " + e.getMessage());
        }
        return webElement;
    }

    protected Boolean waitForElementInvisible(WebElement ele) {
        Boolean webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.invisibilityOf(ele));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for element to be present: " + e.getMessage());
        }
        return webElement;
    }

    protected boolean waitForTextToBePresentInElement(WebElement element, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for text to be present in element: " + e.getMessage());
            return false;
        }
    }

    protected void waitForChildWindow(int windowNumber) {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(windowNumber));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for the child window: " + e.getMessage());
        }

    }

    protected void waitForAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for the alert: " + e.getMessage());
        }

    }

    protected void waitTime(int wait) {
        synchronized (driver) {
            try {
                if (wait > 0) {
                    driver.wait(wait);
                } else {
                    System.err.println("Invalid wait time. Wait time should be a positive value.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Wait operation was interrupted: " + e.getMessage());
            }
        }
    }

    protected void pageProcessingWait() {
        try {
            By processingMaskLocator = By.xpath("//div[@id='basicModal']/img");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(processingMaskLocator));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for page processing to complete: " + e.getMessage());
        }

    }

    protected void visibilityOfProcessingMask() {
        try {
            By processingMaskLocator = By.xpath("//div[@id='basicModal']/img");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(processingMaskLocator));
        } catch (TimeoutException e) {
            System.err.println("Timeout occurred while waiting for page processing to visible: " + e.getMessage());
        }

    }

    protected void isElementDisplayed(WebElement ele) {
        wait.until((d) -> ele.isDisplayed());
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

    protected void openUrl(String url) {
        driver.get(url);
    }

    protected String getCurrentPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentPageSource() {
        return driver.getPageSource();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    // Wait for specific ExpectedCondition for the given amount of time in seconds

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(condition);
    }


    //     Wait for given number of seconds for element with given locator to be visible on the page
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    //   Wait for element to be displayed
    protected boolean waitForElementToBeDisplayed(By locator, int timeoutInSeconds) {
        log.info("Waiting for element to be displayed: " + locator);
        int attempts = 0;
        while (attempts < timeoutInSeconds) {
            try {
                if (driver.findElement(locator).isDisplayed()) {
                    log.info("Element is displayed: " + locator);
                    return true;
                }
            } catch (NoSuchElementException e) {
                log.debug("Element not found: " + locator + ", attempt: " + attempts);
            }
            try {
                Thread.sleep(1000); // Wait for 1 second before next attempt
            } catch (InterruptedException e) {
                log.error("InterruptedException during wait", e);
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
            attempts++;
        }
        log.error("Element not displayed after " + timeoutInSeconds + " seconds: " + locator);
        return false;
    }

    protected WebElement fluentWait(final By locator, int timeoutInSeconds, int pollingInMillis) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis)).ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }
}
