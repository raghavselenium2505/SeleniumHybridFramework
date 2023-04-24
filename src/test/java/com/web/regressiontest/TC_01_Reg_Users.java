package com.web.regressiontest;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.UserListPage;

public class TC_01_Reg_Users extends TestBase {
	static int var;

	//@JiraCreateIssue(isCreateIssue = true)
	@Test
	public void UserCreation() throws Exception {
		// test=report.startTest(getData("TC001", "Jira_Story_Name", xlsname));
		test = report.startTest(getData("User", "Jira_Story_Name", xlsname));

		String skip = getData("DashBoard", "User", xlsname);
		UserListPage user = new UserListPage(driver);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("User", "Jira_Story_Description", xlsname));

				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(2000);
				test.log(LogStatus.INFO, "Click on UserManagement and click on Users");
				actionCilck(driver.findElement(home.linkUserManagement), "able to click on UserManagement",
						"Unable to click on UserManagement");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkUser), "able to click on User", "Unable to click on User");
				Click(user.buttonAddUser, "able to click on UserManagement", "Unable to click on UserManagement");

				test.log(LogStatus.INFO, "Enter the details which are mandatory");
				sleepMethod(5000);
				SendKeys(user.textFirstName, getData("User", "UserFirstName", xlsname),
						"able to enter the value in FirstName", "unaable to enter the value in FirstName");
				SendKeys(user.textLastName, getData("User", "UserLastName", xlsname),
						"able to enter the value in LastName", "unaable to enter the value in LastName");
				SendKeys(user.textEmail, getData("User", "textEmail", xlsname), "able to enter the value in Email",
						"unable to enter the value in Email");
				sleepMethod(2000);
				Click(user.dropDownDepartment, "unable to move to othehrs");
				sleepMethod(2000);
				user.dropdownDynamic(getData("User", "dropDownDepartment", xlsname), "dropDownDepartment is selected",
						"dropDownDepartment is not Selected");
				sleepMethod(2000);
				Click(user.dropDownDesignation, "unable to move to others");
				sleepMethod(2000);
				user.dropdownDynamic(getData("User", "dropDownDesignation", xlsname), "Designation is selected ",
						"Designation is not selected ");

				sleepMethod(2000);
				actionCilck(driver.findElement(user.dropDownUserRole), "clicked on userrole",
						"Unable to click on userrole");
				sleepMethod(2000);
				SendKeys(user.dropDownUserRole, getData("User", "dropDownUserRole", xlsname), "UserRole is selected ",
						"UserRole is not selected ");
				user.keyListArrowDownEnter();
				sleepMethod(2000);
				driver.findElement(user.dropDownUserRole).sendKeys(Keys.ARROW_DOWN);
				driver.findElement(user.dropDownUserRole).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				test.log(LogStatus.INFO, "Click on Save/Submit button");
				isEnabled(user.buttonSubmit, user.buttonSubmit, "Able to click on submit button",
						"Unable to click on submit button");
				sleepMethod(10000);
				test.log(LogStatus.INFO, "Click on Signoff");
				if (driver.findElement(home.buttonAccount).isDisplayed()) {
					Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
					sleepMethod(2000);
					actionCilck(driver.findElement(home.linkSignoff), "signoff sucesful", "Unable to do signoff");
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
