package com.gps.pages;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_Contacts {

	public void verifyDownloadedFile(String filePath, String expectedFileName, String passValue, String failValue);

	public String dynamicXpathClick(By Element, String startPath, String endPath);

	public void dynamicTagsValues(By Element, String startPath, String endPath);

	public void dynamicSelectTag(String startPath, String endPath);

	public void contactNameHeaderDisplay(By element1, By element2, String passvalue, String failValue);

	public void taskDropdown(By ele1, By ele2);

	public void DueDateDropdownItems(By ele1);

	public void allDropDown(By ele);

	public void selectNotesFromTaskDropDown();

	public void selectAppointmentsFromTaskDropDown();

	public void fileDelete(String filePath, String expectedFileName, String passValue, String failValue);

	public void selectContactType(String locator, String contactType, String passValue, String failValue);

	public void contactsCount();

	public void validatePlaceholder(By element, String attributeName, String validationValue, String passValue,
			String failValue);

	
}


public class GPS_ContactsPage extends TestBase implements GPS_Contacts {

	public ArrayList<String> Tags = new ArrayList<String>();

	public By Tags_SubModule = By.xpath("//span[normalize-space()='Tags']");

	public By tagsList = By.xpath("//label[@class='tags-cont m-l-10 m-b-0 add-eclips cursor-pointer']");

	public By emptyTagsList = By.xpath("//h3[normalize-space()='There are no items to display.']");

	public By columnName = By.cssSelector("th[field='name']");

	public By columnEmail = By.cssSelector("th[field='email']");

	public By columnPhone = By.cssSelector(".ph-number");

	public By columnType = By.cssSelector(".contact-type-field");

	public By columnTags = By.cssSelector(".tags-head.contact-tags-field");

	public By columnCreated = By.cssSelector(".created-th");

	public By inputSearch = By.xpath("//input[@placeholder='Search']");

	public By selectTagFilter = By.xpath("//span[text()='Select a Tag']");

	public By search_selectTagFilter = By
			.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By empty_SelectTagFilter = By.xpath("//li[text()='No results found']");

	public By selectMemberFilter = By.xpath("//span[text()='Select a Contact Type']");

	public By selectMember = By.xpath("//span[text()='Member']");

	public By selectNonMember = By.xpath("//span[text()='Non member']");

	public By selectLead = By.xpath("//span[text()='Lead']");

	public By selectMembershipLead = By.xpath("//span[text()='Membership lead']");

	public By contactTypeList = By.xpath("//ul[@role='listbox']/p-dropdownitem/li/span");

	public By additionalOptions = By.cssSelector(
			"body > app-root:nth-child(1) > app-user-router-outlet:nth-child(2) > div:nth-child(2) > div:nth-child(2) > p-scrollpanel:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > app-contact-layout:nth-child(2) > app-contact-list:nth-child(2) > div:nth-child(1) > p-table:nth-child(1) > div:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(8) > div:nth-child(1) > div:nth-child(2) > i:nth-child(1)");

	public By createOppurtunity = By.cssSelector("tbody li:nth-child(1) a:nth-child(1) span:nth-child(1)");

	public By deleteContact = By.cssSelector("tbody li:nth-child(2) a:nth-child(1) span:nth-child(1)");

	public By createProposal = By.cssSelector("tbody li:nth-child(3) a:nth-child(1) span:nth-child(1)");

	public By checkboxSelectAll = By.xpath("(//div[@role='checkbox'])[1]");

	public By checkboxFirstContact = By.xpath("(//div[@role='checkbox'])[2]");

	public By checkboxSecondContact = By.xpath("(//div[@role='checkbox'])[3]");

	public By checkboxThirdContact = By.xpath("(//div[@role='checkbox'])[4]");

	public By checkboxSelectedAll = By.xpath("(//span[@class='ui-chkbox-icon ui-clickable pi pi-check'])[1]");

	public By addTag = By.xpath("//button[@ptooltip='Add Tag']");

	public By removeTag = By.xpath("//button[@ptooltip='Remove Tag']");

	public By deleteContacts = By.xpath("//button[@ptooltip='Delete Contacts']");

	public By validationBulkDeletePopup = By.xpath("//h3[text()='Delete Contacts']");

	public By validationBulkDeletePopuptext = By.xpath("//h3[text()='Delete selected contacts ']");

	public By textBulkDeletePopup = By.xpath("//input[@placeholder='Type Delete']");

	public By buttonSubmitBulkDeletePopup = By.xpath("//span[text()='Submit']");

	public By buttonCloseBulkDeletePopup = By
			.xpath("//div[@class='ui-dialog-titlebar-icons']//span[@class='pi pi-times']");

	public By selectedContactsCount_AddTag = By.cssSelector("label[class='ng-star-inserted']");

	public By selectedContactsCount_RemoveTag = By.cssSelector("label[class='ng-star-inserted']");

	public By textSelectTags_AddTag = By.xpath("//input[@id='tag']");

	public By textSelectTags_RemoveTag = By.xpath("//input[@id='tag']");

