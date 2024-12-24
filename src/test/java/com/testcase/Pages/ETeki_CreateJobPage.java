package com.testcase.Pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import com.base.web.TestBase;

public class ETeki_CreateJobPage extends TestBase {

	public By hiringCompanyName = By.xpath("//input[@name='company_name']");

	public By validationCompanyName = By.xpath("//span[@class='help-block'][contains(text(), 'Company name')]");

	public By jobTitle = By.xpath("//input[@name='title']");

	public By validationJobTitle = By.xpath("//span[@class='help-block'][contains(text(), 'Title')]");

	public By jobDescription = By.xpath("//textarea[@name='description']");

	public By validationJobDescription = By
			.xpath("//span[@class='help-block'][contains(text(), 'Please upload file or write the description')]");
	public By successMessage = By.xpath("//p[text()='Job created successfully.']");

	public By skillsToBeAssessed = By.xpath("//input[@placeholder='Select Required Skills (example: java, c, etc)']");

	public By validationSkillsToBeAssessed = By.xpath("//div[@class='help-block'][contains(text(), 'Required skill')]");

	public By yearsOfExperience = By.xpath("//input[@name='expr']");

	public By validationYearsOfExperience = By.xpath("//span[@class='help-block'][contains(text(), 'Experience')]");

	public By jobClosingDate = By.xpath("//input[@placeholder='Job Closing Date']");

	public By validationJobClosingDate = By.xpath("//span[@class='help-block'][contains(text(), 'Job closing date')]");

	public By validationInvalidJobClosingDate = By
			.xpath("//span[@class='help-block'][contains(text(), 'Please enter valid date')]");

	public By areasOfExpertise = By.id("expertise_category");

	public By validationAreasOfExpertise = By
			.xpath("//span[@class='help-block'][contains(text(), 'Please select areas of expertise')]");

	public By interviewMode = By.xpath("//input[@name='interviewmode'][@id='inlineCheckbox2']");

	public By prefferedTechnicalInterviewingService = By.xpath("//input[@name='eteki_managed'][@id='inlineCheckbox1']");

	public By projectedNumberOfCandidates = By.id("number_of_prospects");

	public By validationProjectedNumberOfCandidates = By
			.xpath("//span[@class='help-block'][contains(text(), 'Projected number of candidates')]");

	public By clientSelectedInterviewers = By.xpath("//input[@name='selected_interviewers'][@id='inlineCheckbox1']");

	public By validationClientSelectedInterviewers = By
			.xpath("//span[@class='help-block'][contains(text(), 'Client selected interviewers')]");

	public By saveButton = By.xpath("//button[@ng-click='jobCreate()']");

	public By cancelButton = By.xpath("//button[@ng-click='$dismiss()'][text()='Cancel']");

	public By okButton = By.xpath("//button[@class='confirm']");

	public By createJobTitle = By.xpath("//h4[text()='Create Job ']");

	public String ValidationMessage_CompanyName = "Company name can't be blank";

	public String ValidationMessage_JobTitle = "Title can't be blank";

	public String ValidationMessage_JobDescription = "Please upload file or write the description";

	public String ValidationMessage_SkillsToBeAssessed = "Required skill set can't be blank";

	public String ValidationMessage_YearsOfExperience = "Experience can't be blank";

	public String ValidationMessage_JobClosingDate = "Job closing date can't be blank";
	
	public String ValidationMessage_InvalidJobClosingDate = "Please enter valid date";

	public String ValidationMessage_AreasOfExpertise = "Please select areas of expertise";

	public String ValidationMessage_ProjectedNumberOfCandidates = "Projected number of candidates can't be blank";

	public String ValidationMessage_ClientSelectedInterviewers = "Client selected interviewers can't be blank";

}
