package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface addcustomvalue {
	public void length(By Element, String Value, String passValue, String failValue); 
}

public class GPS_CustomValue_AddCustomValuePage extends TestBase implements addcustomvalue {
	
	public By inputName = By.id("name");
	
	public By inputValue = By.id("value");
	
	public By buttonClose = By.xpath("//button[@aria-label='close button']");
	
	public By buttonSave = By.xpath("//button[@aria-label='save button']");
	
	public By validation_Name = By.xpath("//span[text()=' Name is required. ']");
	
	public By validation_Value = By.xpath("//span[text()=' Value is required. ']");
	
	public By textAddCustomValues = By.xpath("//span[text()='Add Custom Values']");
	
	public By iconCross = By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	
	@Override
	public void length(By Element, String Value,String passValue, String failValue) {
		try {
			 WebElement inputField = driver.findElement(Element);
		        
		        // Enter value
		        sendkeys(Element, excelutil.getData("Settings_CustomValues", Value, xlsname), 
		                 passValue, failValue);

		        // Get the entered value
		        String enteredText = inputField.getAttribute("value");
		        int length = enteredText.length();

		        logger.info("Entered text: " + enteredText);
		        logger.info("The length is: " + length);
		
		}
		
		catch(Exception e) {
			
			test.log(LogStatus.FAIL,
					"<html><body>" + "<p><b>Error occurred while executing the script.</b></p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			screenshotutil.captureScreenshot(value); // ✅ Capture a screenshot for debugging
			
		}
	}

}
