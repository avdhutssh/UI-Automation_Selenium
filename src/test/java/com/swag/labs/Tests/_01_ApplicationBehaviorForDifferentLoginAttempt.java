package com.swag.labs.Tests;

import com.swag.labs.BaseComponents.BaseTest;
import com.swag.labs.PageObjects.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class _01_ApplicationBehaviorForDifferentLoginAttempt extends BaseTest {
    String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";

    @Test
    public void _01_Validate_Incorrect_Login_Attempt() {
        // TO-DO: can try with 3 combinations(Using excel reader) of wrong username and
        // pwd(w_user, c_pwd : c_user, w_pwd : w_user, w_pwd)
        log.info("Starting Add_Highest_Priced_Product_To_Cart_And_Checkout");
        loginPage.loginWithUserInfo("incorrect_user", "incorrect_password");
        Assert.assertEquals(loginPage.getErrorMsg(), expectedErrorMsg);
    }

    @Test
    public void _02_Verify_Successful_Login_Attempt_For_All_Usernames_Given_On_Login_Page() {
        // TO-DO: Use Dataprovider and decorator pattern for this
        String[] usernames = {"standard_user", "problem_user", "performance_glitch_user", "error_user",
                "visual_user"};
        for (String username : usernames) {
            loginPage.loginWithUserInfo(username, "secret_sauce");
            ProductsPage productsPage = new ProductsPage(driver, log);
            productsPage.logout();
        }
    }
}
