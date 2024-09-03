package com.swag.labs.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationUtils {

    public Properties getProperty() {
        Properties prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");

        if (propFile.exists()) {
            try (FileInputStream fis = new FileInputStream(propFile)) {
                prop.load(fis);
            } catch (IOException e) {
                System.err.println("Error loading properties file: " + e.getMessage());
            }
        } else {
            System.err.println("Properties file not found at the specified location: " + propFile.getAbsolutePath());
        }
        return prop;
    }
}
