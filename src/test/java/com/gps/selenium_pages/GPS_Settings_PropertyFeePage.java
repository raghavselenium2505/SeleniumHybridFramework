package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface propertyFee {
	public void verifyInput(Boolean getValue, String passValue, String failValue);

	public void verifyTitle(String verifyTitle, String passValue, String failVallue);
	
	public void login(String username, String password, String usernamePassValue, String usernameFailValue, String passwordPassValue, String passwordFailValue);
	
	public String futureDate(int futurevalue);
}


public class GPS_Settings_PropertyFeePage {
//org
	public By tabPropertyFee = By.xpath("//span[contains(text(),'Property Fee')]");
//dup
	public By headerPropertyFeeDetails = By.xpath("//h4[contains(text(),'Payment Fee Details')]");
	
	public By checkboxTax = By.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
	
	public By textReactive = By.xpath("//div[contains(text(),'Re-active')]");
	
	public By textHealthCare = By.xpath("//p[contains(text(),'Health Care')]");
	
	public By tabHospitality = By.xpath("//p[contains(text(),'Hospitality')]");

	
	//dup
	public By tabIndustrial = By.xpath("//p[contains(text(),'Industrial')]");
	public By tabLand = By.xpath("//p[contains(text(),'Land')]");
	public By tabMultiFamily = By.xpath("//p[contains(text(),'Multi-family')]");
	public By tabOffice = By.xpath("//p[contains(text(),'Office')]");
	public By tabResIncome = By.xpath("//p[contains(text(),'Residential Income')]");
	public By tabRestaurant = By.xpath("//p[contains(text(),'Restaurant')]");
	//dup
	public By tabRetail = By.xpath("//p[contains(text(),'Retail')]");
	public By tabShoppingCenter = By.xpath("//p[contains(text(),'Shopping Center')]");
	public By tabSpeciality = By.xpath("//p[contains(text(),'Specialty')]");
	public By tabSportEntertainment = By.xpath("//p[contains(text(),'Sports & Entertainment')]");
	public By buttonUpdate = By.xpath("//button[contains(text(),'Update')]");
	public By buttonCancel = By.xpath("//span[contains(text(),'Cancel')]");
	public By textTaxFild = By.xpath("//input[@id='taxFiled']");
	public By textpercentage = By.xpath("//div[contains(text(),'%')]");
	
	
}
