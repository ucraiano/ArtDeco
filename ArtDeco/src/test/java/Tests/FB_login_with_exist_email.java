package Tests;

import Pages.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by savchenkoaleksandr on 4/20/17.
 */
public class FB_login_with_exist_email {
    WebDriver driver;
    Main obj_Main;
    SignIn obj_SignIn;
    SelectRole obj_Select_Role;
    SignUp obj_SignUp;
    Facebook obj_Facebook;
    ExtentReports extent;
    ExtentTest logger;

    @Parameters("browser")
    @BeforeClass
    public void set_up(String browser){
        if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();}
        else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "/Users/savchenkoaleksandr/Documents/Саша/Automation/chromedriver");
            driver = new ChromeDriver();}
//      driver = new FirefoxDriver();
        obj_Main = new Main(driver);
        obj_Facebook = new Facebook(driver);
        obj_SignIn = new SignIn(driver);
        driver.get("https://www.artdecobeauty.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (browser.equalsIgnoreCase("firefox")) {
            extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/FireFoxLoginReport.html", false);
            extent.addSystemInfo("Environment", "Selenium WebDriver > FireFox Driver");
            extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/firefox-login-extent-config.xml"));

        } else if (browser.equalsIgnoreCase("chrome")){
            extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/ChromeLoginReport.html", false);
            extent.addSystemInfo("Environment", "Selenium WebDriver > Chrome Driver");
            extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/chrome-login-extent-config.xml"));

        }
        extent.addSystemInfo("Host Name","artdecobeauty.com");
        extent.addSystemInfo("Developed by","Aleksandr Savchenko");
    }

    @Test()
    public void test_facebook_with_email_which_already_exist_in_artdeco() throws InterruptedException {
        logger = extent.startTest("test_facebook_with_email_which_already_exist_in_artdeco");
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setWait();
        obj_Main.setAccount_button();
        obj_SignIn.setFacebook();
        obj_Facebook.setEmail_field("ucraiano.test1@gmail.com");
        obj_Facebook.setPassword_field("REM4)nexuses");
        obj_Facebook.setLogin_button();
        Assert.assertTrue(obj_Main.is_successfully_logIN_and_logOUT_present().toLowerCase().contains("email has already been taken"));
    }

    @AfterMethod
    public void getReportResults(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.FAIL, "Failed Test Case is : "+result.getName());
            logger.log(LogStatus.FAIL, "Failed with next error : "+ result.getThrowable());
        }else if (result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Skipped Test Case is :"+result.getName());
        }else {
            logger.log(LogStatus.PASS,"Test Passed");
        }
        extent.endTest(logger);
    }


    @AfterClass
    public void set_down(){
        extent.flush();
        extent.close();
        driver.close();
        driver.quit();

    }

}
