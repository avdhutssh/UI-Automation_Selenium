package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends ElementUtils {

    @FindBy(className = "complete-header")
    WebElement lable_success_header;
    private WebDriver driver;

    public CheckoutCompletePage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSuccessfulOrderMsg() {
        return waitForElementVisible(this.lable_success_header).getText();
    }

}
