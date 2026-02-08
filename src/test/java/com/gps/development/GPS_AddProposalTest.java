package com.gps.development;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.GPS_AddProposalPage;
import com.gps.pages.GPS_ContactsPage;
import com.gps.pages.GPS_HomePage;
import com.gps.pages.GPS_LoginPage;
import com.gps.pages.GPS_OrgDashboardPage;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

public class GPS_AddProposalTest extends TestBase {
	@Test
	public void GPS_VerifyAddProposalOption() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Contacts", "HeaderName_AddProposal", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Verify Add Proposal", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));
    	    	
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();
				
				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Contacts", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				
				test.log(LogStatus.INFO, "User should be able to click on Contacts module Successfully.");
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				WaitUtils.waitClickByRef(orgdashboardpage.orgContacts);
				
				WaitUtils.waitClickByRef(orgdashboardpage.orgContacts).click();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				test.log(LogStatus.INFO, "User should be able to click on 3 dots of the Contact.");

				GPS_ContactsPage contactspage = new GPS_ContactsPage();
				
				WaitUtils.waitClickByRef(contactspage.contactListEllipsis);
				
				WaitUtils.waitClickByRef(contactspage.contactListEllipsis).click();

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				WaitUtils.waitClickByRef(contactspage.createProposal);
				
				WaitUtils.waitClickByRef(contactspage.createProposal).click();

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);				

				GPS_AddProposalPage addProposalpage = new GPS_AddProposalPage();

				test.log(LogStatus.INFO, "User should be able to Select Create Proposal.");

				WaitUtils.waitClickByRef(addProposalpage.labelAddProposal);
				
				isdisplay(addProposalpage.labelAddProposal, "Add Proposal Page is displayed",
						"Add Proposal Page is NOT displayed");

				assertTrue(driver.findElement(addProposalpage.labelAddProposal).isDisplayed());
				
				WaitUtils.waitClickByRef(addProposalpage.labelProposalName);

				isdisplay(addProposalpage.labelProposalName,
						"Proposal Name is displayed in Page", "Proposal Name is NOT displayed in Page");

				assertTrue(driver.findElement(addProposalpage.labelProposalName).isDisplayed());
				
				WaitUtils.waitClickByRef(addProposalpage.labelProposalTemplate);

				isdisplay(addProposalpage.labelProposalTemplate,
						"Proposal Template is displayed in Page", "Proposal Template is NOT displayed in Page");

				assertTrue(driver.findElement(addProposalpage.labelProposalTemplate).isDisplayed());
				
				WaitUtils.waitClickByRef(addProposalpage.buttonSave_AddProposal);

				isdisplay(addProposalpage.buttonSave_AddProposal,
						"User able to find Save Button", "User unable to find Save Button");

				assertTrue(driver.findElement(addProposalpage.buttonSave_AddProposal).isDisplayed());
				
				WaitUtils.waitClickByRef(addProposalpage.buttonClose_AddProposal);
				
				isdisplay(addProposalpage.buttonClose_AddProposal,
						"User able to find Close Button", "User unable to find Close Button");

				assertTrue(driver.findElement(addProposalpage.buttonClose_AddProposal).isDisplayed());
				
				WaitUtils.waitClickByRef(addProposalpage.buttonClose_AddProposal).click();

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);


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
		}
	}

	@Test
	public void GPS_VerifyAddProposalWithEmptyData() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Contacts", "HeaderName_AddProposalWithEmptyData", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Verify Add Proposal With Empty Data", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));
    	    	
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "User should be able to select the Organization Successfully.");


				GPS_HomePage homepage = new GPS_HomePage();
				homepage.chooseOrganzation1(excelutil.getData("Contacts", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "User should be able to click on Contacts module Successfully.");

				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				WaitUtils.waitClickByRef(orgdashboardpage.orgContacts);

				WaitUtils.waitClickByRef(orgdashboardpage.orgContacts).click();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
								

				test.log(LogStatus.INFO, "User should be able to click on 3 dots of the Contact.");


				GPS_ContactsPage contactspage = new GPS_ContactsPage();
				
				WaitUtils.waitClickByRef(contactspage.contactListEllipsis);

				WaitUtils.waitClickByRef(contactspage.contactListEllipsis).click();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				WaitUtils.waitClickByRef(contactspage.createProposal);

				WaitUtils.waitClickByRef(contactspage.createProposal).click();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_AddProposalPage addProposalpage = new GPS_AddProposalPage();

				test.log(LogStatus.INFO, "User should be able to Select Create Proposal.");
				
				WaitUtils.waitClickByRef(addProposalpage.buttonSave_AddProposal);

				WaitUtils.waitClickByRef(addProposalpage.buttonSave_AddProposal).click();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				WaitUtils.waitClickByRef(addProposalpage.buttonSave_AddProposal);
		
				isdisplay(addProposalpage.validationProposalNameRequired,
						"Validation Proposal Name is required displayed in Page", "Validation Proposal Name is required NOT displayed in Page");

				assertEquals(driver.findElement(addProposalpage.validationProposalNameRequired).getText(),
						excelutil.getData("Contacts", "Validation Proposal Name", xlsname));
				
				WaitUtils.waitClickByRef(addProposalpage.validationProposalTemplateRequired);
				
				isdisplay(addProposalpage.validationProposalTemplateRequired,
						"Validation Proposal Template is required displayed in Page", "Validation Proposal Template is required NOT displayed in Page");

				assertEquals(driver.findElement(addProposalpage.validationProposalTemplateRequired).getText(),
						excelutil.getData("Contacts", "Validation Proposal Template", xlsname));
				
				WaitUtils.waitClickByRef(addProposalpage.buttonClose_AddProposal);
				
				WaitUtils.waitClickByRef(addProposalpage.buttonClose_AddProposal).click();
				
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

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
		}
	}
}
