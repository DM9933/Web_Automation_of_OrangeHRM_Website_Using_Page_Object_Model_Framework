package me.selenium.POM.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import me.selenium.POM.basedrivers.PageDriver;
import me.selenium.POM.pages.Employee_Page;
import me.selenium.POM.pages.Login_Page;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.ExtentFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Add_Employee extends CommonMethods {

    ExtentReports extentReports;
    ExtentTest parentTest;
    ExtentTest childTest;


    public void sleep() throws InterruptedException {
        Thread.sleep(5000);
    }

    @BeforeClass
    public void openURL() throws InterruptedException {

        extentReports = ExtentFactory.getInstance();
        parentTest = extentReports.createTest("<p style = 'color:green; font-size:14px'> <b>OrangeHRM</b> </p>").assignAuthor("Dabarjun Mazumdar").assignDevice("Windows");
    }
    @Test
    public void orangeHRM_addEmployee() throws IOException, InterruptedException {
        childTest = parentTest.createNode("<p style = 'color:green; font-size:14px'> <b>Add Employee</b> </p>");
        Employee_Page employeePage = new Employee_Page(childTest);
        employeePage.add_employee();
    }

    @AfterClass
    public void report(){
        extentReports.flush();
    }

}
