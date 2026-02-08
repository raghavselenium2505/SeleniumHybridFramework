package com.gps.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.bytedeco.javacpp.chrono.Seconds;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface SequencePage {
	public void isDisplay(String locator, String userType, String passValue, String failValue);
}

public class GPS_SequencePage extends TestBase implements SequencePage {

	public By linkSequence = By.xpath(
			"//a[@class='ui-menuitem-link ui-corner-all ng-tns-c13-45 ng-star-inserted ui-menuitem-link-active']/span[contains(text(),'Sequences')]");
	public By linkSequenceLauncher = By.xpath(
			"//a[@class='ui-menuitem-link ui-corner-all ng-tns-c13-45 ng-star-inserted ui-menuitem-link-active' ]/span[contains(text(),'Sequence Launcher')]");

	public By linkRules = By.xpath(
			"//a[@class='ui-menuitem-link ui-corner-all ng-tns-c13-45 ng-star-inserted' ]/span[contains(text(),'Rules')]");
	public By titleSequence = By.xpath("//h4[contains(text(),'Sequences')]");

	public By textSearch = By.xpath("//input[@name='search']");
	public By buttonAddSequence = By.xpath("//span[contains(text(),'Add New Sequence')]");

	public By headerSequenceName = By.xpath("//th[contains(text(),'Sequence Name')]");
	public By headerTotal = By.xpath("//th[contains(text(),'Total')]");
	public By headerActive = By.xpath("//th[contains(text(),'Active')]");
	public By headerCompleted = By.xpath("//th[contains(text(),'Completed')]");
	public By headerReplied = By.xpath("//th[contains(text(),'Replied')]");
	public By headerReply = By.xpath("//th[contains(text(),'Reply %')]");
	public By headerStatus = By.xpath("//th[contains(text(),'Status')]");
	public By headerSequenceId = By.xpath("//th[contains(text(),'Sequence ID')]");

	public By textNoSearchResults = By.xpath("//h3[contains(text(),'No Match Found.')]");

	public By iconEdit = By.xpath("//a[@class='icon icon-edo-edit']");
	public By iconEdit_dup=By.xpath("//i[@aria-label='edit icons']");
	
	public By iconDelete = By.xpath("//a[@class='icon icon-edo-trash m-l-10 del-icon']");

	
	public By iconDelete_Dup=By.xpath("//i[@aria-label='delete icons']");
	public By textThereNoItemsDisplay = By.xpath("//h3[contains(text(),'There are no items to display.')]");
	public By titleAddNewSequence = By.xpath("//h3[contains(text(),'Add New Sequence')]");
	public By textSequenceName = By.xpath("//input[@id='sequenceName']");
	public By dropDownSelectFolder = By.xpath("//span[contains(text(),'Select Folder')]");
	public By buttonClose = By.xpath("//span[contains(text(),'Close')]");
	public By buttonSave = By.xpath("//span[contains(text(),'Save')]");

	public By errorSequenceName = By.xpath("//span[contains(text(),' Sequence Name is required. ')]");
	public By titleSequenceConfiguration = By.xpath("//h6[contains(text(),'Sequence Configuration ')]");

	public By linkback = By.xpath("//h6[@class='fs-18 fw-400 d-flex align-items-center']/a");
	public By rightPanel = By.xpath(
			"//button[@class='edo-btn-primary ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-right']");

	public By buttonYes = By.xpath("//span[contains(text(),'Yes')]");

	public By buttonNo = By.xpath("//span[contains(text(),'No')]");
	public By iconArrow = By.xpath("//a[@class='icon-edo-reply fs-24 m-t-3 m-r-10 cursor-pointer']");

	// New testcases
	public By textSequencename = By.xpath("//input[@placeholder='Enter Sequence Name']");
	public By toggleCustomTime = By.xpath("//p-inputswitch[@inputid='customTime']");

