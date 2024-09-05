package com.swag.labs.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertionUtils {
    public void assertElementText(WebElement element, String expectedText) {
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed on the page");
        Assert.assertEquals(element.getText(), expectedText);
    }

    public static boolean verifyPageTitle(WebDriver driver, String expectedTitle) {
        String actualTitle = driver.getTitle();
        return actualTitle.equals(expectedTitle);
    }
}
