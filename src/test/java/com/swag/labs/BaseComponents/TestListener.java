package com.swag.labs.BaseComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.swag.labs.Utilities.ExtentReporter;
import com.swag.labs.Utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    ExtentTest test;
    ExtentReports extentReport;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReport.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, result.getName() + " started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            String destPath = TestUtils.takeScreenshot(driver, result.getTestName());
            try {
                extentTest.get().addScreenCaptureFromPath(destPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        extentTest.get().log(Status.INFO, result.getThrowable());
        extentTest.get().log(Status.FAIL, result.getName() + " got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.INFO, result.getThrowable());
        extentTest.get().log(Status.SKIP, result.getName() + " got skipped");
    }


    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();

    }
}
