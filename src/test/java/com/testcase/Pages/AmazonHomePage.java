package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;

public class AmazonHomePage  extends TestBase{
	WebDriver driver;
	
	public By linkAccount = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
	
	
	
	

	
	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
	}
}