package com.testcase.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import com.base.web.TestBase;

interface handleAlert {
	public void alert();
}

public class ETeki_HomePage extends TestBase implements handleAlert {

	public By Dashboard = By.xpath("//a[@ng-click='selectParentTabs(0)']");
	
	public By Interviewers = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[1]");
	
	public By Analytics = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[2]");
	
	public By Admin = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[3]");
	
	public By Recruiter = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[4]");
	public By GlobalSearch = By.xpath("//div[@class='input-group global-search']");


	public By Notifications = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[5]");
	
	public By userProfile = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[6]");

	public By logoutButton = By.xpath(
			"//ul[@class='nav navbar-nav navbar-right']/li[6]/ul[@class='dropdown-menu header-dropdown-menu']/li[7]");

	@Override
	public void alert() {
		if (!driver.findElement(Dashboard).isDisplayed()) {
			Alert a = driver.switchTo().alert();
			a.dismiss();
		}
	}
}
