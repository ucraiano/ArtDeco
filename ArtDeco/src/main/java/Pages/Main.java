package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by savchenkoaleksandr on 4/14/17.
 */
public class Main {

    WebDriver driver;
    WebDriverWait wait;
    Main obj_Main;
    SignUp obj_SignUp;

    By sign_in_button = By.xpath("//span[@class=\"sign-link__text\"]");
    By my_account = By.xpath(".//*[@id='dLabel']");
    By logout_my_account = By.xpath("//a[@class =\"uppertext\" and @href=\"/logout\"]");
    By first_visit_alert_close = By.xpath(".//*[@id='first-visit']/div/div/button");
    By successfully_logIN_and_logOUT_alert = By.xpath("//main/div[4]");
    By close_successfully_login_alert = By.xpath("//main/div[4]/button[1]");
    By search_open = By.xpath("//button[@class=\"search-open-link\"]");
    By search_close = By.xpath("//button[@class=\"search-close\"]");
    By search_input = By.xpath("//header/descendant::input[@id=\"search-input\"]");
    By search_start = By.xpath("//header/descendant::button[@id='search-submit']");
    By navigation_menu = By.xpath("//ul[@class=\"main-menu clearfix\"]");
    By search_all_results_for = By.xpath("//a[@class=\"tt-header\"]");
    By home = By.xpath("//img[@alt=\"Logo\"]");

    public Main(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }


    // Determine actions with elements

    // LOGIN TEST

    public void setFirst_visit_alert_close(){
        wait.until(ExpectedConditions.elementToBeClickable(first_visit_alert_close));
        driver.findElement(first_visit_alert_close).click();
    }

    public void setAccount_button(){
        driver.findElement(sign_in_button).click();
    }

    public void setWait()throws InterruptedException{
        Thread.sleep(2000);

    }

    public void setClose_successfully_login_alert(){
        driver.findElement(close_successfully_login_alert).click();
    }

    public void setMy_account(){driver.findElement(my_account).click();}

    public void setLogout_my_account(){
        driver.findElement(logout_my_account).click();
    }

    // SEARCH TEST

    public void setSearch_open(){
        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(search_open));
        driver.findElement(search_open).click();
    }

    public void setSearch_close(){
        driver.findElement(search_close).click();
    }

    public void setSearch_input(String str){
        driver.findElement(search_input).clear();
        driver.findElement(search_input).sendKeys(str);
        wait.until(ExpectedConditions.elementToBeClickable(search_all_results_for));
    }

    public void setSearch_start(){
        wait.until(ExpectedConditions.elementToBeClickable(search_start));
        driver.findElement(search_start).click();
    }

    public void setSearch_all_results_for(){
        wait.until(ExpectedConditions.elementToBeClickable(search_all_results_for));
        driver.findElement(search_all_results_for).click();
    }

    public void setHome(){
        driver.findElement(home).click();
    }




    // Determine assert methods
    // LOGIN TEST
    public java.lang.String is_successfully_logIN_and_logOUT_present(){
        return driver.findElement(successfully_logIN_and_logOUT_alert).getText();
    }
    public java.lang.String is_it_my_account_button(){
        return driver.findElement(my_account).getText();
    }
    public java.lang.String is_it_signin_button(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(sign_in_button));
        return driver.findElement(sign_in_button).getText();
    }


    // SEARCH TEST

    public boolean is_search_field_present(){
        try {
            driver.findElement(search_input);
            driver.findElement(search_start);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String is_navigation_menu_present(){
        return driver.findElement(navigation_menu).getText();
    }

    public String is_search_all_results_for_present(){
        wait.until(ExpectedConditions.elementToBeClickable(search_all_results_for));
        return driver.findElement(search_all_results_for).getText();
    }

}
