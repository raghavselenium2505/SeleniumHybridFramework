package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface orgEditUser {
	public void toggleSwitchOn(By toggleState, By element, String passValue, String failValue);

	public void toggleSwitchOff(By toggleState, By element, String passValue, String failValue);
}

public class GPS_OrgEditUserPage extends TestBase implements orgEditUser {

	public By textEditUser = By.xpath("//h3[text()='Edit User']");

	public By buttonUpload = By.xpath("//label[text()='Upload ']");

	public By buttonRemove = By.xpath("//span[text()='Remove']");

	public By buttonChange = By.xpath("//label[text()='Change ']");

	public By inputFirstName = By.id("firstName");

	public By inputLastName = By.id("lastName");

	public By inputPassword = By.id("password1");

	public By inputEmailAddress = By.id("emailAddress");

	public By inputPhoneNumber = By.id("phoneNumber");

	public By inputExtension = By.id("extension");

	public By dropdownCalendar = By.xpath("//span[text()='Select Calendar']");

	public By dropdwonTwilioNumber = By.xpath("//span[text()='Select Twilio Number']");

	public By textUserType = By.xpath("//label[text()='User Type*']");

	public By textUserRole = By.xpath("//label[text()='User Role*']");

	public By dropdownValueAdmin = By.xpath("//span[text()='Admin']");

	public By dropdwonValueUser = By.xpath("//span[text()='User']");

	public By buttonCancel = By.xpath("//span[text()='Cancel']");

	public By buttonUpdate = By.xpath("//span[text()='Update']");

	public By iconClose = By.xpath("(//span[@class='pi pi-times'])[2]");

	public By toastUpdateUser = By.xpath("//div[text()='User updated successfully.']");

	public By toggleContacts = By.xpath("//input[@id='Contacts_inputSwitch']");
	public By textSearch = By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By toggleSwitchContacts = By.xpath("(//div[@class='ui-inputswitch ui-widget ui-inputswitch-checked'])[10]");

	@Override
	public void toggleSwitchOn(By toggleState, By element, String passValue, String failValue) {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(toggleState));

		if (driver.findElement(toggleState).getAttribute("aria-checked").equals("false")) {
			click(element);
			test.log(LogStatus.PASS, passValue);
		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
		}
	}

	@Override
	public void toggleSwitchOff(By toggleState, By element, String passValue, String failValue) {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(toggleState));

		if (driver.findElement(toggleState).getAttribute("aria-checked").equals("true")) {
			click(element, passValue, failValue);
			waitforelement(shortwaitvalue);
		} else {
			test.log(LogStatus.PASS, "Contacts toggle was already off.");
		}
	}

}
