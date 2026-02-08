package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface GPS_SysEditUser {
	public void user(String locator,String userType, String passValue, String failValue) ;
}

public class GPS_SysEditUserPage extends TestBase implements GPS_SysEditUser {
	
	public By textEditUser = By.xpath("//h3[text()='Edit User']");
	
	public By buttonUpload = By.xpath("//label[text()='Upload ']");
	
	public By buttonRemove = By.xpath("//span[text()='Remove']");
	
	public By buttonChange = By.xpath("//label[text()='Change ']");
	
	public By inputFirstName = By.id("firstName");
	
	public By inputLastName = By.id("lastName");
	
	public By inputPassword = By.id("password1");
	
	public By inputEmailAddress = By.id("emailAddress");
	
	public By inputPhoneNumber = By.id("phoneNumber");
	
	public By inputExtension = By.id("extension");
			
	public By textUserType = By.xpath("//label[text()='User Type*']");
		
	public By textUserRole = By.xpath("//label[text()='User Role*']");
	
	public By dropdownIconUserType = By.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[1]");
	
	public By dropdownIconUserRole = By.xpath("(//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down'])[2]");
	
	public By dropdownValueAdmin = By.xpath("//span[text()='Admin']");
	
	public By dropdwonValueUser = By.xpath("//span[text()='User']");
	
	public By inputselectAgency = By.xpath("//input[@placeholder='Select Agency']");
	
	public By buttonCancel = By.xpath("//span[text()='Cancel']");
	
	public By buttonUpdate = By.xpath("//span[text()='Update']");
	
	public By iconClose = By.xpath("(//span[@class='pi pi-times'])[2]");
	
	@Override
	public void user(String locator,String userType, String passValue, String failValue) {

	actionclick(driver.findElement(By.xpath("//"+locator+"[text()='"+userType+"']")), passValue, failValue);
	
	}

}
