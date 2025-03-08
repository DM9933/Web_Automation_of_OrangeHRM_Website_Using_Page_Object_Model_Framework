package me.selenium.POM.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import me.selenium.POM.basedrivers.PageDriver;
import me.selenium.POM.utilities.ExcelUtils;
import me.selenium.POM.utilities.Screenshots;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class Employee_Page {

    ExtentTest test;
    ExcelUtils excelUtils = new ExcelUtils();

    public Employee_Page(ExtentTest test){
        PageFactory.initElements(PageDriver.getCurrentDriver(),this);
        this.test = test;
    }

    @FindBys({
            @FindBy(xpath = "//ul[@class='oxd-main-menu']/li[2]/a")
    })
    WebElement pim_option;

    @FindBys({
            @FindBy(xpath = "//div[@class='orangehrm-header-container']/button")
    })
    WebElement add;

    @FindBys({
            @FindBy(xpath = "//input[@name='firstName']")
    })
    WebElement firstName;

    @FindBys({
            @FindBy(xpath = "//input[@name='middleName']")
    })
    WebElement middleName;

    @FindBys({
            @FindBy(xpath = "//input[@name='lastName']")
    })
    WebElement lastName;

    @FindBys({
            @FindBy(xpath = "//button[@type='submit']")
    })
    WebElement buttonSave;


    public void passCase(String message){
        test.pass("<b><p style = 'color:green; font-size:14px'>" + message + "</p></b>");
    }

    public void passCaseWithSC(String message, String screenshotName) throws IOException {
        test.pass("<b><p style = 'color:green; font-size:14px'>" + message + "</p></b>");
        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotName +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" +""+ screenshotName + ".png";
        test.info(dest);
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
    }

    public void failCase(String message, String screenshotName) throws IOException {
        test.fail("<b><p style = 'color:red; font-size:14px'>" + message + "</p></b>");

        Throwable t = new InterruptedException("Exception");
        test.fail(t);

        String screenshotPath = Screenshots.capture(PageDriver.getCurrentDriver(),""+ screenshotName +"");
        String dest = System.getProperty("user.dir") + "\\screenshots\\" +""+ screenshotName + ".png";

        test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
        PageDriver.getCurrentDriver().quit();
    }

    public void add_employee() throws IOException, InterruptedException {
        test.info("Add Employee");

        try {
            pim_option.click();
            Thread.sleep(3000);
            passCase("Clicked on PIM Option");
        } catch (Exception e) {
            failCase("Failed to click on PIM Option", "PIMOption_click_fail");
        }

        try {
            add.click();
            Thread.sleep(3000);
            passCase("Clicked on Add Option");
        } catch (Exception e) {
            failCase("Failed to click on Add Option", "AddOption_click_fail");
        }

        try {
            firstName.sendKeys("Dabarjun");
            Thread.sleep(3000);
        } catch (Exception e) {
            failCase("Failed to enter first name", "FirstName_fail");
        }

        try {
            middleName.sendKeys("Mazumdar");
            Thread.sleep(3000);
        } catch (Exception e) {
            failCase("Failed to enter middle name", "MiddleName_fail");
        }

        try {
            lastName.sendKeys("Vasker");
            Thread.sleep(3000);
            passCaseWithSC("Information Provided", "pass");
        } catch (Exception e) {
            failCase("Failed to enter last name", "LastName_fail");
        }

        try {
            buttonSave.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            failCase("Failed to click on Save button", "SaveButton_click_fail");
        }
    }


}
