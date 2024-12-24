package com.testcase.testsuite;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.ETeki_CreateJobPage;
import com.testcase.Pages.ETeki_HomePage;
import com.testcase.Pages.ETeki_JobsPage;
import com.testcase.Pages.ETeki_LoginPage;
import com.testcase.Pages.ETeki_OpenJobsPage;
import com.testcase.Pages.ETeki_UsersPage;

public class ETeki_JobCreation extends TestBase{
	@Test(priority=2)
	public void validCreateJob() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		try {
			test = report.startTest(getData("CreateJob", "Jira_Story_Name", xlsname));
			if (getData("Dashboard", "Validating Create Job with mandatory fields data.", xlsname)
					.equalsIgnoreCase("N")) {
				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();

				ETeki_UsersPage userspage = new ETeki_UsersPage();

				ETeki_JobsPage jobspage = new ETeki_JobsPage();

				test.log(LogStatus.INFO, "Click on Actions Button.");

				click(userspage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions Button.", "User unable to click on Actions Button.");

				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Create Job.");

				click(userspage.optionCreateJob, driver.findElement(homepage.Dashboard),
						"User able to click on Create Job.", "User unable to click on Create Job.");

				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();

				waitforelement(shortwaitvalue);

				// Fill job details.
				test.log(LogStatus.INFO, "Fill the required details in Create Job popup");

				sendkeys(createjob.hiringCompanyName, getData("CreateJob", "Hiring Company Name", xlsname),
						"User able to enter Hiring Company Name.", "User unable to enter Hiring Company Name.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.jobTitle, getData("CreateJob", "Job Title", xlsname),
						"User able to enter Job Title.", "User unable to enter Job Title.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.jobDescription, getData("CreateJob", "Job Description", xlsname),
						"User able to enter Job Description.", "User unable to enter Job Description.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.skillsToBeAssessed, getData("CreateJob", "Skills to be Assessed", xlsname),
						"User able to select Assessed Skills.", "User unable to select Assessed Skills.");
				waitforelement(shortwaitvalue);

				driver.findElement(createjob.skillsToBeAssessed).sendKeys(Keys.ENTER);

				waitforelement(shortwaitvalue);

				sendkeys(createjob.yearsOfExperience, getData("CreateJob", "Years of Experience", xlsname),
						"User able to enter Years of Experience.", "User unable to enter Years of Experience.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.jobClosingDate, loginpage.futureDate(15), "User able to enter Job Closing Date.",
						"User unable to enter Job Closing Date.");

				waitforelement(shortwaitvalue);

				selectDropdownValue(createjob.areasOfExpertise, getData("CreateJob", "Areas of Expertise", xlsname),
						"User able to select Areas of Expertise.", "User unable to select Areas of Expertise.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.projectedNumberOfCandidates,
						getData("CreateJob", "Projected Number of Candidates", xlsname),
						"User able to enter Projected Number of Candidates.",
						"User unable to enter Projected Number of Candidates.");

				waitforelement(shortwaitvalue);

				click(createjob.clientSelectedInterviewers, driver.findElement(createjob.createJobTitle),
						"User able to select Client Selected Interviewers.",
						"User unable to select Client Selected Interviewers.");

				waitforelement(shortwaitvalue);

				SubmitClick(createjob.saveButton, "User able to Create Job.", "User unable to Create Job.");

				waitforelement(mediumwaitvalue);

				Assert.assertEquals(driver.findElement(createjob.successMessage).getText(),
						getData("CreateJob", "Success_Message", xlsname));

				click(createjob.okButton, driver.findElement(homepage.Dashboard), "User able to click on OK button.",
						"User unable to click on OK button.");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Verify Created Job in Open Jobs");

				ETeki_OpenJobsPage openjobspage = new ETeki_OpenJobsPage();

				click(jobspage.headerOpenJobs, driver.findElement(homepage.Dashboard),
						"User able to click on Open Jobs", "User unable to click on Open Jobs");

				waitforelement(mediumwaitvalue);

				sendkeys(openjobspage.openJobsSearchInput, getData("CreateJob", "Job Title", xlsname),
						"User able to input search value.", "User unable to input search value.");

				waitforelement(shortwaitvalue);

				click(openjobspage.openJobsSearchButton, driver.findElement(homepage.Dashboard),
						"User able to perform Search.", "User unable to perform Search.");

				waitforelement(mediumwaitvalue);

				click(openjobspage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions.", "User unable to click on Actions.");

				waitforelement(shortwaitvalue);

				click(openjobspage.fullJobDetails, driver.findElement(homepage.Dashboard),
						"User able to click on Full Job Details.", "User unable to click on Full Job Details.");

				waitforelement(shortwaitvalue);

				click(openjobspage.fullJobDetailsTitle, driver.findElement(openjobspage.fullJobDetailsTitle),
						"User able to view Full Job Details.", "User unable to view Full Job Details.");

				waitforelement(shortwaitvalue);

				click(openjobspage.closeButton, driver.findElement(homepage.Dashboard),
						"User able to close Full Job Details.", "User unable to close Full Job Details.");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "User logout eTeki successfully");

				click(homepage.userProfile, driver.findElement(homepage.Dashboard), "User able to click on Profile.",
						"User unable to click on Profile.");

				waitforelement(shortwaitvalue);

				click(homepage.logoutButton, driver.findElement(homepage.userProfile), "User able to click on Logout.",
						"User unable to click on Logout.");

				waitforelement(mediumwaitvalue);

			} else
			{
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}

	@Test(priority = 0)
	public void emptyCreateJob() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		try {
			test = report.startTest(getData("CreateJob", "Jira_Story_Name_1", xlsname));
			if (getData("Dashboard", "Validating Create Job without mandatory fields data.", xlsname)
					.equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				loginpage.login(config.getProperty("userName"), config.getProperty("password"),
						"User able to enter valid Username.", "User unable to enter invalid Username.",
						"User able to enter valid Password.", "User unable to enter invalid Password.");

				waitforelement(mediumwaitvalue);

				ETeki_HomePage homepage = new ETeki_HomePage();

				alert(homepage.Dashboard);

				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(homepage.Dashboard), "User able to click on eTeki Logo.",
						"User unable to click on eTeki Logo.");

				waitforelement(mediumwaitvalue);

				ETeki_JobsPage jobspage = new ETeki_JobsPage();

				test.log(LogStatus.INFO, "Click on Users Header.");

				click(jobspage.headerUsers, driver.findElement(homepage.Dashboard),
						"User able to click on Users Header.", "User unable to click on Users Header.");

				waitforelement(mediumwaitvalue);

				ETeki_UsersPage userspage = new ETeki_UsersPage();

				test.log(LogStatus.INFO, "Click on Actions Button.");

				click(userspage.buttonActions, driver.findElement(homepage.Dashboard),
						"User able to click on Actions Button.", "User unable to click on Actions Button.");

				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Create Job.");

				click(userspage.optionCreateJob, driver.findElement(homepage.Dashboard),
						"User able to click on Create Job.", "User unable to click on Create Job.");

				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on save button without filling mandatory fields.");

				SubmitClick(createjob.saveButton,
						"User able to find mandatory field validations after clicking on save button.",
						"User unable to find mandatory field validations after clicking on save button.");

				Assert.assertEquals(driver.findElement(createjob.validationCompanyName).getText(),
						getData("CreateJob", "Validation_CompanyName", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationJobTitle).getText(),
						getData("CreateJob", "Validation_JobTitle", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationJobDescription).getText(),
						getData("CreateJob", "Validation_JobDescription", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationSkillsToBeAssessed).getText(),
						getData("CreateJob", "Validation_SkillsToBeAssessed", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationYearsOfExperience).getText(),
						getData("CreateJob", "Validation_YearsOfExperience", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationJobClosingDate).getText(),
						getData("CreateJob", "Validation_JobClosingDate", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationAreasOfExpertise).getText(),
						getData("CreateJob", "Validation_AreasOfExpertise", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationProjectedNumberOfCandidates).getText(),
						getData("CreateJob", "Validation_ProjectedNumberOfCandidates", xlsname));

				Assert.assertEquals(driver.findElement(createjob.validationClientSelectedInterviewers).getText(),
						getData("CreateJob", "Validation_ClientSelectedInterviewers", xlsname));

				waitforelement(shortwaitvalue);

			}else
			{
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}
	@Test(priority=1)
	public void invalidCreateJob() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		try {
			test = report.startTest(getData("CreateJob", "Jira_Story_Name", xlsname));
			if (getData("Dashboard", "Validating Create Job with mandatory fields data.", xlsname)
					.equalsIgnoreCase("N")) {

				// Fill job details.
				test.log(LogStatus.INFO, "Fill the Create Job popup with invalid data.");

				ETeki_CreateJobPage createjob = new ETeki_CreateJobPage();

				sendkeys(createjob.hiringCompanyName, getData("CreateJob", "Hiring Company Name", xlsname),
						"User able to enter Hiring Company Name.", "User unable to enter Hiring Company Name.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.jobTitle, getData("CreateJob", "Job Title", xlsname),
						"User able to enter Job Title.", "User unable to enter Job Title.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.jobDescription, getData("CreateJob", "Job Description", xlsname),
						"User able to enter Job Description.", "User unable to enter Job Description.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.skillsToBeAssessed, getData("CreateJob", "Skills to be Assessed", xlsname),
						"User able to select Assessed Skills.", "User unable to select Assessed Skills.");
				waitforelement(shortwaitvalue);

				driver.findElement(createjob.skillsToBeAssessed).sendKeys(Keys.ENTER);

				waitforelement(shortwaitvalue);

				sendkeys(createjob.yearsOfExperience, getData("CreateJob", "Years of Experience", xlsname),
						"User able to enter Years of Experience.", "User unable to enter Years of Experience.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.jobClosingDate, getData("CreateJob", "Job Closing Date", xlsname),
						"User able to enter invalid Job Closing Date.",
						"User unable to enter invalid Job Closing Date.");

				waitforelement(shortwaitvalue);

				selectDropdownValue(createjob.areasOfExpertise, getData("CreateJob", "Areas of Expertise", xlsname),
						"User able to select Areas of Expertise.", "User unable to select Areas of Expertise.");

				waitforelement(shortwaitvalue);

				sendkeys(createjob.projectedNumberOfCandidates,
						getData("CreateJob", "Projected Number of Candidates", xlsname),
						"User able to enter Projected Number of Candidates.",
						"User unable to enter Projected Number of Candidates.");

				waitforelement(shortwaitvalue);

				click(createjob.clientSelectedInterviewers, driver.findElement(createjob.createJobTitle),
						"User able to select Client Selected Interviewers.",
						"User unable to select Client Selected Interviewers.");

				waitforelement(shortwaitvalue);

				SubmitClick(createjob.saveButton, "User unable to Create Job with invalid data.",
						"User able to Create Job with invalid data.");

				waitforelement(mediumwaitvalue);

				Assert.assertEquals(driver.findElement(createjob.validationInvalidJobClosingDate).getText(),
						getData("CreateJob", "Validation_Invalid_JobClosingDate", xlsname));

				SubmitClick(createjob.cancelButton, "User able to cancel Job Creation.",
						"User unable to cancel Job Creation.");
				waitforelement(shortwaitvalue);

			}else
			{
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}
	}
}
	
	