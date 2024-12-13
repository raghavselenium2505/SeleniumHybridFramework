package com.testcase.Pages;

import org.openqa.selenium.By;

import com.base.web.TestBase;

public class ETeki_JobsPage extends TestBase {

	public By headerOpenJobs = By.xpath(
			"//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[1]/a[contains(text(),'Open Jobs')]");

	public By headerInterviews = By.xpath(
			"//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[2]/a[contains(text(),'Interviews')]");

	public By headerUsers = By.xpath(
			"//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[3]/a[contains(text(),'Users')]");

	public By headerCandidates = By.xpath(
			"//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[4]/a[contains(text(),'Candidates')]");

	public By headerCompanies = By.xpath(
			"//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[5]/a[contains(text(),'Companies')]");

	public By headerNotifications = By.xpath(
			"//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[6]/a[contains(text(),'Notifications')]");
}
