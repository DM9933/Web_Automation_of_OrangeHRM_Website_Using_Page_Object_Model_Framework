package me.selenium.POM.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import me.selenium.POM.basedrivers.PageDriver;
import me.selenium.POM.utilities.CommonMethods;
import me.selenium.POM.utilities.ExcelUtils;
import me.selenium.POM.utilities.Screenshots;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class Login_Page extends CommonMethods {

    ExtentTest test;
    ExcelUtils excelUtils = new ExcelUtils();

    public Login_Page(ExtentTest test){
        PageFactory.initElements(PageDriver.getCurrentDriver(),this);
        this.test = test;
    }

    //locators
    @FindBys({
            @FindBy(xpath = "//input[@name = 'username']"),
            @FindBy(xpath = "//input[@placeholder = 'Username']")
    })
    WebElement username;

    @FindBys({
            @FindBy(xpath = "//input[@name = 'password']"),
            @FindBy(xpath = "//input[@placeholder = 'Password']")
    })
    WebElement password;

    @FindBys({
            @FindBy(xpath = "//button[@type = 'submit']")
    })
    WebElement login_button;

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

        public void login() throws IOException {
            try{
                excelUtils.ReadExcel();
                test.info("Please enter your username");
                if(username.isDisplayed()){
                    username.sendKeys(excelUtils.username);
                    passCase("You have successfully entered your username");
                    Thread.sleep(5000);

                    try{
                        test.info("Please enter your password");
                        if(password.isDisplayed()){
                            password.sendKeys(excelUtils.password);
                            passCase("You have successfully entered your password");
                            Thread.sleep(5000);

                            try{
                                test.info("Please click on login Button");
                                if(login_button.isDisplayed()) {
                                    login_button.click();
                                    Thread.sleep(5000);
                                    passCaseWithSC("You have successfully login", "Login_success");
                                }
                            }catch (Exception e){
                                failCase("Login Button was not locatable. please check the log", "Login_Button_fail");

                            }

                        }
                    } catch (Exception e){
                        failCase("Password was not locatable. please check the log", "Password_fail");
                    }
                }
            } catch (Exception e){
                failCase("User name was not locatable. please check the log", "Username_fail");
            }
        }
}