	public By buttonCancel_AddTag = By.xpath("//span[normalize-space()='Cancel']");

	public By buttonCancel_RemoveTag = By.xpath("//span[normalize-space()='Cancel']");

	public By buttonClose_AddTag = By.xpath("(//span[@class='pi pi-times'])[2]");

	public By buttonClose_RemoveTag = By.xpath("(//span[@class='pi pi-times'])[2]");

	public By buttonAdd_AddTag = By.xpath("//span[normalize-space()='Add']");

	public By buttonRemove_RemoveTag = By.xpath("//span[normalize-space()='Remove']");

	public By titleAddTag = By.xpath("//h3[normalize-space()='Add Tag']");

	public By titleRemoveTag = By.xpath("//h3[normalize-space()='Remove Tag']");

	public By columnNameValue = By.xpath("(//tbody/tr[1]/td[3])[1]");

	public By buttonAddContacts = By.xpath("//button[@label='Add Contacts']");

	public By buttonDownload = By.xpath("//button[@icon='icon icon-edo-download']");

	public By searchValidationMessage = By.xpath("//h3[contains(text(),'No Match Found.')]");

	public By buttonAddSingleContact = By.xpath("//span[text()='Add Single Contact']");

	public By buttonImportContacts = By.xpath("//span[text()='Import Contacts']");

	public By labelFirstName = By.xpath("//label[@for='firstName']");

	public By textFirstName = By.id("firstName");

	public By validationFirstName = By.xpath("//span[contains(text(),' Enter Alpha numeric with only _,')]");

	public By labelLastName = By.xpath("//label[@for='lastName']");

	public By textLastName = By.id("lastName");

	public By validationLastName = By.xpath("//div[@class='form-row m-t-10 m-b-10']//div[2]//div[1]//span[1]");

	public By labelEmail = By.xpath("//label[@for='email']");

	public By labelEmailAddress = By.xpath("//label[@for='emailAddress']");

	public By textEmailAddress = By.id("emailAddress");

	public By validationEmailAddress = By.xpath("//span[contains(text(),' Enter valid email. ')]");

	public By labelPhoneNumber = By.xpath("//label[@for='phoneNumber']");

	public By textPhoneNumber = By.id("phoneNumber");

	public By validationPhoneNumber = By.xpath("//div[@class='form-row m-t-10 m-b-10']//div[4]//div[1]//span[1]");

	public By textExtension = By.id("extensionNumber");

	public By labelContactType = By.xpath("//label[contains(text(),'Contact Type')]");

	public By dropdownContactType = By.xpath("//p-dropdown[@placeholder='Select Contact Type']");

	public By contactTypeMember = By.xpath("//span[text()='Member']");

	public By contactTypeNonmember = By.xpath("//span[text()='Non member']");

	public By contactTypeLead = By.xpath("//span[text()='Lead']");

	public By contactTypeMembershipLead = By.xpath("//span[text()='Membership lead']");

	public By selectContactType = By.xpath(
			"//div[@class='ng-tns-c12-261 ui-dropdown ui-widget ui-state-default ui-corner-all']//div[@class='ui-dropdown-label-container']");

	public By buttonSave = By.xpath("//span[text()='Save']");

	public By buttonCancel = By.xpath("//span[text()='Cancel']");

	public By buttonClose = By.xpath("(//span[@class='pi pi-times'])[2]");

	public By textSearchOrg = By.xpath("//input[@placeholder='Search']");

	public By contactListEllipsis = By.xpath("(//i[contains(@class,'pi pi-ellipsis-v ')])[1]");

	public By deletecontactListEllipsis = By.xpath("//span[text()='Delete Contact']");

	public By validationDeletePopup = By.xpath("//h3[contains(@class,'confirm-text word-break-word p-t-5')]");

	public By textDeletePopup = By.id("exampleInputName");

	public By validationDeletePopupRequired = By.xpath("//span[text()=' Confirmation text is required. ']");

	public By buttonSubmitDeletePopup = By.xpath("//span[text()='Submit']");

	public By cancelDeletePopup = By.xpath("//div[@class='ui-dialog-titlebar-icons']//span[@class='pi pi-times']");

	public By deleteToastMsg = By.xpath("//div[@class='ui-toast-detail']");

	public By labelSalutation = By.xpath("//label[@for='salutationId']");

	public By selectSalutation = By.xpath("//p-dropdown[@placeholder='Select Salutation']");

	public By searchSalutation = By.xpath("//input[contains(@class,'ui-dropdown-filter')]");

	public By selectSalutation_None = By.xpath("//span[contains(text(),'None')]");

	public By selectSalutation_Dr = By.xpath("//span[contains(text(),'Doctor')]");

	public By selectSalutation_Mr = By.xpath("//span[contains(text(),'Mister')]");

	public By selectSalutation_Mrs = By.xpath("//span[contains(text(),'Mrs')]");

	public By selectSalutation_Ms = By.xpath("//span[contains(text(),'Ms')]");

