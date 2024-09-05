package com.swag.labs.BaseComponents;

import com.swag.labs.PageObjects.LoginPage;
import org.testng.Assert;

import java.util.function.Consumer;

public class LoginDecorators {
    static String[] usernames = {"standard_user", "problem_user", "performance_glitch_user", "error_user",
            "visual_user", "invalidUser", ""};

    //Login Attempts
    public static final Consumer<LoginPage> validLogin1 = (LP) -> LP.loginWithUserInfo(usernames[0], "secret_sauce");
    public static final Consumer<LoginPage> validLogin2 = (LP) -> LP.loginWithUserInfo(usernames[1], "secret_sauce");
    public static final Consumer<LoginPage> validLogin3 = (LP) -> LP.loginWithUserInfo(usernames[2], "secret_sauce");
    public static final Consumer<LoginPage> validLogin4 = (LP) -> LP.loginWithUserInfo(usernames[3], "secret_sauce");
    public static final Consumer<LoginPage> validLogin5 = (LP) -> LP.loginWithUserInfo(usernames[4], "secret_sauce");
    public static final Consumer<LoginPage> invalidLogin1 = (LP) -> LP.loginWithUserInfo(usernames[5], "secret_sauce");
    public static final Consumer<LoginPage> invalidLogin2 = (LP) -> LP.loginWithUserInfo(usernames[6], "secret_sauce");

    //validations
    public static final Consumer<LoginPage> successfulLogin = (LP) -> LP.logout();
    public static final Consumer<LoginPage> unsuccessfulLogin = (LP) -> Assert.assertTrue(LP.VerifyErrorMsgIsGettingDisplayed());

}
