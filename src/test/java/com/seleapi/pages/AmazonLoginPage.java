package com.seleapi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.seleapi.base.TestBase;

interface LoginPage {
    void login(String userName, String passWord, String passValue, String failValue);
}

public class AmazonLoginPage extends TestBase implements LoginPage {

    WebDriver driver;

    public By linkEmailid    = By.xpath("//input[@id='ap_email_login']");
    public By linkPassword   = By.xpath("//input[@id='ap_password']");
    public By buttonContinue = By.xpath("//input[@class='a-button-input']");
    public By buttonSignin   = By.xpath("//input[@id='signInSubmit']");

    public AmazonLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void login(String userName, String passWord, String passValue, String failValue) {

        try {

            elementhighlight(driver.findElement(linkEmailid));
            driver.findElement(linkEmailid).sendKeys(userName);

            elementhighlight(driver.findElement(buttonContinue));
            driver.findElement(buttonContinue).click();

            elementhighlight(driver.findElement(linkPassword));
            driver.findElement(linkPassword).sendKeys(passWord);

            elementhighlight(driver.findElement(buttonSignin));
            click(buttonSignin, passValue, failValue);

        } catch (Exception e) {

            // ✅ Log AI + Actual error into EXTENT REPORT
         //   logAIFailure(e, failValue);

            // ✅ Fail test (do not log again)
            throw new RuntimeException("Login failed", e);
        }
    }
}
