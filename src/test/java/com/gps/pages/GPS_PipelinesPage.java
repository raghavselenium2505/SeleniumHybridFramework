package com.gps.pages;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface pipelinesPage {
	public void pipelineNameInDropdown();
	public void clickNewOpportunity();
	public void checkDeletedStage();
	public void checkPipelineDropdown();
	public void checkDeletedPipeline();
	public void checkUpdatedStatusNames();
	public void download();
	public void verifyDownloadedFile(String filePath, String expectedFileName, String passValue, String failValue);
	public void fileDelete(String filePath, String expectedFileName, String passValue, String failValue);
	public void checkDownloadFileStatusColumn(String filePath);
	public void checkDownloadFile(String filePath); // not used
	public void verifyPipelineLandingPage();
	public void selectOrgFromNavigateToOrg(String orgName);
	public void checkNoPipelines();
	public void verifySearch(String value, By ref);
	public void selectPipeline(By ref, String colName);
	public void selectStatus(String colName);
	public void verifyDateAddedDescFilter();
	public void verifyDescAscFilter();
	public void createSimpleOpportunityWithSortPipeline(int i, String pipelineName);
	public void deleteOpportunityCards();
	public void verifyDropdown(By ddIcon, By ddItems);
	public void verifySearchInOwnerDD();
	public void verifyStatusDropdown();
	public void selectStatusFromStatusDropdown();
	public void additionalInformationDropdown();
	public void verifyNotesInCards();
	public void delete();
	public void verifyHideTags();
	public void verifyHideAssignedUser();
	public ArrayList<String> toGetPipelinesListFromPipelineDropdown(By ref1,By ref2);
	public boolean verifyAlphabeticalOrder(By ref1, By ref2);
	public ArrayList<String> toGetElementListFromEachPage(By ref);
	public ArrayList<String> toGetElementsTillLastPage(By ref);
	public ArrayList<String> toGetAssignedUserListFromOwnerDropdown(By ref1,By ref2);
	public void verifyCreateOpportunityPopup();
	public ArrayList<String> toGetStatusFromStatusDropdown(By ref1,By ref2);

}

public class GPS_PipelinesPage extends TestBase implements pipelinesPage

{
	int statusListSize;
	public ArrayList<String> elementsArrayList = new ArrayList<String>();
	public By orgPipelinesLeftSide = By.xpath("//span[text()='Pipelines']");
	public By FirstPipelineNameInFilter = By
			.xpath("//p-dropdown[contains(@class,'oppor-dropdown')]//span[contains(@class,'dropdown')]");
	public By pipelineDropdownExpand = By.xpath("//p-dropdown[contains(@placeholder,'Select a Pipeline')]");

	public By pipelineListFromDropdown = By.xpath("//ul[contains(@class,'dropdown-items')]//span");
	public By newOpportunityButton = By.xpath("//span[text()='New Opportunity']");
	public By contactNameTextBoxInNewOppPopup = By.xpath("//input[@id='contactName']");
	public By select1stContactNameFromSuggestion = By.xpath("//div[contains(@class,'autocomplete')]//span");
	public By selectPipelineDDExpandFromOpporPopup = By
			.xpath("//p-dropdown[contains(@formcontrolname,'pipelineId')]//span");
	public By selectStageDDExpandFromOpporPopup = By
			.xpath("//p-dropdown[@formcontrolname='stageId']//span[contains(@class,'dropdown-trigger')]");
	public By stagesListFromDropdown = By.xpath("//ul[contains(@class,'dropdown-items')]//span");
	public By saveButton = By.xpath("//span[text()='Save']");
	public By cancelButton = By.xpath("//span[text()='Cancel']");
	public By selectStatusDropdownExpand = By.xpath(
			"//p-dropdown[contains(@placeholder,'Owner')]/following-sibling::p-dropdown//span[contains(@class,'trigger')]");
	public By statusDefaultOption = By
			.xpath("//p-dropdown[contains(@placeholder,'Owner')]/following-sibling::p-dropdown//span");
	public By statusListFromDropdown = By.xpath("//ul[contains(@class,'dropdown-items')]//span");
	public By selectStatusDDExpandFromOpporPopup = By
			.xpath("//p-dropdown[contains(@placeholder,'Select Status')]//span[contains(@class,'trigger')]");
	public By downloadIcon = By.xpath("//button[contains(@ptooltip,'Download Pipeline')]");
	public By orgHeading = By.xpath("//h5[contains(text(),'QA Automation')]");
	public By navigateToOrgdd = By
			.xpath("//span[contains(text(),'Navigate to Organization')]//following::span[contains(@class,'dropdown')]");
	public By searchField = By.xpath("//input[@placeholder='Search']");
	public By calendar = By.xpath("//input[@placeholder='Choose Time Period']");
	public By dateDescDD = By.xpath("//span[contains(text(),'Date Added (Desc)')]");
	public By dateDescDDIcon=By.xpath("//span[contains(text(),'Date Added (Desc)')]//following::span");
	public By ownerDD = By.xpath("//span[contains(text(),'All Users')]");
	public By ownerDropdownAfter = By.xpath("//p-dropdown[@placeholder='Owner']//following::span");
	public By additionalInfoDD = By.xpath(" (//span[contains(text(),'Additional Information')])[2]");
	public By stageName = By.xpath("//h4[contains(@class,'stage')]");
	public By dropdownElements = By.xpath("//li[contains(@class,'dropdown')]/span");
	public By noPipelinesText = By.xpath("//h3[contains(text(),'no Pipelines')]");
	public By noMatchFound = By.xpath("//h3[contains(text(),'No Match Found')]");
	public By contactNameSearchResult = By.xpath("//p[contains(text(),'SPH')]");
	public By opportunityNameSearchResult = By.xpath("//p[contains(text(),'deal')]");
	public By pipelineOpportunityCards = By.xpath("//p[contains(@class,'title')]");
	public By opportunityCardStatus = By.xpath("//p[contains(@class,'status')]");
	public By contactNameList = By.xpath("//li[contains(@class,'autocomplete')]/span");
	public By dateAsc = By.xpath("//span[contains(text(),'Asc')]");
	public By dateDesc = By.xpath("//span[contains(text(),'Desc')]");
	public By statusInCards = By.xpath("//p[contains(@class,'status')]");
	public By deleteButton = By.xpath("//span[text()='Delete']");
	public By typeDeleteTextbox = By.xpath("//input[@placeholder='Type Delete']");
	public By submitButton = By.xpath("//span[text()='Submit']");
	public By searchInOwnerdd = By.xpath("//span[contains(@class,'search')]/preceding-sibling::input");
	public By noResultFoundInOwner = By.xpath("//li[text()='No results found']");
	public By ownerAssignedUserInCards = By
			.xpath("//div[contains(@class,'blocks')]//following::div[contains(@class,'profile')]");
	public By spinner = By.xpath("//div[contains(@class,'spinner')]");
	public By dropdownClearIcon = By.xpath("//i[contains(@class,'clear')]");
	public By opportunityCloseIcon = By.xpath("//a[contains(@class,'titlebar-close')]");
	public By taskInAdditionalInfo = By.xpath("//span[text()='Task']");
	public By noteInAdditionalInfo = By.xpath("//span[text()='Note']");
	public By additionalInfoDDList = By.xpath("//div[contains(@class,'chkbox')]//following-sibling::span");
	public By opportunityTextBox = By.xpath("//input[@id='opportunityName']");
	public By additionalInfoDDCheckbox = By
			.xpath("//div[contains(@class,'chkbox') and contains(@class,'state-default')]");
	public By noteInCard = By.xpath("//i[contains(@class,'notes')]");
	public By noteTabInEditOpportunity = By.xpath("//span[text()='Notes']");
	public By noItems = By.xpath("//h3[contains(text(),'no items')]");
	public By iconsInCard = By.xpath("//i[contains(@class,'pipeline-icons')]");
	public By noteTextboxInEditOppor = By.xpath("//textarea[@id='note']");
	public By notesAddedText = By.xpath("//pre[contains(@class,'notes')]");
	public By deleteIcon = By.xpath("//a[contains(@class,'trash')]");
	public By hideTagsInAdditionalInfo = By.xpath("//span[text()='Hide Tags']");
	public By tagsInCard = By.xpath("//span[contains(@class,'add')]");
	//public By selectOrganization = By.xpath("//h3[text()='QA Automation']");
	public By selectOrganization=By.xpath("//h3[text()='QA Automation']//preceding::div[contains(@class,'agent-details')]");
	public By hideAssignedUserInAdditionalInfo = By.xpath("//span[text()='Hide Assigned To']");
	public By nextPageArrow = By.xpath("//a[contains(@class,'next')]");
	public By assignedUserNamesFromUserManage = By.xpath("//tbody//p[contains(@class,'fs-name')]");
	public By contactInformationHeaderFromOpporPopup=By.xpath("//h4[text()='Contact Information']");
	public By contactNameLabelFromOpporPopup=By.xpath("//label[text()='Contact Name*']");
	public By businessNameLabelFromOpporPopup=By.xpath("//label[text()='Business Name']");
	public By emailLabelFromOpporPopup=By.xpath("//label[text()='Email']");
	public By phoneNumberLabelFromOpporPopup=By.xpath("//label[text()='Phone Number']");
	public By extensionLabelFromOpporPopup=By.xpath("//label[text()='Extension']");
	public By tagsLabelFromOpporPopup=By.xpath("//label[text()='Tags']");
	public By contactNameInputFromOpporPopup=By.xpath("//input[@id='contactName']");
	public By businessNameInputFromOpporPopup=By.xpath("//input[@id='businessName']");
	public By emailInputFromOpporPopup=By.xpath("//input[@id='email']");
	public By phoneNumberInputFromOpporPopup=By.xpath("//input[@id='phoneNumber']");
	public By extnNumberInputFromOpporPopup=By.xpath("//input[@id='extensionNumber']");
	public By tagsInputFromOpporPopup=By.xpath("//input[@id='tags']");
	public By opportunityInformationHeaderFromOpporPopup=By.xpath("//h4[text()='Opportunity Information']");
	public By opportunityNameLabelFromOpporPopup=By.xpath("//label[text()='Opportunity Name*']");
	public By pipelineLabelFromOpporPopup=By.xpath("//label[text()='Pipeline*']");
	public By stageLabelFromOpporPopup=By.xpath("//label[text()='Stage*']");
	public By leadValueLabelFromOpporPopup=By.xpath("//label[text()='Lead Value']");
	public By assignedUserLabelFromOpporPopup=By.xpath("//label[text()='Assigned User']");
	public By sourceLabelFromOpporPopup=By.xpath("//label[text()='Source']");
	public By statusLabelFromOpporPopup=By.xpath("//label[text()='Status*']");
	public By opportunityNameInputFromOpporPopup=By.xpath("//input[@id='opportunityName']");
	public By sourceInputFromOpporPopup=By.xpath("//input[@id='source']");
	public By leadValueInputFromOpporPopup=By.xpath("//input[@formcontrolname='leadValue']");
	public By  assignedUserDropdownFromOpporPopup=By.xpath("//p-dropdown[@formcontrolname='ownerId']//span[contains(@class,'dropdown-trigger')]");
	public By contactNameErrorFromOpporPopup=By.xpath("//span[contains(text(),'Contact Name is required')]");
	public By opporNameErrorFromOpporPopup=By.xpath("//span[contains(text(),'Opportunity Name is required')]");



	


			
	
	
	@Override
	public void pipelineNameInDropdown() {
		boolean status = false;
		click(pipelineDropdownExpand, "Able to expand dropdown", "Unable to expand dropdown");

		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(pipelineListFromDropdown,20);

		logger.info("Pipeline dropdown has: ");
		test.log(LogStatus.INFO, "Pipeline dropdown has: ");

		for (WebElement ele : l1) {
			String name = ele.getText();
			logger.info(name);
			test.log(LogStatus.INFO, name);

			if (ele.getText().equalsIgnoreCase(excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname))) {
				status = true;
				break;
			}

		}

