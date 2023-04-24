package com.web.FlowchartAssessment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.base.web.JiraCreateIssue;
import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.HomePage;
import com.testcase.Pages.LoginPage;
import com.testcase.Pages.ViewAllAssessmentPage;

public class TC_37_PreProjectPlan extends TestBase {
	static int var;

	@JiraCreateIssue(isCreateIssue = true)
	@Test
	public void AddQuestions() throws Exception {
		test = report.startTest(getData("postprojectplan", "Jira_Story_Name", xlsname));
		String skip = getData("DashBoard", "postprojectplan", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				executioncount++;
				var = 0;
				test.log(LogStatus.INFO, getData("postprojectplan", "Jira_Story_Description", xlsname));
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

				sleepMethod(5000);
				ViewAllAssessmentPage view = new ViewAllAssessmentPage(driver);
				test.log(LogStatus.INFO, "Check for data nd also click on Project plan ");
				view.clickDisplay(getData("postprojectplan", "assessmentpage", xlsname), "Able to click on assessment", "Unable to click on assessment");
				sleepMethod(2000);
				String s = getData("postprojectplan", "Condition", xlsname);

				if (s.contains("Y")) {
					// JOptionPane.showMessageDialog(f, "Hello, Welcome to Javatpoint.0");
					
					Click(view.buttonGeneate, "Able to click on Generate","Unable to click on Genearate");
					sleepMethod(2000);
					Click(view.buttonGeneratePlan, "Able to click on Geneate Plan","Unable to click on Generate");
				} else {
					
					
					//driver.findElement(By.xpath("//button[text()='Go To Project Plan']")).click();
			Click(view.buttongoToProjectPlan, "Able to click on ButtonProject", "Unable to click on ButtonProject");
				
				}
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div/div[1]/label/span[1]/span[1]/input"))
						.click();
				sleepMethod(2000);
//1-Quality Automation Process

				test.log(LogStatus.INFO, "1-Quality Automation Process");

				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[3]/div[2]/div[1]"))
						.click();
				sleepMethod(2000);
				Actions action = new Actions(driver);
				sleepMethod(2000);
				driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(2000);
				driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div/div/div/input"))
				.sendKeys(Keys.ARROW_DOWN);
		sleepMethod(2000);

				driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(2000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(4000);

				// 2 Prepare Process design

				test.log(LogStatus.INFO, "2 Prepare Process design");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[4]/div[2]/div[1]"))
						.click();
				sleepMethod(2000);
//				driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[5]/div/div/div/input")).sendKeys("Vivek");
				sleepMethod(2000);
				driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(2000);
				driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(4000);

				// 3-Setup Automation Infrastructure.
				test.log(LogStatus.INFO, "3-Setup Automation Infrastructure.");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[5]/div[2]/div[1]"))
						.click();
				sleepMethod(2000);
			//	driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[5]/div/div/div/input")).sendKeys("Vivek");
				//sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[5]/div/div/div/input"))
				.sendKeys(Keys.ARROW_DOWN);
		sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(4000);

				// 4-obtain solution Design Approaval.
				test.log(LogStatus.INFO, "4-obtain solution Design  Approaval.");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[6]/div[2]/div[1]"))
						.click();
				sleepMethod(3000);
				//driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[5]/div/div/div/input")).sendKeys("Leo");
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(4000);

				// 5-Prepare Solution Design

				test.log(LogStatus.INFO, "5-Prepare Solution Design");
				sleepMethod(3000);
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[7]/div[2]/div[1]"))
						.click();

				sleepMethod(2000);
				//driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[5]/div/div/div/input")).sendKeys("Leo");
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[5]/div/div/div/input"))
				.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(4000);

				// Development and internal

				test.log(LogStatus.INFO, "Development and internal");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[8]/div[2]/div[3]"))
						.click();
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(4000);

				// Develop tesstcases.

				test.log(LogStatus.INFO, "Develop tesstcases.");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[9]/div[2]/div[1]"))
						.click();
				sleepMethod(2000);
			//	driver.findElement(By.xpath("/html/body/div[18]/div[2]/div/div[5]/div/div/div/input"))
		//				.sendKeys("Anees");
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[18]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[18]/div[2]/div/div[5]/div/div/div/input"))
				.sendKeys(Keys.ARROW_DOWN);
		sleepMethod(3000);
		
				driver.findElement(By.xpath("/html/body/div[18]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "SetupAutomation Framework");

				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[10]/div[2]/div[1]"))
						.click();
				sleepMethod(3000);
				/*
				 * driver.findElement(By.xpath(
				 * "/html/body/div[20]/div[2]/div/div[5]/div/div/div/input"))
				 * .sendKeys("Vivek");
				 */
				/*
				 * sleepMethod(4000);
				 */
				// JOptionPane.showMessageDialog(f,"Hello, Welcome to Javatpoint.");

				driver.findElement(By.xpath("/html/body/div[20]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(4000);
				driver.findElement(By.xpath("/html/body/div[20]/div[2]/div/div[5]/div/div/div/input"))
				.sendKeys(Keys.ARROW_DOWN);
		sleepMethod(4000);
				driver.findElement(By.xpath("/html/body/div[20]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				// 9
				test.log(LogStatus.INFO, "Develop Reusable Components");
				// JOptionPane.showMessageDialog(f, "Hello, Welcome to Javatpoint.");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[11]/div[2]/div[1]"))
						.click();
				sleepMethod(3000);
			//	driver.findElement(By.xpath("/html/body/div[22]/div[2]/div/div[5]/div/div/div/input")).sendKeys("Leo");
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[22]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[22]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[22]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();

				sleepMethod(3000);

				// 9
				test.log(LogStatus.INFO, "Core Development");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[12]/div[2]/div[1]"))
						.click();
				sleepMethod(3000);

				/*
				 * driver.findElement(By.xpath(
				 * "/html/body/div[24]/div[2]/div/div[5]/div/div/div/input"))
				 * .sendKeys("Vivek"); sleepMethod(3000);
				 */
				driver.findElement(By.xpath("/html/body/div[24]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);
				driver.findElement(By.xpath("/html/body/div[24]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ARROW_DOWN);
				sleepMethod(3000);

				driver.findElement(By.xpath("/html/body/div[24]/div[2]/div/div[5]/div/div/div/input"))
						.sendKeys(Keys.ENTER);
				sleepMethod(3000);

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Unit Testing");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[13]/div[2]/div[1]"))
						.click();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div"))).click().perform();
				sleepMethod(4000);
				/*
				 * action.moveToElement(driver.findElement(By.xpath(
				 * "//div[2]/div/div[5]/div/div/div"))).sendKeys("Leo") .perform();
				 */
				/*
				 * driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div"))
				 * .sendKeys("Leo"); sleepMethod(3000);
				 */

				action.moveToElement(driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div")))
				.sendKeys(Keys.ARROW_DOWN).perform();
sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div")))
						.sendKeys(Keys.ENTER).perform();

				/*
				 * driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div"))
				 * .sendKeys(Keys.ARROW_DOWN); sleepMethod(3000);
				 * driver.findElement(By.xpath("//div[2]/div/div[5]/div/div/div"))
				 * .sendKeys(Keys.ENTER);
				 */
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

//11

				test.log(LogStatus.INFO, "Internal Integration Testing");
				driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[14]/div[2]/div[1]"))
						.click();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				sleepMethod(4000);
			//	action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys("Vivek")
				//		.perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
			
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

//12

				test.log(LogStatus.INFO, "User Acceptance Tests");

				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[15]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "UAT Signoff");

				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[16]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				/*
				 * sleepMethod(4000);
				 * action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"
				 * ))).sendKeys("Vivek") .perform();
				 */
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "UAT Disc and Exp setting");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[17]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				/*
				 * sleepMethod(4000);
				 * action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"
				 * ))).sendKeys("Joe") .perform();
				 */
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();

				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "UAT Session");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[18]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				sleepMethod(3000);

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Post UAT fixes");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[19]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Go Live Activities");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[20]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(4000);
				/*
				 * action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"
				 * ))).sendKeys(Keys.ENTER) .perform();
				 */

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Product");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[21]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Golive");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[22]/div[2]/div[1]")))
						.click().perform();

				sleepMethod(3000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Hypercare");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[23]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(3000);
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				test.log(LogStatus.INFO, "Hypercare and doc");
				action.moveToElement(driver.findElement(By.xpath(
						"/html/body/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div/div[3]/div/div[3]/div/div[3]/div/div[24]/div[2]/div[1]")))
						.click().perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).click().perform();
				sleepMethod(4000);
				/*
				 * action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"
				 * ))).sendKeys("Leo") .perform(); sleepMethod(4000);
				 */

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);
				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				sleepMethod(4000);

				action.moveToElement(driver.findElement(By.xpath("//div[5]/div/div/div/input"))).sendKeys(Keys.ENTER)
						.perform();

				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				sleepMethod(3000);

				action.moveToElement(driver.findElement(By.xpath("//button[text()='Submit Post Project Plan']")))
						.click().perform();
				sleepMethod(40000);
				action.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/button/span"))).click().perform();
				sleepMethod(4000);
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