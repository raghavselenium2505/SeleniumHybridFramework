package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface userManagement {
	
	
	
	
	

}

public class GPS_UserManagementPage extends TestBase implements userManagement {

	public By buttonAddUser = By.xpath("//span[text()='Add User']");

	public By inputSearch = By.xpath("//input[@aria-label='search button']");

	public By iconEdit = By.xpath("//i[@aria-label='edit icon']");

	public By iconDelete = By.xpath("//i[@aria-label='delete icon']");

	public By headerName = By.xpath("//th[contains(@class, 'user-name')]");

	public By headerEmail = By.xpath("//th[contains(@class, 'email-chng')]");

	public By headerPhone = By.xpath("//th[contains(@class, 'phone-chng')]");

	public By headerUserRole = By.xpath("//th[text()='User Role']");

	public By textNoMatchFound = By.xpath("(//h3[normalize-space()='No Match Found.'])[1]");

	public By textDashboard = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Dashboard')]");
	
	public By textpipelineDashboard = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Pipelines Dashboard']");

	public By textCommunicationPanel = By
			.xpath("//span[@class='word-break-inherit'][normalize-space()='Communication Panel']");

	public By textpipeline = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Pipelines']");

	public By textLeadQualification = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Lead Qualification']");
	
	public By textProposalANDTemplate = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Proposal & Templates')]");
	
	public By textProposals = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Proposals']");
	
	public By textProposalTemplates = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Proposal Templates']");
	
	public By textCollaboration = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Collaboration')]");
	
	public By checkboxSms = By.xpath("//span[@class='word-break-inherit'][normalize-space()='SMS']");
	
	public By checkBoxEmail = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Email']");
	
	public By textContacts = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Contacts']");
	
	public By textAutomation = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Automation')]");
	
	public By textSequence = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Sequences']");
	
	public By textSequenceLauncher = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Sequence Launcher']");
	
	public By textRules = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Rules']");
	
	public By textMessagingTemplates = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Messaging Templates')]");
	
	public By textEmailHTMLBuilder = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Email HTML Builder']");
	
	public By textMessageTemplates = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Message Templates']");
	
	public By textCallBackLinks = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Call Back Links']");
	
	public By textFormsSurvey = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Forms & Surveys')]");
	
	public By textSurveyBuilder = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Survey Builder')]");
	
	public By textStandardSurvey = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Standard Survey']");
	
	public By textFormBuilder = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Form Builder']");
	
	public By textAppointments = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Appointments']");
	
	public By textProperties = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Properties']");
	
	public By textMemberManagement = By.xpath("(//span[@class='word-break-inherit'][normalize-space()='Member Management'])");
	
	public By textReports = By.xpath("(//span[@class='word-break-inherit'][normalize-space()='Reports'])");
	
	public By textSurveyReport = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Survey Reports' ]");

	public By textSettings = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Settings')]");
	
	public By checkboxReadonly = By.xpath("//label[text()='Read only']");
	
	public By textLogs = By.xpath("(//span[@class='word-break-inherit'][normalize-space()='Logs'])");
	
	public By textAccountBilling = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Account Billing']");

	public By textTags = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Tags']");

	public By titleUserPermissions = By.xpath("//p[contains(text(),'User Permissions')]");
	
	public By errorFirstName=By.xpath("//input[@id='firstName']/../div");
	
	public By errorLastName=By.xpath("//input[@id='lastName']/../div");
	
}
