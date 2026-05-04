package com.seleapi.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.seleapi.base.TestBase;

interface MilkMan_DeliveryDetailInterface {
	
}

public class MilkMan_DeliveryDetailPage extends TestBase implements MilkMan_DeliveryDetailInterface {
	WebDriver driver;

   public By titleDeliveryDetail=By.xpath("//h1[contains(text(),'Delivery Details')]");
   public By textStartWith=By.xpath("//input[@aria-label='Start with postcode or street name']");
	
	public By buttonConfirmAddress=By.xpath("//button[contains(text(),'Confirm Address')]");
	public By buttonConfirmPin=By.xpath("//button[contains(text(),'Confirm')]");
	public By buttonContinue=By.xpath("//button[contains(text(),'Continue')]");
	
	
	public void selectFromDropdownValue(String tagName, String textValue) {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

	    String dynamicXpath = "//" + tagName + "[contains(normalize-space(.),'" + textValue + "')]";

	    try {
	        logger.info("Selecting value: " + textValue);
	        logger.info("XPath: " + dynamicXpath);

	        By locator = By.xpath(dynamicXpath);
		//	elementhighlight(getDriver().findElement(locator));

	        WebElement element = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(locator)
	        );

	        // 🔥 Wait until NOT obstructed
	        wait.until(ExpectedConditions.elementToBeClickable(locator));

	        // 🔥 Scroll properly
	        ((JavascriptExecutor) getDriver()).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", element
	        );

	        // 🔥 Small wait for UI animation
	        Thread.sleep(300);

	        // 🔥 Use JS click (bypass overlay issue)
	        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);

	        test.get().log(Status.PASS, "Selected value: " + textValue);

	    } catch (Exception e) {

	        logger.error("Failed to select value: " + textValue + " | Error: " + e.getMessage());

	    	logAIFailure(e, textValue);

	        throw new RuntimeException("Dropdown selection failed", e);
	    }
	}
	
	public void selectFromDropdown(String tagName, String textValue) {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

	    try {
	        logger.info("Starting Dropdown Selection");

	        // Dynamic XPath with tag + text
	        String dynamicXpath = "//" + tagName + "[contains(text(),'" + textValue + "')]";
waitforelement(2000);

	        logger.info("Constructed XPath: " + dynamicXpath);

	        WebElement option = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath))
	        );

	        logger.info("Element found with text: " + textValue);

	        wait.until(ExpectedConditions.elementToBeClickable(option));

	        option.click();

	        test.get().log(Status.PASS, "Selected value: " + textValue);

	    } catch (Exception e) {

	        logger.error("Failed to select value: " + textValue + " | Error: " + e.getMessage());

	    	logAIFailure(e, textValue);


	        throw new RuntimeException("Dropdown selection failed", e);
	    }
	}
	
}
