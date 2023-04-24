package com.testcase.Pages;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends TestBase {

	WebDriver driver;

	public By emailText = By.xpath("//input[@name='email']");

	public By passwordText = By.xpath("//input[@name='password']");

	public By submitButton = By.xpath("//button[contains(text(),'Sign in')]");
	
	public By agreecheckbox=By.xpath("/html/body/div/div[2]/div/form/div[3]/label");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String title) {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		elementHighlight(driver.findElement(login.emailText));
		SendKeys(login.emailText, config.getProperty("userName"), "able to enter username in email",
				"unable to enter username in email");

		elementHighlight(driver.findElement(login.passwordText));
		SendKeys(login.passwordText, config.getProperty("passWord"), "Able to enter password",
				"unable to enter password");
		
		//elementHighlight(driver.findElement(login.agreecheckbox));
		Click(login.agreecheckbox, "Able to click on Checkbox", "Unable to click on Checkbox");
		sleepMethod(2000);
		elementHighlight(driver.findElement(login.submitButton));
		
		
		
		Click(login.submitButton, "unable to login");
	//		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		//	JOptionPane.showMessageDialog(null, "value"+driver.getTitle());
sleepMethod(10000);
			VerifyTitle(title);
			}

	
}
