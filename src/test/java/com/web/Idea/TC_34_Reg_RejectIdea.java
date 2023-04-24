package com.web.Idea;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.testng.annotations.Test;

import com.base.web.JiraCreateIssue;
import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddIdeaPage;
import com.testcase.Pages.HomePage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllIdeaPage;

public class TC_34_Reg_RejectIdea extends TestBase {
	static int var;

	@JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("RejectedIdea", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "RejectedIdea", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("RejectedIdea", "Jira_Story_Description", xlsname));
				LoginPage login = new LoginPage(driver);
				test.log(LogStatus.INFO, "Enter the login the details");
				login.login("Dashboard | Navigo");
				sleepMethod(2000);
				test.log(LogStatus.INFO, "Click on Analytics and check for count earlier");
				HomePage home = new HomePage(driver);
				actionCilck(driver.findElement(home.analytics), "Able to click on Analytics page",
						"Unable to click on Analytics page");
				sleepMethod(2000);
				AddIdeaPage add = new AddIdeaPage(driver);
				elementHighlight(driver.findElement(add.ideaRejection));
				String IdeaRejectedCount_S = driver.findElement(add.ideaRejection).getText();
				int IdeaRejectedCount = Integer.parseInt(IdeaRejectedCount_S);
				IdeaRejectedCount++;
				test.log(LogStatus.INFO, "Click on ideapage and enter the details");
				actionCilck(driver.findElement(home.ideaPage), "Able to click on Idea page",
						"Unable to click on Idea page");
				sleepMethod(3000);
				actionCilck(driver.findElement(home.viewAll_1), "Able to click on Viewall page",
						"Unable to click on Idea page");
				ViewAllIdeaPage view = new ViewAllIdeaPage(driver);
				view.isDisplayed(getData("RejectedIdea", "ideaName", xlsname),
						"Able to click on the idea which is selected ",
						"Unable to click on the idea which is selected");
				sleepMethod(5000);
				Click(view.rejectButton, "Able to click on Reject button", "Unable to click on Reject button");
				sleepMethod(2000);
				Click(view.buttonYes, "Able to click on Yes button", "Unable to click on Yes button");
				sleepMethod(5000);
				elementHighlight(driver.findElement(view.textRejected));
				if (driver.findElement(view.textRejected).getText().equalsIgnoreCase("Rejected")) {
					test.log(LogStatus.PASS, "Status changed to Rejection");
				} else {
					test.log(LogStatus.FAIL, "Unable to change the status to Rejection");
				}
				sleepMethod(5000);
				actionCilck(driver.findElement(home.analytics), "Able to click on Analytics page",
						"Unable to click on Analytics page");
				elementHighlight(driver.findElement(add.ideaRejection));
				String value = driver.findElement(add.ideaRejection).getText();

				test.log(LogStatus.INFO, "Compare whether the count is added in the dashboard or not");
				int IdeaRejetion = Integer.parseInt(value);
				if (IdeaRejetion == IdeaRejectedCount) {
					test.log(LogStatus.PASS,
							"Compared with earlier count ,idea rejection count is created in dashboard");

				} else {
					test.log(LogStatus.FAIL, "Unable to see the count idea rejection added in the dashboard");
				}
				test.log(LogStatus.INFO, "Click on Signoff");

				Click(home.buttonAccount, "clicked on account button", "unable to click on account button");
				sleepMethod(2000);
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