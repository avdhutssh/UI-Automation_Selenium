package com.swag.labs.Tests;

import com.swag.labs.BaseComponents.BaseTest;
import com.swag.labs.PageObjects.*;
import com.swag.labs.Utils.JavaUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class _02_Add_Product_To_Cart_And_Checkout extends BaseTest {

    String CheckoutOverviewPageTitle = "Checkout: Overview";
    String OrderSuccessMsg = "Thank you for your order!";
    String productName = "Sauce Labs Backpack";
    String firstname;
    String lastname;
    String postalcode;

    @BeforeTest
    public void SetupTestData() {
        this.firstname = JavaUtils.generateRandom(4, "name");
        this.lastname = JavaUtils.generateRandom(8, "name");
        this.postalcode = JavaUtils.generateRandom(6, "number");
    }

    @Test
    public void Add_Highest_Priced_Product_To_Cart_And_Checkout() {
        log.info("Starting Add_Highest_Priced_Product_To_Cart_And_Checkout");
        ProductsPage productsPage = loginPage.login();
        productsPage.addHighestPriceProductToCart();
        CartPage cartPage = productsPage.navigateToCartPage();
        CheckoutYourInformationPage checkoutYourInformationPage = cartPage.navigateToCheckoutPage();
        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInformationPage.fillCheckoutInfo(this.firstname, this.lastname, this.postalcode);
        Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewPageTitle(), CheckoutOverviewPageTitle);
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.ClickOnFinish();
        Assert.assertEquals(checkoutCompletePage.getSuccessfulOrderMsg(), OrderSuccessMsg);
    }

}
