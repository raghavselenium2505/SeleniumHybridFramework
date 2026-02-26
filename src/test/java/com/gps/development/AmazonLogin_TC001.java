package com.gps.development;

import java.time.Duration;

import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.AmazonHomePage;
import com.gps.pages.AmazonLoginPage;
import com.gps.utilities.DynamicDataProvider;

public class AmazonLogin_TC001 extends TestBase {

    // 20 TESTS LIKE THIS
    @Test(groups = {"json"},
          dataProvider = "dynamicData",
          dataProviderClass =
                  DynamicDataProvider.class)
    public void TC_Login_JSON(
            String username,
            String password,
            String searchItem) {

        System.out.println(
                "Running JSON Test");
        System.out.println(username);
        System.out.println(password);
        System.out.println(searchItem);
        
        AmazonHomePage home = new AmazonHomePage();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        click(
                home.linkAccount,
                "Able to click on link account",
                "Unable to click on link account"
        );
        

        AmazonLoginPage login = new AmazonLoginPage(getDriver());

        login.login(
                config.getProperty("userName"),
                config.getProperty("passWord"),
                "Able to login",
                "Unable to login"
        );

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    // 80 TESTS LIKE THIS
    @Test(groups = {"excel"},
          dataProvider = "dynamicData",
          dataProviderClass =
                  DynamicDataProvider.class)
    public void TC_Login_Excel(
            String username,
            String password,
            String searchItem) {

        System.out.println(
                "Running Excel Test");
        System.out.println(username);
        System.out.println(password);
        System.out.println(searchItem);
        AmazonHomePage home = new AmazonHomePage();

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        click(
                home.linkAccount,
                "Able to click on link account",
                "Unable to click on link account"
        );
        

        AmazonLoginPage login = new AmazonLoginPage(getDriver());

        login.login(
                config.getProperty("userName"),
                config.getProperty("passWord"),
                "Able to login",
                "Unable to login"
        );

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }
}