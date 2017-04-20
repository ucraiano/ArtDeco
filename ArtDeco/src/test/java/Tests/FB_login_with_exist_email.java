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
 * Created by savchenkoaleksandr on 4/20/17.
 */
public class FB_login_with_exist_email {
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
        obj_Facebook = new Facebook(driver);
        obj_SignIn = new SignIn(driver);
        driver.get("https://www.artdecobeauty.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void test_facebook_with_email_which_already_exist_in_artdeco() throws InterruptedException {
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setAccount_button();
        obj_SignIn.setFacebook();
        obj_Facebook.setEmail_field("ucraiano.test1@gmail.com");
        obj_Facebook.setPassword_field("REM4)nexuses");
        obj_Facebook.setLogin_button();
        Assert.assertTrue(obj_Main.is_successfully_logIN_and_logOUT_present().toLowerCase().contains("email has already been taken"));
    }

    @AfterTest
    public void set_down(){
        driver.close();
        driver.quit();

    }

}