		if (status) {
			test.log(LogStatus.PASS,
					"Created Pipelines from settings module is displayed in Pipelines module dropdown");
		} else if (status) {
			test.log(LogStatus.FAIL,
					"Created Pipelines from settings module is NOT displayed in Pipelines module dropdown");
		}
	}

	@Override
	public void clickNewOpportunity() {
		click(newOpportunityButton, "Able to click on New Opportunity button and Create Opportunity Popup is dispalyed",
				"Unable to click on New Opportunity button");

	}

	@Override
	public void createSimpleOpportunityWithSortPipeline(int i, String pipelineName) {

		clickNewOpportunity();

		WaitUtils.waitInvisibleSpinner(spinner, 40);

		WaitUtils.waitClickByRef(contactNameTextBoxInNewOppPopup, 40);

		sendkeys(contactNameTextBoxInNewOppPopup, "d", "Able to enter value in contact name field",
				"Able to enter value in contact name field");

		List<WebElement> contactNames = WaitUtils.waitVisibilityOfDropdownElements(contactNameList, 40);

		logger.info("Size is " + contactNames.size());

		WaitUtils.waitClickWebElement(contactNames.get(i), 30).click();

		waitforelement(shortwaitvalue);

		WaitUtils.waitClickByRef(saveButton, 40).click();

		WaitUtils.waitInvisibleSpinner(spinner, 40);

	}

	@Override
	public void checkDeletedStage() {

boolean status = false;
		
		try {
		
		GPS_SettingsPipelinesPage settingsPipelinePage = new GPS_SettingsPipelinesPage();

		String pname = settingsPipelinePage.pipelineNameCreated;

		logger.info("Pipelines module " + pname);

		//To click new opportunity
		clickNewOpportunity();
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		
		//To expand pipeline dropdown
		click(selectPipelineDDExpandFromOpporPopup, "Able to click select pipeline dropdown",
				"Unable to click pipeline dropdown");

		//To fetch pipeline dropdown
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(pipelineListFromDropdown,20);


		//To select pipeline 
		for (i = 0; i < l1.size(); i++) 
		{
			if (l1.get(i).getText().equals(pname)) {
				WaitUtils.waitClickWebElement(l1.get(i),10).click();
				break;
			}
		}
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		//To expand stage dropdown
		click(selectStageDDExpandFromOpporPopup, "Able to click Select Stage Dropdown",
					"Unable to click select stage dropdown");
		
		//To fetch stage dropdown elements
		List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(stagesListFromDropdown,20);
		logger.info("Number of stages in opportunity popup " + l2.size());
		logger.info("Stage dropdown has");
		for (i = 0; i < l2.size(); i++) {
			logger.info(l2.get(i).getText());
			//test.log(LogStatus.PASS, l2.get(i).getText());
			if (l2.get(i).getText().equals("Automation")) {
				status = false;
			}

		}
		
		
		}

		catch (StaleElementReferenceException e) {
			click(selectStageDDExpandFromOpporPopup, "Able to click on Select Stage Dropdown",
					"Unable to click select stage dropdown");
			List<WebElement> l2 =  WaitUtils.waitVisibilityOfDropdownElements(stagesListFromDropdown,20);
			logger.info("Number of stages in opportunity popup " + l2.size());
			logger.info("Stage dropdown has");
			for (i = 0; i < l2.size(); i++) {
				logger.info(l2.get(i).getText());
				test.log(LogStatus.PASS, l2.get(i).getText());
				if (l2.get(i).getText().equals("Automation")) {
					status = false;
				}

			}

		}

		if (status) {
			logger.info("Deleted Stage is displayed");
			test.log(LogStatus.FAIL, "Deleted Stage is displayed");
		}

		else {
			logger.info("Deleted Stage is NOT displayed");
			test.log(LogStatus.PASS, "Deleted Stage is NOT displayed");
		}

		click(cancelButton, "Able to click on cancel button and New Opportunity popup is closed",
				"Unable to click on cancel button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	public void checkPipelineDropdown() {
		GPS_SettingsPipelinesPage settingsPipelinePage = new GPS_SettingsPipelinesPage();

		//boolean status = settingsPipelinePage.toggleStatus;
		//boolean flag=settingsPipelinePage.toggleProgress;

		//logger.info("Status is Now " + status);
		String ActivePname=excelutil.getData("Settings_PipelinesTab", "PipelineNameForActiveInActive", xlsname);
		String InActivePname=excelutil.getData("Settings_PipelinesTab", "PipelineNameForActiveInActiveSecond", xlsname);
		
		logger.info("Active pipeline is "+ActivePname);
		
		logger.info("InActive pipeline is "+InActivePname);
		
		WaitUtils.waitClickByRef(stageName,100);
	
	click(pipelineDropdownExpand, "Able to click on pipeline dropdown", "Unable to click on pipeline dropdown");

		
		
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(pipelineListFromDropdown,20);
		boolean result = false;
		boolean flag=false;

	
			for (i = 0; i < l1.size(); i++) {

				if(l1.get(i).getText().equals(ActivePname))
				{
					test.log(LogStatus.INFO,"Active name "+l1.get(i).getText());

					result = true;
				

				}
				
				if(l1.get(i).getText().equals(InActivePname))
				{
					test.log(LogStatus.INFO,"In active name "+l1.get(i).getText());
					flag = true;

				}
				

			}

		

		if (result) {
			test.log(LogStatus.PASS, "Active pipelines are dispalyed in pipeline dropdown");
		} else {
			test.log(LogStatus.FAIL, "Active pipelines are NOT dispalyed in pipeline dropdown");
		}
		
		if (flag) {
			test.log(LogStatus.FAIL, "InActive pipelines are dispalyed in pipeline dropdown");
		} else {
			test.log(LogStatus.PASS, "InActive pipelines are NOT dispalyed in pipeline dropdown");
		}
		
		
		
	}

	@Override
	public void checkDeletedPipeline() {
		GPS_SettingsPipelinesPage settingsPipelinePage = new GPS_SettingsPipelinesPage();

		//To fetch pipeline name which is deleted from settings > pipelines
		String pname=GPS_SettingsPipelinesPage.pipelineNameCreated;
		
		click(pipelineDropdownExpand, "Able to click on pipeline dropdown", "Unable to click on pipeline dropdown");

		List<WebElement> l1 =WaitUtils.waitVisibilityOfDropdownElements(pipelineListFromDropdown,20);
		boolean result = false;
		
		for(WebElement ele:l1)
		{
			if(ele.getText().equals(pname))
			{
				result=true;
				break;
			}
		}
		
		if(result)
		{
			test.log(LogStatus.FAIL, "Deleted pipeline is dispalyed in pipeline dropdown");
		}
		
		else
		{
			test.log(LogStatus.PASS, "Deleted pipeline is NOT dispalyed in pipeline dropdown");
			
		}
		

	}

	@Override
	public void checkUpdatedStatusNames() {
		// GPS_SettingsPipelinesPage settingsPipelinesPage = new
		// GPS_SettingsPipelinesPage();
		String firstName = GPS_SettingsPipelinesPage.firstStatusName;
		String secondName = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdName = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthName = GPS_SettingsPipelinesPage.fourthStatusName;
		
		WaitUtils.waitClickByRef(stageName);

		click(selectStatusDropdownExpand, "Able to expand status dropdown", "Unable to expand status dropdown");

		List<WebElement> l1 =WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown,20);

		//test.log(LogStatus.INFO, "status dropdown  has");
		logger.info("status from dropdown ");
		
		logger.info("Will remove "+l1.get(0).getText());
		
		l1.remove(l1.get(0));
		
		

		for (WebElement ele : l1) {
			String statusName = ele.getText();
			logger.info(statusName);
			
			if(statusName.equals(firstName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown");

			}
			
			else if(statusName.equals(secondName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown");

			}
			else if(statusName.equals(thirdName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown");

			}
			else if(statusName.equals(fourthName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown");

			}
			else
			{
				test.log(LogStatus.FAIL, "Status names are NOT Updated");

			}
			
		
		}

		click(selectStatusDropdownExpand, "Able to expand status dropdown", "Unable to expand status dropdown");
		
		clickNewOpportunity();
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		WaitUtils.waitClickByRef(selectPipelineDDExpandFromOpporPopup);

		click(selectStatusDDExpandFromOpporPopup, "Able to click status dropdown in create opportunity popup",
				"Unable to click status dropdown in create opportunity popup");

		List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown,20);

		//test.log(LogStatus.INFO, "status dropdown  has");
		logger.info("l2 size  "+l2.size());
		
		
		
		for (WebElement ele : l2)
		{
			String statusName = ele.getText();
			logger.info(statusName);
			
			if(statusName.equals(firstName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");

			}
			
			else if(statusName.equals(secondName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");

			}
			else if(statusName.equals(thirdName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");
			}
			else if(statusName.equals(fourthName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");
			}
			
			else
			{
				test.log(LogStatus.FAIL, "Status names are NOT Updated");

			}

	
		}

		click(cancelButton, "Able to click on cancel button and create opportunity popup is closed",
				"Unable to click om cancel button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	public void download() {
		click(pipelineDropdownExpand, "Able to click on pipeline dropdown", "Unable to click on pipelines dropdown");

		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(pipelineListFromDropdown,20);

		for (WebElement ele : l1) {
			if (ele.getText().equals(excelutil.getData("PipelinesModule", "Status pipeline name", xlsname))) {
				ele.click();
				break;
			}

		}


		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		click(selectStatusDropdownExpand, "Able to click on status dropdown", "Unable to click on status dropdown");

		List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown,20);
		for (WebElement ele : l2) {
			if (ele.getText().equals("All")) {
				ele.click();
				break;
			}

		}
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		click(downloadIcon, "Able to download Opportunities file", "Unable to download");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

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
	public void checkDownloadFileStatusColumn(String filePath) {

		String firstName = GPS_SettingsPipelinesPage.firstStatusName;
		String secondName = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdName = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthName = GPS_SettingsPipelinesPage.fourthStatusName;
		// Read the CSV file
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));

			// Read the header row to get column names
			String headerRow = reader.readLine();
			String[] headers = headerRow.split(","); // Split based on comma

			// Find the column index of "Status"
			int statusColumnIndex = -1;

			for (int i = 0; i < headers.length; i++) {
				if (headers[i].trim().equalsIgnoreCase("Status")) {
					statusColumnIndex = i;
					break;
				}
			}

			// Now process the rows
			String line;
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(","); // Split based on comma
				String statusValue = row[statusColumnIndex].trim();

				if (statusValue.equalsIgnoreCase(firstName)) {
					test.log(LogStatus.PASS, statusValue + " is displayed in downloaded file");
				}

				else if (statusValue.equalsIgnoreCase(secondName)) {
					test.log(LogStatus.PASS, statusValue + " is displayed in downloaded file");
				} else if (statusValue.equalsIgnoreCase(thirdName)) {
					test.log(LogStatus.PASS, statusValue + " is displayed in downloaded file");
				} else if (statusValue.equalsIgnoreCase(fourthName)) {
					test.log(LogStatus.PASS, statusValue + " is displayed in downloaded file");
				}

				else {
					test.log(LogStatus.FAIL, "Downloaded file of status column values are NOT matched");
				}

			}
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@Override
	public void checkDownloadFile(String filePath) {

		String firstName = GPS_SettingsPipelinesPage.firstStatusName;
		String secondName = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdName = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthName = GPS_SettingsPipelinesPage.fourthStatusName;

		File f = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet = wb.getSheetAt(0);

			XSSFRow row = sheet.getRow(0);
			int statusColumnIndex = -1;

			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
				String colName = row.getCell(i).getStringCellValue().trim();

				if (colName.equalsIgnoreCase("Status")) {
					statusColumnIndex = i;
					break;
				}
			}

			test.log(LogStatus.PASS, "Status column of downloaded file has ");

			for (int i = 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row1 = sheet.getRow(i);

				if (row1 != null) {
					XSSFCell statusCell = row.getCell(statusColumnIndex);

					if (statusCell != null) {
						String statusValue = statusCell.getStringCellValue().trim();

						if (statusValue.equalsIgnoreCase(firstName)) {
							test.log(LogStatus.PASS, statusValue);
						}

						else if (statusValue.equalsIgnoreCase(secondName)) {
							test.log(LogStatus.PASS, statusValue);
						} else if (statusValue.equalsIgnoreCase(thirdName)) {
							test.log(LogStatus.PASS, statusValue);
						} else if (statusValue.equalsIgnoreCase(fourthName)) {
							test.log(LogStatus.PASS, statusValue);
						}
					} else {
						test.log(LogStatus.FAIL, "Downloaded file of status column values are NOT matched");
					}

				}

			}

			fis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void verifyPipelineLandingPage() {

		WaitUtils.waitInvisibleSpinner(spinner);
		// organization heading
		isdisplay(orgHeading, "Organization heading is displayed", "Organization heading is NOT displayed");
		assertTrue(driver.findElement(orgHeading).isDisplayed(), "Organization heading is NOT displayed");

		// navigate to dropdown
		isdisplay(navigateToOrgdd, "Navigate To Organization dropdown is displayed",
				"Navigate To Organization dropdown is NOT displayed");
		assertTrue(driver.findElement(navigateToOrgdd).isDisplayed(),
				"Navigate To Organization dropdown is NOT displayed");

		// search
		isdisplay(searchField, "Search field is displayed", "Search field is NOT displayed");
		assertTrue(driver.findElement(searchField).isDisplayed(), "Search field is NOT displayed");

		// choose time calendar
		isdisplay(calendar, "Calendar is displayed", "Calendar is NOT displayed");
		assertTrue(driver.findElement(calendar).isDisplayed(), "Calendar is NOT displayed");

		// date descending dropdown
		isdisplay(dateDescDD, "Date Added(DESC) dropdown is displayed", "Date Added(DESC) dropdown is NOT displayed");
		assertTrue(driver.findElement(dateDescDD).isDisplayed(), "Date Added(DESC) dropdown is NOT displayed");

		// select pipeline dropdown
		isdisplay(pipelineDropdownExpand, "Select a Pipeline dropdown is displayed",
				"Select a Pipeline dropdown is NOT displayed");
		assertTrue(driver.findElement(pipelineDropdownExpand).isDisplayed(),
				"Select a Pipeline dropdown is NOT displayed");

		// Opportunity
		isdisplay(newOpportunityButton, "Opportunity button is displayed", "Opportunity button is NOT displayed");
		assertTrue(driver.findElement(newOpportunityButton).isDisplayed(), "Opportunity button is NOT displayed");

		// download
		isdisplay(downloadIcon, "Download option is displayed", "Download option is NOT displayed");
		assertTrue(driver.findElement(downloadIcon).isDisplayed(), "Download option is NOT displayed");

		// owner or user dropdown
		isdisplay(ownerDD, "Owner dropdown is displayed", "Owner dropdown is NOT displayed");
		assertTrue(driver.findElement(ownerDD).isDisplayed(), "Owner dropdown is NOT displayed");

		// status drodown
		isdisplay(selectStatusDropdownExpand, "Status dropdown is displayed", "Status dropdown is NOT displayed");
		assertTrue(driver.findElement(selectStatusDropdownExpand).isDisplayed(), "Status dropdown is NOT displayed");

		// Additional information
		isdisplay(additionalInfoDD, "Additional Information dropdown is displayed",
				"Additional Information dropdown is NOT displayed");
		assertTrue(driver.findElement(additionalInfoDD).isDisplayed(),
				"Additional Information dropdown is NOT displayed");

	}

	@Override
	public void selectOrgFromNavigateToOrg(String orgName) {
		WaitUtils.waitClickByRef(navigateToOrgdd).click();
		test.log(LogStatus.PASS, "Able to click on Navigate To Organization dropdown");

		WebElement ele = driver.findElement(By.xpath("//span[text()='" + orgName + "']"));

		if (ele.isDisplayed()) {
			ele.click();
			test.log(LogStatus.PASS, "Able to select " + orgName + "  from Navigate to Organization dropdown");
		}

		else {
			test.log(LogStatus.FAIL, "Unable to select organization from dropdown");
		}

		WaitUtils.waitInvisibleSpinner(spinner);
	}

	@Override
	public void checkNoPipelines() {

		try {

			// Randomly clicking as page takes time to load

			// Random click of additional info dropdown
			WebElement eleDD=WaitUtils.waitClickByRef(additionalInfoDD);
			
			Actions act=new Actions(driver);
			act.moveToElement(eleDD).click().perform();

			// fetching dropdown list
			List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(additionalInfoDDList, 40);

			logger.info("size " + l1.size());

			// clicking checkbox of each element in list
			for (WebElement ele : l1) {
				WaitUtils.waitClickWebElement(ele, 30).click();

			}
			// clicking uncheckbox of each element in list
			for (WebElement ele : l1) {
				WaitUtils.waitClickWebElement(ele, 30).click();

			}

			// closing additional info dropdown
			WaitUtils.waitClickByRef(additionalInfoDD, 30).click();

			// clicking status dropdown
			WaitUtils.waitClickByRef(selectStatusDropdownExpand).click();

			// fetching status dropdown
			List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 40);

			// closing status dropdown
			WaitUtils.waitClickByRef(selectStatusDropdownExpand, 40).click();

			// clicking crossmark
			WaitUtils.waitClickByRef(dropdownClearIcon, 30).click();

			// Verifying text of There are no Pipelines to display opportunities.
			String value = driver.findElement(noPipelinesText).getText();
			logger.info(value);

			if (value.equals(excelutil.getData("PipelinesModule", "NoPipelinesText", xlsname))) {
				// checking pipeline dropdowns after selecting org from dropdown
				verifyPipelineLandingPage();

				test.log(LogStatus.PASS,
						excelutil.getData("PipelinesModule", "NoPipelinesText", xlsname) + " is displayed");
			}
		}

		catch (NoSuchElementException e) {

			test.log(LogStatus.PASS, "Pipelines are avaialble for this organization");
		}
	}

	@Override
	public void verifySearch(String value, By ref) {

		WebElement ele=WaitUtils.waitVisibilityByRef(searchField, 30);
		ele.sendKeys(value);
		test.log(LogStatus.PASS, "Able to enter value in search field as \" + value");

	}

	@Override
	public void selectPipeline(By ref, String colName) {

		WaitUtils.waitClickByRef(ref).click();
		test.log(LogStatus.PASS, "Able to click on pipeline dropdown");

		List<WebElement> pipelineList = WaitUtils.waitVisibilityOfDropdownElements(pipelineListFromDropdown, 30);

		String actualPipelineName = colName;

		boolean pipelineFound = false;

		for (WebElement pipelineName : pipelineList) {
			// logger.info("pipelines from dropdown " + pipelineName.getText());
			if (pipelineName.getText().equals(actualPipelineName)) {

				WaitUtils.waitClickWebElement(pipelineName, 30);
				pipelineName.click();
				pipelineFound = true;
				break;

			}
		}

		if (pipelineFound) {
			test.log(LogStatus.PASS, "Able to select pipeline as " + colName + " from pipeline dropdown");
		} else {
			test.log(LogStatus.FAIL, "Unable to select pipeline from pipeline dropdown");
		}
		WaitUtils.waitInvisibleSpinner(spinner, 40);

	}

	@Override
	public void selectStatus(String colName) {

		// click status dropdown
		WaitUtils.waitClickByRef(selectStatusDropdownExpand).click();
		test.log(LogStatus.PASS, "Able to click on Status dropdown");

		// fetching status dropdown values
		List<WebElement> statusList = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 30);

		// here status dropdown value is getting from excel
		String actualStatusName = colName;

		boolean statusFound = false;

		// if status dropdown matches with excel value then it clicks
		for (WebElement status : statusList) {
			logger.info("pipelines from dropdown " + status.getText());
			if (status.getText().equals(actualStatusName)) {
				WaitUtils.waitClickWebElement(status, 30).click();

				statusFound = true;
				break;

			}
		}

		if (statusFound) {
			test.log(LogStatus.PASS, "Able to select status as " + colName + " from status dropdown");
		} else {
			test.log(LogStatus.FAIL, "Unable to select status from status dropdown");
		}

		waitforvisibilityOfElement(spinner, 30);

	}

	@Override
	public void verifyDateAddedDescFilter() {

		// clicking sorting dropdown
		WebElement ele = WaitUtils.waitClickByRef(dateDescDDIcon);
		actionclick(ele, "Able to click on Date Added Desc dropdown", "Unable to click on Date Added Desc dropdown");

		// fetching elements from sorting dropdown
		List<WebElement> dateDD = WaitUtils.waitVisibilityOfDropdownElements(dropdownElements, 30);

		logger.info("dropdown has ");
		test.log(LogStatus.INFO, "dropdown has ");

		// checking sorting dropdown values with excel
		for (WebElement datedd : dateDD) {

			String actualValue = datedd.getText();

			if (actualValue.equals(excelutil.getData("PipelinesModule", "DescValue", xlsname))) {
				test.log(LogStatus.PASS, actualValue + " is displayed");
			} else if (actualValue.equals(excelutil.getData("PipelinesModule", "AscValue", xlsname))) {
				test.log(LogStatus.PASS, actualValue + " is displayed");
			}

		}

	}

	@Override
	public void verifyDescAscFilter() {

		// Create 1st opportunity by selecting sort pipeline name
		createSimpleOpportunityWithSortPipeline(0, excelutil.getData("PipelinesModule", "SortPipelineName", xlsname));

		// fetching opportunity cards
		List<WebElement> cards = WaitUtils.waitVisibilityOfDropdownElements(pipelineOpportunityCards, 30);
		logger.info("size is " + cards.size());

		// fetching contact name of newly added opportunity
		String value0 = cards.get(0).getText();
		logger.info("Before sorting 2nd contact name is " + value0);

		// Create 2nd opportunity by selecting sort pipeline name
		createSimpleOpportunityWithSortPipeline(1, excelutil.getData("PipelinesModule", "SortPipelineName", xlsname));

		// fetching opportunity cards
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		List<WebElement> cardsNew = wait
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(pipelineOpportunityCards, 1));

		// fetching contact name of newly added opportunity
		String value1 = cardsNew.get(0).getText();
		logger.info("Before sorting 1st contact name is " + value1);
		logger.info("New size is " + cardsNew.size());

		// clicking date dropdown
		WaitUtils.waitClickByRef(dateDescDD).click();

		// select ascending from date dropdown
		click(dateAsc, "Able to select Ascending from filter", "Unable to select ascending from filter");

		WaitUtils.waitInvisibleSpinner(spinner, 30);

		// fetching opportunity cards
		List<WebElement> cardsAfterSortAsc = WaitUtils.waitVisibilityOfDropdownElements(pipelineOpportunityCards, 30);

		// fetching contact names of opportunities
		String ascSort0 = cardsAfterSortAsc.get(0).getText();
		String ascSort1 = cardsAfterSortAsc.get(1).getText();

		logger.info("After sorting 1st contact name is " + ascSort0);
		logger.info("After sorting 2nd contact name is " + ascSort1);

		// checking opportunities order after selecting ascending
		if (ascSort0.equalsIgnoreCase(value0)) {
			if (ascSort1.equalsIgnoreCase(value1)) {
				test.log(LogStatus.PASS, "Oppourtunities are sorted in Ascending order");
			}

		}

		else {
			test.log(LogStatus.FAIL, "Oppourtunities are NOT sorted in Ascending order");
		}

		// click date dropdown
		WaitUtils.waitClickByRef(dateAsc, 40).click();

		// select descending
		click(dateDesc, "Able to select Descending from filter", "Unable to select Descending from filter");
		WaitUtils.waitInvisibleSpinner(spinner, 40);

		// fetching opportunity cards
		List<WebElement> cardsAfterSortDesc = WaitUtils.waitVisibilityOfDropdownElements(pipelineOpportunityCards, 30);

		String descSort0 = cardsAfterSortDesc.get(0).getText();
		String descSort1 = cardsAfterSortDesc.get(1).getText();

		// checking opportunities order after selecting descending
		if (descSort0.equalsIgnoreCase(ascSort1)) {
			if (descSort1.equalsIgnoreCase(ascSort0)) {
				test.log(LogStatus.PASS, "Oppourtunities are sorted in Descending order");
			}

		}

		else {
			test.log(LogStatus.FAIL, "Oppourtunities are NOT sorted in Descending order");
		}

	}

	@Override
	public void deleteOpportunityCards() {

		// select sort pipeline sph dont pipeline from pipeline dropdown
		selectPipeline(pipelineDropdownExpand, excelutil.getData("PipelinesModule", "SortPipelineName", xlsname));

		try {

			WaitUtils.waitInvisibleSpinner(spinner);

			// fetching opportunities for that pipeline
			List<WebElement> cards = WaitUtils.waitVisibilityOfDropdownElements(statusInCards, 40);

			// iterating each card
			for (WebElement card : cards) {

				// opening opportunity card
				WaitUtils.waitClickWebElement(card, 40).click();

				WaitUtils.waitInvisibleSpinner(spinner, 40);

				// clicking on delete of each card
				WebElement ele = WaitUtils.waitClickByRef(deleteButton);
				Actions act = new Actions(driver);
				act.moveToElement(ele).click().perform();
				test.log(LogStatus.PASS, "Able to click on delete button");

				// entering delete in delete popup
				WaitUtils.waitClickByRef(typeDeleteTextbox, 40).sendKeys("delete");

				// click on submit
				WaitUtils.waitClickByRef(submitButton, 40).click();

				WaitUtils.waitInvisibleSpinner(spinner,60);

			}

		} catch (StaleElementReferenceException e) {

			WaitUtils.waitInvisibleSpinner(spinner);
			waitForWebElement(statusInCards, 40);

			List<WebElement> cards = driver.findElements(statusInCards);

			for (WebElement card : cards) {

				WaitUtils.waitClickWebElement(card, 40).click();

				WaitUtils.waitInvisibleSpinner(spinner, 40);

				WebElement ele = WaitUtils.waitClickByRef(deleteButton, 40);
				Actions act = new Actions(driver);
				act.moveToElement(ele).click().perform();
				test.log(LogStatus.PASS, "Able to click on delete button");

				waitForWebElement(typeDeleteTextbox, 40);
				driver.findElement(typeDeleteTextbox).sendKeys("delete");
				waitForWebElement(submitButton, 40);
				driver.findElement(submitButton).click();
				waitForWebElement(dateDescDD, 40);

			}

		}

		catch (NoSuchElementException e) {

		}

		catch (TimeoutException e) {

		}

	}

	@Override
	public void verifyDropdown(By ddIcon, By ddItems) {
		click(ddIcon);

		List<WebElement> ddValues = driver.findElements(ddItems);

		logger.info("dropdown has");
		test.log(LogStatus.INFO, "dropdown has");

		for (WebElement ddValue : ddValues) {
			logger.info(ddValue.getText());
			test.log(LogStatus.PASS, ddValue.getText());

		}

	}

	@Override
	public void verifySearchInOwnerDD() {

		// click owner dropdown
		WaitUtils.waitClickByRef(ownerDD).click();
		test.log(LogStatus.PASS, "Able to click on owner dropdown");

		// fetching invalid owner from excel
		String invalidOwner = excelutil.getData("PipelinesModule", "invalidSearchData", xlsname);

		// proving invalid search in owner dropdown
		sendkeys(searchInOwnerdd, invalidOwner, "Able to enter search value as " + invalidOwner,
				"Unable to enter search value");

		// waiting for no result text
		WaitUtils.waitVisibilityByRef(noResultFoundInOwner);

		// checking no result text
		isdisplay(noResultFoundInOwner, "No results found is displayed", "No results found is NOT displayed");
		assertTrue(driver.findElement(noResultFoundInOwner).isDisplayed(), "No results found is NOT displayed");

		// clearing search value
		clear(searchInOwnerdd, "Able to clear search value", "Unable to clear search value");

		// fetching Valid owner from excel
		String validOwner = excelutil.getData("PipelinesModule", "validOwnerName", xlsname);

		// proving Valid search as raghavendra r in owner dropdown
		sendkeys(searchInOwnerdd, validOwner, "Able to enter search value as " + validOwner,
				"Unable to enter search value");

		WaitUtils.waitInvisibleSpinner(spinner);

		// fetching owner dropdown values and clicking owner which is equal to excel
		// owner
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(dropdownElements, 40);
		for (WebElement ele : l1) {

			if (ele.getText().equals(validOwner)) {
				WaitUtils.waitClickWebElement(ele, 30).click();
				break;
			}

		}

		// Fetching opportunities assigned user names
		List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(ownerAssignedUserInCards, 30);

		// iterating each element to check text of assigned user
		boolean status = true;
		for (WebElement ele : l2) {
			logger.info(ele.getText());
			if (!ele.getText().equals("RR")) {
				status = false;
				break;

			}
		}

		if (status) {
			test.log(LogStatus.PASS, "Respective opportunities are displayed");
		}

		else {
			test.log(LogStatus.FAIL, "Respective opportunities are NOT displayed");

		}

	}

	
	@Override
	public void verifyStatusDropdown() {

		// fetching status dropdown default value
		String actualStatusFilterDefaultValue =WaitUtils.waitClickByRef(statusDefaultOption).getText();

		logger.info("default value " + actualStatusFilterDefaultValue);

		GPS_SettingsPipelinesPage settingsPipelinesPage = new GPS_SettingsPipelinesPage();

		// Fetching status names from settings > pipelines
		String firstExpectedStatusValue = GPS_SettingsPipelinesPage.firstStatusName;
		String secondStatusValue = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdStatusValue = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthStatusValue = GPS_SettingsPipelinesPage.fourthStatusName;

		// checking status dropdown default value with settings >pipelines status
		assertTrue(actualStatusFilterDefaultValue.equals(firstExpectedStatusValue));

		test.log(LogStatus.PASS, "Status Dropdown default value is " + actualStatusFilterDefaultValue);

		// initializing array
		ArrayList<String> statusArray = new ArrayList<String>();

		// adding values to status array
		Collections.addAll(statusArray, firstExpectedStatusValue, secondStatusValue, thirdStatusValue,
				fourthStatusValue);

		// clicking status dropdown from pipeline module
		WaitUtils.waitClickByRef(selectStatusDropdownExpand, 40).click();

		// fetching status dropdown elements
		List<WebElement> statusDropdownList = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 40);

		// initializing array
		ArrayList<String> statusDropdown = new ArrayList<String>();

		// iterating status dropdown elements and add it to array
		for (WebElement statusDropdownValue : statusDropdownList) {
			String status = statusDropdownValue.getText();
			logger.info("dropdown has " + status);
			statusDropdown.add(status);
			test.log(LogStatus.PASS, status);

		}

		logger.info("dropdown " + statusDropdown);
		logger.info("status array " + statusArray);

		// checking status dropdown values with status array of settings > pipelines
		if (statusDropdown.containsAll(statusArray)) {
			test.log(LogStatus.PASS, "Dropdown values are matched");
		}

		else {
			test.log(LogStatus.FAIL, "dropdown values are NOT matched");
		}

		
		
		// fetching list to get size
		List<WebElement> statusList = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 40);
		 statusListSize=statusList.size();
		
		logger.info("size is " + statusList.size());
		
		WaitUtils.waitClickByRef(selectStatusDropdownExpand).click();

	}

	@Override
	public void selectStatusFromStatusDropdown() {

		GPS_SettingsPipelinesPage settingsPipelinesPage = new GPS_SettingsPipelinesPage();

		String firstExpectedStatusValue = GPS_SettingsPipelinesPage.firstStatusName;
		String secondStatusValue = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdStatusValue = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthStatusValue = GPS_SettingsPipelinesPage.fourthStatusName;
		
		//Random click
		WaitUtils.waitClickByRef(additionalInfoDD).click();
		WaitUtils.waitClickByRef(additionalInfoDD).click();
		

		// clicking status dropdown
		//WaitUtils.waitClickByRef(selectStatusDropdownExpand).click();

		// fetching list to get size
		//List<WebElement> statusList = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 40);

		//logger.info("size is " + statusList.size());

		// closing status dropdown
		//WaitUtils.waitClickByRef(selectStatusDropdownExpand).click();

		// iterating status dropdown
		for (i = 0; i < statusListSize; i++) {

			// clicking status dropdown
			WaitUtils.waitClickByRef(selectStatusDropdownExpand).click();

			// fetching status
			List<WebElement> statusListNew = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 40);

			logger.info("i is " + i);
			String filterValue = statusListNew.get(i).getText();
			logger.info(filterValue);

			// clicking 1st value from status dropdown
			WaitUtils.waitClickWebElement(statusListNew.get(i), 40).click();

			WaitUtils.waitInvisibleSpinner(spinner, 40);

			// Fetching opportunities card status
			List<WebElement> status = WaitUtils.waitVisibilityOfDropdownElements(opportunityCardStatus, 40);

			// filtering 1st value i.e All from status dropdown and checking card status
			if (i == 0) {
				boolean flag = false;
				for (int j = 0; j < status.size(); j++) {
					String value = status.get(j).getText();

					if (value.equals(firstExpectedStatusValue) || value.equals(secondStatusValue)
							|| value.equals(thirdStatusValue) || value.equals(fourthStatusValue)) {
						flag = true;
					} else {
						flag = false;
						break;
					}

				}

				if (flag) {
					test.log(LogStatus.PASS, "status are displayed based on " + filterValue + " from status dropdwn");

				} else {
					test.log(LogStatus.FAIL, "values are not based on selected filter value " + filterValue);
				}
			}

			// filtering 2nd value from status dropdown and checking card status
			if (i == 1) {

				for (int j = 0; j < status.size(); j++) {
					String value = status.get(j).getText();

					if (value.equals(firstExpectedStatusValue)) {
						test.log(LogStatus.PASS,
								"status are displayed based on " + filterValue + " from status dropdwn");
					} else {
						test.log(LogStatus.FAIL, "status are NOT based on filter selection " + filterValue);
						break;
					}

				}

			}

			// filtering 3rd value from status dropdown and checking card status
			if (i == 2) {

				for (int j = 0; j < status.size(); j++) {
					String value = status.get(j).getText();

					if (value.equals(secondStatusValue)) {
						test.log(LogStatus.PASS,
								"status are displayed based on " + filterValue + " from status dropdwn");
					} else {
						test.log(LogStatus.FAIL, "status are NOT based on filter selection " + filterValue);
						break;
					}

				}

			}

			// filtering 4th value from status dropdown and checking card status
			if (i == 3) {

				for (int j = 0; j < status.size(); j++) {
					String value = status.get(j).getText();

					if (value.equals(thirdStatusValue)) {
						test.log(LogStatus.PASS,
								"status are displayed based on " + filterValue + " from status dropdwn");
					} else {
						test.log(LogStatus.FAIL, "status are NOT based on filter selection " + filterValue);
						break;
					}

				}

			}
			// filtering 5th value from status dropdown and checking card status
			if (i == 4) {

				for (int j = 0; j < status.size(); j++) {
					String value = status.get(j).getText();

					if (value.equals(fourthStatusValue)) {
						test.log(LogStatus.PASS,
								"status are displayed based on " + filterValue + " from status dropdwn");
					} else {
						test.log(LogStatus.FAIL, "status are NOT based on filter selection " + filterValue);
						break;
					}

				}

			}

		}

	}

	@Override
	public void additionalInformationDropdown() {

		WaitUtils.waitClickByRef(additionalInfoDD).click();

		// Fetching additional info dropdown elements
		List<WebElement> l1 =WaitUtils.waitVisibilityOfDropdownElements(additionalInfoDDList, 40);

		// fetching checkboxes of additional info dropdown
		List<WebElement> checkboxes = driver.findElements(additionalInfoDDCheckbox);

		logger.info("Additional Information dropdown has ");

		// iterating each element
		for (int i = 0; i < l1.size(); i++) {
			String actualValue = l1.get(i).getText();
			logger.info(actualValue);

			// checking additional info dropdown values with excel
			if (actualValue.equals(excelutil.getData("PipelinesModule", "AdditionalInfoDDTask", xlsname))) {
				// checking checkbox is present or not
				assertTrue(checkboxes.get(i).isDisplayed());
				logger.info(actualValue + " with checkbox is displayed");
				test.log(LogStatus.PASS, actualValue + " with checkbox is displayed");
			}
			// checking additional info dropdown values with excel
			else if (actualValue.equals(excelutil.getData("PipelinesModule", "AdditionalInfoDDCalendar", xlsname))) {
				// checking checkbox is present or not
				assertTrue(checkboxes.get(i).isDisplayed());
				logger.info(actualValue + " with checkbox is displayed");
				test.log(LogStatus.PASS, actualValue + " with checkbox is displayed");
			}
			// checking additional info dropdown values with excel
			else if (actualValue.equals(excelutil.getData("PipelinesModule", "AdditionalInfoDDNote", xlsname))) {
				// checking checkbox is present or not
				assertTrue(checkboxes.get(i).isDisplayed());
				logger.info(actualValue + " with checkbox is displayed");
				test.log(LogStatus.PASS, actualValue + " with checkbox is displayed");
			}
			// checking additional info dropdown values with excel
			else if (actualValue.equals(excelutil.getData("PipelinesModule", "AdditionalInfoDDHideTags", xlsname))) {
				// checking checkbox is present or not
				assertTrue(checkboxes.get(i).isDisplayed());
				logger.info(actualValue + " with checkbox is displayed");
				test.log(LogStatus.PASS, actualValue + " with checkbox is displayed");
			}
			// checking additional info dropdown values with excel
			else if (actualValue
					.equals(excelutil.getData("PipelinesModule", "AdditionalInfoDDHideAssignedTo", xlsname))) {
				// checking checkbox is present or not
				assertTrue(checkboxes.get(i).isDisplayed());
				logger.info(actualValue + " with checkbox is displayed");
				test.log(LogStatus.PASS, actualValue + " with checkbox is displayed");
			} else {
				test.log(LogStatus.FAIL, "Failed- " + actualValue);
			}

		}

	}

	@Override
	public void verifyNotesInCards() {

		// Selecting icon related pipeline
		selectPipeline(pipelineDropdownExpand, excelutil.getData("PipelinesModule", "IconPipelineName", xlsname));

		WaitUtils.waitInvisibleSpinner(spinner);

		// clicking additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();

		test.log(LogStatus.PASS, "Able to click on Additinal Information dropdown");

		// click checkbox of notes
		WaitUtils.waitClickByRef(noteInAdditionalInfo).click();
		test.log(LogStatus.PASS, "Note checkbox is clicked in Additional Information dropdown");

		// close additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();

		try {
			// To check notes icon in card
			if (driver.findElement(noteInCard).isDisplayed()) {
				test.log(LogStatus.PASS, "Note Icon is displayed");

				// To fetch all notes in cards
				List<WebElement> icons = WaitUtils.waitVisibilityOfDropdownElements(noteInCard, 40);

				// iterating clicking on each notes icon
				for (WebElement icon : icons) {
					WaitUtils.waitClickWebElement(icon, 40).click();
					WaitUtils.waitInvisibleSpinner(spinner);

					// To check notes tab in opportunity popup
					isdisplay(noteTabInEditOpportunity, "Notes tab is displayed", "notes tab is NOT displayed");
					assertTrue(driver.findElement(notesAddedText).isDisplayed());
					test.log(LogStatus.PASS, "Added notes is displayed");

					// Clicking delete icon
					WaitUtils.waitClickByRef(deleteIcon).click();
					test.log(LogStatus.PASS, "Able to click on delete icon and delete popup is displayed");

					// deleting notes
					delete();

					WaitUtils.waitInvisibleSpinner(spinner);

					// checking no items as notes is deleted
					WaitUtils.waitVisibilityByRef(noItems);
					isdisplay(noItems, "There are no items to display is displayed",
							"There are no items to display is NOT displayed");
					WaitUtils.waitClickByRef(opportunityCloseIcon).click();
					test.log(LogStatus.PASS, "Able to click on close icon and Edit Oppourtunity popup is closed");

				}

				// To check note icon in card is not present
				WaitUtils.waitInVisibleElement(noteInCard, 40);
				test.log(LogStatus.PASS,
						"Note icon is NOT displayed though note is checked in Additional Filter dropdown");

			}
		} catch (NoSuchElementException e) {
			// clicking on card to open edit opportunity popup
			WaitUtils.waitClickByRef(statusInCards).click();
			test.log(LogStatus.PASS, "Able to click on card and Edit Oppourtunity popup is displayed");
			WaitUtils.waitInvisibleSpinner(spinner);
			waitforelement(shortwaitvalue);

			// Switch to notes tab
			isdisplay(noteTabInEditOpportunity, "notes tab displayed", "notes tab not displayed");
			WaitUtils.waitClickByRef(noteTabInEditOpportunity).click();
			test.log(LogStatus.PASS, "Able to switch to Note tab");
			WaitUtils.waitInvisibleSpinner(spinner);

			// To check no items is displayed or not
			assertTrue(driver.findElement(noItems).isDisplayed(), "There are no items to display is NOT displayed");
			test.log(LogStatus.PASS, "Notes icon is NOT displayed when card does not have it");

			// To fetch notes text from excel
			String noteText = excelutil.getData("PipelinesModule", "write", xlsname);

			// enter text
			WaitUtils.waitClickByRef(noteTextboxInEditOppor).sendKeys(noteText);
			test.log(LogStatus.PASS, "Able to enter note in textbox");

			// click save
			WaitUtils.waitClickByRef(saveButton).click();
			test.log(LogStatus.PASS, "Able to click on save button and notes is added");
			WaitUtils.waitInvisibleSpinner(spinner);

			// click cross icon
			WaitUtils.waitClickByRef(opportunityCloseIcon).click();
			test.log(LogStatus.PASS, "Able to click on close icon and Edit Oppourtunity popup is closed");

			// To check note icon is present or not
			assertTrue(WaitUtils.waitClickByRef(noteInCard).isDisplayed());

			test.log(LogStatus.PASS,
					"Note Icon is displayed in card when Note checkbox is checked in Additional Information dropdown");

		}

	}

	@Override
	public void delete() {
		String deleteText = excelutil.getData("PipelinesModule", "writeDelete", xlsname);
		WaitUtils.waitClickByRef(typeDeleteTextbox).sendKeys(deleteText);
		test.log(LogStatus.PASS, "Able to enter delete");
		WaitUtils.waitClickByRef(submitButton).click();
		test.log(LogStatus.PASS, "Able to click on submit button and delete popup is closed");

	}



	@Override
	public void verifyHideTags() {

		// Selecting icon related pipeline
		selectPipeline(pipelineDropdownExpand, excelutil.getData("PipelinesModule", "IconPipelineName", xlsname));
		WaitUtils.waitInvisibleSpinner(spinner);

		// selecting status from status dropdown
		selectStatus(excelutil.getData("PipelinesModule", "statusValue", xlsname));
		WaitUtils.waitInvisibleSpinner(spinner);

		WaitUtils.waitClickByRef(additionalInfoDD).click();
		test.log(LogStatus.PASS, "Able to click on Additinal Information dropdown");

		// click hide tags checkbox
		WaitUtils.waitClickByRef(hideTagsInAdditionalInfo).click();

		test.log(LogStatus.PASS, "Able to check Hide Tags checkbox");

		// closing additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();

		try {
			// To check tags are displayed or not
			if (driver.findElement(tagsInCard).isDisplayed()) {
				test.log(LogStatus.FAIL,
						"Tags are still displayed though Hide tags checkbox is checked in Additional Info dropdown");
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.PASS,
					"Tags are hided after clicking on Hide Tags checkbox in Additional Intormation dropdown");

			// click additional info dropdown
			WaitUtils.waitClickByRef(additionalInfoDD).click();

			// click uncheckbox of hide tags
			WaitUtils.waitClickByRef(hideTagsInAdditionalInfo).click();
			test.log(LogStatus.PASS, "Able to uncheck Hide Tags checkbox");

			// closing dropdown
			WaitUtils.waitClickByRef(additionalInfoDD).click();

			// To check tags in cards
			if (WaitUtils.waitClickByRef(tagsInCard).isDisplayed()) {
				test.log(LogStatus.PASS,
						"Tags are dispalyed after unchecked Hide Tags checkbox in Additional Intormation dropdown");

			}

			else {
				test.log(LogStatus.FAIL,
						"Tags are NOT displayed though Hide tags checkbox is unchecked in Additional Info dropdown");

			}

		}

	}

	@Override
	public void verifyHideAssignedUser() {

		// Selecting icon related pipeline
		selectPipeline(pipelineDropdownExpand, excelutil.getData("PipelinesModule", "Status pipeline name", xlsname));
		WaitUtils.waitInvisibleSpinner(spinner);

		// selecting status from status dropdown
		selectStatus(excelutil.getData("PipelinesModule", "statusValue", xlsname));
		WaitUtils.waitInvisibleSpinner(spinner);

		// clicking additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();
		test.log(LogStatus.PASS, "Able to click on Additinal Information dropdown");

		// clicking checkbox of hide assigned user
		WaitUtils.waitClickByRef(hideAssignedUserInAdditionalInfo).click();
		test.log(LogStatus.PASS, "Able to click on checkbox of Hide Assigned User");

		// closing additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();

		List<WebElement> assignedUsers = driver.findElements(ownerAssignedUserInCards);

		if (assignedUsers.size() == 0) {
			test.log(LogStatus.PASS,
					"Assigned user is NOT displayed in opportunities cards as Hide Assignedd User checkbox is checked");
		}

		else {
			test.log(LogStatus.FAIL,
					"Assigned user is STILL displayed in opportunities cards though Hide Assigned user checkbox is checked");
		}

		// clicking additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();

		// unchecking checkbox of hide assigned user
		WaitUtils.waitClickByRef(hideAssignedUserInAdditionalInfo).click();
		test.log(LogStatus.PASS, "Able to uncheck Hide Assigned User checkbox");

		// closing additional info dropdown
		WaitUtils.waitClickByRef(additionalInfoDD).click();

		// Fetching assigned users as
		List<WebElement> assignedUsersNew =WaitUtils.waitVisibilityOfDropdownElements(ownerAssignedUserInCards, 40);

		boolean flag = false;

		logger.info("size is " + assignedUsers.size());

		try {
			// iterating each assigned user
			for (WebElement assignedUser : assignedUsersNew) {
				if (WaitUtils.waitClickWebElement(assignedUser, 40).isDisplayed()) {
					flag = true;
				}
			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL,
					"Assigned user is NOT displayed in opportunities cards though Hide Assigned user checkbox is Unchecked");
		}

		if (flag) {
			test.log(LogStatus.PASS,
					"Assigned user is displayed in opportunities cards as Hide Assignedd User checkbox is Unchecked");
		}

		else {
			test.log(LogStatus.FAIL,
					"Assigned user is NOT displayed in opportunities cards though Hide Assigned user checkbox is Unchecked");

		}

	}

	@Override
	public ArrayList<String> toGetPipelinesListFromPipelineDropdown(By ref1,By ref2) {
		WaitUtils.waitClickByRef(ref1).click();
		List<WebElement> pipelineList = WaitUtils.waitVisibilityOfDropdownElements(ref2, 40);

		ArrayList<String> pipelineDDArray = new ArrayList<String>();

		for (WebElement p : pipelineList) {
			pipelineDDArray.add(p.getText());
		}

		WaitUtils.waitClickByRef(ref1).click();

		pipelineDDArray.size();

		logger.info("Pipeline dropdown size is " + pipelineDDArray.size());
		logger.info("pipeline dropdown elements "+pipelineDDArray);

		return pipelineDDArray;
	}

	@Override
	public boolean verifyAlphabeticalOrder(By ref1, By ref2) {

		ArrayList<String> dropdownArray;
		ArrayList<String> copyList;

		WaitUtils.waitClickByRef(stageName);

		WaitUtils.waitClickByRef(ref1).click();
		WaitUtils.waitInvisibleSpinner(spinner);

		List<WebElement> elementsList = WaitUtils.waitVisibilityOfDropdownElements(ref2, 40);

		dropdownArray = new ArrayList<String>();

		for (WebElement ele : elementsList) {
			dropdownArray.add(ele.getText());
		}

		logger.info("Actual is " + dropdownArray);

		// created copy of above list
		copyList = new ArrayList<String>(dropdownArray);

		// Sorting list
		Collections.sort(copyList);

		logger.info("After sorted " + copyList);

		return copyList.equals(dropdownArray);

	}

	@Override
	public ArrayList<String> toGetElementListFromEachPage(By ref) {
		// Fetch elements
		List<WebElement> elements = WaitUtils.waitVisibilityOfDropdownElements(ref, 50);
		ArrayList<String> pageEleArray=new ArrayList<String>();

		// iterating each element and adding to arraylist
		for (WebElement ele : elements) {
			pageEleArray.add(ele.getText());
			
		}
		logger.info("size is " +pageEleArray.size());
		logger.info(pageEleArray);
		
		elementsArrayList.addAll(pageEleArray);
		
		logger.info("size is " +elementsArrayList.size());
		logger.info(elementsArrayList);
      return elementsArrayList;
		
	}

	@Override
	public ArrayList<String> toGetElementsTillLastPage(By ref) {
		elementsArrayList.clear();
		while (true) {
			
			toGetElementListFromEachPage(ref);
			
			// loop breaks if class has disabled attribute
			if (driver.findElement(nextPageArrow).getAttribute("class").contains("disabled")) {
				
				break;
			}

			// if class does not has disabled attribute then it runs else block
			else {
				
				WaitUtils.waitClickByRef(nextPageArrow).click();
				WaitUtils.waitInvisibleSpinner(spinner);

			}

		}

		 return elementsArrayList;
	}

	@Override
	public ArrayList<String> toGetAssignedUserListFromOwnerDropdown(By ref1,By ref2) {
		WaitUtils.waitClickByRef(ref1).click();
		List<WebElement> pipelineList = WaitUtils.waitVisibilityOfDropdownElements(ref2, 40);

		ArrayList<String> assignedUserDDArray = new ArrayList<String>();

		for (WebElement p : pipelineList) {
			assignedUserDDArray.add(p.getText());
		}

		logger.info("Assigned User dropdown size is " + assignedUserDDArray.size());
		logger.info("assignedUserDDArray is " + assignedUserDDArray);
		
		assignedUserDDArray.remove("All Users");
		
		logger.info("Assigned User dropdown size is after removing " + assignedUserDDArray.size());
		logger.info("assignedUserDDArray is after removing " + assignedUserDDArray);
		
		WaitUtils.waitClickByRef(ref1).click();

		return assignedUserDDArray;
	}

	@Override
	public void verifyCreateOpportunityPopup() {
		
		clickNewOpportunity();
		
		WaitUtils.waitInvisibleSpinner(spinner);
		
		//isdisplay(contactInformationHeaderFromOpporPopup,"Contact Information heading is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(contactInformationHeaderFromOpporPopup).isDisplayed(),"Contact Information heading is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Contact Information header is displayed in Create Opportunity popup");
		
		//isdisplay(contactNameLabelFromOpporPopup,"Contact Name label is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(contactNameLabelFromOpporPopup).isDisplayed(),"Contact Name label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Contact Name label is displayed in Create Opportunity popup");
		
		//isdisplay(businessNameLabelFromOpporPopup,"Business Name label is displayed in Create Opportunity popup", "Business Name label is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(businessNameLabelFromOpporPopup).isDisplayed(),"Business Name label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Business Name label is displayed in Create Opportunity popup");
		
		//isdisplay(emailLabelFromOpporPopup,"Email label is displayed in Create Opportunity popup", "Email label is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(emailLabelFromOpporPopup).isDisplayed(),"Email label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Email label is displayed in Create Opportunity popup");
		
		//isdisplay(phoneNumberLabelFromOpporPopup,"Phone Number label is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(phoneNumberLabelFromOpporPopup).isDisplayed(),"Phone Number label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Phone Number label is displayed in Create Opportunity popup");
		
		//isdisplay(extensionLabelFromOpporPopup,"Extension is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(extensionLabelFromOpporPopup).isDisplayed(),"Extension is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Extension is displayed in Create Opportunity popup");
		
		//isdisplay(opportunityInformationHeaderFromOpporPopup,"Opportunity Information heading is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(tagsLabelFromOpporPopup).isDisplayed(),"Tags label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Tags label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(contactNameInputFromOpporPopup).isDisplayed(),"Contact Name textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Contact Name textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(businessNameInputFromOpporPopup).isDisplayed(),"Business Name textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Business Name textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(emailInputFromOpporPopup).isDisplayed(),"Email textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Email textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(phoneNumberInputFromOpporPopup).isDisplayed(),"Phone Number textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Phone Number textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(extnNumberInputFromOpporPopup).isDisplayed(),"Extension textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Extension textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(tagsLabelFromOpporPopup).isDisplayed(),"Tags textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Tags textbox is displayed in Create Opportunity popup");
		
		//isdisplay(opportunityInformationHeaderFromOpporPopup,"Opportunity Information heading is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(opportunityInformationHeaderFromOpporPopup).isDisplayed(),"Opportunity Information header is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Opportunity Information header is displayed in Create Opportunity popup");
		
		//isdisplay(opportunityInformationHeaderFromOpporPopup,"Opportunity Information heading is displayed in Create Opportunity popup", "Contact Information heading is NOT displayed in Create Opportunity popup");
		assertTrue(driver.findElement(opportunityNameLabelFromOpporPopup).isDisplayed(),"Opportunity Name label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Opportunity Name label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(pipelineLabelFromOpporPopup).isDisplayed(),"Pipeline label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Pipeline label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(stageLabelFromOpporPopup).isDisplayed(),"Stage label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Stage label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(leadValueLabelFromOpporPopup).isDisplayed(),"Lead Value label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Lead Value label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(assignedUserLabelFromOpporPopup).isDisplayed(),"Assigned User label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Assigned User label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(sourceLabelFromOpporPopup).isDisplayed(),"Source label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Source label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(statusLabelFromOpporPopup).isDisplayed(),"Status label is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Status label is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(opportunityNameInputFromOpporPopup).isDisplayed(),"Opportunity Name is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Opportunity Name textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(selectPipelineDDExpandFromOpporPopup).isDisplayed(),"Pipeline dropdown is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Pipeline dropdown is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(selectStageDDExpandFromOpporPopup).isDisplayed(),"Stage dropdown is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Stage dropdown is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(leadValueInputFromOpporPopup).isDisplayed(),"Lead Value Textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Lead Value Textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(assignedUserDropdownFromOpporPopup).isDisplayed(),"Assigned User dropdown is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Assigned User dropdown is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(sourceInputFromOpporPopup).isDisplayed(),"Source textbox is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Source textbox is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(selectStatusDDExpandFromOpporPopup).isDisplayed(),"Status dropdown is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Status dropdown is displayed in Create Opportunity popup");
	
		assertTrue(driver.findElement(cancelButton).isDisplayed(),"Cancel button is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Cancel button is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(saveButton).isDisplayed(),"Save button is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Save button is displayed in Create Opportunity popup");
		
		click(opportunityCloseIcon, "Able to click on close icon and Create Opportunity popup is closed", "Unable to click on close icon and Create Opportunity popup is NOT closed");
		
		WaitUtils.waitInvisibleSpinner(spinner);
		
		clickNewOpportunity();
		
		click(saveButton, "Able to click on Save button", "Unable to click on Save button");
		
		assertTrue(driver.findElement(contactNameErrorFromOpporPopup).isDisplayed(),"Contact Name is Required error is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Contact Name is Required error is displayed in Create Opportunity popup");
		
		assertTrue(driver.findElement(opporNameErrorFromOpporPopup).isDisplayed(),"Opportunity Name is Required  error is NOT displayed in Create Opportunity popup");
		test.log(LogStatus.PASS, "Opportunity Name is Required error is displayed in Create Opportunity popup");
		
		click(cancelButton, "Able to click on Cancel button and Create Opportunity popup is closed", "Unable to click on Cancel button and Create Opportunity popup is NOT closed");
	
	}
	

	@Override
	public ArrayList<String> toGetStatusFromStatusDropdown(By ref1,By ref2) {
		
		List<WebElement> pipelineList;
		WaitUtils.waitClickByRef(ref1).click();
		
		try {
		 pipelineList = WaitUtils.waitVisibilityOfDropdownElements(ref2, 40);
		}
		
		catch(StaleElementReferenceException e)
		{
			pipelineList = WaitUtils.waitVisibilityOfDropdownElements(ref2, 40);

		}
		
		ArrayList<String> statusArray = new ArrayList<String>();

		for (WebElement p : pipelineList) {
			statusArray.add(p.getText());
		}

		logger.info("Status dropdown size is " + statusArray.size());
		logger.info("Status dropdown has " + statusArray);
		
		WaitUtils.waitClickByRef(ref1).click();

		return statusArray;
	}

}
