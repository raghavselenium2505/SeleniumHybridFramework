package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface editcustomvalue {
	
}

public class GPS_CustomValue_EditCustomValuePage extends TestBase implements editcustomvalue {
	
	public By inputName = By.id("name");
	
	public By inputValue = By.id("value");
	
	public By buttonClose = By.xpath("//button[@aria-label='close button']");
	
	public By buttonUpdate = By.xpath("//button[@aria-label='update button']");
	
	public By validation_Name = By.xpath("//span[text()=' Name is required. ']");
	
	public By validation_Value = By.xpath("//span[text()=' Value is required. ']");
	
	public By textEditCustomValues = By.xpath("//span[text()='Edit Custom Values']");
	
	public By iconCross = By.xpath("//div[@class='ui-dialog-titlebar-icons']");

}
