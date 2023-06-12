package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.web.TestBase;

interface LoginPage{
	public void click(By element);
	//public void signup(String firstName,String lastName,String EmailId,String Password,String ConfPassword);
	
}



public class ParabankHomePage  extends TestBase implements LoginPage{
	WebDriver driver;
	
	public By linkAdminPage = By.xpath("//a[contains(text(),'Admin Page')]");
	public By linkRegister = By.xpath("//a[contains(text(),'Register')]");

	
	public ParabankHomePage(WebDriver driver) {
		this.driver = driver;
	}
	@Override
	public void click(By element) {
	driver.findElement(element).click();
	}
	
	
	
	
	
}
