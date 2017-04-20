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
public class FB_login_correct {
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

    @Test
    public void test_sign_in_with_facebook() throws InterruptedException {
        obj_Main.setFirst_visit_alert_close();
        obj_Main.setAccount_button();
        obj_SignIn.setFacebook();
        obj_Facebook.setEmail_field("ucraiano.test2@gmail.com");
        obj_Facebook.setPassword_field("REM4)nexuses");
        obj_Facebook.setLogin_button();
        Assert.assertTrue(obj_Main.is_successfully_logIN_and_logOUT_present().toLowerCase().contains("you are now signed in with your facebook account."));
        obj_Main.setClose_successfully_login_alert();
        Assert.assertTrue(obj_Main.is_it_my_account_button().toLowerCase().contains("my account"));
    }

    @AfterTest
    public void set_down(){
        driver.close();
        driver.quit();

    }

}


