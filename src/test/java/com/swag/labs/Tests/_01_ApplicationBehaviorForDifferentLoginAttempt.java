package com.swag.labs.Tests;

import com.swag.labs.BaseComponents.BaseTest;
import com.swag.labs.PageObjects.LoginPage;
import com.swag.labs.Utilities.CsvDataProviders;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.function.Consumer;

import static com.swag.labs.BaseComponents.LoginDecorators.*;

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

    @Test(dataProvider = "getData")
    public void _02_Verify_Successful_Login_Attempt_For_All_Usernames_Given_On_Login_Page(Consumer<LoginPage> consumer) {
        consumer.accept(loginPage);
    }

    @DataProvider
    public Object[] getData() {
        return new Object[]{
                validLogin1.andThen(successfulLogin),
                validLogin2.andThen(successfulLogin),
                validLogin3.andThen(successfulLogin),
                validLogin4.andThen(successfulLogin),
                validLogin5.andThen(successfulLogin),
                invalidLogin1.andThen(unsuccessfulLogin),
                invalidLogin2.andThen(unsuccessfulLogin)
        };
    }
}
