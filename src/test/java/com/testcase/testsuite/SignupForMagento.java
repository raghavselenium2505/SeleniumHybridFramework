package com.testcase.testsuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.LoginPageMagento;

public class SignupForMagento extends TestBase {


		static int var;

		//@JiraCreateIssue(isCreateIssue = true)
		@Test
		public void UserCreation() throws Exception {
			// test=report.startTest(getData("TC001", "Jira_Story_Name", xlsname));
			test = report.startTest(getData("Signup", "Jira_Story_Name", xlsname));

			String skip = getData("DashBoard", "Signup", xlsname);
			try {
				if (skip.equalsIgnoreCase("N")) {
					test.log(LogStatus.INFO, "Click on Create an Account");
					LoginPageMagento login=new LoginPageMagento(driver);
					test.log(LogStatus.INFO, "Enter all valid details for signup");

					click(login.buttonCreateAnAccount, "Able to click on  Create button", "Unable to click on Create button");
					waitforelement(5000);
					login.signup(getData("Signup", "textFirstName", xlsname), getData("Signup", "textLastName", xlsname), getData("Signup", "textEmailId", xlsname), getData("Signup", "textPassword", xlsname), getData("Signup", "textConfirmPassword", xlsname));
					//login.signup("A", "B", "A@CD121.com", "CBA15487", "CBA15487");
					
					click(login.buttonRegister, "Able to click  on Register", "Unable to click on Register");
				if (driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed()) {
					test.log(LogStatus.PASS, "Enter all valid details for signup");
					
					
				}
				
					passcount++;
				} else {
					
					test.log(LogStatus.SKIP, "This testcases will not run as this testcases is skipped");
				}
			} catch (Exception e) {
				// driver.findElement(user.dropDownUserRole).sendKeys(Keys.ENTER);
				test.log(LogStatus.FAIL, "Exception happeneed" + e);
				executioncount++;
				failcount++;
			}
		}
	}


