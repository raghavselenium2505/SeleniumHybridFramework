package com.testcase.testsuite;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.ETeki_AddCandidateAvailabilityPage;
import com.testcase.Pages.ETeki_AddCandidatePage;
import com.testcase.Pages.ETeki_HomePage;
import com.testcase.Pages.ETeki_LoginPage;
import com.testcase.Pages.ETeki_OpenJobsPage;

public class ETeki_AddCandidate extends TestBase {
	@Test(priority =3)
	public void emptyAddCandidate() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));
		try {
			test = report.startTest(getData("AddCandidate", "Jira_Story_Name", xlsname));

			if (getData("Dashboard", "Validating Add Candidate without mandatory fields data.", xlsname)
					.equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "Enter valid Login Credentails.");
				test.log(LogStatus.INFO, "Enter valid Login Credentails.");

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				loginpage.login(config.getProperty("userName"), config.getProperty("password"),
						"User able to enter valid Username.", "User unable to enter invalid Username.",
						"User able to enter valid Password.", "User unable to enter invalid Password.");

				waitforelement(mediumwaitvalue);

				ETeki_HomePage homepage = new ETeki_HomePage();

				alert(homepage.Dashboard);

				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(homepage.GlobalSearch),
						"User able to click on eTeki Logo.", "User unable to click on eTeki Logo.");

				ETeki_OpenJobsPage openjobspage = new ETeki_OpenJobsPage();

				waitforelement(mediumwaitvalue);

				selectDropdownValue(openjobspage.openJobsSelectStatus,
						getData("AddCandidate", "Select Status", xlsname), "User able to select Status.",
						"User unable to select Status.");

				waitforelement(mediumwaitvalue);

				ETeki_AddCandidatePage addcandidatepage = new ETeki_AddCandidatePage();

				test.log(LogStatus.INFO, "User able to Add Candidate to Active Job.");

				SubmitClick(openjobspage.buttonActions, "User able to click on Actions button.",
						"User unable to click on Actions button.");

				waitforelement(shortwaitvalue);

				click(openjobspage.addCandidate, driver.findElement(openjobspage.buttonActions),
						"User able to click on Add Candidate.", "User unable to click on Add Candidate.");

				waitforelement(shortwaitvalue);

				SubmitClick(addcandidatepage.buttonAddCandidateAvailability,
						"User able to click on Candidate Availability.",
						"User unable to click on Candidate Availability.");

				Assert.assertEquals(driver.findElement(addcandidatepage.validationFirstName).getText(),
						getData("AddCandidate", "Validation_FirstName", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationLastName).getText(),
						getData("AddCandidate", "Validation_LastName", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationEmailAddress).getText(),
						getData("AddCandidate", "Validation_EmailAddress", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationPhoneNumber).getText(),
						getData("AddCandidate", "Validation_PhoneNumber", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationUploadChooseFile).getText(),
						getData("AddCandidate", "Validation_UploadChooseFile", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationCandidateReportDueBy).getText(),
						getData("AddCandidate", "Validation_CandidateReportDueBy", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationTime).getText(),
						getData("AddCandidate", "Validation_Time", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationCandidateCountry).getText(),
						getData("AddCandidate", "Validation_CandidateCountry", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationCandidateTimeZone).getText(),
						getData("AddCandidate", "Validation_CandidateTimeZone", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationYearsOfExperience).getText(),
						getData("AddCandidate", "Validation_YearsofExperience", xlsname));
			} else
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}

	@Test(priority = 4)
	public void invalidAddCandidate() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		try {
			test = report.startTest(getData("AddCandidate", "Jira_Story_Name_1", xlsname));

			if (getData("Dashboard", "Validating Add Candidate mandatory fields with invalid data.", xlsname)
					.equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User able to fill Add Candidate details in Active Job.");

				ETeki_AddCandidatePage addcandidatepage = new ETeki_AddCandidatePage();

				sendkeys(addcandidatepage.firstName, getData("AddCandidate", "First Name", xlsname),
						"User able to enter First Name.", "User unable to enter First Name.");

				waitforelement(shortwaitvalue);

				sendkeys(addcandidatepage.lastName, getData("AddCandidate", "Last Name", xlsname),
						"User able to enter Last Name.", "User unable to enter Last Name.");

				waitforelement(shortwaitvalue);

				sendkeys(addcandidatepage.emailAddress,
						randomNumberGeneration() + getData("AddCandidate", "Email Address", xlsname),
						"User able to enter Email Address.", "User unable to enter Email Address.");

				System.out.println(randomValue);

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.phoneNumber, getData("AddCandidate", "Phone Number", xlsname),
						"User able to enter Phone Number.", "User unable to enter Phone Number.");

				waitforelement(mediumwaitvalue);

				chooseFile(addcandidatepage.uploadResume,
						driver.findElement(addcandidatepage.addingCheckfileName),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ getData("AddCandidate", "File Name", xlsname),
						"User able to Upload File.", "User unable to Upload File.");

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.candidateReportDueBy,
						getData("AddCandidate", "Candidate Report Due By", xlsname),
						"User able to enter Candidate Report Due By.", "User unable to enter Candidate Report Due By.");

				waitforelement(shortwaitvalue);

				selectDropdownValue(addcandidatepage.candidateCountry,
						getData("AddCandidate", "Candidate Country", xlsname), "User able to select Country.",
						"User unable to select Country.");

				waitforelement(mediumwaitvalue);

				selectDropdownValue(addcandidatepage.candidateTimeZone,
						getData("AddCandidate", "Candidate Time Zone", xlsname), "User able to select Timezone.",
						"User unable to select Timezone.");

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.yearsOfExperience, getData("AddCandidate", "Years of Experience", xlsname),
						"User able to enter Years of Experience.", "User unable to enter Years of Experience.");

				waitforelement(shortwaitvalue);

				SubmitClick(addcandidatepage.buttonAddCandidateAvailability,
						"User able to click on Candidate Availability.",
						"User unable to click on Candidate Availability.");

				Assert.assertEquals(driver.findElement(addcandidatepage.invalidCandidateReportDueBy).getText(),
						getData("AddCandidate", "Validation_Invalid_CandidateReportDueBy", xlsname));

				Assert.assertEquals(driver.findElement(addcandidatepage.validationTime).getText(),
						getData("AddCandidate", "Validation_Time", xlsname));

				SubmitClick(addcandidatepage.buttonCancel, "User able to click on Cancel.",
						"User unable to click on Cancel.");
			} else
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}

	@Test(priority = 5)
	public void validAddCandidate() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		try {
			test = report.startTest(getData("AddCandidate", "Jira_Story_Name_2", xlsname));

			if (getData("Dashboard", "Validating Add Candidate with mandatory fields data.", xlsname)
					.equalsIgnoreCase("N")) {

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();

				ETeki_OpenJobsPage openjobspage = new ETeki_OpenJobsPage();

				test.log(LogStatus.INFO, "User able to Add Candidate to Active Job.");

				waitforelement(mediumwaitvalue);

				SubmitClick(openjobspage.buttonActions, "User able to click on Actions button.",
						"User unable to click on Actions button.");

				waitforelement(mediumwaitvalue);

				/*
				 * click(openjobspage.addCandidate,driver.findElement(By.xpath(
				 * "//h4[@id='add-candidate-job']")), "User able to click on Add Candidate.",
				 * "User unable to click on Add Candidate.");
				 */

				click(openjobspage.addCandidate, driver.findElement(openjobspage.buttonActions),
						"User able to click on Add Candidate.", "User unable to click on Add Candidate.");
				// driver.findElement(By.xpath("//h4[@id='add-candidate-job']"))

				waitforelement(shortwaitvalue);

				ETeki_AddCandidatePage addcandidatepage = new ETeki_AddCandidatePage();

				sendkeys(addcandidatepage.firstName, getData("AddCandidate", "First Name", xlsname),
						"User able to enter First Name.", "User unable to enter First Name.");

				waitforelement(shortwaitvalue);

				sendkeys(addcandidatepage.lastName, getData("AddCandidate", "Last Name", xlsname),
						"User able to enter Last Name.", "User unable to enter Last Name.");

				waitforelement(shortwaitvalue);

				sendkeys(addcandidatepage.emailAddress,
						randomNumberGeneration() + getData("AddCandidate", "Email Address", xlsname),
						"User able to enter Email Address.", "User unable to enter Email Address.");

				System.out.println(randomValue);

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.phoneNumber, getData("AddCandidate", "Phone Number", xlsname),
						"User able to enter Phone Number.", "User unable to enter Phone Number.");

				waitforelement(mediumwaitvalue);

				chooseFile(addcandidatepage.uploadResume,
						driver.findElement(addcandidatepage.addingCheckfileName),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ getData("AddCandidate", "File Name", xlsname),
						"User able to Upload File.", "User unable to Upload File.");

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.candidateReportDueBy, loginpage.futureDate(14),
						"User able to enter Candidate Report Due By.", "User unable to enter Candidate Report Due By.");

				waitforelement(shortwaitvalue);

				selectDropdownValue(addcandidatepage.candidateCountry,
						getData("AddCandidate", "Candidate Country", xlsname), "User able to select Country.",
						"User unable to select Country.");

				waitforelement(mediumwaitvalue);

				selectDropdownValue(addcandidatepage.candidateTimeZone,
						getData("AddCandidate", "Candidate Time Zone", xlsname), "User able to select Timezone.",
						"User unable to select Timezone.");

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.yearsOfExperience, getData("AddCandidate", "Years of Experience", xlsname),
						"User able to enter Years of Experience.", "User unable to enter Years of Experience.");

				waitforelement(shortwaitvalue);

				SubmitClick(addcandidatepage.buttonAddCandidateAvailability,
						"User able to click on Candidate Availability.",
						"User unable to click on Candidate Availability.");

				// Note :- slot selection will work only when we have Job closing time more than
				// a week from current date.

				test.log(LogStatus.INFO, "User able to Add Candidate Availability.");

				ETeki_AddCandidateAvailabilityPage candidateavailabilitypage = new ETeki_AddCandidateAvailabilityPage();

				waitforelement(extraverylongwaitvalue);

				click(candidateavailabilitypage.slotSelection_1,
						driver.findElement(candidateavailabilitypage.titleCandidateAvailablity),
						"User able to select First slot", "User unable to select First slot");
				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.slotSelection_2,
						driver.findElement(candidateavailabilitypage.titleCandidateAvailablity),
						"User able to select First slot", "User unable to select Second slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.slotSelection_3,
						driver.findElement(candidateavailabilitypage.titleCandidateAvailablity),
						"User able to select First slot", "User unable to select Third slot");
				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.radioButtonCandidate,
						driver.findElement(candidateavailabilitypage.radioButtonCandidate),
						"User able to select Candidate Radio button.", "User unable to select Candidate Radio button.");

				waitforelement(shortwaitvalue);

				SubmitClick(candidateavailabilitypage.buttonSubmit, "User able to submit Candidate Availability.",
						"User unable to submit Candidate Availability.");

				waitforelement(longwaitvalue);

				Assert.assertEquals(driver.findElement(candidateavailabilitypage.successMessage).getText(),
						getData("AddCandidate", "Success_Message", xlsname));

				waitforelement(mediumwaitvalue);
				click(candidateavailabilitypage.buttonOk, driver.findElement(homepage.Dashboard),
						"User able to click on Ok button.", "User unable to click on Ok button.");

				waitforelement(mediumwaitvalue);

				/*
				 * test.log(LogStatus.INFO, "User logout eTeki successfully");
				 * 
				 * click(homepage.userProfile, driver.findElement(homepage.logoutButton),
				 * "User able to click on Profile.", "User unable to click on Profile.");
				 * 
				 * waitforelement(mediumwaitvalue);
				 * 
				 * click(homepage.logoutButton, driver.findElement(homepage.userProfile),
				 * "User able to click on Logout.", "User unable to click on Logout.");
				 */
				waitforelement(mediumwaitvalue);

			} else
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}

	@Test(priority = 13)
	public void validAddCandidateWithRecruiter() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		try {
			test = report.startTest(getData("AddCandidate", "Jira_Story_Name_2", xlsname));

			if (getData("Dashboard", "Validating Add Candidate with recruiter radio button", xlsname)
					.equalsIgnoreCase("N")) {

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				ETeki_HomePage homepage = new ETeki_HomePage();

				ETeki_OpenJobsPage openjobspage = new ETeki_OpenJobsPage();

				test.log(LogStatus.INFO, "User able to Add Candidate to Active Job.");

				waitforelement(mediumwaitvalue);

				SubmitClick(openjobspage.buttonActions, "User able to click on Actions button.",
						"User unable to click on Actions button.");

				waitforelement(mediumwaitvalue);
				click(openjobspage.addCandidate, driver.findElement(openjobspage.buttonActions),
						"User able to click on Add Candidate.", "User unable to click on Add Candidate.");
				waitforelement(shortwaitvalue);

				ETeki_AddCandidatePage addcandidatepage = new ETeki_AddCandidatePage();

				sendkeys(addcandidatepage.firstName, getData("AddCandidate", "First Name", xlsname),
						"User able to enter First Name.", "User unable to enter First Name.");

				waitforelement(shortwaitvalue);

				sendkeys(addcandidatepage.lastName, getData("AddCandidate", "Last Name", xlsname),
						"User able to enter Last Name.", "User unable to enter Last Name.");

				waitforelement(shortwaitvalue);

				sendkeys(addcandidatepage.emailAddress,
						randomNumberGeneration() + getData("AddCandidate", "Email Address", xlsname),
						"User able to enter Email Address.", "User unable to enter Email Address.");

				System.out.println(randomValue);

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.phoneNumber, getData("AddCandidate", "Phone Number", xlsname),
						"User able to enter Phone Number.", "User unable to enter Phone Number.");

				waitforelement(mediumwaitvalue);

				chooseFile(addcandidatepage.uploadResume,
						driver.findElement(addcandidatepage.addingCheckfileName),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ getData("AddCandidate", "File Name", xlsname),
						"User able to Upload File.", "User unable to Upload File.");

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.candidateReportDueBy, loginpage.futureDate(14),
						"User able to enter Candidate Report Due By.", "User unable to enter Candidate Report Due By.");

				waitforelement(shortwaitvalue);

				selectDropdownValue(addcandidatepage.candidateCountry,
						getData("AddCandidate", "Candidate Country", xlsname), "User able to select Country.",
						"User unable to select Country.");

				waitforelement(mediumwaitvalue);

				selectDropdownValue(addcandidatepage.candidateTimeZone,
						getData("AddCandidate", "Candidate Time Zone", xlsname), "User able to select Timezone.",
						"User unable to select Timezone.");

				waitforelement(mediumwaitvalue);

				sendkeys(addcandidatepage.yearsOfExperience, getData("AddCandidate", "Years of Experience", xlsname),
						"User able to enter Years of Experience.", "User unable to enter Years of Experience.");

				waitforelement(shortwaitvalue);

				SubmitClick(addcandidatepage.buttonAddCandidateAvailability,
						"User able to click on Candidate Availability.",
						"User unable to click on Candidate Availability.");
				waitforelement(shortwaitvalue);
				// Note :- slot selection will work only when we have Job closing time more than
				// a week from current date.

				test.log(LogStatus.INFO, "User able to Add Candidate Availability.");

				ETeki_AddCandidateAvailabilityPage candidateavailabilitypage = new ETeki_AddCandidateAvailabilityPage();

				waitforelement(extraverylongwaitvalue);

				click(candidateavailabilitypage.slotSelection_1,
						driver.findElement(candidateavailabilitypage.titleCandidateAvailablity),
						"User able to select First slot", "User unable to select First slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.slotSelection_2,
						driver.findElement(candidateavailabilitypage.titleCandidateAvailablity),
						"User able to select First slot", "User unable to select Second slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.slotSelection_3,
						driver.findElement(candidateavailabilitypage.titleCandidateAvailablity),
						"User able to select First slot", "User unable to select Third slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.radioButtonRecruiter,
						driver.findElement(candidateavailabilitypage.radioButtonRecruiter),
						"User able to select Recruiter Radio button.", "User unable to select Recruiter Radio button.");

				waitforelement(shortwaitvalue);

				SubmitClick(candidateavailabilitypage.buttonSubmit, "User able to submit Candidate Availability.",
						"User unable to submit Candidate Availability.");

				waitforelement(longwaitvalue);

				click(candidateavailabilitypage.buttonOk, driver.findElement(homepage.Dashboard),
						"User able to click on Ok button.", "User unable to click on Ok button.");

				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "User logout eTeki successfully");

				click(homepage.userProfile, driver.findElement(homepage.logoutButton), "User able to click on Profile.",
						"User unable to click on Profile.");

				waitforelement(mediumwaitvalue);

				click(homepage.logoutButton, driver.findElement(homepage.userProfile), "User able to click on Logout.",
						"User unable to click on Logout.");

				waitforelement(mediumwaitvalue);

			} else
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}
}