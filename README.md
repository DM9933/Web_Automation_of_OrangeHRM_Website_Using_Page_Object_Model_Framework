# Web Automation of OrangeHRM Website Using Page Object Model Framework - TestNG - ExtentReport

## Project Overview

This project demonstrates the **Web Automation** of the **OrangeHRM** website using the **Page Object Model (POM)** framework. The automation tests are developed using **Selenium WebDriver** with the **TestNG** framework for efficient test execution and reporting. The project includes **ExtentReports** for detailed and visually appealing test reports.

This project is structured to test various functionalities on the OrangeHRM website, such as login, and employee addition, by creating automated test scripts. The tests are run using TestNG, and the results are captured using **ExtentReports**, with **screenshots** taken at key points during the execution.

## Features and Technologies Used

- **Selenium WebDriver**: For browser automation.
- **Page Object Model (POM)**: To create an object-oriented approach for managing the page objects.
- **TestNG**: For test execution and parallel test runs.
- **ExtentReports**: For generating reports with detailed information about the test execution.
- **Apache POI**: To read Excel files for data-driven testing.
- **Maven**: For dependency management and project build automation.

## Project Structure

The project is organized as follows:

- **BaseDriver**: Contains the setup and teardown methods to initialize the WebDriver and close the browser after tests.
- **Pages**: Contains the Page Object classes that represent various pages of the OrangeHRM website.
- **Tests**: Contains the test cases like Login_Test, Add_Employee, etc.
- **Utilities**: Contains helper classes like `CommonMethods.java`, `ExcelUtils.java`, `ExtentFactory.java`, and `Screenshots.java` for various utility functions used across tests.

### Directory Structure

```plaintext
src/
  |-- me/
       |-- selenium/
            |-- POM/
                |-- basedrivers/
                |-- pages/
                |-- tests/
                |-- utilities/
```

## Example Code Snippets

### Login_Test.java

```java
package me.selenium.POM.tests;

import me.selenium.POM.pages.LoginPage;
import me.selenium.POM.utilities.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Login_Test {
    WebDriver driver;

    @Test
    public void loginTest() throws Exception {
        driver = BaseDriver.getDriver();
        ExcelUtils excel = new ExcelUtils();
        excel.ReadExcel();
        LoginPage login = new LoginPage(driver);
        login.login(excel.username, excel.password);
        // Assert login success here
    }
}
```
[View Login_Test.java](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/src/test/java/me/selenium/POM/tests/Login_Test.java)


### LoginPage.java
```java
package me.selenium.POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    By usernameField = By.id("txtUsername");
    By passwordField = By.id("txtPassword");
    By loginButton = By.id("btnLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
```
[View Login_Page.java](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/src/test/java/me/selenium/POM/pages/Login_Page.java)

### Add_Employee.java
```java
package me.selenium.POM.tests;

import me.selenium.POM.pages.EmployeePage;
import me.selenium.POM.utilities.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AddEmployee {
    WebDriver driver;

    @Test
    public void addEmployeeTest() throws Exception {
        driver = BaseDriver.getDriver();
        ExcelUtils excel = new ExcelUtils();
        excel.ReadExcel();
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.addEmployee(excel.firstName, excel.lastName, excel.employeeID);
        // Assert employee addition success here
    }
}
```
[View Add_Employee.java](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/src/test/java/me/selenium/POM/tests/Add_Employee.java)

### Employee_Page.java
```java
package me.selenium.POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmployeePage {
    WebDriver driver;

    By firstNameField = By.id("firstName");
    By lastNameField = By.id("lastName");
    By employeeIDField = By.id("employeeId");
    By saveButton = By.id("saveBtn");

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void addEmployee(String firstName, String lastName, String employeeID) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(employeeIDField).sendKeys(employeeID);
        driver.findElement(saveButton).click();
    }
}

```
[View Employee_Page.java](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/src/test/java/me/selenium/POM/pages/Employee_Page.java)

### ExcelUtils.java
```java
package me.selenium.POM.utilities;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static String username;
    public static String password;

    public void ReadExcel() throws IOException {
        String excelFilePath = "./TestData/TestData.xlsx";
        File file = new File(excelFilePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(1);

        XSSFCell cell = row.getCell(0);
        username = cell.getStringCellValue();
        cell = row.getCell(1);
        password = cell.getStringCellValue();
    }
}
```
[View ExcelUtils.java](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/src/test/java/me/selenium/POM/utilities/ExcelUtils.java)


### ExtentFactory.java
```java
package me.selenium.POM.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getInstance() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/Report.html");
        reporter.config().setReportName("Automation - Debarjun Mazumdar");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
```
[View ExtentFactory](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/src/test/java/me/selenium/POM/utilities/ExtentFactory.java)


### Test Report 
#### OrangeHRm - Login Success Report
![Login Successful Report](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/reports/OrangeHRM1.png)

#### OrangeHRM - Add Employee Success Report
![Report 2](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/reports/OrangeHRM2.png)


### Watch the Automation Test Video
![Watch the video](https://github.com/DM9933/Web_Automation_of_OrangeHRM_Website_Using_Page_Object_Model_Framework/blob/main/OrangeHRM_Automation.gif)












