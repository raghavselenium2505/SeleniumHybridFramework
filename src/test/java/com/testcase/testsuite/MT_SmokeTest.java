package com.testcase.testsuite;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.MT_HomePage;

public class MT_SmokeTest extends TestBase {
	@Test
	public void MT_Smoke() throws Exception {
		test = report.startTest(getData("SmokeTest", "Jira_Story_Name", xlsname));

		if (getData("DashBoard", "smokeTest", xlsname).equalsIgnoreCase("N")) {

			test.log(LogStatus.INFO, "Click on icon close");

			MT_HomePage home = new MT_HomePage();

			home.click(home.iconClose, "Able to click on close icon", "Unable to close icon");

			test.log(LogStatus.INFO, "Click on Button login and close icon ");

			waitforelement(1000);
			logger.info("waiting for the login button");
			home.click(home.buttonLogin, "Click on Button login", "Unable to click on Button Login");
			logger.info("clicking on login button");
			waitforelement(1000);

			home.click(home.iconClose, "Click on close icon", "Unable to close icon");

			// test.log(LogStatus.INFO, "Click on cabs tab");

			// Flights

			test.log(LogStatus.INFO, "Click on Flights Header");

			home.click(home.headerFlights, "User able to click on Flights", "User unable to click on Flights");

			waitforelement(1000);

			String flightsTitle = driver.getTitle();

			home.getTitle(flightsTitle, "Webpage Title contain Flights", "Webpage Title doesn't contain Flights");

			// Hotels

			test.log(LogStatus.INFO, "Click on Hotels Header");

			home.click(home.headerHotels, "User able to click on Hotels", "User unable to click on Hotels");

			waitforelement(1000);

			String hotelsTitle = driver.getTitle();

			home.getTitle(hotelsTitle, "Webpage Title contain Hotels", "Webpage Title doesn't contain Hotels");

			// Homestays & Villas

			test.log(LogStatus.INFO, "Click on Homestays & Villas Header");

			home.click(home.headerHomestays, "User able to click on Homestays & Villas",
					"User unable to click on Homestays & Villas");

			waitforelement(1000);

			String homestaysTitle = driver.getTitle();

			home.getTitle(homestaysTitle, "Webpage Title contain Homestays & Villas",
					"Webpage Title doesn't contain Homestays & Villas");

			// Holiday Packages

			test.log(LogStatus.INFO, "Click on Holiday Packages Header");

			home.click(home.headerHolidays, "User able to click on Holiday Packages",
					"User unable to click on Holiday Packages");

			waitforelement(1000);

			String holidaysTitle = driver.getTitle();

			home.getTitle(holidaysTitle, "Webpage Title contain Holiday Packages",
					"Webpage Title doesn't contain Holiday Packages");

			// Trains

			test.log(LogStatus.INFO, "Click on Trains Header");

			home.click(home.headerTrain, "User able to click on Trains", "User unable to click on Trains");

			waitforelement(1000);

			String trainsTitle = driver.getTitle();

			home.getTitle(trainsTitle, "Webpage Title contain Trains", "Webpage Title doesn't contain Trains");

			// Buses

			test.log(LogStatus.INFO, "Click on Buses Header");

			home.click(home.headerBuses, "User able to click on Buses", "User unable to click on Buses");

			waitforelement(1000);

			String busesTitle = driver.getTitle();

			home.getTitle(busesTitle, "Webpage Title contain Buses", "Webpage Title doesn't contain Buses");

			// Cabs

			test.log(LogStatus.INFO, "Click on Cabs Header");

			home.click(home.headerCab, "User able to click on Cabs", "User unable to click on Cabs");

			waitforelement(1000);

			String cabsTitle = driver.getTitle();

			home.getTitle(cabsTitle, "Webpage Title contain Cabs", "Webpage Title doesn't contain Cabs");

			// Forex Card & Currency

			test.log(LogStatus.INFO, "Click on Forex Card & Currency Header");

			home.click(home.headerForex, "User able to click on Forex Card & Currency",
					"User unable to click on Forex Card & Currency");

			waitforelement(1000);

			String forexTitle = driver.getTitle();

			home.getTitle(forexTitle, "Webpage Title contain Forex Card & Currency",
					"Webpage Title doesn't contain Forex Card & Currency");

			// Travel Insurance

			test.log(LogStatus.INFO, "Click on Travel Insurance Header");

			home.click(home.headerTravelInsurance, "User able to click on Travel Insurance",
					"User unable to click on Travel Insurance");

			waitforelement(1000);

			String travelinsuranceTitle = driver.getTitle();

			home.getTitle(travelinsuranceTitle, "Webpage Title contain Travel Insurance",
					"Webpage Title doesn't contain Travel Insurance");

		}

		else {
			test.log(LogStatus.INFO, "Please check the testcase  Skip condition");
			test.log(LogStatus.SKIP, "Testcase got skipped");

		}
	}

}
