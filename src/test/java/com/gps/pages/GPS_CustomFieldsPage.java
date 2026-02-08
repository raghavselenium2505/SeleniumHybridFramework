package com.gps.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface customFields {
	//public void chooseTextButtonAddCustomFieldPopup(By Element, String passValue, String failValue);
}

public class GPS_CustomFieldsPage extends TestBase implements customFields{
	
	public By inputSearch = By.xpath("//input[@aria-label='search']");
	public By buttonAddCustomFields = By.xpath("//span[text()='Add Custom Fields']");
	public By headerFieldName = By.xpath("//th[text()='Field Name']");
	public By headerFieldType = By.xpath("//th[text()='Field Type']");
	public By headerFieldKey = By.xpath("//th[text()='Field Key']");
	public By headerPlaceHolder = By.xpath("//th[text()='Placeholder']");
	public By iconEdit = By.xpath("//i[@aria-label='edit icon']");
	public By iconDelete = By.xpath("//i[@aria-label='delete icon']");
	public By iconDragDrop = By.xpath("//i[@aria-label='cursor-move icon']");
	public By textNoMatchFound = By.xpath("//h3[text()='No Match Found.']");
	
	
	//Add Custom Field popup
	
	public By textAddCustomField = By.xpath("//span[text()='Add Custom Field']");
	public By textTypeOfField = By.xpath("//p[text()='What type of field do you want to add ?']");
	public By buttonSingleLineText = By.xpath("//a[normalize-space()='Single Line Text']");
	public By buttonNumbers = By.xpath("//a[normalize-space()='Numbers']");
	public By buttonPhoneNumber = By.xpath("//a[normalize-space()='Phone Number']");
	public By buttonCheckboxes = By.xpath("//a[normalize-space()='Checkboxes']");
	public By buttonCurrency = By.xpath("//a[normalize-space()='Currency']");
	public By buttonDropdown = By.xpath("//a[normalize-space()='Dropdown']");
	public By buttonMultipleChoice = By.xpath("//a[normalize-space()='Multiple Choice']");
	public By buttonRadioButtons = By.xpath("//a[normalize-space()='Radio Buttons']");
	public By buttonDateTime = By.xpath("//a[normalize-space()='Date/Time']");
	public By buttonParagraphText  = By.xpath("//a[normalize-space()='Paragraph Text']");
	public By buttonListUserEntry = By.xpath("//a[normalize-space()='List User Entry']");
	
	
	//Add Custom Field popup2
	
	public By inputNameofTheField = By.id("nameOfTheFiled");
	public By inputPlaceholder = By.id("placeholder");
	public By dropdownType = By.xpath("//span[text()='contact']");
	public By buttonClose = By.xpath("//button[@aria-label='close button']");
	public By buttonSave = By.xpath("//button[@aria-label='save button']");
	public By buttonSingleLineTextInPopup = By.xpath("//div[contains(text(),' Single Line Text ')]");
	public By buttonNumbersInPopup = By.xpath("//div[contains(text(),'Numbers')]");
	public By buttonPhoneNumberInPopup = By.xpath("//div[contains(text(),' Phone Number')]");
	public By buttonCheckboxesInPopup = By.xpath("//div[contains(@class,'ui-widget-content')]//following::div[contains(text(),' Checkboxes ')]");
	public By buttonCurrencyInPopup = By.xpath("//div[contains(text(),' Currency ')]");
	public By buttonDropdownInPopup = By.xpath("//div[contains(text(),' Dropdown ')]");
	public By buttonMultipleChoiceInPopup = By.xpath("//div[contains(text(),' Multiple Choice ')]");
	public By buttonRadioButtonsInPopup = By.xpath("//div[contains(text(),' Radio Buttons ')]");
	public By buttonDateTimeInPopup = By.xpath("//div[contains(text(),' Date/Time ')]");
	public By buttonParagraphTextInPopup = By.xpath("//div[contains(text(),' Paragraph Text ')]");
	public By buttonListUserEntryInPopup = By.xpath("//div[contains(text(),' List User Entry ')]");
	public By validationMessage = By.xpath("//span[text()=' Name is required. ']");
	public By inputPossibleValues = By.xpath("//textarea[@placeholder='Enter Text']");
	public By validationPossibleValues = By.xpath("//span[text()=' Value is required. ']");
	public By iconDragdropinAddCustomField = By.id("HANDLEDRAG");
	public By inputLabel = By.xpath("//input[@formcontrolname='label']");
	public By inputValue = By.xpath("//input[@formcontrolname='value']");
	public By inputLabel_2 = By.xpath("(//input[@formcontrolname='label'])[2]");
	public By inputValue_2 = By.xpath("(//input[@formcontrolname='value'])[2]");
	public By linkAddOptions = By.xpath("//span[text()=' Add Options ']");
	public By checkboxforAllowCustomValues = By.xpath("//div[@role='checkbox']");
	public By validationLabel = By.xpath("//span[text()=' Label is required. ']");
	public By iconDeleteinAddCustomField = By.xpath("//div[contains(@class,'icon-edo-trash')]");
	public By inputPossibleValuesinRadioButtonCF_1 = By.id("possibleValues");
	public By inputPossibleValuesinRadioButtonCF_2 = By.xpath("(//input[@name='possibleValues'])[2]");
	public By iconDelete_2 = By.xpath("(//div[contains(@class,'icon-edo-trash')])[2]");
	
	
	
	
	
