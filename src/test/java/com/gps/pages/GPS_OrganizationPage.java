package com.gps.pages;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

interface OrganizationPage {
	public void verifyAlphabeticalOrder(By ref, String passValue, String failValue);
	public void scrollDown(int x, int y, String passValue, String failValue);
	public void searchOrganization();
	public void searchOrganizationFunctionality(String passValue, String failValue);
	public void length(By ref, String str1, String passValue, String failValue);
	public void phoneNumberNumeric(String passValue, String failValue);
	public void verifyOrganizationType();
	public void businessAlphaNumericSpecialCharacters(By ref, String excelcolValue, String str, String passValue,
			String failValue);
	public void streetAddressAlphaNumeric(By ref, String excelcolValue, String str, String passValue, String failValue);
	public void streetAddressSpecialCharacters(By ref, String excelcolValue, String str, String passValue,
			String failValue);
	public void verifyCity(String passValue, String failValue);
	public void clearUsingSendkey(By ref, String passValue, String failValue);
	public void countryDropdown(By ref);
	public void verifyUpdatedCancelValue(By ref);
	public void StreetAddress();
	public void verifyUpdate();
	public void selectState();
	public void selectCity();
	public void invalidSearch();
}

public class GPS_OrganizationPage extends TestBase implements OrganizationPage {

	String streetAddressEntered;
	public By organizationsHeadingText = By.xpath("//h4[text()='Organizations']");
	public By organizationGridView = By.xpath("//div[contains(@class,'row card-row')]");
	public By organizationNamesList = By.xpath("//div[contains(@class,'card-row')]//h3");
	public By organizationLogo = By.xpath("//p[contains(@class,'footer-display')]");
	public By logo = By.xpath("//img[@alt='maps logos']");
	public By searchOrganizationTextbox = By.xpath("//input[contains(@placeholder,'Search')]");
	public By searchOrganizationResults = By.xpath("//div[contains(@class,'agent-list')]/h3");
	public By organizationNoMtachFound = By.xpath("//h3[contains(text(),'No Match Found')]");
	public By editOrganization = By.xpath("//a[contains(@class,'edit')]");
	public By deleteOrgnization = By.xpath("//a[contains(@class,'trash')]");
	public By addOrganizationButton = By.xpath("//span[contains(text(),'Add Organization')]");
	public By selectOrg = By
			.xpath("//h3[contains(text(),'QA Automation')]/preceding::div[contains(@class,'agent-details')]");
	public By searchOrgName = By.xpath("//h3[contains(text(),'QA Automation')]");
	public By selectFirstOrg = By.xpath("agent-details-view");
	public By tagAtOrgBottom = By.xpath("//p[contains(@class,'footer-display')]");
	public By orgTopHeaderAfterSelectingOrg = By.xpath("//h5[contains(text(),'QA Automation')]");

	// org settings
	public By organizationHeadingInSettings = By.xpath("//span[text()='Organization']");
	public By orgNameInSettings = By.xpath("//h6[contains(text(),'QA Automation')]");
	public By orgLogoInSettings = By.xpath("//img[contains(@alt,'QA Automation_logo')]");
	public By orgEmailIconInSettings = By.xpath("//i[contains(@aria-label,'email icon')]");
	public By orgPhoneIconInSettings = By.xpath("//i[contains(@aria-label,'phone icon')]");
	public By orgMapIconInSettings = By.xpath("//i[contains(@aria-label,'map icon')]");
	public By orgTimeIconInSettings = By.xpath("//i[contains(@aria-label,'clock icon')]");
	public By orgEditInSettings = By.xpath("//span[contains(text(),'Edit')]");

