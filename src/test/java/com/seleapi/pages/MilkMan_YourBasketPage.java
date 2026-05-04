package com.seleapi.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.seleapi.base.TestBase;

interface MilkMan_YourBasketPageInterface {
	
}

public class MilkMan_YourBasketPage extends TestBase implements MilkMan_YourBasketPageInterface {
	WebDriver driver;
	public By buttonContinueToCheckOut=By.xpath("//button[contains(text(),'Continue to checkout')]");

  
	
	public void selectDaysForProducts(String product, String day) {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

	    try {
	        logger.info("Starting selection for product: " + product + " and day: " + day);
waitforelement(1000);
	        String xpath = "//h5[contains(text(),'" + product + "')]"
	                     + "/ancestor::div[contains(@class,'rounded')]"
	                     + "//*[normalize-space()='" + day + "']"
	                     + "/following::*[name()='svg'][1]";

	        logger.info("Using XPath: " + xpath);

	        By locator = By.xpath(xpath);

	        // Step 1: Wait for element presence
	        WebElement element = wait.until(
	                ExpectedConditions.presenceOfElementLocated(locator)
	        );

	        // Step 2: Scroll to element (center)
	        ((JavascriptExecutor) getDriver()).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", element
	        );

	        // Step 3: Small wait for UI stabilization
	        Thread.sleep(300);

	        // Step 4: Wait until clickable
	        wait.until(ExpectedConditions.elementToBeClickable(locator));

	        // Step 5: JS Click (fix click interception issue)
	        ((JavascriptExecutor) getDriver()).executeScript(
	                "arguments[0].click();", element
	        );

	        logger.info("Successfully clicked day: " + day + " for product: " + product);

	        test.get().log(Status.PASS, "Clicked " + day + " for " + product);

	    } catch (Exception e) {

	        logger.error("Failed selecting day: " + day + " for product: " + product + " | Error: " + e.getMessage());

	        test.get().log(Status.FAIL, "Failed selecting " + day + " for " + product);

	        throw new RuntimeException("Dropdown selection failed", e);
	    }
	}

	
	public void openDayDropdown(String product, String day) {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

	    try {
	        logger.info("Opening dropdown for " + day + " - " + product);

	        String dropdownXpath = "//h5[contains(text(),'" + product + "')]"
	                + "/ancestor::div[contains(@class,'rounded')]"
	                + "//*[normalize-space()='" + day + "']"
	                + "/following::*[name()='svg'][1]"
	                + "/ancestor::*[self::button or self::div][1]";

	        By locator = By.xpath(dropdownXpath);

	        WebElement dropdown = wait.until(
	                ExpectedConditions.elementToBeClickable(locator)
	        );

	        ((JavascriptExecutor) getDriver()).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", dropdown
	        );

	        dropdown.click();

	        logger.info("Dropdown opened successfully");

	        test.get().log(Status.PASS, "Dropdown opened for " + day + " - " + product);

	    } catch (Exception e) {

	        logger.error("Failed to open dropdown: " + e.getMessage());
	    	logAIFailure(e, "Failed");

	        throw new RuntimeException("Dropdown open failed", e);
	    }
	}
	
	public void selectDropdownValue(String value) {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

	    try {
	        logger.info("Selecting value: " + value);

	        // ✅ Better XPath (handles spaces correctly)
	        String valueXpath = "//div[text()='" + value + "']";

	        logger.info("Value XPath: " + valueXpath);

	        By locator = By.xpath(valueXpath);

	        // ✅ Wait until visible
	        WebElement option = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(locator)
	        );
	        Thread.sleep(6000);

	        // ✅ Scroll to element (important for overlay dropdown)
	        ((JavascriptExecutor) getDriver()).executeScript(
	                "arguments[0].scrollIntoView({block: 'center'});", option
	        );

	        // ✅ Wait until clickable
	        wait.until(ExpectedConditions.elementToBeClickable(locator));

	        // ✅ JS Click (avoids interception issues)
	        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);

	        logger.info("Value selected: " + value);
	        test.get().log(Status.PASS, "Selected value: " + value);

	    } catch (Exception e) {

	        logger.error("Failed selecting value: " + e.getMessage());
	    	logAIFailure(e, "Failed");


	        throw new RuntimeException("Value selection failed", e);
	    }
	}
	
	public void scrollUpAndClick(WebElement element, String elementName) {

	    try {
	        logger.info("Scrolling up and clicking on: " + elementName);

	        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

	        // 🔥 Wait for element to be visible
	        wait.until(ExpectedConditions.visibilityOf(element));

	        JavascriptExecutor js = (JavascriptExecutor) getDriver();

	        // 🔥 Scroll to element (top)
	        js.executeScript("arguments[0].scrollIntoView(true);", element);

	        // Small wait for UI stabilization
	        Thread.sleep(300);

	        // 🔥 JS Click
	        js.executeScript("arguments[0].click();", element);

	        logger.info("Clicked successfully on: " + elementName);
	        test.get().log(Status.PASS, "Clicked on: " + elementName);

	    } catch (Exception e) {

	        logger.error("Failed to click on: " + elementName + " | Error: " + e.getMessage());
	        test.get().log(Status.FAIL, "Failed to click on: " + elementName);

	        throw new RuntimeException("Scroll and click failed", e);
	    }
	}
}
