package com.web.regressiontest;

import org.testng.annotations.Test;
import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.UserListPage;
public class TC_02_Reg_Users extends TestBase {
	static int var;

	//@com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void editUser() throws Exception {
		// test=report.startTest(getData("TC001", "Jira_Story_Name", xlsname));
		test = report.startTest(getData("Useredit", "Jira_Story_Name", xlsname));

		String skip = getData("DashBoard", "Useredit", xlsname);
		UserListPage user = new UserListPage(driver);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("Useredit", "Jira_Story_Description", xlsname));

				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				
				sleepMethod(8000);
				test.log(LogStatus.INFO, "Click on UserManagement and click on Users");
				actionCilck(driver.findElement(home.linkUserManagement), "able to click on UserManagement",
						"Unable to click on UserManagement");
				sleepMethod(5000);
				actionCilck(driver.findElement(home.linkUser), "able to click on User", "Unable to click on User");

				test.log(LogStatus.INFO, "Search for the data to edit");
				String textEmailSearch = getData("Useredit", "textEmailsearch", xlsname);
				if (!textEmailSearch.equalsIgnoreCase("")) {
					SendKeys(user.textEmailSearch, getData("Useredit", "textEmailsearch", xlsname),
							"Entered the email for search sucesfully", "issue with entering email");
				}
				sleepMethod(4000);
				Click(user.buttonSearch, "click on search button", "unable to click on search button");

				test.log(LogStatus.INFO, "Click on edit button");
				Click(user.iconEdit, "able to click on edit ", "Unable to click on edit");
				test.log(LogStatus.INFO, "Modify the data which is needed");

				String textfirstName = getData("Useredit", "textFirstName", xlsname);
				sleepMethod(3000);
				if (!textfirstName.equalsIgnoreCase("NA")) {
					System.out.println("i am either anywhere" + 1);

					driver.findElement(user.textFirstName).clear();
					sleepMethod(3000);
					SendKeys(user.textFirstName, getData("Useredit", "textFirstName", xlsname),
							"able to enter the value in FirstName", "unaable to enter the value in FirstName");
				}
				String textLastName = getData("Useredit", "textLastName", xlsname);

				if (!textLastName.equalsIgnoreCase("NA")) {
					System.out.println("i am either anywhe" + 2);
					driver.findElement(user.textLastName).clear();
					SendKeys(user.textLastName, getData("Useredit", "textLastName", xlsname),
							"able to enter the value in LastName", "unaable to enter the value in LastName");
				}
				String textEmailId = getData("Useredit", "textEmail", xlsname);
				System.out.println(textEmailId + "email");
				if (!textEmailId.equalsIgnoreCase("NA")) {
					driver.findElement(user.editTextEmail).clear();
					SendKeys(user.editTextEmail, getData("Useredit", "textEmail", xlsname),
							"able to enter the value in Email", "unable to enter the value in Email");
					sleepMethod(5000);
				}
			

				test.log(LogStatus.INFO, "Click on Save button");

				isEnabled(user.buttonSubmit, user.buttonSubmit,"able to click on submit button","Unable to click on submit button");
				sleepMethod(4000);
				test.log(LogStatus.INFO, "signoff");

				if(driver.findElement(home.buttonAccount).isDisplayed()) {
					
					Click(home.buttonAccount,"clicked on account button","unable to click on account button");
					sleepMethod(3000);
					actionCilck(driver.findElement(home.linkSignoff),"signoff sucesful","Unable to do signoff");
				}
				passcount++;
			}
				else
				{
					executioncount++;
					skipCount++;
					test.log(LogStatus.SKIP, "This testcases will not run as this testcases is skipped");

				}
			} catch (Exception e) {
				executioncount++;

				//driver.findElement(user.dropDownUserRole).sendKeys(Keys.ENTER);
				test.log(LogStatus.FAIL, "Exception happeneed" + e);
				failcount++;
			}
	}
		}
