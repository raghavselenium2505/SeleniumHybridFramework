package com.testcase.testsuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.ETeki_HomePage;
import com.testcase.Pages.ETeki_LoginPage;

public class ETeki_Login extends TestBase {

	@Test(priority = 10)
	public void eTekiValidLogin() throws Exception {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(getData("Login", "Jira_Story_Name", xlsname));
		try {
			if (getData("Dashboard", "Validate Login with valid Username & Password.", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				loginpage.login(config.getProperty("userName"), config.getProperty("password"),
						"User able to enter valid Username.", "User unable to enter invalid Username.",
						"User able to enter valid Password.", "User unable to enter invalid Password.");

				waitforelement(mediumwaitvalue);

				ETeki_HomePage homepage = new ETeki_HomePage();

				alert(homepage.Dashboard);

				waitforelement(shortwaitvalue);

				Assert.assertEquals(driver.getCurrentUrl(), loginpage.dashboardURL);

				test.log(LogStatus.INFO, "User logout eTeki successfully");

				click(homepage.userProfile, driver.findElement(homepage.Dashboard), "User able to click on Profile.",
						"User unable to click on Profile.");

				waitforelement(shortwaitvalue);

				click(homepage.logoutButton, driver.findElement(homepage.userProfile), "User able to click on Logout.",
						"User unable to click on Logout.");

				waitforelement(mediumwaitvalue);
			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}

	@Test(priority = 11)
	public void eTekiEmptyLogin() throws Exception {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(getData("Login", "Jira_Story_Name_1", xlsname));
		try {
			if (getData("Dashboard", "Validate Login with empty Username or Password.", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "Enter empty Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				SubmitClick(loginpage.buttonSignIn, "User able to click on Sign IN button.",
						"User unable to click on Sign IN button.");

				waitforelement(shortwaitvalue);

				Assert.assertEquals(driver.findElement(loginpage.validationUsername).getText(),
						loginpage.usernamevalidationmessage);

				waitforelement(shortwaitvalue);

				Assert.assertEquals(driver.findElement(loginpage.validationPassword).getText(),
						loginpage.passwordvalidationmessage);

				waitforelement(shortwaitvalue);

				driver.navigate().refresh();

				waitforelement(mediumwaitvalue);
			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}

	@Test(priority = 12)
	public void eTekiInvalidLogin() throws Exception {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(getData("Login", "Jira_Story_Name_2", xlsname));
		try {

			if (getData("Dashboard", "Validate Login with invalid Username or Password.", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "Enter invalid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				loginpage.login(getData("Login", "Invalid Username", xlsname),
						getData("Login", "Invalid Password", xlsname), "User able to enter invalid Username.",
						"User unable to enter valid Username.", "User able to enter invalid Password.",
						"User unable to enter valid Password.");

				waitforelement(mediumwaitvalue);

				Assert.assertEquals(driver.findElement(loginpage.validationmessage).getText(),
						loginpage.messageValidation);

				waitforelement(shortwaitvalue);

				driver.navigate().refresh();

				waitforelement(mediumwaitvalue);

			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}
}