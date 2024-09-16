package com.swag.labs.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File reportsDir = new File(System.getProperty("user.dir") + File.separator + "Reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
//        File extentReportFile = new File(reportsDir + File.separator + timestamp + "_extentReport.html");
        File extentReportFile = new File(reportsDir + File.separator + "extentReport.html");

        ExtentReports extentReport = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Test Suite Execution for Sauce Lab");
        sparkReporter.config().setDocumentTitle("Automation Execution Report");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy hh:mm:ss");
        extentReport.attachReporter(sparkReporter);

        Properties prop = new ConfigurationUtils().getProperty();

        extentReport.setSystemInfo("Executed by", "Avdhut Satish Shirgaonkar");
        extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        return extentReport;

    }
}