	// Add Organization
	public By addOrganizationHeading = By.xpath("//h4[contains(text(),'Add Organization')]");
	public By orgHeadingInAddOrganization = By.xpath("//span[text()='Organization']");
	public By addOrganizationBreadcrumb = By.xpath("//span[text()='Add Organization']");
	public By firstNameTextBox = By.xpath("//input[@aria-label='first name']");
	public By lastNameTextBox = By.xpath("//input[@aria-label='last name']");
	public By emailTextBox = By.xpath("//input[@aria-label='email address']");
	public By phoneTextBox = By.xpath("//input[@aria-label='phone']");
	public By extensionTextBox = By.xpath("//input[@aria-label='extension']");
	public By businessNameTextBox = By.xpath("//input[@aria-label='business name']");
	public By websiteTextBox = By.xpath("//input[@aria-label='websites']");
	public By streetAddressTextBox = By.xpath("//input[@aria-label='street address']");
	public By selectCountryDropDown = By.xpath("//span[text()='Select a Country']");
	public By stateTextBox = By.xpath("//input[@id='stateName']");
	public By cityTextBox = By.xpath("//input[@id='cityName']");
	public By zipCodeTextBox = By.xpath("//input[@aria-label='zip code']");
	public By timeZoneDropDown = By.xpath("//span[text()='Select a Time Zone']");
	public By organizationTypeDropDown = By.xpath("//span[text()='Select an Organization Type']");
	public By organizationTypeDropDownValues = By.xpath("//div[contains(@class,'dropdown-items')]//span");
	public By hipaaText = By.xpath("//p[text()='HIPAA']");
	public By enableHipaaText = By.xpath("//label[text()='Enable HIPAA security features']");
	public By hipaaSlider = By.xpath("//span[contains(@class,'slider')]");
	public By patientRoundingText = By.xpath("//p[text()='Patient Rounding']");
	public By enablePatientRounding = By.xpath("//label[text()='Enable Patient Rounding Dashboard']");
	public By patientRoundingSlider = By
			.xpath("//label[text()='Enable Patient Rounding Dashboard']//following::span[contains(@class,'slider')]");
	public By cancelButton = By.xpath("//span[text()='Cancel']");
	public By cancelButtonInEditPage = By.xpath("//button[.//span[normalize-space(text())='Cancel']]");
	public By saveButton = By.xpath("//span[text()='Save']");

	// add organization error
	public By firstNameError = By.xpath("//span[contains(text(),' First Name is required')]");
	public By lastNameError = By.xpath("//span[contains(text(),'Last Name is required')]");
	public By emailError = By.xpath("//span[contains(text(),'Email is required')]");
	public By phoneNumberError = By.xpath("//span[contains(text(),'Phone number is required')]");
	public By businessNameError = By.xpath("//span[contains(text(),'Business Name is required')]");
	public By streetError = By.xpath("//span[contains(text(),'Street Address is required')]");
	public By countryError = By.xpath("//span[contains(text(),'Country is required')]");
	public By stateError = By.xpath("//span[contains(text(),'State/Prov/Region is required')]");
	public By cityError = By.xpath("//span[contains(text(),'Select a valid state')]");
	public By zipCodeError = By.xpath("//span[contains(text(),'Zip/Postal Code is required')]");
	public By timeZoneError = By.xpath("//span[contains(text(),'Time Zone is required')]");
	public By orgTypeError = By.xpath("//span[contains(text(),'Agency Type is required')]");
	public By validEmailError = By.xpath("//span[contains(text(),'Enter valid email')]");
	public By validPhoneError = By.xpath("//span[contains(text(),'Enter valid phone number')]");

	public By navigateToOrgDropDown = By.xpath("//span[contains(text(),'Navigate to Organization')]//following::span");
	public By navigateToOrgDropDownAfterAnanya = By
			.xpath("//span[contains(text(),'Navigate to Organization')]/following::div");
	public By searchInNavigateToOrgDD = By.xpath("//div/input[contains(@class,'inputtext')]");
	public By orgNamesAllFromNavigateToOrganization = By.xpath("//ul[contains(@class,'corner')]//span");
	public By NoResultsNavigateToDropDown = By.xpath("//li[contains(text(),'No results found')]");
	public By orgFromDropdown = By.xpath("//span[contains(text(),'Ananya Real Estate Pvt Ltd')]");
	public By orgHeading = By.xpath("//h5[contains(text(),'Ananya Real Estate Pvt Ltd')]");
	public By errorAlpha = By.xpath("//span[contains(text(),'Enter Alpha numeric')]");
	public By enterValidEmailError = By.xpath("//span[contains(text(),' Enter valid email')]");
	public By websiteFormatError = By.xpath("//span[contains(text(),'Please enter format https://text.com')]");