	public By dropDownSelectUser = By.xpath("//span[contains(text(),'Select Users')]");
	public By dropDownSelectSequence = By.xpath("//span[contains(text(),'Select Sequence')]");
	public By textFromAddress = By
			.xpath("//label[contains(text(),'From Address ')]/../input[@placeholder='Esearchnter Name']");
	public By textToAddress = By
			.xpath("//label[contains(text(),'From Address ')]/../input[@placeholder='Enter Email Address']");
	public By textTags = By.xpath("//input[@id='tags']");

	public By textEventDate = By.xpath("//input[@id='startDate']");

	public By toggleAllowDate = By
			.xpath("//p-inputswitch[@inputid='allowMultiple']//span[contains(@class, 'ui-inputswitch-slider')]");

	public By toggleStopOnResponse = By
			.xpath("//p-inputswitch[@inputid='stopOnResponse']//span[contains(@class, 'ui-inputswitch-slider')]");

	public By buttonAddEvent = By.xpath("//span[contains(text(),'Add Event')]");

	public By dropdownSms = By.xpath("//span[contains(text(),'SMS')]");
	public By dropdownMessenger = By.xpath("//span[contains(text(),'Messenger')]");
	public By dropdownEmail = By.xpath("//span[@class='ui-menuitem-text' and text()='Email']");
	public By dropdownVoiceemail = By.xpath("//span[contains(text(),'Voicemail')]");
	public By dropdownCall = By.xpath("//span[@class='ui-menuitem-text' and text()='Call']");
	public By dropdownWait = By.xpath("//span[contains(text(),'Wait')]");
	public By dropdownAddTask = By.xpath("//span[contains(text(),'Add Task')]");
	public By dropdownWebhook = By.xpath("//span[contains(text(),'Webhook')]");

	public By textSMS = By.xpath("//input[@id='name']");

	public By textEvent = By.xpath("//input[@id='eventName']");

	public By callWhiskper = By.xpath("//iframe[contains(@id, 'tiny-angular') and contains(@id, '_ifr')]");

	public By textWhisker = By.xpath("//body[@id='tinymce']");

	public By headerAddTask = By.xpath("//h4[contains(text(),'Add Task')]");

	public By labelAddTask = By.xpath("//label[contains(text(),'Task *')]");

	public By placeHolderAddTask = By.xpath("//input[@placeholder='Enter Task Name']");

	public By labelTaskDesc = By.xpath("//label[contains(text(),'Task Description *')]");

	public By placeHolderTaskDesc = By.xpath("//textarea[@placeholder='Enter Task Description']");

	public By labelDueIn = By.xpath("//label[contains(text(),'Due in *')]");

	public By placeholderDropDownDueIn = By.xpath("//span[contains(text(),'Select Due in')]");

	public By textTask = By.xpath("//input[@id='task']");

	public By textDate1 = By.xpath("//span[contains(text(),'1 day')]");

	public By textDate2 = By.xpath("//span[contains(text(),'2 days')]");

	public By textDate3 = By.xpath("//span[contains(text(),'3 days')]");

	public By textDate4 = By.xpath("//span[contains(text(),'4 days')]");

	public By iconDelete_1 = By.xpath("//i[@class='icon icon-edo-trash cursor-pointer ']");

	public By textDelete = By.xpath("//input[@id='exampleInputName']");

	public By buttonSubmit = By.xpath("//span[contains(text(),'Submit')]");

	public By textStartByEvent = By.xpath("//p[contains(text(),'- Start by adding an event to this sequence -')]");

	public By iconCross = By.xpath("//a[contains(@class, 'ui-dialog-titlebar-close')]");

	public By buttonUpdate = By.xpath("//span[contains(text(),'Update')]");

	public By dropDownDraft = By.xpath("//span[contains(text(),'Draft')]");

	public By buttonCopySequence = By.xpath("//span[contains(text(),'Copy Sequence')]");

	public By frameElement = By.xpath("//iframe[contains(@id,'tiny-angular') and contains(@id,'_ifr')]");

	public By headerDelete = By.xpath("//h3[contains(text(),'Please enter word') and contains(text(), 'Delete')]");

