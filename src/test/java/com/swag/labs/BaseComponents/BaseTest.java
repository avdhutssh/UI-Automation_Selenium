package com.swag.labs.BaseComponents;

import com.swag.labs.PageObjects.LoginPage;
import com.swag.labs.Utilities.BrowserDriverFactory;
import com.swag.labs.Utilities.ConfigurationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    protected Logger log;
    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    protected Properties prop = new ConfigurationUtils().getProperty();
    public LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, ITestContext ctx) {
        log = LogManager.getLogger(testName);
        String browser = prop.getProperty("browser", "chrome").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));
        if (isHeadless) {
            browser += "headless";
        }
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();
        driver.manage().window().maximize();
        this.testName = ctx.getCurrentXmlTest().getName();
        this.testSuiteName = ctx.getSuite().getName();
        this.testMethodName = method.getName();
        this.loginPage = new LoginPage(driver, log);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        driver.quit();
    }
}
