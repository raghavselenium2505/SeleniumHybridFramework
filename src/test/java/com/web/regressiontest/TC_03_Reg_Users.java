package com.web.regressiontest;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.UserListPage;

public class TC_03_Reg_Users extends TestBase {
	static int var;

	//@com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void deleteUser() throws Exception {
		// test=report.startTest(getData("TC001", "Jira_Story_Name", xlsname));
		test = report.startTest(getData("UserDelete", "Jira_Story_Name", xlsname));

		String skip = getData("DashBoard", "UserDelete", xlsname);
		UserListPage user = new UserListPage(driver);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("UserDelete", "Jira_Story_Description", xlsname));

				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");

				sleepMethod(5000);
				test.log(LogStatus.INFO, "Click on UserManagement and click on Users");
				actionCilck(driver.findElement(home.linkUserManagement), "able to click on UserManagement",
						"Unable to click on UserManagement");
				sleepMethod(4000);
				actionCilck(driver.findElement(home.linkUser), "able to click on User", "Unable to click on User");
				test.log(LogStatus.INFO, "Search for the data to edit");
				String textEmailSearch = getData("UserDelete", "textEmailsearch", xlsname);
				if (!textEmailSearch.equalsIgnoreCase("")) {
					SendKeys(user.textEmailSearch, getData("UserDelete", "textEmailsearch", xlsname),
							"Entered the email for search sucesfully", "issue with entering email");
				}
				sleepMethod(4000);			
				Click(user.buttonSearch, "click on search button", "unable to click on search button");
				sleepMethod(6000);
				test.log(LogStatus.INFO, "Click on Delete button and click on confirm button");
				if(driver.findElement(user.iconDelete).isDisplayed())
				{
				Click(user.iconDelete, "click on Delete button", "unable to click on Delete button");
				sleepMethod(6000);
				Click(user.buttonConfirm, "click on confirm button", "unable to click on confirm button");
				sleepMethod(10000);
				}
				test.log(LogStatus.INFO, "Click on Signoff");

				if(driver.findElement(home.buttonAccount).isDisplayed()) {
					
					Click(home.buttonAccount,"clicked on account button","unable to click on account button");
					sleepMethod(3000);
					actionCilck(driver.findElement(home.linkSignoff),"signoff sucesful","Unable to do signoff");
				}
			
				passcount++;
			} else {
				executioncount++;
				skipCount++;
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
