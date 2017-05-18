package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by savchenkoaleksandr on 5/18/17.
 */
public class Product_list_page {
    WebDriver driver;
    WebDriverWait wait;

    By category_title = By.xpath(".//*[@id='category-cover-image']/div");
    By kits_category_title = By.xpath("//div[@class=\"page-title\"]");
    By filter_body = By.xpath("//div[@class = \"filter long-filter category-filter\"]/div");
    By sort_by_body = By.xpath("//div[@class = \"filter one-filter short-filter\"]/div");
    By choose_sort_by = By.xpath("");
    By chose_Blusher_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[2]/a");
    By chose_Concealer_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[3]/a");
    By chose_Face_Specials_filter = By.xpath("//ul[@id=\"taxon_filterSelectBoxItOptions\"]//li[4]/a");
    By products_in_category = By.xpath("//div[@class=\"row\"]");

    public Product_list_page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
    }


    //Determine actions
    public void setFilter_body(){
        driver.findElement(filter_body).click();
    }
    public void setChose_Blusher_filter(){
        driver.findElement(chose_Blusher_filter).click();
    }
    public void setChose_Concealer_filter(){
        driver.findElement(chose_Concealer_filter).click();
    }
    public void setChose_Face_Specials_filter(){
        driver.findElement(chose_Face_Specials_filter).click();
    }
    //Determine assert methods
    public String is_it_correct_category(){
        return driver.findElement(category_title).getText();
    }
    public String is_it_correct_sort(){
        return driver.findElement(sort_by_body).getText();
    }
    public String is_it_KITS_category(){
        return driver.findElement(kits_category_title).getText();
    }

    public String is_it_correct_filter_value(){
        return driver.findElement(filter_body).getText();
    }

    public String is_filtered_products_present(){
        return driver.findElement(products_in_category).getText();
    }
}
