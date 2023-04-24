package com.web.Idea;
import org.testng.annotations.Test;
import com.base.web.JiraCreateIssue;
import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddAssessmentPage;
import com.testcase.Pages.AddIdeaPage;
import com.testcase.Pages.HomePage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;
import com.testcase.Pages.ViewAllIdeaPage;

public class TC_35_Reg_ApprovalIdea extends TestBase {
	static int var;

	@JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("ApprovalIdea", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "ApprovalIdea", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("ApprovalIdea", "Jira_Story_Description", xlsname));
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
				String IdeaApprovalcount_S = driver.findElement(add.ideaApproval).getText();
				elementHighlight(driver.findElement(add.ideaApproval));

				int IdeaApprovalcount = Integer.parseInt(IdeaApprovalcount_S);
				IdeaApprovalcount++;
				test.log(LogStatus.INFO, "Click on ideapage and enter the details");

				actionCilck(driver.findElement(home.ideaPage), "Able to click on Idea page",
						"Unable to click on Idea page");
				sleepMethod(3000);
				actionCilck(driver.findElement(home.viewAll_1), "Able to click on Viewall page",
						"Unable to click on Idea page");

				ViewAllIdeaPage view = new ViewAllIdeaPage(driver);
				view.isDisplayed(getData("ApprovalIdea", "ideaName", xlsname),
						"Able to click on the idea which is selected ",
						"Unable to click on the idea which is selected");
				sleepMethod(5000);
				Click(view.approveButton, "Able to click on approve button", "Unable to click on approve button");
				sleepMethod(2000);
				Click(view.buttonYes, "ABle to click on Yes button", "Unable to click on Yes button");
				AddAssessmentPage add1 = new AddAssessmentPage(driver);

				Click(add1.dropDownSelect_Users, "Unable to click on users");
				sleepMethod(2000);
				add1.clickwithli(getData("ApprovalIdea", "Users", xlsname), "User is selected", "User is not selected");
				sleepMethod(2000);
				ViewAllAssessmentPage view1 = new ViewAllAssessmentPage(driver);

				Click(view1.buttonStartAssessment, "Able to click on start assessment",
						"Unable to click on Assessment");
				sleepMethod(5000);
				actionCilck(driver.findElement(home.viewAll_1), "Able to click on Viewall page",
						"Unable to click on Idea page");
				sleepMethod(2000);
				view.isDisplayed(getData("ApprovalIdea", "ideaName", xlsname),
						"Able to click on the idea which is selected ",
						"Unable to click on the idea which is selected");
				elementHighlight(driver.findElement(view.textApprove));
				if (driver.findElement(view.textApprove).getText().equalsIgnoreCase("Approved")) {
					test.log(LogStatus.PASS, "Approved the idea");

				} else {
					test.log(LogStatus.FAIL, "Unable to Approve the idea");
				}
				sleepMethod(2000);
				actionCilck(driver.findElement(home.analytics), "Able to click on Analytics page",
						"Unable to click on Analytics page");
				elementHighlight(driver.findElement(add.ideaApproval));
				String value = driver.findElement(add.ideaApproval).getText();
				test.log(LogStatus.INFO, "Compare whether the count is added in the dashboard or not");
				int ideaapprovalCount = Integer.parseInt(value);
				if (ideaapprovalCount == IdeaApprovalcount) {
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
				sleepMethod(10000);
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