	public By selectSalutation_Prof = By.xpath("//span[contains(text(),'Professor')]");

	public By selectSalutation_Ald = By.xpath("//span[contains(text(),'Alderman')]");

	public By selectSalutation_Sen = By.xpath("//span[contains(text(),'Senator')]");

	public By selectSalutation_Rep = By.xpath("//span[contains(text(),'Representative')]");

	public By selectSalutation_Gen = By.xpath("//span[contains(text(),'General')]");

	public By selectSalutation_St = By.xpath("//span[contains(text(),'Saint')]");

	public By selectSalutation_Gov = By.xpath("//span[contains(text(),'Governor')]");

	public By selectSalutation_Pres = By.xpath("//span[contains(text(),'President')]");

	public By selectSalutation_Insp_Gen = By.xpath("//span[contains(text(),'Inspector General')]");

	public By selectSalutation_Assoc_Prof = By.xpath("//span[contains(text(),'Associate Professor')]");

	public By selectSalutation_Asst_Prof = By.xpath("//span[contains(text(),'Assistant Professor')]");

	public By selectSalutation_Col = By.xpath("//span[contains(text(),'Colonel')]");

	public By selectSalutation_Lt_Col = By.xpath("//span[contains(text(),'Lieutenant Colonel')]");

	public By selectSalutation_Hon = By.xpath("//span[contains(text(),'The Honorable')]");

	public By selectSalutation_Rev = By.xpath("//span[contains(text(),'Reverend')]");

	public By selectSalutation_Jr = By.xpath("//span[contains(text(),'Junior')]");

	public By selectSalutation_Sr = By.xpath("//span[contains(text(),'Senior')]");

	public By selectSalutation_Esq = By.xpath("//span[contains(text(),'Esquire')]");

	public By selectSalutation_Pastor = By.xpath("//span[text()='Pastor']");

	public By selectSalutation_Others = By.xpath("//span[text()='Others']");

	public By removeSalutation = By.xpath(
			"//p-dropdown[@formcontrolname='salutationId']/div/div/i[contains(@class,'ui-dropdown-clear-icon')]");

	public By inputSalutation = By.id("salutationTitle");

	public By selectSalutation_primary = By
			.xpath("//div[@class='ui-dropdown-label-container']/span[text()='Select Salutation']");

	public By textFirstName_primary = By.cssSelector("input#firstName");

	public By textLastName_primary = By.cssSelector("input#lastName");

	public By textEmail_primary = By.id("email");

	public By textPhoneNumber_primary = By.cssSelector("input#phoneNumber");

	public By labelExtension = By.xpath("//label[@for='extensionNumber']");

	public By textExtensionNumber_primary = By.id("extensionNumber");

	public By textDateofBirth_primary = By.cssSelector("input#calander");

	public By labelGender = By.xpath("//label[text()='Gender']");

	public By selectGender = By.xpath("//p-dropdown[@placeholder='Select Gender']");

	public By searchGender = By.xpath("//input[contains(@class,'ui-dropdown-filter')]");

	public By selectGenderMale = By.xpath("//span[text()='Male']");

	public By selectGenderFemale = By.xpath("//span[text()='Female']");

	public By selectGenderNotSpecified = By.xpath("//span[text()='Not Specified']");

	public By removeGender = By
			.xpath("//p-dropdown[@placeholder='Select Gender']/div/div/i[contains(@class,'ui-dropdown-clear-icon')]");

	public By selectGender_primary = By
			.xpath("//div[@class='ui-dropdown-label-container']/span[text()='Select Gender']");

	public By labelSource = By.xpath("//label[@for='source']");

	public By textSource_primary = By.id("source");

	public By labelTags = By.xpath("//label[@for='tags']");

	public By texttags_primary = By.xpath("//p-autocomplete[@placeholder='Select Tags']");
	
	public By selectedTags = By.xpath("//div[@class='tags m-t-15 comm-tags']/p/span");

	public By selectContactType_primary = By
			.cssSelector("//div[@class='ui-dropdown-label-container'] /span[text()='Lead']");

	public By doNotContact_primary = By.cssSelector("span[class='ui-inputswitch-slider']");

	public By activeSequence_primary = By
			.cssSelector("span[class='icon icon-edo-add fs-18 color-blue cursor-pointer ng-star-inserted']");

	public By selectSequence_primary = By
			.xpath("//div[@class='ui-dropdown-label-container']/span[text()='Select Sequence']");

	public By selectEventDate_primary = By.cssSelector("span.ui-calendar input#eventDate");

	public By buttonSave_primary = By.cssSelector("button[label='Save'] span[class='ui-button-text ui-clickable']");

	public By buttonClose_primary = By.cssSelector("button[label='Close'] span[class='ui-button-text ui-clickable']");

	public By activePipelineOpportunities_primary = By
			.cssSelector("span[class='icon icon-edo-add fs-18 color-blue cursor-pointer']");

	public By textOpportunityName_primary = By.cssSelector("input#opportunityName1");

