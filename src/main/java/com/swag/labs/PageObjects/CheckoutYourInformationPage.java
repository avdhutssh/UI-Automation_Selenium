package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutYourInformationPage extends ElementUtils {

    @FindBy(id = "first-name")
    private WebElement txt_firstName;
    @FindBy(id = "last-name")
    private WebElement txt_lastName;
    @FindBy(id = "postal-code")
    private WebElement txt_postalCode;
    @FindBy(id = "continue")
    private WebElement btn_continue;
    private WebDriver driver;

    public CheckoutYourInformationPage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutOverviewPage fillCheckoutInfo(String fname, String lname, String postalCode) {
        waitForElementVisible(this.txt_firstName).sendKeys(fname);
        this.txt_lastName.sendKeys(lname);
        this.txt_postalCode.sendKeys(postalCode);
        this.btn_continue.click();
        return new CheckoutOverviewPage(driver, log);
    }

}
