package com.web.FlowchartAssessment;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddAssessmentPage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;

public class TC_003_Reg_FlowChart extends TestBase {
	static int var;

	//@com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("Flowchart_2", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "Flowchart_2", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;

				test.log(LogStatus.INFO, getData("Flowchart_2", "Jira_Story_Description", xlsname));
				LoginPage login = new LoginPage(driver);

				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(9000);

				test.log(LogStatus.INFO, "Click on linkAssessments and click on Viewall");
				actionCilck(driver.findElement(home.linkAssessments), "able to click on linkAssessments",
						"Unable to click on linkAssessments");
				sleepMethod(4000);
				actionCilck(driver.findElement(home.linkViewAllAssessment), "able to click on viewall",
						"Unable to click on viewall");
				sleepMethod(4000);
				ViewAllAssessmentPage view = new ViewAllAssessmentPage(driver);
				AddAssessmentPage add = new AddAssessmentPage(driver);

				test.log(LogStatus.INFO, "Click on View Assessment and click on edit details ");
				view.isDisplayed(getData("Flowchart_2", "assessmentName", xlsname), "Able to click on",
						"Unable to click on ");
				sleepMethod(4000);
				Click(view.view_FlowChart, "Able to click on Flowchart", "Unable to click on FlowChart");
				sleepMethod(4000);
				test.log(LogStatus.INFO, "Use the existing template and enter all the valid details");

				view.questionCheck(getData("Flowchart_2", "Template Name", xlsname), "Able to click on template required",
						"Unable to click on Template");
				Click(view.radioTemplate, "Unable to click on template");
				sleepMethod(4000);
				Click(view.togoleWorkFlow, "Unable to click on workflow");
				sleepMethod(3000);
				Click(view.buttonCreateWorkflow, "Able to click on Create WorkFlow",
						"Unable to click on Create Workflow");
				sleepMethod(4000);
				Click(view.ischeckPrimary, "Unable to click on checkprimary");
				sleepMethod(3000);
				Click(view.checkInvolvesDoc, "Unable to click on Involves doc");
				sleepMethod(3000);
				Click(view.buttonOk, "Unable to click on ok button");
				sleepMethod(3000);
				Click(view.workflowcheck, "Unable to click on workFlow check");
				sleepMethod(4000);
				Click(view.buttonSaveWorkFlow, "Unable to click on Save workFlow");
				sleepMethod(3000);
				Click(view.toggleFlowchart, "Able to turn on Flowchart mode", "Unable to turn to flowchart mode");
				sleepMethod(3000);
				Click(view.clickProcess, "Unable to click on Process");
				sleepMethod(4000);

				Click(view.textOrg_id, "Unable to click on textOrg");
				sleepMethod(4000);
				view.ValueAdded(getData("Flowchart_2", "ApplicationAction", xlsname),
						"Able to click on Application action ", "Unable to click on Application action");
				sleepMethod(3000);
				Click(view.dropDownClick, "Unable to click on dropDownclick");
				sleepMethod(4000);
				view.ValueAdded(getData("Flowchart_2", "selectTeam", xlsname), "Able to click on Select team ",
						"Unable to click on select team");
				sleepMethod(3000);
				sleepMethod(3000);
				SendKeys(view.activityTime, getData("Flowchart_2", "activityTime", xlsname),
						"Able to enter the value of activity time", "Unable to enter the value of activity time");
				sleepMethod(3000);
				SendKeys(view.waitTime, getData("Flowchart_2", "waitTime", xlsname),
						"Able to enter the value of wait time", "Unable to enter the value of wait time");
				sleepMethod(3000);
				Click(view.checkboxrework, "Unable to click on checkboxrework");
				sleepMethod(3000);
				SendKeys(view.textRework, getData("Flowchart_2", "Percentage", xlsname),
						"Able to enter the value of Percentage", "Unable to enter the value of Percentage");
				sleepMethod(3000);
				Click(view.buttonSubmit_Flowchart, "Able to click on submit after entering all the details",
						"Unable to click on submit buttton");
				sleepMethod(4000);

				Click(view.dropDownClick_1, "Unable to click on dropDown click");

				Click(view.textOrg_id, "Unable to click on textOrg");
				sleepMethod(4000);
		
				view.ValueAdded(getData("Flowchart_2", "ApplicationAction_2", xlsname),
						"Able to click on Application action ", "Unable to click on Application action");
				
				
				sleepMethod(3000);
				Click(view.dropDownClick, "Unable to click on dropDownclick");
				sleepMethod(4000);
				view.ValueAdded(getData("Flowchart_2", "selectTeam_2", xlsname), "Able to click on Select team ",
						"Unable to click on select team");
				sleepMethod(3000);
				SendKeys(view.activityTime, getData("Flowchart_2", "activityTime_2", xlsname),
						"Able to enter the value of activity time", "Unable to enter the value of activity time");
				sleepMethod(3000);
				SendKeys(view.waitTime, getData("Flowchart_2", "waitTime_2", xlsname),
						"Able to enter the value of wait time", "Unable to enter the value of wait time");
				sleepMethod(3000);
				Click(view.checkboxrework, "Unable to click on checkboxrework");
				sleepMethod(3000);
				SendKeys(view.textRework, getData("Flowchart_2", "Percentage_2", xlsname),
						"Able to enter the value of Percentage", "Unable to enter the value of Percentage");
				sleepMethod(3000);
				Click(view.buttonSubmit_Flowchart, "Able to click on submit after entering all the details",
						"Unable to click on submit buttton");
				sleepMethod(4000);

				Click(view.buttonPublish, "Able to click on Publish button", "Unable to click on Publish button");
				sleepMethod(4000);
				Click(view.buttonOk_Flowchart, "Able to click on Ok button", "Unable to click on Ok Button");
				sleepMethod(4000);
				driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/div/div[2]/div/button")).click();
				test.log(LogStatus.INFO, "Click on Signoff");
				Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
				sleepMethod(6000);
				actionCilck(driver.findElement(home.linkSignoff), "signoff sucesful", "Unable to do signoff");

				sleepMethod(9000);

				passcount++;

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
