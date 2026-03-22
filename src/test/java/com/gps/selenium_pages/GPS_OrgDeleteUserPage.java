package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface deleteuser {
	
}

public class GPS_OrgDeleteUserPage extends TestBase implements deleteuser {
	
	public By textDeletepopup = By.xpath("(//h3[normalize-space()=\"Please enter word 'Delete' to continue\"])[1]");
				
	public By inputDelete = By.xpath("//input[@placeholder='Type Delete']");
	
	public By buttonSubmit = By.xpath("//span[text()='Submit']");
	
	public By iconClose = By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	
	public By validationMessage = By.xpath("//span[text()=' Confirmation text is required. ']");
	
}
