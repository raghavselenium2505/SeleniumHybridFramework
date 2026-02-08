package com.gps.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface Gps_AllCountries
{	
	public  void validateSingleSelectDropdown(String countryToVerify,String passvalue,String failValue);
	public  void printAllCountryOptions(By element1,By element2,String passValue,String failValue);
	

	
}










public class GPS_AllCountriesPage extends TestBase implements Gps_AllCountries {
	
	
	public By dropdownToggle = By.xpath("//span[contains(text(),'Select a Country') and contains(@class,'ui-dropdown-label')]");
	public By dropdownOptions = By.xpath("//li[contains(@class, 'ui-dropdown-item')]");
	public By dropdownContactPlaceHolder=By.xpath("//span[contains(text(),'Select Country')]");
	public By dropdownContryToggle=By.xpath("//span[contains(@class,'ui-dropdown-label') and contains(text(),'Select a Country')]");
	public By dropdownOptioncontact=By.xpath("//li[contains(@class,'ui-dropdown-item') and @role='option']");
	@Override
	public  void validateSingleSelectDropdown(String countryToVerify,String passvalue,String failValue) {
	    try {

	     	        // Step 3: Locate selected item using the verification string
	        List<WebElement> selectedItems = driver.findElements(
	                By.xpath("//span[contains(text(),'" + countryToVerify + "')]"));

	        // Step 4: Validate selection
	        if (selectedItems.size() == 1) {
				test.log(LogStatus.PASS, passvalue);

	        
	        } else if (selectedItems.size() > 1) {
				test.log(LogStatus.FAIL, failValue);
	        } else {
				test.log(LogStatus.FAIL, failValue);
	        }

	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	}

	@Override
	public  void printAllCountryOptions(By element1,By element2,String passValue,String failValue) {
	    try {
	     //  By dropdownToggle = By.xpath("//span[contains(text(),'Select a Country') and contains(@class,'ui-dropdown-label')]");
	        WebElement dropdown = driver.findElement(element1);
	        dropdown.click();

	        // Step 2: Get all options once dropdown is expanded
	     //   By optionsXPath = By.xpath("//li[contains(@class, 'ui-dropdown-item')]");
	        List<WebElement> allOptions = driver.findElements(element2);
	        waitforelement(longwaitvalue);
	        // Step 3: Print each country name
			test.log(LogStatus.PASS, passValue);

	        for (WebElement option : allOptions) {
	            System.out.println("- " + option.getText());
	        }
	    } catch (Exception e) {

	    }
	}

	public  void printAllCountryOptions() {
	    try {
	        // Click the dropdown toggle span
	        By dropdownToggle = By.xpath("//span[contains(@class, 'ui-dropdown-label') and contains(text(), 'Select Country')]");
	        WebElement dropdown = driver.findElement(dropdownToggle);
	        dropdown.click();

	        // Wait briefly to allow options to render (use explicit wait in real test)
	        Thread.sleep(500);

	        // Get all option elements in the dropdown list
	        By optionsXPath = By.xpath("//li[@class='ui-dropdown-item ui-corner-all' and @role='option']");
	        List<WebElement> allOptions = driver.findElements(optionsXPath);

	        // Print all country names
	        System.out.println("✅ Country options:");
	        for (WebElement option : allOptions) {
	            System.out.println("- " + option.getText());
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Error while printing country options: " + e.getMessage());
	    }
	}
	
	
	
	
	
	
	
}
