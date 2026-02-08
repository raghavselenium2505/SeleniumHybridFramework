package com.gps.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface settingsPipelinePage {
	public String randomGenerator();

	public void clickPipelinesTab();

	public void verifyFilter();

	public void selectFilter(String filterOption);

	public void verifyAlphabeticalOrder(String passValue, String failValue);

	public void pipelinesCount();

	public int pipelinesCountFromHeader();

	public void validSearchFunctionality(By ref, String colName, String passValue, String failValue, String passValue1,
			String failValue1);

	public void verifyActiveInactivePipelineBoth();


	public void pagination();

	public void selectPageSizeFromDropdown();

	public void verifyNextPageArrow();

	public void verifyPreviousPageArrow();

	public void verifyLastPageArrow();

	public void verifyFirstPageArrow();

	public void verifySelectPages();

	public void goToPage();

	public void clickAddPipeline();

	public void addPipelineFields();

	public void addPipelineMandatoryFieldsErrors();

	public void pipelineNameTextboxField();

	public void pipelineNameFieldValidation();

	public void verifyPipelineNameLength();

	public void stageNameTextboxField();

	public void stageNameFieldValidation();

	public void verifyStageNameLength();

	public void stageDragDrop();

	public void deleteSingleStagePopup();

	public void verifyCancelCloseAddPopupWithDetails(By ref, String str);

	public void verifyCancelCloseAddPopupWithOutDetails(By ref, String str);

	public void addPipelineInPipelinesModule();

	public void verifyAddPipeline();

	public void editPipeline();

	public void editPipelineErrorsPlaceholder();

	public void deletePipelinePopup();

	public void deleteWithoutSubmit();

	public void verifyDeletePipeline();

	public void verifyMaxStages();

	public void deleteStage();

	public void settingsGearIcon();

	public void checkResetFields();

	public void checkDisplayLabels();

	public void statusFieldValidation();

	public void checkResetNo();

	public void checkResetClose();

	public void checkCustomCancel();

	public void checkResetYesCustomCancel();

	public void checkStatusNameCustomCloseIcon();

	public void checkUpdateStatusNames();

	public void pipelineActiveInActive();
	
	

	public void checkMixStatusNames();

	public ArrayList<String> toGetElementListFromEachPage(By ref);

	public ArrayList<String> toGetElementsTillLastPage(By ref);

	public ArrayList<String> toGetUpdateStatusNames();

	public void InvalidSearchFunctionality(By ref, String colName, String passValue, String failValue,
			String passValue1, String failValue1);

}

public class GPS_SettingsPipelinesPage extends TestBase implements settingsPipelinePage {

	public ArrayList<String> elementsArrayList = new ArrayList<String>();
	int pipelineNamesCount;
	int count1;
	static boolean toggleStatus;
	static boolean toggleProgress;

	String currentPageNumber;
	static String pipelineNameCreated;
	static String firstStatusName;
	static String secondStatusName;
	static String thirdStatusName;
	static String fourthStatusName;

	public By pipelinesTab = By.xpath("//span[contains(@class,'tabview') and contains(text(),'Pipelines')]");
	public By pipelinesHeading = By.xpath("//h4[@aria-label='pipelines']");
	public By pipelinesCount = By.xpath("//span[contains(text(),'(')]");
	public By addPipelineButton = By.xpath("//span[contains(text(),'Add Pipeline')]");
	public By nameColumn = By.xpath("//th[contains(text(),'Name')]");
	public By pipelineName = By.xpath("//tbody//p[contains(@class,'name')]");
	public By statusColumn = By.xpath("//th[contains(text(),'Status')]");
	public By toggle = By.xpath("//span[contains(@class,'slider')]");
	public By editIcon = By.xpath("//i[contains(@class,'edit')]");
	public By deleteIcon = By.xpath("//i[contains(@aria-label,'delete')]");

	public By searchInPipelinesTab = By.id("search");

	public By filter = By
			.xpath("//div[contains(@class,'pipelines-component ')]//span[contains(@class,'trigger-icon')]");
	public By searchInFilter = By.xpath("//input[contains(@autocomplete,'off')]");
	public By filterExpand = By.xpath("//ul[contains(@class,'dropdown')]//span");
	public By defaultFilterValue = By.xpath("//span[contains(text(),'Active')]");
	public By noMatchFound = By.xpath("//h3[contains(text(),'No Match Found')]");

	public By togglePipelineNameP3 = By.xpath("//p[contains(text(),'p3')]/following::span[contains(@class,'slider')]");
	// public By togglePipelineP3Status =
	// By.xpath("//p[contains(text(),'p3')]/following::input[contains(@id,'Switch')]");
	public By togglePipelineP3Status = By
			.xpath("//p[contains(text(),'p3')]//following::span[contains(@class,'switch')]");
	public By pipelineNameP3 = By.xpath("//p[contains(text(),'p3')]");
	public By toggleClick = By.xpath("//span[contains(@class,'slider')]");
	public By toggleStatusChecked = By.xpath("//div[contains(@class,'checked')]");
	public By toggleStatusNOChecked = By.xpath("//div[contains(@class,'inputswitch')]");
	public By disableTextInActivePopup = By.xpath("//h3[contains(text(),'disable')]");
	public By enableTextInActivePopup = By.xpath("//h3[contains(text(),'enable')]");

	public By inActivePopup = By
			.xpath("//h3[contains(text(),'This will disable all opportunities mapped to the pipeline')]");
	public By typeInActive = By.xpath("//input[@placeholder='Type In-Active']");
	public By typeActive = By.xpath("//input[@placeholder='Type Active']");
	public By closeIcon = By.xpath("//a[contains(@class,'titlebar-close')]/span");
	public By submitButton = By.xpath("//span[contains(text(),'Submit')]");

	public By pagination = By.xpath("//div[contains(@class,'paginator')]");
	public By pageDropdownExpand = By
			.xpath("//a[contains(@class,'paginator')]//following::span[contains(@class,'trigger-icon')]");
	public By pageDropdownValues = By.xpath("//ul//p-dropdownitem//span");
	public By goToText = By.xpath("//p[text()='Go To']");
	public By goToInputField = By.xpath("//input[@id='goto']");
	public By firstPageArrow = By.xpath("//a[contains(@class,'first')]");
	public By previousPageArrow = By.xpath("//a[contains(@class,'prev')]");
	public By nextPageArrow = By.xpath("//a[contains(@class,'next')]");
	public By lastPageArrow = By.xpath("//a[contains(@class,'last')]");
	public By totalPagesList = By.xpath("//span[contains(@class,'pages')]/a");
	public By currentPage = By.xpath("//a[contains(@class,'state-active')]");

	public By addPipelineHeading = By.xpath("//h3[contains(text(),'Add Pipeline')]");
	public By pipelineNameTextInAddPopline = By.xpath("//label[contains(text(),'Pipeline Name*')]");
	public By duplicateOppourtnity = By.xpath("//label[contains(text(),'Allow Duplicate Opportunity')]");
	public By duplicateOpportunityText=By.xpath("//span[contains(text(),'checkbox')]");
	public By stageName = By.xpath("//label[contains(text(),'Stage Name*')]");
	public By pipelineNameTextbox = By.id("pipelineName");
	public By stageNameTextbox = By.id("stageName");
	public By blankStageTextbox = By.xpath("//input[contains(@class,'ng-invalid')]");
	public By deleteIconInAddPipeline = By.xpath("//a[@title='Delete']");
	public By addStageButton = By.xpath("//span[contains(text(),'Add Stage')]");
	public By cancelButton = By.xpath("//span[contains(text(),'Cancel')]");
	public By saveButton = By.xpath("//span[contains(text(),'Save')]");

	public By pipelineRequireError = By.xpath("//span[contains(text(),'Pipeline name is required')]");
	public By stageRequireError = By.xpath("//span[contains(text(),'Stage name is required')]");
	public By specialCharacterError = By.xpath("//span[contains(text(),'Enter Alpha numeric with only _ and -')]");

	public By dragIconAfterClickAddStage = By.xpath("//div[contains(@class,'pristine')]//div[@id='HANDLEDRAG']");
	public By newStageAfterClickAddStage = By.xpath("//div[contains(@class,'pristine')]//input[@id='stageName']");
	public By deleteForNewStageAfterClickAddStage = By.xpath("//div[contains(@class,'pristine')]//a[@title='Delete']");
	public By previousStageNameTextbox = By.xpath("//div[contains(@class,'ng-valid')]//input[@id='stageName']");

	public By sourceDrag = By.xpath("(//div[@id='HANDLEDRAG'])[1]");
	public By destinationDrag = By.xpath("(//div[@id='HANDLEDRAG'])[2]");
	public By stageFirst = By.xpath("(//input[@id='stageName'])[1]");
	public By stageSecond = By.xpath("(//input[@id='stageName'])[2]");
	public By deleteStageSecond = By.xpath("(//a[@title='Delete'])[2]");
	public By stage = By.xpath("//input[@id='stageName']");

	public By deleteStageText = By.xpath("//span[contains(text(),'Delete Stage')]");
	public By needOneStage = By.xpath("//p[contains(text(),'You need atleast 1 stage in the pipeline')]");
	public By deleteStageCloseIcon = By
			.xpath("//span[contains(text(),'Delete Stage')]//following::a[contains(@class,'titlebar-close')]");
	public By okButton = By.xpath("//span[contains(text(),'Ok')]");

	public By toastSummaryCreatePipeline = By.xpath("//div[contains(text(),'Create Pipeline')]");
	public By toastDetailCreatePipeline = By.xpath("//div[contains(text(),'Pipeline created successfully')]");
	public By toastIcon = By.xpath("//span[contains(@class,'toast-icon')]");
	public By toastClose = By.xpath("//a[contains(@class,'toast-close')]");
	public By newPipelineName = By.xpath("//p[contains(@class,'fs-name')]");

	public By editPipelineHeading = By.xpath("//h3[contains(text(),'Edit Pipeline')]");
	public By updateButton = By.xpath("//span[contains(text(),'Update')]");

	public By toastSummaryUpdatePipeline = By.xpath("//div[contains(text(),'Update Pipeline')]");
	public By toastDetailUpdatePipeline = By.xpath("//div[contains(text(),'Pipeline updated successfully')]");
	public By deleteStageMsgText = By.xpath("//p[contains(text(),'Are you sure you want to delete this stage?')]");
	public By deletePleaseEnterText = By.xpath("//h3[contains(text(),'Please enter word')]");
	public By deleteTextBox = By.xpath("//input[contains(@placeholder,'Type Delete')]");

	public By deleteEnterCorrectTextInToast = By.xpath("//div[contains(text(),'Enter Correct Word')]");
	public By deleteEnterCorrectToastIcon = By.xpath("//span[contains(@class,'toast-icon')]");
	public By deleteConfirmation = By.xpath("//span[contains(text(),'Confirmation text is required')]");
	public By noItems = By.xpath("//h3[contains(text(),'There are no items to display')]");

	public By gearIcon = By.xpath("//button[contains(@class,'gear')]");
	public By statusLabelHeading = By.xpath("//h3[contains(text(),'Status Label Change')]");
	public By notes = By.xpath("//p[contains(text(),'Note')]");
	public By resetButton = By.xpath("//span[contains(text(),'Reset')]");
	public By defaultLabel = By.xpath("//label[contains(text(),'Default Status Labels')]");
	public By displayLabel = By.xpath("//label[contains(text(),'Display Status Labels')]");
	public By defaultLabelValues = By.xpath("//input[contains(@formcontrolname,'defaultStatusName')]");
	public By displayLabelValues = By.xpath("//input[contains(@formcontrolname,'customStatusName')]");
	public By statusRequireError = By.xpath(" //div[contains(text(),'Status is required')]");
	public By toastSummaryStatusLabelChanges = By.xpath("//div[contains(text(),'Status Label Changes')]");
	public By toastDetailStatusLabelChanges = By
			.xpath("//div[contains(text(),'Status label changes saved successfully')]");
	public By applyDefaultLabel = By
			.xpath("//p[contains(text(),'Are you sure you want to apply Default Status Labels ?')]");
	public By NoButton = By.xpath("//span[text()='No']");
	public By YesButton = By.xpath("//span[text()='Yes']");
	public By closeIconReset = By.xpath("(//a[contains(@class,'titlebar-close')])[2]");
	public By activePage = By.xpath("//a[contains(@class,'paginator') and contains(@class,'active')]");
	public By spinner = By.xpath("//div[contains(@class,'spinner')]");

	@Override
	public String randomGenerator() {
		Random r = new Random();
		int x = r.nextInt(10000);
		int timestamp = (int) (System.currentTimeMillis() % 10000);
		String value = String.valueOf(x) + String.valueOf(timestamp);
		logger.info("Numbr is " + value);
		return value;
	}

