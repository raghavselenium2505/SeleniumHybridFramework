package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;
interface sys_UserManagement {
	

}

public class GPS_SysUserManagementPage extends TestBase implements sys_UserManagement {
	
	public By buttonAddUser = By.xpath("//span[text()='Add User']");
	
	public By inputSearch = By.xpath("//input[@aria-label='search button']");
	
	public By iconEdit = By.xpath("//i[@aria-label='edit icon']");
	
	public By iconDelete = By.xpath("//i[@aria-label='delete icon']");
	
	public By headerName = By.xpath("//th[contains(@class, 'user-name')]");
	
	public By headerEmail = By.xpath("//th[contains(@class, 'email-chng')]");
	
	public By headerPhone = By.xpath("//th[contains(@class, 'phone-chng')]");
	
	public By headerUserRole = By.xpath("//th[text()='User Role']");
	
	public By headerAgencies = By.xpath("//th[text()='Agencies']");
	
	public By textNoMatchFound = By.xpath("(//h3[normalize-space()='No Match Found.'])[1]");

}
