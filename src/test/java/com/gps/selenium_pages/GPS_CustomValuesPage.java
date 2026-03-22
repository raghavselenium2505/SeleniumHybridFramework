package com.gps.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;

interface customValues {
	
}
public class GPS_CustomValuesPage extends TestBase implements customValues {
	
	public By inputSearch = By.id("search");
	
	public By buttonAddCustomValue = By.xpath("//span[text()='Add Custom Value']");
	
	public By tableHeader = By.xpath("h4[aria-label='custome values']");
	
	public By headerCustomValueName = By.xpath("//th[text()='Custom Value Name']");
	
	public By headerFieldReferenceCode = By.xpath("//th[text()='Field Reference Code']");
	
	public By headerCustomValue = By.xpath("//th[text()='Custom Value']");
	
	public By textNoMatchFound = By.xpath("//h3[@aria-label='no match found']");
	
	public By iconEdit = By.xpath("//i[@aria-label='edit icon']");
	
	public By iconDelete = By.xpath("//i[@aria-label='delete icon']");
	
	
	}


