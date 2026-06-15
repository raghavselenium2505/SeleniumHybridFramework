package com.seleapi.tests;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.seleapi.base.TestBase;
import com.seleapi.config.EnvironmentManager;
import com.seleapi.dataprovider.DynamicDataProvider;
import com.seleapi.listeners.TestListener;
import com.seleapi.pages.MilkMan_HomePage;
import com.seleapi.pages.MilkMan_LoginPage;
import com.seleapi.pages.MilkMan_SignUpPage;

@Listeners(TestListener.class)

public class NewFeature extends TestBase {

    @Test(

            groups = "MilkManSignupTests",

            dataProvider = "dynamicData",

            dataProviderClass =
                    DynamicDataProvider.class
    )

    public void MilkManLoginWithMontly(
            Map<String, String> data) {

        try {

            logger.info(
                    "Starting Monthly Signup Test");

            test.get().log(

                    Status.INFO,

                    "Monthly Signup Test Started");

            /*
             * TEST DATA
             */

            String fName =
                    data.get("FirstName");

            String lastName =
                    data.get("LastName");

            /*
             * ENVIRONMENT DRIVEN
             * CREDENTIALS
             */

            String email =

                    EnvironmentManager
                    .getUserName();

            String pass =

                    EnvironmentManager
                    .getPassword();

            /*
             * PAGE OBJECTS
             */

            MilkMan_HomePage homepage =
                    new MilkMan_HomePage();

            MilkMan_LoginPage loginpage =
                    new MilkMan_LoginPage();

            MilkMan_SignUpPage signup =
                    new MilkMan_SignUpPage();

            logger.info(
                    "Launching Signup Flow");

            /*
             * CLICK LOGIN BUTTON
             */

            homepage.hiddenElementDisplay(

                    getDriver().findElement(
                            homepage.buttonLogin),

                    "Able to click on signin button",

                    "Unable to click on signin button"
            );

            logger.info(
                    "Clicked Login Button");

            /*
             * WAIT FOR SIGNUP LINK
             */

            waitForElementVisible(

                    loginpage.linkSignUp,

                    20,

                    "Signup link visible",

                    "Signup link not visible"
            );

            /*
             * CLICK SIGNUP LINK
             */

            click(

                    loginpage.linkSignUp,

                    "Clicked Signup Link",

                    "Unable To Click Signup Link"
            );

            logger.info(
                    "Clicked Signup Link");

            /*
             * VERIFY SIGNUP PAGE
             */

            verifyElementDisplayed(

                    signup.titleModernMilk,

                    "Signup Page Displayed",

                    "Signup Page Not Displayed"
            );

            /*
             * ENTER FIRST NAME
             */

            sendkeys(

                    signup.textFirstName,

                    fName,

                    "Entered First Name",

                    "Unable To Enter First Name"
            );

            /*
             * ENTER LAST NAME
             */

            sendkeys(

                    signup.textLastName,

                    lastName,

                    "Entered Last Name",

                    "Unable To Enter Last Name"
            );

            /*
             * ENTER EMAIL
             */

            sendkeys(

                    signup.textEmail,

                    email,

                    "Entered Email",

                    "Unable To Enter Email"
            );

            /*
             * ENTER PASSWORD
             */

            sendkeys(

                    signup.textpassword,

                    pass,

                    "Entered Password",

                    "Unable To Enter Password"
            );

            /*
             * CLICK CREATE ACCOUNT
             */

            /*
             * click(
             *
             * signup.buttonCreateAccount,
             *
             * "Clicked Create Account",
             *
             * "Unable To Click Create Account"
             * );
             */

            logger.info(
                    "Signup Flow Completed Successfully");

            test.get().log(

                    Status.PASS,

                    "Signup Flow Completed Successfully"
            );

        }

        catch (Exception e) {

            logger.error(
                    "Monthly Signup Test Failed",
                    e);

            throw e;
        }
    }
}