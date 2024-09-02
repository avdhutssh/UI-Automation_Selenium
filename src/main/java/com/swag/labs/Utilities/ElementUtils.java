package com.swag.labs.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementUtils extends BasePage {

    protected WebDriver driver;

    public ElementUtils(WebDriver driver) {
        super(driver);
        this.driver = driver;
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
}
