package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface GPS_SysMySettings {
	
}

public class GPS_SysMySettingsPage extends TestBase implements GPS_SysMySettings {
	
	public By buttonUpload = By.xpath("//label[text()='Upload ']");
	
	public By buttonRemove = By.xpath("//span[text()='Remove']");
	
	public By buttonChange = By.xpath("//label[text()='Change ']");
	
	public By textLogoContent = By.xpath("//p[text()='The proposed size is 512*512px ']");
	
	public By textMyInformation = By.xpath("//h4[text()='My Information']");
	
	public By inputFirstName = By.id("firstName");
	
	public By inputLastName = By.id("lastName");
	
	public By inputEmail = By.id("emailAddress");
	
	public By inputPhone = By.id("phone");
	
	public By inputExtension = By.id("extension");
	
	public By buttonUpdateProfile = By.xpath("//span[text()='Update Profile']");
	
	public By textChangePassword = By.xpath("//h4[text()='Change Password']");
	
	public By buttonUpdatePassword = By.xpath("//span[text()='Update Password']");
	
	public By inputNewPassword = By.id("newPassword");
	
	public By inputConfirmPassword = By.id("confirmPassword");
	
	public By ValidationMessage_FirstName = By.xpath("//span[text()=' First Name is required. ']");
	
	public By ValidationMessage_LastName = By.xpath("//span[text()=' Last Name is required. ']");
	
	public By ValidationMessage_Email = By.xpath("//span[text()=' Email is required. ']");
	
	public By ValidationMessage_Phone = By.xpath("//span[text()=' Phone number is required. ']");
	
	public By ValidationMessage_NewPassword = By.xpath("//span[text()=' New Password is required. ']");
	
	public By ValidationMessage_ConfirmPassword = By.xpath("//span[text()=' Confirm Password is required. ']");
	
	public By Invalid_validationFirstName = By.xpath("//span[text()=\" Enter Alpha numeric with only _,'. and - \"]");
	
	public By Invalid_validationLastName = By.xpath("(//span[text()=\" Enter Alpha numeric with only _,'. and - \"])[2]");
	
	public By Invalid_validationEmail = By.xpath("//span[text()=' Enter valid email. ']");
	
	public By Invalid_validationPhone = By.xpath("//span[text()=' Enter valid phone number. ']");
	
	public By Existing_ValidationEmail = By.xpath("//span[text()=' This email address is already in use. ']");
	
	public By Validation_PasswordnotMatch = By.xpath("//span[text()=\" Password didn't match. \"]");
	
	public By ValidationMessageforNewPassword_Characters = By.xpath("(//p[text()='Use atleast 8 characters and no more than 30 characters '])[1]");
	
	public By ValidationMessageforNewPassword_UpperCase = By.xpath("(//p[text()='Use atleast 1 upper case letter '])[1]");
	
	public By ValidationMessageforNewPassword_LowerCase = By.xpath("(//p[text()='Use atleast 1 lower case letter '])[1]");
	
	public By ValidationMessageforNewPassword_Numeric = By.xpath("(//p[text()='Use atleast 1 numeric letter '])[1]");
	
	public By ValidationMessageforNewPassword_SpecialCharacters = By.xpath("(//p[text()='Use atleast 1 special character '])[1]");
	
	public By ValidationMessageforConfirmPassword_Characters = By.xpath("(//p[text()='Use atleast 8 characters and no more than 30 characters '])[2]");
	
	public By ValidationMessageforConfirmPassword_UpperCase = By.xpath("(//p[text()='Use atleast 1 upper case letter '])[2]");
	
	public By ValidationMessageforConfirmPassword_LowerCase = By.xpath("(//p[text()='Use atleast 1 lower case letter '])[2]");
	
	public By ValidationMessageforConfirmPassword_Numeric = By.xpath("(//p[text()='Use atleast 1 numeric letter '])[2]");
	
	public By ValidationMessageforConfirmPassword_SpecialCharacters = By.xpath("(//p[text()='Use atleast 1 special character '])[2]");
	
	

}
