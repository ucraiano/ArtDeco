package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by savchenkoaleksandr on 4/18/17.
 */
public class SelectRole {
    WebDriver driver;

    By customer_getstarted = By.xpath("//a[@class=\"custom-btn primary-btn sign-up__role-btn\"]");


    public SelectRole(WebDriver driver) {
        this.driver = driver;
    }

    public void setCustomer_getstarted() {
        driver.findElement(customer_getstarted).click();
    }

}
