package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ElementUtils {

    private String url = (prop.getProperty("url"));
    private String userName = (prop.getProperty("loginId"));
    private String password = (prop.getProperty("password"));
    @FindBy(css = "#user-name")
    private WebElement txt_username;
    @FindBy(css = "#password")
    private WebElement txt_pwd;
    @FindBy(id = "login-button")
    private WebElement btn_login;
    @FindBy(css = "[data-test='error']")
    private WebElement label_errorMsg;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement section_Menu;
    @FindBy(id = "logout_sidebar_link")
    private WebElement label_logout;
    private WebDriver driver;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login() {
        driver.get(url);
        waitForElementVisible(this.txt_username).sendKeys(this.userName);
        waitForElementVisible(this.txt_pwd).sendKeys(this.password);
        waitForElementClickable(this.btn_login).click();
        return new ProductsPage(driver, log);
    }

    public void loginWithUserInfo(String userName, String pwd) {
        driver.get(url);
        waitForElementVisible(this.txt_username).sendKeys(userName);
        waitForElementVisible(this.txt_pwd).sendKeys(pwd);
        waitForElementClickable(this.btn_login).click();
    }

    public boolean VerifyErrorMsgIsGettingDisplayed() {
        return waitForElementVisible(this.label_errorMsg).isDisplayed();
    }

    public String getErrorMsg() {
        return waitForElementVisible(this.label_errorMsg).getText();
    }

    public void logout() {
        waitForElementVisible(this.section_Menu).click();
        waitForElementVisible(this.label_logout).click();
    }
}
