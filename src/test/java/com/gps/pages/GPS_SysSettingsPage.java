package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface settings {
	

}

public class GPS_SysSettingsPage extends TestBase implements settings {
	
	public By tabMySettings = By.xpath("//span[text()='My Settings']");
	
	public By tabUserManagement = By.xpath("//span[text()='User Management']");
	
	

	
}
