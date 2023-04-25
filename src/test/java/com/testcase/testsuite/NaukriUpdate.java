package com.testcase.testsuite;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.NaukriLoginPage;

public class NaukriUpdate extends TestBase{
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
				login1.login();
			}
		}
			catch(Exception e)
			{
				
			}
	}
}
