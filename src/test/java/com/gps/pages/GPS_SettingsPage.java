package com.gps.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface gps_settings {
	public int CountWebElement(By element1, String passValue, String failValue);

	public String removeAlphabets(String str);
	public void CompareStrings(int value_String1,int value_string2,String passValue,String failValue);

}

public class GPS_SettingsPage extends TestBase implements gps_settings {

	public By tabOrganization = By.xpath("//span[text()='Organization']");

	public By tabUserManagement = By.xpath("//span[text()='User Management']");

	public By tabTags = By.xpath("//span[text()='Tags']");

	public By tabPipelines = By.xpath("//span[text()='Pipelines']");

	public By tabPhoneNumbers = By.xpath("//span[text()='Phone Numbers']");

	public By tabCustomValues = By.xpath("//span[text()='Custom Values']");

	public By tabPropertyFee = By.xpath("//span[text()='Property Fee']");

	public By tabPayments = By.xpath("//span[text()='Payments']");

	public By tabCalendars = By.xpath("//span[text()='Calendars']");

	public By tabMembershipSettings = By.xpath("//span[text()='Membership Settings']");

	public By tabCustomFields = By.xpath("//span[text()='Custom Fields']");

	public By tabIntegrations = By.xpath("//span[text()='Integrations']");

	public By tabFacebookLeadForms = By.xpath("//span[text()='Facebook Lead Forms']");

	public By tabSmtpServices = By.xpath("//span[text()='SMTP Services']");

	public By tabAppointmentWidget = By.xpath("//span[text()='Appointment Widget']");

	public By tabPortalWidgets = By.xpath("//span[text()='Portal & Widgets']");

	public By tabSecurity = By.xpath("//span[text()=' Security']");

	public By tabAccountBilling = By.xpath("//span[text()='Account Billing']");

	public By tabContactDuplicate = By.xpath("//span[text()='Contact Duplicate']");

	public By tableName = By.xpath("//th[contains(text(),'Name')]");

	public By tableEmail = By.xpath("//th[contains(text(),'Email')]");

	public By tablePhone = By.xpath("//th[contains(text(),'Phone')]");

	public By tableUserRole = By.xpath("//th[contains(text(),'User Role')]");

	public By textSearch = By.xpath("//input[@aria-label='search button']");

	public By buttonAddUser = By.xpath("//span[contains(text(),'Add User')]");

	public By headerUsers = By.xpath("//h4[contains(text(),'Users ')]");

	public By iconEdit = By.xpath("//i[@class='icon icon-edo-edit ng-star-inserted']");
	public By  textCount=By.xpath("//div[@class='accounts-header d-flex align-items-center justify-content-between p-b-15']");
	
	public By iconPageIcon=By.xpath("//a[@class='ui-paginator-page ui-paginator-element ui-state-default ui-corner-all ng-star-inserted']");
	
	public By iconValue=By.xpath("//h4[@class='fs-18 fw-400 m-all-0']");
	
	@Override
	public int CountWebElement(By element1, String passValue, String failValue) {

		List<WebElement> element = driver.findElements(element1);
		int value_sub = element.size();
		return value_sub;

	}

	@Override
	public String removeAlphabets(String str) {
		String numberOnly = str.replaceAll("[^0-9]", "");
		return numberOnly;

	}
	
	@Override
	public void CompareStrings(int value_String1,int value_string2,String passValue,String failValue)
	{try {
		if(value_String1==value_string2)
		{
			waitforelement(shortwaitvalue);
			test.log(LogStatus.PASS, passValue);

		}
	}catch (Exception e) {
			test.log(LogStatus.FAIL, failValue+"<html><body><p><a href="+screenshotutil.captureScreenshot(value)+">click here for screenshot</p></body></html>");
			e.printStackTrace();
			//screenshotutil.captureScreenshot(value);
		}
	
	}
}