	//Edit Custom Field popup
	
	public By textEditCustomField = By.xpath("//span[text()='Edit Custom Field']");
	public By iconCross = By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	public By buttonUpdate = By.xpath("//span[text()='Update']");
	
	
	//Delete Custom Field popup
	
	public By textDeletepopup = By.xpath("(//h3[normalize-space()=\"Please enter word 'Delete' to continue\"])[1]");
	public By inputDelete = By.xpath("//input[@placeholder='Type Delete']");
	public By buttonSubmit = By.xpath("//span[text()='Submit']");
	public By iconClose = By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	public By validationMessage_Delete = By.xpath("//span[text()=' Confirmation text is required. ']");
	
	//@Override
	public void chooseTextButtonAddCustomFieldPopup(By Element, String passValue, String failValue) {
		
		actionclick(driver.findElement(Element), passValue, failValue);
		
	}
	
	public void displayValues(By Element, String passValue, String failValue) {
		try {
		isdisplay(Element, passValue, failValue );
		assertTrue(driver.findElement(Element).isDisplayed(), failValue);
		}
		
		catch(Exception e) {
			
			test.log(LogStatus.FAIL, failValue,
					"<html><body>" + "<p><b>Error occurred while executing the script.</b></p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			screenshotutil.captureScreenshot(value); // ✅ Capture a screenshot for debugging
			
		}
	}
	
	public void fields(By Element1, By Element2, By Element3, By Element4, By Element5, By Element6, String passValue, String failValue) {
		try {
			isdisplay(Element1, "User is able to see the input Name of the field", "User is unable to see the input Name of the field" );
			assertTrue(driver.findElement(Element1).isDisplayed(), failValue);
			
			isdisplay(Element2, "User is able to see the input Placeholder", "User is unable to see the input Placeholder" );
			assertTrue(driver.findElement(Element2).isDisplayed(), failValue);
			
			isdisplay(Element3, "User is able to see the type dropdown field", "User is unable to see the type dropdown field" );
			assertTrue(driver.findElement(Element3).isDisplayed(), failValue);
			
			isdisplay(Element4, "User is able to see the close button", "User is unable to see the close button");
			assertTrue(driver.findElement(Element4).isDisplayed(), failValue);
			
			isdisplay(Element5,"User is able to see the Save button", "User is unable to see the Save button");
			assertTrue(driver.findElement(Element5).isDisplayed(), failValue);
			
			waitforelement(shortwaitvalue);
			actionclick(driver.findElement(Element5), "User is able to click on Save button", "User is unable to click on Save button");
			waitforelement(shortwaitvalue);
			
			isdisplay(Element6, "User is able to see the validation message on click off Save button with empty data", "User is unable to see the validation message on click off Save button with empty data");
			assertTrue(driver.findElement(Element1).isDisplayed(), failValue);
			
			}
			
			catch(Exception e) {
				
				test.log(LogStatus.FAIL, failValue,
						"<html><body>" + "<p><b>Error occurred while executing the script.</b></p>"
								+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
								+ screenshotutil.captureScreenshot(value)
								+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

				screenshotutil.captureScreenshot(value); // ✅ Capture a screenshot for debugging
				
			}
	}
	
	public void length(By Element, String Value,String passValue, String failValue) {
		try {
			 WebElement inputField = driver.findElement(Element);
		        
		        // Enter value
		        sendkeys(Element, excelutil.getData("Settings_CustomFields", Value, xlsname), 
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
	
	public void checkboxesDropdownMultipleChoices(By Element1, By Element2, By Element3, By Element4, By Element5, By Element6, By Element7, By Element8, String passValue, String failValue) {
		try {
			isdisplay(Element1, "User is able to see the input Name of the field", "User is unable to see the input Name of the field" );
			assertTrue(driver.findElement(Element1).isDisplayed(), failValue);
			
			isdisplay(Element2, "User is able to see the input Placeholder", "User is unable to see the input Placeholder" );
			assertTrue(driver.findElement(Element2).isDisplayed(), failValue);
			
			isdisplay(Element3, "User is able to see the type dropdown field", "User is unable to see the type dropdown field" );
			assertTrue(driver.findElement(Element3).isDisplayed(), failValue);
			
			isdisplay(Element4, "User is able to see the input Possible values field", "User is unable to see the input possible value field" );
			assertTrue(driver.findElement(Element3).isDisplayed(), failValue);
			
			isdisplay(Element5, "User is able to see the close button", "User is unable to see the close button");
			assertTrue(driver.findElement(Element4).isDisplayed(), failValue);
			
			isdisplay(Element6,"User is able to see the Save button", "User is unable to see the Save button");
			assertTrue(driver.findElement(Element5).isDisplayed(), failValue);
			
			waitforelement(shortwaitvalue);
			actionclick(driver.findElement(Element6), "User is able to click on Save button", "User is unable to click on Save button");
			waitforelement(shortwaitvalue);
			
			isdisplay(Element7, "User is able to see the validation message on click off Save button with empty data for the Name field", "User is unable to see the validation message on click off Save button with empty data for the Name field");
			assertTrue(driver.findElement(Element1).isDisplayed(), failValue);
			
			isdisplay(Element8, "User is able to see the validation message on click off Save button with empty data for the possible values field", "User is unable to see the validation message on click off Save button with empty data for the possible values field");
			assertTrue(driver.findElement(Element1).isDisplayed(), failValue);
			
			}
			
			catch(Exception e) {
				
				test.log(LogStatus.FAIL, failValue,
						"<html><body>" + "<p><b>Error occurred while executing the script.</b></p>"
								+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
								+ screenshotutil.captureScreenshot(value)
								+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

				screenshotutil.captureScreenshot(value); // ✅ Capture a screenshot for debugging
				
			}
	}
	
	public void AddcheckboxesDropdownMultipleChoices(By Element1,String Value1, By Element2,String Value2, By Element3,String Value3, By Element4, String passValue, String failValue ) {
		
		sendkeys(Element1,Value1, "Able to enter the Name value in input field", "Unble to enter the Name value in input field");
		waitforelement(shortwaitvalue);
		
		sendkeys(Element2,Value2, "Able to enter the Placeholder value in input field", "Unble to enter the Placeholder value in input field");
		waitforelement(shortwaitvalue);
		
		sendkeys(Element3,Value3, "Able to enter the Possible values in the input field", "Unble to enter the Possible values in the input field");
		waitforelement(shortwaitvalue);
		
		test.log(LogStatus.INFO, "User should click on Save button");
		click(Element4, "User is able to click on Save button successfully", "User is unable to click on Save button");
		waitforelement(shortwaitvalue);
		
	}
	
	public void customField(String locator,String customField, String passValue, String failValue) {

		actionclick(driver.findElement(By.xpath("//"+locator+"[text()='"+customField+"']")), passValue, failValue);
		
		}

}
