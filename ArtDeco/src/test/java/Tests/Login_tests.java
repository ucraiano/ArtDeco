package Tests;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by savchenkoaleksandr on 4/14/17.
 */
public class Login_tests {
WebDriver driver;
Main obj_Main;
SignIn obj_SignIn;
SelectRole obj_Select_Role;
SignUp obj_SignUp;
Facebook obj_Facebook;

@BeforeTest
    public void set_up(){
    driver = new FirefoxDriver();
    obj_Main = new Main(driver);
    obj_SignIn = new SignIn(driver);
    obj_Facebook = new Facebook(driver);
    driver.get("https://www.artdecobeauty.com");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}

@Test(priority = 1)
    public void test_empty_fields() throws InterruptedException {
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setAccount_button();
        obj_SignIn.setEmail_field("");
        obj_SignIn.setPassword_field("");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_incorrect_login_alert_present().toLowerCase().contains("invalid email or password."));
        obj_SignIn.setClose_allert_incorect_login();

}
@Test(priority = 2)
    public void test_not_email(){
        obj_SignIn.setEmail_field("asdasd.com");
        Assert.assertTrue(obj_SignIn.is_email_label_present());
        obj_SignIn.setPassword_field("123");
        Assert.assertTrue(obj_SignIn.is_password_label_present());
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_it_signIN_page().toLowerCase().contains("sign in"));
        Assert.assertTrue(obj_SignIn.is_facebook_present().toLowerCase().contains("sign in with facebook"));
}
@Test(priority = 3)
    public void test_non_existing_email(){
        obj_SignIn.setEmail_field("123@gmail.com");
        obj_SignIn.setPassword_field("123123");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_incorrect_login_alert_present().toLowerCase().contains("invalid email or password."));
        obj_SignIn.setClose_allert_incorect_login();

}

@Test(priority =4)
    public void test_existing_email_and_non_existing_password(){
        obj_SignIn.setEmail_field("ucraiano.test1@gmail.com");
        obj_SignIn.setPassword_field("123123123");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_SignIn.is_incorrect_login_alert_present().toLowerCase().contains("invalid email or password."));
        obj_SignIn.setClose_allert_incorect_login();
}


@Test(priority = 5)
    public void test_correct_email_and_password(){
        obj_SignIn.setEmail_field("ucraiano.test1@gmail.com");
        obj_SignIn.setPassword_field("REM4)nexuses");
        obj_SignIn.setSign_IN_button();
        Assert.assertTrue(obj_Main.is_successfully_logIN_and_logOUT_present().toLowerCase().contains("logged in successfully"));
        obj_Main.setClose_successfully_login_alert();
        Assert.assertTrue(obj_Main.is_it_my_account_button().toLowerCase().contains("my account"));
}

@AfterTest
    public void set_down(){
        driver.close();
        driver.quit();

}


}
