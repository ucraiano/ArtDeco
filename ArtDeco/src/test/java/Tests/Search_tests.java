package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.sun.tools.doclint.Entity.or;


/**
 * Created by savchenkoaleksandr on 4/24/17.
 */
public class Search_tests {
    WebDriver driver;
    Main obj_Main;
    Search_result obj_Search_result;

    @BeforeTest
    public void set_up(){
        driver = new FirefoxDriver();
        obj_Main = new Main(driver);
        obj_Search_result = new Search_result(driver);
        driver.get("https://www.artdecobeauty.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void test_correct_full_name() throws InterruptedException {
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setWait();
        obj_Main.setSearch_open();
        obj_Main.setSearch_input("Camouflage Cream");
        obj_Main.setSearch_start();
        Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
        Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("camouflage cream"));
        Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
        Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("camouflage cream"));
        Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("camouflage cream"));
    }

    @Test(priority = 2)
    public void test_hint_presents_and_start_search_from_hint_in_main_page(){
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

    @Test(priority = 3)
    public void test_previous_query_hint_presents_after_search_open(){
        obj_Main.setSearch_open();
        Assert.assertTrue(obj_Main.is_search_all_results_for_present().toLowerCase().contains("show all results for \"sun kissed bronzing set\""));
        obj_Main.setSearch_all_results_for();
        Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
        Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("sun kissed bronzing set"));
        Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
        Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("sun kissed bronzing set"));
        Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("sun kissed bronzing set"));

    }

    @Test(priority = 4)
    public void test_part_name_query(){
        obj_Main.setSearch_open();
        obj_Main.setSearch_input("bronzing set");
        obj_Main.setSearch_start();
        Assert.assertTrue(obj_Search_result.is_title_SEARCH_RESULTS_present().toLowerCase().contains("search results"));
        Assert.assertTrue(obj_Search_result.is_query_present().toLowerCase().contains("bronzing set"));
        Assert.assertTrue(obj_Search_result.is_result_number_present().toLowerCase().contains("1"));
        Assert.assertTrue(obj_Search_result.is_product_present_in_the_list().toLowerCase().contains("sun kissed bronzing set"));
        Assert.assertTrue(obj_Search_result.is_first_in_list().toLowerCase().contains("sun kissed bronzing set"));
    }

    @Test(priority = 5)
    public void test_open_close_search_input(){
        obj_Main.setSearch_open();
        Assert.assertTrue(obj_Main.is_search_all_results_for_present().toLowerCase().contains("show all results for \"bronzing set\""));
        obj_Main.setSearch_close();
        Assert.assertTrue(obj_Main.is_navigation_menu_present().toLowerCase().contains("face eyes lips nails skin care accessories kits looks rewards program"));
    }

    @Test(priority = 6)
    public void test_empty_query() throws InterruptedException {
        obj_Main.setWait();
        obj_Main.setSearch_open();
        obj_Main.setWait();
        obj_Main.setSearch_start();
        Assert.assertFalse(obj_Search_result.is_title_NOT_FOUND_present().toLowerCase().contains("no products found"));
    }


    @AfterTest
    public void set_down(){
        driver.close();
        driver.quit();
    }


}
