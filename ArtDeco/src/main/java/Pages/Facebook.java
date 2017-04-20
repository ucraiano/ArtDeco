package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by savchenkoaleksandr on 4/20/17.
 */
public class Facebook {
    WebDriver driver;
    Facebook obj_Facebook;

    By email_field = By.xpath(".//*[@id='email']");
    By password_field = By.xpath(".//*[@id='pass']");
    By login_button = By.xpath(".//*[@id='loginbutton']");
    By accept_button = By.xpath("");

    public Facebook(WebDriver driver){this.driver = driver;}

    // Determine actions with elements

    public void setEmail_field(String str){
        driver.findElement(email_field).clear();
        driver.findElement(email_field).sendKeys(str);
    }

    public void setPassword_field(String str){
        driver.findElement(password_field).clear();
        driver.findElement(password_field).sendKeys(str);
    }

    public void setLogin_button(){
        driver.findElement(login_button).click();
    }

    public void setAccept_button(){
        driver.findElement(accept_button).click();
    }

    // Determine asserts methods
}