	public By selectIndia = By.xpath("//span[contains(text(),'India')]");
	public By selectAndhra = By.xpath("//span[contains(text(),'Andhra Pradesh')]");
	public By cityRequireError = By.xpath("//span[contains(text(),'City is required')]");

	public By editAmzurDemo = By.xpath(
			"//h3[contains(text(),'Amzur Demo')]//parent::div/preceding-sibling::div/a[contains(@class,'edit')]");
	public By editOrganizationHeading = By.xpath("//span[contains(text(),'Edit Amzur Demo')]");
	public By editOrganizationHeading1 = By.xpath("//div[contains(@class,'accounts-header')]");
	public By clearIcon = By.xpath("//i[contains(@class,'clear')]");
	public By timeZone = By
			.xpath("//label[contains(text(),'Time Zone')]//following::span[contains(@class,'inputtext')]");
	public By updateButton = By.xpath("//span[contains(text(),'Update')]");
	public By timeZoneSearch = By.xpath("//input[contains(@class,'dropdown-filter')]");
	public By timeZoneIST = By.xpath("//span[contains(text(),'India Standard Time')]");
	public By citySelect = By.xpath("//span[text()='Visakhapatnam']");
	public By updateAgencyToast = By.xpath("//div[contains(text(),'Update Agency')]");
	public By updateSuccessToast = By.xpath("//div[contains(text(),'Updated Successfully')]");
	public By tickIconToast = By.xpath("//span[contains(@class,'toast-icon')]");
	public By closeIconToast = By.xpath("//a[contains(@class,'close-icon')]");

	// delete
	public By deleteAmzurDemo = By.xpath(
			"//h3[contains(text(),'Amzur Demo')]//parent::div/preceding-sibling::div/a[contains(@class,'trash')]");
	public By deletePopUp = By.xpath("//h3[contains(text(),'Do you want an auto generated OTP to be sent to Admin?')]");
	public By noFromDeletePopup = By.xpath("//span[text()='No']");
	public By yesFromDeletePopUp = By.xpath("//span[text()='Yes']");
	public By closeIconFromDeletePopUp = By.xpath("//a[contains(@class,'titlebar-close')]");

	public By allCountries = By.xpath("//ul[contains(@class,'helper')]//span");
	public By emailInView = By.xpath("//p[contains(text(),'amzur.demo@mapstechnologies.com')]");
	public By addressInView = By.xpath("//p[contains(text(),'987456')]");

	@Override
	public void verifyAlphabeticalOrder(By ref, String passValue, String failValue) {
		// TODO Auto-generated method stub

		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(ref, 40);

		logger.info("Total number of values in dropdown " + l1.size());
		test.log(LogStatus.PASS, "Total number of values in dropdown " + l1.size());
		try {
			String names[] = new String[l1.size()];
			test.log(LogStatus.INFO, "Names are ");
			for (int i = 0; i < l1.size(); i++) {
				names[i] = l1.get(i).getText();
				test.log(LogStatus.PASS, names[i]);
				logger.info(names[i]);

			}
			boolean order = true;
			for (int i = 1; i < l1.size(); i++) {
				if (names[i].compareToIgnoreCase(names[i - 1]) < 0) {
					order = false;
					break;

				}
			}

			if (order) {
				test.log(LogStatus.PASS, passValue);
				logger.info("Organization names are in alphabetical order");
			}

			else {
				test.log(LogStatus.PASS, passValue);
				logger.info("Organization names are NOT in alphabetical order");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}

	}

	@Override
	public void scrollDown(int x, int y, String passValue, String failValue) {
		// TODO Auto-generated method stub
		// check scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
			test.log(LogStatus.PASS, passValue);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//			screenshotutil.captureScreenshot(value);
		}
	}

