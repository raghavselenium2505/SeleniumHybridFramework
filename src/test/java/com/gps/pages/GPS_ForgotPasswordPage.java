package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface gps_ForgotPassword{
	
}

public class GPS_ForgotPasswordPage  extends TestBase implements gps_ForgotPassword 

{
	public By logo = By.xpath("//img[contains(@alt,'EDO')]");
	public By forgotPasswordText = By.xpath("//h3[contains(text(),'Forgot Password')]");
	public By emailTextInForgotPassword = By.xpath("//label[contains(text(),'Email')]");
	public By emailInForgotPassword = By.name("email");

	public By ButtonResetPassword = By.xpath("//span[contains(text(),'Reset Password')]");
	public By linkBackToLogin = By.xpath("//a[contains(text(),'Back to login')]");

	public By emailRequireError = By.xpath("//span[contains(text(),'Email is required')]");

	public By validEmailError = By.xpath("//span[contains(text(),'Enter valid Email')]");

	public By forgotPasswordToastIcon = By.xpath("//span[contains(@class,'toast-icon')]");

	public By forgotPasswordToast = By.xpath("//div[contains(text(),'Forgot Password')]");

	public By userDoesNotExist = By.xpath("//div[contains(text(),'User does not exist')]");

	public By linkTermsConditions = By.xpath("//b[contains(text(),'Terms and Conditions')]");

	public By signingInText = By.xpath("//p[contains(text(),'By signing in you agree to our ')]");
	
	public By titleTermsConditions = By.xpath("//span[contains(text(),'Terms and Conditions')]");

	public By textPhase1 = By.xpath(
			"//p[contains(text(),' MAPSTechnologies.com is owned and operated by MAPS Technologies, LLC (“MAPS”). By using the website MAPTechnologies.com, (including but not limited to mobile, responsive web or otherwise), related data and/or related services (collectively the “Services”) you agree to be bound by the following terms of use, as updated from time to time (“Terms of Use”). ')]");
	public By titleContactMapTechnologies = By.xpath("//b[contains(text(),'Contacting MAPS Technologies')]");
	
	public By iconClose=By.xpath("//a[contains(@class,'close')]");

}