	@Override
	public void clickPipelinesTab() {
		// Verify pipelines tab
		isdisplay(pipelinesTab, "Pipelines Tab is displayed in Settings", "Pipelines Tab is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(pipelinesTab).isDisplayed(), "Pipelines Tab is NOT displayed");

		// click pipelines Tab
		WaitUtils.waitClickByRef(pipelinesTab).click();
		test.log(LogStatus.PASS, "Able to click on Pipelines Tab and Pipelines page is displayed");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	public void verifyFilter() {

		try {
			// click on filter
			click(filter, "Able to click on filter", "Unable to click on filter");

			// Verify search in filter
			WebElement searchEleInFilter = WaitUtils.waitClickByRef(searchInFilter);
			isdisplay(searchInFilter, "Search is displayed in filter", "Search is NOT displayed in filter");
			assertTrue(searchEleInFilter.isDisplayed(), "Search is NOT displayed in filter");

			// Verify dropdown values
			WaitUtils.waitClickByRef(filterExpand);
			List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(filterExpand, 20);

			logger.info("Filter has " + l1.size() + " options");
			test.log(LogStatus.PASS, "Filter has " + l1.size() + "options");

			for (WebElement ele : l1) {
				String filterValue = ele.getText();

				if (filterValue.equalsIgnoreCase("All")) {
					logger.info("All is displayed in filter");
					test.log(LogStatus.PASS, "All is displayed in filter");
				}

				else if (filterValue.equalsIgnoreCase("Active")) {
					logger.info("Active is displayed in filter");
					test.log(LogStatus.PASS, "Active is displayed in filter");
				}

				else if (filterValue.equalsIgnoreCase("Inactive")) {
					logger.info("Inactive is displayed in filter");
					test.log(LogStatus.PASS, "Inactive is displayed in filter");
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
	// To select specific filter option and to check status of pipeline toggle
	public void selectFilter(String filterOption) {
		// clicking on filter to expand
		click(filter, "Able to click on filter", "Unable to click on filter");

		// Select specific value from filter
		click(By.xpath("//span[contains(text(),'" + filterOption + "')]"),
				"Able to select " + filterOption + " from filter", "Unable to select from filter");

	}

	@Override
	public void verifyAlphabeticalOrder(String passValue, String failValue) {
		try {

			ArrayList<String> pipelineNamesList = toGetElementsTillLastPage(pipelineName);
			String pipelineNames[] = new String[pipelineNamesList.size()];

			for (int i = 0; i < pipelineNames.length; i++) {
				pipelineNames[i] = pipelineNamesList.get(i);
			}

			boolean status = true;
			for (int i = 1; i < pipelineNames.length; i++) {
				// compares current with previous
				if (pipelineNames[i].compareToIgnoreCase(pipelineNames[i - 1]) < 0) {
					status = false;
					break;
				}
			}

			/*
			 * List<WebElement> l1 = driver.findElements(pipelineName); String
			 * pipelineNames[] = new String[l1.size()];
			 * 
			 * for (int i = 0; i < pipelineNames.length; i++) { pipelineNames[i] =
			 * l1.get(i).getText(); }
			 * 
			 * boolean status = true; for (int i = 1; i < pipelineNames.length; i++) { //
			 * compares current with previous if
			 * (pipelineNames[i].compareToIgnoreCase(pipelineNames[i - 1]) < 0) { status =
			 * false; break;
			 * 
			 * } }
			 */

			if (status) {
				test.log(LogStatus.PASS, passValue);
				logger.info(passValue);
			} else {
				test.log(LogStatus.FAIL, failValue);
				logger.info(failValue);
			}
		}

		catch (Exception e) {
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
	public void pipelinesCount() {
		String value = WaitUtils.waitClickByRef(pipelinesCount).getText();
		String count = value.replaceAll("[^0-9]", "");

		count1 = Integer.parseInt(count);

		test.log(LogStatus.PASS, "Pipeline count from heading is " + count1);
		logger.info("Pipeline count from heading is " + count1);

		List<WebElement> pipelineNamesList = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 10);

		pipelineNamesCount = pipelineNamesList.size();

		test.log(LogStatus.PASS, "Pipeline count from search result is " + pipelineNamesCount);
		logger.info("Pipeline count from search result is " + pipelineNamesCount);

		// return count1;
	}

	@Override
	public int pipelinesCountFromHeader() {
		String value = WaitUtils.waitClickByRef(pipelinesCount).getText();
		String count = value.replaceAll("[^0-9]", "");

		count1 = Integer.parseInt(count);

		test.log(LogStatus.PASS, "Pipeline count from header is " + count1);
		logger.info("Pipeline count from heading is " + count1);

		return count1;

	}

	@Override
	public void validSearchFunctionality(By ref, String colName, String passValue, String failValue, String passValue1,
			String failValue1) {
		try {

			// Search pipeline name
			sendkeys(
					searchInPipelinesTab, excelutil.getData("Settings_PipelinesTab", colName, xlsname), "Able to enter "
							+ excelutil.getData("Settings_PipelinesTab", colName, xlsname) + " in Search Textbox",
					"Unable to enter in Search Testbox");

			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

			WaitUtils.waitClickByRef(ref, 20);

			waitforelement(extraverylongwaitvalue);

			pipelinesCount();

			if (pipelineNamesCount == count1) {
				test.log(LogStatus.PASS, passValue);
				logger.info(passValue);

			}

			else {
				test.log(LogStatus.FAIL, failValue);
				logger.info(failValue);
			}

			String result = WaitUtils.waitClickByRef(ref).getText();

			if (result.contains(excelutil.getData("Settings_PipelinesTab", colName, xlsname))) {
				test.log(LogStatus.PASS,
						"Entered Search Value is " + excelutil.getData("Settings_PipelinesTab", colName, xlsname));
				logger.info("Entered Search Value is " + excelutil.getData("Settings_PipelinesTab", colName, xlsname));
				logger.info("Pipeline name in search result is " + result);
				test.log(LogStatus.PASS, "Pipeline name in search result is " + result);
				test.log(LogStatus.PASS, passValue1);
				logger.info(passValue1);
			}

			else {
				test.log(LogStatus.FAIL, failValue1);
				logger.info(failValue1);
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
	public void InvalidSearchFunctionality(By ref, String colName, String passValue, String failValue,
			String passValue1, String failValue1) {
		try {

			// Search pipeline name
			sendkeys(
					searchInPipelinesTab, excelutil.getData("Settings_PipelinesTab", colName, xlsname), "Able to enter "
							+ excelutil.getData("Settings_PipelinesTab", colName, xlsname) + " in Search Textbox",
					"Unable to enter in Search Testbox");

			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

			String result = WaitUtils.waitClickByRef(ref).getText();

			String value = WaitUtils.waitClickByRef(pipelinesCount).getText();
			String count = value.replaceAll("[^0-9]", "");

			count1 = Integer.parseInt(count);

			test.log(LogStatus.PASS, "Pipeline count from heading is " + count1);
			logger.info("Pipeline count from heading is " + count1);

			if (count1 == 0) {
				test.log(LogStatus.PASS, passValue);
				logger.info(passValue);

			}

			else {
				test.log(LogStatus.FAIL, failValue);
				logger.info(failValue);
			}

			if (result.contains("There are no items to display")) {
				test.log(LogStatus.PASS,
						"Entered Search Value is " + excelutil.getData("Settings_PipelinesTab", colName, xlsname));
				logger.info("Entered Search Value is " + excelutil.getData("Settings_PipelinesTab", colName, xlsname));
				isdisplay(noItems, passValue1, failValue1);
				assertTrue(driver.findElement(noItems).isDisplayed(), failValue1);

			}

			else {
				test.log(LogStatus.FAIL, failValue1);
				logger.info(failValue1);
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
	// To make pipeline status active and inactive and checking filter values are
	// correctly displayed
	public void verifyActiveInactivePipelineBoth() {

		// Filter with active to see active pipelines
		selectFilter("All");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		WaitUtils.waitClickByRef(searchInPipelinesTab).sendKeys("p3");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner, 40);

		waitforelement(extraverylongwaitvalue);

		// To click on toggle to open popup
		WaitUtils.waitClickByRef(toggleClick).click();
		test.log(LogStatus.PASS, "Able to click on Slider and popup is displayed");

		boolean toggleStatus;

		try {
			// if pipeline is Active then clicking on slider contains disable text
			// To makes pipeline inactive
			// if it is true means it is active so below code makes pipeline inactive

			toggleStatus = WaitUtils.waitVisibilityByRef(disableTextInActivePopup,6).isDisplayed();

			// to get slider status
			logger.info("status is " + toggleStatus);

			if (toggleStatus) {

				// test.log(LogStatus.PASS, "InActive popup is displayed");
				// String expectedPipelineName=driver.findElement(pipelineNameP3).getText();

				// To check In active pop up Text1
				isdisplay(
						By.xpath("//h3[contains(text(),'"
								+ excelutil.getData("Settings_PipelinesTab", "InactivePopUpText", xlsname) + "')]"),
						"This will disable all opportunities mapped to the pipeline is displayed in In Active Pop Up",
						"This will disable all opportunities mapped to the pipeline NOT displayed");

				assertTrue(WaitUtils
						.waitClickByRef(By.xpath("//h3[contains(text(),'"
								+ excelutil.getData("Settings_PipelinesTab", "InactivePopUpText", xlsname) + "')]"))
						.isDisplayed(), "This will disable all opportunities mapped to the pipeline NOT displayed");

				// To check In active pop up Text2
				isdisplay(
						By.xpath("//h3[contains(text(),'"
								+ excelutil.getData("Settings_PipelinesTab", "PopUpText", xlsname) + "')]"),
						"Please enter word 'In-Active' to continue is displayed in In Active Pop Up",
						"Please enter word 'In-Active' to continue is NOT displayed");
				assertTrue(WaitUtils
						.waitClickByRef(By.xpath("//h3[contains(text(),'"
								+ excelutil.getData("Settings_PipelinesTab", "InactivePopUpText", xlsname) + "')]"))
						.isDisplayed(), "Please enter word 'In-Active' to continue is NOT displayed");

				// To type InActive
				sendkeys(typeInActive, excelutil.getData("Settings_PipelinesTab", "TypeInActive", xlsname),
						"Able to enter In-Active in Pop Up", "Unable to enter In-Active in Pop Up");

				// Click submit and so pipeline becomes inactive
				click(submitButton, "Able to click on Submit button.Pop Up is closed and Pipeline is inactivated",
						"Unable to click on Submit button");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				// Filter with Inactive to see inactive pipelines
				selectFilter("Inactive");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				// if pipeline is inactive then clicking on toggle open popup contains enable
				// text
				// Click on toggle to open active popup
				WaitUtils.waitClickByRef(toggleClick).click();
				// click(toggleClick, "Able to click on toggle and Active PopUp is
				// displayed","Unable to click on toggle");

				toggleStatus = WaitUtils.waitClickByRef(enableTextInActivePopup,6).isDisplayed();

				logger.info("status is after making pipeline inactive " + toggleStatus);

				if (toggleStatus) {
					logger.info("InActive Pipelines are displayed");
					test.log(LogStatus.PASS, "InActive Pipelines are displayed");

					// Type Active in Active Pop Up
					sendkeys(typeActive, excelutil.getData("Settings_PipelinesTab", "TypeActive", xlsname),
							"Able to enter Active in PopUp", "Unable to enter");

					// Click Submit button
					click(submitButton, "Able to click on Submit.PopUp is closed and Pipeline is Activated",
							"Unable to click on Submit button");

					WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

					// To filter with Active
					selectFilter("Active");

					WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

					// Click on toggle to open active popup
					WaitUtils.waitClickByRef(toggleClick).click();

					// To fetch toggle status
					// if pipeline is active after clicking on slider opened popup has disable text
					toggleStatus = WaitUtils.waitClickByRef(disableTextInActivePopup,6).isDisplayed();
					logger.info("Status is after updating pipeline active " + toggleStatus);

					if (toggleStatus) {
						logger.info("Active Pipelines are displayed");
						test.log(LogStatus.PASS, "Active Pipelines are displayed");
					}

					else {
						logger.info("Active Pipelines are NOT displayed");
						test.log(LogStatus.PASS, "Active Pipelines are NOT displayed");
					}

				}

				else {
					logger.info("InActive Pipelines are NOT displayed");
					test.log(LogStatus.FAIL, "InActive Pipelines are NOT displayed");
				}

			}

		}
		// Below code makes inactive pipeline to active
		catch (TimeoutException e) {

			// To check 1st text in active pop up
			isdisplay(
					By.xpath("//h3[contains(text(),'"
							+ excelutil.getData("Settings_PipelinesTab", "ActivePopText", xlsname) + "')]"),
					"This will enable all opportunities mapped to the pipeline is displayed in Active PopUp",
					"This will enable all opportunities mapped to the pipeline is NOT displayed");

			assertTrue(
					WaitUtils
							.waitClickByRef(By.xpath("//h3[contains(text(),'"
									+ excelutil.getData("Settings_PipelinesTab", "ActivePopText", xlsname) + "')]"))
							.isDisplayed(),
					"This will enable all opportunities mapped to the pipeline is NOT displayed");

			// To check 2nd text in active pop up
			isdisplay(
					By.xpath("//h3[contains(text(),'" + excelutil.getData("Settings_PipelinesTab", "PopUpText", xlsname)
							+ "')]"),
					"Please enter word 'Active' to continue is displayed in Active PopUp",
					"Please enter word 'Active' to continue is NOT displayed");

			assertTrue(
					WaitUtils
							.waitClickByRef(By.xpath("//h3[contains(text(),'"
									+ excelutil.getData("Settings_PipelinesTab", "PopUpText", xlsname) + "')]"))
							.isDisplayed(),
					"Please enter word 'Active' to continue is NOT displayed");

			// Type Active in Active Pop Up
			sendkeys(typeActive, excelutil.getData("Settings_PipelinesTab", "TypeActive", xlsname),
					"Able to enter Active in PopUp", "Unable to enter");

			// Click Submit button
			click(submitButton, "Able to click on Submit.PopUp is closed and Pipeline is Activated",
					"Unable to click on Submit button");

			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

			// To filter with Active
			selectFilter("Active");

			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

			// Click on toggle to open active popup
			WaitUtils.waitClickByRef(toggleClick).click();

			// To fetch toggle status
			// if pipeline is active after clicking on slider opened popup has disable text
			toggleStatus = WaitUtils.waitClickByRef(disableTextInActivePopup,6).isDisplayed();
			logger.info("Status is after updating pipeline active " + toggleStatus);

			if (toggleStatus) {
				logger.info("Active Pipelines are displayed");
				test.log(LogStatus.PASS, "Active Pipelines are displayed");

				// To type InActive
				sendkeys(typeInActive, excelutil.getData("Settings_PipelinesTab", "TypeInActive", xlsname),
						"Able to enter In-Active in Pop Up", "Unable to enter In-Active in Pop Up");

				// Click submit and so pipeline becomes inactive
				click(submitButton, "Able to click on Submit button.Pop Up is closed and Pipeline is inactivated",
						"Unable to click on Submit button");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				// Filter with Inactive to see inactive pipelines
				selectFilter("Inactive");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				// if pipeline is inactive then clicking on toggle open popup contains enable
				// text
				// Click on toggle to open active popup
				WaitUtils.waitClickByRef(toggleClick).click();
				// click(toggleClick, "Able to click on toggle and Active PopUp is
				// displayed","Unable to click on toggle");

				toggleStatus = WaitUtils.waitClickByRef(enableTextInActivePopup,6).isDisplayed();

				logger.info("status is after making pipeline inactive " + toggleStatus);

				if (toggleStatus) {
					logger.info("InActive Pipelines are displayed");
					test.log(LogStatus.PASS, "InActive Pipelines are displayed");
				} else {
					logger.info("InActive Pipelines are NOT displayed");
					test.log(LogStatus.PASS, "InActive Pipelines are NOT displayed");

				}

			}

			else {
				logger.info("Active Pipelines are NOT displayed");
				test.log(LogStatus.PASS, "Active Pipelines are NOT displayed");
			}

		}

	}

	
	@Override
	public void pagination() {
		// check pagination
		isdisplay(pagination, "Pagination is displayed", "Pagination is NOT displayed");
		assertTrue(driver.findElement(pagination).isDisplayed(), "Pagination is NOT displayed");

		// check first page arrow
		isdisplay(firstPageArrow, "First Page Arrow is displayed", "First Page Arrow is NOT displayed");
		assertTrue(driver.findElement(firstPageArrow).isDisplayed(), "First Page Arrow is NOT displayed");

		// check last page arrow
		isdisplay(lastPageArrow, "Last Page Arrow is displayed", "Last Page Arrow is NOT displayed");
		assertTrue(driver.findElement(lastPageArrow).isDisplayed(), "Last Page Arrow is NOT displayed");

		// check previous page arrow
		isdisplay(previousPageArrow, "Previous Page Arrow is displayed", "Previous Page Arrow is NOT displayed");
		assertTrue(driver.findElement(previousPageArrow).isDisplayed(), "Previous Page Arrow is NOT displayed");

		// check Next page arrow
		isdisplay(nextPageArrow, "Next Page Arrow is displayed", "Next Page Arrow is NOT displayed");
		assertTrue(driver.findElement(previousPageArrow).isDisplayed(), "Next Page Arrow is NOT displayed");

		// check page dropdown
		click(pageDropdownExpand, "Able to click on page dropdown", "Unable to click on page dropdown");

		try {
		
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(pageDropdownValues, 10);
		logger.info(l1.size());
		logger.info("Page size dropdown has: ");
		test.log(LogStatus.INFO, "Page dropdown has: ");

		for (WebElement ele : l1) {
			logger.info(ele.getText());
			if (ele.getText().equals("10")) {
				test.log(LogStatus.PASS, ele.getText());

			} else if (ele.getText().equals("20")) {
				test.log(LogStatus.PASS, ele.getText());

			} else if (ele.getText().equals("50")) {
				test.log(LogStatus.PASS, ele.getText());

			} else if (ele.getText().equals("100")) {
				test.log(LogStatus.PASS, ele.getText());

			}

		}

		}
		catch(StaleElementReferenceException e)
		{
			
			List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(pageDropdownValues, 10);
			logger.info(l1.size());
			logger.info("Page size dropdown has: ");
			test.log(LogStatus.INFO, "Page dropdown has: ");

			for (WebElement ele : l1) {
				logger.info(ele.getText());
				if (ele.getText().equals("10")) {
					test.log(LogStatus.PASS, ele.getText());

				} else if (ele.getText().equals("20")) {
					test.log(LogStatus.PASS, ele.getText());

				} else if (ele.getText().equals("50")) {
					test.log(LogStatus.PASS, ele.getText());

				} else if (ele.getText().equals("100")) {
					test.log(LogStatus.PASS, ele.getText());

				}

			}
			
		}
		isdisplay(goToText, "Go To Text is displayed", "Go to Text is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(goToText).isDisplayed(), "Go To text is NOT displayed");

		isdisplay(goToInputField, "Go To input field is displayed", "Go to input field is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(goToInputField).isDisplayed(), "Go To input field is NOT displayed");

		WaitUtils.waitClickByRef(pageDropdownExpand).click();
	}

	@Override
	public void selectPageSizeFromDropdown() {

		int totalPipelinesCount = pipelinesCountFromHeader();

		try
		{
		
		//click on page size dropdown
		WaitUtils.waitClickByRef(pageDropdownExpand).click();
		
		//Fetch list from page size
		List<WebElement> pageSizeDropdown = WaitUtils.waitVisibilityOfDropdownElements(pageDropdownValues, 20);

		for (i = 0; i < pageSizeDropdown.size(); i++) {
			
			//Fetching text of page size
			String value = pageSizeDropdown.get(i).getText();

			//Selecting page size from dropdown
			pageSizeDropdown.get(i).click();
			
			logger.info("Page number is " + value);

			if (value.equals("10")) 
			{
				test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
				
				//Fetching count per page
				List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

				//Checking page size because some times module has less than 10 records
				if (totalPipelinesCount > 100) {
					if (pipelineCount.size() == 10) {
						test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
					} else {
						test.log(LogStatus.FAIL,
								pipelineCount.size() + " records are dispalyed per page though page size is " + value);

					}
				}

			}
			
			else if (value.equals("20")) {
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
				List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

				if (totalPipelinesCount > 100) {
					if (pipelineCount.size() == 20) {
						test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
					} else {
						test.log(LogStatus.FAIL,
								pipelineCount.size() + " records are dispalyed per page though page size is " + value);

					}
				}

			}
			
			else if (value.equals("50")) {
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
				List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

				if (totalPipelinesCount > 100) {
					if (pipelineCount.size() == 50) {
						test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
					} else {
						test.log(LogStatus.FAIL,
								pipelineCount.size() + " records are dispalyed per page though page size is " + value);

					}
				}

			}
			
			else if (value.equals("100")) {
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
				List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

				if (totalPipelinesCount > 100) {
					if (pipelineCount.size() == 100) {
						test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
					} else {
						test.log(LogStatus.FAIL,
								pipelineCount.size() + " records are dispalyed per page though page size is " + value);

					}
				}

			}
			else
			{
				test.log(LogStatus.FAIL, "Page size dropdown has "+value);
			}

			waitforelement(shortwaitvalue);
			
			WaitUtils.waitClickByRef(pageDropdownExpand).click();
			pageSizeDropdown = WaitUtils.waitVisibilityOfDropdownElements(pageDropdownValues,20);
		}
		}
		catch(StaleElementReferenceException e)
		{
			//click on page size dropdown
			WaitUtils.waitClickByRef(pageDropdownExpand).click();
			
			//Fetch list from page size
			List<WebElement> pageSizeDropdown = WaitUtils.waitVisibilityOfDropdownElements(pageDropdownValues, 20);

			for (i = 0; i < pageSizeDropdown.size(); i++) {
				
				//Fetching text of page size
				String value = pageSizeDropdown.get(i).getText();

				//Selecting page size from dropdown
				pageSizeDropdown.get(i).click();
				
				logger.info("Page number is " + value);

				if (value.equals("10")) 
				{
					test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
					
					//Fetching count per page
					List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

					//Checking page size because some times module has less than 10 records
					if (totalPipelinesCount > 100) {
						if (pipelineCount.size() == 10) {
							test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
						} else {
							test.log(LogStatus.FAIL,
									pipelineCount.size() + " records are dispalyed per page though page size is " + value);

						}
					}

				}
				
				else if (value.equals("20")) {
					
					WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
					
					test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
					List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

					if (totalPipelinesCount > 100) {
						if (pipelineCount.size() == 20) {
							test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
						} else {
							test.log(LogStatus.FAIL,
									pipelineCount.size() + " records are dispalyed per page though page size is " + value);

						}
					}

				}
				
				else if (value.equals("50")) {
					
					WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
					test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
					List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

					if (totalPipelinesCount > 100) {
						if (pipelineCount.size() == 50) {
							test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
						} else {
							test.log(LogStatus.FAIL,
									pipelineCount.size() + " records are dispalyed per page though page size is " + value);

						}
					}

				}
				
				else if (value.equals("100")) {
					WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
					test.log(LogStatus.PASS, "Able to select " + value + " from page size dropdown");
					List<WebElement> pipelineCount = WaitUtils.waitVisibilityOfDropdownElements(pipelineName, 20);

					if (totalPipelinesCount > 100) {
						if (pipelineCount.size() == 100) {
							test.log(LogStatus.PASS, pipelineCount.size() + " records are dispalyed per page");
						} else {
							test.log(LogStatus.FAIL,
									pipelineCount.size() + " records are dispalyed per page though page size is " + value);

						}
					}

				}
				else
				{
					test.log(LogStatus.FAIL, "Page size dropdown has "+value);
				}


				WaitUtils.waitClickByRef(pageDropdownExpand).click();
				pageSizeDropdown = WaitUtils.waitVisibilityOfDropdownElements(pageDropdownValues,20);
		}

	}
	}
	@Override
	public void verifyNextPageArrow() {

		// To get current page
		currentPageNumber = WaitUtils.waitClickByRef(currentPage).getText();
		logger.info("The current page number is " + currentPageNumber);

		// to check page number is 1
		if (Integer.parseInt(currentPageNumber) == 1) {
			test.log(LogStatus.PASS, "The current page number is " + currentPageNumber);

			String firstPageArrowStatus = WaitUtils.waitClickByRef(firstPageArrow).getAttribute("class");
			String previousPageArrowStatus = WaitUtils.waitClickByRef(previousPageArrow).getAttribute("class");
			logger.info("Status of 1st arrow " + firstPageArrowStatus);
			logger.info("Status of previous arrow " + previousPageArrowStatus);

			if (firstPageArrowStatus.contains("disabled") && previousPageArrowStatus.contains("disabled")) {
				test.log(LogStatus.PASS, "Backward arrows are disabled");
			} else {
				test.log(LogStatus.FAIL, "Backward arrows are NOT disabled");
			}

		}

		// to click next arrow
		click(nextPageArrow, "Able to click on next page arrow", "Unable to click on nextarrow");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// to get current page after clicking next arrow
		currentPageNumber = WaitUtils.waitClickByRef(currentPage).getText();
		logger.info("The page number is " + currentPageNumber + " after clicking on next page arrow");

		// to check page number is 2
		assertTrue(Integer.parseInt(currentPageNumber) == 1 + 1);
		test.log(LogStatus.PASS, "The page number is " + currentPageNumber + " after clicking on next page arrow");

	}

	@Override
	public void verifyPreviousPageArrow() {
		// use this method after verifyNextPageArrow()

		// to click previous arrow
		click(previousPageArrow, "Able to click on previous arrow", "Unable to click on Previous Arrow");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		// to get current page after clicking previous arrow
		currentPageNumber = WaitUtils.waitClickByRef(currentPage).getText();
		logger.info("The page number is " + currentPageNumber + " after clicking on previous page arrow");

		//To check previous page is 1
		assertTrue(Integer.parseInt(currentPageNumber) == 1);
		test.log(LogStatus.PASS, "The page number is " + currentPageNumber + " after clicking on previous page arrow");

	}

	@Override
	public void verifyLastPageArrow() {
		// to click last page arrow
		click(lastPageArrow, "Able to click on last page arrow", "Unable to click on last page arrow");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		//To fetch status of next and last arrows
		String nextPageArrowStatus = WaitUtils.waitClickByRef(nextPageArrow).getAttribute("class");
		String lastPageArrowStatus = WaitUtils.waitClickByRef(lastPageArrow).getAttribute("class");
		logger.info("Status of next arrow " + nextPageArrowStatus);
		logger.info("Status of last arrow " + lastPageArrowStatus);

		//To check status is disable
		assertTrue(nextPageArrowStatus.contains("disabled") && lastPageArrowStatus.contains("disabled"));
		test.log(LogStatus.PASS, "Forward arrows are disabled");

		// to get current page after clicking last page arrow
		currentPageNumber = WaitUtils.waitClickByRef(currentPage).getText();
		logger.info("The page number is " + currentPageNumber + " after clicking on last page arrow");

	}

	@Override
	public void verifyFirstPageArrow() {
		// to click first page arrow
		click(firstPageArrow, "Able to click on first page arrow", "Unable to click on first page arrow");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		// to get current page after clicking first page arrow
		currentPageNumber = WaitUtils.waitClickByRef(currentPage).getText();
		logger.info("The page number is " + currentPageNumber + " after clicking on first page arrow");

		//To check page number is 1
		assertTrue(Integer.parseInt(currentPageNumber) == 1);
		test.log(LogStatus.PASS, "The page number is " + currentPageNumber + " after clicking on first page arrow");

	}

	@Override
	public void verifySelectPages() {

		click(lastPageArrow);
		waitforelement(shortwaitvalue);
		currentPageNumber = driver.findElement(currentPage).getText();

		int pageNumber = Integer.parseInt(currentPageNumber);

		logger.info("Total pages are " + currentPageNumber);
		test.log(LogStatus.INFO, "Total pages are " + currentPageNumber);

		if (pageNumber > 1) {
			List<WebElement> numberOfRecordsPerPage = driver.findElements(editIcon);
			test.log(LogStatus.PASS, "By default number of records per page are " + numberOfRecordsPerPage.size());

		}

		click(firstPageArrow);
		waitforelement(shortwaitvalue);

		int x = 1;
		int y = 5;
		int cnt = 0;

		while (y < pageNumber) {

		}

		//// a[contains(@class,'paginator-page')]
		for (int i = 1; i < pageNumber; i++) {
			WebElement page = driver.findElement(By.xpath("//a[contains(@class,'paginator-page')][" + i + "]"));

			if (i >= 4) {
				String pn = driver.findElement(By.xpath("//(a[contains(@class,'paginator-page')])[1]")).getText();

				if (pn.equals("1")) {
					page.click();
				} else {
					i = 4;
					page.click();
				}

			}

			page.click();
			logger.info("Page no is " + page.getText());
			test.log(LogStatus.PASS, "User is in page number " + page.getText());
		}

	}

	@Override
	public void goToPage() {
		// To go to last page
		WaitUtils.waitClickByRef(lastPageArrow).click();
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		//To get last page number
		String pageNum = WaitUtils.waitClickByRef(currentPage).getText();

		//To convert into int
		int pageNumber = Integer.parseInt(pageNum);

		logger.info("Total pages are " + pageNumber);

		//iterating each page
		for (int i = 1; i <= pageNumber; i++)

		{
			logger.info("User enter as " + String.valueOf(i));
			
			//To clear Go to field
			WaitUtils.waitClickByRef(goToInputField).clear();
			
			//To enter value in go to textbox
			sendkeys(goToInputField, String.valueOf(i),
					"Able to enter page number " + String.valueOf(i) + " in Go To field",
					"Unable to enter page number in Go to Field");
			
			//To click on go to text as need to click outside
			WaitUtils.waitClickByRef(goToText).click();
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

			//To check current page with given go to field
			assertTrue(WaitUtils.waitClickByRef(By.xpath("//a[contains(@class,'state-active') and  text()='" + i + "']"))
					.isDisplayed());
			
			test.log(LogStatus.PASS, "User in " + i + " page ");
			logger.info("User enter as " + String.valueOf(i));

		}

		//To clear go to field
		WaitUtils.waitClickByRef(goToInputField).clear();
		
		//To generate random number
		Random r = new Random();
		
		// ex: pagenumber is 10 means generates pages from 0 to 90 but adding 10 again so 10 to 100 then adding 1 again so 11 to 101
		int x = r.nextInt(100 - pageNumber) + pageNumber + 1;
		logger.info("r value is " + x);
		
		//To enter value in go to field 
		WaitUtils.waitClickByRef(goToInputField).sendKeys(String.valueOf(x));
		
		// should be blank as giving page size is more than actual page size
		assertTrue(WaitUtils.waitClickByRef(goToInputField).getText().isBlank());
		test.log(LogStatus.PASS, "Go to field is NOT accepting pages which are NOT matched");

		//checking it is accepting alphabets
		WaitUtils.waitClickByRef(goToInputField).sendKeys("abc");
		assertTrue(WaitUtils.waitClickByRef(goToInputField).getText().isBlank());
		test.log(LogStatus.PASS, "Go to field is NOT accepting text field");

	}

	@Override
	public void clickAddPipeline() {
		// to check add pipeline button
		isdisplay(addPipelineButton, "Add Pipeline Button is displayed", "Add Pipeline Button is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(addPipelineButton).isDisplayed(), "Add Pipeline Button is NOT displayed");

		// click on add pipeline button
		click(addPipelineButton, "Able to click on Add Pipeline Button and Add Pipeline Pop up is displayed",
				"Unable to click on Add Pipeline button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	}

	@Override
	public void addPipelineFields() {

		//clicks add pipeline
		clickAddPipeline();

		// To check Add pipeline heading
		isdisplay(addPipelineHeading, "Add Pipeline heading is displayed", "Add Pipeline heading is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(addPipelineHeading).isDisplayed(), "Add Pipeline heading is NOT displayed");

		// to check pipeline name
		isdisplay(pipelineNameTextInAddPopline, "Pipeline Name text is displayed",
				"Pipeline Name text is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(pipelineNameTextInAddPopline).isDisplayed(),
				"Pipeline Name text is NOT displayed");

		// to check pipeline name textbox
		isdisplay(pipelineNameTextbox, "Pipeline Name textbox is displayed", "Pipeline Name textbox is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(pipelineNameTextbox).isDisplayed(), "Pipeline Name textbox is NOT displayed");

		// to check duplicate opportunity
		isdisplay(duplicateOppourtnity, "Allow Duplicate Oppourtnity is displayed",
				"Allow Duplicate Oppourtnity is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(duplicateOppourtnity).isDisplayed(),
				"Allow Duplicate Oppourtnity is NOT displayed");
		
		WaitUtils.waitClickByRef(duplicateOppourtnity).click();
		isdisplay(duplicateOpportunityText, "Note: By selecting this checkbox, you are allowing multiple records/ duplicates to be created for the same contact under this pipeline. is displayed after enabling checkbox",
				"Note: By selecting this checkbox, you are allowing multiple records/ duplicates to be created for the same contact under this pipeline. is NOT displayed after enabling checkbox");
		assertTrue(WaitUtils.waitClickByRef(duplicateOpportunityText).isDisplayed(),
				"Allow Duplicate Oppourtnity is NOT displayed");
		
		// to check stage name text
		isdisplay(stageName, "Stage Name text is displayed", "Stage Name text is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(stageName).isDisplayed(), "Stage Name text is NOT displayed");

		// to check stage name Textbox
		isdisplay(stageNameTextbox, "Stage Name textbox is displayed", "Stage Name textbox is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(stageNameTextbox).isDisplayed(), "Stage Name textbox is NOT displayed");

		// to check delete icon
		isdisplay(deleteIconInAddPipeline, "Delete icon is displayed", "Delete icon is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(deleteIconInAddPipeline).isDisplayed(), "Delete icon is NOT displayed");

		// to check add stage button
		isdisplay(addStageButton, "Add Stage button is displayed", "Add Stage button is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(addStageButton).isDisplayed(), "Add Stage button is NOT displayed");

		// to check cancel button
		isdisplay(cancelButton, "Cancel button is displayed", "Cancel button is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(cancelButton).isDisplayed(), "Cancel button is NOT displayed");

		// to check save button
		isdisplay(saveButton, "Save button is displayed", "Save button is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(saveButton).isDisplayed(), "Save button is NOT displayed");

		// to check close icon
		isdisplay(closeIcon, "Close icon is displayed", "Close icon is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(closeIcon).isDisplayed(), "Close icon is NOT displayed");

	}

	@Override
	public void addPipelineMandatoryFieldsErrors() {

		clickAddPipeline();

		// click on save button
		click(saveButton, "Able to click on save button wihout filling mandatory fields",
				"Unable to click on Save button");

		// to check pipeline name require error
		isdisplay(pipelineRequireError, "Pipeline name is required error is displayed",
				"Pipeline name is required error is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(pipelineRequireError).isDisplayed(),
				"Pipeline name is required error is NOT displayed");

		// to check stage name require error
		isdisplay(stageRequireError, "Stage name is required error is displayed",
				"Stage name is required error is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(stageRequireError).isDisplayed(),
				"Stage name is required error is NOT displayed");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed",
				"Unable to click on Cancel button");
		
		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	public void pipelineNameTextboxField() {

		clickAddPipeline();

		// to get pipeline name placeholder
		String actualPlaceholderName = WaitUtils.waitClickByRef(pipelineNameTextbox).getAttribute("placeholder");

		String expectedPlaceholderName = excelutil.getData("Settings_PipelinesTab", "expectedPlaceholderNamePipeline",
				xlsname);

		//To check pipeline placeholder
		assertEquals(actualPlaceholderName, expectedPlaceholderName, "Placeholder error is different");

		logger.info("Pipeline Name placeholder is " + actualPlaceholderName);
		test.log(LogStatus.PASS, "Pipeline Name placeholder is " + actualPlaceholderName);

		// click pipeline name field and click on outside
		click(pipelineNameTextbox, "Able to click on pipeline name field and again click on outside field",
				"Unable to click on pipeline name field");

		WaitUtils.waitClickByRef(stageNameTextbox).click();

		// to check pipeline name require error
		isdisplay(pipelineRequireError, "Pipeline name is required error is displayed",
				"Pipeline name is required error is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(pipelineRequireError).isDisplayed(),
				"Pipeline name is required error is NOT displayed");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed",
				"Unable to click on Cancel button");

	}

	@Override
	//To check alpha numeric errors and accepting with spaces
	public void pipelineNameFieldValidation() {
		clickAddPipeline();

		// to get pipeline name from excel
		String pipelineNameWithSpaces = excelutil.getData("Settings_PipelinesTab", "Pipeline name with spaces",
				xlsname);

		// To fill pipeline name which has alphanumeric with spaces
		sendkeys(pipelineNameTextbox, pipelineNameWithSpaces,
				"Able to enter pipeline name with spaces as " + pipelineNameWithSpaces,
				"Unable to enter pipeline name");


		// To clear pipeline name value
		clear(pipelineNameTextbox, "Pipeline name field is cleared", "Pipeline name field is NOT cleared");

		String pipelineNameWithSpecialCharacters = excelutil.getData("Settings_PipelinesTab",
				"Pipeline name with special characters", xlsname);


		// To fill pipeline name which has alphanumeric with spaces
		sendkeys(pipelineNameTextbox, pipelineNameWithSpecialCharacters,
				"Able to enter pipeline name as " + pipelineNameWithSpecialCharacters, "Unable to enter pipeline name");

		// To check error msg
		isdisplay(specialCharacterError, "Error: Enter Alpha numeric with only _ and - is displayed",
				"Error: Enter Alpha numeric with only _ and - is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(specialCharacterError).isDisplayed(),
				"Error: Enter Alpha numeric with only _ and - is NOT displayed");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed",
				"Unable to click on Cancel button");

	}

	@Override
	//To check pipeline length is 40

	public void verifyPipelineNameLength() {

		clickAddPipeline();

		// to get pipeline name from excel
		String pipelineNameLengthIs41 = excelutil.getData("Settings_PipelinesTab", "41LengthPipelineName", xlsname);

		// To get length of String
		logger.info("Before entering the pipeline name length is " + pipelineNameLengthIs41.length());
		test.log(LogStatus.PASS, "Before entering pipeline name the length is " + pipelineNameLengthIs41.length());

		// To fill pipeline name which has alphanumeric with spaces
		sendkeys(pipelineNameTextbox, pipelineNameLengthIs41, "Try to enter pipeline name with length 41 characters",
				"Unable to enter pipeline name");


		// to get pipeline name value
		String pipelineName = WaitUtils.waitClickByRef(pipelineNameTextbox).getAttribute("value");

		// To get length after entering
		logger.info("After entering the pipeline name length is  " + pipelineName.length());
		test.log(LogStatus.PASS, "After entering the pipeline name length is " + pipelineName.length());

		assertTrue(pipelineName.length() == 40, "Pipeline name is accepting more than 40 characters");
		logger.info("Pipeline name field length is restricted to 40 characters");
		test.log(LogStatus.PASS, "Pipeline name field length is restricted to 40 characters");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed", "Unable to click on Cancel");

		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	}

	@Override
	//To check stage name place holder and error
	public void stageNameTextboxField() {

		clickAddPipeline();

		// to get pipeline name placeholder
		String actualPlaceholderName = WaitUtils.waitClickByRef(stageNameTextbox).getAttribute("placeholder");

		logger.info("Actual Pipeline Name placeholder is " + actualPlaceholderName);
		test.log(LogStatus.PASS, "Pipeline Name placeholder is " + actualPlaceholderName);

		String expectedPlaceholderName = excelutil.getData("Settings_PipelinesTab", "expectedPlaceholderNameStage",
				xlsname);

		assertEquals(actualPlaceholderName, expectedPlaceholderName, "Placeholder error is different");

		// click pipeline name field and click on outside
		click(stageNameTextbox, "Able to click on Stage name field and again click on outside field",
				"Unable to click on Stage name field");

		WaitUtils.waitClickByRef(pipelineNameTextbox).click();

		// to check pipeline name require error
		isdisplay(stageRequireError, "Stage name is required error is displayed",
				"Stage name is required error is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(stageRequireError).isDisplayed(),
				"Stage name is required error is NOT displayed");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed",
				"Unable to click on Cancel button");
		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	//To check stage name error and accepting with spaces

	public void stageNameFieldValidation() {
		clickAddPipeline();

		// to get pipeline name from excel
		String stageNameWithSpaces = excelutil.getData("Settings_PipelinesTab", "Stage name with spaces", xlsname);

		// To fill pipeline name which has alphanumeric with spaces
		sendkeys(stageNameTextbox, stageNameWithSpaces,
				"Able to enter stage name with spaces as " + stageNameWithSpaces, "Unable to enter stage name");


		// To clear pipeline name value
		clear(stageNameTextbox, "Stage name field is cleared", "Stage name field is NOT cleared");

		String stageNameWithSpecialCharacters = excelutil.getData("Settings_PipelinesTab",
				"Stage name with special characters", xlsname);


		// To fill pipeline name which has alphanumeric with spaces
		sendkeys(stageNameTextbox, stageNameWithSpecialCharacters,
				"Able to enter stage name as " + stageNameWithSpecialCharacters, "Unable to enter stage name");

		// To check error msg
		isdisplay(specialCharacterError, "Error : Enter Alpha numeric with only _ and - is displayed",
				"Error: Enter Alpha numeric with only _ and - is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(specialCharacterError).isDisplayed(),
				"Error: Enter Alpha numeric with only _ and - is NOT displayed");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed",
				"Unable to click on Cancel button");
		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	//To check stage name length is 40
	public void verifyStageNameLength() {

		clickAddPipeline();

		// to get pipeline name from excel
		String stageNameLengthIs41 = excelutil.getData("Settings_PipelinesTab", "41LengthStageName", xlsname);

		// To get length of String
		logger.info("Before entering the stage name length is " + stageNameLengthIs41.length());
		test.log(LogStatus.PASS, "Before entering the length is " + stageNameLengthIs41.length());

		// To fill pipeline name which has alphanumeric with spaces
		sendkeys(stageNameTextbox, stageNameLengthIs41, "Try to enter stage name with length 41 characters",
				"Unable to enter stage name");

		// to get pipeline name value
		String stageName = driver.findElement(stageNameTextbox).getAttribute("value");

		// To get length after entering
		logger.info("After entering the stage name length is  " + stageName.length());
		test.log(LogStatus.PASS, "After entering the stage name length is " + stageName.length());

		assertTrue(stageName.length() == 40, "Pipeline name is accepting more than 40 characters");
		logger.info("Stage name field length is restricted to 40 characters");
		test.log(LogStatus.PASS, "Stage name field length is restricted to 40 characters");

		// click Cancel button
		click(cancelButton, "Able to click on Cancel button and Add Popup is closed", "Unable to click on Cancel");

		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	}

	@Override
	//To drag and drop stages
	public void stageDragDrop() {

		clickAddPipeline();

		// To fetch pipeline name from excel
		String pipelineNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");

		// To fetch stage name from excel
		String firstStageNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

		// to fill stage name from excel
		sendkeys(stageNameTextbox, firstStageNameFromExcl,
				"Able to enter stage name as " + firstStageNameFromExcl + " in add pipeline popup",
				"Unable to enter stage name");

		// To click add stage button
		click(addStageButton, "Able to click on add stage button", "Unable to click on add stage button");

		// To check previous stage name
		String actualStageName = 
				WaitUtils.waitClickByRef(previousStageNameTextbox).getAttribute("value");
		assertTrue(actualStageName.equals(firstStageNameFromExcl), "Previous Stage name is NOT displayed");
		test.log(LogStatus.PASS, "Previously added stage name is displayed as " + actualStageName);

		// to check empty stage name textbox
		isdisplay(newStageAfterClickAddStage, "Stage name textbox is added after previously added stage",
				"Stage name textbox is NOT added after clicking on Add Stage button");
		assertTrue(
				WaitUtils.waitClickByRef(newStageAfterClickAddStage).isDisplayed(),
				"Stage name textbox is NOT added after clicking on Add Stage button");

		// to check drag icon
		isdisplay(dragIconAfterClickAddStage, "Drag icon is displayed before newly added stage name",
				"Drag icon is NOT displayed before stage name");
		assertTrue(
				WaitUtils.waitClickByRef(dragIconAfterClickAddStage).isDisplayed(),
				"Drag icon is NOT displayed before stage name");

		// to check delete icon
		isdisplay(deleteForNewStageAfterClickAddStage, "Delete icon is displayed against newly added stage name",
				"Delete icon is NOT displayed against stage name");
		assertTrue(
				WaitUtils.waitClickByRef(deleteForNewStageAfterClickAddStage).isDisplayed(),
				"Delete icon is NOT displayed against stage name");


		// To fetch second stage name from excel
		String secondStageNameExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Second Stage Name", xlsname);

		// to fill stage name from excel
		sendkeys(newStageAfterClickAddStage, secondStageNameExcl,
				"Able to enter second stage name as " + secondStageNameExcl + " in add pipeline popup",
				"Unable to enter second stage name");

		// To get 1st and 2nd stages names before drag and drop
		String stageNameFirst = driver.findElement(stageFirst).getAttribute("value");
		String stageNameSecond = driver.findElement(stageSecond).getAttribute("value");

		logger.info("Before drag and drop first stage name is " + stageNameFirst);
		logger.info("Before drag and drop second stage name is " + stageNameSecond);

		assertTrue(firstStageNameFromExcl.equals(stageNameFirst));
		assertTrue(secondStageNameExcl.equals(stageNameSecond));

		test.log(LogStatus.PASS, "Before drag and drop first stage name is " + stageNameFirst);
		test.log(LogStatus.PASS, "Before drag and drop second stage name is " + stageNameSecond);

		Actions act = new Actions(driver);

		WebElement src = 
				WaitUtils.waitClickByRef(sourceDrag);

		WebElement des = 
				WaitUtils.waitClickByRef(destinationDrag);

		act.dragAndDrop(src, des).build().perform();


		// To get 1st and 2nd stages names after drag and drop
		String stageNameFirst_afterdrag = 
				WaitUtils.waitClickByRef(stageFirst).getAttribute("value");
		String stageNameSecond_afterdrag = 
				WaitUtils.waitClickByRef(stageSecond).getAttribute("value");

		logger.info("After drag and drop first stage name is " + stageNameFirst_afterdrag);
		logger.info("After drag and drop second stage name is " + stageNameSecond_afterdrag);

		assertTrue(secondStageNameExcl.equals(stageNameFirst_afterdrag));
		assertTrue(firstStageNameFromExcl.equals(firstStageNameFromExcl));

		test.log(LogStatus.PASS, "After drag and drop first stage name is " + stageNameFirst_afterdrag);

		test.log(LogStatus.PASS, "After drag and drop second stage name is " + stageNameSecond_afterdrag);

		// click Save button
		click(saveButton, "Able to click on Save button and Add Popup is closed", "Unable to click on Save button");

		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
	}

	@Override
	//Check popup when trying to delete single stage
	public void deleteSingleStagePopup() {
		clickAddPipeline();

		// click on delete
		click(deleteIconInAddPipeline, "Able to click on delete icon against stage and Delete Popup is displayed",
				"Unable to click on delete icon");

		// verify delete pop up
		isdisplay(deleteStageText, "Delete Stage text is displayed", "Delete Stage text is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(deleteStageText).isDisplayed(), "Delete Stage text is NOT displayed");

		isdisplay(needOneStage, "You need atleast 1 stage in the pipeline text is displayed",
				"You need atleast 1 stage in the pipeline text is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(needOneStage).isDisplayed(),
				"You need atleast 1 stage in the pipeline text is NOT displayed");

		isdisplay(deleteStageCloseIcon, "Close icon is displayed", "Close icon is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(deleteStageCloseIcon).isDisplayed(), "Close icon is NOT displayed");

		isdisplay(okButton, "Ok button is displayed", "Ok button is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(okButton).isDisplayed(), "Ok button is NOT displayed");

		click(okButton, "Able to click on Ok button and Delete PopUp is closed", "Unable to click on Close button");

		click(cancelButton, "Able to click on Cancel button and Add Popup is closed",
				"Unable to click on Cancel button");

		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	}

	@Override
	//To check pipeline is NOT created after clicking on cancel or close
	public void verifyCancelCloseAddPopupWithDetails(By ref, String str) {

		clickAddPipeline();

		// To fetch pipeline name from excel
		String pipelineNameFromExcl = randomGenerator()+excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");

		// To fetch stage name from excel
		String firstStageNameFromExcl = randomGenerator()+excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

		// to fill stage name from excel
		sendkeys(stageNameTextbox, firstStageNameFromExcl,
				"Able to enter stage name as " + firstStageNameFromExcl + " in add pipeline popup",
				"Unable to enter stage name");

		click(ref, "Able to click on " + str + " and Add Popup is closed", "Unable to click on " + str);
		//WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		sendkeys(searchInPipelinesTab, pipelineNameFromExcl, "Able to enter pipline name as "+pipelineNameFromExcl, "Unable to enter pipeline name in search");
		
		isdisplay(noItems, "There are no items to display is displayed", "There are no items to display is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(noItems).isDisplayed(),"There are no items to display is NOT displayed");
		test.log(LogStatus.PASS, "Pipeline is NOT created");
		
	}

	@Override
	//To check add popup is closed after Clicking on cancel or close without details
	public void verifyCancelCloseAddPopupWithOutDetails(By ref, String str) {

		clickAddPipeline();

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		test.log(LogStatus.INFO, "Add Popup is empty");

		click(ref, "Able to click on " + str + " and Add Popup is closed", "Unable to click on " + str);

	}

	@Override
	public void addPipelineInPipelinesModule() {

		clickAddPipeline();

		// To fetch pipeline name from excel
		String pipelineNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");

		// To fetch stage name from excel
		String firstStageNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

		// to fill stage name from excel
		sendkeys(stageNameTextbox, firstStageNameFromExcl,
				"Able to enter stage name as " + firstStageNameFromExcl + " in add pipeline popup",
				"Unable to enter stage name");

		click(saveButton, "Able to click on Save button and Popup is closed", "Unable to click on save button");

		pipelineNameCreated=pipelineNameFromExcl;
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		

	
	}

	@Override
	//To check created pipeline status,edit,delete icon is present
	public void verifyAddPipeline() {

		clickAddPipeline();

		// To fetch pipeline name from excel + add random value
		String pipelineNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");

		// To fetch stage name from excel+ add random value
		String firstStageNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

		// to fill stage name
		sendkeys(stageNameTextbox, firstStageNameFromExcl,
				"Able to enter stage name as " + firstStageNameFromExcl + " in add pipeline popup",
				"Unable to enter stage name");


		click(saveButton, "Able to click on Save button and Popup is closed", "Unable to click on save button");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		
		// To verify toast summary
		// isdisplay(toastSummaryCreatePipeline, "Create Pipeline is displayed in toast
		// message","Create Pipeline is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastSummaryCreatePipeline).isDisplayed(),"Create
		// Pipeline is NOT displayed in toast message");

		// To verify toast detail
		// isdisplay(toastDetailCreatePipeline, "Pipeline created successfully is
		// displayed in toast message","Pipeline created successfully is NOT displayed
		// in toast message");
		// assertTrue(driver.findElement(toastDetailCreatePipeline).isDisplayed(),"Pipeline
		// created successfully is NOT displayed in toast message");

		// To verify toast icon
		// isdisplay(toastIcon, "Toast icon is displayed in toast message","Toast icon
		// is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastIcon).isDisplayed(), "Toast icon is NOT
		// displayed in toast message");

		// To verify toast close
		// isdisplay(closeIcon, "Toast close icon is displayed in toast message", "Toast
		// close icon is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastIcon).isDisplayed(),"Toast close icon is
		// NOT displayed in toast message");

		// Search new pipeline
		sendkeys(searchInPipelinesTab, pipelineNameFromExcl, "Able to search newly created pipeline",
				"Unable to search pipeline");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		WaitUtils.waitTextIsPresent(pipelineName, pipelineNameFromExcl);

		// To get text of pipeline name
		String actualPipelineName = 
				WaitUtils.waitClickByRef(newPipelineName).getText();

		logger.info("Created pipeline name is " + actualPipelineName);


		// To check name
		assertTrue(actualPipelineName.equals(pipelineNameFromExcl));

		test.log(LogStatus.PASS, "Created Pipeline name " + actualPipelineName + " is displayed");

		WaitUtils.waitClickByRef(toggleClick).click();
		
		boolean actualToggleStatus=WaitUtils.waitClickByRef(disableTextInActivePopup).isDisplayed();
		
		
		
	//String actualToggleStatus = WaitUtils.waitClickByRef(By.xpath("//p[contains(text(),'" + pipelineNameFromExcl
					//	+ "')]/following::input[contains(@id,'" + pipelineNameFromExcl + "_inputSwitch')]"))
				//.getAttribute("aria-checked");

		assertTrue(actualToggleStatus,"Created Pipeline toggle status is Inactive");

		test.log(LogStatus.PASS, "Created Pipeline toggle status is active");

		// to check edit icon
		assertTrue(
				WaitUtils.waitClickByRef(editIcon).isDisplayed(), "Edit icon is NOT displayed");

		test.log(LogStatus.PASS, "Edit icon is displayed against newly created pipeline");

		// To check delete icon
		assertTrue(
				WaitUtils.waitClickByRef(deleteIcon).isDisplayed(), "Delete icon is NOT displayed");

		test.log(LogStatus.PASS, "Delete icon is displayed against newly created pipeline");

	}

	@Override
	//To check edit pipeline popup errors and placeholder
	public void editPipelineErrorsPlaceholder() {

		selectFilter("All");
		test.log(LogStatus.INFO, "All pipelines are displayed");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String pipeilineNameValue=excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);


		// Search pipeline name
		sendkeys(searchInPipelinesTab,pipeilineNameValue ,
				"Able to search pipeline name", "Unable to search pipeline name");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName, pipeilineNameValue));
		

		// To click edit icon
		click(editIcon, "Able to click on edit icon and edit Popup is displayed", "Unable to click on edit icon");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// to check pipeline heading
		isdisplay(editPipelineHeading, "Edit Pipeline heading is dispalyed", "Edit pipeline heading is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(editPipelineHeading).isDisplayed(), "Edit Pipeline heading is NOT displayed");

		// to clear pipeline name
		// clear(pipelineNameTextbox, "Able to clear Pipeline name in edit pipeline
		// popup", "Unable to clear pipeline name");

		WaitUtils.waitClickByRef(pipelineNameTextbox).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);

		test.log(LogStatus.PASS, "Pipeline name is cleared");

		// to check pipeline placeholder
		String actualEditPipelinePlaceHolder = 
				WaitUtils.waitClickByRef(pipelineNameTextbox).getAttribute("placeholder");

		assertTrue(actualEditPipelinePlaceHolder
				.equals(excelutil.getData("Settings_PipelinesTab", "PipelinePlaceholder", xlsname)));
		test.log(LogStatus.PASS, "The pipeline placeholder is " + actualEditPipelinePlaceHolder);

		// To clear stage name
		// clear(stageNameTextbox, "Able to clear Stage name in edit pipeline popup",
		// "Unable to clear stage name");

		WaitUtils.waitClickByRef(stageNameTextbox).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
		test.log(LogStatus.INFO, "Pipeline name is cleared");
		
		// to check stage name placeholder
		String actualStagePlaceHolder = 
				WaitUtils.waitClickByRef(stageNameTextbox).getAttribute("placeholder");

		assertTrue(
				actualStagePlaceHolder.equals(excelutil.getData("Settings_PipelinesTab", "StagePlaceholder", xlsname)));
		test.log(LogStatus.PASS, "The Stage name placeholder is " + actualStagePlaceHolder);

		// to click update button
		click(updateButton, "Able to click on update button", "Unable to click on update button");

		// To check pipeline error
		isdisplay(pipelineRequireError, "Error : Pipeline name is required is displayed",
				"Error : Pipeline name is required is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(pipelineRequireError).isDisplayed(),
				"Error : Pipeline name is required is NOT displayed");

		// To Check stage require error
		isdisplay(stageRequireError, "Error : Stage name is required is displayed",
				"Stage name is required is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(stageRequireError).isDisplayed(),
				"Error : Stage name is required is NOT displayed");

		// To click cancel
		click(cancelButton, "Able to click on Cancel button and Add Pipeline Popup is closed",
				"Unable to click on Cancel button");

	}

	@Override
	//To update pipeline and stage name 
	public void editPipeline() {
		selectFilter("All");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// Search pipeline name
		sendkeys(searchInPipelinesTab, excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname),
				"Able to search pipeline name", "Unable to search pipeline name");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String fetchPipelineName=WaitUtils.waitClickByRef(pipelineName).getText();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName, "Test Pipeline Name"));
		
		// To click edit icon
		click(editIcon, "Able to click on edit icon and edit Popup is displayed", "Unable to click on edit icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// to clear pipeline name
		clear(pipelineNameTextbox, "Able to clear Pipeline name in edit pipeline popup",
				"Unable to clear pipeline name");
		// driver.findElement(pipelineNameTextbox).sendKeys(Keys.CONTROL,"a",Keys.BACK_SPACE);


		// To fetch pipeline name from excel+random generator
		String pipelineNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");
		
logger.info("pipelineNameFromExcl  is "+pipelineNameFromExcl);

waitforelement(mediumwaitvalue);
		// to clear stage name
		clear(stageNameTextbox, "Able to clear Stage name in edit pipeline popup", "Unable to clear Stage name");

		waitforelement(mediumwaitvalue);		
		// To fetch second stage name from excel+add random value
		String secondStageNameExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Second Stage Name", xlsname);
		waitforelement(mediumwaitvalue);
		// To fill Stage name
		sendkeys(stageNameTextbox, secondStageNameExcl,
				"Able to enter stage name as " + secondStageNameExcl + " in add pipeline popup",
				"Unable to enter stage name");
		
		logger.info("pipelineNameFromExcl  is "+pipelineNameFromExcl);
		
		waitforelement(mediumwaitvalue);
		// to click update button
		click(updateButton, "Able to click on update button and Edit PopUp is closed",
				"Unable to click on update button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		//waitforelement(mediumwaitvalue);
		
		

		// To verify toast summary
		// isdisplay(toastSummaryUpdatePipeline, "Update Pipeline is displayed in toast
		// message","Update Pipeline is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastSummaryUpdatePipeline).isDisplayed(),"Update
		// Pipeline is NOT displayed in toast message");

		// To verify toast detail
		// isdisplay(toastDetailUpdatePipeline, "Pipeline updated successfully is
		// displayed in toast message","Pipeline updated successfully is NOT displayed
		// in toast message");
		// assertTrue(driver.findElement(toastDetailUpdatePipeline).isDisplayed(),"Pipeline
		// updated successfully is NOT displayed in toast message");

		// To verify toast icon
		// isdisplay(toastIcon, "Toast icon is displayed in toast message","Toast icon
		// is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastIcon).isDisplayed(), "Toast icon is NOT
		// displayed in toast message");

		// To verify toast close
		// isdisplay(closeIcon, "Toast close icon is displayed in toast message", "Toast
		// close icon is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastIcon).isDisplayed(),"Toast close icon is
		// NOT displayed in toast message");

		
		

		// To clear search value
		clear(searchInPipelinesTab, "Able to clear Search value", "Unable to cler search value");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		
		
		// To search modified pipeline name
		sendkeys(searchInPipelinesTab, pipelineNameFromExcl, "Entered search value is " + pipelineNameFromExcl,
				"Unable to enter search value");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName, pipelineNameFromExcl));
		
		
		//waitforelement(extraverylongwaitvalue);


		String modifiedPipelineName = WaitUtils.waitClickByRef(pipelineName).getText();
		logger.info("modifiedPipelineName is "+modifiedPipelineName);
		test.log(LogStatus.PASS, "Pipeline name is "+modifiedPipelineName);
		

		assertTrue(pipelineNameFromExcl.equalsIgnoreCase(modifiedPipelineName));
		test.log(LogStatus.PASS, "Updated pipeline name is displayed");
		
		WaitUtils.waitClickByRef(editIcon).click();
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String modifiedStageName=WaitUtils.waitClickByRef(stageNameTextbox).getAttribute("value");
		assertTrue(secondStageNameExcl.equalsIgnoreCase(modifiedStageName));
		test.log(LogStatus.PASS, "Updated stage name is displayed");
		test.log(LogStatus.PASS, "Pipeline is updated");
		
		WaitUtils.waitClickByRef(cancelButton).click();

	}

	@Override
	//to check delete popup fields, errors
	public void deletePipelinePopup() {

		// click delete icon
		click(deleteIcon, "Able to click on delete and delete pop up is displayed", "Unable to click on delete icon");

		// To check text
		isdisplay(deletePleaseEnterText, "Please enter word Delete to continue is displayed",
				"Please enter word Delete to continue is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(deletePleaseEnterText).isDisplayed(),
				"Please enter word Delete to continue is NOT displayed");

		// To check place holder
		String ActualDeleteStagePlaceholder = WaitUtils.waitClickByRef(deleteTextBox).getAttribute("placeholder");
		assertTrue(ActualDeleteStagePlaceholder
				.equals(excelutil.getData("Settings_PipelinesTab", "ExpectedDeleteStagePlaceholder", xlsname)));
		test.log(LogStatus.PASS, "Placeholder is " + ActualDeleteStagePlaceholder);

		// To check close icon
		isdisplay(closeIcon, "Close icon is displayed", "Close icon is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(closeIcon).isDisplayed(), "Close icon is NOT displayed");

		// to check submit button
		isdisplay(submitButton, "Submit button is displayed", "Submit button is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(closeIcon).isDisplayed(), "Submit button is NOT displayed");

		// Enter incorrect word
		sendkeys(deleteTextBox, excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname),
				"Able to enter incorrect word", "Unable to enter delete");

		// click submit
		click(submitButton, "Able to click on Submit button", "Unable to click on Submit button");

		// To check enter correct toast
		// isdisplay(deleteEnterCorrectTextInToast, "Enter correct word is
		// displayed","Enter correct word is NOT dispalyed");
		// assertTrue(driver.findElement(deleteEnterCorrectTextInToast).isDisplayed(),"Enter
		// correct word is NOT dispalyed");

		// To check toast icon
		// isdisplay(DeleteEnterCorrectToastIcon,"Toast icon is displayed", "Toast icon
		// is NOT dispalyed");
		// assertTrue(driver.findElement(DeleteEnterCorrectToastIcon).isDisplayed(),"Toast
		// icon is NOT dispalyed");

		// To check close icon
		// isdisplay(closeIcon,"Close icon is displayed", "Close icon is NOT
		// dispalyed");
		// assertTrue(driver.findElement(closeIcon).isDisplayed(),"Close icon is NOT
		// dispalyed");

		// To get provided value
		String deleteTextboxValue = WaitUtils.waitClickByRef(deleteTextBox).getAttribute("value");

		assertTrue(deleteTextboxValue.isBlank());

		test.log(LogStatus.PASS, "Incorrect word is cleared");

		// click submit without entering anyword
		click(submitButton, "Able to click on submit button", "Unable to click on submit button");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// Check error msg
		isdisplay(deleteConfirmation, "Error : Confirmation text is required is displayed",
				"Error: Confirmation text is required is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(deleteConfirmation).isDisplayed(),
				"Error : Confirmation text is required is NOT displayed");

		// click close icon
		click(closeIcon, "Able to click on close icon and delete popup is closed", "Unable to click on close icon");

	}

	@Override
	//enter delete, click on close without clicking on submit
	public void deleteWithoutSubmit() {

		String actualPipelineName = 
				WaitUtils.waitClickByRef(pipelineName).getText();

		// click delete icon
		click(deleteIcon, "Able to click on delete and delete pop up is displayed", "Unable to click on delete icon");

		// Enter correct word
		sendkeys(deleteTextBox, excelutil.getData("Settings_PipelinesTab", "TypeDelete", xlsname),
				"Able to enter delete", "Unable to enter delete");

		// click close icon
		click(closeIcon, "Able to click on close icon and delete popup is closed", "Unable to click on close icon");

		String expectedPipelineName = 
				WaitUtils.waitClickByRef(pipelineName).getText();

		assertTrue(actualPipelineName.equals(expectedPipelineName));

		test.log(LogStatus.PASS, "Pipeline is NOT deleted");

	}

	@Override
	//To check 40 stages are added to pipeline
	public void verifyMaxStages() {
		String pipelineNameFromExcl = "";

		try {
			clickAddPipeline();

			// To fetch pipeline name from excel + add random value
			pipelineNameFromExcl = randomGenerator()
					+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

			// To fill pipeline name
			sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
					"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
					"Unable to enter pipeline name");

			// To fetch stage name from excel+ add random value
			String stageNameFromExcl = randomGenerator()
					+ excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

			// to fill stage name
			sendkeys(stageNameTextbox, stageNameFromExcl,
					"Able to enter stage name as " + stageNameFromExcl + " in add pipeline popup",
					"Unable to enter stage name");


			test.log(LogStatus.INFO, "Click on Add Stage");

			while (true) {
				try {


					WaitUtils.waitClickByRef(addStageButton,10).click();

					WaitUtils.waitClickByRef(blankStageTextbox).sendKeys(stageNameFromExcl);


				}

				catch (NoSuchElementException | TimeoutException e) {

					break;
				}
			}

		} catch (Exception e) {

			List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(stageNameTextbox,20);


			logger.info("Number of stages are " + l1.size());
			assertTrue(l1.size() == Integer
					.parseInt(excelutil.getData("Settings_PipelinesTab", "MaxStageCount", xlsname)),"Number of stages are "+l1.size()+ " instead of 40");

			test.log(LogStatus.PASS, "Number of stages are " + l1.size());

			click(saveButton, "Able to click on Save button and add pipeline popup is closed",
					"Unable to click on Save button");
			
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

			sendkeys(searchInPipelinesTab, pipelineNameFromExcl, "Able to search newly created pipeline",
					"Unable to search pipeline");
			
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
			

			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName, pipelineNameFromExcl));

			String pname = 
					WaitUtils.waitClickByRef(pipelineName).getText();
			assertTrue(pname.equals(pipelineNameFromExcl));

			test.log(LogStatus.PASS, "Able to see pipeline with 40 stages in search results");
		

			WaitUtils.waitClickByRef(editIcon).click();
			
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
			
			List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(stageNameTextbox,20);


			logger.info("Number of stages are " + l2.size());
			assertTrue(l2.size() == Integer
					.parseInt(excelutil.getData("Settings_PipelinesTab", "MaxStageCount", xlsname)),"Number of stages are "+l2.size()+ " instead of 40");

			test.log(LogStatus.PASS, "Pipeline has "+l2.size()+ " stages");

			
			

		
		}

	}

	@Override
	public void verifyDeletePipeline() {

		clickAddPipeline();

		// To fetch pipeline name from excel + add random value
		String pipelineNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");

		pipelineNameCreated = pipelineNameFromExcl;

		// To fetch stage name from excel+ add random value
		String firstStageNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

		// to fill stage name
		sendkeys(stageNameTextbox, firstStageNameFromExcl,
				"Able to enter stage name as " + firstStageNameFromExcl + " in add pipeline popup",
				"Unable to enter stage name");


		click(saveButton, "Able to click on Save button and Popup is closed", "Unable to click on save button");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
//Search created pipeline
		sendkeys(searchInPipelinesTab, pipelineNameFromExcl, "Able to search newly created pipeline",
				"Unable to search pipeline");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBe(pipelineName,pipelineNameFromExcl));
		

		// Click delete
		click(deleteIcon, "Able to click on delete and delete Popup is displayed", "Unable to click on delete");

		sendkeys(deleteTextBox, excelutil.getData("Settings_PipelinesTab", "TypeDelete", xlsname),
				"Able to enter delete", "Unable to enter");

		click(submitButton, "Able to click on Submit button and delete Popup is closed",
				"Unable to click on Submit button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		// Verify no items as deleted pipeline
		isdisplay(noItems, "There are no items to display is displayed",
				"There are no items to display is NOT displayed");
		assertTrue(WaitUtils.waitClickByRef(noItems).isDisplayed(), "There are no items to display is NOT displayed");
		test.log(LogStatus.PASS, "Pipeline is deleted");

	}

	@Override
	//To delete stage, check stage is deleted or not
	public void deleteStage() {
		
		clickAddPipeline();

		// To fetch pipeline name from excel + add random value
		String pipelineNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Pipeline Name", xlsname);

		pipelineNameCreated = pipelineNameFromExcl;

		logger.info("Assigned to class variable " + pipelineNameCreated);

		// To fill pipeline name
		sendkeys(pipelineNameTextbox, pipelineNameFromExcl,
				"Able to enter pipeline name as " + pipelineNameFromExcl + " in add pipeline popup",
				"Unable to enter pipeline name");

		// To fetch stage name from excel+ add random value
		String firstStageNameFromExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Stage Name", xlsname);

		// to fill stage name
		sendkeys(stageNameTextbox, firstStageNameFromExcl,
				"Able to enter stage name as " + firstStageNameFromExcl + " in add pipeline popup",
				"Unable to enter stage name");

		// To click add stage button
		click(addStageButton, "Able to click on add stage button", "Unable to click on add stage button");

		// To fetch second stage name from excel
		String secondStageNameExcl = randomGenerator()
				+ excelutil.getData("Settings_PipelinesTab", "Second Stage Name", xlsname);

		// to fill 2nd stage name
		sendkeys(newStageAfterClickAddStage, secondStageNameExcl,
				"Able to enter second stage name as " + secondStageNameExcl + " in add pipeline popup",
				"Unable to enter second stage name");


		click(saveButton, "Able to click on Save button and Popup is closed", "Unable to click on save button");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		sendkeys(searchInPipelinesTab, pipelineNameFromExcl, "Able to search pipeline", "Unable to search pipeline");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName,pipelineNameFromExcl));

		click(editIcon, "Able to click on edit", "Unable to click on edit");

		// Delete stage
		String firstStageName = 
				WaitUtils.waitClickByRef(stageFirst).getAttribute("value");
		String secondStageName = 
				WaitUtils.waitClickByRef(stageSecond).getAttribute("value");

		logger.info("1st stage name is " + firstStageName);
		logger.info("2nd stage name is " + secondStageName);

		List<WebElement> l1 =WaitUtils.waitVisibilityOfDropdownElements(stageNameTextbox,10);

		test.log(LogStatus.PASS, "Number of stages are "+l1.size());
		
		// To delete added stage
		click(deleteStageSecond, "Able to click on delete of 2nd stage and delete Popup is dispalyed",
				"Unable to click on delete icon");

		String ActualDeleteStagePlaceholder =
				WaitUtils.waitClickByRef(deleteTextBox).getAttribute("placeholder");

		assertTrue(ActualDeleteStagePlaceholder
				.equals(excelutil.getData("Settings_PipelinesTab", "ExpectedDeleteStagePlaceholder", xlsname)));

		test.log(LogStatus.PASS, "Placeholder is " + ActualDeleteStagePlaceholder);


		// To check opportunities text
		isdisplay(
				By.xpath("//h3[contains(text(),'All opportunities in')]"), "All opportunities in " + secondStageName
						+ " will be moved to " + firstStageName + " text is displayed",
				"All opportunities Text is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(By.xpath("//h3[contains(text(),'All opportunities in')]")).isDisplayed(),
				"All opportunities Text is NOT displayed");

		assertTrue(
				WaitUtils.waitClickByRef(By.xpath("//h3[contains(text(),'will be moved to')]")).isDisplayed(),
				"All opportunities Text is NOT displayed");

		isdisplay(deleteStageMsgText, "Are you sure you want to delete this stage? is displayed",
				"Are you sure you want to delete this stage? is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(deleteStageMsgText).isDisplayed(),
				"Are you sure you want to delete this stage? is NOT displayed");

		isdisplay(deletePleaseEnterText, "Please enter word Delete to continue is displayed",
				"Please enter word Delete to continue is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(deletePleaseEnterText).isDisplayed(),
				"Please enter word Delete to continue is NOT displayed");

		sendkeys(deleteTextBox, excelutil.getData("Settings_PipelinesTab", "TypeDelete", xlsname),
				"Able to enter delete", "Unable to enter delete");

		click(submitButton, "Able to click on Submit button and delete stage Popup is closed",
				"Unable to click on submit button");

		List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(stage,10);

		logger.info("l1 size is " + l1.size());

		logger.info("l2 size is " + l2.size());

		assertTrue(l1.size() == l2.size() + 1, "Stage is NOT deleted");

		test.log(LogStatus.PASS, "Stage is deleted");

		click(updateButton, "Able to click on Update button", "Unable to click on Update button");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		WaitUtils.waitClickByRef(editIcon).click();
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		List<WebElement> l3 = WaitUtils.waitVisibilityOfDropdownElements(stage,10);
		
		test.log(LogStatus.PASS, "Number of stages are  "+l3.size()+" after deleting stage ");
		
		WaitUtils.waitClickByRef(closeIcon).click();
		
		
	}

	
	
	@Override
	//To check/make pipeline is active
	public void pipelineActiveInActive() {

		// Filter with All to see inactive pipelines
		selectFilter("All");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		String expectedActivePipelineName=excelutil.getData("Settings_PipelinesTab", "PipelineNameForActiveInActive", xlsname);
		
		sendkeys(searchInPipelinesTab,expectedActivePipelineName,
				"Able to search "+expectedActivePipelineName, "Unable to search pipeline name");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);


		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName,expectedActivePipelineName));
		
		WaitUtils.waitClickByRef(toggleClick).click();
		
		try {
		toggleStatus=WaitUtils.waitClickByRef(enableTextInActivePopup,10).isDisplayed();
		
		if(toggleStatus)
		{
			// To type Active
			sendkeys(typeActive, excelutil.getData("Settings_PipelinesTab", "TypeActive", xlsname),
					"Able to enter Active in Pop Up", "Unable to enter Active in Pop Up");

			// Click submit and so pipeline becomes inactive
			click(submitButton, "Able to click on Submit button.Pop Up is closed and Pipeline is Activated",
					"Unable to click on Submit button");
			
			test.log(LogStatus.PASS, expectedActivePipelineName+" becomes Active");
			
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);	
			
		}
		
	
		
		}
		catch(NoSuchElementException | TimeoutException e)
		{
			WaitUtils.waitClickByRef(closeIcon).click();
			test.log(LogStatus.PASS, expectedActivePipelineName+" is Active");
		}
		
		
		clear(searchInPipelinesTab, "Able to clear Pipeline search", "Unable to clear pipeline search");
		
		String expectedInActivePipelineName=excelutil.getData("Settings_PipelinesTab", "PipelineNameForActiveInActiveSecond", xlsname);
		
		sendkeys(searchInPipelinesTab,expectedInActivePipelineName,
				"Able to search pipeline name "+expectedInActivePipelineName, "Unable to search pipeline name");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		wait.until(ExpectedConditions.textToBePresentInElementLocated(pipelineName,expectedInActivePipelineName));
		
		WaitUtils.waitClickByRef(toggleClick).click();
		
		try {
			toggleProgress=WaitUtils.waitClickByRef(disableTextInActivePopup,10).isDisplayed();
		
		if(toggleProgress)
		{
			// To type InActive
			sendkeys(typeInActive, excelutil.getData("Settings_PipelinesTab", "TypeInActive", xlsname),
					"Able to enter InActive in Pop Up", "Unable to enter InActive in Pop Up");

			// Click submit and so pipeline becomes inactive
			click(submitButton, "Able to click on Submit button.Pop Up is closed and Pipeline is inactivated",
					"Unable to click on Submit button");
			
			test.log(LogStatus.PASS, expectedInActivePipelineName+" becomes InActive");
			
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);	
			
		}
		
		
		}
		catch(NoSuchElementException | TimeoutException e)
		{
			WaitUtils.waitClickByRef(closeIcon).click();
			test.log(LogStatus.PASS, expectedInActivePipelineName+" is InActive");
			
		}
			

	}

	@Override
	//To check status custom popup fields and errors
	public void settingsGearIcon() {

		// check gear icon
		isdisplay(gearIcon, "Settings gear icon is displayed", "Settings gear icon is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(gearIcon).isDisplayed(), "Settings gear icon is NOT dispalyed");

		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// Check status label
		isdisplay(statusLabelHeading, "Status Label Change heading is displayed",
				"Status Label Change heading is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(statusLabelHeading).isDisplayed(),
				"Status Label Change heading is NOT dispalyed");

		// check Note
		isdisplay(notes, "Note is displayed", "Note is NOT dispalyed");
		assertTrue(WaitUtils.waitClickByRef(notes).isDisplayed(), "Note is NOT dispalyed");

		// check Reset
		isdisplay(resetButton, "Reset button is displayed", "Reset button is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(resetButton).isDisplayed(), "Reset button is NOT dispalyed");

		// check default status label
		isdisplay(defaultLabel, "Default Status Labels is displayed", "Default Status Labels is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(defaultLabel).isDisplayed(), "Default Status Labels is NOT dispalyed");

		// check display status label
		isdisplay(displayLabel, "Display Status Labels is displayed", "Display Status Labels is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(displayLabel).isDisplayed(), "Display Status Labels is NOT dispalyed");

		// check cancel button
		isdisplay(cancelButton, "Cancel button is displayed", "Cancel button is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(cancelButton).isDisplayed(), "Cancel button is NOT dispalyed");

		// check Save button
		isdisplay(saveButton, "Save button is displayed", "Save button is NOT dispalyed");
		assertTrue(
				WaitUtils.waitClickByRef(saveButton).isDisplayed(), "Save button is NOT dispalyed");

		// Check notesText
		assertTrue(
				WaitUtils.waitClickByRef(notes).getText()
				.contains(excelutil.getData("Settings_PipelinesTab", "Note", xlsname)), "Notes Text is NOT matched");
		test.log(LogStatus.PASS, 
				WaitUtils.waitClickByRef(notes).getText() + " is displayed");

		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(defaultLabelValues,20);


		for (WebElement ele : l1) {

			String defaultLabelName = ele.getAttribute("value");

			logger.info(defaultLabelName);

			if (defaultLabelName.equals("Open") && !ele.isEnabled()) {
				assertTrue(defaultLabelName.equals("Open") && !ele.isEnabled());
				test.log(LogStatus.PASS, "Open is displayed and Open field is in disable mode");
			}

			else if (defaultLabelName.equals("Won") && !ele.isEnabled()) {
				assertTrue(defaultLabelName.equals("Won") && !ele.isEnabled());
				test.log(LogStatus.PASS, "Won is displayed and Won field is in disable mode");
			}

			else if (defaultLabelName.equals("Lost") && !ele.isEnabled()) {
				assertTrue(defaultLabelName.equals("Lost") && !ele.isEnabled());
				test.log(LogStatus.PASS, "Lost is displayed and Lost field is in disable mode");
			}

			else if (defaultLabelName.equals("Abandoned") && !ele.isEnabled()) {
				assertTrue(defaultLabelName.equals("Abandoned") && !ele.isEnabled());
				test.log(LogStatus.PASS, "Abandoned is displayed and Abandoned field is in disable mode");
			}
		}
		
		
		List<WebElement> l2 = WaitUtils.waitVisibilityOfDropdownElements(displayLabelValues,20);
boolean flag=false;
		
		for (WebElement ele : l2) {
			
			ele.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			if(ele.isEnabled())
			{
				flag=true;
			}
			

			//String displayLabelName = ele2.getAttribute("value");


			//logger.info(displayLabelName);
			
			
/*
			if (displayLabelName.equals("Open") && ele2.isEnabled()) {
				assertTrue(displayLabelName.equals("Open") && ele2.isEnabled());
				test.log(LogStatus.PASS, "Open is displayed and Open field is in enable mode");
				ele2.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);

			}

			else if (displayLabelName.equals("Won") && ele2.isEnabled()) {
				assertTrue(displayLabelName.equals("Won") && ele2.isEnabled());
				test.log(LogStatus.PASS, "Won is displayed and Won field is in enable mode");
				// ele2.clear();
				ele2.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			}

			else if (displayLabelName.equals("Lost") && ele2.isEnabled()) {
				assertTrue(displayLabelName.equals("Lost") && ele2.isEnabled());
				test.log(LogStatus.PASS, "Lost is displayed and Lost field is in enable mode");
				// ele2.clear();
				ele2.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			}

			else if (displayLabelName.equals("Abandoned") && ele2.isEnabled()) {
				assertTrue(displayLabelName.equals("Abandoned") && ele2.isEnabled());
				test.log(LogStatus.PASS, "Abandoned is displayed and Abandoned field is in enable mode");
				// ele2.clear();
				ele2.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			}
			*/

		}
		
		if(flag)
		{
			test.log(LogStatus.PASS, "Display label is in enable mode");

		}
		

		click(saveButton, "Able to click on save button", "Unable to click on save button");

		isdisplay(statusRequireError, "Error : Status is Required is displayed",
				"Error : Status is Required is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(statusRequireError).isDisplayed(), "Error : Status is Required is NOT displayed");

		String actualPlaceholder = 
				WaitUtils.waitClickByRef(displayLabelValues).getAttribute("placeholder");

		assertTrue(excelutil.getData("Settings_PipelinesTab", "StatusPlaceholder", xlsname).equals(actualPlaceholder),
				"Placeholder is not matched");
		test.log(LogStatus.PASS, "Placeholder is " + actualPlaceholder);
		
		click(closeIcon, "Able to click on close icon and custom pop up is closed", "Unable to click on close icon");

	}

	@Override
	//To check status label is accepting alpha numeric and length is 40
	public void statusFieldValidation() {

		//click gear icon
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");

		//clear status label value
		WaitUtils.waitClickByRef(displayLabelValues).clear();

		// to get status name which has alphanumeric from excel
		String statusNameValue = excelutil.getData("Settings_PipelinesTab", "statusName", xlsname);

		// To fill status name
		sendkeys(displayLabelValues, statusNameValue, "Able to enter display status label name as " + statusNameValue,
				"Unable to enter display status label name");

		// To clear status name value
		clear(displayLabelValues, "Display status label is cleared", "Display status label field is NOT cleared");

		// to get status name from excel
		String statusNameLengthIs41 = excelutil.getData("Settings_PipelinesTab", "41LengthName", xlsname);

		// To get length of String
		logger.info("Before entering the status name length is " + statusNameLengthIs41.length());
		test.log(LogStatus.PASS, "Before entering status name the length is " + statusNameLengthIs41.length());

		// To fill status name
		sendkeys(displayLabelValues, statusNameLengthIs41, "Try to enter status name with length 41 characters",
				"Unable to enter status name");


		// to get pipeline name value
		String statusName = driver.findElement(displayLabelValues).getAttribute("value");

		// To get length after entering
		logger.info("After entering the status name length is  " + statusName.length());
		test.log(LogStatus.PASS, "After entering the status name length is " + statusName.length());

		assertTrue(statusName.length() == 40, "Status name is accepting more than 40 characters");
		logger.info("Status name field length is restricted to 40 characters");
		test.log(LogStatus.PASS, "Status name field length is restricted to 40 characters");

		click(resetButton, "Click on reset button and reset popup is displayed", "Unable to click on reset button");
		click(YesButton, "Able to click on Yes button and reset popup is closed", "Unable to click on yes button");

		test.log(LogStatus.INFO, "Status Labels after clciking on reset");
		
		//After clicking reset check status labels
		checkDisplayLabels();

		// click save button
		click(saveButton, "Able to click on Save button and custom popup is closed", "Unable to click on Save");

		// To verify toast summary
		// isdisplay(toastSummaryStatusLabelChanges, "Status Label Changes is displayed
		// in toast message","Status Label Changes is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastSummaryStatusLabelChanges).isDisplayed(),"Status
		// Label Changes is NOT displayed in toast message");

		// To verify toast detail
		// isdisplay(toastDetailStatusLabelChanges,"Status label changes saved
		// successfully is displayed in toast message","Status label changes saved
		// successfully is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastDetailStatusLabelChanges).isDisplayed(),"Status
		// label changes saved successfully is NOT displayed in toast message");

		// To verify toast icon
		// isdisplay(toastIcon, "Toast icon is displayed in toast message","Toast icon
		// is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastIcon).isDisplayed(), "Toast icon is NOT
		// displayed in toast message");

		// To verify toast close
		// isdisplay(closeIcon, "Toast close icon is displayed in toast message", "Toast
		// close icon is NOT displayed in toast message");
		// assertTrue(driver.findElement(toastIcon).isDisplayed(),"Toast close icon is
		// NOT displayed in toast message");
		

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	}

	@Override
	public void checkDisplayLabels() {
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(displayLabelValues,20);

		for (WebElement ele : l1) {

			String displayLabelName = ele.getAttribute("value");

			logger.info(displayLabelName);

			if (displayLabelName.equals("Open") && ele.isEnabled()) {
				assertTrue(displayLabelName.equals("Open") && ele.isEnabled());
				test.log(LogStatus.PASS, "Open is displayed and Open field is in enable mode");

			}

			else if (displayLabelName.equals("Won") && ele.isEnabled()) {
				assertTrue(displayLabelName.equals("Won") && ele.isEnabled());
				test.log(LogStatus.PASS, "Won is displayed and Won field is in enable mode");

			}

			else if (displayLabelName.equals("Lost") && ele.isEnabled()) {
				assertTrue(displayLabelName.equals("Lost") && ele.isEnabled());
				test.log(LogStatus.PASS, "Lost is displayed and Lost field is in enable mode");

			}

			else if (displayLabelName.equals("Abandoned") && ele.isEnabled()) {
				assertTrue(displayLabelName.equals("Abandoned") && ele.isEnabled());
				test.log(LogStatus.PASS, "Abandoned is displayed and Abandoned field is in enable mode");

			}

		}

	}

	@Override
	//Check  reset popup fields
	public void checkResetFields() {
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		click(resetButton, "Able to click on reset button", "Unable to click on reset button");

		isdisplay(applyDefaultLabel, "Are you sure you want to apply Default Status Labels ? is displayed",
				"Are you sure you want to apply Default Status Labels ? is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(applyDefaultLabel).isDisplayed(),
				"Are you sure you want to apply Default Status Labels ? is NOT displayed");

		isdisplay(NoButton, "No button is displayed", "No button is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(NoButton).isDisplayed(), "No button is NOT displayed");

		isdisplay(YesButton, "Yes button is displayed", "Yes button is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(YesButton).isDisplayed(), "Yes button is NOT displayed");

		isdisplay(closeIcon, "Close Icon is displayed", "Close Icon is NOT displayed");
		assertTrue(
				WaitUtils.waitClickByRef(closeIcon).isDisplayed(), "Close Icon is NOT displayed");

		click(closeIconReset, "Able to click on close icon and reset popup is closed", "Unable to click on close icon");

		click(closeIcon, "Able to click on close icon and custom popup is closed", "Unable to click on close icon");

	}

	@Override
	//To check values are not default values as clicked on No in reset 
	public void checkResetNo() {

		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		WaitUtils.waitClickByRef(displayLabelValues).clear();

		// to get status name from excel
		String actualStatusName = randomGenerator()+excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// To fill status name
		sendkeys(displayLabelValues, actualStatusName, "Able to enter display status label name as " + actualStatusName,
				"Unable to enter display status label name");

		click(resetButton, "Able to click on reset button and reset popup is displayed",
				"Unable to click on reset button");

		click(NoButton, "Able to click on No button and reset popup is closed", "Unable to click on No button");

		String expectedStatusName = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		logger.info("Actual is " + actualStatusName);
		logger.info("Expected is " + expectedStatusName);

		assertTrue(actualStatusName.equals(expectedStatusName), "Status names are NOT modified");

		test.log(LogStatus.PASS, "Status name after modification is " +expectedStatusName+ " after clicking on No in Reset popup");

		click(saveButton, "Able to click on Save button and custom popup is closed",
				"Unable to click on Save button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		click(gearIcon, "Able to click on gear icon again and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String StatusNameAfterSave = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		assertTrue(actualStatusName.equals(StatusNameAfterSave), "Status names are default values instead of modified values");

		test.log(LogStatus.PASS, "Status name after modification "+StatusNameAfterSave+ " as modified  after clicking on Save");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		click(closeIcon, "Able to click on close icon and custom popup is closed", "Unable to click on close icon");

		

	}

	@Override
	//To check values are not default values as clicked on close in reset
	public void checkResetClose() {
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		WaitUtils.waitClickByRef(displayLabelValues).clear();

		// to get status name from excel
		String actualStatusName = randomGenerator()+excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// To fill status name
		sendkeys(displayLabelValues, actualStatusName, "Able to enter display status label name as " + actualStatusName,
				"Unable to enter display status label name");

		//click reset button
		click(resetButton, "Able to click on reset button and reset popup is displayed",
				"Unable to click on reset button");

		//click close in reset popup
		click(closeIconReset, "Able to click on Close icon and reset popup is closed", "Unable to click on Close icon");

		String expectedStatusName = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		logger.info("Actual is " + actualStatusName);
		logger.info("Expected is " + expectedStatusName);

		assertTrue(actualStatusName.equals(expectedStatusName), "Status names are default values");

		test.log(LogStatus.PASS, "Status name after modification is " +expectedStatusName+ " after clicking on No in Reset popup");

		//click on save
		click(saveButton, "Able to click on Save button and custom popup is closed",
				"Unable to click on Save button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		//click on gear icon again after clicking on save
		click(gearIcon, "Able to click on gear icon again and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String StatusNameAfterSave = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		assertTrue(actualStatusName.equals(StatusNameAfterSave), "Status names are default values instead of modified values");

		assertTrue(actualStatusName.equals(StatusNameAfterSave), "Status names are default values instead of modified values");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		click(closeIcon, "Able to click on close icon and custom popup is closed", "Unable to click on close icon");


	}

	@Override
	//to check status name are NOT updated after clicking on cancel in custom popup
	public void checkCustomCancel() {
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		
		String statusNameBeforeUpdating =WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");


		WaitUtils.waitClickByRef(displayLabelValues).clear();

		// to get status name from excel
		String actualStatusName = randomGenerator()+excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// To fill status name
		sendkeys(displayLabelValues, actualStatusName, "Able to enter display status label name as " + actualStatusName,
				"Unable to enter display status label name");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		//Click cancel
		click(cancelButton, "Able to click on cancel button and custom popup is closed",
				"Unable to click on cancel button");

		//click gear again
		click(gearIcon, "Able to click on gear icon again and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		String expectedStatusName =WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		logger.info("Actual is " + actualStatusName);
		logger.info("Expected is " + expectedStatusName);

		assertTrue(statusNameBeforeUpdating.equals(expectedStatusName), "Status names are updated");
		logger.info("Status name after clicking on gear again " + expectedStatusName);
		
		test.log(LogStatus.PASS, "Status names are NOT updated");

		click(closeIcon, "Able to click on close icon and custom popup is closed",
				"Unable to click on close icon");

	}

	@Override
	//To check status names are NOT default values as reset is yes and clicking on cancel 
	public void checkResetYesCustomCancel() {
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String statusNameBefore = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		WaitUtils.waitClickByRef(displayLabelValues).clear();
		

		// to get status name from excel
		String actualStatusName = randomGenerator()+excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// To fill status name
		sendkeys(displayLabelValues, actualStatusName, "Able to enter display status label name as " + actualStatusName,
				"Unable to enter display status label name");

		//click reset button
		click(resetButton, "Able to click on Reset button and Reset popup is displayed",
				"Unable to click on Reset button");

		//click yes button
		click(YesButton, "Able to click on Yes button and Reset Popup is closed", "Unable to click on Yes button");

		String expectedStatusName = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		logger.info("Actual is " + actualStatusName);
		logger.info("Expected is " + expectedStatusName);

		assertTrue(!actualStatusName.equals(expectedStatusName), "Status names are modified to "+expectedStatusName+ " instead of default values");

		test.log(LogStatus.PASS, "Status names are NOT default values");
		
		//click cancel button
		click(cancelButton, "Able to click on cancel button and custom popup is closed",
				"Unable to click on cancel button");
		
		//click gear again
				click(gearIcon, "Able to click on gear icon again and customization Popup is displayed",
						"Unable to click on gear icon");
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				String statusNameAfterCancel =WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

				logger.info("Actual is " + actualStatusName);
				logger.info("Expected is " + expectedStatusName);
				logger.info("statusNameAfterCancel is " + statusNameAfterCancel);

				assertTrue(statusNameBefore.equals(statusNameAfterCancel), "Status names are modified");
				logger.info("Status name after clicking on gear again " + expectedStatusName);
				
				test.log(LogStatus.PASS, "Status names are NOT default values");

				click(closeIcon, "Able to click on close icon and custom popup is closed",
						"Unable to click on close icon");


	}

	@Override
	//Status names areNOT updated as clicking on close
	public void checkStatusNameCustomCloseIcon() {
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		String statusNameBefore = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		
		WaitUtils.waitClickByRef(displayLabelValues).clear();

		// to get status name from excel
		String actualStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// To fill status name
		sendkeys(displayLabelValues, actualStatusName, "Able to enter display status label name as " + actualStatusName,
				"Unable to enter display status label name");

		click(closeIcon, "Able to click on close icon and custom pop is closed", "Unable to click on close icon");

		click(gearIcon, "Able to click on gear icon again and customization Popup is displayed",
				"Unable to click on gear icon");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		String expectedStatusName = WaitUtils.waitClickByRef(displayLabelValues).getAttribute("value");

		logger.info("Actual is " + actualStatusName);
		logger.info("Expected is " + expectedStatusName);

		assertTrue(statusNameBefore.equals(expectedStatusName), "Status names are updated");

		test.log(LogStatus.PASS, "Status names are NOT updated");
		click(cancelButton, "Able to click on cancel button and custom popup is closed",
				"Unable to click on cancel button");
	}

	@Override
	//To update status names
	public void checkUpdateStatusNames() {

		// Fetching names from excel
		// to get status name from excel
		firstStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// to get status name from excel
		secondStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName2", xlsname);

		// to get status name from excel
		thirdStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName3", xlsname);

		// to get status name from excel
		fourthStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName4", xlsname);
		test.log(LogStatus.INFO, "Status names are updated ");


		// clicking on gear icon
		WaitUtils.waitClickByRef(gearIcon).click();

		test.log(LogStatus.PASS, "Able to click on gear icon and customization Popup is displayed");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// Fetching status names
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(displayLabelValues, 40);

		// iterating each one, clearing it, giving name from excel

		test.log(LogStatus.PASS, "Status Names are ");

		for (i = 0; i < l1.size(); i++) {
			l1.get(i).clear();
			if (i == 0) {
				l1.get(i).sendKeys(firstStatusName);
				test.log(LogStatus.PASS, firstStatusName);
			} else if (i == 1) {
				l1.get(i).sendKeys(secondStatusName);
				test.log(LogStatus.PASS, secondStatusName);
			} else if (i == 2) {
				l1.get(i).sendKeys(thirdStatusName);
				test.log(LogStatus.PASS, thirdStatusName);
			} else if (i == 3) {
				l1.get(i).sendKeys(fourthStatusName);
				test.log(LogStatus.PASS, fourthStatusName);
			}
		}

		WaitUtils.waitClickByRef(saveButton).click();
		test.log(LogStatus.PASS, "Able to click on Save button and custom popup is closed");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	@Override
	//To check status names are combination of default and updated values

	public void checkMixStatusNames() {
		waitforelement(longwaitvalue);
		click(gearIcon, "Able to click on gear icon and customization Popup is displayed",
				"Unable to click on gear icon");
		waitforelement(longwaitvalue);
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		waitforelement(longwaitvalue);
		click(resetButton,
				"Ablet to click on reset button and Popup of Are you sure you want to apply Default Status Labels ? is displayed",
				"Unable to click on reset button");
		waitforelement(longwaitvalue);
		click(YesButton, "Able to click on Yes button and popup is closed", "Unable to click on yes button");
		waitforelement(longwaitvalue);
		List<WebElement> l1 =WaitUtils.waitVisibilityOfDropdownElements(displayLabelValues,20);

		// to get status name from excel
		firstStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);
waitforelement(longwaitvalue);
		// to get status name from excel
		secondStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName2", xlsname);

		test.log(LogStatus.INFO, "Status names are updated ");

		for (i = 0; i < l1.size() - 2; i++) {
			l1.get(i).clear();
			if (i == 0) {
				l1.get(i).sendKeys(firstStatusName);
				test.log(LogStatus.PASS, "First dispaly label is updated to "+firstStatusName);
			} else if (i == 1) {
				l1.get(i).sendKeys(secondStatusName);
				test.log(LogStatus.PASS, "Second dispaly label is updated to "+secondStatusName);
			}

		}

		click(saveButton, "Able to click on Save button and custom popup is closed", "Unable to click on save button");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		click(gearIcon, "Able to click on gear icon again and customization Popup is displayed",
				"Unable to click on gear icon");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		List<WebElement> l2 =WaitUtils.waitVisibilityOfDropdownElements(displayLabelValues,20);

		waitforelement(extraverylongwaitvalue);
		
		for (WebElement ele : l2) 
		{
			String statusName = ele.getAttribute("value");
			logger.info("Status names are " + statusName);
			if (statusName.equalsIgnoreCase(firstStatusName)) {
				test.log(LogStatus.PASS, firstStatusName + " is updated");
			} else if (statusName.equalsIgnoreCase(secondStatusName)) {
				test.log(LogStatus.PASS, secondStatusName + " is updated");
			} else if (statusName.equalsIgnoreCase("Lost")) {
				test.log(LogStatus.PASS, "Lost is displayed");
			} else if (statusName.equalsIgnoreCase("Abandoned")) {
				test.log(LogStatus.PASS, "Abandoned is displayed");
			} else {
				test.log(LogStatus.FAIL, "Status names are NOT updated");
			}

		}
		click(cancelButton, "Able to click on cancel button and custom popup is closed",
				"Unable to click on cancel button");

		test.log(LogStatus.PASS, "Status names are mix of custom and default labels");

	}

	@Override
	public ArrayList<String> toGetElementListFromEachPage(By ref) {
		// Fetch elements
		List<WebElement> elements = WaitUtils.waitVisibilityOfDropdownElements(ref, 20);

		// iterating each element and adding to arraylist
		for (WebElement ele : elements) {
			elementsArrayList.add(ele.getText());

		}
		logger.info("size is " + elementsArrayList.size());
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
		// return toGetElementListFromEachPage(ref);

	}

	@Override
	public ArrayList<String> toGetUpdateStatusNames() {

		ArrayList<String> statusArray = new ArrayList<String>();

		// Fetching names from excel
		// to get status name from excel
		firstStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname);

		// to get status name from excel
		secondStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName2", xlsname);

		// to get status name from excel
		thirdStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName3", xlsname);

		// to get status name from excel
		fourthStatusName = excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName4", xlsname);
		test.log(LogStatus.INFO, "Status names are updated ");


		// clicking on gear icon
		WaitUtils.waitClickByRef(gearIcon).click();

		test.log(LogStatus.PASS, "Able to click on gear icon and customization Popup is displayed");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		// Fetching status names
		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(displayLabelValues, 40);

		// iterating each one, clearing it, giving name from excel

		test.log(LogStatus.PASS, "Status Names are ");

		for (i = 0; i < l1.size(); i++) {
			l1.get(i).clear();
			if (i == 0) {
				l1.get(i).sendKeys(firstStatusName);
				test.log(LogStatus.PASS, firstStatusName);
				logger.info("After sendkyes " + l1.get(i).getAttribute("value"));

			} else if (i == 1) {
				l1.get(i).sendKeys(secondStatusName);
				test.log(LogStatus.PASS, secondStatusName);
			} else if (i == 2) {
				l1.get(i).sendKeys(thirdStatusName);
				test.log(LogStatus.PASS, thirdStatusName);
			} else if (i == 3) {
				l1.get(i).sendKeys(fourthStatusName);
				test.log(LogStatus.PASS, fourthStatusName);
			}
			statusArray.add(l1.get(i).getAttribute("value"));
		}

		logger.info("Status values from custom popup are " + statusArray);
		WaitUtils.waitClickByRef(saveButton).click();
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		test.log(LogStatus.PASS, "Able to click on Save button and custom popup is closed");

		
		logger.info("Status names "+statusArray);
		return statusArray;

	}

}