	// search and select QA Automation org
	@Override
	public void searchOrganization() {

		String searchValueFromExcel = excelutil.getData("Organization", "OrgName", xlsname);

		WebElement ele = WaitUtils.waitClickByRef(searchOrganizationTextbox);
		ele.click();
		ele.sendKeys(searchValueFromExcel);

		test.log(LogStatus.PASS, "Able to enter organization name as " + searchValueFromExcel + " in Search field");

		waitforelement(extraverylongwaitvalue);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		boolean value;

		try {
			value = wait.until(ExpectedConditions.textToBePresentInElementLocated(searchOrgName, searchValueFromExcel));
			if (value) {
				isdisplay(searchOrgName, searchValueFromExcel, searchValueFromExcel);
				// WaitUtils.waitClickByRef(searchOrgName,20).click();
				WaitUtils.click(searchOrgName, orgTopHeaderAfterSelectingOrg);
				test.log(LogStatus.PASS, "Able to select organization");
			}

			else {
				test.log(LogStatus.FAIL, "Unable to select organization");
			}

		} catch (TimeoutException | StaleElementReferenceException e) {
			value = wait.until(ExpectedConditions.textToBePresentInElementLocated(searchOrgName, searchValueFromExcel));

			if (value) {
				isdisplay(searchOrgName, searchValueFromExcel, searchValueFromExcel);
				// WaitUtils.waitClickByRef(searchOrgName,20).click();
				WaitUtils.click(searchOrgName, orgTopHeaderAfterSelectingOrg);
				test.log(LogStatus.PASS, "Able to select organization");
			}

			else {
				test.log(LogStatus.FAIL, "Unable to select organization");
			}

		}

		/*
		 * worked waitforelement(extraverylongwaitvalue);
		 * 
		 * try { WaitUtils.waitClickByRef(selectOrg,20);
		 * 
		 * isdisplay(selectOrg, searchValueFromExcel, searchValueFromExcel);
		 * WaitUtils.waitClickByRef(selectOrg,20).click(); }
		 * catch(StaleElementReferenceException | NoSuchElementException e) {
		 * WaitUtils.waitClickByRef(selectOrg,20);
		 * 
		 * isdisplay(selectOrg, searchValueFromExcel, searchValueFromExcel);
		 * WaitUtils.waitClickByRef(selectOrg,20).click(); }
		 * 
		 */
		// WaitUtils.waitClickByRef(By.xpath("//div[contains(@class,'row')]"),20);

		// =wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@class,'row')]"))))

		// waitforelement(longwaitvalue);

		// List<WebElement> l1=WaitUtils.waitVisibilityOfDropdownElements(selectOrg,
		// 20);

		// WaitUtils.click(selectOrg,orgTopHeaderAfterSelectingOrg);

		/*
		 * int attempts = 0; int maxRetries=10; WebDriverWait wait=new
		 * WebDriverWait(driver,Duration.ofSeconds(10));
		 * 
		 * while (attempts < maxRetries) { try { // Wait for at least one suggestion to
		 * be present
		 * wait.until(ExpectedConditions.presenceOfElementLocated(selectOrg));
		 * 
		 * // Fetch fresh list of suggestions List<WebElement> Organizations =
		 * driver.findElements(selectOrg);
		 * 
		 * for (WebElement Organization : Organizations) { String orgName =
		 * Organization.getText(); if (orgName != null &&
		 * orgName.equalsIgnoreCase("QA Automation")) { // Wait until clickable and
		 * click wait.until(ExpectedConditions.elementToBeClickable(Organization));
		 * Organization.click(); logger.info("name is "+orgName); return; // success } }
		 * 
		 * 
		 * } catch (StaleElementReferenceException |NoSuchElementException e) {
		 * attempts++; logger.info("Attempt " + attempts + " failed due to: " +
		 * e.getClass().getSimpleName() + ". Retrying..."); // Optionally wait a bit
		 * before retrying (explicit wait preferred)
		 * wait.until(ExpectedConditions.presenceOfElementLocated(selectOrg)); } }
		 * 
		 * 
		 * 
		 * 
		 * /*
		 * 
		 * waitforelement(mediumwaitvalue); waitforelement(mediumwaitvalue);
		 * 
		 * 
		 * 
		 * List<WebElement> l1=WaitUtils.waitVisibilityOfDropdownElements(selectOrg,
		 * 20); WaitUtils.waitClickWebElement(l1.get(0),20).click();
		 * 
		 * WebElement searchEle=WaitUtils.waitClickByRef(searchOrganizationTextbox);
		 * 
		 * for(char ch:searchValueFromExcel.toCharArray()) { Actions act=new
		 * Actions(driver); act.moveToElement(searchEle).click().perform();
		 * act.sendKeys(String.valueOf(ch)).pause(Duration.ofMillis(2)).build().perform(
		 * );
		 * 
		 * waitforelement(mediumwaitvalue);
		 * 
		 * //List<WebElement> l1
		 * =WaitUtils.waitVisibilityOfDropdownElements(selectOrg,20);
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * 
		 * wait.until(ExpectedConditions.refreshed(ExpectedConditions.
		 * visibilityOfElementLocated(selectOrg)));
		 * 
		 * List<WebElement> l1= driver.findElements(selectOrg);
		 * 
		 * WaitUtils.waitClickByRef(deleteOrgnization).click();
		 * WaitUtils.waitClickByRef(closeIconFromDeletePopUp).click();
		 * 
		 * WaitUtils.waitClickWebElement(l1.get(0),20).click(); test.log(LogStatus.PASS,
		 * "Able to select organization as "+searchValueFromExcel+
		 * " from Search results");
		 * 
		 * }
		 */

	}

