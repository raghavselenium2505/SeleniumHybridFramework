package com.testcase.Pages;

import org.openqa.selenium.By;

import com.base.web.TestBase;

interface candidateCount {
	public int listitem();
}

public class ETeki_AddCandidatePage extends TestBase implements candidateCount {

	public String stringCandidateCount;

	public int candidateCountValue, listItemIndex;

	public By firstName = By.xpath("//input[@placeholder='First Name']");

	public By validationFirstName = By.xpath("//p[@class='help-block'][contains(text(),'First Name')]");

	public By lastName = By.xpath("//input[@placeholder='Last Name']");

	public By validationLastName = By.xpath("//p[@class='help-block'][contains(text(),'Last Name')]");

	public By emailAddress = By.xpath("//input[@placeholder='Email Address']");

	public By validationEmailAddress = By.xpath("//span[@class='help-block'][contains(text(),'Email')]");

	public By selectFlag = By
			.xpath("//div[@class='flag-dropdown']/ul[@class='country-list hide']/li[@data-dial-code='91']");

	public By phoneNumber = By.id("phone");

	public By validationPhoneNumber = By.xpath("//p[@class='help-block'][contains(text(),'Phone number')]");

	public By uploadResume = By.xpath("//button[@filetype='brief_bio_file']");

	public By uploadChooseFile = By.xpath("//a[contains(text(),'Choose File')]");

	public By validationUploadChooseFile = By.xpath("//span[@class='help-block'][contains(text(),'Resume File')]");

	public By candidateReportDueBy = By
			.xpath("//input[@placeholder='Candidate Report Due By'][@name='preferred_interview_date']");

	public By validationCandidateReportDueBy = By
			.xpath("//span[@class='help-block'][contains(text(),'Candidate report due date')]");
	
	public By invalidCandidateReportDueBy = By.xpath("//span[@class='help-block'][contains(text(),'Please enter valid date')]");

	public By timeHours = By.xpath("//input[@placeholder='HH']");

	public By validationTime = By.xpath("//span[@class='help-block'][contains(text(),'Candidate report due time')]");

	public By timeMinutes = By.xpath("//input[@placeholder='MM']");

	public By showMeridian = By
			.xpath("//td[@class='uib-time am-pm']/button[@ng-class='{disabled: noToggleMeridian()}']");

	public By candidateCountry = By.xpath("//select[@placeholder='Select Country'][@name='country']");

	public By validationCandidateCountry = By
			.xpath("//span[@class='help-block'][contains(text(),'Candidate country')]");

	public By candidateTimeZone = By.xpath("//select[@placeholder='Select TimeZone'][@name='time_zone']");

	public By validationCandidateTimeZone = By
			.xpath("//span[@class='help-block'][contains(text(),'Candidate timezone')]");

	public By yearsOfExperience = By.xpath("//input[@placeholder='Years Of Experience'][@name='experience']");

	public By validationYearsOfExperience = By
			.xpath("//p[@class='help-block'][contains(text(),'Years Of Experience')]");

	public By additionalDetails = By.xpath("//input[@placeholder='Additional Details'][@name='additional_details']");

	public By buttonAddCandidateAvailability = By
			.xpath("//button[@ng-click='addCandidate()'][contains(text(),'Add Candidate Availability')]");

	public By buttonCancel = By.xpath("//button[text()='Cancel'][@class='btn btn-default']");

	public By activeJobRow = By.xpath("//div[@class='panel panel-default']");

	public By candidateCount = By
			.xpath("//div[contains(@class, 'int-members job-candidates')]/h5/span[@class='badge']");

	public By activeJobRowActionButton = By
			.xpath("//ul[@class='list-group']/li[" + listitem() + "]/div[1]/div[4]/button[text()='Actions ']");

	public By availability = By.xpath(
			"//div[@class='col-md-1 col-sm-12 col-xs-12 total-interviews open']/ul[1]/li[2]/a[text()='Availability']");

	public By manualScheduleOn = By.xpath(
			"//div[@class='col-md-1 col-sm-12 col-xs-12 total-interviews open']/ul[1]/li[9]/a[text()='Manual Schedule On']");

	public By scheduuleInterview = By.xpath(
			"//div[@class='col-md-1 col-sm-12 col-xs-12 total-interviews open']/ul[1]/li[10]/a[text()='Schedule Interview']");
	
	public String ValidationMessage_FirstName = "First Name can't be blank.";
	
	public String ValidationMessage_LastName = "Last Name can't be blank.";
	
	public String ValidationMessage_EmailAddress = "Email can't be blank";
	
	public String ValidationMessage_PhoneNumber = "Phone number can't be blank.";
	
	public String ValidationMessage_UploadChooseFile = "Resume File can't be blank";
	
	public String ValidationMessage_CandidateReportDueBy = "Candidate report due date can't be blank";
	
	public String ValidationMessage_Time = "Candidate report due time can't be blank";
	
	public String ValidationMessage_CandidateCountry = "Candidate country can't be blank";
	
	public String ValidationMessage_CandidateTimeZone = "Candidate timezone can't be blank";
	
	public String ValidationMessage_YearsofExperience = "Years Of Experience can't be blank.";
	
	public String InvalidationMessage_CandidateReportDueBy = "Please enter valid date";

	@Override
	public int listitem() {
		stringCandidateCount = driver.findElement(candidateCount).getText();
		candidateCountValue = Integer.parseInt(stringCandidateCount);
		return listItemIndex = candidateCountValue + 3;
	}
}
