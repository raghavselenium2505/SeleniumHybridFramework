package com.testcase.testsuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.ETeki_AddCandidateAvailabilityPage;
import com.testcase.Pages.ETeki_AddCandidatePage;
import com.testcase.Pages.ETeki_HomePage;
import com.testcase.Pages.ETeki_LoginPage;
import com.testcase.Pages.ETeki_OpenJobsPage;
import com.testcase.Pages.ETeki_ScheduleInterviewPage;

public class ETeki_AddCandidateAvailability extends TestBase {

	@Test
	public void addCandidate() throws Exception {
		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(getData("AddCandidateAvailability", "Jira_Story_Name", xlsname));

		if (getData("Dashboard", "Add Candidate Availability", xlsname).equalsIgnoreCase("N")) {

			test.log(LogStatus.INFO, "Enter valid Login Credentails.");

			ETeki_LoginPage loginpage = new ETeki_LoginPage();

			loginpage.login(config.getProperty("userName"), config.getProperty("password"));

			waitforelement(mediumwaitvalue);
			
			ETeki_HomePage homepage = new ETeki_HomePage();

			alert(homepage.Dashboard);

			test.log(LogStatus.INFO, "Click on eTeki Logo.");

			click(homepage.Dashboard, driver.findElement(By.xpath("//div[@class='input-group global-search']")), "User able to click on eTeki Logo.", "User unable to click on eTeki Logo.");

			test.log(LogStatus.INFO, "Verify Created Job in Open Jobs.");

			ETeki_OpenJobsPage openjobspage = new ETeki_OpenJobsPage();

			waitforelement(mediumwaitvalue);

			selectDropdownValue(openjobspage.openJobsSelectStatus, getData("AddCandidateAvailability", "Select Status", xlsname),
					"User able to select Status.", "User unable to select Status.");

			waitforelement(mediumwaitvalue);

			ETeki_AddCandidatePage addcandidatepage = new ETeki_AddCandidatePage();

			test.log(LogStatus.INFO, "User able to Add Candidate to Active Job.");

			SubmitClick(openjobspage.buttonActions, "User able to click on Actions button.",
					"User unable to click on Actions button.");

			waitforelement(shortwaitvalue);

			click(openjobspage.addCandidate, driver.findElement(openjobspage.buttonActions), "User able to click on Add Candidate.",
					"User unable to click on Add Candidate.");

			waitforelement(shortwaitvalue);

			sendkeys(addcandidatepage.firstName, getData("AddCandidateAvailability", "First Name", xlsname),
					"User able to enter First Name.", "User unable to enter First Name.");

			waitforelement(shortwaitvalue);

			sendkeys(addcandidatepage.lastName, getData("AddCandidateAvailability", "Last Name", xlsname),
					"User able to enter Last Name.", "User unable to enter Last Name.");

			waitforelement(shortwaitvalue);

			sendkeys(addcandidatepage.emailAddress,
					randomNumberGeneration() + getData("AddCandidateAvailability", "Email Address", xlsname),
					"User able to enter Email Address.", "User unable to enter Email Address.");

			System.out.println(randomValue);

			waitforelement(mediumwaitvalue);

			sendkeys(addcandidatepage.phoneNumber, getData("AddCandidateAvailability", "Phone Number", xlsname),
					"User able to enter Phone Number.", "User unable to enter Phone Number.");

			waitforelement(mediumwaitvalue);

			chooseFile(addcandidatepage.uploadResume, driver.findElement(By.xpath("//a[@ng-click='candidate.brief_bio_file = undefined']")),
					System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
							+ getData("AddCandidateAvailability", "File Name", xlsname),
					"User able to Upload File.", "User unable to Upload File.");

			waitforelement(mediumwaitvalue);

			sendkeys(addcandidatepage.candidateReportDueBy, getData("AddCandidateAvailability", "Candidate Report Due By", xlsname),
					"User able to enter Candidate Report Due By.", "User unable to enter Candidate Report Due By.");

			waitforelement(shortwaitvalue);

			selectDropdownValue(addcandidatepage.candidateCountry,
					getData("AddCandidateAvailability", "Candidate Country", xlsname), "User able to select Country.",
					"User unable to select Country.");

			waitforelement(mediumwaitvalue);

			selectDropdownValue(addcandidatepage.candidateTimeZone,
					getData("AddCandidateAvailability", "Candidate Time Zone", xlsname), "User able to select Timezone.",
					"User unable to select Timezone.");

			waitforelement(mediumwaitvalue);

			sendkeys(addcandidatepage.yearsOfExperience, getData("AddCandidateAvailability", "Years of Experience", xlsname),
					"User able to enter Years of Experience.", "User unable to enter Years of Experience.");

			waitforelement(shortwaitvalue);

			SubmitClick(addcandidatepage.buttonAddCandidateAvailability, "User able to click on Candidate Availability.",
					"User unable to click on Candidate Availability.");

			waitforelement(verylongwaitvalue);

			test.log(LogStatus.INFO, "User able to Add Candidate Availability to Active Job.");

			ETeki_AddCandidateAvailabilityPage candidateavailabilitypage = new ETeki_AddCandidateAvailabilityPage();

			SubmitClick(candidateavailabilitypage.buttonSkip, "User able to Skip Candidate Availability.",
					"User unable to Skip Candidate Availability.");

			waitforelement(mediumwaitvalue);

			SubmitClick(candidateavailabilitypage.buttonOk, "User able to click on Ok button.",
					"User unable to click on Ok button.");

			waitforelement(mediumwaitvalue);

			sendkeys(openjobspage.openJobsSearchInput, getData("AddCandidateAvailability", "Job Owner", xlsname),
					"User able to input search value.", "User unable to input search value.");

			waitforelement(shortwaitvalue);

			SubmitClick(openjobspage.openJobsSearchButton, "User able to perform Search.", "User unable to perform Search.");

			waitforelement(longwaitvalue);

			click(addcandidatepage.activeJobRow, driver.findElement(addcandidatepage.activeJobRow), "User able to click on Active Job Row.",
					"User unable to click on Active Job Row.");

			waitforelement(longwaitvalue);

			click(addcandidatepage.activeJobRowActionButton, driver.findElement(addcandidatepage.activeJobRowActionButton), "User able to click on Actions button.",
					"User unable to click on Actions button.");

			waitforelement(longwaitvalue);

			System.out.println(addcandidatepage.listitem());

			click(addcandidatepage.manualScheduleOn, driver.findElement(addcandidatepage.manualScheduleOn), "User able to click on Manual Schedule ON option.",
					"User unable to click on Manual Schedule ON option.");

			waitforelement(longwaitvalue);

			click(addcandidatepage.activeJobRowActionButton, driver.findElement(addcandidatepage.activeJobRowActionButton), "User able to click on Actions button.",
					"User unable to click on Actions button.");

			waitforelement(longwaitvalue);

			click(addcandidatepage.scheduuleInterview, driver.findElement(addcandidatepage.scheduuleInterview), "User able to click on Schedule Interview option.",
					"User unable to click on Schedule Interview option.");

			waitforelement(verylongwaitvalue);

			test.log(LogStatus.INFO, "User able to Add Availability through Manual Scheduling.");

			ETeki_ScheduleInterviewPage scheduleinterviewpage = new ETeki_ScheduleInterviewPage();

			click(scheduleinterviewpage.manualscheduling, driver.findElement(scheduleinterviewpage.manualscheduling), "User able to clickon Manual Scheduling option.",
					"User unable to clickon Manual Scheduling option.");

			waitforelement(mediumwaitvalue);

			click(scheduleinterviewpage.selectInterviewer, driver.findElement(scheduleinterviewpage.selectInterviewer), "User able to select Interviewer.",
					"User unable to select Interviewer.");

			waitforelement(mediumwaitvalue);

			click(scheduleinterviewpage.buttonNext, driver.findElement(scheduleinterviewpage.buttonNext), "User able to click on Next Button.",
					"User unable to click on Next button.");

			waitforelement(mediumwaitvalue);

			calendarSelection(scheduleinterviewpage.calendarMonth_YearButton,
					scheduleinterviewpage.calendarMonthSelection, scheduleinterviewpage.calendarDaySelection,
					getData("AddCandidateAvailability", "Month", xlsname), getData("AddCandidateAvailability", "Date", xlsname),
					"User able to select given Month & Date from Calendar.",
					"User unable to select given Month & Date from Calendar.");

			waitforelement(longwaitvalue);

			selectTime(scheduleinterviewpage.inputHours, scheduleinterviewpage.inputMinutes,
					scheduleinterviewpage.inputTimeConvention, getData("AddCandidateAvailability", "TimeHours", xlsname),
					getData("AddCandidateAvailability", "TimeMinutes", xlsname), getData("AddCandidateAvailability", "TimeConvention", xlsname),
					"User able to select Hours, Minutes and Time Convention.",
					"User unable to select Hours, Minutes and Time Convention.");

			waitforelement(longwaitvalue);

			selectDropdownValue(scheduleinterviewpage.scheduleCountry,
					getData("AddCandidateAvailability", "Candidate Country", xlsname), "User able to select Country.",
					"User unable to select Country.");

			waitforelement(mediumwaitvalue);

			selectDropdownValue(scheduleinterviewpage.scheduleTimeZone,
					getData("AddCandidateAvailability", "Candidate Time Zone", xlsname), "User able to select Timezone.",
					"User unable to select Timezone.");

			waitforelement(mediumwaitvalue);

			click(scheduleinterviewpage.buttonScheduleInterview, driver.findElement(scheduleinterviewpage.buttonScheduleInterview), "User able to click on Schedule Interview.",
					"User unable to click on Schedule Interview.");

			waitforelement(longwaitvalue);

			scheduleinterviewpage.scheduleInterviewAlert();

			waitforelement(extraverylongwaitvalue);

			test.log(LogStatus.INFO, "User logout eTeki successfully");

			click(homepage.userProfile, driver.findElement(homepage.Dashboard), "User able to click on Profile.", "User unable to click on Profile.");

			waitforelement(shortwaitvalue);

			click(homepage.logoutButton, driver.findElement(homepage.userProfile), "User able to click on Logout.", "User unable to click on Logout.");

			waitforelement(mediumwaitvalue);

		}
	}
}
