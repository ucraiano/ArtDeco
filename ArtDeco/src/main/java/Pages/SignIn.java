package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by savchenkoaleksandr on 4/18/17.
 */
public class SignIn {
    WebDriver driver;


    By facebook = By.xpath("//a[@class=\"social-auth uppertext\"]");
    By email_field = By.xpath(".//*[@id='email-field']");
    By password_field = By.xpath(".//*[@id='password-field']");
    By sign_IN_button = By.xpath(".//*[@id='sign-submit']");
    By forgot_password = By.xpath("//a[@href=\"/password/recover\"]");
    By sign_UP_button = By.xpath("//a[@href=\"/brand/sign_up\"]");
    By alert_incorrect_login = By.xpath("//div[@class=\"alert alert-error alert-dismissible\"]");
    By close_allert_incorect_login = By.xpath("//button[@class=\"close\"]");
    By sign_IN_title = By.xpath(".//*[@id='sign-forms-wrapper']/div/div/h1");
    By email_label = By.xpath(".//*[@id='new_spree_user']/div[1]/div/label");
    By password_label = By.xpath(".//*[@id='new_spree_user']/div[2]/div/label");

    public SignIn(WebDriver driver) {
        this.driver = driver;
    }

    // Determine actions with elements

    public void setEmail_field(String email) {
        driver.findElement(email_field).clear();
        driver.findElement(email_field).sendKeys(email);
    }

    public void setPassword_field(String password) {
        driver.findElement(password_field).clear();
        driver.findElement(password_field).sendKeys(password);
    }

    public void setSign_IN_button() {
        driver.findElement(sign_IN_button).click();
    }

    public void setForgot_password() {
        driver.findElement(forgot_password).click();
    }

    public void setFacebook() {
        driver.findElement(facebook).click();
    }

    public void setSign_UP_button() {
        driver.findElement(sign_UP_button).click();
    }

    public void setClose_allert_incorect_login() {
        driver.findElement(close_allert_incorect_login).click();
    }

    // Determine assert methods

    public String is_incorrect_login_alert_present() {
        return driver.findElement(alert_incorrect_login).getText();
    }

    public String is_it_signIN_page() {
        return driver.findElement(sign_IN_title).getText();
    }

    public String is_facebook_present() {
        return driver.findElement(facebook).getText();
    }

    public boolean is_email_label_present(){
        try {
            driver.findElement(email_label);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean is_password_label_present(){
        try{
            driver.findElement(password_label);
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
