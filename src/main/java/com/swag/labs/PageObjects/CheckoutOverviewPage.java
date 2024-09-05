package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage extends ElementUtils {

    @FindBy(css = "span[class='title']")
    WebElement label_header;
    @FindBy(id = "finish")
    WebElement btn_finish;
    @FindBy(id = "cancel")
    WebElement btn_cancel;
    private WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCheckoutOverviewPageTitle() {
        return waitForElementVisible(this.label_header).getText();
    }

    public CheckoutCompletePage ClickOnFinish() {
        waitForElementClickable(this.btn_finish).click();
        return new CheckoutCompletePage(driver, log);
    }

    public ProductsPage ClickOnCancelBtn() {
        waitForElementClickable(this.btn_cancel).click();
        return new ProductsPage(driver, log);
    }
}