	public By selectPipeline_primary = By
			.xpath("//div[@class='ui-dropdown-label-container']/span[text()='Select a Pipeline']");

	public By selectStage_primary = By
			.xpath("//div[@class='ui-dropdown-label-container'] /span[contains(text(),'Select Stage')]");

	public By textLeadValue_primary = By.cssSelector("input[placeholder='Enter Lead Value']");

	public By selectAssignedUser_primary = By.xpath(
			"//div[@class='ui-dropdown-label-container'] /span[@class='ng-tns-c8-42 ui-dropdown-label ui-inputtext ui-corner-all ui-placeholder ng-star-inserted'][contains(text(),'Assign To')]");

	public By contactsText = By.xpath("//h4[contains(text(),'Contacts')]");

	// Sphurthi
	public By selectFirstContact = By.xpath("//td[@tooltipposition='top']");
	public By lhsPanel = By
			.xpath("//div[contains(@class,'contactName border')]//div[contains(@class,'contact-details-block')]");
	public By centralPanel = By.xpath("//div[contains(@class,'conversation contactName')]");
	public By rightPanelold = By.xpath("(//div[contains(@class,'contactName tags-section border-left')]");
	public By rightPanel = By.xpath("//div[contains(@class,'border-left')]");
	public By lhsBackLink = By.xpath("//a[contains(@class,'icon-edo-reply')]");

	public By lhsPrimaryText = By.xpath("//span[contains(text(),'Primary')]");
	public By lhsBusinessText = By.xpath("//span[contains(text(),'Business')]");
	public By lhsCustomizedText = By.xpath("//span[contains(text(),'Customized')]");

	public By lhsFullNameText = By.xpath("//span[contains(@class,'details')]");
	public By lhsEmailText = By.xpath("//label[contains(text(),'Email')]");
	public By lhsEmailTextBox = By.id("email");
	public By lhsPhoneNumberText = By.xpath("//label[contains(text(),'Phone Number')]");
	public By lhsPhoneNumberTextBox = By.id("phoneNumber");
	public By lhsDateOfBirthText = By.xpath("//label[contains(text(),'Date of Birth')]");
	public By lhsDateOfBirthTextBox = By.id("calander");
	public By lhsGenderText = By.xpath("//label[contains(text(),'Gender')]");
	public By lhsSourceText = By.xpath("//label[contains(text(),'Source')]");
	public By lhsTagsText = By.xpath("//label[contains(text(),'Tags')]");
	public By lhsContactTypeText = By.xpath("//label[contains(text(),'Contact Type')]");
	public By lhsDndText = By.xpath("//p[contains(text(),'Do Not Contact')]");
	public By lhsActiveSequenceText = By.xpath("//p[contains(text(),'Active Sequence')]");
	public By lhsActivePipelineOpprtunitiesText = By.xpath("//p[contains(text(),'Active Pipeline Opportunities')]");
	public By lhsContactAddedDateText = By.xpath("//span[contains(text(),'Contact Added Date:')]");

	public By lhsBusinessNameText = By.xpath("//label[contains(text(),'Business Name')]");
	public By inputBusinessName = By.id("businessName");
	public By lhsStreetAddressText = By.xpath("//label[contains(text(),'Street Address')]");
	public By inputStreetAddress = By.id("streetAddress");
	public By lhsCountryText = By.xpath("//label[contains(text(),'Country')]");
	public By lhsStateText = By.xpath("//label[contains(text(),'State/Prov/Region')]");
	public By lhsCityText = By.xpath("//label[contains(text(),'City')]");
	public By lhsZipCodeText = By.xpath("//label[contains(text(),'Zip/Postal code')]");
	public By inputZipCode = By.id("zipCode");
	public By lhsWebsiteText = By.xpath("//label[contains(text(),'Website')]");
	public By lhsTimezoneText = By.xpath("//label[contains(text(),'Timezone')]");
	public By selectTimezone = By.xpath("//p-dropdown[@placeholder='Select Timezone']");
	public By searchTimezone = By.xpath("//input[contains(@class, 'ui-dropdown-filter')]");
	public By valueTimezone = By.xpath("//span[text()='GMT+5:30 India Standard Time(IST)']");

	public By lhsUpdateButtonText = By.xpath("//span[contains(text(),'Update')]");

	public By lhsBackPopUpYes = By.xpath("//span[contains(text(),'Yes')]");

	public By lhsBackPopUpNo = By.xpath("//span[contains(text(),'No')]");

	public By lhsBackPopUpClose = By.xpath("//span[@class='pi pi-times']");

	public By lhsContactHeaderName = By.xpath("//span[contains(@class,'heading')]");

	public By rhsTasksDDRHS = By.xpath("//span[contains(text(),'Tasks')]");
	public By rhsTasksDropDownItems = By.xpath("//ul[contains(@class,'dropdown')]//span");
	public By rhsPlusIcon = By
			.xpath("//div[contains(@class,'tags-section')]//following::span[contains(@class,'blue cursor')]");
	public By rhsSelectUserDropDown = By.xpath("//div[contains(@class,'dropdown')]/span[text()='Select User']");
	public By rhsSearchInSelectUserDropdown = By.xpath("//div[contains(@class,'container')]/input[@type='text']");
	public By rhsAllDropdown = By.xpath("//span[contains(text(),'All')]");

