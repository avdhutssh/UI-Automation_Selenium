package com.swag.labs.Utilities;

import com.swag.labs.PageObjects.CartPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class TestUtils extends CartPage {

    WebDriver driver;

    public TestUtils(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void takeScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + File.separator + "screenshots"
                + File.separator + getTodaysDate() + File.separator + testSuiteName + File.separator + testName
                + File.separator + testMethodName + File.separator + getSystemTime() + " " + fileName + ".png";
    }

}
