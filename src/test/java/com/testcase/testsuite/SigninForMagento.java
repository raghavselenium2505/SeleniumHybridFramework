package com.testcase.testsuite;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPageMagento;

public class SigninForMagento extends TestBase {

	static int var;

	// @JiraCreateIssue(isCreateIssue = true)
	@Test
	public void SignInForMagento() throws Exception {
		test = report.startTest(getData("Signin", "Jira_Story_Name", xlsname));

		String skip = getData("DashBoard", "SignInMagento", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				test.log(LogStatus.INFO, "Click on Create an Account");
				LoginPageMagento login = new LoginPageMagento(driver);
				test.log(LogStatus.INFO, "Enter all valid details for signup");

				login.login();
				waitforelement(5000);
				passcount++;
				
			} else {

				test.log(LogStatus.SKIP, "This testcases will not run as this testcases is skipped");
			}
		} catch (Exception e) {
			// driver.findElement(user.dropDownUserRole).sendKeys(Keys.ENTER);
			test.log(LogStatus.FAIL, "Exception happeneed" + e);
			executioncount++;
			failcount++;
		}
	}
}
