package com.swag.labs.Tests;

import com.swag.labs.BaseComponents.BaseTest;
import com.swag.labs.PageObjects.ProductsPage;
import com.swag.labs.Utilities.CsvDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class _01_ApplicationBehaviorForDifferentLoginAttempt extends BaseTest {
    @Test(dataProvider = "csvFileReader", dataProviderClass = CsvDataProviders.class)
    public void _01_Validate_Incorrect_Login_Attempt(Map<String, String> testData) {
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedMsg = testData.get("expectedResult");
        log.info("Starting _01_Validate_Incorrect_Login_Attempt for #username: " + username + " & password: " + password);
        loginPage.loginWithUserInfo(username, password);
        Assert.assertEquals(loginPage.getErrorMsg(), expectedMsg);
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
