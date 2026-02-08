package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

public class ETeki_HomePage extends TestBase {

	public By Dashboard = By.xpath("//a[@ng-click='selectParentTabs(0)']");
	
	//public By GlobalSearch = By.xpath("//div[@class='input-group global-search']");

	public By Interviewers = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[1]");

	public By Analytics = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[2]");

	public By Admin = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[3]");

	public By Recruiter = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[4]");

	public By Notifications = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[5]");

	public By userProfile = By.xpath("//ul[@class='nav navbar-nav navbar-right']/li[6]");

	public By logoutButton = By.xpath(
			"//ui[@class='nav navbar-nav navbar-right']/li[6]/ul[@class='dropdown-menu header-dropdown-menu']/li[7]");
	
	
	
}
