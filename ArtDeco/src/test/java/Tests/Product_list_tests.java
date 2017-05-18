package Tests;

import Pages.Main;
import Pages.Product_list_page;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
    obj_Main = new Main(driver);
    obj_Product_list_page = new Product_list_page(driver);
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
    @Test(priority = 1)
    public void select_category_FACE() throws InterruptedException {
        logger = extent.startTest("select_category_FACE");
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

    /*
    @Test(priority = 3)
    public void select_category_EYES(){
        logger = extent.startTest("select_category_EYES");
        obj_Main.setEyes_category();
        Assert.assertTrue(obj_Product_list_paga.is_it_correct_category().toLowerCase().contains("eyes"));
    }
    @Test(priority = 3)
    public void select_category_LIPS(){
        logger = extent.startTest("select_category_LIPS");
        obj_Main.setLips_category();
        Assert.assertTrue(obj_Product_list_paga.is_it_correct_category().toLowerCase().contains("lips"));
    }
    @Test(priority = 4)
    public void select_category_Nails(){
        logger = extent.startTest("select_category_Nails");
        obj_Main.setNails_category();
        Assert.assertTrue(obj_Product_list_paga.is_it_correct_category().toLowerCase().contains("nails"));
    }
    @Test(priority = 5)
    public void select_category_SKIN(){
        logger = extent.startTest("select_category_SKIN");
        obj_Main.setSkin_category();
        Assert.assertTrue(obj_Product_list_paga.is_it_correct_category().toLowerCase().contains("skin"));
    }
    @Test(priority = 6)
    public void select_category_ACCESSORIES(){
        logger = extent.startTest("select_category_ACCESSORIES");
        obj_Main.setAccessories_category();
        Assert.assertTrue(obj_Product_list_paga.is_it_correct_category().toLowerCase().contains("accessories"));
    }
    @Test(priority = 7)
    public void select_category_KITS(){
        logger = extent.startTest("select_category_KITS");
        obj_Main.setKits_category();
        Assert.assertTrue(obj_Product_list_paga.is_it_KITS_category().toLowerCase().contains("kits"));
    }
/*
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
    */

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
