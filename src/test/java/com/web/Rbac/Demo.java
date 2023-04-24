package com.web.Rbac;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPage;

public class Demo  extends TestBase{
	
	static int var;
	//@com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("Add Assessment", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "Add Assessment", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				
				test.log(LogStatus.INFO, getData("Add Assessment", "Jira_Story_Description", xlsname));
				LoginPage login = new LoginPage(driver);
				
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(3000);
			}
		
		else {
			executioncount++;
			skipCount++;
			test.log(LogStatus.SKIP, "This testcases will not run as this testcases is skipped");
		}
	} catch (Exception e) {
		test.log(LogStatus.FAIL, "Exception happeneed" + e);
		failcount++;
		executioncount++;
	}
}
}