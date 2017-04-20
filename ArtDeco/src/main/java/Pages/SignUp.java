package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by savchenkoaleksandr on 4/18/17.
 */
public class SignUp {
    WebDriver driver;

    By fecebook =By.xpath("//a[@class=\"social-auth uppertext\"]");
    By email_field = By.xpath(".//*[@id='email-field']");
    By password_field = By.xpath(".//*[@id='password-field']");
    By confirm_password_field = By.xpath(".//*[@id='conf-password-field']");
    By sign_up_button = By.xpath(".//*[@id='sign-submit']");

    public SignUp(WebDriver driver){this.driver = driver;}
}
