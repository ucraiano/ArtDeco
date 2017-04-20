package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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
    public Main(WebDriver driver){this.driver = driver;}

    // Determine actions with elements

    public void setFirst_visit_alert_close(){
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(first_visit_alert_close));
        driver.findElement(first_visit_alert_close).click();
    }

    public void setAccount_button() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(sign_in_button).click();
    }

    public void setClose_successfully_login_alert(){
        driver.findElement(close_successfully_login_alert).click();
    }

    public void setMy_account(){driver.findElement(my_account).click();}

    public void setLogout_my_account(){
        driver.findElement(logout_my_account).click();
    }

    // Determine assert methods

    public String is_successfully_logIN_and_logOUT_present(){
        return driver.findElement(successfully_logIN_and_logOUT_alert).getText();
    }
    public String is_it_my_account_button(){
        return driver.findElement(my_account).getText();
    }
    public String is_it_signin_button(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(sign_in_button));
        return driver.findElement(sign_in_button).getText();
    }

}
