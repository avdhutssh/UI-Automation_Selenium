package com.swag.labs.Utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class ElementUtils extends BasePage {

    protected WebDriver driver;
    protected Logger log;
    protected JavascriptExecutor js;

    public ElementUtils(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        this.log = log;
        this.js = (JavascriptExecutor) driver;
    }

    public static WebElement findElement(WebElement we) {
        WebElement element = null;
        int attempts = 0;
        while (attempts < 2) {
            try {
                element = we;
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            attempts++;
        }
        return element;
    }

    public void clickOnElement(WebElement element) {
        WebElement webElement = waitForElementClickable(element);
        try {
            webElement.click();
        } catch (Exception e) {
            webElement.click();
            System.err.println("Failed to click on element " + e.getMessage());
        }
    }

    public void clickOnElementJS(WebElement ele) {
        try {
            js.executeScript("arguments[0].click()", ele);
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }

    public void SwitchTowindow(int windowNumber) {
        waitForChildWindow(i);
        String[] windowHandles = driver.getWindowHandles().stream().toArray(String[]::new);
        try {
            if (windowNumber > windowHandles.length) {
                throw new RuntimeException(String.format(
                        "The specified window number (%s) is greater than the " + "number of windows created (%s).",
                        windowNumber, windowHandles.length));
            }
            driver.switchTo().window(windowHandles[windowNumber - 1]);
            log.info("Successfully switched to window number: " + windowNumber);
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("Failed to switch to window number %s. The total number of open windows was %s.",
                            windowNumber, windowHandles.length),
                    ex);
        }
    }

    public void enterText(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to enter text" + e.getMessage());
        }
    }

    public void enterText(WebElement element, String text, boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to enter text" + e.getMessage());
        }
    }

    public void enterText(WebElement element, String text, boolean clear, boolean pressEnter) {
        try {
            if (clear) {
                element.clear();
            }
            element.sendKeys(text);
            if (pressEnter) {
                element.sendKeys(Keys.ENTER);
            }
        } catch (Exception e) {
            System.err.println("Failed to enter text" + e.getMessage());
        }
    }
}
