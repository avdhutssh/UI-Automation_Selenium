package standAlone;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class standAloneScripts {
	static WebDriver driver;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	public static void takePageSS(String fileName) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("ScreenShots/" + fileName + ".png"));
	}

	@Test
	public void _01_Validate_Incorrect_Login_Attempt() throws IOException {
		// TO-DO can try with 3 combinations(Using excel reader) of wrong username and
		// pwd(w_user, c_pwd : c_user, w_pwd : w_user, w_pwd)
		String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";
		driver.findElement(By.cssSelector("#user-name")).sendKeys("incorrect_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("incorrect_password");
		driver.findElement(By.id("login-button")).click();
		WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		wt.until(ExpectedConditions.visibilityOf(errorMsg));
		Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);
		takePageSS("SA_InvalidLogin");
	}

}
