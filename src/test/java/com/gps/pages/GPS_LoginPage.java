package com.gps.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface LoginPage {

	public void login(String username, String password, String usernamePassValue, String usernameFailValue,
			String passwordPassValue, String passwordFailValue);

	public void getText(By element, String inputValue, String passvalue, String failValue);

	public void scrollUp(WebElement element, String passvalue, String failValue);

	public void Logout();

}

public class GPS_LoginPage extends TestBase implements LoginPage {

	public By logo = By.xpath("//img[contains(@alt,'EDO')]");

	public By emailText = By.xpath("//label[contains(text(),'Email')]");

	public By passwordText = By.xpath("//label[contains(text(),'Password')]");
	public By inputUsername = By.xpath("//input[@id='email']");

	public By inputPassword = By.xpath("//input[@id='password']");

	public By loginText = By.xpath("//h3[contains(text(),'Login')]");

	public By validationPassword = By.xpath("//span[@class='help-block'][contains(text(), 'Password')]");

	public By buttonLogin = By.xpath("//span[contains(text(),'Login')]");

	public By linkForgotPassword = By.xpath("//a[contains(text(),'Forgot Password?')]");

	// public By linkTermcondition = By.xpath("//p[contains(text(),'By signing in
	// you agree to our ')]");
	public By linkTermsConditions = By.xpath("//b[contains(text(),'Terms and Conditions')]");
	public By titleTermsConditions = By.xpath("//span[contains(text(),'Terms and Conditions')]");

	public By textPhase1 = By.xpath(
			"//p[contains(text(),' MAPSTechnologies.com is owned and operated by MAPS Technologies, LLC (“MAPS”). By using the website MAPTechnologies.com, (including but not limited to mobile, responsive web or otherwise), related data and/or related services (collectively the “Services”) you agree to be bound by the following terms of use, as updated from time to time (“Terms of Use”). ')]");
	public By titleContactMapTechnologies = By.xpath("//b[contains(text(),'Contacting MAPS Technologies')]");

	public By toastMsg = By.xpath("div[contains(@class,'toast-message-text')]//div");
	public By toastMsg1 = By
			.xpath("//p-toastitem[contains(@class,'toastAnimation')]//div[contains(@class,'toast-message-text')]/div");

	public By loginStatusToastIcon = By.xpath("//span[contains(@class,'toast-icon')]");

	public By loginStatusToast = By.xpath("//div[contains(text(),'Login Status')]");

	public By invalidtoastMessage = By.xpath("//div[contains(text(),'Invalid Login.')]");
	public By iconClose = By.xpath("//span[@class='pi pi-times']");
	public By textEmail = By.xpath("//input[@id='exampleInputEmail1']");

	public By organizationsText = By.xpath("/div/h4[contains(text(),'Organizations')]");

	public By profileImage = By.xpath("//p//span[contains(@class,'profile-img')]");

	public By logOut = By.xpath("//span[contains(text(),'Logout')]");

	public By emailRequireError = By.xpath("//span[contains(text(),'Email is required')]");

	public By passwordRequireError = By.xpath("//span[contains(text(),'Password is required')]");

	public By validEmailError = By.xpath("//span[contains(text(),'Enter valid Email')]");

	public By organizationLeftHandSide = By.xpath("//span[text()='Organizations']");

	@Override
	public void login(String username, String password, String usernamePassValue, String usernameFailValue,
			String passwordPassValue, String passwordFailValue) {
		/*
		 * 
		 * try { waitforelement(mediumwaitvalue);
		 * 
		 * Alert alert = driver.switchTo().alert(); // switch to alert
		 * logger.info("alert is present"); alert.accept(); // Accept the alert (you can
		 * also use alert.dismiss() if you want to dismiss it) } catch (Exception e) {
		 * logger.info("alert is not present"); // Handle the case where no alert
		 * appears }
		 */

		waitforelement(mediumwaitvalue);

		sendkeys(inputUsername, username, usernamePassValue, usernameFailValue);

		waitforelement(shortwaitvalue);

		sendkeys(inputPassword, password, passwordPassValue, passwordFailValue);

		waitforelement(shortwaitvalue);

		click(buttonLogin, "User able to click on Sign IN button.", "User unable to click on Sign IN button.");

	}

	@Override
	public void getText(By element, String inputValue, String passvalue, String failValue) {

		try {
			if (driver.findElement(element).getText().contains(inputValue)) {
				elementhighlight(driver.findElement(element));
				logger.info("passed click statement");
				test.log(LogStatus.PASS, passvalue);
			}
		} catch (Exception e) {
			logger.info("issue with some error");
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//		screenshotutil.captureScreenshot(value);
		}
	}

	

	@Override
	public void scrollUp(WebElement element, String passvalue, String failValue) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			waitforelement(shortwaitvalue);
			// Scrolling down the page till the element is found
			js.executeScript("arguments[0].scrollIntoView();", element);
			waitforelement(shortwaitvalue);

			elementhighlight(element);
			logger.info("passed click statement");
			test.log(LogStatus.PASS, passvalue);
		} catch (Exception e) {
			logger.info("issue with some error");
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

//screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void Logout() {

		actionclick(driver.findElement(profileImage), "User able to click on profile Image.",
				"User unable to click on profile Image.");

		// waitForWebElement(logOut, longwaitvalue);

		actionclick(driver.findElement(logOut), "User able to click on logout", "User unable to click on logout");

		waitforelement(mediumwaitvalue);
	}
	
	
	
	public void login(String username, String password) {

		// WaitUtils.waitClickByRef(buttonLogin);
			//WaitUtils.waitClickByRef(linkTermsConditions);

	WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
waitforelement(shortwaitvalue);
	WaitUtils.waitClickByRef(inputUsername).sendKeys(username);
		test.log(LogStatus.PASS, "Able to enter email");

		WaitUtils.waitClickByRef(inputPassword).sendKeys(password);
		test.log(LogStatus.PASS, "Able to enter password");

		WaitUtils.click(buttonLogin, organizationLeftHandSide);

		test.log(LogStatus.PASS, "Able to click on Login button");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

	public void logout() {

		WaitUtils.click(profileImage, logOut);
		test.log(LogStatus.PASS, "User able to click on profile Image");
		WaitUtils.click(logOut, inputUsername);
		test.log(LogStatus.PASS, "User able to click on logout");

	}

	public void logoutForFailure(String failValue) {

		WaitUtils.click(profileImage, logOut);
		test.log(LogStatus.FAIL, failValue);
		WaitUtils.click(logOut, inputUsername);
		test.log(LogStatus.FAIL,failValue);

	}
	
}
