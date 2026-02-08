package com.gps.pages;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;
import com.gps.pages.GPS_SettingsPipelinesPage;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface rulesPage {
	public void selectRules();

	public void checkUpdateStatusName();
}

public class GPS_RulesPage extends TestBase implements rulesPage {

	public By automationExpandIcon = By.xpath("//a[contains(@href,'automation')]/span");
	public By rules = By.xpath("//span[contains(text(),'Rules')]");
	public By editIcon1 = By.xpath("//a[contains(@title,'Edit')]");
	public By editIcon = By.xpath(
			"//h5[contains(text(),'Sph dont delete rule1 aged Opportunity')]//following::a[contains(@class,'edit')]");
	public By statusExpand = By
			.xpath("//p-dropdown[contains(@formcontrolname,'opportunityStatusId')]//span[contains(@class,'trigger')]");
	public By statusList = By.xpath("//li[contains(@class,'dropdown')]/span");
	public By titleRules = By.xpath("//h4[contains(text(),'Rules')]");
	public By dropDownStatus = By.xpath("//span[@class='ng-tns-c8-304 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']");
	public By buttonAddNewFolder= By.xpath("//span[contains(text(),'Add New Folder')]");
	public By buttonAddNewRule = By.xpath("//span[contains(text(),'Add New Rule')]");
	public By titleAddNewFolder = By.xpath("//h3[contains(text(),'Add New Folder')]");
	public By titleAddNewRule = By.xpath("//h3[contains(text(),'Add New Rule')]");
	public By labelFolderName = By.xpath("//label[contains(text(),'Folder Name*')]");
	public By buttonClose = By.xpath("//span[contains(text(),'Close')]");
	public By buttonSave = By.xpath("//span[contains(text(),'Save')]");
	public By buttonUpdate = By.xpath("//span[contains(text(),'Update')]");
	public By errorFolderName = By.xpath("//span[contains(text(),' Folder name is required. ')]");
	public By inputfolderName = By.xpath("//input[@id='folderName']");
	public By iconArrow=By.xpath("//span[@class='ui-accordion-toggle-icon pi pi-fw pi-chevron-right']");
	public By iconthreedots=By.xpath("//i[@class='pi pi-ellipsis-v cursor-pointer rules-split']");
	public By textNewRuleName=By.xpath("//input[@id='newRuleName']");
	public By textRuleName=By.xpath("//input[@id='ruleName']");
	public By dropDownSelectOrg=By.xpath("//span[contains(text(),'Select Organizations')]");
	public By textOrganzation=By.xpath("//input[@class='ui-inputtext ui-widget ui-state-default ui-corner-all']");
	public By textFolderName=By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");
	public By buttonCancel=By.xpath("//span[contains(text(),'Cancel')]");
	public By buttonCopy=By.xpath("//span[contains(text(),'Copy')]");
	public By labelRuleName=By.xpath("//label[contains(text(),'Rule Name *')]");
	public By dropdownSelectFolder=By.xpath("//span[contains(text(),'Select Folder')]");
	public By iconBack=By.xpath("//a[@class='icon-edo-reply fs-24 m-t-3 m-r-10 cursor-pointer']");
	public By buttonYes=By.xpath("//span[contains(text(),'Yes')]");
	public By buttonNo=By.xpath("//span[contains(text(),'No')]");
	public By buttonDelete=By.xpath("//a[@class='icon icon-edo-trash']");
	public By textEnterPhase=By.xpath("//input[@id='defineCondition']");
	public By textEnterPhase_1=By.xpath("//input[@class='form-control ng-untouched ng-pristine ng-invalid ng-star-inserted']");
	public By textFormSubmission=By.xpath("//div[text()='Form Submission']");
	public By buttonAddActions=By.xpath("//span[contains(text(),'Add Action')]");
	public By errorSelectAnActionItem=By.xpath("//span[contains(text(),' Select an Action item. ')]");
	public By buttonClose_1=By.xpath("//span[@class='icon icon-edo-close fs-12  m-t-3 float-right cursor-pointer']");
	public By textTagName=By.xpath("//input[@placeholder='Enter Tag Name']");
	public By iconClose=By.xpath("//i[@aria-label='close icon']");
	public By toggleOn=By.xpath("//div[@class='ui-inputswitch ui-widget ui-inputswitch-checked']");
	public By toggleOff=By.xpath("//div[@class='ui-inputswitch ui-widget']");
	public By textOpportunityName= By.xpath("//input[@id='opportunityName']");
	public By dropDownSelectAPipeline= By.xpath("//span[contains(text(),'Select a Pipeline')]");
	public By dropDownSelectStage= By.xpath("//span[contains(text(),'Select Stage')]");
	public By textLeadValue= By.xpath("//input[@placeholder='Enter Lead Value']");
	public By textEnterSource= By.xpath("//input[@placeholder='Enter Source']");
	public By textDollar= By.xpath("//div[contains(text(),'$')]");
	public By textFromEmail= By.xpath("//input[@placeholder='From Email']");
	public By textToEmail= By.xpath("//input[@placeholder='To Email']");
	public By textSubject= By.xpath("//input[@placeholder='Subject']");
	public By textfieldContentEditingTools= By.xpath("//html/body[@aria-label='Rich Text Area. Press ALT-0 for help.']");
	public By tableCustomValues= By.xpath("//span[contains(text(),'Custom Values')]");
	public By linkAttachment= By.xpath("//button[@aria-label='Insert/edit link']/span[@class='tox-icon tox-tbtn__icon-wrap']");
	public By buttonSendTest= By.xpath("//span[contains(text(),'Send Test Email')]");
	public By iconUndo= By.xpath("//button[@title='Undo']");
	public By iconRedo= By.xpath("//button[@title='Redo']");
	public By linkeditlink= By.xpath("//button[@title='Insert/edit link']");
	public By linkeditimage= By.xpath("//button[@title='Insert/edit image']");
	public By linkUpload= By.xpath("//div[contains(text(),'Upload')]");
	public By buttonBrowseForImage= By.xpath("//button[contains(text(),'Browse for an image')]");
	public By buttonCancel_1= By.xpath("//button[contains(text(),'Cancel')]");
	public By buttonOk= By.xpath("//button[contains(text(),'OK')]");
	public By buttonCopyRule= By.xpath("//button[@pbutton and @ptooltip='Copy Rule']");
	public By buttonDeleteRule= By.xpath("//button[@pbutton and @ptooltip='Delete Rule']");



