package com.web.FlowchartAssessment;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddAssessmentPage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;

public class TC_001_Reg_AddAssessment  extends TestBase {
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
				
				test.log(LogStatus.INFO, "Click on linkAssessments and click on Viewall");
				actionCilck(driver.findElement(home.linkAssessments), "able to click on linkAssessments",
						"Unable to click on linkAssessments");
				sleepMethod(4000);
				actionCilck(driver.findElement(home.linkViewAllAssessment), "able to click on viewall", "Unable to click on viewall");
				sleepMethod(2000);
				ViewAllAssessmentPage view =new ViewAllAssessmentPage(driver);
				AddAssessmentPage add=new AddAssessmentPage(driver);
				
				test.log(LogStatus.INFO, "Click on Add Assessment and click on all details ");
				Click(view.addAssessment, "Able to click on the Assessment page", "Unable to click on add assessment page");
				sleepMethod(2000);
				Click(add.dropDownSelect, "unable to click on select button");
				sleepMethod(2000);
				actiontabsWithText(getData("Add Assessment", "DropDowntext",xlsname), "Able to click on Capital markets", "Unable to click on Captital markets");	
				sleepMethod(2000);
				SendKeys(add.value, getData("Add Assessment", "Title",xlsname), "Able to enter the tittle ", "Unable to enter the title");
				sleepMethod(2000);
				SendKeys(add.textDescription, getData("Add Assessment", "Description",xlsname), "Able to enter the value of Description", "Unable to enter the value in   description ");
				sleepMethod(2000);
				Click(add.dropDownSelect_Users, "Unable to click on users");
				sleepMethod(2000);
				add.clickwithli(getData("Add Assessment", "Users",xlsname), "User is selected", "User is not selected");
				sleepMethod(2000);
				Click(view.buttonStartAssessment, "Able to click on start assessment", "Unable to click on Assessment");
				sleepMethod(10000);
				test.log(LogStatus.INFO, "Click on Signoff");
				Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkSignoff), "signoff sucesful", "Unable to do signoff");
				passcount++;

			}  else {
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
