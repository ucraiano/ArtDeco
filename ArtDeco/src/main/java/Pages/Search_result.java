package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sun.tools.doclint.Entity.or;

/**
 * Created by savchenkoaleksandr on 4/24/17.
 */
public class Search_result {
    WebDriver driver;
    WebDriverWait wait;


    By title_search_results = By.xpath("//h1[@class=\"page-title\"]");
    By title_not_found = By.xpath("//h6[@class=\"search-results-title\"]");
    By page_title_wraper = By.xpath("//div[@class=\"page-title-wrapper search-page\"]");
    By filter_wraper = By.xpath("//div[@class=\"filter-sort-wrapper container-fluid\"]");
    By products_list_wraper = By.xpath("//div[@class=\"category-products\"]");
    By pagination_wraper = By.xpath("//div[@class=\"pagination-wrapper\"]");
    By first_in_list = By.xpath("//div[@class=\"row\"]/div[1]");
    By second_in_list = By.xpath("//div[@class=\"row\"]/div[2]");
    By third_in_list = By.xpath("//div[@class=\"row\"]/div[3]");



    public Search_result(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);

    }

    // Determine actions
    // SEARCH TEST




    // Determine asserts
    // SEARCH TEST
    public String is_title_SEARCH_RESULTS_present(){
        return driver.findElement(title_search_results).getText();
    }

    public String is_title_NOT_FOUND_present(){
        return driver.findElement(page_title_wraper).getText();
    }

    public String is_query_present(){
        return driver.findElement(page_title_wraper).getText();
    }

    public String is_result_number_present(){
        return driver.findElement(page_title_wraper).getText();
    }

    public String is_product_present_in_the_list(){
        return driver.findElement(products_list_wraper).getText();
    }

    public String is_first_in_list(){
        return driver.findElement(first_in_list).getText();
    }

    public String is_second_in_list(){
        return driver.findElement(second_in_list).getText();
    }

    public String is_third_in_list(){
        return driver.findElement(third_in_list).getText();
    }




}