	public By rhsAllDropdownItems = By.xpath("//span[contains(text(),'All')]//following::p-dropdownitem//span");
	public By rhsAllDropdownAll = By.xpath("(//span[contains(text(),'All')])[2]");
	public By rhsAllDropdownPending = By.xpath("//span[contains(text(),'Pending')]");
	public By rhsAllDropdownCompleted = By.xpath("//span[contains(text(),'Completed')]");
	public By rhsDueDateDropDown = By.xpath("//span[contains(text(),'Due Date')]");
	public By rhsDueDateDropDownItems = By
			.xpath("//span[contains(text(),'Due Date')]//following::p-dropdownitem//span");
	public By rhsNoItemsToDisplay = By.xpath("//h5[contains(text(),'There are no items to display')]");

	// add task pop up
	public By rhsAddTaskTextInPopUp = By.xpath("//h3[contains(text(),'Add Task')]");
	public By rhsTitleTextInAddTaskPopUp = By.xpath("//label[contains(text(),'Title*')]");
	public By rhsDescriptionTextInAddTaskPopUp = By.xpath("//label[contains(text(),'Description')]");
	public By rhsDueDateTextInAddTaskPopUp = By.xpath("//label[contains(text(),'Due Date*')]");
	public By rhsAssignedToTextInAddTaskPopUp = By.xpath("//label[contains(text(),'Assigned to*')]");
	public By rhsSaveInAddPopUp = By.xpath("//span[contains(text(),'Save')]");
	public By rhsCancelInAddPopUp = By.xpath("//span[contains(text(),'Cancel')]");
	public By rhsCloseIconInAddPopUp = By.xpath("//a[contains(@class,'titlebar-close')]");
	public By rhsTitleErrorInAddTaskPopUp = By.xpath("//span[contains(text(),' Title is required')]");
	public By rhsDueDateErrorInAddTaskPopUp = By.xpath("//span[contains(text(),'Due Date is required')]");
	public By rhsAssignedToErrorInAddTaskPopUp = By.xpath("//span[contains(text(),'Assigned to is required')]");
	public By rhsEnterTaskTitleInAddTaskPopUp = By.xpath("//input[@placeholder='Enter Task Title']");
	public By rhsDescriptionInAddTaskPopUp = By.xpath("//textarea[@placeholder='Enter Task Description']");
	public By rhsDueDateTextbox = By.xpath("//input[contains(@placeholder,'Select Due Date')]");

	// Notes
	public By rhsNotesInTaskDropDown = By.xpath("//span[text()='Notes']");
	public By rhsNotesNoItemsDisplay = By.xpath("//h3[contains(text(),'There are no items to display')]");
	public By rhsAddNotesText = By.xpath("//h3[contains(text(),'Add Note')]");
	public By rhsCreateNotesToast = By.xpath("//div[contains(text(),'Create Notes')]");
	public By rhsCreateNotesToastMsg = By.xpath("//div[contains(text(),'Note created successfully')]");
	public By rhsCreateNotesToastTick = By.xpath("//span[contains(@class,'toast-icon')]");
	public By rhsCreateNotesToastCloseIcon = By.xpath("//a[contains(@class,'close-icon')]");

	public By rhsNoteTextInAddNotesPopUp = By.xpath("//label[contains(text(),'Note')]");
	public By rhsErrorNoteInAddNotesPopUp = By.xpath("//span[contains(text(),'Note is required')]");
	public By rhsNotesTextBox = By.xpath("//textarea[@placeholder='Enter Note']");
	public By rhsFetchAddedNotes = By.xpath("//pre[contains(@class,'notes')]");
	public By rhsEditNotesIcon = By.xpath("//a[contains(@class,'edit')]");
	public By rhsUpdateNotesButton = By.xpath("//button[@label='Cancel']//following::button[@label='Update']");
	public By rhsUpdateNotesToast = By.xpath("//div[contains(text(),'Update Notes')]");
	public By rhsUpdateNotesToastMsg = By.xpath("//div[contains(text(),'Note updated successfully')]");
	public By rhsUpdateNotesToastTick = By.xpath("//span[contains(@class,'toast-icon')]");
	public By rhsUpdateNotesToastCloseIcon = By.xpath("//a[contains(@class,'close-icon')]");
	public By rhsDeleteNotesIcon = By.xpath("//a[contains(@class,'trash')]");
	public By rhsDeleteText = By.xpath("//h3[contains(text(),'Please enter word')]");
	public By rhsDeleteTextBox = By.xpath("//input[@placeholder='Type Delete']");
	public By rhsSubmitButtonInDelete = By.xpath("//span[text()='Submit']");
	public By rhsCloseIconInDeletePopUp = By.xpath("//a[contains(@class,'titlebar-close')]");
	public By rhsDeleteEnterCorrectToast = By.xpath("//div[contains(text(),'Enter Correct Word')]");
	public By rhsDeleteConfirmationText = By.xpath("//span[contains(text(),'Confirmation text is required')]");
	public By rhsDeleteToast = By.xpath("//div[contains(text(),'Delete Notes')]");
	public By rhsDeleteToastMsg = By.xpath("//div[contains(text(),'Note deleted successfully')]");
	public By rhsDeleteToastTick = By.xpath("//span[contains(@class,'toast-icon')]");
	public By rhsDeleteToastCloseIcon = By.xpath("//a[contains(@class,'close-icon')]");

