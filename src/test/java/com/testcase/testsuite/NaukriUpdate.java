package com.testcase.testsuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.NaukriHomePage;
import com.testcase.Pages.NaukriLoginPage;

public class NaukriUpdate extends TestBase {
	static int var;

	@Test
	public void UserCreation() throws Exception {
		// test=report.startTest(getData("TC001", "Jira_Story_Name", xlsname));
		test = report.startTest(getData("User", "Jira_Story_Name", xlsname));

		String skip = getData("DashBoard", "User", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				test.log(LogStatus.INFO, getData("User", "Jira_Story_Description", xlsname));

				NaukriLoginPage login1 = new NaukriLoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login1.login(config.getProperty("userName"), config.getProperty("passWord"));
				waitforelement(5000);
				NaukriHomePage homepage = new NaukriHomePage(driver);
				actionclick(driver.findElement(homepage.profileClick), "able to click on profileclick ",
						"Unable to click on profileclick ");
				waitforelement(2000);
				click(homepage.viewprofile, "Able to click on viewprofile", "Unable to click on viewprofile");

				waitforelement(4000);
				actionclick(driver.findElement(homepage.profilesummary), "able to click on profilesummaryclick ",
						"Unable to click on profilesummaryclick ");
				waitforelement(5000);
				String value = driver.findElement(homepage.profileText).getAttribute("value");
				clear(homepage.profileText);
				waitforelement(3000);
				sendkeys(homepage.profileText, value, "Able to send the value for profilesummaryclick",
						"Unable to send the profilesummaryclick");
				waitforelement(3000);
				click(homepage.saveButton, "Able to click on save button", "Unable to click on save button");
				waitforelement(5000);
				homepage.isdisplayed(homepage.texttoday, "Able to update", "Not updated");
			}
		} catch (Exception e) {

		}
	}
}
