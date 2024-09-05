package com.swag.labs.Tests;

import com.swag.labs.BaseComponents.BaseTest;
import com.swag.labs.PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class _03_Add_Specific_Product_To_Cart_And_Checkout extends BaseTest {

    String CheckoutOverviewPageTitle = "Checkout: Overview";
    String OrderSuccessMsg = "Thank you for your order!";
    String productName = "Sauce Labs Backpack";

    @Test
    public void Add_Highest_Priced_Product_To_Cart_And_Checkout() {
        log.info("Starting Add_Highest_Priced_Product_To_Cart_And_Checkout");
        ProductsPage productsPage = loginPage.login();
        productsPage.addHighestPriceProductToCart();
        CartPage cartPage = productsPage.navigateToCartPage();
        CheckoutYourInformationPage checkoutYourInformationPage = cartPage.navigateToCheckoutPage();
        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInformationPage.fillCheckoutInfo("Albus", "Severus", "9-3/4");
        Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewPageTitle(), CheckoutOverviewPageTitle);
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.ClickOnFinish();
        Assert.assertEquals(checkoutCompletePage.getSuccessfulOrderMsg(), OrderSuccessMsg);
    }

}
