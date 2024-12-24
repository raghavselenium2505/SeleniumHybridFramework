package com.testcase.Pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ETeki_OpenJobsPage extends TestBase{

	public By openJobsSelectStatus = By.xpath("//select[@ng-model=\"jobKey.status\"]");

	public By openJobsSearchInput = By.xpath("//input[@ng-model='jobKey.keyword']");

	public By openJobsSearchButton = By.xpath("//span[@class=\"input-group-addon btn\"][contains(text(),'Search')]");

	public By buttonActions = By.xpath("//button[@type='button']/following::button[6]");

	public By addCandidate = By
			.xpath("//div[@class='btn-group pull-right open']/ul/li[1]/a[@ng-click='addCandidate(job)']");

	public By fullJobDetails = By
			.xpath("//div[@class='btn-group pull-right open']/ul/li[1]/a[@ng-click='jobDetails(job)']");

	public By fullJobDetailsTitle = By.id("job-details");

	public By closeButton = By.xpath("//button[@ng-click='$close()'][contains(text(),'Close')]");
	public By linkEditJob = By.xpath("//*[@id='open_jobs']/div[2]/div[1]/div/div[1]/div[1]/div/div[3]/div/div/ul/li[3]/a");
	public By titleEditJob = By.xpath("//h4[@id='edit_job_dialog_title']");
	public By errorInvalidDate = By.xpath("//span[contains(text(),'Please enter valid date')]");
	public By jobClosingDate = By.xpath("//input[@placeholder='Job Closing Date']");

	public By buttonUpdateAndApprove=By.xpath("//button[contains(text(),'Update and Approve')]");

	public By errorCompanyname=By.xpath("/html/body/div[1]/div/div/div[2]/form/div[2]/div[1]/div/span");
	public By errorJobTitle=By.xpath("/html/body/div[1]/div/div/div[2]/form/div[3]/div[2]/div/span");
	public By errorJobDescription=By.xpath("//span[contains(text(),'Please upload file or write the description')]");
	public By errorExperience=By.xpath("/html/body/div[1]/div/div/div[2]/form/div[6]/div[1]/div/span");

	public By buttonClose=By.xpath("//button[contains(text(),'Cancel')]");
	
	public By errorSelectSkills=By.xpath("//span[contains(text(),'Required skills should not exceed more than 5')]");
	
	public By errorJobDate=By.xpath("//span[contains(text(),'Job due date expired')]");
	
	public void isdisplay(By element1,By element2,By element3,By element4,String passValue,String failureValue)
	{
		if(driver.findElement(element1).isDisplayed()&& driver.findElement(element2).isDisplayed()&& driver.findElement(element3).isDisplayed()&&driver.findElement(element4).isDisplayed())
		{
			elementhighlight(driver.findElement(element1));

			Assert.assertTrue(driver.findElement(element1).isDisplayed(),
					"Able to enter the login page");
			elementhighlight(driver.findElement(element2));
			Assert.assertTrue(driver.findElement(element2).isDisplayed(),
					"Able to enter the login page");
			
			elementhighlight(driver.findElement(element3));
			Assert.assertTrue(driver.findElement(element3).isDisplayed(),
					"Able to enter the login page");
			elementhighlight(driver.findElement(element4));
			Assert.assertTrue(driver.findElement(element4).isDisplayed(),
					"Able to enter the login page");

			test.log(LogStatus.PASS, passValue);

		}
		else
		{
			test.log(LogStatus.FAIL, failureValue);

		}
	}
}
