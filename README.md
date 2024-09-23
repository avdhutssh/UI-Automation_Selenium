<!--
    #/**
    # * @author Avdhut Shirgaonkar
    # * Email: avdhut.ssh@gmail.com
    # * LinkedIn: https://www.linkedin.com/in/avdhut-shirgaonkar-811243136/
    # */
    #/***************************************************/
-->

# 💻 UI Automation Using Selenium, Java (Maven Project)

## 📑 Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Test Execution](#test-execution)
- [Data-Driven Testing](#data-driven-testing)
- [Reporting](#reporting)
- [CI/CD Using Jenkins](#cicd-using-jenkins)
- [CI/CD Using GitHub Actions](#cicd-using-github-actions)
- [Contacts](#contacts)

## 📖 Introduction

This repository contains a Selenium-based UI automation framework written in Java, utilizing Maven as the build tool for managing dependencies. The framework is designed to automate test cases for the [SauceDemo](https://www.saucedemo.com/) web application. It follows best practices such as the Page Object Model (POM) approach, which enhances test case management and maintainability by separating test logic from the user interface structure. Test execution is optimized with parallel processing using **TestNG**, and data-driven testing is implemented through the use of CSV and Json files. Detailed reporting is generated using **Extent Reports**, providing comprehensive logs, test results, and screenshots for easy analysis.

The framework also leverages **Object-Oriented Programming (OOP)** concepts, including inheritance, encapsulation, and polymorphism, to promote code reusability and modularity. Several design patterns are implemented, adhering to the Single Responsibility Principle (SRP), such as the **Factory Pattern**, **Strategy Pattern**, and **Decorator Pattern**, ensuring scalability, flexibility, and ease of maintenance.

Additionally, **Log4J** is integrated for effective logging, providing detailed runtime information and debugging support throughout the test execution process.

The project supports (CI/CD) pipelines via **Jenkins** and **GitHub Actions**, enabling automated test execution, seamless integration with version control, and streamlined reporting.

## 🛠️ Prerequisites

Before you start, ensure you have the following installed on your machine:

- **Java Development Kit (JDK)**: Version 8 or later.
- **Maven**: To manage project dependencies.
- **Git**: To clone the repository.
- **An IDE**: (such as IntelliJ IDEA or Eclipse) with TestNG plugin installed

For CI/CD:

- **Jenkins**: For automated build and test execution.
- **GitHub Actions**: Integrated for CI pipeline.

## 📁 Project Structure

This project follows the **Page Object Model (POM)** approach and includes various components following SOLID principles. The structure is hybrid and designed for maintainability and scalability. Below is the high-level structure of the project:

Here is a detailed overview of the project structure:

```plaintext
| src/
├── main/
│   ├── java/
│   │   ├── com.swag.labs/
│   │   │   ├── 1. Utilities/               # Utility classes for various tasks
│   │   │   │   ├── BrowserDriverFactory.java  # Setup and configuration of browser drivers
│   │   │   │   ├── CSVDataReader.java         # Reads data from CSV files
│   │   │   │   ├── JSONDataReader.java        # Reads data from JSON files
│   │   │   │   ├── ElementUtils.java          # Common methods to interact with web elements
│   │   │   │   ├── ConfigurationUtils.java    # Loads and handles application config (e.g. app.properties)
│   │   │   │   ├── ExtentReporter.java        # Manages Extent report integration
│   │   │   │   ├── BasePage.java              # Base class for all page objects, defines driver and waits
│   │   │   ├── 2. PageObjects/                # Page Object classes for the website's pages
│   │   │   │   ├── LoginPage.java             # Methods and actions for Login page
│   │   │   │   ├── ProductPage.java           # Methods and actions for Product page
│   │   │   │   ├── CartPage.java              # Methods and actions for Cart page
│   │   │   │   ├── CheckoutPage.java          # Methods for Checkout process
│   │   │   │   ├── CheckoutCompletePage.java  # Final Checkout confirmation page methods
│   │   │   ├── 3. Components/                 # Components built following Single Responsibility Principle (SRP)
│   └── resources/                             # Configuration files (e.g., log4j.xml, config.properties)
├── test/
│   ├── java/
│   │   ├── com.swag.labs/
│   │   │   ├── 1. BaseComponents/            # Shared base components for tests
│   │   │   │   ├── BaseTest.java             # Base test class with setup/teardown methods
│   │   │   │   ├── TestListener.java         # Listener for Extent Reports and Log4j integration
│   │   │   │   ├── RetryTestListener.java    # Logic to retry failed test cases
│   │   │   ├── 2. Tests/                     # Test cases
│   │   │   │   ├── _01_ApplicationBehaviorForDifferentLoginAttempt.java
│   │   │   │   ├── _02_Add_Product_To_Cart_And_Checkout.java
│   │   │   │   ├── _03_Add_Specific_Product_To_Cart_And_Checkout.java
│   │   │   │   ├── _04_Add_Product_To_Cart_Checkout_And_Verify_Cancel_Button_Functionality
│   │   ├── 3. utils/                         # Test utilities for custom actions
│   │   │   │   ├── AssertionUtils.java       # Utilities for assertions
│   │   │   │   ├── TestUtils.java            # General helper methods (e.g., screenshots)
│   └── resources/                            # Test resources like data files
│       └── TestData/
│           ├── testdata.csv                  # CSV Test data for data-driven tests
│           ├── testdata.json                 # JSon Test data for data-driven tests
├── Reports/                                  # Extent reports generated during test execution
├── ScreenShot/                               # Folder for screenshots taken during tests
├── target/                                   # Compiled output and plugins info
├── test-output/                              # Default TestNG output folder
├── pom.xml                                   # Maven project file for dependencies and plugins
└── TestNG.xml                                # TestNG configuration file for managing test suite execution
```

## ▶️ Getting Started

1. Clone the repository:

```bash
git clone https://github.com/avdhutssh/UI-Automation_Selenium.git
```

2. Navigate to the project directory:

```bash
cd UI-Automation_Selenium
```

3. Run Maven clean install to resolve dependencies:

```bash
mvn clean install
```

This will download all required dependencies such as Selenium, TestNG, Extent Reports, and Log4j.

## 🚀 Test Execution

You can run the test cases using TestNG directly from the IDE or from the command line:

```bash
mvn test
```

## 📊 Data-Driven Testing

The framework supports data-driven testing using the DataProvider feature in TestNG, with data being sourced from CSV or JSON files.

Example for Incorrect_Login_Attempt test case:

```bash
    @Test(dataProvider = "csvFileReader", dataProviderClass = CsvDataProviders.class)
    public void _01_Validate_Incorrect_Login_Attempt(Map<String, String> testData) {
        String username = testData.get("username");
        String password = testData.get("password");
        String expectedMsg = testData.get("expectedResult");
        log.info("Starting _01_Validate_Incorrect_Login_Attempt for #username: " + username + " & password: " + password);
        loginPage.loginWithUserInfo(username, password);
        Assert.assertEquals(loginPage.getErrorMsg(), expectedMsg);
    }
```

## 🎯 Reporting

This project integrates **Extent Reports** for detailed reporting of test executions, including logs, screenshots, and status updates.

To view the generated reports:

1. After test execution, navigate to the `Reports/` directory.
2. Open the `ExtentReport.html` file to see a detailed execution report.

You can also capture screenshots for failed test cases and view them in the Extent Report.

![Extent Report](/Misc/ExtentReport.PNG)

## 🤖 CI/CD Using Jenkins and GitHub Actions

### 1. Jenkins Integration

You can integrate the project with Jenkins for Continuous Integration. Follow these steps:

1. Install Maven and TestNg reporter plugin
2. Set up a Maven Project Dashboard in Jenkins.
3. Clone the GitHub repository under Source Code Management
4. In the Build section, add the following command to run the tests:
   ```bash
   test   (mvn test not needed as its a maven dashboard)
   ```
5. After the build, the reports are automatically generated in the `Reports/` folder amd also you can view TestNg reports

![Jenkins](/Misc/Jenkins.PNG)

### 2. GitHub Actions

This project also uses **GitHub Actions** for CI/CD:

- The CI pipeline triggers on every push or pull request to the main branch.
- It automatically runs the test cases using the TestNG suite on github provided Windows machine and generates the Extent Reports as Artifacts.
- The results can be viewed in the `Actions` tab of the GitHub repository.
- Refer workflow yaml file for same. [Workflow file](/.github/workflows/maven.yml)
  ![GitHub Actions](/Misc/GitHub_Actions.PNG)

## 📧 Contacts

- [![Email](https://img.shields.io/badge/Email-avdhut.ssh@gmail.com-green)](mailto:avdhut.ssh@gmail.com)
- [![LinkedIn](https://img.shields.io/badge/LinkedIn-Profile-blue)](https://www.linkedin.com/in/avdhut-shirgaonkar-811243136/)

Feel free to reach out if you have any questions, or suggestions.

Happy Learning!

Avdhut Shirgaonkar
