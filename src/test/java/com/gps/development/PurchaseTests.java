package com.gps.development;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.GPS_HomePage;
import com.gps.pages.GPS_LoginPage;
import com.gps.pages.GPS_OrgDashboardPage;
import com.gps.pages.GPS_SequencePage;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

public class PurchaseTests extends TestBase {

	@Test(groups = { "smoke", "purchase" })

	public void GPS_VerifyAutomationLHS() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_1", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				test.log(LogStatus.INFO, "Click on any Organization.");

				homepage.chooseOrganzation1(excelutil.getData("Rules", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				GPS_SequencePage sequencepage = new GPS_SequencePage();
				test.log(LogStatus.INFO, "3. Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "option2", xlsname),
						"Able to display the following fields.1." + excelutil.getData("Rules", "option2", xlsname),
						"Unable to display the following fields." + excelutil.getData("Rules", "option2", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "option3", xlsname),
						"Able to display the following fields" + excelutil.getData("Rules", "option3", xlsname),
						"Unable to display the following fields" + excelutil.getData("Rules", "option3", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "option4", xlsname),
						"Able to display the following fields" + excelutil.getData("Rules", "option4", xlsname),
						"Unable to display the following fields." + excelutil.getData("Rules", "option4", xlsname));
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

			} else {
				test.log(LogStatus.SKIP,
						"<span style='color:blue;'>Test case got skipped. Please check the Excel sheet for reference.</span>");
			}
		} catch (Exception e) {

			test.log(LogStatus.FAIL,
					"<html><body>"
							+ "<p>Error occurred while executing the script. Please check the exception below:</p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			e.printStackTrace();
			screenshotutil.captureScreenshot(value);
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test(groups = { "regression", "purchase" })
	public void testPurchaseWithoutStock() {
		System.out.println("Running testPurchaseWithoutStock");
		// your Selenium-WebDriver code here
		Assert.assertTrue(true);
	}
}
