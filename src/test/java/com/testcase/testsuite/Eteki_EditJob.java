package com.testcase.testsuite;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.ETeki_AddCandidateAvailabilityPage;
import com.testcase.Pages.ETeki_AddCandidatePage;
import com.testcase.Pages.ETeki_CreateJobPage;
import com.testcase.Pages.ETeki_HomePage;
import com.testcase.Pages.ETeki_JobsPage;
import com.testcase.Pages.ETeki_LoginPage;
import com.testcase.Pages.ETeki_OpenJobsPage;
import com.testcase.Pages.ETeki_UsersPage;

public class Eteki_EditJob extends TestBase {

	@Test(priority = 6)
	public void Eteki_EditJobInvalidDate() throws Exception {

		shortwaitvalue = Integer.parseInt(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.parseInt(config.getProperty("mediumwait"));
		longwaitvalue = Integer.parseInt(config.getProperty("longwait"));
		verylongwaitvalue = Integer.parseInt(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.parseInt(config.getProperty("extraverylongwait"));

		test = report.startTest(getData("EditJobPage", "Jira_Story_Name", xlsname));
		try {
			if (getData("Dashboard", "EditJobInvalidDate", xlsname).equalsIgnoreCase("N")) {
				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));
				waitforelement(mediumwaitvalue);

				alert(homepage.Dashboard);

				Boolean signIn = driver.findElement(homepage.Dashboard).isDisplayed();

				loginpage.verifyInput(signIn, "User login was success.", "User login was failed.");

				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(homepage.Dashboard), "User able to click on eTeki Logo.",
						"User unable to click on eTeki Logo.");

				waitforelement(mediumwaitvalue);

				ETeki_JobsPage jobspage = new ETeki_JobsPage();

				Boolean dashboard = driver.findElement(jobspage.headerUsers).isDisplayed();

				loginpage.verifyInput(dashboard, "User successfully clicked on eTeki Logo.",
						"User failed to click on eTeki Logo.");

				test.log(LogStatus.INFO, "Click on Actions Button.");
				ETeki_OpenJobsPage openJobpage = new ETeki_OpenJobsPage();

				click(openJobpage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions Button.", "User unable to click on Actions Button.");
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on edit Job.");

				actionclick(driver.findElement(openJobpage.linkEditJob), "Able to click on edit job",
						"Unable to click on edit Job");
				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();
				sendkeys(createjob.jobClosingDate, loginpage.futureDate(14), "User able to enter Job Closing Date.",
						"User unable to enter Job Closing Date.");
				waitforelement(mediumwaitvalue);
				Assert.assertTrue(driver.findElement(openJobpage.titleEditJob).isDisplayed(),
						"Unable to see the edit job");

				test.log(LogStatus.INFO, "Check invalid fields displayed or not ");

				click(openJobpage.buttonUpdateAndApprove);

				isdisplay(openJobpage.errorInvalidDate, "Able to display errorjobdate",
						"Unable to display error jobddate");
				click(openJobpage.buttonClose);
				waitforelement(longwaitvalue);

				// span[contains(text(),'Job due date expired')]

			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}

	@Test(priority = 7, groups = "smoke")
	public void Eteki_EditJobInvalidJobFormat() throws Exception {

		test = report.startTest(getData("EditJobPage", "Jira_Story_Name_1", xlsname));
		try {
			if (getData("Dashboard", "Add Candidate", xlsname).equalsIgnoreCase("N")) {
				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();

				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(homepage.Dashboard), "User able to click on eTeki Logo.",
						"User unable to click on eTeki Logo.");

				waitforelement(mediumwaitvalue);

				ETeki_JobsPage jobspage = new ETeki_JobsPage();

				Boolean dashboard = driver.findElement(jobspage.headerUsers).isDisplayed();

				loginpage.verifyInput(dashboard, "User successfully clicked on eTeki Logo.",
						"User failed to click on eTeki Logo.");

				test.log(LogStatus.INFO, "Click on Actions Button.");
				ETeki_OpenJobsPage openJobpage = new ETeki_OpenJobsPage();

				click(openJobpage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions Button.", "User unable to click on Actions Button.");
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on edit Job.");

				actionclick(driver.findElement(openJobpage.linkEditJob), "Able to click on edit job",
						"Unable to click on edit Job");
				Assert.assertTrue(driver.findElement(openJobpage.titleEditJob).isDisplayed(),
						"Unable to see the edit job");

				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();

				test.log(LogStatus.INFO, "Clear  Job Description");

				clear(createjob.jobDescription, "Able to clear the data on job Description",
						"Unable to clear the data on job Description");
				ETeki_AddCandidatePage addcandidatepage = new ETeki_AddCandidatePage();
				chooseFile(addcandidatepage.uploadChooseFile, driver.findElement(addcandidatepage.uploadChooseFile),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\" + "DSL.jpg",
						"User able to Upload File.", "User unable to Upload File.");

				waitforelement(mediumwaitvalue);
				ETeki_AddCandidateAvailabilityPage candidateavailabilitypage = new ETeki_AddCandidateAvailabilityPage();
				click(candidateavailabilitypage.buttonOk, driver.findElement(homepage.Dashboard),
						"User able to click on Ok button.", "User unable to click on Ok button.");

				waitforelement(mediumwaitvalue);
				click(openJobpage.buttonClose);
				waitforelement(mediumwaitvalue);

			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}

	@Test(priority = 8)
	public void eTekiEditJob() throws Exception {

		test = report.startTest(getData("EditJobPage", "Jira_Story_Name", xlsname));
		try {
			if (getData("DashBoard", "Edit Job", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));
				waitforelement(mediumwaitvalue);

				alert(homepage.Dashboard);

				Boolean signIn = driver.findElement(homepage.Dashboard).isDisplayed();

				loginpage.verifyInput(signIn, "User login was success.", "User login was failed.");

				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(homepage.Dashboard), "User able to click on eTeki Logo.",
						"User unable to click on eTeki Logo.");

				waitforelement(mediumwaitvalue);

				ETeki_JobsPage jobspage = new ETeki_JobsPage();

				Boolean dashboard = driver.findElement(jobspage.headerUsers).isDisplayed();

				loginpage.verifyInput(dashboard, "User successfully clicked on eTeki Logo.",
						"User failed to click on eTeki Logo.");

				test.log(LogStatus.INFO, "Click on action button");
				ETeki_OpenJobsPage openJobpage = new ETeki_OpenJobsPage();

				click(openJobpage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions Button.", "User unable to click on Actions Button.");
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Edit Job option from dropdown menu");
				actionclick(driver.findElement(openJobpage.linkEditJob), "Able to click on edit job",
						"Unable to click on edit Job");
				Assert.assertTrue(driver.findElement(openJobpage.titleEditJob).isDisplayed(),
						"Unable to see the edit job");

				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();

				clear(createjob.hiringCompanyName, "Able to clear the data", "Unable to clear the data");
				test.log(LogStatus.INFO, "Edit the hiring company name correctly");
				sendkeys(createjob.hiringCompanyName, getData("EditJobPage", "CompanyName", xlsname),
						"User is able to edit the hiring companay name",
						"User is unable to edit the hiring companay name");
				waitforelement(shortwaitvalue);

				clear(createjob.jobTitle, "Able to clear the data", "Unable to clear the data");
				test.log(LogStatus.INFO, "Edit the job title correctly");
				sendkeys(createjob.jobTitle, getData("EditJobPage", "jobTitle", xlsname),
						"User is able to edit the job title", "User is unable to edit the job title");
				waitforelement(shortwaitvalue);

				clear(createjob.jobDescription, "Able to clear the data", "Unable to clear the data");
				test.log(LogStatus.INFO, "Edit the job description");
				sendkeys(createjob.jobDescription, getData("EditJobPage", "description", xlsname),
						"User is able to edit the job description", "Unser is unable to edit the job description");
				waitforelement(shortwaitvalue);

				clear(createjob.yearsOfExperience, "Able to clear the data", "Unable to clear the data");
				test.log(LogStatus.INFO, "Edi the Years of experience correctly ");
				sendkeys(createjob.yearsOfExperience, getData("EditJobPage", "experienceRequired", xlsname),
						"User is able to edit the years of experience",
						"User is unable to edit the years of experience");
				waitforelement(mediumwaitvalue);

				clear(createjob.jobClosingDate, "Able to clear the data", "Unable to clear the data");
				test.log(LogStatus.INFO, "select date from calendar correctly");
				sendkeys(createjob.jobClosingDate, getData("EditJobPage", "Job Closing Date", xlsname),
						"User is able to edit the job closing date", "User is unable to edit the job closing date");
				waitforelement(mediumwaitvalue);

				clear(createjob.projectedNumberOfCandidates, "Able to clear the data", "Unable to clear the data");
				test.log(LogStatus.INFO, "Edi the projected number of candidates correctly");
				sendkeys(createjob.projectedNumberOfCandidates,
						getData("EditJobPage", "Projected Number of Candidates", xlsname),
						"User is able to edit the no of candidates", "User is unable to edit the no of candidates");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Update and Approve button");
				click(openJobpage.buttonUpdateAndApprove);

				waitforelement(verylongwaitvalue);

				click(createjob.okButton, driver.findElement(homepage.Dashboard), "User able to click on ok button",
						"User unable to click on ok button");
				waitforelement(mediumwaitvalue);
				test.log(LogStatus.INFO, "User logout eTeki successfully");
				click(openJobpage.buttonClose);
				waitforelement(mediumwaitvalue);

				click(homepage.userProfile, driver.findElement(homepage.Dashboard), "User able to click on Profile.",
						"User unable to click on Profile.");

				waitforelement(mediumwaitvalue);

				click(homepage.logoutButton, driver.findElement(homepage.userProfile), "User able to click on Logout.",
						"User unable to click on Logout.");

				waitforelement(mediumwaitvalue);

			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}

	@Test(priority = 9, groups = "smoke")
	public void addCandidate() throws Exception {
		test = report.startTest(getData("EditJobPage", "Jira_Story_Name_2", xlsname));
		try {
			if (getData("Dashboard", "EditJobMandatoryFields", xlsname).equalsIgnoreCase("N")) {
				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();
				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(homepage.Dashboard), "User able to click on eTeki Logo.",
						"User unable to click on eTeki Logo.");

				waitforelement(mediumwaitvalue);

				ETeki_JobsPage jobspage = new ETeki_JobsPage();

				Boolean dashboard = driver.findElement(jobspage.headerUsers).isDisplayed();

				loginpage.verifyInput(dashboard, "User successfully clicked on eTeki Logo.",
						"User failed to click on eTeki Logo.");

				test.log(LogStatus.INFO, "Click on Actions Button.");
				ETeki_OpenJobsPage openJobpage = new ETeki_OpenJobsPage();

				click(openJobpage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions Button.", "User unable to click on Actions Button.");
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on edit Job.");

				actionclick(driver.findElement(openJobpage.linkEditJob), "Able to click on edit job",
						"Unable to click on edit Job");
				Assert.assertTrue(driver.findElement(openJobpage.titleEditJob).isDisplayed(),
						"Unable to see the edit job");

				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();

				test.log(LogStatus.INFO, "Clear all the fields which  are mandatory fields in edit job fields ");

				clear(createjob.hiringCompanyName, "Able to clear  CompanyName", "Unable to cleared CompanyName");
				waitforelement(shortwaitvalue);
				clear(createjob.jobTitle, "Able to clear Job title", "Unable to clear job Title");
				waitforelement(shortwaitvalue);
				clear(createjob.jobDescription, "Able to clear Job description", "Unable to clear on Job Description");
				waitforelement(shortwaitvalue);
				clear(createjob.yearsOfExperience, "Able to clear years of Experience",
						"unable to clear years of experience field");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Update and Approve button");

				click(openJobpage.buttonUpdateAndApprove);

				openJobpage.isdisplay(openJobpage.errorCompanyname, openJobpage.errorExperience,
						openJobpage.errorJobDescription, openJobpage.errorJobTitle, "errors displayed sucesfully",
						"Unable to display error msgs");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "User logout eTeki successfully");
				click(openJobpage.buttonClose);
				waitforelement(mediumwaitvalue);

				click(homepage.userProfile, driver.findElement(homepage.Dashboard), "User able to click on Profile.",
						"User unable to click on Profile.");

				waitforelement(mediumwaitvalue);

				click(homepage.logoutButton, driver.findElement(homepage.userProfile), "User able to click on Logout.",
						"User unable to click on Logout.");

				waitforelement(mediumwaitvalue);
			} else {
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}

}