package com.gps.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface tagAutomationInterface {
	public void Placeholder(String value, WebElement element, String passValue, String failValue) ;
	
}


public class GPS_TagAutomationPage extends TestBase implements tagAutomationInterface{

	
	public By titleTagAutomation = By.xpath("//h4[@aria-label='tag automations' ]");//input[@placeholder='Sequence Launcher Name']
	public By textSearch = By.xpath("//input[@id='search']");
	public By buttonAddSequence = By.xpath("//span[contains(text(),'Add Sequence Launcher')]");
	public By iconedit = By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-edit']");
	public By iconDelete = By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-trash']");
	public By titleDelete = By.xpath("//h3[contains(text(), \"Please enter word\") and contains(text(), \"Delete\")]");
	public By textDelete = By.xpath("//input[@placeholder='Type Delete']");
	public By buttonSubmit = By.xpath("//span[contains(text(),'Submit')]");
	public By buttonAddSequenceLauncher = By.xpath("//span[contains(text(),'Add Sequence Launcher')]");
	public By buttonCancel = By.xpath("//span[contains(text(),'Cancel')]");
	public By textSequenceLauncher = By.xpath("//input[@placeholder='Sequence Launcher Name']");
	public By iconPlayButton = By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-play-button']");
	public By buttonSave = By.xpath("//span[contains(text(),'Save')]");
	public By dropdownSequence = By.xpath("//span[contains(text(),'Select Sequence')]");
	public By textSearchsequence = By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");
	public By dropdownSelectaTag = By.xpath("//span[contains(text(),'Select a Tag')]");
	public By textSelectDateAndTime = By.xpath("//input[@placeholder='Select Date & Time']");
	public By iconcorrect = By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-check']");
	public By dropdownSelectAContactType = By.xpath("//span[contains(text(),'Select a Contact Type')]");
	public By buttonUpdate= By.xpath("//span[contains(text(),'Update')]");
	public By titleEditSequenceLauncher= By.xpath("//span[contains(text(),'Edit Sequence Launcher')]");
	public By errorNoMatchFound= By.xpath("//h3[contains(text(),'No Match Found.')]");
	public By textSequenceDate= By.xpath("//input[@id='scheduleDate']");
	public By buttonClear= By.xpath("//span[contains(text(),'Clear')]");
	public By iconArrowLeft= By.xpath("//span[@class='ui-paginator-icon pi pi-caret-left']");
	public By iconArrowRight= By.xpath("//span[@class='ui-paginator-icon pi pi-caret-right']");
	public By iconArrowForward= By.xpath("//span[@class='ui-paginator-icon pi pi-step-forward']");
	public By iconArrowBackward= By.xpath("//span[@class='ui-paginator-icon pi pi-step-backward']");
	public By iconPauseButton= By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-pause']");
	public By iconRestartButton= By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-layer']");
	
	
	
public void ListSizeOfElement(int count,By value,String passValue,String failValue)
	
	{
		List<WebElement >element=driver.findElements(value);
	
		
	if(element.size()>=count)
	{
		test.log(LogStatus.PASS, passValue);
		

	}
	else
	{
		test.log(LogStatus.FAIL, failValue);

		
	}
		}
	
	
	
	public void dateFormat(WebElement element,String dateformat,String getvalue,String passValue,String failValue)
	{
		//Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);

	        sdf.setLenient(false);  // Set lenient to false for strict validation
	        
	        try {
	            // Parse the date string with the specified format
	            sdf.parse(getvalue);
	    		test.log(LogStatus.PASS, passValue);

			}
	        catch (Exception e){
				test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
						+ ">click here for screenshot</p></body></html>");

				screenshotutil.captureScreenshot(value);
			}
		}

	
	public void Placeholder(String value, WebElement element, String passValue, String failValue) {

		if (element.getAttribute("placeholder").contains(value)) {

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}
	
	
}
