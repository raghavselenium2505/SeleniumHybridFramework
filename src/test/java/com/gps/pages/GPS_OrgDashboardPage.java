package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_OrgDashboard {

	public void chooseModuleName(String moduleName, String passValue, String failValue);

	public void moduleUnavailable(String moduleName, String passValue, String failValue);
}

public class GPS_OrgDashboardPage extends TestBase implements GPS_OrgDashboard {

	public By orgContacts = By.xpath("(//span[text()='Contacts'])[1]");

	public By orgCommunicationPanel = By.xpath("//span[text()='Communication Panel']");

	public By orgSettings = By.xpath("(//span[text()='Settings'])[1]");

	public By orgOrganisation = By
			.xpath("//span[@class='ui-menuitem-icon ng-tns-c15-96 icon icon-edo-home ng-star-inserted'][1]/../span[2]");

	public By orgDashboard = By.xpath(
			"//span[@class='ui-menuitem-icon ng-tns-c15-103 icon icon-edo-speedometer ng-star-inserted'][1]/../span[2]");

	 public By orgPipelines=By.xpath("//span[text()='Pipelines']");
	 
	 public By dashboardIconExpand=By.xpath("//a[contains(@href,'dashboard')]//span[contains(@class,'panelmenu-icon')]");
	 
	 public By orgPipelinesDashboard=By.xpath("//span[contains(text(),'Pipelines Dashboard')]");
	 
	 public By orgLeadQualification = By.xpath("(//span[text()='Lead Qualification'])[1]");
	
	
	
	
	@Override
	public void chooseModuleName(String moduleName, String passValue, String failValue) {


		if (driver.findElement(By.xpath("(//span[text()='" + moduleName + "'])[1]")).isDisplayed()) {

			click(By.xpath("(//span[text()='" + moduleName + "'])[1]"));

			test.log(LogStatus.PASS, passValue);

		} else {

			test.log(LogStatus.FAIL, failValue);
		}

	}

	@Override
	public void moduleUnavailable(String moduleName, String passValue, String failValue) {

		try {
			driver.findElement(By.xpath("(//span[text()='" + moduleName + "'])[1]")).isDisplayed();
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
			+ ">click here for screenshot</p></body></html>");
		} catch (NoSuchElementException e) {
			test.log(LogStatus.PASS, passValue);
		}
	}
}
