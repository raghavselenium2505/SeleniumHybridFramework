package com.web.Idea;
import org.testng.annotations.Test;
import com.base.web.JiraCreateIssue;
import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.AddIdeaPage;
import com.testcase.Pages.HomePage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;
public class TC_33_Reg_AddIdeaPage extends TestBase {
	static int var;

@JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("AddIdea", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "AddIdea", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("AddIdea", "Jira_Story_Description", xlsname));
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
				String Ideasubmission = driver.findElement(add.ideasubmission).getText();
				int IdeaCountBefore = Integer.parseInt(Ideasubmission);
				IdeaCountBefore++;
				test.log(LogStatus.INFO, "Click on ideapage and enter the details");

				actionCilck(driver.findElement(home.ideaPage), "Able to click on Idea page",
						"Unable to click on Idea page");
				sleepMethod(3000);
				actionCilck(driver.findElement(home.viewAll_1), "Able to click on Viewall page",
						"Unable to click on Idea page");
				AddIdeaPage adIdea = new AddIdeaPage(driver);
				Click(adIdea.buttonAddIdea, "Able to click on Add Idea", "Unable to click on Add Idea page");
				sleepMethod(3000);
				SendKeys(adIdea.textTitle, getData("AddIdea", "title", xlsname), "Able to add the title ",
						"Unable to enter text");
				sleepMethod(4000);
				SendKeys(adIdea.textDescription, getData("AddIdea", "description", xlsname),
						"Able to add the description", "Unable to add the text");
				sleepMethod(3000);
				Click(add.dropDownClick, "Unable to click on dropDownclick");
				sleepMethod(2000);
				actiontabsWithText(getData("AddIdea", "DropDowntext", xlsname), "Able to click on Capital markets",
						"Unable to click on Captital markets");
				ViewAllAssessmentPage view = new ViewAllAssessmentPage(driver);
				sleepMethod(2000);
				view.questionCheck(getData("AddIdea", "Question1", xlsname), "Able to answer a question1",
						"Unable to answer a question3");
				sleepMethod(2000);
				view.questionCheck(getData("AddIdea", "Question2", xlsname), "Able to answer a question2",
						"Unable to answer a question4");
				sleepMethod(2000);
				view.questionCheck(getData("AddIdea", "Question3", xlsname), "Able to answer a question3",
						"Unable to answer a question5");
				sleepMethod(2000);
				Click(add.submitButon, "Unable to click on Submit button");
				sleepMethod(2000);
				Click(home.analytics, "Unable to click on Analystics");
				String value = driver.findElement(add.ideasubmission).getText();
				System.out.println(value);
				test.log(LogStatus.INFO, "Compare whether the count is added in the dashboard or not");

				int ideasubmittionAfter = Integer.parseInt(value);
				if (ideasubmittionAfter == IdeaCountBefore) {
					test.log(LogStatus.PASS,
							"Compared with earlier count ,idea submission count is created in dashboard");

				} else {
					test.log(LogStatus.FAIL, "Unable to see the count added in the dashboard");

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
