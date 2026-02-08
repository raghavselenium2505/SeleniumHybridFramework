package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;
interface sys_homePage{
	
	public void chooseOrganzation(String organizationName,String passValue,String failValue);
	
	
}

public class GPS_SysHomePage extends TestBase implements sys_homePage{
	
	public By inputSearch = By.xpath("//input[@placeholder='Search']");
	
	@Override
	public void chooseOrganzation(String organizationName, String passValue, String failValue) {

	actionclick(driver.findElement(By.xpath("//h3[text()='"+organizationName+"']")), passValue, failValue);
	
	}

	
	

}