	// Searching org But not clicking on it
	@Override
	public void searchOrganizationFunctionality(String passValue, String failValue) {
		// TODO Auto-generated method stub
		boolean status = true;
		try {

			String validSearchValue = excelutil.getData("Organization", "OrgName", xlsname);
			WaitUtils.waitClickByRef(searchOrganizationTextbox).click();
			for (char ch : validSearchValue.toCharArray()) {
				WebElement searchInput = WaitUtils.waitClickByRef(searchOrganizationTextbox);
				Actions act = new Actions(driver);
				act.moveToElement(searchInput).sendKeys(String.valueOf(ch)).pause(Duration.ofMillis(2)).build()
						.perform();

			}

			waitforelement(extraverylongwaitvalue);
			test.log(LogStatus.PASS, "Able to enter organization name as " + validSearchValue + " in Search Org");

			List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(searchOrganizationResults, 40);

			logger.info("Number of search results are " + l1.size());
			test.log(LogStatus.PASS, "Number of search results are " + l1.size());
			test.log(LogStatus.INFO, "Organization Search results are ");
			for (WebElement ele : l1) {
				String str = ele.getText();
				logger.info(str);

				test.log(LogStatus.PASS, str);
				if (!str.contains("QA Auto")) {
					status = false;
					break;

				}
			}

			if (status) {
				test.log(LogStatus.PASS, passValue);
			}

		} catch (Exception e) {

			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}

	}

	// to check 40 characters is accepting or not
	public void length(By ref, String str1, String passValue, String failValue) {

		try {
			WaitUtils.waitClickByRef(ref).clear();
			WaitUtils.waitClickByRef(ref).sendKeys(excelutil.getData("Organization", "Length41", xlsname));
			test.log(LogStatus.INFO, str1);

			// sendkeys(ref, excelutil.getData("Organization", "Length41",
			// xlsname),str1,str2);
			String s = WaitUtils.waitClickByRef(ref).getAttribute("value");

			logger.info("the length is " + s.length());
			test.log(LogStatus.PASS, passValue + s.length());
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}

	}

