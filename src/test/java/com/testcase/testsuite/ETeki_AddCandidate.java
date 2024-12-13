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

	@Test(priority = 3)
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

				ETeki_LoginPage loginpage = new ETeki_LoginPage();

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				waitforelement(mediumwaitvalue);

				ETeki_HomePage homepage = new ETeki_HomePage();

				alert(homepage.Dashboard);

				test.log(LogStatus.INFO, "Click on eTeki Logo.");

				click(homepage.Dashboard, driver.findElement(By.xpath("//div[@class='input-group global-search']")),
						"User able to click on eTeki Logo.", "User unable to click on eTeki Logo.");

				test.log(LogStatus.INFO, "Verify Created Job in Open Jobs.");

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
                        addcandidatepage.ValidationMessage_FirstName);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationLastName).getText(),
                        addcandidatepage.ValidationMessage_LastName);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationEmailAddress).getText(),
                        addcandidatepage.ValidationMessage_EmailAddress);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationPhoneNumber).getText(),
                        addcandidatepage.ValidationMessage_PhoneNumber);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationUploadChooseFile).getText(),
                        addcandidatepage.ValidationMessage_UploadChooseFile);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationCandidateReportDueBy).getText(),
                        addcandidatepage.ValidationMessage_CandidateReportDueBy);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationTime).getText(),
                        addcandidatepage.ValidationMessage_Time);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationCandidateCountry).getText(),
                        addcandidatepage.ValidationMessage_CandidateCountry);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationCandidateTimeZone).getText(),
                        addcandidatepage.ValidationMessage_CandidateTimeZone);

                Assert.assertEquals(driver.findElement(addcandidatepage.validationYearsOfExperience).getText(),
                        addcandidatepage.ValidationMessage_YearsofExperience);
			}else 
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}

	@Test(priority =4)
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
						driver.findElement(By.xpath("//a[@ng-click='candidate.brief_bio_file = undefined']")),
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
						addcandidatepage.InvalidationMessage_CandidateReportDueBy);
				Assert.assertEquals(driver.findElement(addcandidatepage.validationTime).getText(),
                        addcandidatepage.ValidationMessage_Time);
				waitforelement(longwaitvalue);
				SubmitClick(addcandidatepage.buttonCancel, "User able to click on Cancel.",
						"User unable to click on Cancel.");
			}else 
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
						driver.findElement(By.xpath("//a[@ng-click='candidate.brief_bio_file = undefined']")),
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
						driver.findElement(By.xpath("//h3[contains(text(),'Provide Candidate Availability ')]")),
						"User able to select First slot", "User unable to select First slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.slotSelection_2,
						driver.findElement(By.xpath("//h3[contains(text(),'Provide Candidate Availability ')]")),
						"User able to select Second slot", "User unable to select Second slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.slotSelection_3,
						driver.findElement(By.xpath("//h3[contains(text(),'Provide Candidate Availability ')]")),
						"User able to select Thrid slot", "User unable to select Thrid slot");

				waitforelement(mediumwaitvalue);

				click(candidateavailabilitypage.radioButtonCandidate,
						driver.findElement(candidateavailabilitypage.radioButtonCandidate),
						"User able to select Candidate Radio button.", "User unable to select Candidate Radio button.");

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

			}else 
				test.log(LogStatus.SKIP, "Testcase got skipped please check the excel sheet for reference");

			

		} catch (Exception e) {
			e.printStackTrace();
			captureScreenshot(value_extentreport);
		}

	}
}