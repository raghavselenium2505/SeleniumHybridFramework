package com.gps.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;

interface MilkMan_MilkMan_PaymentInterface {

}

public class MilkMan_PaymentPage extends TestBase implements MilkMan_MilkMan_PaymentInterface {
	WebDriver driver;

	public By titlePayment = By.xpath("//h1[contains(text(),'Payment')]");
	public By textCardNumber = By.xpath("//input[@id='payment-numberInput']");
	public By textExpiryInput = By.xpath("//input[@id='payment-expiryInput']");
	public By textCVV = By.xpath("//input[@id='payment-cvcInput']");
	public By buttonPayment = By.xpath("//button[contains(normalize-space(text()),'Pay')]");
	public By buttonPayment_1 = By.xpath("//button[contains(normalize-space(text()),'Pay')]");
	public By textThankYou = By.xpath("//p[contains(text(),'Thank you!')]");
	public By spinerLoading = By.xpath("//h3[contains(text(),'Payment is Processing!')]");

	public By copyLink=By.xpath("//button[contains(text(),'Copy Link')]");
	public By frames=By.xpath("//iframe[@title='Secure payment input frame']");
	
	

	
	
	
	
	
	
	
	public void enterCardDetails() {

	    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

	    try {
	        logger.info("Switching to Stripe iframe");

	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
	                By.xpath("//iframe[@title='Secure payment input frame']")
	        ));

	        WebElement cardNumber = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.name("cardnumber"))
	        );
	        cardNumber.sendKeys("4242424242424242");

	        logger.info("Entered Card Number");

	        getDriver().switchTo().defaultContent();

	    } catch (Exception e) {
	        logger.error("Failed to enter card details: " + e.getMessage());
	        throw new RuntimeException(e);
	    }
	}
}
