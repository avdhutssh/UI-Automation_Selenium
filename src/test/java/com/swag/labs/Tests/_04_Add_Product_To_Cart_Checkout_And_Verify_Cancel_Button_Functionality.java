package com.swag.labs.Tests;

import com.swag.labs.BaseComponents.BaseTest;
import com.swag.labs.PageObjects.CartPage;
import com.swag.labs.PageObjects.CheckoutOverviewPage;
import com.swag.labs.PageObjects.CheckoutYourInformationPage;
import com.swag.labs.PageObjects.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class _04_Add_Product_To_Cart_Checkout_And_Verify_Cancel_Button_Functionality extends BaseTest {
    String CheckoutOverviewPageTitle = "Checkout: Overview";

    @Test
    public void Verify_Cancel_Button_Functionality_On_Checkout_Page() {
        log.info("Starting Verify_Cancel_Button_Functionality_On_Checkout_Page");
        ProductsPage productsPage = loginPage.login();
        productsPage.addHighestPriceProductToCart();
        CartPage cartPage = productsPage.navigateToCartPage();
        CheckoutYourInformationPage checkoutYourInformationPage = cartPage.navigateToCheckoutPage();
        CheckoutOverviewPage checkoutOverviewPage = checkoutYourInformationPage.fillCheckoutInfo("Albus", "Severus", "9-3/4");
        Assert.assertEquals(checkoutOverviewPage.getCheckoutOverviewPageTitle(), CheckoutOverviewPageTitle);
        checkoutOverviewPage.ClickOnCancelBtn();
        Assert.assertEquals(productsPage.getProductsPageTitle(), "Products");
    }

    // Intentionally failed and skipped the below test cases to see everything getting captured in the extent report properly or not

    //this should be failed
    @Test
    public void Verify_Wrong_Functionality_On_Checkout_Page() {
        log.info("Starting Verify_Wrong_Functionality_On_Checkout_Page");
        ProductsPage productsPage = loginPage.login();
        productsPage.addHighestPriceProductToCart();
        Assert.assertTrue(false);
    }

    //this should be skipped
    @Test(dependsOnMethods = {"Verify_Cancel_Button_Functionality_On_Checkout_Page", "Verify_Wrong_Functionality_On_Checkout_Page"})
    public void Verify_Skipped_Functionality_On_Checkout_Page() {
        log.info("Starting Verify_Skipped_Functionality_On_Checkout_Page");
        ProductsPage productsPage = loginPage.login();
    }
}
