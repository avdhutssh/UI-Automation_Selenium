package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends ElementUtils {

    @FindBy(xpath = "//button[normalize-space(text())='Checkout']")
    private WebElement label_checkout;
    private WebDriver driver;

    public CartPage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutYourInformationPage navigateToCheckoutPage() {
        waitForElementVisible(this.label_checkout).click();
        return new CheckoutYourInformationPage(driver, log);
    }

}
