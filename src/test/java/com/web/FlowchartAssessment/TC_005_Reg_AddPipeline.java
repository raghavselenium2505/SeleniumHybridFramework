package com.web.FlowchartAssessment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddIdeaPage;
import com.testcase.Pages.HomePage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;

public class TC_005_Reg_AddPipeline extends TestBase {
	static int var;

	//@com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("AddPipeline", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "AddPipeline", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("AddPipeline", "Jira_Story_Description", xlsname));
				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(2000);

				test.log(LogStatus.INFO, "Click on Analytics and check for count earlier for adding of pipeline");
				HomePage home = new HomePage(driver);

				actionCilck(driver.findElement(home.analytics), "Able to click on Analytics page",
						"Unable to click on Analytics page");
				sleepMethod(2000);
				AddIdeaPage add = new AddIdeaPage(driver);
				String addToPipeline_S = driver.findElement(add.addToPipeline).getText();
				elementHighlight(driver.findElement(add.addToPipeline));

				int addToPipeline = Integer.parseInt(addToPipeline_S);
				addToPipeline++;

				test.log(LogStatus.INFO,
						"Click on linkAssessments and click on Viewall and select the completed assessment");
				actionCilck(driver.findElement(home.linkAssessments), "able to click on linkAssessments",
						"Unable to click on linkAssessments");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkViewAllAssessment), "able to click on viewall",
						"Unable to click on viewall");
				ViewAllAssessmentPage view = new ViewAllAssessmentPage(driver);
				Click(view.statusSelect, "Unable to click on status");
				sleepMethod(2000);
				view.ValueAdded(getData("AddPipeline", "status", xlsname), "Able to click on Completed",
						"Unable to click on Completed");
				sleepMethod(2000);
				actionCilck(driver.findElement(view.buttonSearch), "Able to click on search ",
						"Unable to click on search");
				sleepMethod(4000);
				view.clickDisplay(getData("AddPipeline", "ideaName", xlsname), "Able to click on",
						"Unable to click on ");
				sleepMethod(2000);
				Click(view.AddPipeline, "Able to click on Add pipeline", "Unable to click on Add pipeline");
				sleepMethod(2000);
				Click(view.buttonConfirm, "Unable to click on Yes button");
				
				
				sleepMethod(5000);
				test.log(LogStatus.INFO, "Compare whether the count of pipeline added in the dashboard or not");
				actionCilck(driver.findElement(home.analytics), "Able to click on Analytics page",
						"Unable to click on Analytics page");
				elementHighlight(driver.findElement(add.addToPipeline));
				String value = driver.findElement(add.addToPipeline).getText();

				int ideaapprovalCount = Integer.parseInt(value);
				if (ideaapprovalCount == addToPipeline) {
					test.log(LogStatus.PASS,
							"Compared with earlier count ,idea approval count is created in dashboard");
				} else {
					test.log(LogStatus.FAIL, "Unable to see the count idea approvaladded in the dashboard");
				}
				test.log(LogStatus.INFO, "Click on Signoff");
				Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkSignoff), "signoff sucesful", "Unable to do signoff");
				passcount++;
				sleepMethod(2000);
			} else {
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