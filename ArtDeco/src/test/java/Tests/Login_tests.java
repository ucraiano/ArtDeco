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
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by savchenkoaleksandr on 4/14/17.
 */
public class Login_tests {
WebDriver driver;
Main obj_Main;
SignIn obj_SignIn;
Facebook obj_Facebook;
ExtentReports extent;
ExtentTest logger;
String chrome;

@Parameters("browser")
@BeforeClass
    public void set_up(String browser){
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();}
        else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/Users/savchenkoaleksandr/Documents/Саша/Automation/chromedriver");
            driver = new ChromeDriver();
            chrome = "chrome";
        }
        obj_Main = new Main(driver);
        obj_SignIn = new SignIn(driver);
        obj_Facebook = new Facebook(driver);
        driver.get("https://www.artdecobeauty.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (browser.equalsIgnoreCase("firefox")) {
            extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/FireFox/FireFoxLoginReport.html", true);
            extent.addSystemInfo("Environment", "Selenium WebDriver > FireFox Driver");
            extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/firefox-login-extent-config.xml"));
        } else if (browser.equalsIgnoreCase("chrome")){
            extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/Chrome/ChromeLoginReport.html", true);
            extent.addSystemInfo("Environment", "Selenium WebDriver > Chrome Driver");
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/chrome-login-extent-config.xml"));

        }
        extent.addSystemInfo("Host Name","artdecobeauty.com");
        extent.addSystemInfo("Developed by","Aleksandr Savchenko");

}


@Test(priority = 1)
    public void test_empty_fields() throws InterruptedException {
        logger = extent.startTest("test_empty_fields");
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setWait();
        obj_Main.setAccount_button();
        obj_SignIn.setEmail_field("");
        obj_SignIn.setPassword_field("");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_incorrect_login_alert_present().toLowerCase().contains("invalid email or password."));
        obj_SignIn.setClose_allert_incorect_login();

}
@Test(priority = 2)
    public void test_not_email(){
        logger = extent.startTest("test_not_email");
        obj_SignIn.setEmail_field("asdasd.com");
        Assert.assertTrue(obj_SignIn.is_email_label_present());
        obj_SignIn.setPassword_field("123");
        Assert.assertTrue(obj_SignIn.is_password_label_present());
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_it_signIN_page().toLowerCase().contains("sign in"));
        Assert.assertTrue(obj_SignIn.is_facebook_present().toLowerCase().contains("sign in with facebook"));
}
@Test(priority = 3)
    public void test_non_existing_email(){
        logger = extent.startTest("test_non_existing_email");
        obj_SignIn.setEmail_field("123@gmail.com");
        obj_SignIn.setPassword_field("123123");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_incorrect_login_alert_present().toLowerCase().contains("invalid email or password."));
        obj_SignIn.setClose_allert_incorect_login();

}

@Test(priority = 4)
    public void test_existing_email_and_non_existing_password(){
        logger = extent.startTest("test_existing_email_and_non_existing_password");
        obj_SignIn.setEmail_field("ucraiano.test1@gmail.com");
        obj_SignIn.setPassword_field("123123123");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_incorrect_login_alert_present().toLowerCase().contains("invalid email or password."));
        obj_SignIn.setClose_allert_incorect_login();
}

@Test(priority = 5)
// Chrome WebDriver allow spaces in email field
    public void test_correct_email_with_one_space_in_the_beginning(){
        if(chrome != null) {
            logger = extent.startTest("test_correct_email_with_one_space_in_the_beginning");
            throw new SkipException("Case was skipped because of Chrome");
        }else {
            logger = extent.startTest("test_correct_email_with_one_space_in_the_beginning");
            obj_SignIn.setEmail_field(" ucraiano.test1@gmail.com");
            obj_SignIn.setPassword_field("REM4)nexuses");
            obj_SignIn.setSign_IN_button();
            Assert.assertTrue(obj_SignIn.is_it_signIN_page().toLowerCase().contains("sign in"));
            Assert.assertTrue(obj_SignIn.is_facebook_present().toLowerCase().contains("sign in with facebook"));
        }
}
@Test(priority = 6)
    public void test_correct_email_with_one_space_in_the_end(){
        if(chrome !=null){
            logger = extent.startTest("test_correct_email_with_one_space_in_the_end");
            throw new SkipException("Case was skipped because of Chrome");
        }else {
            logger = extent.startTest("test_correct_email_with_one_space_in_the_end");
            obj_SignIn.setEmail_field("ucraiano.test1@gmail.com ");
            obj_SignIn.setPassword_field("REM4)nexuses");
            obj_SignIn.setSign_IN_button();
            Assert.assertTrue(obj_SignIn.is_it_signIN_page().toLowerCase().contains("sign in"));
            Assert.assertTrue(obj_SignIn.is_facebook_present().toLowerCase().contains("sign in with facebook"));
        }
}

@Test(priority = 7)
    public void password_with_one_space_in_the_beginning(){
        logger = extent.startTest("password_with_one_space_in_the_beginning");
        obj_SignIn.setEmail_field("ucraiano.test1@gmail.com");
        obj_SignIn.setPassword_field(" REM4)nexuses");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_it_signIN_page().toLowerCase().contains("sign in"));
        Assert.assertTrue(obj_SignIn.is_facebook_present().toLowerCase().contains("sign in with facebook"));
}

@Test(priority = 8)
    public void password_with_one_space_in_the_end(){
        logger = extent.startTest("password_with_one_space_in_the_end");
        obj_SignIn.setEmail_field("ucraiano.test1@gmail.com");
        obj_SignIn.setPassword_field("REM4)nexuses ");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_it_signIN_page().toLowerCase().contains("sign in"));
        Assert.assertTrue(obj_SignIn.is_facebook_present().toLowerCase().contains("sign in with facebook"));
}
@Test(priority = 9)
    public void test_correct_email_and_password() {
    logger = extent.startTest("test_correct_email_and_password");
    obj_SignIn.setEmail_field("ucraiano.test1@gmail.com");
    obj_SignIn.setPassword_field("REM4)nexuses");
    obj_SignIn.setSign_IN_button();
    Assert.assertTrue(obj_Main.is_successfully_logIN_and_logOUT_present().toLowerCase().contains("logged in successfully"));
    obj_Main.setClose_successfully_login_alert();
    Assert.assertTrue(obj_Main.is_it_my_account_button().toLowerCase().contains("my account"));
}
@Test(priority = 10)
    public void test_logOut(){
    logger = extent.startTest("test_logOut");
    obj_Main.setMy_account();
    obj_Main.setLogout_my_account();
    Assert.assertTrue(obj_Main.is_successfully_logIN_and_logOUT_present().toLowerCase().contains("signed out successfully"));
    Assert.assertTrue(obj_Main.is_it_signin_button().toLowerCase().contains("sign in"));
}
@AfterMethod
    public void getReportResults(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.FAIL, "Failed Test Case is : "+result.getName());
            logger.log(LogStatus.FAIL, "Failed with next error : "+ result.getThrowable());
        }else if (result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Skipped Test Case is : " + result.getName());
            logger.log(LogStatus.SKIP, "Skipped with next message : " + result.getThrowable());
        }else if (result.getStatus() == ITestResult.SUCCESS){
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