	//h5[contains(text(),'Raghav_Please DonotDelete')]/../../div[2]/i[@class='pi pi-ellipsis-v cursor-pointer rules-split']
	
	
	public void elementTextClickForThreedots(String locator, String classValue, String passValue, String failValue) throws AWTException {
	    // This matches the exact class as in your example
	    String xpath = "//" + locator + "[contains(text(), '" + classValue + "')]/../../div[2]/i[@class='pi pi-ellipsis-v cursor-pointer rules-split']";


	    WaitUtils.waitVisibilityByRef(By.xpath(xpath));
	    actionclick(driver.findElement(By.xpath(xpath)), passValue, failValue);
	}

	
	public void ListofElement(By value, String passValue) {
	    try {
	        List<WebElement> elements = driver.findElements(value);


	        for (WebElement el : elements) {
	        	Actions actions = new Actions(driver);
	            if (el.isEnabled()) {
	                try {
	                    actions.moveToElement(el).click().perform();

	                } catch (Exception  te) {
	                }
	            }
	        }

	        test.log(LogStatus.PASS, passValue);

	    } catch (Exception e) {
	        throw new RuntimeException("Error while clicking list of elements: " + e.getMessage(), e);
	    }
	}


	
	
	
	
	@Override
	public void selectRules() {
		click(automationExpandIcon, "Able to click on Automation icon", "Unable to click automation icon");
		click(rules, "Able to select rules and Rules page is displayed", "Unable to select Rules page");
	}

	@Override
	// To check updated status names in rules
	public void checkUpdateStatusName() {

		String firstName = GPS_SettingsPipelinesPage.firstStatusName;
		String secondName = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdName = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthName = GPS_SettingsPipelinesPage.fourthStatusName;
		click(editIcon, "Able to click on edit icon and rule is opened", "Unable to click on edit icon");

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		click(statusExpand, "Able to click on status dropdown", "Unable to click on status dropdown");

		List<WebElement> l1;

		try {
			l1 = WaitUtils.waitVisibilityOfDropdownElements(statusList, 20);
			logger.info("status size " + l1.size());
			for (WebElement ele : l1) {
				String statusName = ele.getText();
				logger.info(statusName);

				if (statusName.equals(firstName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");

				}

				else if (statusName.equals(secondName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");

				} else if (statusName.equals(thirdName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");
				} else if (statusName.equals(fourthName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");
				}

				else {
					test.log(LogStatus.FAIL, "Status names are NOT Updated");

				}

			}
		} catch (StaleElementReferenceException e) {

			l1 = WaitUtils.waitVisibilityOfDropdownElements(statusList, 20);
			logger.info("status size " + l1.size());
			for (WebElement ele : l1) {
				String statusName = ele.getText();
				logger.info(statusName);

				if (statusName.equals(firstName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");

				}

				else if (statusName.equals(secondName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");

				} else if (statusName.equals(thirdName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");
				} else if (statusName.equals(fourthName)) {
					test.log(LogStatus.PASS, "Updated status name " + statusName
							+ " is reflected in status dropdown of create opportunity popup");
				}

				else {
					test.log(LogStatus.FAIL, "Status names are NOT Updated");

				}

			}

		
		}

	}
	
	public void isdisplayed(String locator, String text, String targetValue, String passValue, String failValue) throws AWTException {
	     WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[contains(text(),'" + text + "')]/parent::*//a[@title='" + targetValue + "']"));
	    isdisplay1(driver.findElement(By.xpath("//" + locator + "[contains(text(),'" + text + "')]/parent::*//a[@title='" + targetValue + "']")), passValue, failValue);
	}
	
	public void elementclick(String locator, String text, String targetValue, String passValue, String failValue) throws AWTException {
	     WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[contains(text(),'" + text + "')]/parent::*//a[@title='" + targetValue + "']"));
	   
	     By element =By.xpath("//" + locator + "[contains(text(),'" + text + "')]/parent::*//a[@title='" + targetValue + "']");
	     click(element, passValue, failValue);
	}
	
	
	public void Datecheck(By element,int datevalue,String passvalue,String failValue)
	{
		
		for (int i = 0; i <= datevalue; i++) {
		    WebElement input = driver.findElement(element);
		    input.clear();
		    input.sendKeys(String.valueOf(i));
		}
	}
	
	
	public void stringCompare(String actual,String expected,String passValue,String failValue)
	{
	      // one space

		if (actual.endsWith(expected)) {
			test.log(LogStatus.PASS, passValue);

			} else {
				test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
						+ ">click here for screenshot</p></body></html>");

				screenshotutil.captureScreenshot(value);
			}
		}
		

}
