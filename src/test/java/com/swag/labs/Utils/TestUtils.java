package com.swag.labs.Utils;

import com.swag.labs.BaseComponents.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils extends BaseTest {

    private static String getTodaysDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    private static String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }

    //static sleep
    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
}
