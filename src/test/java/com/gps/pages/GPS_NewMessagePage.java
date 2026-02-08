package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_NewMessage{
	
	public void isdisplayed(By element, String passValue, String failValue);
}

public class GPS_NewMessagePage extends TestBase implements GPS_NewMessage {
	
	public By labelNewMessage = By.xpath("//h3[text()='New Message']");

	public By labelName_NewMessage = By.xpath("//label[text()='Name*']");
	
	public By labelPhoneNumber_NewMessage = By.xpath("//label[text()='Phone Number*']");
	
	public By labelSelectTemplates_NewMessage = By.xpath("//label[text()='Select Templates']");
	
	public By labelEnterMessage_NewMessage = By.xpath("//label[text()='Enter Message*']");
	
	public By buttonSend_NewMessage = By.xpath("//span[text()='Send']");
	
	public By buttonClose_NewMessage = By.xpath("//span[text()='Close']");
	
	public By cancelIcon_NewMessage = By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	
	public By textName_NewMessage = By.id("name");
	
	public By textPhoneNumber_NewMessage = By.id("phoneNumber");
	
	public By textEnterMessage_NewMessage = By.id("enterMessage");
		
	public By selectDropdown_NewMessage = By.xpath("//li[@role='option']");
	
	public By selectTemplate_NewMessage = By.xpath("//span[text()='Select Template']");
	
	public By search_SelectTemplate = By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");
	
	public By selectTemplateDropdown_NewMessage = By.xpath("//span[@class='ng-star-inserted']");
	
	public By cancelTemplateDropdown_NewMessage = By.xpath("//i[contains(@class,'ui-dropdown-clear-icon pi pi-times ng-tns')]");
	
	public By validationName_NewMessage= By.xpath("//span[text()=' Name is required. ']");
	
	public By validationPhoneNumber_NewMessage = By.xpath("//span[text()=' Phone number is required. ']");
	
	public By validationEnterMessage_NewMessage = By.xpath("//span[text()=' Message is required. ']");
	
	public By validationInvalidName_NewMessage= By.xpath("//span[contains(text(),' Name is required. ')]");
	
	public By validationInvalidPhoneNumber_NewMessage= By.xpath("//span[text()=' Enter valid phone number. ']");
	
	
	
	
	
	@Override
	public void isdisplayed(By element, String passValue, String failValue) {
		
		 try {
		if (driver.findElement(element).isDisplayed()) {
			elementhighlight(driver.findElement(element));
			test.log(LogStatus.PASS, passValue);

		} else {
		 test.log(LogStatus.FAIL, failValue);
			
		}
		 } catch (NoSuchElementException e) {
		        // Log failure if the element is not found
		        logger.error("Element not found: " + element.toString());
		        test.log(LogStatus.INFO, failValue);
	}
	}
}
