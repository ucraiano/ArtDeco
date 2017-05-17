package Tests;

import Pages.Main;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by savchenkoaleksandr on 5/17/17.
 */
public class Product_list_tests {
WebDriver driver;
Main obj_Main;
ExtentReports extent;
ExtentTest logger;

@Parameters("browser")
    @BeforeClass
    public void set_up(String browser){
    if (browser.equalsIgnoreCase("firefox")){
        driver = new FirefoxDriver();
    }else if (browser.equalsIgnoreCase("chrome")){
        System.setProperty("webdriver.chrome.driver","/Users/savchenkoaleksandr/Documents/Саша/Automation/chromedriver");
        driver = new ChromeDriver();
    }
    obj_Main = new Main(driver);
    driver.get("https://www.artdecobeauty.com");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    if (browser.equalsIgnoreCase("firefox")) {
        extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/FireFox/FireFoxProductList.html", false);
        extent.addSystemInfo("Environment", "Selenium WebDriver > FireFox Driver");
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/firefox-product-list-extent-config.xml"));

    } else if (browser.equalsIgnoreCase("chrome")){
        extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/Chrome/ChromeLProductList.html", false);
        extent.addSystemInfo("Environment", "Selenium WebDriver > Chrome Driver");
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/chrome-product-list-extent-config.xml"));
    }
    extent.addSystemInfo("Host Name","artdecobeauty.com");
    extent.addSystemInfo("Developed by","Aleksandr Savchenko");

}

    @Test
    public void test_filter(){
        logger = extent.startTest("test_filter");


    }
    @Test
    public void test_sort_by(){
        logger = extent.startTest("test_sort_by");

    }
    @Test
    public void test_show_all_show_18_per_page(){
        logger = extent.startTest("test_show_all_show_18_per_page");

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
