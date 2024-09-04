package com.swag.labs.PageObjects;

import com.swag.labs.Utilities.ElementUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends ElementUtils {
    @FindBy(css = ".product_sort_container")
    private WebElement label_productSort;
    @FindBy(xpath = "//button[normalize-space(text())='Add to cart']")
    private List<WebElement> btn_AddToCart;

    @FindBy(className = "shopping_cart_link")
    private WebElement label_cart;

    private WebDriver driver;

    public ProductsPage(WebDriver driver, Logger log) {
        super(driver, log);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addHighestPriceProductToCart() {
        selectSortingOption("hilo");
        isElementDisplayed(this.btn_AddToCart.get(0));
        this.btn_AddToCart.get(0).click();
    }

    private void selectSortingOption(String value) {
        waitForElementClickable(this.label_productSort);
        selectDropdownOption(this.label_productSort, "value", value);
    }

    public void clickOnProductAddToCartButton(String productName) {
        driver.findElement(By.xpath("//*[normalize-space(text())='" + productName + "']/../../..//button")).click();
    }

    public CartPage navigateToCartPage() {
        this.label_cart.click();
        return new CartPage(driver, log);
    }

}