	public By placeholderDeleteText = By.xpath("//input[@placeholder='Type Delete']");

	public By errorConfirmationRequired = By.xpath("//span[contains(text(),' Confirmation text is required. ')]");

	public By eclipseIcon = By.xpath("//i[@class='pi pi-ellipsis-v cursor-pointer rules-split m-l-10']");

	public By ecipseIconMoveToFolder = By.xpath("//span[contains(text(),'Move to Folder')]");

	public By textMovetoFolder = By.xpath("//h3[contains(text(),'Move to Folder')]");

	public By headerFolderName = By.xpath("//label[contains(text(),'Folder Name  *')]");
	public By searchFolderDropDown = By
			.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By dropDownAddNewFolder = By.xpath("//span[contains(text(),'Add New Folder')]");
	public By titleAddNewFolder = By.xpath("//h3[contains(text(),'Add New Folder')]");
	public By textFolderName = By.xpath("//input[@name='folderName']");
	public By errorFolderName = By.xpath("//span[contains(text(),' Folder name is required. ')]");

	public By toggleCustomTimeOff = By.xpath("(//div[@class='ui-inputswitch ui-widget'])[1]");
	//public By toggleStopOnResponse = By.xpath("//div[@class='ui-inputswitch ui-widget ui-inputswitch-checked']");

	public By toggleCustomTimeON = By.xpath("(//div[@class='ui-inputswitch ui-widget ui-inputswitch-checked'])[1]");
public By toggleDisable = By.xpath("(//span[@class='ui-inputswitch-slider'])[1] ");
	public By dropDownSchedule = By
			.xpath("//span[contains(@class, 'ui-dropdown-label') and normalize-space(text())='WHEN']");

	public By selectIncludeDays = By.xpath(
			"(//div[@class='ui-button ui-widget ui-state-default ui-button-text-only ng-star-inserted ui-state-active'])[1]");

	public By dropDownStartDate = By
			.xpath("//span[contains(@class, 'ui-dropdown-label') and normalize-space(text())='8:00 am']");

	public By dropDownEndDate = By
			.xpath("//span[contains(@class, 'ui-dropdown-label') and normalize-space(text())='5:00 pm']");

	public By dropDownUsers = By
			.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default' and @role='checkbox']");

	public By dropDownUsers_1 = By.xpath("//span[@class='ui-multiselect-label ui-corner-all']");

	public By textSearch_Users = By
			.xpath("//input[@class='ui-inputtext ui-widget ui-state-default ui-corner-all' and @type='text']");

	public By iconClsoe = By.xpath("//i[@aria-label='close icon']");

	public By dropDownSelectExistingTemplate = By.xpath("//span[contains(text(),'Select Template')]");

	public By searchTemplate = By
			.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By titleComposeMail = By.xpath("//h4[contains(text(),'Compose Email')]");

	public By textComposeName = By.xpath("//input[@placeholder='Enter Name']");

	public By textSubject = By.xpath("//input[@placeholder='Enter Subject']");

	public By textAttachment = By.xpath("//span[contains(text(),' Attachments ')]");

	public By buttonSendText = By.xpath("//span[contains(text(),'Send Test')]");

	public By textFromAddressEmail = By.xpath("//input[@name='fromEmail']");
	public By textToAddressEmail = By.xpath("//input[@name='toEmail']");

	public By dragAndDrop = By.xpath("//h5[contains(text(),'Drag file here or click to upload')]");

	public By titleVoiceEmail = By.xpath("//h4[contains(text(),'Voice Mail')]");

	public By customTimeToggle = By.xpath("//span[@class='ui-inputswitch-slider']");

	public By iconDownloadEmail = By.xpath("//i[@class='icon icon-edo-download']");

	public By icondelete = By.xpath("//i[@class='icon icon-edo-trash close-audio-player']");

	public By titleCustomValues = By.xpath("//span[contains(text(),'Custom Values')]");

	public By titleSend = By.xpath("//label[contains(text(),'Send')]");

