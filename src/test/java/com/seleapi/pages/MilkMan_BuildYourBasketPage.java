package com.seleapi.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.seleapi.base.TestBase;

interface MilkMan_BuildYourBasketPageInterface {
	
}

public class MilkMan_BuildYourBasketPage extends TestBase implements MilkMan_BuildYourBasketPageInterface {
	WebDriver driver;

   public By titleBuildYourBasket=By.xpath("//h1[contains(text(),'Build your')]");
   public By textStartWith=By.xpath("//input[@aria-label='Start with postcode or street name']");
	
	public By buttonConfirmAddress=By.xpath("//button[contains(text(),'Confirm Address')]");
	public By buttonConfirmPin=By.xpath("//button[contains(text(),'Confirm')]");
	
	
   
   
   public void selectAddressFromDropdown(String addressText) {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

	    try {
	        logger.info("Starting Address Selection from Dropdown");

	        // Dynamic XPath
	        String dynamicXpath = "//div[contains(text(),'" + addressText + "')]";

	        WebElement addressOption = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath))
	        );

	        logger.info("Address found: " + addressText);

	        wait.until(ExpectedConditions.elementToBeClickable(addressOption));

	        addressOption.click();

	        test.get().log(Status.PASS, "Selected address: " + addressText);

	    } catch (Exception e) {

	        logger.error("Failed to select address: " + addressText + " | Error: " + e.getMessage());

	        test.get().log(Status.FAIL, "Failed to select address: " + addressText);

	        throw new RuntimeException("Dropdown selection failed", e);
	    }
	}
	
}
