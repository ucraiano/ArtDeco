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
 * Created by savchenkoaleksandr on 4/24/17.
 */
public class Search_tests {
    // What driver we'll use ?________________________________________________________

    //String sDriver = "FirefoxDriver";
    //String sDriver = "ChromeDriver";


    //________________________________________________________________________________
    WebDriver driver;
    Main obj_Main;
    Search_result obj_Search_result;
    ExtentReports extent;
    ExtentTest logger;

    @Parameters("browser")
        @BeforeTest
        public void set_up (String browser) {


        if (browser.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/Users/savchenkoaleksandr/Documents/Саша/Automation/chromedriver");
                driver = new ChromeDriver();
            }
            obj_Main = new Main(driver);
            obj_Search_result = new Search_result(driver);
            driver.get("https://www.artdecobeauty.com/");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            if (browser.equalsIgnoreCase("firefox")) {
                extent = new ExtentReports(System.getProperty("user.dir") + "/test-reports/FireFox/FireFoxSearchReport.html", true);
                extent.addSystemInfo("Environment", "Selenium WebDriver > FireFox Driver");
                extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-configs/firefox-search-extent-config.xml"));

            } else if (browser.equalsIgnoreCase("chrome")){
                extent = new ExtentReports(System.getProperty("user.dir") + "/test-reports/Chrome/ChromeSearchReport.html", true);
                extent.addSystemInfo("Environment", "Selenium WebDriver > Chrome Driver");
                extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-configs/chrome-search-extent-config.xml"));

            }
            extent.addSystemInfo("Developed by", "Aleksandr Savchenko");
            extent.addSystemInfo("Host Name", "artdecobeauty.com");
        }

        @Test(/*priority = 1*/)
        public void test_correct_full_name () throws InterruptedException {
            logger = extent.startTest("test_correct_full_name");
            obj_Main.setFirst_visit_alert_close();
            obj_Main.setWait();
            obj_Main.setSearch_open();
            obj_Main.setSearch_input("camouflage cream");
            obj_Main.setSearch_start();
            Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
            Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("camouflage cream"));
            Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
            Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("camouflage cream"));
            Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("camouflage cream"));

        }

        @Test(/*priority = 2,*/ dependsOnMethods = {"test_correct_full_name"})
        public void test_hint_presents_and_start_search_from_hint_in_main_page () {
            logger = extent.startTest("test_hint_presents_and_start_search_from_hint_in_main_page");
            obj_Main.setHome();
            obj_Main.setSearch_open();
            obj_Main.setSearch_input("Sun Kissed Bronzing Set");
            Assert.assertTrue(obj_Main.is_search_all_results_for_present().toLowerCase().contains("show all results for \"sun kissed bronzing set\""));
            obj_Main.setSearch_all_results_for();
            Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
            Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("sun kissed bronzing set"));
            Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
            Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("sun kissed bronzing set"));
            Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("sun kissed bronzing set"));
        }

        @Test(/*priority = 3,*/ dependsOnMethods = {"test_hint_presents_and_start_search_from_hint_in_main_page"})
        // Test doesn't work if focus is not on the browser window !!!!!
        public void test_previous_query_hint_presents_after_search_open () {
            logger = extent.startTest("test_previous_query_hint_presents_after_search_open");
            obj_Main.setSearch_open();
            Assert.assertTrue(obj_Main.is_search_all_results_for_present().toLowerCase().contains("show all results for \"sun kissed bronzing set\""));
            obj_Main.setSearch_all_results_for();
            Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
            Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("sun kissed bronzing set"));
            Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
            Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("sun kissed bronzing set"));
            Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("sun kissed bronzing set"));
        }

        @Test(/*priority = 4,*/ dependsOnMethods = "test_previous_query_hint_presents_after_search_open")
        public void test_part_name_query () {
            logger = extent.startTest("test_part_name_query");
            obj_Main.setSearch_open();
            obj_Main.setSearch_input("bronzing set");
            obj_Main.setSearch_start();
            Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
            Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("bronzing set"));
            Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
            Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("sun kissed bronzing set"));
            Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("sun kissed bronzing set"));
        }

        @Test(/*priority = 5,*/ dependsOnMethods = "test_part_name_query")
        public void test_open_close_search_input () {
            logger = extent.startTest("test_open_close_search_input");
            obj_Main.setSearch_open();
            obj_Main.setSearch_input("sasa");
            Assert.assertTrue(obj_Main.is_search_all_results_for_present().toLowerCase().contains("show all results for \"sasa\""));
            obj_Main.setSearch_close();
            Assert.assertTrue(obj_Main.is_navigation_menu_present().toLowerCase().contains("face eyes lips nails skin care accessories kits looks rewards program"));
        }

        @Test(/*priority = 6,*/ dependsOnMethods = "test_open_close_search_input")
        public void test_empty_query () throws InterruptedException {
            logger = extent.startTest("test_empty_query");
            obj_Main.setWait();
            obj_Main.setSearch_open();
            obj_Main.setWait();
            obj_Main.setSearch_start();
            Assert.assertFalse(obj_Search_result.is_title_NOT_FOUND_present().toLowerCase().contains("no products found"));
        }

        @Test(/*priority = 7,*/ dependsOnMethods = "test_empty_query")
        public void test_empty_query_from_main_page () throws InterruptedException {
            logger = extent.startTest("test_empty_query_from_main_page");
            obj_Main.setHome();
            obj_Main.setSearch_open();
            obj_Main.setWait();
            obj_Main.setSearch_start();
            Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
            Assert.assertFalse(obj_Search_result.is_title_NOT_FOUND_present().toLowerCase().contains("no products found"));
        }

        @AfterMethod
        public void getReportResults (ITestResult result){
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.log(LogStatus.FAIL, "Failed Test Case is : " + result.getName());
                logger.log(LogStatus.FAIL, "Failed with next error : " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                logger.log(LogStatus.SKIP, "Skipped Test Case is :" + result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                logger.log(LogStatus.PASS, "Test Passed");
            }
            extent.endTest(logger);
        }

        @AfterTest
        public void set_down () {
            extent.flush();
            extent.close();
            driver.close();
            driver.quit();
        }
    }


