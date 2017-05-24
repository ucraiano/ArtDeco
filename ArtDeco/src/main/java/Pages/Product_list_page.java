package Pages;

import com.relevantcodes.extentreports.model.ExceptionInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.jvm.hotspot.oops.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by savchenkoaleksandr on 5/18/17.
 */
public class Product_list_page {
    WebDriver driver;
    WebDriverWait wait;


    By category_title = By.xpath(".//*[@id='category-cover-image']/div");
    By kits_category_title = By.xpath("//div[@class=\"page-title\"]");
    By filter_body = By.xpath("//div[@class = \"filter long-filter category-filter\"]/div");
    By choose_Blusher_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[2]/a");
    By choose_Concealer_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[3]/a");
    By choose_Face_Specials_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[4]/a");
    By choose_Foundation_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[5]/a");
    By choose_Powder_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[6]/a");
    By products_in_category = By.xpath("//div[@class=\"row\"]");
    By sort_by_body = By.xpath("//div[@class = \"filter one-filter short-filter\"]/div");
    By choose_sort_by_Newest = By.xpath("//div[@id=\"mCSB_4_container\"]//li[1]/a");
    By choose_sort_by_BestSellers = By.xpath("//div[@id=\"mCSB_4_container\"]//li[2]/a");
    By choose_sort_by_Price_High_Low = By.xpath("//div[@id=\"mCSB_4_container\"]//li[3]/a");
    By choose_sort_by_Price_Low_High = By.xpath("//div[@id=\"mCSB_4_container\"]//li[4]/a");
    By choose_sort_by_A_Z = By.xpath("//div[@id=\"mCSB_4_container\"]//li[5]/a");
    By choose_sort_by_Z_A = By.xpath("//div[@id=\"mCSB_4_container\"]//li[6]/a");
    By first_product_in_list = By.xpath("//div[@class=\"row\"]/div[1]");
    By all_products_prices = By.xpath("//div[@class=\"categoty-poduct-price\"]/span");
    By all_products_names = By.xpath("//a[@class=\"info\"]");


    public Product_list_page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);

    }


    //Determine actions
    public void setFilter_body() {
        driver.findElement(filter_body).click();
    }

    public void setChose_Blusher_filter() {
        driver.findElement(choose_Blusher_filter).click();
    }

    public void setChose_Concealer_filter() {
        driver.findElement(choose_Concealer_filter).click();
    }

    public void setChose_Face_Specials_filter() {
        driver.findElement(choose_Face_Specials_filter).click();
    }

    public void setChose_Foundation_filter() {
        driver.findElement(choose_Foundation_filter).click();
    }

    public void setChose_Powder_filter() {
        driver.findElement(choose_Powder_filter).click();
    }

    public void setSort_by_body() {
        driver.findElement(sort_by_body).click();
    }

    public void setChoose_sort_by_Newest() {
        driver.findElement(choose_sort_by_Newest).click();
    }

    public void setChoose_sort_by_BestSellers() {
        driver.findElement(choose_sort_by_BestSellers).click();
    }

    public void setChoose_sort_by_Price_High_Low() {
        driver.findElement(choose_sort_by_Price_High_Low).click();
    }

    public void setChoose_sort_by_Price_Low_High() {
        driver.findElement(choose_sort_by_Price_Low_High).click();
    }

    public void setChoose_sort_by_A_Z() {
        driver.findElement(choose_sort_by_A_Z).click();
    }

    public void setChoose_sort_by_Z_A() {
        driver.findElement(choose_sort_by_Z_A).click();
    }

    //Determine assert methods

    public String is_it_KITS_category() {
        return driver.findElement(kits_category_title).getText();
    }

    public String is_it_correct_category() {
        return driver.findElement(category_title).getText();
    }

    public String is_it_correct_sort() {
        return driver.findElement(sort_by_body).getText();
    }

    public String is_it_correct_filter_value() {
        return driver.findElement(filter_body).getText();
    }

    public String is_filtered_products_present() {
        return driver.findElement(products_in_category).getText();
    }

    public String is_first_product_correct() {
        return driver.findElement(first_product_in_list).getText();
    }

    public boolean is_first_product_price_highest() {
        List<WebElement> webElements = driver.findElements(all_products_prices);
        double first_price = Double.parseDouble(webElements.get(0).getText().substring(1));
        for (WebElement element : webElements) {
            Double val = Double.parseDouble(element.getText().substring(1));
            System.out.println(val);
            if (first_price < val) {
                return false;
            }
        }
        return true;
    }

    public boolean is_first_product_price_lowest(){
        List<WebElement> webElements = driver.findElements(all_products_prices);
        double first_price = Double.parseDouble(webElements.get(0).getText().substring(1));
        for (WebElement element : webElements) {
            Double val = Double.parseDouble(element.getText().substring(1));
            System.out.println(val);
            if (first_price >val) {
                return false;
            }
        }
        return true;
    }

    public void is_first_product_name_starts_with_A() {
        List<WebElement> webElements_sorted_by_web = driver.findElements(all_products_names);
        List<WebElement> webElements = driver.findElements(all_products_names);
        for (int i = 0; i < webElements.size(); i++) {
            ArrayList<String> array_names = null;
            String name = webElements.get(i).getText();

            for (int x =0; x< webElements.size(); x++){
                array_names.add(name);
                System.out.println();
            }
        }
        for (int i =0; i < webElements_sorted_by_web.size(); i++){
            ArrayList<String> array_names_sorted_by_web = null;
            String name_sorted_by_web = webElements_sorted_by_web.get(i).getText();
            for(int x = 0; x < webElements_sorted_by_web.size(); x++){
                array_names_sorted_by_web.add(name_sorted_by_web);
            }
        }

        }




        /* String first_name = webElements.get(0).getText().toLowerCase();
        String last_name = webElements.get(9).getText().toLowerCase();
        for (int i =1; i <webElements.size(); i++){
            String name = webElements.get(i).getText();
            System.out.println("Name "+ i +" is "+name);
        }
        if (first_name.startsWith("a") & last_name.startsWith("s")){
            return true;
        }else {
            return false;
        }

*/

    }











