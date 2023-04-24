package com.web.FlowchartAssessment;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPage;
//import com.testcase.Pages.ViewAllAssessmentPage;
import com.testcase.Pages.ViewAllAssessmentPage;

public class TC_002_Reg_EditAssessment extends TestBase {
	static int var;

	// @com.base.web.JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("EditAssessment_6", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "EditAssessment_6", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("EditAssessment_6", "Jira_Story_Description", xlsname));
				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(2000);
				test.log(LogStatus.INFO, "Click on linkAssessments and click on Viewall");
				actionCilck(driver.findElement(home.linkAssessments), "able to click on linkAssessments",
						"Unable to click on linkAssessments");
				sleepMethod(2000);
				actionCilck(driver.findElement(home.linkViewAllAssessment), "able to click on viewall",
						"Unable to click on viewall");
				ViewAllAssessmentPage view = new ViewAllAssessmentPage(driver);
				view.isDisplayed(getData("EditAssessment_6", "assessmentName", xlsname), "Able to click on",
						"Unable to click on ");
				sleepMethod(2000);
				test.log(LogStatus.INFO, "Click on View and open the questionaire page and answer the questions");

				Click(view.view, "Unable to click on view");

				view.questionCheck(getData("EditAssessment_6", "Question1", xlsname), "Able to answer a question",
						"Unable to answer a question1");
				sleepMethod(2000);
				view.questionCheck(getData("EditAssessment_6", "Question2", xlsname), "Able to answer a question2",
						"Unable to answer a question2");
				sleepMethod(2000);
				view.questionCheck(getData("EditAssessment_6", "Question3", xlsname), "Able to answer a question3",
						"Unable to answer a question3");
				sleepMethod(2000);
				view.questionCheck(getData("EditAssessment_6", "Question4", xlsname), "Able to answer a question4",
						"Unable to answer a question4");
				sleepMethod(2000);
				view.questionCheck(getData("EditAssessment_6", "Question5", xlsname), "Able to answer a question5",
						"Unable to answer a question5");
				sleepMethod(2000);
				
				  if(driver.findElement(view.buttonSubmit).isDisplayed()) {
				  
				  Click(view.buttonSubmit, "Unable to click on Submit button"); }
				 	
				
				
				sleepMethod(2000);
actionCilck(driver.findElement(By.xpath("//button[@id='close-assessment-questions_page_button']")),"Able to", "Unable to ");
			//	driver.findElement(By.xpath("//button[@id='close-assessment-questions_page_button']")).click();
				Click(view.view, "Unable to click on view button");
				sleepMethod(2000);

				actionCilck(driver.findElement(view.buttonSubmitQuestionaire), "Able to click on Questionaire",
						"Unable to click on Questionaire");
				sleepMethod(3000);
				Click(view.buttonConfirm, "Unable to click on Confirm");
				sleepMethod(12000);
				isDisplayed(view.status_Complete, "Questionares are entered and submited status changed to completed",
						"Questionares are entered and submited status changed to completed");
				test.log(LogStatus.INFO, "Click on Signoff");
				Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
				actionCilck(driver.findElement(home.linkSignoff), "signoff sucesful", "Unable to do signoff");
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
