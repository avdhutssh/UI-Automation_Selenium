package standAlone;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class standAloneScripts {
    static WebDriver driver;
    WebDriverWait wt;
    String productName = "Sauce Labs Backpack";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wt = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static void takePageSS(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("ScreenShots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login(String userName, String pwd) {
        driver.findElement(By.cssSelector("#user-name")).sendKeys(userName);
        driver.findElement(By.cssSelector("#password")).sendKeys(pwd);
        driver.findElement(By.id("login-button")).click();
    }

    public static void fillCheckoutInfo(String fname, String lname, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(fname);
        driver.findElement(By.id("last-name")).sendKeys(lname);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        driver.findElement(By.id("continue")).click();
    }

    public static void SelectDropDown(WebElement ele, String value) {
        Select ddl = new Select(ele);
        ddl.selectByValue(value);
    }

    @Test
    public void _01_Validate_Incorrect_Login_Attempt() {
        // TO-DO: can try with 3 combinations(Using excel reader) of wrong username and
        // pwd(w_user, c_pwd : c_user, w_pwd : w_user, w_pwd) -> Done
        String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";
        driver.findElement(By.cssSelector("#user-name")).sendKeys("incorrect_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("incorrect_password");
        driver.findElement(By.id("login-button")).click();
        WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
        wt.until(ExpectedConditions.visibilityOf(errorMsg));
        Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);
        takePageSS("SA_InvalidLogin");
    }

    @Test
    public void _02_Add_Highest_Priced_Product_To_Cart_And_Checkout() {
        // TO-DO: create functions to generate random names and postal code and enter -> Done
        login("standard_user", "secret_sauce");
        WebElement dropDown = driver.findElement(By.cssSelector(".product_sort_container"));
        SelectDropDown(dropDown, "hilo");
        List<WebElement> cartBtns = wt.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//button[normalize-space(text())='Add to cart']")));
        cartBtns.get(0).click();
        takePageSS("ClickHighestPricedProduct");
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.xpath("//button[normalize-space(text())='Checkout']")).click();
        fillCheckoutInfo("Albus", "Severus", "9-3/4");
        WebElement checkoutHeader = wt
                .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("span[class='title']"))));
        Assert.assertEquals(checkoutHeader.getText(), "Checkout: Overview");
        driver.findElement(By.id("finish")).click();
        WebElement confirmMsg = wt
                .until(ExpectedConditions.visibilityOfElementLocated((By.className("complete-header"))));
        Assert.assertEquals(confirmMsg.getText(), "Thank you for your order!");
        takePageSS("SA_HighestPriceProduct_OrderPlaced");
    }

    @Test
    public void _03_Add_Specific_Product_To_Cart_And_Checkout() {
        login("standard_user", "secret_sauce");
        driver.findElement(By.xpath("//*[normalize-space(text())='" + productName + "']/../../..//button")).click();
        // TO-DO: Here instead of xpath create a function and check the product name and
        // click on add to cart button as per its index -> Done
        takePageSS("SA_ClickSpecificProduct");
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.xpath("//button[normalize-space(text())='Checkout']")).click();
        fillCheckoutInfo("Albus", "Severus", "9-3/4");
        WebElement checkoutHeader = wt
                .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("span[class='title']"))));
        Assert.assertEquals(checkoutHeader.getText(), "Checkout: Overview");
        driver.findElement(By.id("finish")).click();
        WebElement confirmMsg = wt
                .until(ExpectedConditions.visibilityOfElementLocated((By.className("complete-header"))));
        Assert.assertEquals(confirmMsg.getText(), "Thank you for your order!");
        takePageSS("SA_SpecificProduct_OrderPlaced");
    }

    @Test
    public void _04_Add_Specific_Product_To_Cart_Checkout_And_Verify_Cancel_Button_Functionality() {
        login("standard_user", "secret_sauce");
        driver.findElement(By.xpath("//*[normalize-space(text())='" + productName + "']/../../..//button")).click();
        takePageSS("ClickSpecificProduct");
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.xpath("//button[normalize-space(text())='Checkout']")).click();
        fillCheckoutInfo("Albus", "Severus", "9-3/4");
        WebElement title = wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
        Assert.assertEquals(title.getText(), "Checkout: Overview");
        driver.findElement(By.id("cancel")).click();
        WebElement homePageTitle = wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
        Assert.assertEquals(homePageTitle.getText(), "Products");
        takePageSS("SA_After_Cancel_Btn_Click");
    }

    @Test
    public void _05_Verify_Successful_Login_Attempt_For_All_Usernames_Given_On_Login_Page() {
        // TO-DO: Use Dataprovider and decorator pattern for this -> Done
        String[] usernames = {"standard_user", "problem_user", "performance_glitch_user", "error_user",
                "visual_user"};
        for (String username : usernames) {
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            WebElement menuButton = wt
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
            Assert.assertTrue(menuButton.isDisplayed());
            driver.findElement(By.id("react-burger-menu-btn")).click();
            wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link"))).click();
        }
    }
}