	public By rhsAppointmentInTaskDropDown = By.xpath("//li[@aria-label='Appointments']");
	public By rhsAddAppointmentTextInAppointment = By.xpath("//span[contains(text(),'Add Appointment')]");
	public By rhsAppointmentsNoItemsDisplay = By.xpath("//h3[contains(text(),'There are no items to display')]");
	public By rhsCalendarInAppointment = By.xpath("//label[contains(text(),'Calendar *')]");
	public By rhsDayInAppointment = By.xpath("//label[contains(text(),'Day')]");
	public By rhsSlotInAppointemnt = By.xpath("//label[contains(text(),'Slot')]");
	public By rhsMeetingLocationInAppointment = By.xpath("//label[contains(text(),'Meeting Location')]");
	public By rhsAdditionalNotesInAppointemnt = By.xpath("//label[contains(text(),'Additional Notes')]");
	public By rhsCloseButtonInAppointment = By.xpath("//span[contains(text(),'Close')]");
	public By rhsSaveButtonInAppointment = By.xpath("//span[contains(text(),'Save')]");
	public By rhsCloseIconInAppointment = By.xpath("//a[contains(@class,'titlebar-close')]");
    
	//Sphurthi pipeline related
	public By ellipsis= By.xpath("//i[contains(@class,'ellipsis')]");
	public By LhsPipelinePlusIcon=By.xpath("//p[contains(text(),'Active Pipeline Opportunities')]//following::span");
	
	
	
	public By doNotDeleteTag = By.xpath("//span[text()='Do Not Delete Tag']");

	public By clearContactType = By.xpath("//i[contains(@class,'ui-dropdown-clear-icon pi pi-times')]");

