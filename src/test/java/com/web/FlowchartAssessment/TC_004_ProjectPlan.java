package com.web.FlowchartAssessment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddIdeaPage;
import com.testcase.Pages.HomePage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;

public class TC_004_ProjectPlan extends TestBase {
	static int var;

	//@com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("ProjectPlan", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "ProjectPlan", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("ProjectPlan", "Jira_Story_Description", xlsname));
				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(2000);

				test.log(LogStatus.INFO, "Click on Analytics and check for count earlier for adding of pipeline");
				HomePage home = new HomePage(driver);

				actionCilck(driver.findElement(home.analytics), "Able to click on Analytics page",
						"Unable to click on Analytics page");
				sleepMethod(2000);
				test.log(LogStatus.INFO,
						"Click on linkAssessments and click on Viewall and select the completed assessment");
				actionCilck(driver.findElement(home.linkAssessments), "able to click on linkAssessments",
						"Unable to click on linkAssessments");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkViewAllAssessment), "able to click on viewall",
						"Unable to click on viewall");
				ViewAllAssessmentPage view = new ViewAllAssessmentPage(driver);
				test.log(LogStatus.INFO, "Check for data nd also click on Project plan ");
				view.clickDisplay("An automation _1", "Able to click on assessment", "Unable to click on assessment");

				sleepMethod(5000);

				actionCilck(driver.findElement(view.buttonGeneate), "Able to click on Geneate",
						"Unable to click on Geneate");
				sleepMethod(2000);
				Click(view.submitPreprojectPlan, "Click on Submit preproject plan",
						"Unable to click on Preproject plan");
				sleepMethod(2000);
				actionCilck(driver.findElement(view.buttonBack), "Able to click on button Back",
						"Unable to click on button back");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkViewAllAssessment), "able to click on viewall",
						"Unable to click on viewall");
				view.clickwithIndexDisplay(getData("ProjectPlan", "assessmentName", xlsname),
						"Able to see the status to completed", "Unable to change the status to completed");
				test.log(LogStatus.INFO, "Click on Signoff");
				Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkSignoff), "signoff sucesful", "Unable to do signoff");
				passcount++;
				sleepMethod(2000);
			}
			else {

				test.log(LogStatus.SKIP, "This testcases will not run as this testcases is skipped");
				executioncount++;
				skipCount++;
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Exception happened" + e);
			failcount++;
			executioncount++;
		}
	}
}