package Tests;

import Pages.Main;
import Pages.Product_list_page;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by savchenkoaleksandr on 5/17/17.
 */
public class Product_list_tests {
WebDriver driver;
Main obj_Main;
Product_list_page obj_Product_list_page;
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
    obj_Product_list_page = new Product_list_page(driver);
    obj_Main = new Main(driver);
    driver.get("https://www.artdecobeauty.com");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    if (browser.equalsIgnoreCase("firefox")) {
        extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/FireFox/FireFoxProductList.html", true);
        extent.addSystemInfo("Environment", "Selenium WebDriver > FireFox Driver");
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/firefox-product-list-extent-config.xml"));

    } else if (browser.equalsIgnoreCase("chrome")){
        extent = new ExtentReports(System.getProperty("user.dir")+"/test-reports/Chrome/ChromeLProductList.html", true);
        extent.addSystemInfo("Environment", "Selenium WebDriver > Chrome Driver");
        extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-configs/chrome-product-list-extent-config.xml"));
    }
    extent.addSystemInfo("Host Name","artdecobeauty.com");
    extent.addSystemInfo("Developed by","Aleksandr Savchenko");

}
    @Test(priority = 1)
    public void select_category_FACE_and_check_default_filter_and_sort_values() throws InterruptedException {
        logger = extent.startTest("select_category_FACE_and_check_default_filter_and_sort_values");
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setWait();
        obj_Main.setFace_category();
        Assert.assertTrue(obj_Product_list_page.is_it_correct_category().toLowerCase().contains("face"));
        Assert.assertTrue(obj_Product_list_page.is_it_correct_filter_value().toLowerCase().contains("all"));
        Assert.assertTrue(obj_Product_list_page.is_it_correct_sort().toLowerCase().contains("best sellers"));
    }
    @Test(priority = 2)
    public void test_filter_Blusher(){
        logger = extent.startTest("test_filter_Blusher");
        obj_Product_list_page.setFilter_body();
        obj_Product_list_page.setChose_Blusher_filter();
        Assert.assertTrue(obj_Product_list_page.is_it_correct_filter_value().toLowerCase().contains("blusher"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("blusher"));
    }
    @Test(priority = 3)
    public void test_filter_Concealer(){
        logger = extent.startTest("test_filter_Concealer");
        obj_Product_list_page.setFilter_body();
        obj_Product_list_page.setChose_Concealer_filter();
        Assert.assertTrue(obj_Product_list_page.is_it_correct_filter_value().toLowerCase().contains("concealer"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("camouflage cream"));
    }
    @Test(priority = 4)
    public void test_filter_Face_Specials(){
        logger = extent.startTest("test_filter_Face_Specials");
        obj_Product_list_page.setFilter_body();
        obj_Product_list_page.setChose_Face_Specials_filter();
        Assert.assertTrue(obj_Product_list_page.is_it_correct_filter_value().toLowerCase().contains("face specials"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("fixing powder"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("fixing spray"));
    }
    @Test(priority = 5)
    public void test_filter_Foundation(){
        logger = extent.startTest("test_filter_Foundation");
        obj_Product_list_page.setFilter_body();
        obj_Product_list_page.setChose_Foundation_filter();
        Assert.assertTrue(obj_Product_list_page.is_it_correct_filter_value().toLowerCase().contains("foundation"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("high performance lifting foundation"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("double finish"));
    }
    @Test(priority = 6)
    public void test_filter_Powder(){
        logger = extent.startTest("test_filter_Powder");
        obj_Product_list_page.setFilter_body();
        obj_Product_list_page.setChose_Powder_filter();
        Assert.assertTrue(obj_Product_list_page.is_it_correct_filter_value().toLowerCase().contains("powder"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("sun kissed bronzing set"));
        Assert.assertTrue(obj_Product_list_page.is_filtered_products_present().toLowerCase().contains("glow bronzer"));
    }
    @Test(priority = 7)
    public void test_sort_by_Newest() throws Exception {
        logger = extent.startTest("test_sort_by_Newest");
        obj_Product_list_page.setSort_by_body();
        obj_Product_list_page.setChoose_sort_by_Newest();
        Assert.assertTrue(obj_Product_list_page.is_first_product_correct().toLowerCase().contains("glow bronzer"));

    }

    @Test(priority = 8)
    public void test_sort_by_Best_Sellers(){
        logger = extent.startTest("test_sort_by_Best_Sellers");
        obj_Product_list_page.setSort_by_body();
        obj_Product_list_page.setChoose_sort_by_BestSellers();
        Assert.assertTrue(obj_Product_list_page.is_first_product_correct().toLowerCase().contains("sun kissed bronzing set"));
    }
    @Test(priority = 9)
    public void test_sort_by_Price_High_Low(){
        logger = extent.startTest("test_sort_by_Price_High_Low");
        obj_Product_list_page.setSort_by_body();
        obj_Product_list_page.setChoose_sort_by_Price_High_Low();
        //obj_Product_list_page.is_first_price_is_highest();
        Assert.assertTrue(obj_Product_list_page.is_first_product_price_highest());
    }
    @Test(priority = 10)
    public void test_sort_by_Price_Low_High(){
        logger = extent.startTest("test_sort_by_Price_Low_High");
        obj_Product_list_page.setSort_by_body();
        obj_Product_list_page.setChoose_sort_by_Price_Low_High();
        Assert.assertTrue(obj_Product_list_page.is_first_product_price_lowest());

    }

    @Test(priority = 11)
    public void test_sort_by_name_A_Z(){
        logger = extent.startTest("test_sort_by_name_A_Z");
        obj_Product_list_page.setSort_by_body();
        obj_Product_list_page.setChoose_sort_by_A_Z();
        Assert.assertTrue(obj_Product_list_page.is_sort_A_Z_correct());
    }



    @Test(priority = 12)
    public void test_sort_by_name_Z_A(){
        logger = extent.startTest("test_sort_by_name_Z_A");
        obj_Product_list_page.setSort_by_body();
        obj_Product_list_page.setChoose_sort_by_Z_A();
        Assert.assertTrue(obj_Product_list_page.is_sort_Z_A_correct());

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
