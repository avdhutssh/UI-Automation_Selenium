package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends ElementUtils {

    String url = (prop.getProperty("url"));
    String userName = (prop.getProperty("loginId"));
    String password = (prop.getProperty("password"));
    @FindBy(css = "#user-name")
    WebElement txt_username;
    @FindBy(css = "#password")
    WebElement txt_pwd;
    @FindBy(id = "login-button")
    WebElement btn_login;
    private WebDriver driver;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login() {
        waitForElementVisible(this.txt_username).sendKeys(this.userName);
        waitForElementVisible(this.txt_pwd).sendKeys(this.password);
        waitForElementClickable(this.btn_login).click();
        return new ProductsPage(driver, log);
    }

    public void loginWithUserInfo(String userName, String pwd) {
        waitForElementVisible(this.txt_username).sendKeys(userName);
        waitForElementVisible(this.txt_pwd).sendKeys(pwd);
        waitForElementClickable(this.btn_login).click();
    }

}
