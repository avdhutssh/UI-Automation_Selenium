<!--
    #/**
    # * @author Avdhut Shirgaonkar
    # * Email: avdhut.ssh@gmail.com
    # * LinkedIn: https://www.linkedin.com/in/avdhut-shirgaonkar-811243136/
    # */
    #/***************************************************/
-->

---

# 🎨 UI Automation- Selenium(Java)

```plaintext
|src/
├── main/
│   ├── java/
│   │   ├── com.swag.labs/
│   │   │   ├── 1. Utilities/               # Utility classes for various tasks
│   │   │   │   ├── BrowserDriverFactory.java  # Utility class for browser driver setup
│   │   │   │   ├── CSVDataReader.java         # Utility class for reading CSV files
│   │   │   │   ├── JSONDataReader.java        # Utility class for reading JSON files
│   │   │   │   ├── ElementUtils.java          # Utility class for common web element actions
│   │   │   │   ├── ConfigurationUtils.java    # Utility class for configuration management(app.properties file read)
│   │   │   │   ├── TestUtils.java             # Utility class for common functions use by page functions like takingSS, getBrowserLogs,getCurrentDate
│   │   │   │   ├── ExtentReporter.java        # Utility class for implmenting extent report
│   │   │   │   ├── BasePage.java              # Parent Utility class for all utils classes defining WebDriver and Wait instances for further use
│   │   │   ├── 2. PageObjects/                # Page Object classes
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── ProductPage.java
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── CheckoutPage.java
│   │   │   │   ├── CheckoutOverviewPage.java
│   │   │   │   ├── CheckoutCompletePage.java
│   │   │   ├── 3. Components/               # Create component classes following SRP principle
│   └── resources/                           # Configuration files (e.g., log4j.properties)
│       ├── log4j.xml          # Log4j configuration file
│       ├── app.properties     # Application data like url, username, password, browser to use
├── test/
│   ├── java/
│   │   ├── com.swag.labs/
│   │   │   ├── 1. BaseComponents/                # Base test classes
│   │   │   │   ├── BaseTest.java    # Base test class with common setup and teardown methods
│   │   │   │   ├── TestListener.java    # Listener class
│   │   │   │   ├── RetryTestListener.java    # Retry listener class for executing failed test cases
│   │   │   ├── 2. Tests/               # Test classes
│   │   │   │   ├── LoginTest.java   # Test class for login functionality
│   │   │   │   ├── AddProductToCartTest.java    # Test class for products page functionality
│   │   │   │   ├── CartTest.java    # Test class for cart functionality
│   │   │   ├── 3. utils/               # Utility classes for various tasks
│   │   │   │   ├── AssertionUtils.java        # Utility class for assertions and verifications
│   │   │   │   ├── JavaUtils.java        # Utility class for creating common function such as generting random names, numbers
│   └── resources/
│       └── TestData/                            # Data files for data-driven testing
│           ├── testdata.csv                 # CSV file for test data
│           ├── testdata.json                # JSON file for test data
```