	public By editIcons = By.xpath("//i[@aria-label='edit icons']");
	public By deleteIcons = By.xpath("//i[@aria-label='delete icons']");

	public By dropdownAfter = By
			.xpath("//span[@class='ng-tns-c12-213 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']");

	public By dropdownMinutes = By
			.xpath("//span[@class='ng-tns-c12-214 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']");

	public By dropdownAfter_1 = By
			.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[4]");

	public By dropdownMinutes_1 = By
			.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[5]");

	public By textZero = By.xpath("//input[@id='Call 1_send']");
	public By dropdownDraft = By
			.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[3]");

	public By dropdownSelectFolder_1 = By
			.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[2]");

	public By errorNameDisplayed = By.xpath("//span[contains(text(),' Folder Name is required. ')]");

	public By textFolderName_1 = By.xpath("//input[@placeholder='Enter Folder Name']");

	public By errorSubjectRequired = By.xpath("//span[contains(text(),' Subject is required. ')]");
	public By errortoastmessage= By.xpath("//div[contains(@class,'ui-toast-detail')]");
	
	public By customtime_Toggle_Call=By.xpath("(//div[@class='ui-inputswitch ui-widget'])[2]");
	
	public By sendAfter_2=By.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[8]");
	public By deleteoption=By.xpath("(//span[@class='ui-button-icon-left ui-clickable icon icon-edo-trash'])[1]");
	
	//Wait
	
	public By textWait=By.xpath("//input[@placeholder='Enter Wait Time']");
	
	public By textPhoneNumber=By.xpath("//input[@id='phoneNum']");
	
	
	public By smstext=By.xpath("//input[@id='Sms 1_send']");
	public By smstext_1=By.xpath("//input[@id='Sms 2_send']");
	public By emailtext=By.xpath("//input[@id='Email 2_send']");
	public By emailtext_1=By.xpath("//input[@id='Email 1_send']");
	public By buttonCancel=By.xpath("//span[contains(text(),'Cancel')]");
	public By buttonCopy=By.xpath("//button[@aria-label='copy button']");
	public By dropdownDestinationAgencies=By.xpath("//p-multiselect[@id='destinationAgencies']");
	public By toolpick1=By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-run man-tooltip']");

	public By pauseaction=By.xpath("(//span[@aria-hidden='true' and @class='ui-button-icon-left ui-clickable icon icon-edo-close'])[1]");
	public By errorFromAddress=By.xpath("//span[contains(text(),' From Address is required. ')]");
	public By errorToAddress=By.xpath("//span[contains(text(),' To Address is required. ')]");
	public By erroremail=By.xpath("//span[contains(text(),' Enter valid email.')]");
	public By placeholderFromAddress=By.xpath("//input[@placeholder='Enter From Address']");
	
	public By placeholderToAddress=By.xpath("//input[@placeholder='Enter To Address']");
	
	public By tooltipInfoIcon=By.xpath("//i[@aria-label='info icon']");
	public By disableVoicemailDetect=By.xpath("(//span[@class='ui-inputswitch-slider'])[1] ");
	public By restartToggle=By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-layer']");
	
	public By donotcontactToggle=By.xpath("//span[@class='ui-inputswitch-slider']");
	
	public By cursortoDelete=By.xpath("//i[@class='icon icon-edo-close fs-8 m-l-5 cursor-pointer ng-star-inserted']");
	
	public void MoveToFrame(By element) {
		WebElement iframe = driver.findElement(element);
		driver.switchTo().frame(iframe);
	}

	public void defaultContent() {
		driver.switchTo().defaultContent();

	}

