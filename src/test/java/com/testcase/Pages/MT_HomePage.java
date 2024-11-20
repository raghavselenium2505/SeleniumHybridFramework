package com.testcase.Pages;

import org.openqa.selenium.By;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface homePage {

	public void getTitle(String getValue, String passValue, String failValue);

}

public class MT_HomePage extends TestBase implements homePage {


	public By iconClose = By.xpath("//span[contains(@class,'close')]");


	public By buttonLogin = By.xpath("//li[@data-cy='account']");

	public By headerFlights = By.xpath("//li[contains(@class,'Flights')]");

	public By headerHotels = By.xpath("//li[contains(@class,'Hotels')]");

	public By headerHomestays = By.xpath("//li[contains(@class,'Homestays')]");

	public By headerHolidays = By.xpath("//li[contains(@class,'Holidays')]");
	public By headerTrain = By.xpath("//li[contains(@class,'Trains')]");
	public By headerBuses = By.xpath("//li[contains(@class,'Buses')]");
	public By headerCab = By.xpath("//li[contains(@class,'Cabs')]");
	public By headerForex = By.xpath("//li[contains(@class,'Forex')]");
	public By headerTravelInsurance = By.xpath("//li[contains(@class,'TravelInsurance')]");

	@Override
	public void getTitle(String getValue, String passValue, String failValue) {
		try {
			if (driver.getTitle().equals(getValue)) {
				// test.log(LogStatus.PASS, passValue);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, e);

		}

	}

}
