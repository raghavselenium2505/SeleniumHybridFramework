package com.gps.development;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.gps.base.TestBase;
import com.gps.pages.NaukriHomePage;
import com.gps.pages.NaukriLandingPage;
import com.gps.pages.NaukriResumeHeadingPage;

public class Naukri_Login extends TestBase {

    @Test(groups = "json", dataProvider = "dynamicData",
          dataProviderClass = com.gps.utilities.DynamicDataProvider.class)
    public void jsonLoginTest(String username, String password) {

        JSONArray jsonArray = getJsonArray();

        String sectionName = ""; // ✅ Declare outside loop
String resumeheader="";
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;

            String runMode = jsonObject.get("runMode").toString();
            sectionName = jsonObject.get("sectionName").toString(); // ✅ assign here
            resumeheader = jsonObject.get("resumeheader").toString(); // ✅ assign here

            if (runMode.equalsIgnoreCase("no")) {

                logger.warn("Test skipped due to RunMode NO in JSON");

                test.get().skip(
                        "<span style='color:orange;font-weight:bold;'>⚠ Test skipped because RunMode is NO</span>");

                throw new SkipException("RunMode NO");
            }
        }

        logger.info("Starting JSON Login Test");

        NaukriHomePage homepage = new NaukriHomePage();
		test.get().log(Status.INFO, "Click on Login button,enter valid credentials ");

        click(homepage.buttonLogin, "Clicked on login button", "Unable to click login button");

        waitForElementVisible(homepage.titleLogin, 350,
                "waiting for login page", "Unable to wait");

        getTextAndVerify(homepage.titleLogin, "Login",
                "Login page title verified", "Login page title mismatch");

        waitForElementVisible(homepage.textEmailId, 10,
                "waiting for email field", "Unable to wait");

        sendkeys(homepage.textEmailId, config.getProperty("userName"),
                "Entered username successfully " + config.getProperty("userName"),
                "Unable to enter username");

        sendkeys(homepage.textpassword, config.getProperty("password"),
                "Entered password successfully",
                "Unable to enter password");

        click(homepage.buttonSubmit,
                "Clicked on submit button",
                "Unable to click on submit button");

        NaukriLandingPage landingpage = new NaukriLandingPage();

        verifyElementDisplayed(landingpage.iconBell,
                "Logged in successfully",
                "Unable to login to the application");
		test.get().log(Status.INFO, "Click on view Profile");

        click(landingpage.buttonViewProfile,
                "Clicked on view profile",
                "Unable to click on view profile");

        waitForElementVisible(landingpage.buttonUpdateResume, 350,
                "waiting for update resume button", "Unable to wait");

        verifyElementDisplayed(landingpage.buttonUpdateResume,
                "Update resume button displayed",
                "Unable to display update resume button");

        test.get().log(Status.INFO, "Click on edit iconof the page ,clear the  text(if any) and enter the text");
        // 🔥 Final dynamic action
        landingpage.clickEditIcon(sectionName);
        
        
        NaukriResumeHeadingPage resumeheading = new NaukriResumeHeadingPage();
       
        verifyElementDisplayed(resumeheading.textResumeheader,
                "Update resume header displayed",
                "Unable to display update resume header");
        
        clear(resumeheading.textResumeheader, "Able to clear the resumeheader text", "Unable to clear the resume header text");
        
        sendkeys(resumeheading.textResumeheader, resumeheader, "Able to enter the text", "Unable to enter the text");
        
        test.get().log(Status.INFO, "Click on Save Button");
        click(resumeheading.buttonSave, "Able to click on save button and updated sucesfully", "Unable to click on save button");
        test.get().log(Status.INFO, "Check the updated status changed to today/system date or not ");
        verifyElementDisplayed(landingpage.textToday,
                "Data updated",
                "Unable to save the data sucesfully");

        assertElementDisplayed(landingpage.textToday, "Updated sucesfully", "Updated sucesfully");

        waitForElementVisible(landingpage.iconBars, 350,
                "waiting for update resume button", "Unable to wait");
        test.get().log(Status.INFO, "Click on profile icon and click on logout from the applications");
        actionclick(getDriver().findElement(landingpage.iconBars) , "Able to click on profile icon", "Unable to click on profile icon");
        
        click(landingpage.linkLogout, "Able to click on logout", "Unable to click on logout");
        assertElementDisplayed(homepage.buttonLogin, "Returned back to homepage", "Unable to returned back to homepage");
        
    }
}