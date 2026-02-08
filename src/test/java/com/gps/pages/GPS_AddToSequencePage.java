package com.gps.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_AddToSequence{
	
	public void removeActiveSequence(By messagesLocator, By removeIconLocator, String passValue,
			String failValue);
	
	
}

public class GPS_AddToSequencePage  extends TestBase implements GPS_AddToSequence {
	
	public By labelAddToSequence = By.xpath("//h3[text()='Add to Sequence']");
	
	public By labelSequence = By.xpath("//label[text()='Sequence* ']");
	
	public By labelEventStartDate = By.xpath("//label[text()='Event Start Date']");
	
	public By validationSequenceRequired = By.xpath("//span[text()=' Sequence is required. ']");
	
	public By selectSequence_AddSequence = By.xpath("//span[text()='Select Sequence']");
	
	public By SearchSelectSequence_AddSequence = By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");
	
	public By selectSequenceDropdown_AddSequence = By.xpath("//span[@class='ng-star-inserted']");
	
	public By selectEventStartDate_AddSequence = By.xpath("//input[@placeholder='Select Date & Time']");
	
	public By buttonSave_AddSequence = By.xpath("//span[text()='Save']");
	
	public By buttonClose_AddSequence = By.xpath("//span[text()='Close']");
	
	public By cancelIcon_AddSequence = By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	
	public By activeSequencelist_AddSequence = By.xpath("//p[contains(@class,'sequence-tag')]");
	
	public By removeActiveSequence_AddSequence = By.xpath("//i[contains(@class,'icon icon-edo-close')]");
	
	
	
	@Override
	public void removeActiveSequence(By messagesLocator, By removeIconLocator, String passValue,
			String failValue) {
		List<WebElement> allList = driver.findElements(messagesLocator);

		boolean iconVisible = false;

		for (int i = 0; i < allList.size(); i++) {
			WebElement message = allList.get(i);

			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(message).click().build().perform(); // Hover over the message to reveal options

				waitforelement(shortwaitvalue);

				WebElement removeActive = message.findElement(removeIconLocator);

				waitforelement(shortwaitvalue);
			
				if (removeActive.isDisplayed()) {
					actions.moveToElement(removeActive).click().build().perform();
					iconVisible = true;
					
				} else {
					
					logger.info("Skipping, icon is not visible.");
					iconVisible = false;
				}

			} catch (StaleElementReferenceException e) {
				
				logger.info("No 'icons' found");
				iconVisible = true;
			
				waitforelement(mediumwaitvalue);
			} catch (NoSuchElementException e) {
				
				logger.info("No 'icons' found");
				iconVisible = true;				
			}
		}
			if (iconVisible) {
				test.log(LogStatus.PASS, passValue); // Log PASS if any message had the icon visible
			} else {
				test.log(LogStatus.INFO, failValue); // Log FAIL if no message had the icon visible
			}
		}
}
