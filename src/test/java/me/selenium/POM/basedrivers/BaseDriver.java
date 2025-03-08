package me.selenium.POM.basedrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseDriver {

    public static String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    public static WebDriver driver;

    @BeforeSuite
    public void startBrowser(){

        String browser_name = "chrome";

        if (browser_name.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }else if(browser_name.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }else{
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }

        PageDriver.getInstance().setDriver(driver);
    }

    @AfterSuite
    public void closeBrowser(){
        driver.quit();
    }

}
