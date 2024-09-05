package com.swag.labs.Utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class ElementUtils extends BasePage {

    protected WebDriver driver;
    protected Logger log;
    protected JavascriptExecutor js;
    protected Actions act;

    protected ElementUtils(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        this.log = log;
        this.js = (JavascriptExecutor) driver;
        this.act = new Actions(driver);
    }

    protected static WebElement findElement(WebElement we) {
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

    protected void clickOnElement(WebElement element) {
        WebElement webElement = waitForElementClickable(element);
        try {
            webElement.click();
        } catch (Exception e) {
            webElement.click();
            System.err.println("Failed to click on element " + e.getMessage());
        }
    }

    protected void clickOnElementJS(WebElement ele) {
        try {
            js.executeScript("arguments[0].click()", ele);
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }

    protected void clickOnElementAction(WebElement ele) {
        act.moveToElement(ele).click().build().perform();
    }

    protected void SwitchTowindow(int windowNumber) {
        waitForChildWindow(windowNumber);
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

    protected void SwitchTowindow(String expectedTitle) {
        String parentWindow = driver.getWindowHandle();
        Set<String> allwindows = driver.getWindowHandles();

        Iterator<String> windowsIterator = allwindows.iterator();
        while (windowsIterator.hasNext()) {
            String windowHandle = windowsIterator.next().toString();
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                if (getCurrentPageTitle().equals(expectedTitle)) {
                    break;
                }
            }
        }
    }

    protected void enterText(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to enter text" + e.getMessage());
        }
    }

    protected void enterText(WebElement element, String text, boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to enter text" + e.getMessage());
        }
    }

    protected void enterText(WebElement element, String text, boolean clear, boolean pressEnter) {
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

    protected void selectDropdownOption(WebElement ele, String type, String selectValue) {
        Select ddl = new Select(ele);
        switch (type) {
            case "text":
                ddl.selectByVisibleText(selectValue);
                break;
            case "value":
                ddl.selectByValue(selectValue);
                break;
            case "index":
                ddl.selectByIndex(Integer.parseInt(selectValue));
                break;
            default:
                throw new IllegalArgumentException("Invalid selection type");
        }
    }

    protected Alert switchToAlert() {
        waitForAlert();
        return driver.switchTo().alert();
    }

    protected void handleAlert(String expectedText, String promt) {
        try {
            Alert alert = switchToAlert();
            String actualText = alert.getText();
            Assert.assertEquals(actualText, expectedText);
            if (promt.equals("accept")) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } catch (Exception e) {
            System.err.println("Failed to handle the Alert" + e.getMessage());
        }
    }

    protected void switchToFrame(WebElement ele) {
        driver.switchTo().frame(ele);
    }

    protected void pressKey(WebElement ele, Keys key) {
        ele.sendKeys(key);
    }

    protected void pressKeyWithActions(Keys key) {
        log.info("Pressing " + key.name() + " using Actions class");
        act.sendKeys(key).perform();
    }

    protected void scrollToBottom() {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    protected void performDragAndDrop(WebElement from, WebElement to) {
        act.dragAndDrop(from, to).perform();
    }

    protected void hoverOverElement(WebElement element) {
        act.moveToElement(element).perform();
    }

    protected void hoverOverElementJS(WebElement element) {
        String script = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', \n"
                + "\n"
                + "true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script, element);
    }

    protected void setCookie(Cookie ck) {
        log.info("Adding cookie " + ck.getName());
        driver.manage().addCookie(ck);
        log.info("Cookie added");
    }

    protected String getCookie(String name) {
        log.info("Getting value of cookie " + name);
        return driver.manage().getCookieNamed(name).getValue();
    }
}
