package com.swag.labs.Utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserDriverFactory {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver() {
        log.info("Create driver: " + browser);

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                final Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.password_manager_enabled", false);
                chromePrefs.put("profile.password_manager_leak_detection", false);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                driver.set(new FirefoxDriver());
                break;

            case "chromeheadless":
                log.info("Starting Chrome in Headless mode");
                ChromeOptions headlessChromeOptions = new ChromeOptions();
                headlessChromeOptions.addArguments("--headless");
                headlessChromeOptions.addArguments("--disable-gpu");
                headlessChromeOptions.addArguments("--no-sandbox");
                headlessChromeOptions.addArguments("--disable-dev-shm-usage");
                headlessChromeOptions.addArguments("--window-size=1920,1080");  // Set window size
                headlessChromeOptions.addArguments("--remote-allow-origins=*"); // Prevent CORS issue

                // To Handle password unknown pop-ups
                final Map<String, Object> headlessChromePrefs = new HashMap<>();
                headlessChromePrefs.put("credentials_enable_service", false);
                headlessChromePrefs.put("profile.password_manager_enabled", false);
                headlessChromePrefs.put("profile.password_manager_leak_detection", false);
                headlessChromeOptions.setExperimentalOption("prefs", headlessChromePrefs);
                driver.set(new ChromeDriver(headlessChromeOptions));
                break;

            case "firefoxheadless":
                log.info("Starting Firefox in Headless mode");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                driver.set(new FirefoxDriver(firefoxOptions));
                break;

            default:
                log.info("Do not know how to start: " + browser + ", starting chrome.");
                driver.set(new ChromeDriver());
                break;
        }

        driver.get().manage().window().maximize();
        return driver.get();
    }

    public WebDriver createChromeWithProfile(String profile) {
        log.info("Starting chrome driver with profile: " + profile);
        ChromeOptions chromeOptions = getChromeOptions();
        chromeOptions.addArguments("user-data-dir=src/main/resources/Profiles/" + profile);
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }

    public WebDriver createChromeWithMobileEmulation(String deviceName) {
        log.info("Starting driver with " + deviceName + " emulation]");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = getChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        return options;
    }
}