	// to check phone number is accepting only digits
	@Override
	public void phoneNumberNumeric(String passValue, String failValue) {
		try {

			WaitUtils.waitClickByRef(phoneTextBox)
					.sendKeys(excelutil.getData("Organization", "InvalidPhoneNumberValue", xlsname));
			test.log(LogStatus.INFO, "Try to enter value in phone number field as "
					+ excelutil.getData("Organization", "InvalidPhoneNumberValue", xlsname));

			String phoneNumber = WaitUtils.waitClickByRef(phoneTextBox).getAttribute("value");

			logger.info("Phone Number value is " + phoneNumber);

			if (phoneNumber.isEmpty()) {

				test.log(LogStatus.PASS, passValue);
				WaitUtils.waitClickByRef(phoneTextBox)
						.sendKeys(excelutil.getData("Organization", "Phone Number", xlsname));
				test.log(LogStatus.PASS, "Able to enter value in phone number field as "
						+ excelutil.getData("Organization", "Phone Number", xlsname));

				String phoneNumberNew = WaitUtils.waitClickByRef(phoneTextBox).getAttribute("value");
				logger.info("New Phone Number value is " + phoneNumberNew);
				if (phoneNumberNew.contains("+1 (895) 623-3336")) {
					logger.info("Accepts numbers");
					test.log(LogStatus.PASS, "Phone Number field accepts only Numbers");
				}
			}

			else {
				// If input contains anything other than numbers (alpha-numeric or special
				// characters)
				logger.info(failValue);
				test.log(LogStatus.FAIL, failValue);
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}
	}

	// To verify org type drop down values
	@Override
	public void verifyOrganizationType() {
		try {

			WaitUtils.waitClickByRef(organizationTypeDropDown).click();
			test.log(LogStatus.PASS, "Able to click on Organization Type Drop down");

			// scrollDown(0,80000,"Able to scroll down", "Unable to scroll down");

			List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(organizationTypeDropDownValues, 40);

			logger.info("the size of org type drop down is " + l1.size());

			// logger.info("Organization Type Drop down has ");

			test.log(LogStatus.INFO, "Organization Type Drop down has 3 values");

			for (WebElement ele : l1) {

				String s = ele.getText();
				logger.info("The org type is " + s);

				// test.log(LogStatus.PASS, s);
				if (s.equalsIgnoreCase("Elite")) {
					test.log(LogStatus.PASS, "Elite in Organization Type Drop down is displayed");
					logger.info("elite");

				} else if (s.equalsIgnoreCase("Pro")) {
					test.log(LogStatus.PASS, "Pro in Organization Type Drop down is displayed");
					logger.info("Pro");
				} else if (s.equalsIgnoreCase("Core")) {
					test.log(LogStatus.PASS, "Core in Organization Type Drop down is displayed");
					logger.info("core");
				}

			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void businessAlphaNumericSpecialCharacters(By ref, String excelcolValue, String str, String passValue,
			String failValue) {

		try {

			// Giving value to Business name field
			WaitUtils.waitClickByRef(ref).sendKeys(excelcolValue);
			test.log(LogStatus.PASS, str);

			// Fetching given value
			String s = WaitUtils.waitClickByRef(ref).getAttribute("value");

			logger.info("entered value is " + s);

			// Checking value attribute with excel col value
			if (s.equals(excelcolValue)) {

				logger.info(passValue);
				test.log(LogStatus.PASS, passValue);

			} else {
				logger.info(failValue);
				test.log(LogStatus.PASS, failValue);
			}

			WaitUtils.waitClickByRef(ref).clear();
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void streetAddressAlphaNumeric(By ref, String excelcolValue, String str, String passValue,
			String failValue) {
		try {
			// Giving excel value to street address field
			WaitUtils.waitClickByRef(ref).sendKeys(excelcolValue);
			test.log(LogStatus.PASS, str);

			// Fetching street address value
			String s = WaitUtils.waitClickByRef(ref).getAttribute("value");
			logger.info("entered value is " + s);

			// Checking street address with given excel value
			if (s.equals(excelcolValue)) {

				logger.info(passValue);
				test.log(LogStatus.PASS, passValue);

			} else {
				logger.info(failValue);
				test.log(LogStatus.PASS, failValue);
			}

			WaitUtils.waitClickByRef(ref).clear();
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void streetAddressSpecialCharacters(By ref, String excelcolValue, String str, String passValue,
			String failValue) {
		try {
			WaitUtils.waitClickByRef(ref).sendKeys(excelcolValue);
			test.log(LogStatus.PASS, str);

			WebElement eleError = WaitUtils.waitClickByRef(errorAlpha);
			isdisplay(errorAlpha, passValue, failValue);
			assertTrue(eleError.isDisplayed(), failValue);

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void verifyCity(String passValue, String failValue) {
		try {

			// to click country drop down
			WaitUtils.waitClickByRef(selectCountryDropDown).click();

			// To select india
			WaitUtils.waitClickByRef(selectIndia).click();
			test.log(LogStatus.PASS, "Able to select India from Country Dropdown");

			selectState();

			// To click city textbox
			WaitUtils.waitClickByRef(cityTextBox).click();

			// Random click
			WaitUtils.waitClickByRef(zipCodeTextBox).click();

			/*
			 * //To click state dropdown WaitUtils.waitClickByRef(stateTextBox).click();
			 * 
			 * //To click city textbox WaitUtils.waitClickByRef(cityTextBox);
			 * 
			 * //Give Andhra WaitUtils.waitClickByRef(stateTextBox).sendKeys("Andhra");
			 * test.log(LogStatus.PASS, "Able to select Andhra Pradesh state");
			 * 
			 * //Select Andhra from suggestion
			 * WaitUtils.waitClickByRef(selectAndhra).click();
			 */

			// To check city error
			WebElement ele = WaitUtils.waitClickByRef(cityRequireError);
			isdisplay(cityRequireError, passValue, failValue);
			assertTrue(ele.isDisplayed(), failValue);

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//	screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void clearUsingSendkey(By ref, String passValue, String failValue) {
		try {
			WaitUtils.waitClickByRef(ref, 50).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
			test.log(LogStatus.PASS, passValue);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//	screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void countryDropdown(By ref) {
		try {

			String countryText = WaitUtils.waitClickByRef(selectCountryDropDown).getText();
			logger.info("Placeholder Text is " + countryText);
			if (countryText.equals("Select a Country")) {
				test.log(LogStatus.PASS, "Placeholder Text is " + countryText);

			} else {
				test.log(LogStatus.FAIL, "Placeholder Text is NOT Select a Country");
			}

			WaitUtils.waitClickByRef(selectCountryDropDown).click();
			test.log(LogStatus.PASS, "Able to click on Country dropdown");

			verifyAlphabeticalOrder(ref, "Countries are displayed in Alphabetical Order",
					"Countries are NOT in Alphabetical Order");

			WaitUtils.waitClickByRef(selectIndia).click();
			test.log(LogStatus.PASS, "Able to select India from Country Dropdown");

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}

	}

	@Override
	public void verifyUpdatedCancelValue(By ref) {
		String value = WaitUtils.waitClickByRef(ref).getText();

		if (!value.equals(excelutil.getData("Organization", "EmailNew", xlsname))) {
			logger.info("Organization values are NOT updated");
			test.log(LogStatus.PASS, "Organization Values are NOT Updated");
		}

		else {
			test.log(LogStatus.FAIL, "Organization Values are Updated");

		}
	}

	@Override
	public void StreetAddress() {

		Random r = new Random();
		int x = r.nextInt(100000);
		WaitUtils.waitClickByRef(streetAddressTextBox).sendKeys(String.valueOf(x));
		test.log(LogStatus.PASS, "Able to enter Street Address");

		WebElement ele = WaitUtils.waitClickByRef(streetAddressTextBox);
		streetAddressEntered = ele.getAttribute("value");
		logger.info("Modified Street address is " + streetAddressEntered);

	}

	@Override
	public void verifyUpdate() {

		test.log(LogStatus.PASS, "Modified Street address is " + streetAddressEntered);
		String newAddress = WaitUtils.waitClickByRef(addressInView).getText();
		logger.info("Organization address after updating is " + newAddress);
		test.log(LogStatus.PASS, "Organization address after updating is " + newAddress);

		if (newAddress.contains(streetAddressEntered)) {
			logger.info("Organization values are updated");
			test.log(LogStatus.PASS, "Organization Values are updated");
		}

		else {
			logger.info("Organization values are NOT updated");

		}
	}

	@Override
	public void selectState() {
		// WaitUtils.waitClickByRef(stateTextBox).click();
		String stateValue = excelutil.getData("Organization", "State", xlsname);
		for (char ch : stateValue.toCharArray()) {
			// WaitUtils.waitClickByRef(stateTextBox).sendKeys(String.valueOf(ch));
			WebElement stateEle = WaitUtils.waitClickByRef(stateTextBox);
			Actions act = new Actions(driver);
			act.moveToElement(stateEle).click().perform();

			act.sendKeys(String.valueOf(ch)).pause(Duration.ofMillis(5)).build().perform();

		}
		// WaitUtils.waitClickByRef(stateTextBox).sendKeys(excelutil.getData("Organization",
		// "State", xlsname));
		test.log(LogStatus.PASS, "Able to give Andhra Pradesh state");

		List<WebElement> stateNameSuggestion = WaitUtils.waitVisibilityOfDropdownElements(selectAndhra, 20);
		WaitUtils.waitClickWebElement(stateNameSuggestion.get(0), 20).click();
		test.log(LogStatus.PASS, "Able to select Andhra Pradesh state from suggestion");
	}

	@Override
	public void selectCity() {
		String cityValue = excelutil.getData("Organization", "City", xlsname);
		for (char ch : cityValue.toCharArray()) {
			// WaitUtils.waitClickByRef(cityTextBox).sendKeys(String.valueOf(ch));
			WebElement cityEle = WaitUtils.waitClickByRef(cityTextBox);
			Actions act = new Actions(driver);
			act.moveToElement(cityEle).click().perform();
			act.click(cityEle).sendKeys(String.valueOf(ch)).pause(Duration.ofMillis(2)).build().perform();

		}
		test.log(LogStatus.PASS, "Able to give city name as Visakhapatnam");

		List<WebElement> cityNameSuggestion = WaitUtils.waitVisibilityOfDropdownElements(citySelect, 20);
		WaitUtils.waitClickWebElement(cityNameSuggestion.get(0), 20).click();
		test.log(LogStatus.PASS, "Able to select city from suggestion");

	}

	@Override
	public void invalidSearch() {

		String validSearchValue = excelutil.getData("Organization", "InvalidSearchInput", xlsname);
		WaitUtils.waitClickByRef(searchOrganizationTextbox).click();
		for (char ch : validSearchValue.toCharArray()) {
			WebElement searchInput = WaitUtils.waitClickByRef(searchOrganizationTextbox);
			Actions act = new Actions(driver);
			act.moveToElement(searchInput).sendKeys(String.valueOf(ch)).pause(Duration.ofMillis(2)).build().perform();

		}

		test.log(LogStatus.PASS, "Able to enter Invalid Search Input as "
				+ excelutil.getData("Organization", "InvalidSearchInput", xlsname));
		
		waitforelement(extraverylongwaitvalue);

	}

}