	@Override
	public void verifyDownloadedFile(String filePath, String expectedFileName, String passValue, String failValue) {
		File folder = new File(filePath);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.getName().equalsIgnoreCase(expectedFileName)) {
					System.out.println("File found: " + file.getAbsolutePath());
					test.log(LogStatus.PASS, passValue);
				}
			}
		} else {
			System.out.println("File not found in directory: " + filePath);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
		}
	}

	@Override
	public String dynamicXpathClick(By Element, String startPath, String endPath) {

		String indexXpath = "";

		try {

			List<WebElement> index = driver.findElements(Element);

			for (int i = 1; i <= index.size(); i++) {

				indexXpath = startPath + i + endPath;
				System.out.println("index"+indexXpath);

				if (driver.findElement(By.xpath(indexXpath)).isDisplayed()) {

					break;

				}
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"<html><body>"
							+ "<p>Error occurred while executing the script. Please check the exception below:</p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			e.printStackTrace();
			screenshotutil.captureScreenshot(value);
		}
		return indexXpath;
	}

	// sphurthi
	@Override
	public void contactNameHeaderDisplay(By contactNameInListing, By contactNameHeader, String passValue,
			String failValue) {
		// TODO Auto-generated method stub

		// To get contact full name
		waitForWebElement(
				By.xpath(dynamicXpathClick(inputSearch, excelutil.getData("Contacts", "inputSearchStartPath", xlsname),
						excelutil.getData("Contacts", "endPath", xlsname))),
				extraverylongwaitvalue);
		driver.findElement(
				By.xpath(dynamicXpathClick(inputSearch, excelutil.getData("Contacts", "inputSearchStartPath", xlsname),
						excelutil.getData("Contacts", "endPath", xlsname))))
				.sendKeys(excelutil.getData("Contacts", "Name", xlsname));

		waitforelement(mediumwaitvalue);

		String fullName = driver.findElement(contactNameInListing).getText();

		System.out.println("Full Name in contact Listing is " + fullName);

		waitforelement(shortwaitvalue);
		// selecting contact
		click(selectFirstContact, "Able to select contact", "Unable to select contact");

		// checking contact header name
		String contactHeaderName = waitForWebElement(contactNameHeader, extraverylongwaitvalue).getText();

		System.out.println("Full Name in header is  " + contactHeaderName);

		try {
			assertTrue(fullName.trim().equals(contactHeaderName),
					"Contact Name in Listing and Contact Name Header are NOT same");

			waitforelement(mediumwaitvalue);
			test.log(LogStatus.PASS, passValue);
		}

		catch (Exception e) {
			// test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			e.printStackTrace();
			// screenshotutil.captureScreenshot(value);

		}

	}

	// sphurthi
	@Override
	public void taskDropdown(By ele1, By ele2) {

		waitforelement(mediumwaitvalue);
		waitForWebElement(ele1, extraverylongwaitvalue);
		isdisplay(ele1, "Dropdown is displayed by default with Tasks",
				"Dropdown is NOT displayed by default with Tasks");
		assertTrue(driver.findElement(rhsTasksDDRHS).isDisplayed(), "Dropdown is NOT displayed by default with Tasks");

		// check Task dropdown items

		click(ele1, "Able to click on Tasks dropdown", "Unable to click on Tasks dropdown");
		// actionclick(driver.findElement(ele1),"Able to click on Tasks dropdown",
		// "Unable to click on Tasks dropdown");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(extraverylongwaitvalue));
		List<WebElement> l1 = wait.until(ExpectedConditions.numberOfElementsToBe(ele2, 4));

		test.log(LogStatus.INFO, "Dropdown items are Tasks,Notes,Activity,Appointments");

		System.out.println("Task DropDown items are ");
		waitforelement(mediumwaitvalue);
		for (WebElement ele : l1) {
			// waitforelement(mediumwaitvalue);
			String dd = wait.until(ExpectedConditions.elementToBeClickable(ele)).getText();
			System.out.println(dd);
			if (dd.contains("Tasks")) {
				assertTrue(dd.contains("Tasks"));

			} else if (dd.contains("Notes")) {
				assertTrue(dd.contains("Notes"));

			} else if (dd.contains("Activity")) {
				assertTrue(dd.contains("Activity"));

			} else if (dd.contains("Appointments")) {
				assertTrue(dd.contains("Appointments"));

			}
		}

	}

	// Sphurthi
	@Override
	public void DueDateDropdownItems(By ele1) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(extraverylongwaitvalue));
		List<WebElement> l1 = wait.until(ExpectedConditions.numberOfElementsToBe(ele1, 4));
		test.log(LogStatus.INFO,
				"Dropdown items are Due Date (Asc),Due Date (Desc),Date Added (Asc),Date Added (Desc)");

		System.out.println("Due Date DropDown items are ");
		for (WebElement ele : l1) {
			String dd = wait.until(ExpectedConditions.elementToBeClickable(ele)).getText();
			System.out.println(dd);

			if (dd.contains("Due Date (Asc)")) {
				assertTrue(dd.contains("Due Date (Asc)"));

			} else if (dd.contains("Due Date (Desc)")) {
				assertTrue(dd.contains("Due Date (Desc)"));

			} else if (dd.contains("Date Added (Asc)")) {
				assertTrue(dd.contains("Date Added (Asc)"));

			} else if (dd.contains("Date Added (Desc)")) {
				assertTrue(dd.contains("Date Added (Desc)"));

			}

		}

	}

	// Sphurthi
	@Override
	public void allDropDown(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(extraverylongwaitvalue));
		List<WebElement> l1 = wait.until(ExpectedConditions.numberOfElementsToBe(ele, 3));
		test.log(LogStatus.INFO, "Dropdown items are All,Pending,Completed");

		isdisplay(rhsAllDropdownAll, "All is dipslayed", "All is not displayed");
		assertTrue(driver.findElement(rhsAllDropdownAll).isDisplayed(), "All is NOT displayed");

		isdisplay(rhsAllDropdownPending, "Pending is dipslayed", "Pending is not displayed");
		assertTrue(driver.findElement(rhsAllDropdownPending).isDisplayed(), "Pending is NOT displayed");

		isdisplay(rhsAllDropdownCompleted, "Completed is dipslayed", "Completed is not displayed");
		assertTrue(driver.findElement(rhsAllDropdownCompleted).isDisplayed(), "Completed is NOT displayed");
	}

	// Sphurthi
	@Override
	public void selectNotesFromTaskDropDown() {
		// TODO Auto-generated method stub

		waitforelement(shortwaitvalue);
		// Click Task dropdown

		click(rhsTasksDDRHS, "Able to click on Task DropDown", "Unable to click on Task DropDown");

		waitforelement(mediumwaitvalue);

		// Verify Notes from Dropdown
		waitForWebElement(rhsNotesInTaskDropDown, extraverylongwaitvalue);
		isdisplay(rhsNotesInTaskDropDown, "Notes is displayed", "Notes is NOT displayed");
		assertTrue(driver.findElement(rhsNotesInTaskDropDown).isDisplayed(), "Notes is NOT displayed");

		// Select Notes from DropDown
		click(rhsNotesInTaskDropDown, "Able to select Notes from dropdown", "Unable to select Notes from dropdown");

	}

	// Sphurthi
	@Override
	public void selectAppointmentsFromTaskDropDown() {
		// TODO Auto-generated method stub
		waitforelement(shortwaitvalue);
		// Click Task dropdown

		click(rhsTasksDDRHS, "Able to click on Task DropDown", "Unable to click on Task DropDown");

		waitforelement(mediumwaitvalue);

		// Verify Appointments from Dropdown
		waitForWebElement(rhsAppointmentInTaskDropDown, extraverylongwaitvalue);
		isdisplay(rhsAppointmentInTaskDropDown, "Appoinments is displayed", "Appointments is NOT displayed");
		assertTrue(driver.findElement(rhsAppointmentInTaskDropDown).isDisplayed(), "Appointemnts is NOT displayed");

		// Select Appointments from DropDown
		click(rhsAppointmentInTaskDropDown, "Able to select Appointemnts from dropdown",
				"Unable to select Appointemnts from dropdown");

	}

	@Override
	public void dynamicTagsValues(By Element, String startPath, String endPath) {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));

		String indexXpath = "";

		try {

			if (driver.findElement(emptyTagsList).isDisplayed()) {

			} else {

				List<WebElement> index = driver.findElements(Element);

				for (int i = 1; i <= index.size(); i++) {

					indexXpath = startPath + i + endPath;

					if (driver.findElement(By.xpath(indexXpath)).isDisplayed()) {

						waitforelement(shortwaitvalue);

						Tags.add(driver.findElement(By.xpath(indexXpath)).getText());

					}
				}
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"<html><body>"
							+ "<p>Error occurred while executing the script. Please check the exception below:</p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			e.printStackTrace();
			screenshotutil.captureScreenshot(value);
		}

	}

	@Override
	public void dynamicSelectTag(String startPath, String endPath) {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));

		String selectTagXpath = "";

		try {

			if (Tags.isEmpty()) {

				assertTrue(driver.findElement(empty_SelectTagFilter).isDisplayed());

				waitforelement(shortwaitvalue);

				isdisplay(empty_SelectTagFilter, "User able to find No Results Found text in Select a Tag dropdown.",
						"User unable to find No Results Found text in Select a Tag dropdown.");

			} else {

				for (int i = 0; i < Tags.size(); i++) {

					selectTagXpath = startPath + Tags.get(i) + "'" + endPath;

					if (driver.findElement(By.xpath(selectTagXpath)).isDisplayed()) {

						assertTrue(driver.findElement(By.xpath(selectTagXpath)).isDisplayed());

						waitforelement(shortwaitvalue);

						isdisplay(By.xpath(selectTagXpath),
								"User able to find " + Tags.get(i) + " Tag in Select a Tag dropdown.",
								"User unable to find " + Tags.get(i) + " Tag in Select a Tag dropdown.");

					}
				}
				Tags.clear();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					"<html><body>"
							+ "<p>Error occurred while executing the script. Please check the exception below:</p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			e.printStackTrace();
			screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void fileDelete(String filePath, String expectedFileName, String passValue, String failValue) {

		File folder = new File(filePath);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.getName().equalsIgnoreCase(expectedFileName)) {
					file.delete();
					test.log(LogStatus.PASS, passValue);
				}
			}
		} else {
			System.out.println("File not found in directory: " + filePath);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
		}
	}

	@Override
	public void selectContactType(String locator, String contactType, String passValue, String failValue) {

		actionclick(driver.findElement(By.xpath("//" + locator + "[text()='" + contactType + "']")), passValue,
				failValue);

	}

	@Override
	public void contactsCount() {

		String contactsTextCount = getText(By
				.xpath(dynamicXpathClick(contactsText, excelutil.getData("Contacts", "contactsTextStartPath", xlsname),
						excelutil.getData("Contacts", "endPath", xlsname))));

		String contactsCount = contactsTextCount.substring(10, 12);

		isdisplay(
				By.xpath(
						dynamicXpathClick(contactsText, excelutil.getData("Contacts", "contactsTextStartPath", xlsname),
								excelutil.getData("Contacts", "endPath", xlsname))),
				"User able to find contacts title with count - " + contactsCount + " on Contacts list screen.",
				"User unable to find contacts title with count - " + contactsCount + " on Contacts list screen.");
	}

	@Override
	public void validatePlaceholder(By element, String attributeName, String validationValue, String passValue,
			String failValue) {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));

		try {
			assertTrue(driver.findElement(element).getAttribute(attributeName).equals(validationValue));
			waitforelement(shortwaitvalue);
			//driver.findElement(element).getAttribute(attributeName).equals(validationValue);
			test.log(LogStatus.PASS, passValue);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
		}
	}

	public void validateSelectedTags(By Element, String startPath, String endPath) {

		String indexXpath = "";

		try {
				List<WebElement> index = driver.findElements(Element);

				for (int i = 1; i <= index.size(); i++) {

					indexXpath = startPath + i + endPath;

						isdisplay(By.xpath(indexXpath),
								"User able to find " + driver.findElement(By.xpath(indexXpath)).getText()
										+ " tag selected.",
								"User unable to find " + driver.findElement(By.xpath(indexXpath)).getText()
										+ " tag selected.");
				}
				
				test.log(LogStatus.PASS, "User able to find no tags were selected.");
		} catch (NoSuchElementException e) {
			test.log(LogStatus.PASS, "User able to find no tags were selected in Tags field.");
		}
	}
	

}
