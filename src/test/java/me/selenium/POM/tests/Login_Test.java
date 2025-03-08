package me.selenium.POM.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import me.selenium.POM.basedrivers.PageDriver;
import me.selenium.POM.pages.Login_Page;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.ExtentFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Login_Test extends CommonMethods {

    ExtentReports extentReports;
    ExtentTest parentTest;
    ExtentTest childTest;


    public void sleep() throws InterruptedException {
        Thread.sleep(5000);
    }

    @BeforeClass
    public void openURL() throws InterruptedException {
        PageDriver.getCurrentDriver().get(url);
        sleep();

        extentReports = ExtentFactory.getInstance();
        parentTest = extentReports.createTest("<p style = 'color:green; font-size:14px'> <b>OrangeHRM</b> </p>").assignAuthor("Dabarjun Mazumdar").assignDevice("Windows");
    }
    @Test
    public void orangeHRM_login() throws IOException {
        childTest = parentTest.createNode("<p style = 'color:green; font-size:14px'> <b>Login</b> </p>");
        Login_Page loginPage = new Login_Page(childTest);
        loginPage.login();
    }

    @AfterClass
    public void report(){
        extentReports.flush();
    }

}
