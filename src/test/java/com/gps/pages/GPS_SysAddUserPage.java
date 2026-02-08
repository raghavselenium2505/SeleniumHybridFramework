package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;


interface sysaddUserPage {
	public void userType(String locator,String userType, String passValue, String failValue) ;
	

}


public class GPS_SysAddUserPage extends TestBase implements sysaddUserPage{
	
public By buttonUpload = By.xpath("//label[text()='Upload ']");
	
	public By buttonRemove = By.xpath("//span[text()='Remove']");
	
	public By inputFirstName = By.id("firstName");
	
	public By inputLastName = By.id("lastName");
	
	public By inputPassword =By.xpath("//input[@id='password']");
			//By.id("password");
	
	public By inputEmailAddress = By.xpath("//input[@id='emailAddress']");
		//	By.id("emailAddress");
	
	public By inputPhoneNumber = By.xpath("//input[@id='phoneNumber']");
	//By.id("phoneNumber");
	
	public By inputExtension =By.xpath("//input[@id='extension']") ;
	//By.id("extension");
		
	public By dropdownUserRole = By.xpath("//span[text()='Select User Role']");
	
	public By dropdownValueAdmin = By.xpath("//li[@aria-label='Admin']");
	
	public By dropdwonValueUser = By.xpath("//li[@aria-label='User']");
	
	public By dropdownUserType = By.xpath("//span[text()='Select User Type']");
	
	public By inputAddAgency = By.xpath("//input[@id='addAgency']");
		
	public By buttonCancel = By.xpath("//span[text()='Cancel']");
	
	public By buttonSave = By.xpath("//span[text()='Save']");
	
	public By iconClose = By.xpath("(//span[@class='pi pi-times'])[2]");
	
	public By textAdduser = By.xpath("//h3[text()='Add User']");
	
	public By ValidationMessage_FirstName = By.xpath("//span[text()=' First Name is required. ']");
	
	public By ValidationMessage_LastName = By.xpath("//span[text()=' Last Name is required. ']");
	
	public By ValidationMessage_Password = By.xpath("//span[text()=' Password is required. ']");
	
	public By ValidationMessage_EmailAddress = By.xpath("//span[text()=' Email is required. ']");
	
	public By ValidationMessage_PhoneNumber = By.xpath("//span[text()=' Phone number is required. ']");
	
	public By ValidationMessage_UserRole = By.xpath("//span[text()=' User Role is required. ']");
	
	public By ValidationMessageforExistingEmail = By.xpath("//span[text()=' This email address is already in use. ']");
	
	public By ValidationMessageforInvalidEmail = By.xpath("//span[text()=' Enter valid email. ']");
	
	public By ValidationMessageforInvalidPhone = By.xpath("//span[text()=' Enter valid phone number. ']");
	
	public By ValidationMessageforPassword_Characters = By.xpath("//p[text()='Use atleast 8 characters and no more than 30 characters ']");
	
	public By ValidationMessageforPassword_UpperCase = By.xpath("//p[text()='Use atleast 1 upper case letter ']");
	
	public By ValidationMessageforPassword_LowerCase = By.xpath("//p[text()='Use atleast 1 lower case letter ']");
	
	public By ValidationMessageforPassword_Numeric = By.xpath("//p[text()='Use atleast 1 numeric letter ']");
	
	public By ValidationMessageforPassword_SpecialCharacters = By.xpath("//p[text()='Use atleast 1 special character ']");
	
	public By textUserType=By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");
	
	public By textDashboard = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Dashboard')]");
	
	public By textpipelineDashboard = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Pipelines Dashboard']");

	public By textPatientRoundingDashboard = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Patient Rounding Dashboard']");
	
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
	
	public By textSDOHSurvey = By.xpath("//span[@class='word-break-inherit'][normalize-space()='SDOH Survey']");
	
	public By textPatientRoundingSurvey = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Patient Rounding Survey' ]");
	
	public By textFormBuilder = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Form Builder']");
	
	public By textAppointments = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Appointments']");
	
	public By textProperties = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Properties']");
	
	public By textMemberManagement = By.xpath("(//span[@class='word-break-inherit'][normalize-space()='Member Management'])");
	
	public By textReports = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Reports')]");
	
	public By textReports_Pro = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Reports' ]");
	
	public By textReports_Elite = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Reports' ]");
	
	public By textSurveyReport = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Survey Reports' ]");

	public By textSettings = By.xpath("//p-header[@class='ng-star-inserted']//span[contains(text(),'Settings')]");
	
	public By textHospitalFloors = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Hospital Floors' ]");
	
	public By checkboxReadonly = By.xpath("//label[text()='Read only']");
	
	public By textLogs = By.xpath("(//span[@class='word-break-inherit'][normalize-space()='Logs'])");
	
	public By textAccountBilling = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Account Billing']");

	public By textTags = By.xpath("//span[@class='word-break-inherit'][normalize-space()='Tags']");


	@Override
	public void userType(String locator,String userType, String passValue, String failValue) {

	actionclick(driver.findElement(By.xpath("//"+locator+"[text()='"+userType+"']")), passValue, failValue);
	
	}


}