	public void verifyWordsAbove40Characters(int Count, WebElement element, String passValue, String failValue) {
		String fullText = element.getText();
		String[] words = fullText.split("\\s+");

		int longWordCount = 0;
		for (String word : words) {
			if (word.length() <= Count) {
				longWordCount++;
			}
		}

		if (longWordCount <= Count) {
			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}

	public void getText(String value, WebElement element, String passValue, String failValue) {

		elementhighlight(element);
		if (element.getAttribute("value").contains(value)) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}

	public void getvalue(String value, WebElement element, String passValue, String failValue) {

		if (element.getText().contains(value)) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}

	public void isEditable(WebElement element, String passValue, String failValue) {

		if (element.isEnabled()) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}

	public void isEditableWithLocator(String locator, String userType, String passValue, String failValue) {

		if (driver.findElement(By.xpath("//" + locator + "[contains(text(),'" + userType + "')]")).isEnabled()) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}
	public void isDisabledWithLocator(String locator, String userType, String passValue, String failValue) {

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//" + locator + "[contains(text(),'" + userType + "')]")));
System.out.println("check"+element!=null);		
		
		if (element != null) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}
	
	
	@Override
	public void isDisplay(String locator, String userType, String passValue, String failValue) {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[text()='" + userType + "']"));

		isdisplay1(driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']")), passValue, failValue);

	}

	public void elementClick(String locator, String userType, String passValue, String failValue) throws AWTException {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[text()='" + userType + "']"));
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_DOWN);
		actionclick(driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']")), passValue, failValue);

	}
	public void elementClickWithArrow(String locator, String userType, String passValue, String failValue) throws AWTException {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[text()='" + userType + "']"));
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_DOWN);
		actionclick(driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']")), passValue, failValue);

	}
	
	public void elementClick1(String locator, String userType, String passValue, String failValue) throws AWTException {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[text()='" + userType + "']"));

		actionclick(driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']")), passValue, failValue);

	}

	public void elementDisplay(String locator, String userType, String passValue, String failValue)
			throws AWTException {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[contains(text(),'" + userType + "')]"));

		isdisplay1(driver.findElement(By.xpath("//" + locator + "[contains(text(),'" + userType + "')]")), passValue,
				failValue);

	}
	
	
	public void elementTextClick(String locator, String userType, String passValue, String failValue)
			throws AWTException {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[contains(text(),'" + userType + "')]"));

		actionclick(driver.findElement(By.xpath("//" + locator + "[contains(text(),'" + userType + "')]")), passValue,
				failValue);

	}
	
	public void elementTextClickAnd(String locator, int userType, String passValue, String failValue)
			throws AWTException {
		WaitUtils.waitVisibilityByRef(By.xpath("//"+locator+"[normalize-space(text())='"+userType+"'" +"and contains(@class,'anchor')]"));

		actionclick(driver.findElement(By.xpath("//"+locator+"[normalize-space(text())='"+userType+"'"+"and contains(@class,'anchor')]")), passValue,
				failValue);

	}

	public void elementTextClickWithClass(String locator, String text, String classValue, String passValue, String failValue) throws AWTException {
	    // This matches the exact class as in your example
	    String xpath = "//" + locator + "[@class='" + classValue + "' and normalize-space(text())='" + text + "']";

	    WaitUtils.waitVisibilityByRef(By.xpath(xpath));
	    actionclick(driver.findElement(By.xpath(xpath)), passValue, failValue);
	}


	
	
	public void elementColor(String locator, String value2, String userType, String passValue, String failValue)
			throws AWTException {
		// WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[text()='" +
		// userType + "']"));

		System.out.println("color" + driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']"))
				.getCssValue("background-color"));
		if (driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']")).getCssValue("background-color")
				.contains(value2)) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}

	public void toastMessage(By element, String value1, String passValue, String failValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1));
		WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(element)); // Adjust if alert

		elementhighlight(alert);
		// appears elsewhere
		if (alert.getText().contains(value1)) {
			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}
	
	
	
	
	
	
	public void ListofElement(By value,String passValue)
	
	{
		List<WebElement >element=driver.findElements(value);
	
		for(int i=0;i<element.size();i++)
		{
	if(element.get(i).isEnabled())
	{
		element.get(i).click();
		

	}
		}
		test.log(LogStatus.PASS, passValue);
	}
}
