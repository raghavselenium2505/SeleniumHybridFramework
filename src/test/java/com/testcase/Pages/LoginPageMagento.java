package com.testcase.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;

interface LoginPage{
	public void login();
	public void signup(String firstName,String lastName,String EmailId,String Password,String ConfPassword);
	
}

public class LoginPageMagento extends TestBase implements LoginPage {

	WebDriver driver;

	public By buttonCreateAnAccount = By.xpath("//span[text()='Create an Account']");

	public By textFirstName = By.xpath("//input[@id='firstname']");

	public By textLastName = By.xpath("//input[@id='lastname']");
	
	public By textEmailId=By.xpath("//input[@id='email_address']");

	public By textPassword=By.xpath("//input[@id='password']");
	
	public By textConfPassword=By.xpath("//input[@id='confirmation']");
	
	public By buttonRegister=By.xpath("//span[text()='Register']");
	
	public By textUserName=By.xpath("//input[@name='login[username]']");
	public By textPasword=By.xpath("//input[@name='login[password]']");
	public By textSubmit=By.xpath("//button[@name='send']");
	
	public LoginPageMagento(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String title) {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		
			}

	@Override
	public void login() {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		elementhighlight(driver.findElement(textUserName));
		sendkeys(textUserName, config.getProperty("userName"), "able to enter username in email",
				"unable to enter username in email");

		elementhighlight(driver.findElement(textPasword));
		sendkeys(textPasword, config.getProperty("passWord"), "Able to enter password",
				"unable to enter password");
		
		//elementHighlight(driver.findElement(login.agreecheckbox));
		click(textSubmit, "Able to click on Checkbox", "Unable to click on Checkbox");
		elementhighlight(driver.findElement(textSubmit));		
	}

	

	@Override
	public void signup(String firstName, String lastName, String EmailId, String Password, String ConfPassword) {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		elementhighlight(driver.findElement(textFirstName));
		sendkeys(textFirstName, firstName, "Entered firname", "Unable to enter firstName");
		elementhighlight(driver.findElement(textLastName));
		sendkeys(textLastName, lastName, "Entered lastName", "Unable to enter lastName");
		elementhighlight(driver.findElement(textEmailId));

		sendkeys(textEmailId, EmailId, "Entered emailid", "Unable to enter emailid");
		elementhighlight(driver.findElement(textPassword));

		sendkeys(textPassword, Password, "Entered password", "Unable to enter password");
		elementhighlight(driver.findElement(textConfPassword));

		sendkeys(textConfPassword, ConfPassword, "Entered confirmpassword", "Unable to enter cofirmpassword");
	}

	
	
}
