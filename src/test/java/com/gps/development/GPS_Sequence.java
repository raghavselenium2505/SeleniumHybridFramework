package com.gps.development;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.UUID;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.GPS_ContactsPage;
import com.gps.pages.GPS_HomePage;
import com.gps.pages.GPS_LoginPage;
import com.gps.pages.GPS_OrgDashboardPage;
import com.gps.pages.GPS_SequencePage;
import com.gps.pages.GPS_SysMySettingsPage;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

public class GPS_Sequence extends TestBase {
	@Test(priority = 0)
	public void GPS_VerifyTheAutomationMenuAtLHS() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_01", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO,
						"'Automation' menu should be displayed at LHS with expand option Expanding this option should display with below 3 options 1. Sequences 2. Tag automations 3. Rules");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink2", xlsname),
						"Able to display sequences module", "Unable to display sequence module");

				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink3", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				orgdashboardpage.chooseModuleName("Rules", "Able to display Rules Module",
						"Unable to display Rules Module");

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

	@Test(priority = 1)
	public void GPS_VerifyTheSequencessubmenu() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_1", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_02", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu and verify the page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				isdisplay(sequencepage.buttonAddSequence, "Able to display the AddSequence",
						"Unable to display the Add sequence Button");

				isdisplay(sequencepage.textSearch, "Able to display the textSearch",
						"Unable to display the Add sequence Button");

				isdisplay(sequencepage.headerSequenceName, "Able to display the header SequenceName",
						"Unable to display headerSequenceName");
				isdisplay(sequencepage.headerTotal, "Able to display the header Total",
						"Unable to display the header total");
				isdisplay(sequencepage.headerActive, "Able to display the Active Header",
						"Unable to display the Active header");
				isdisplay(sequencepage.headerCompleted, "Able to display the Completed Header",
						"Unable to display Completed Header");
				isdisplay(sequencepage.headerReplied, "Able to display the Replied Header",
						"Unable to display Replied Header");
				isdisplay(sequencepage.headerReply, "Able to display Reply header", "Unable to display Reply header");
				isdisplay(sequencepage.headerStatus, "Able to display Status Header",
						"Unable to display Status Header");
				isdisplay(sequencepage.headerSequenceId, "Able to display SequenceId", "Unable to display SequenceID");
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

	@Test(priority = 3)
	public void GPS_VerifyTheValidSearchFieldOnThePage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_2", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_03", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu and verify the page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Verify the Search field on the page by entering keyword in it");

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				isdisplay(sequencepage.textSearch, "Able to display the textSearch",
						"Unable to display the Add sequence Button");

				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "validText", xlsname),
						"Able to enter the text", "Unable to enter the text");

				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "validText", xlsname),
						"Able to display the data which is entered", "Unable to display the data which is entered");

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyTheInvalidSearchFieldOnThePage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_3", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_04", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu and verify the page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Verify the Search field on the page by entering invalid keyword in it");

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				isdisplay(sequencepage.textSearch, "Able to display the textSearch",
						"Unable to display the Add sequence Button");

				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "invalidText", xlsname),
						"Able to enter the text", "Unable to enter the text");
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.textNoSearchResults,
						"Able to display the no search results for the invalid text",
						"Unable to display the no search results for the invalid text");

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyTheListingTable() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_4", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_05", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu and verify the page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				isdisplay(sequencepage.buttonAddSequence, "Able to display the AddSequence",
						"Unable to display the Add sequence Button");

				isdisplay(sequencepage.textSearch, "Able to display the textSearch",
						"Unable to display the Add sequence Button");

				isdisplay(sequencepage.headerSequenceName, "Able to display the header SequenceName",
						"Unable to display headerSequenceName");
				isdisplay(sequencepage.headerTotal, "Able to display the header Total",
						"Unable to display the header total");
				isdisplay(sequencepage.headerActive, "Able to display the Active Header",
						"Unable to display the Active header");
				isdisplay(sequencepage.headerCompleted, "Able to display the Completed Header",
						"Unable to display Completed Header");
				isdisplay(sequencepage.headerReplied, "Able to display the Replied Header",
						"Unable to display Replied Header");
				isdisplay(sequencepage.headerReply, "Able to display Reply header", "Unable to display Reply header");
				isdisplay(sequencepage.headerStatus, "Able to display Status Header",
						"Unable to display Status Header");
				isdisplay(sequencepage.headerSequenceId, "Able to display SequenceId", "Unable to display SequenceID");

				isdisplay(sequencepage.iconEdit, "Able to display the iconEdit", "Unable to display iconedit");
				isdisplay(sequencepage.iconDelete, "Able to display the iconDelete", "Unable to display iconDelete");

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
	public void GPS_VerifyTheListingTableForNoSequence() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_5", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_06", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name_1", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO,
						"Click on Sequences submenu and  Verify the fields displayed in Listing table when there are no sequences created or when all the sequences are deleted.");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				waitforelement(extraverylongwaitvalue);
				isdisplay(sequencepage.textThereNoItemsDisplay, "Getting the display as no items displayed",
						"Unable to display as no items displayed");
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
	public void GPS_VerifyTheAddSequence() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_6", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_07", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textSequenceName, "Able to display the text SequenceName",
						"Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.dropDownSelectFolder, "Able to display dropdown SelectFolder",
						"Unable to display dropdown Select Folder");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonClose, "Able to display the close button",
						"Unable to display the close button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSave, "Able to display the save button",
						"Unable to display the save button");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
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
	public void GPS_VerifyTheAddSequenceWithEmptyData() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_7", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_08", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textSequenceName, "Able to display the text SequenceName",
						"Unable to display the text Sequence Name");

				test.log(LogStatus.INFO,
						"Click on Sequence name field and clickon anywhere on the application or click on save");

				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.errorSequenceName, "Able to display the error as expected",
						"Unable to display the error");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
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
	public void GPS_VerifyTheAddSequenceWithValidData() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_8", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_09", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.linkback, "Able to display the Back link as expected",
						"Unable to display back link");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonClose, "Able to display the left link with Close button",
						"Unable to display left link with Close button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSave, "Able to display the left link with buttonSave",
						"Unable to display left link with buttonSave");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.rightPanel, "Able to display right panel ", "Unable to display right panel");

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
	public void GPS_VerifyTheBackLink() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_9", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_10", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Click on 'Back' link on 'Sequence Configuration' page");

				isdisplay(sequencepage.linkback, "Able to display the Back link as expected",
						"Unable to display back link");

				waitforelement(shortwaitvalue);

				click(sequencepage.linkback, "Able to click on linkback", "Unable to click on linkback");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				click(sequencepage.buttonYes, "Able to click on Yes Button", "Unable to click on Yes Button");
				isdisplay(sequencepage.buttonAddSequence, "Able to navigate to listing page",
						"Unable to navigate to listing page");
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

	//
	@Test
	public void GPS_VerifyTheSequenceConfigurationPage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_10", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_11", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Verify the left box on the 'Sequence Configuration' page");

				isdisplay(sequencepage.textSequencename, "Able to display SequenceName",
						"Unable to display Sequence Name");

				isdisplay(sequencepage.toggleCustomTime, "Able to display CustomTime", "Unable to display CustomTime");

				isdisplay(sequencepage.dropDownSelectUser, "Able to display dropdownSelectUser",
						"Unable to display dropdownSelectUser");

				isdisplay(sequencepage.dropDownSelectSequence, "Able to display dropDownSelectSequence",
						"Unable to display dropDownSelectSequence");

				isdisplay(sequencepage.textTags, "Able to display textTags", "Unable to display textTags");

				isdisplay(sequencepage.textEventDate, "Able to display textEventDate",
						"Unable to display textEventDate");
				isdisplay(sequencepage.toggleAllowDate, "Able to display toggleAllowDate",
						"Unable to display toggleAllowDate");
				isdisplay(sequencepage.toggleStopOnResponse, "Able to display toggleStopOnResponse",
						"Unable to display toggleStopOnResponse");

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
	public void GPS_VerifyTheSequenceNameText() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_11", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_12", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Verify the left box on the 'Sequence Configuration' page");

				isdisplay(sequencepage.textSequencename, "Able to display SequenceName",
						"Unable to display Sequence Name");

				sequencepage.getText(excelutil.getData("Sequences", "validSequence", xlsname),
						driver.findElement(sequencepage.textSequencename), "text is similar as entered text",
						"Unable to get the same text entered");

				sequencepage.isEditable(driver.findElement(sequencepage.textSequencename), "Able to edit the text",
						"Unable to edit the textSequenceName");

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
	public void GPS_VerifyTheRightPaneModule() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_12", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_13", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Add Sequence and verify the Right pane");

				isdisplay(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

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
	public void GPS_VerifyTheAddEventDropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_13", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_14", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Add Event dropdown");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownSms, "Able to display dropdownSms", "Unable to display dropdownSms");
				isdisplay(sequencepage.dropdownMessenger, "Able to display dropdownMessanger",
						"Unable to display dropdownMessanger");
				isdisplay(sequencepage.dropdownEmail, "Able to display dropdownEmail",
						"Unable to display dropdownEmail");
				isdisplay(sequencepage.dropdownVoiceemail, "Able to display dropdownvoiceemail",
						"Unable to display dropdownvoiceemail");
				isdisplay(sequencepage.dropdownCall, "Able to display dropdownCall", "Unable to display dropdownCall");
				isdisplay(sequencepage.dropdownWait, "Able to display dropdownwait", "Unable to display dropdownwait");
				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");

				isdisplay(sequencepage.dropdownWebhook, "Able to display dropdownWebhook",
						"Unable to display dropdownWebhook");

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
	public void GPS_VerifyTheNameFieldInSms() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_14", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_15", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Name and verify the results");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownSms, "Able to display dropdownSms", "Unable to display dropdownSms");
				click(sequencepage.dropdownSms, "Able to display dropdownSms", "Unable to display dropdownSms");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				sequencepage.getText(excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						driver.findElement(sequencepage.textSMS), "Able to display the default text as expected",
						"Unable to display the text as expected");

				clear(sequencepage.textSMS, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(sequencepage.textSMS, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				clear(sequencepage.textSMS, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(sequencepage.textSMS, excelutil.getData("Sequences", "characters", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textSMS),
						"Maximum character can be entered is 40 characters only", "Maximum characters exceeded");
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
	public void GPS_VerifyTheCalWhisper() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_15", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_16", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "1. Click on Call module.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownCall, "Able to display dropdownCall", "Unable to display dropdownCall");
				click(sequencepage.dropdownCall, "Able to display dropdownCall", "Unable to display dropdownCall");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(extraverylongwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Call Whisper and verify the results");
				sequencepage.MoveToFrame(sequencepage.frameElement);

				clear(sequencepage.textWhisker, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(sequencepage.textWhisker, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				clear(sequencepage.textWhisker, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(sequencepage.textWhisker, excelutil.getData("Sequences", "characters", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textWhisker),
						"Maximum character can be entered is 40 characters only", "Maximum characters exceeded");
				sequencepage.defaultContent();

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
	public void GPS_VerifyTheEventNameField() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_16", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_17", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "1. Click on Call module.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownCall, "Able to display dropdownCall", "Unable to display dropdownCall");
				click(sequencepage.dropdownCall, "Able to display dropdownCall", "Unable to display dropdownCall");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Event name and verify the results");

				sequencepage.getText("Call 1", driver.findElement(sequencepage.textEvent),
						"Able to display the default text as expected", "Unable to display the text as expected");

				clear(sequencepage.textEvent, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(sequencepage.textEvent, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				clear(sequencepage.textEvent, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(sequencepage.textEvent, excelutil.getData("Sequences", "characters", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

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
	public void GPS_VerifyAddtask() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_17", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_18", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.headerAddTask, "Able to display the header As AddTask",
						"Unable to display the header as AddTask");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.labelAddTask, "Able to see the label for Add Task",
						"Unable to see the label for Add Task");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.placeHolderAddTask, "Able to see the placeholder for Add Task Field",
						"Unable to see the placeholder for Add Task Field");
				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.labelTaskDesc, "Able to see the task Description",
						"Unable to see the task Description");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.placeHolderTaskDesc, "Able to see the placeHolderTaskDesc",
						"Unable to see the placeHolderTaskDesc");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.labelDueIn, "Able to see the DueIn Label DropDown",
						"Unable to see the DueIn DropDown Label");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.placeholderDropDownDueIn, "Able to see the DueIn DropDown",
						"Unable to see the DueIn DropDown Label");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSave, "Able to see the button Save", "Unable to see the button Save");

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
	public void GPS_VerifyTextAddtask() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_18", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_19", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Title task field");

				clear(sequencepage.textTask, "Able to edit the task sucesfully", "Unable to edit the task sucesfully");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textTask, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to add new Data", "Unable to add new Data");
				waitforelement(shortwaitvalue);

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textTask),
						"Maximum character can be entered is 40 characters only", "Maximum characters exceeded");

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
	public void GPS_VerifyTextAddtaskDescription() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_19", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_20", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on task description");

				// clear(sequencepage.textTask, "Able to edit the task sucesfully", "Unable to
				// edit the task sucesfully");
				// waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to add new Data", "Unable to add new Data");
				waitforelement(shortwaitvalue);

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.placeHolderTaskDesc),
						"Maximum character can be entered is 400 characters only", "Maximum characters exceeded");

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
	public void GPS_VerifyDueFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_20", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_21", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Due in");

				click(sequencepage.placeholderDropDownDueIn);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textDate1, "Able to display 1 day in the dropdown",
						"Unable to display 1 day in the dropdown");
				isdisplay(sequencepage.textDate2, "Able to display 2 days in the dropdown",
						"Unable to display 2 days in the dropdown");
				isdisplay(sequencepage.textDate3, "Able to display 3 days in the dropdown",
						"Unable to display 3 days in the dropdown");
				isdisplay(sequencepage.textDate4, "Able to display 4 days in the dropdown",
						"Unable to display 4 days in the dropdown");

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");

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
	public void GPS_VerifySaveButton() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_21", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_22", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Select required fields and click on Save");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(longwaitvalue);

				sequencepage.isDisplay("h3", "Task", "Able to get the value saved in the Event Right Side",
						"Unable to get the value saved in the Event Right side");

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
	public void GPS_VerifyBreadCrumbFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_22", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_23", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(longwaitvalue);

				sequencepage.isDisplay("h3", "Task", "Able to get the value saved in the Event Right Side",
						"Unable to get the value saved in the Event Right side");

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				String task = driver.findElement(sequencepage.textTask).getAttribute("value");
				sequencepage.getText(task, driver.findElement(sequencepage.textTask), "Able to clear the event",
						"Unable to clear the Event");

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
	public void GPS_VerifyDeleteFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_23", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_24", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				// waitforelement(shortwaitvalue);

				/*
				 * sequencepage.isDisplay("h3", "Task",
				 * "Able to get the value saved in the Event Right Side",
				 * "Unable to get the value saved in the Event Right side");
				 * 
				 * WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				 */
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Enter word delete and click on submit");
				click(sequencepage.buttonSave);
				waitforelement(extraverylongwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				actionclick(driver.findElement(sequencepage.iconDelete_1), "Able to click on iconDelete",
						"Unable to click on iconDleete");

				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textDelete, "Delete", "Able to enter the delete text",
						"Unable to enter the text Delete");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textStartByEvent, "Able to delete the event/Sequence successfully",
						"Unable to delete the sequence/Event");
				waitforelement(mediumwaitvalue);
				// click(sequencepage.buttonUpdate);
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
	public void GPS_VerifyInvalidDeleteFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_24", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_25", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Enter word delete and click on submit");
				click(sequencepage.buttonSave);
				waitforelement(extraverylongwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				actionclick(driver.findElement(sequencepage.iconDelete_1), "Able to click on iconDelete",
						"Unable to click on iconDleete");

				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textDelete, "Delete1", "Able to enter the delete text",
						"Unable to enter the text Delete");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSubmit, "Unable to delete the event/Sequence due to invalid name",
						"Able to delete the sequence/Event");
				waitforelement(shortwaitvalue);
				click(sequencepage.iconCross);
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
	public void GPS_VerifySequenceConfigurationPage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_25", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_26", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Navigate to RHS");
				click(sequencepage.buttonSave);

				waitforelement(extraverylongwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				isdisplay(sequencepage.dropDownDraft, "Able to display the dropDown Draft sucesfully",
						"Unable to display the dropDown Draft Sucesfully");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonCopySequence, "Able to display copysequence succesfully ",
						"Unable to display the copysequence");

				/*
				 * waitforelement(extraverylongwaitvalue);
				 * WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				 * 
				 * actionclick(driver.findElement(sequencepage.iconDelete_1),
				 * "Able to click on iconDelete", "Unable to click on iconDleete");
				 * 
				 * waitforelement(shortwaitvalue); sendkeys(sequencepage.textDelete, "Delete1",
				 * "Able to enter the delete text", "Unable to enter the text Delete");
				 * waitforelement(shortwaitvalue); click(sequencepage.buttonSubmit);
				 * waitforelement(shortwaitvalue); isdisplay(sequencepage.buttonSubmit,
				 * "Unable to delete the event/Sequence due to invalid name",
				 * "Able to delete the sequence/Event"); waitforelement(shortwaitvalue);
				 * click(sequencepage.iconCross);
				 */

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
	public void GPS_VerifyCloseFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_26", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_27", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name ");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "2. Click on Close after selecting the events");
				click(sequencepage.buttonClose, "Able to click on close button", "Unable to click on close button");

				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.titleSequence, "User didnt save the sequence in the selected folder",
						"Unable to save the sequence in the selected folder");
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
	public void GPS_VerifyDeleteOptionInSequencePage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_27", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_28", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Sequences submenu ");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO,
						"3. Click on 'delete' icon against any of the sequence in the listing table and verify the result.");
				click(sequencepage.iconDelete, "Able to click on delete icon", "Unable to click on delete icon");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.headerDelete, "Header is displayed as expected",
						"Header is displayed as expected");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.placeholderDeleteText, "Able to display a text box with placeholder",
						"Unable to display a textbox with placeholder");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSubmit, "Able to display the button Submit",
						"Unable to display the button Submit");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.iconCross, "Able to display the cross icon sucesfully",
						"Unable to display the cross icon");
				waitforelement(shortwaitvalue);
				click(sequencepage.iconCross);
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
	public void GPS_VerifyDeleteOptionInSequenceWithoutProvidedText() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_28", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_29", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceNam" + "+63e", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Sequences submenu ");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO,
						"Click on 'Submit' button on the popup without providing \"Delete\" text and verify the result ");

				click(sequencepage.iconDelete, "Able to click on delete icon", "Unable to click on delete icon");
				waitforelement(shortwaitvalue);

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSubmit, "Able to display the button Submit",
						"Unable to display the button Submit");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.errorConfirmationRequired, "Able to display the error message sucesfully",
						"Unable to display the error message");

				waitforelement(shortwaitvalue);
				click(sequencepage.iconCross);
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
	public void GPS_VerifyelipsesForTherule() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_29", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_30", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				String textSequenceName = "Demo" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
				sendkeys(sequencepage.textSequenceName, textSequenceName,
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Save and the sequence will be listed in listing page");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO, "Click on Elipses for an individual Sequence");

				sendkeys(sequencepage.textSearch, textSequenceName, "Able to search with the data provided",
						"Unable to get the search data");
				waitforelement(mediumwaitvalue);
				click(sequencepage.eclipseIcon, "Able to click on eclipseIcon ", "Unable to click on eclipseIcon");
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.ecipseIconMoveToFolder, "Able to Display MoveTOFolder link sucesfully",
						"Unable to display the MoveToFolder Link");
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
	public void GPS_VerifyelipsesMoveTOFolderFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_30", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_31", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				String sample = "Demo" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
				sendkeys(sequencepage.textSequenceName, sample, "Able to display the text SequenceNam" + "+63e",
						"Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Save and the sequence will be listed in listing page");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc,
						"Test" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6),
						"Able to enter the value in Task Description", "Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO, "Click on Elipses for an individual Sequence");

				sendkeys(sequencepage.textSearch, sample, "Able to search with the data provided",
						"Unable to get the search data");
				waitforelement(shortwaitvalue);
				click(sequencepage.eclipseIcon, "Able to click on eclipseIcon ", "Unable to click on eclipseIcon");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Click on Move to Folder");

				click(sequencepage.ecipseIconMoveToFolder, "Able to Display MoveTOFolder link sucesfully",
						"Unable to display the MoveToFolder Link");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textMovetoFolder, "Header the MovetoFolder sucesfully",
						"Unable to display the MovetoFolder header");

				isdisplay(sequencepage.headerFolderName, "Header the FolderName sucesfully",
						"Unable to display the FolderName header");

				isdisplay(sequencepage.buttonClose, "Able to display the close button",
						"Unable to display the close button");
				isdisplay(sequencepage.buttonSave, "Unable to display the save button",
						"Unable to display the save button");
				click(sequencepage.buttonClose);
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
	public void GPS_VerifyCloseFunctionalityWithMoveToFolder() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_31", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_32", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, "Demo", "Able to display the text SequenceNam" + "+63e",
						"Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Save and the sequence will be listed in listing page");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc, "Test", "Able to enter the value in Task Description",
						"Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO, "Click on Elipses for an individual Sequence");

				sendkeys(sequencepage.textSearch, "Demo", "Able to search with the data provided",
						"Unable to get the search data");
				waitforelement(shortwaitvalue);
				click(sequencepage.eclipseIcon, "Able to click on eclipseIcon ", "Unable to click on eclipseIcon");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Click on Move to Folder");

				click(sequencepage.ecipseIconMoveToFolder, "Able to Display MoveTOFolder link sucesfully",
						"Unable to display the MoveToFolder Link");
				waitforelement(shortwaitvalue);
				click(sequencepage.textMovetoFolder, "Header the MovetoFolder sucesfully",
						"Unable to display the MovetoFolder header");

				test.log(LogStatus.INFO, "Click on Folder name and select a folder");
				click(sequencepage.dropDownSelectFolder, "Able to click on dropDownSelectFolder",
						"Unable to click on DropdownSelctFolder");
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.searchFolderDropDown, "Test Sequence", "Able to click on Search Folder dropDown",
						"Unable to click on Search Folder");

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "SequenceFolder", xlsname),
						"Able to click on Sequences Folder sucesfully", "Unable to click on Sequence Folder");

				waitforelement(longwaitvalue);
				test.log(LogStatus.INFO, "6. Click on Close");

				click(sequencepage.buttonClose, "Able to close the button", "Unable to close the button");
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.buttonAddSequence, "All the data is cleared sucesfully",
						"Unable to clear the data");

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
	public void GPS_VerifyAddFolder() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_32", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_33", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on Automation");

				isdisplay(sequencepage.dropDownAddNewFolder,
						"Able to display Add NewFolder DropDown beside Add Sequence button",
						"Unable to display Add NewFolder DropDown beside Add Sequence button");

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
	public void GPS_VerifyAddNewFolderFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_33", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_34", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on Automation");

				isdisplay(sequencepage.dropDownAddNewFolder,
						"Able to display Add NewFolder DropDown beside Add Sequence button",
						"Unable to display Add NewFolder DropDown beside Add Sequence button");

				test.log(LogStatus.INFO, " Click on Add New Button");

				click(sequencepage.dropDownAddNewFolder, "Able to click on Add NewFolder Sucesfully",
						"Unable to click on Add NewFolder");

				isdisplay(sequencepage.titleAddNewFolder, "Able to view the title for Add New Folder Sucesfully",
						"Unable to display the title");

				isdisplay(sequencepage.textFolderName, "Able to see the textfield with Header FolderName",
						"Unable to get the text field");

				isdisplay(sequencepage.buttonClose, "Ablet to display the close button",
						"Unable to display the close button");

				isdisplay(sequencepage.buttonSave, "Able to display the Save Button",
						"Unable to display the save button");

				click(sequencepage.buttonClose);
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
	public void GPS_VerifyAddNewFolderEmptyFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_34", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_35", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on Automation");

				isdisplay(sequencepage.dropDownAddNewFolder,
						"Able to display Add NewFolder DropDown beside Add Sequence button",
						"Unable to display Add NewFolder DropDown beside Add Sequence button");

				test.log(LogStatus.INFO,
						" Click on Add New Button and Click on Text field and click on save or click on anywhere on the application");

				click(sequencepage.dropDownAddNewFolder, "Able to click on Add NewFolder Sucesfully",
						"Unable to click on Add NewFolder");
				click(sequencepage.buttonSave, "Able to click on Save Button", "Unable to click on save button");
				isdisplay(sequencepage.errorFolderName, "Able to get the error as expected",
						"Unable to get the error as expected");

				click(sequencepage.buttonClose);
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
	public void GPS_VerifySequenceTextFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_35", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_36", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Sequence name field");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Succesfully",
						"Unable to click on Add Sequence");

				click(sequencepage.buttonSave);

				isdisplay(sequencepage.errorSequenceName, " Able to get  the field as mandatory.",
						"Unable to get the field as mandatory");

				isdisplay(sequencepage.textSequencename, "Able to display a placeholder as Enter Sequence Name",
						"Unable to display a placeholder as Enter Sequence Name");

				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to allow user to enter all characters in the field",
						"Unable to allow user to enter all characters in the field");

				sequencepage.verifyWordsAbove40Characters(100, driver.findElement(sequencepage.textSequencename),
						"Able to enter not more than 100 ", "Unable to enter not more than 100");

				click(sequencepage.buttonClose);
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
	public void GPS_VerifyFolderDropDown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_36", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_37", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				click(sequencepage.buttonAddSequence);
				isdisplay(sequencepage.dropDownSelectFolder, "Application should display placeholder as Select Folder",
						"Unable to display placeholder as Select Folder");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "SequenceFolder", xlsname),
						" Able to display all the create folders as it is the listing page.",
						"Unable to  display all the create folders as it is the listing page.");
				click(sequencepage.dropDownSelectFolder);
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.searchFolderDropDown, excelutil.getData("Sequences", "SequenceFolder", xlsname),
						"Able to enter the folder created", "Unable to enter the folder created");
				waitforelement(shortwaitvalue);
				// System.out.println("element" + excelutil.getData("Sequences",
				// "ElementName_1", xlsname));
				// System.out.println("element1" + excelutil.getData("Sequences",
				// "SequenceFolder", xlsname));
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "SequenceFolder", xlsname),
						"Able to allow user to select only one folder from the dropdown",
						"Unable to allow user to select only one folder from the dropdown");
				click(sequencepage.buttonClose);
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
	public void GPS_VerifyFolderSaveFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_37", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_38", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "SequenceFolder", xlsname),
						"Able to allow user to select only one folder from the dropdown",
						"Unable to allow user to select only one folder from the dropdown");
				click(sequencepage.buttonClose);
				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to enter the textSearch Sucesfully", "Unable to enter the textSearch ");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textNoSearchResults, "Sequence is not created untill clicking on save button",
						"Able to create the sequence even after clicking on save button");
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
	public void GPS_VerifyCustomToggleTime() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_38", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_39", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				System.out.println("Danger zone is gone");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.toggleCustomTimeOff);
				System.out.println("Danger zone Entered");
				test.log(LogStatus.INFO,
						"Verify the 'Custom time' toggle button in left box On the 'Sequence Configuration' page");

				isdisplay(sequencepage.toggleCustomTimeON, "CustomTimeOn toggle Sucesfuly",
						"Unable to on the CustomTime Toggle");

				isdisplay(sequencepage.dropDownSchedule, "Able to display schedule dropdown",
						"Unable to display schedule dropdown");

				isdisplay(sequencepage.selectIncludeDays, "Able to display the selected included days",
						"Unable to display the selected included days");

				isdisplay(sequencepage.dropDownStartDate, "Able to display the start date",
						"Unable to display the start date");

				isdisplay(sequencepage.dropDownEndDate, "Able to display the End date",
						"Unable to display the end date");

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
	public void GPS_VerifyUserDropDown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_39", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_40", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO,
						"Turn Off 'Custom time' toggle button in left box on the 'Sequence Configuration' page and Verify the 'User dropdown'");

				click(sequencepage.dropDownUsers_1);

				isdisplay(sequencepage.dropDownUsers, "Able to  display all the User in the respective organization",
						"Unable to  display all the User in the respective organization.");

				click(sequencepage.dropDownUsers_1, "Able to allow admin to select multiple users from the dropdown",
						"Unable to allow admin to select multiple users from the dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.dropDownUsers_1);

				sendkeys(sequencepage.textSearch_Users, excelutil.getData("Sequences", "user", xlsname),
						"Able to enter the user in the search to select",
						"Unable to enter the user in the search to select");

				waitforelement(shortwaitvalue);
				sequencepage.elementClick("span", excelutil.getData("Sequences", "user", xlsname),
						"Able to click on user selected", "Unable to click on the user selected");

				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "user", xlsname),
						"Able to  display the selected user below the User dropdown",
						"Unable to  display the selected user below the User dropdown");

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
	public void GPS_VerifyNextSequenceDropDown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_40", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_41", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO,
						"Turn Off 'Custom time' toggle button in left box on the 'Sequence Configuration' page and Verify the 'Next sequence dropdown'");

				isdisplay(sequencepage.dropDownSelectSequence, "Able to display Placeholder as Select Sequence",
						"Unable to display Placeholder as Select Sequence");

				click(sequencepage.dropDownSelectSequence);

				sendkeys(sequencepage.searchFolderDropDown, excelutil.getData("Sequences", "SequenceList", xlsname),
						"Able to display all the Sequences of the respective organization.",
						"Unable to display all the Sequences of the respective organization.");

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "SequenceList", xlsname),
						"Able to select Sequences from the list and can select only 1 from the list. ",
						"Unable to select Sequences from the list and able to select multiple.");

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
	public void GPS_VerifyEmailAddress() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_41", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_42", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO,
						"Turn Off 'Custom time' toggle button in left box on the 'Sequence Configuration' page and Verify the 'From address field");

				isdisplay(sequencepage.textFromAddress, "Able to display Text fields with placeholders as Enter Name",
						"Unable to display Text fields with placeholders as Enter Name");

				sendkeys(sequencepage.textFromAddress, excelutil.getData("Sequences", "characters", xlsname),
						"Able to Allow Copy paste,AlphaNumeric ", "Unable to Allow Copy paste,AlphaNumeric");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textFromAddress),
						"Able to get allow of 40 characters", "Unable to get 40 characters");

				isdisplay(sequencepage.textToAddress,
						"Able to display Text fields with placeholders as Enter Email Address",
						"Unable to display Text fields with placeholders as Enter Email Address");

				sendkeys(sequencepage.textToAddress, excelutil.getData("Sequences", "emailid", xlsname),
						"Able to get Mandatory for From email address format as text@text.com",
						"Unable to get  Mandatory for From email address format as text@text.com");

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
	public void GPS_VerifyTagName() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_42", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_43", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO,
						"Turn Off 'Custom time' toggle button in left box on the 'Sequence Configuration' page and Verify the Tags field");
				sendkeys(sequencepage.textTags, excelutil.getData("Sequences", "tagname", xlsname),
						"Able to suggest the tags list in the organization when admin enters alphabets in the field.",
						"Unable to suggest the tags list in the organization when admin enters alphabets in the field.");
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "tagname", xlsname),
						"Able to allow admin to select one tag from the suggestion list.",
						"Unable to allow admin to select one tag from the suggestion list.");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "tagname", xlsname),
						"Able to allow admin to add multiple tags and should display tags below the tags text field with cross icon",
						"Unable to allow admin to add multiple tags and should display tags below the tags text field with cross icon");
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyCrossMark() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_43", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_44", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO,
						"Turn Off 'Custom time' toggle button in left box on the 'Sequence Configuration' page and Click on Cross mark");
				sendkeys(sequencepage.textTags, excelutil.getData("Sequences", "tagname", xlsname),
						"Able to suggest the tags list in the organization when admin enters alphabets in the field.",
						"Unable to suggest the tags list in the organization when admin enters alphabets in the field.");
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "tagname", xlsname),
						"Able to allow admin to select one tag from the suggestion list.",
						"Unable to allow admin to select one tag from the suggestion list.");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "tagname", xlsname),
						"Able to allow admin to add multiple tags and should display tags below the tags text field with cross icon",
						"Unable to allow admin to add multiple tags and should display tags below the tags text field with cross icon");
				waitforelement(shortwaitvalue);
				click(sequencepage.iconClsoe, "Able to remove the respective tag from the page",
						"Unable to remove the respective tag from the page");
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

	// From monday-07-07-2025
//Data issue 1
	@Test
	public void GPS_VerifyExistingTemplate() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_44", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_45", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, " Click on SMS module.");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on  Select Existing template dropdown and verify the results");

				click(sequencepage.dropDownSelectExistingTemplate, "Able to click on Existing Template dropDown",
						"Unable to click on Existing Template dropDown");
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "SideLink1", xlsname),
						"Able to enter the SMS 1 text from the dropdown",
						"Unable to select the sms text from the dropdown");

				sequencepage.elementClick("span", excelutil.getData("Sequences", "SideLink1", xlsname),
						"Able to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)",
						"Unable to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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

//No issues
	@Test
	public void GPS_VerifyFieldsSendEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_45", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_46", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Email' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				sendkeys(sequencepage.textComposeName, excelutil.getData("Sequences", "tagname", xlsname),
						"Able to  Enter Name as placeholder", "Unable to  Enter Name as placeholder");
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "tagname", xlsname),
						"Able to  Enter Subject as placeholder.", "Unable to  Enter Subject as placeholder.");
				isdisplay(sequencepage.dropDownSelectExistingTemplate,
						"Able to dropdown with placeholder as Select Template",
						"Unable to dropdown with placeholder as Select Template");

				isdisplay(sequencepage.textAttachment, "Able to display the Attachment ",
						"Unable to display the attachment");

				isdisplay(sequencepage.buttonSave, "Able to display the save button",
						"Unable to display the save button");

				isdisplay(sequencepage.textFromAddressEmail, "Able to display the from Address",
						"Unable to get the from address text");

				isdisplay(sequencepage.textToAddressEmail, "Able to display the to Address",
						"Unable to display the to address text");

				isdisplay(sequencepage.buttonSendText, "Able to display the SendText",
						"Unable to display the sendText");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyNameSendEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_46", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_47", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, " Click on Email Module and Click on Name and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");

				sequencepage.getText(excelutil.getData("Sequences", "defaultEmailText", xlsname),
						driver.findElement(sequencepage.textComposeName),
						"Able to  display Email 1 as text by default.",
						"Unable to  display Email 1 as text by default.");

				clear(sequencepage.textComposeName, "Able to clear the default name", "Able to clear the default name");
				sendkeys(sequencepage.textComposeName, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to display user edit the default name and should allow to enter special characters and alpha numerics",
						"Unable to display user edit the default name and should allow to enter special characters and alpha numerics");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textComposeName),
						"Able to allow user to enter maximum of 40 characters in the field",
						"Unable to allow user to enter maximum of 40 characters in the field");

				// textSms

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifySubjectSendEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_47", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_48", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Click on Subject and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");

				// sequencepage.getText(excelutil.getData("Sequences", "defaultEmailText",
				// xlsname), driver.findElement(sequencepage.textSubject), "Able to display
				// Email 1 as text by default.", "Unable to display Email 1 as text by
				// default.");

				// clear(sequencepage.textComposeName, "Able to clear the default name", "Able
				// to clear the default name");
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to display user edit the default name and should allow to enter special characters and alpha numerics",
						"Unable to display user edit the default name and should allow to enter special characters and alpha numerics");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textSubject),
						"Able to allow user to enter maximum of 100 characters in the field",
						"Unable to allow user to enter maximum of 100 characters in the field");

				// textSms

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifySubjectInSendEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_48", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_49", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Click on Subject and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");

				click(sequencepage.dropDownSelectExistingTemplate,
						"Able to display all the Email templates for the respective Organization.",
						"Unable to display all the Email templates for the respective Organization.");

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "EmailTemplate", xlsname),
						"Able to allow user to select any one of the template and Application should display the saved template data in the body with attachments",
						"Unable to allow user to select any one of the template and Application should display the saved template data in the body with attachments");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyVoiceMailPage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_49", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_50", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Voicemail' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleVoiceEmail, "Able to display header as VoiceEmail",
						"Unable to display display header as VoiceEmail");

				isdisplay(sequencepage.dragAndDrop, "Able to display Voicemail label with upload button field",
						"Unable to display Voicemail label with upload button field");

				isdisplay(sequencepage.toggleCustomTime, "able to display the customTime toggle",
						"Unable to display the customtime toggle");

				isdisplay(sequencepage.buttonSave, "Able to display the save button",
						"Unable to display the save button");
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyUploadFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_50", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_51", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Voicemail' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleVoiceEmail, "Able to display header as VoiceEmail",
						"Unable to display display header as VoiceEmail");

				isdisplay(sequencepage.dragAndDrop, "Able to display Voicemail label with upload button field",
						"Unable to display Voicemail label with upload button field");

				// click(sequencepage.dragAndDrop, "Able to click on dragAndDrop", "Unable to
				// click on draganddrop");
				waitforelement(shortwaitvalue);
				// GPS_SysMySettingsPage sysmysettingspage=new GPS_SysMySettingsPage();
				chooseFile(sequencepage.dragAndDrop, driver.findElement(sequencepage.dragAndDrop),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ "file_example_MP3_700KB.mp3",
						"User able to Upload File.", "User unable to Upload File.");
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.iconDownloadEmail,
						"Able to display Play/Pause,Stop,Play Speed(1x,2x,3x), Volume button, Download button and Delete icons when a file is uploaded.",
						"Unable to display Play/Pause,Stop,Play Speed(1x,2x,3x), Volume button, Download button and Delete icons when a file is uploaded.");
				isdisplay(sequencepage.icondelete, "Able to display the duration of the audio and running duration",
						"Unable to display the duration of the audio and running duration");

				click(sequencepage.icondelete, "Able to  display Remove icon button ",
						"Unable to  display Remove icon button ");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyInvalidUploadFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_51", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_52", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO,
						"Click on 'Add Sequence' button and add the Sequence name and Select an invalid file other than Mp3");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Voicemail' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleVoiceEmail, "Able to display header as VoiceEmail",
						"Unable to display display header as VoiceEmail");

				isdisplay(sequencepage.dragAndDrop, "Able to display Voicemail label with upload button field",
						"Unable to display Voicemail label with upload button field");

				// click(sequencepage.dragAndDrop, "Able to click on dragAndDrop", "Unable to
				// click on draganddrop");
				waitforelement(shortwaitvalue);
				// GPS_SysMySettingsPage sysmysettingspage=new GPS_SysMySettingsPage();
				chooseFile(sequencepage.dragAndDrop, driver.findElement(sequencepage.dragAndDrop),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ "Email Attachement.csv",
						"Able to  display an alert message Invalid File Format",
						"Unable to  display an alert message Invalid File Format");
				waitforelement(mediumwaitvalue);

				isdisplay(sequencepage.dragAndDrop,
						"Able to  allow user to upload image another time when user clicks on Choose file.",
						"Unable to  allow user to upload image another time when user clicks on Choose file.");
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifySelectedCustomValuesAndCustomFields() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_52", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_53", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO,
						"Click on 'Add Sequence' button and add the Sequence name and Select an invalid file other than Mp3");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Call' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");

				waitforelement(shortwaitvalue);

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_1", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_2", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyAddSaveEventOneByOne() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_53", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_54", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO,
						"Click on 'Add Sequence' button and add the Sequence name and Select an invalid file other than Mp3");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Call' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");

				waitforelement(shortwaitvalue);

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_1", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_2", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSave, "Able to click on event sucesfully",
						"Unable to click on event sucesfully");
				click(sequencepage.buttonSave);

				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleSend,
						"Able to  display respective custom days when the event should trigger",
						"Unable to  display respective custom days when the event should trigger");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Save button");
				isdisplay(sequencepage.editIcons, "Able to display the icon edit", "Unable to display the icon edit");
				isdisplay(sequencepage.deleteIcons, "Able to display the icon delete",
						"Unable to display the icon delete");

				isdisplay(sequencepage.titleSend,
						"able to display a label as Send with two dropdowns and a text box for all events except for Wait event",
						"Unable to display a label as Send with two dropdowns and a text box for all events except for Wait event");
				isdisplay(sequencepage.dropdownAfter_1,
						"dropdown-able to display a label as Send with two dropdowns and a text box for all events except for Wait event",
						"dropdown-Unable to display a label as Send with two dropdowns and a text box for all events except for Wait event");
				isdisplay(sequencepage.dropdownMinutes_1,
						"dropdown-able to display a label as Send with two dropdowns and a text box for all events except for Wait event",
						"dropdown-Unable to display a label as Send with two dropdowns and a text box for all events except for Wait event");
				isdisplay(sequencepage.textZero,
						"Text-able to display a label as Send with two dropdowns and a text box for all events except for Wait event",
						"Text-Unable to display a label as Send with two dropdowns and a text box for all events except for Wait event");

				test.log(LogStatus.INFO, "click on Delete");
				waitforelement(shortwaitvalue);
				click(sequencepage.iconDelete_Dup, "Able to click on delete icon", "Able to click on delete icon");
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.headerDelete,
						"Able to  display a popup with header as  Enter word delete to remove the event",
						"Unable to  display a popup with header as  Enter word delete to remove the event");
				isdisplay(sequencepage.placeholderDeleteText, "Able to display text field with placeholder Enter word",
						"Unable to display text field with placeholder Enter word");
				isdisplay(sequencepage.buttonSubmit, "Able to display text field with buttonSubmit",
						"Unable to display text field with buttonSubmit");
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.errorConfirmationRequired, "Able to display validation Enter correct word",
						"Unable to display validation Enter correct word");

				isdisplay(sequencepage.iconCross, "Able to display Cross mark", "Unable to display Cross mark");

				test.log(LogStatus.INFO, "Enter word other than delete and click on submit");

				sendkeys(sequencepage.placeholderDeleteText, excelutil.getData("Sequences", "Tagname_1", xlsname),
						"Able to send the text sucesfully", "Unable to send the text");

				click(sequencepage.buttonSubmit);

				sendkeys(sequencepage.placeholderDeleteText,
						excelutil.getData("Sequences", "deletesampletext", xlsname),
						"Able to enter the delete text sucesfully", "Unable to enter the delete text");
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				/*
				 * click(sequencepage.buttonClose);
				 */
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifySendLabelDropDown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_54", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_55", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO,
						"Click on 'Add Sequence' button and add the Sequence name and Select an invalid file other than Mp3");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Call' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");

				waitforelement(shortwaitvalue);

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_1", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_2", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSave, "Able to click on event sucesfully",
						"Unable to click on event sucesfully");
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleSend,
						"Able to  display respective custom days when the event should trigger",
						"Unable to  display respective custom days when the event should trigger");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on the 2 dropdowns and text field one by one");

				click(sequencepage.dropdownAfter_1);
				waitforelement(mediumwaitvalue);
				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "After_1", xlsname),
						"Able to enter the dropdowntext", "Unable to enter the dropdownText");
				waitforelement(mediumwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "After_1", xlsname),
						"Able to  display the following fields Before in the dropdown",
						"Unable to  display the following fields Before in the dropdown");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.dropdownAfter_1);
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "After_2", xlsname),
						"Able to enter the dropdowntext", "Unable to enter the dropdownText");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "After_2", xlsname),
						"Able to  display the following fields Before in the dropdown",
						"Unable to  display the following fields Before in the dropdown");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.dropdownAfter_1);
				waitforelement(shortwaitvalue);

				waitforelement(shortwaitvalue);
				;

				// click on minutes dropdown

				click(sequencepage.dropdownMinutes_1);

				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Minutes_1", xlsname),
						"Able to enter the dropdowntext", "Unable to enter the dropdownText");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Minutes_1", xlsname),
						"Able to  display the following fields Before in the dropdown",
						"Unable to  display the following fields Before in the dropdown");

				click(sequencepage.dropdownMinutes_1);

				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Minutes_2", xlsname),
						"Able to enter the dropdowntext", "Unable to enter the dropdownText");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Minutes_2", xlsname),
						"Able to  display the following fields Before in the dropdown",
						"Unable to  display the following fields Before in the dropdown");

				click(sequencepage.dropdownMinutes_1);

				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Minutes_3", xlsname),
						"Able to enter the dropdowntext", "Unable to enter the dropdownText");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Minutes_3", xlsname),
						"Able to   allow user to change the selected fields multiple times",
						"Unable to  allow user to change the selected fields multiple times");

				sequencepage.getText(excelutil.getData("Sequences", "Value", xlsname),
						driver.findElement(sequencepage.textZero),
						"Able to display user to enter only numbers in the text field and by default '0' should be displayed.",
						"Unable to display allow user to enter only numbers in the text field and by default 0");
				click(sequencepage.dropdownAfter_1);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "After_3", xlsname),
						"Able to allow user to select only one option at a time.",
						"Unable to allow user to select only one option at a time.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "After_3", xlsname),
						"Able to  display the following fields Before in the dropdown",
						"Unable to  display the following fields Before in the dropdown");

				clear(sequencepage.searchTemplate, "Able to clear ", "Unable to clear ");

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
	public void GPS_VerifyDraftInRHS() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_55", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_56", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO,
						"Click on 'Add Sequence' button and add the Sequence name and Select an invalid file other than Mp3");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Call' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");

				waitforelement(shortwaitvalue);

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_1", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_2", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.buttonSave, "Able to click on event sucesfully",
						"Unable to click on event sucesfully");
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleSend,
						"Able to  display respective custom days when the event should trigger",
						"Unable to  display respective custom days when the event should trigger");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "3. Click on Drafts dropdown");

				sequencepage.getvalue("Draft", driver.findElement(sequencepage.dropDownDraft),
						"Able to display the dropdown draft as default",
						"Unable to display the dropdown draft as default");
				waitforelement(shortwaitvalue);

				click(sequencepage.dropdownDraft);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Dropdown_1", xlsname),
						"Able to allow user to select only one option at a time.",
						"Unable to allow user to select only one option at a time.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_1", xlsname),
						"Able to  display the following fields Draft in the dropdown",

						"Unable to  display the following fields Draft in the dropdown");

				click(sequencepage.dropdownDraft);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Dropdown_2", xlsname),
						"Able to allow user to select only one option at a time.",
						"Unable to allow user to select only one option at a time.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_2", xlsname),
						"Able to  display the following fields Active in the dropdown",
						"Unable to  display the following fields Active in the dropdown");
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
	public void GPS_VerifyelipsesNotInFolderFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_56", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_57", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				String sample = "Demo" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
				sendkeys(sequencepage.textSequenceName, sample, "Able to display the text SequenceNam" + "+63e",
						"Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Save and the sequence will be listed in listing page");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc,
						"Test" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6),
						"Able to enter the value in Task Description", "Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO, "Click on Elipses for an individual Sequence");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.textSearch, sample, "Able to search with the data provided",
						"Unable to get the search data");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				click(sequencepage.eclipseIcon, "Able to click on eclipseIcon ", "Unable to click on eclipseIcon");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Click on Move to Folder");

				click(sequencepage.ecipseIconMoveToFolder, "Able to Display MoveTOFolder link sucesfully",
						"Unable to display the MoveToFolder Link");
				waitforelement(shortwaitvalue);
				click(sequencepage.textMovetoFolder, "User able to display placeholder as Select Folder",
						"Unable to display placeholder as Select Folder");

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				// sequencepage.isdisplay(sequencepage.errorFolderName, "Able to display mark
				// the field as mandatory", "Unable to display mark the field as mandatory");

				isdisplay(sequencepage.errorNameDisplayed, "Able to display mark the field as mandatory",
						"Unable to display mark the field as mandatory");

				click(sequencepage.dropdownSelectFolder_1);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to display all the list of folders in the dropdown",
						"Unable to display all the list of folders in the dropdown.");
				waitforelement(shortwaitvalue);// Foldername

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to  allow user to select the folder in the dropdown",
						"Unable to allow user to select the folder in the dropdown");

				click(sequencepage.buttonClose);

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
	public void GPS_VerifyelipsesSaveFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_57", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_58", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				String sample = "Demo" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
				sendkeys(sequencepage.textSequenceName, sample, "Able to display the text SequenceNam" + "+63e",
						"Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Save and the sequence will be listed in listing page");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc,
						"Test" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6),
						"Able to enter the value in Task Description", "Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO, "Click on Elipses for an individual Sequence");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.textSearch, sample, "Able to search with the data provided",
						"Unable to get the search data");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				click(sequencepage.eclipseIcon, "Able to click on eclipseIcon ", "Unable to click on eclipseIcon");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO,
						"4. Click on Move to Folder  and 5. Click on Folder name and select a folder and Click on Save");

				click(sequencepage.ecipseIconMoveToFolder, "Able to Display MoveTOFolder link sucesfully",
						"Unable to display the MoveToFolder Link");
				waitforelement(shortwaitvalue);
				click(sequencepage.textMovetoFolder, "User able to display placeholder as Select Folder",
						"Unable to display placeholder as Select Folder");

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				// sequencepage.isdisplay(sequencepage.errorFolderName, "Able to display mark
				// the field as mandatory", "Unable to display mark the field as mandatory");

				isdisplay(sequencepage.errorNameDisplayed, "Able to display mark the field as mandatory",
						"Unable to display mark the field as mandatory");

				click(sequencepage.dropdownSelectFolder_1);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to display all the list of folders in the dropdown",
						"Unable to display all the list of folders in the dropdown.");
				waitforelement(shortwaitvalue);// Foldername

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to  allow user to select the folder in the dropdown",
						"Unable to allow user to select the folder in the dropdown");

				click(sequencepage.buttonSave, "Able to move the sequence to the selected folder.",
						"Unable to move the sequence to the selected folder.");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifyClose_X_iconFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_58", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_59", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				String sample = "Test" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
				sendkeys(sequencepage.textSequenceName, sample, "Able to display the text SequenceNam" + "+63e",
						"Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Expand Automations menu from LHS");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on Save and the sequence will be listed in listing page");
				clear(sequencepage.textTask, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textTask, "Task121", "Able to enter the Task Text",
						"Unable to enter the text in the task field");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.placeHolderTaskDesc,
						"Test" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6),
						"Able to enter the value in Task Description", "Unable to enter the value in Task Description");
				waitforelement(shortwaitvalue);
				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonClose);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonYes);
				test.log(LogStatus.INFO, "Click on Elipses for an individual Sequence");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.textSearch, sample, "Able to search with the data provided",
						"Unable to get the search data");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				click(sequencepage.eclipseIcon, "Able to click on eclipseIcon ", "Unable to click on eclipseIcon");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO,
						"4. Click on Move to Folder  and 5. Click on Folder name and select a folder and Click on Save");

				click(sequencepage.ecipseIconMoveToFolder, "Able to Display MoveTOFolder link sucesfully",
						"Unable to display the MoveToFolder Link");
				waitforelement(shortwaitvalue);
				click(sequencepage.textMovetoFolder, "User able to display placeholder as Select Folder",
						"Unable to display placeholder as Select Folder");

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				// sequencepage.isdisplay(sequencepage.errorFolderName, "Able to display mark
				// the field as mandatory", "Unable to display mark the field as mandatory");

				isdisplay(sequencepage.errorNameDisplayed, "Able to display mark the field as mandatory",
						"Unable to display mark the field as mandatory");

				click(sequencepage.dropdownSelectFolder_1);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to display all the list of folders in the dropdown",
						"Unable to display all the list of folders in the dropdown.");
				waitforelement(shortwaitvalue);// Foldername

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to  allow user to select the folder in the dropdown",
						"Unable to allow user to select the folder in the dropdown");

				click(sequencepage.buttonSave, "Able to move the sequence to the selected folder.",
						"Unable to move the sequence to the selected folder.");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifyTextFieldFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_59", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_60", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");

				click(sequencepage.dropDownAddNewFolder);

				click(sequencepage.buttonSave);

				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.errorFolderName, "Able to mark the field as mandatory.",
						"Unable to mark the field as mandatory.");

				isdisplay(sequencepage.textFolderName_1, "Able to display a placeholder as Enter Folder Name",
						"Unable to  display a placeholder as Enter Folder Name");

				sendkeys(sequencepage.textFolderName_1, excelutil.getData("Sequences", "characters", xlsname),
						"Able to allow user to enter all characters in the field",
						"Unable to allow user to enter all characters in the field");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textFolderName_1),
						"Able to  restrict the user to enter 40 characters only",
						"Unable to  restrict the user to enter 40 characters only");
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonClose);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifysaveFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_60", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_61", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");

				click(sequencepage.dropDownAddNewFolder);

				click(sequencepage.buttonSave);

				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.errorFolderName, "Able to mark the field as mandatory.",
						"Unable to mark the field as mandatory.");

				isdisplay(sequencepage.textFolderName_1, "Able to display a placeholder as Enter Folder Name",
						"Unable to  display a placeholder as Enter Folder Name");

				String value_1 = excelutil.getData("Sequences", "TextValid_1", xlsname)
						+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

				sendkeys(sequencepage.textFolderName_1, value_1, "Able to create the folder in sequence listing page.",
						"Unable to create the folder in sequence listing page.");

				click(sequencepage.buttonSave);

				sendkeys(sequencepage.textSearch, value_1,
						"Able to  display all the created folders at the top of the sequences",
						"Unable to display all the created folders at the top of the sequences");

				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname), value_1,
						"Able to latest created folder at the top in folder listing and displayed a dropdown for every folder",
						"Unable to latest created folder at the top in folder listing and dropdown for every folder");

				// validText

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifycloseFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_61", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_62", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");

				click(sequencepage.dropDownAddNewFolder);

				String value_1 = excelutil.getData("Sequences", "TextValid_1", xlsname)
						+ UUID.randomUUID().toString().replaceAll("-%", "").substring(0, 6);

				sendkeys(sequencepage.textFolderName_1, value_1, "Able to create the folder in sequence listing page.",
						"Unable to create the folder in sequence listing page.");

				click(sequencepage.buttonClose, " Able create the folder and the pop up should be closed.",
						"Unable to close the popup");
				sendkeys(sequencepage.textSearch, value_1, "Able to  enter the values in textSearch",
						"Unable to enter the values in textSearch");

				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textNoSearchResults,
						"Able to clear the previous data when user clicks in Add New folder again",
						"Unable to clear the previous data when user clicks in Add New folder again");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifySequenceFolder() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_62", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_63", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO,
						"Click on Add New Button and  Click on Text field and enter folder name and  Click on Save and application will display the folder is listing page.and  Click on Side dropdown");

				click(sequencepage.dropDownAddNewFolder);

				String value_1 = excelutil.getData("Sequences", "TextValid_1", xlsname)
						+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

				sendkeys(sequencepage.textFolderName_1, value_1, "Able to create the folder in sequence listing page.",
						"Unable to create the folder in sequence listing page.");

				click(sequencepage.buttonSave, " Able to save the  folder name", "Unable to save the folder name");
				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to  enter the values in textSearch", "Unable to enter the values in textSearch");
				// Foldername
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to open the folder and all the sequences should be displayed in it.",
						"Unable to open the folder and all the sequences should be displayed in it.");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on the side dropdown and click on elipses");

				click(sequencepage.eclipseIcon);

				// sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1",
				// xlsname), excelutil.getData("Sequences", "Foldername", xlsname), "Able to
				// open the folder and all the sequences should be displayed in it.", "Unable to
				// open the folder and all the sequences should be displayed in it.");

				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "RemoveFolder", xlsname),
						"Able to display the remove from the folder", "Unable to display the remove from the folder");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "MoveToFolder", xlsname), "Able to display the Move to Folder",
						"Unable to display the Move to Folder");

				// elementName

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifyRemoveFolder() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_63", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_64", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO,
						"Click on Add New Button and  Click on Text field and enter folder name and  Click on Save and application will display the folder is listing page.and  Click on Side dropdown");

				click(sequencepage.dropDownAddNewFolder);

				String value_1 = excelutil.getData("Sequences", "TextValid_1", xlsname)
						+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

				sendkeys(sequencepage.textFolderName_1, value_1, "Able to create the folder in sequence listing page.",
						"Unable to create the folder in sequence listing page.");

				click(sequencepage.buttonSave, " Able to save the  folder name", "Unable to save the folder name");
				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to  enter the values in textSearch", "Unable to enter the values in textSearch");
				// Foldername
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to open the folder and all the sequences should be displayed in it.",
						"Unable to open the folder and all the sequences should be displayed in it.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO,
						"Click on the side dropdown and click on elipses and click on Remove from folder");

				click(sequencepage.eclipseIcon);
				sequencepage.elementClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "RemoveFolder", xlsname),
						"Able to remove the sequence from particular folder and moved the sequence to sequence listing.",
						"Unable to remove the sequence from particular folder and moved the sequence to sequence listing. ");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifyMoveFolder() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_64", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_65", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");

				test.log(LogStatus.INFO,
						"Click on Add New Button and  Click on Text field and enter folder name and  Click on Save and application will display the folder is listing page.and  Click on Side dropdown");

				click(sequencepage.dropDownAddNewFolder);

				String value_1 = excelutil.getData("Sequences", "TextValid_1", xlsname)
						+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);

				sendkeys(sequencepage.textFolderName_1, value_1, "Able to create the folder in sequence listing page.",
						"Unable to create the folder in sequence listing page.");

				click(sequencepage.buttonSave, " Able to save the  folder name", "Unable to save the folder name");
				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to  enter the values in textSearch", "Unable to enter the values in textSearch");
				// Foldername
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Foldername", xlsname),
						"Able to open the folder and all the sequences should be displayed in it.",
						"Unable to open the folder and all the sequences should be displayed in it.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on the side dropdown and click on elipses and click on Move to folder");

				click(sequencepage.eclipseIcon);
				sequencepage.elementClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "MoveToFolder", xlsname),
						"Able to move the sequence from particular folder ", "Unable to move the sequence");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textMovetoFolder, "Able to display Header as Move to Folder",
						"Unable to display Header as Move to Folder");
				isdisplay(sequencepage.dropDownSelectFolder, "Able to display dropdown as select Folder",
						"Unable to display dropdown as select Folder");
				isdisplay(sequencepage.buttonClose, "Able to display dropdown as select Folder",
						"Unable to display dropdown as select Folder");
				isdisplay(sequencepage.buttonSave,
						"Able to display  Close and Save buttons at the bottom of the pop up",
						"Unable to display  Close and Save buttons at the bottom of the pop up");

				click(sequencepage.buttonClose);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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

//65,66
	@Test
	public void GPS_VerifyTextInSendEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_65", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_66", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Click on Text and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "FontName", xlsname),
						"Able to display Verdana as a default text", "Unable to display verdana as a default text");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "FontSize", xlsname),
						"Able to display 12pt as a default font size", "Unable to display 12pt as a default font size");

				click(sequencepage.buttonSave);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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

//67,68
	@Test
	public void GPS_VerifyScheduledropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_67", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_68", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.toggleCustomTimeOff);
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_1", xlsname),
						"Able to display help statement when dropdown is selected as 'When'",
						"Unable to display help statement when dropdown is selected as 'when'");
				click(sequencepage.dropDownSchedule);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_3", xlsname), "Selecteddropdown as 'If '",
						"Unable to select dropdown as 'if'");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_2", xlsname),
						"Able to display help statement when dropdown is selected as 'IF'",
						"Unable to display help statement when dropdown is selected as 'IF'");

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
	public void GPS_VerifySelectdaysdropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_68", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_69", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Email module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

				// Backgroundcolor

				sequencepage.elementColor(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Backgroundcolor", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname),
						"Able to display selected day with blue color.",
						"Unable to display selected day with Blue color");

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
	public void GPS_VerifyStartEnddropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_69", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_70", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Email module and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				/*
				 * sequencepage.isDisplay(excelutil.getData("Sequences", "elementName",
				 * xlsname),excelutil.getData("Sequences", "Day_1", xlsname),
				 * "Able to allow user to select days.",
				 * "Unable to allow user to select days.");
				 * 
				 * sequencepage.elementClick1(excelutil.getData("Sequences", "elementName",
				 * xlsname), excelutil.getData("Sequences", "Day_2", xlsname),
				 * "Able to allow user to select and deselect multiple days",
				 * "Unable to allow user to select and deselect multiple days");
				 * 
				 * //Backgroundcolor
				 * 
				 * sequencepage.elementColor(excelutil.getData("Sequences", "elementName",
				 * xlsname), excelutil.getData("Sequences", "Backgroundcolor", xlsname),
				 * excelutil.getData("Sequences", "Day_1", xlsname),
				 * "Able to display selected day with blue color.",
				 * "Unable to display selected day with Blue color");
				 */
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_1", xlsname), "Able to display dropdown with time AM ",
						"Unable to display dropdown with time AM ");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_2", xlsname), "Able to display dropdown with time PM",
						"Unable to display dropdown with time PM ");

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
	public void GPS_VerifyAttachmentButton() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_71", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_72", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO,
						"Click on Email module and Click on Attachments button and Select an image and click on select");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);

				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "validImage", xlsname),
						"Able to  display an upload the image in the page",
						"Unable to  display an upload the image in the page");
				waitforelement(mediumwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(longwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_3", xlsname),
						excelutil.getData("Sequences", "Removetext", xlsname),
						"Able to display Remove icon button at the bottom of the image",
						"Unable to display Remove icon button at the bottom of the image");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Sequences", "ElementName_3", xlsname),
						excelutil.getData("Sequences", "Removetext", xlsname),
						"Able to remove the image from the Upload images",
						"Unable to remove the image from the Upload images");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Select an invalid file format and click on select");
				// InvalidImage
				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "InvalidImage", xlsname),
						"Able to display an alert message Invalid File Format",
						"Unable to  display an alert message Invalid File Format");

				test.log(LogStatus.INFO, "Upload an images with more than 30mb.");
				// Morethan25MB
				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "Morethan25MB", xlsname),
						"Able to  display a validation as 'Images size should not be more than 2.5mb'",
						"Unable to   display a validation as 'Images size should not be more than 2.5mb'");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	// 14-07-2025

	@Test
	public void GPS_VerifyMessagerCustomValuesAndCustomFields() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_72", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_73", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name.");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO,
						"1. Click on Messenger module. and 2. Select any of the Custom values and Trigger links");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messager",
						"Unable to click on dropdown Messager");

				waitforelement(shortwaitvalue);

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyMessagerStartEnddropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_73", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_74", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on Messenger module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_1", xlsname), "Able to display dropdown with time AM ",
						"Unable to display dropdown with time AM ");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_2", xlsname), "Able to display dropdown with time PM",
						"Unable to display dropdown with time PM ");

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
	public void GPS_VerifyMessagerScheduledropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_74", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_75", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");

				waitforelement(shortwaitvalue);
				;
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");
				waitforelement(shortwaitvalue);
				click(sequencepage.toggleCustomTimeOff);
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_3", xlsname),
						"Able to display help statement when dropdown is selected as 'When'",
						"Unable to display help statement when dropdown is selected as 'when'");
				click(sequencepage.dropDownSchedule);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_3", xlsname), "Selecteddropdown as 'If '",
						"Unable to select dropdown as 'if'");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_4", xlsname),
						"Able to display help statement when dropdown is selected as 'IF'",
						"Unable to display help statement when dropdown is selected as 'IF'");
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
	public void GPS_VerifyMessagerAttachmentButton() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_75", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_76", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO,
						"Click on Messenger module and Click on Attachments button and Select an image and click on select");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);

				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "validImage", xlsname),
						"Able to  display an upload the image in the page",
						"Unable to  display an upload the image in the page");
				waitforelement(mediumwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(longwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_3", xlsname),
						excelutil.getData("Sequences", "Removetext", xlsname),
						"Able to display Remove icon button at the bottom of the image",
						"Unable to display Remove icon button at the bottom of the image");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Sequences", "ElementName_3", xlsname),
						excelutil.getData("Sequences", "Removetext", xlsname),
						"Able to remove the image from the Upload images",
						"Unable to remove the image from the Upload images");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Select an invalid file format and click on select");
				// InvalidImage
				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "InvalidImage", xlsname),
						"Able to display an alert message Invalid File Format",
						"Unable to  display an alert message Invalid File Format");

				test.log(LogStatus.INFO, "Upload an images with more than 30mb.");
				// Morethan25MB
				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "Morethan25MB", xlsname),
						"Able to  display a validation as 'Images size should not be more than 2.5mb'",
						"Unable to   display a validation as 'Images size should not be more than 2.5mb'");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyMessagerWithEmptyDetails() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_76", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_77", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				click(sequencepage.buttonSave);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Errormessage_2", xlsname),
						"Able to get the required message succesfully", "Unable to get the required message");
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifyMessagerSelectdaysdropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_77", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_78", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Messenger module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

				// Backgroundcolor

				sequencepage.elementColor(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Backgroundcolor", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname),
						"Able to display selected day with blue color.",
						"Unable to display selected day with Blue color");

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
	public void GPS_VerifyMessagerCustomToggleTime() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_78", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_79", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				System.out.println("Danger zone is gone");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.toggleCustomTimeOff);
				System.out.println("Danger zone Entered");
				test.log(LogStatus.INFO,
						"Verify the 'Custom time' toggle button in left box On the 'Sequence Configuration' page");

				isdisplay(sequencepage.toggleCustomTimeON, "CustomTimeOn toggle Sucesfuly",
						"Unable to on the CustomTime Toggle");

				isdisplay(sequencepage.dropDownSchedule, "Able to display schedule dropdown",
						"Unable to display schedule dropdown");

				isdisplay(sequencepage.selectIncludeDays, "Able to display the selected included days",
						"Unable to display the selected included days");

				isdisplay(sequencepage.dropDownStartDate, "Able to display the start date",
						"Unable to display the start date");

				isdisplay(sequencepage.dropDownEndDate, "Able to display the End date",
						"Unable to display the end date");

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
	public void GPS_VerifyVoiceMailAttachmentButton() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_79", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_80", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO,
						"Click on Messenger module and Click on Attachments button and Select an image and click on Voiceemail");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);

				chooseFile(sequencepage.dragAndDrop, driver.findElement(sequencepage.dragAndDrop),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "validImage", xlsname),
						"Able to  display an upload the image in the page",
						"Unable to  display an upload the image in the page");
				waitforelement(mediumwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(longwaitvalue);
				/*
				 * sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_3",
				 * xlsname), excelutil.getData("Sequences", "Removetext", xlsname),
				 * "Able to display Remove icon button at the bottom of the image",
				 * "Unable to display Remove icon button at the bottom of the image");
				 * waitforelement(shortwaitvalue);
				 * sequencepage.elementTextClick(excelutil.getData("Sequences", "ElementName_3",
				 * xlsname), excelutil.getData("Sequences", "Removetext", xlsname),
				 * "Able to remove the image from the Upload images",
				 * "Unable to remove the image from the Upload images");
				 * 
				 * waitforelement(shortwaitvalue);
				 * 
				 */
				test.log(LogStatus.INFO, "Select an invalid file format and click on select");
				// InvalidImage
				chooseFile(sequencepage.dragAndDrop, driver.findElement(sequencepage.dragAndDrop),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "InvalidImage", xlsname),
						"Able to display an alert message Invalid File Format",
						"Unable to  display an alert message Invalid File Format");

				test.log(LogStatus.INFO, "Upload an images with more than 30mb.");
				// Morethan25MB
				chooseFile(sequencepage.dragAndDrop, driver.findElement(sequencepage.dragAndDrop),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "Morethan25MB", xlsname),
						"Able to  display a validation as 'Images size should not be more than 2.5mb'",
						"Unable to   display a validation as 'Images size should not be more than 2.5mb'");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifySmsAttachmentButton() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_80", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_81", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO,
						"Click on Sms module and Click on Attachments button and Select an image and click on select");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown Sms", "Unable to click on dropdown Sms");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);

				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "validImage", xlsname),
						"Able to  display an upload the image in the page",
						"Unable to  display an upload the image in the page");
				waitforelement(mediumwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(longwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_3", xlsname),
						excelutil.getData("Sequences", "Removetext", xlsname),
						"Able to display Remove icon button at the bottom of the image",
						"Unable to display Remove icon button at the bottom of the image");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Sequences", "ElementName_3", xlsname),
						excelutil.getData("Sequences", "Removetext", xlsname),
						"Able to remove the image from the Upload images",
						"Unable to remove the image from the Upload images");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Select an invalid file format and click on select");
				// InvalidImage
				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "InvalidImage", xlsname),
						"Able to display an alert message Invalid File Format",
						"Unable to  display an alert message Invalid File Format");

				test.log(LogStatus.INFO, "Upload an images with more than 30mb.");
				// Morethan25MB
				chooseFile(sequencepage.textAttachment, driver.findElement(sequencepage.textAttachment),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\"
								+ excelutil.getData("Sequences", "Morethan25MB", xlsname),
						"Able to  display a validation as 'Images size should not be more than 2.5mb'",
						"Unable to   display a validation as 'Images size should not be more than 2.5mb'");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifySmsScheduledropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_81", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_82", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");

				waitforelement(shortwaitvalue);
				;
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");
				waitforelement(shortwaitvalue);
				click(sequencepage.toggleCustomTimeOff);
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_3", xlsname),
						"Able to display help statement when dropdown is selected as 'When'",
						"Unable to display help statement when dropdown is selected as 'when'");
				click(sequencepage.dropDownSchedule);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_3", xlsname), "Selecteddropdown as 'If '",
						"Unable to select dropdown as 'if'");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_4", xlsname),
						"Able to display help statement when dropdown is selected as 'IF'",
						"Unable to display help statement when dropdown is selected as 'IF'");
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
	public void GPS_VerifycallScheduledropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_82", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_83", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");

				waitforelement(shortwaitvalue);
				;
				isdisplay(sequencepage.customtime_Toggle_Call, "By default toggle should be OFF",
						"Toggle is on By Default");
				waitforelement(shortwaitvalue);
				click(sequencepage.customtime_Toggle_Call);
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_3", xlsname),
						"Able to display help statement when dropdown is selected as 'When'",
						"Unable to display help statement when dropdown is selected as 'when'");
				click(sequencepage.dropDownSchedule);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_3", xlsname), "Selecteddropdown as 'If '",
						"Unable to select dropdown as 'if'");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_4", xlsname),
						"Able to display help statement when dropdown is selected as 'IF'",
						"Unable to display help statement when dropdown is selected as 'IF'");
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
	public void GPS_VerifySmsSelectdaysdropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_83", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_84", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Sms module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown Sms", "Unable to click on dropdown Sms");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

				// Backgroundcolor

				sequencepage.elementColor(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Backgroundcolor", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname),
						"Able to display selected day with blue color.",
						"Unable to display selected day with Blue color");

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
	public void GPS_VerifySmsStartEnddropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_84", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_85", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on Sms module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown Sms", "Unable to click on dropdown Sms");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_1", xlsname), "Able to display dropdown with time AM ",
						"Unable to display dropdown with time AM ");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_2", xlsname), "Able to display dropdown with time PM",
						"Unable to display dropdown with time PM ");

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
	public void GPS_VerifycallStartEnddropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_85", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_86", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on Call module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customtime_Toggle_Call),
						"Able to click on CustomTime Toggle", "Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_1", xlsname), "Able to display dropdown with time AM ",
						"Unable to display dropdown with time AM ");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_2", xlsname), "Able to display dropdown with time PM",
						"Unable to display dropdown with time PM ");

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
	public void GPS_VerifyCallSelectdaysdropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_86", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_87", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Call module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customtime_Toggle_Call),
						"Able to click on CustomTime Toggle", "Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

				// Backgroundcolor

				sequencepage.elementColor(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Backgroundcolor", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname),
						"Able to display selected day with blue color.",
						"Unable to display selected day with Blue color");

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
	public void GPS_VerifyVoiceMailScheduledropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_87", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_88", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");

				waitforelement(shortwaitvalue);
				;
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");
				waitforelement(shortwaitvalue);
				click(sequencepage.toggleCustomTimeOff);
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_3", xlsname),
						"Able to display help statement when dropdown is selected as 'When'",
						"Unable to display help statement when dropdown is selected as 'when'");
				click(sequencepage.dropDownSchedule);
				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Dropdown_3", xlsname), "Selecteddropdown as 'If '",
						"Unable to select dropdown as 'if'");
				sequencepage.isDisplay(excelutil.getData("Sequences", "ElementName_2", xlsname),
						excelutil.getData("Sequences", "Text_4", xlsname),
						"Able to display help statement when dropdown is selected as 'IF'",
						"Unable to display help statement when dropdown is selected as 'IF'");
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
	public void GPS_VerifyVoiceMailSelectdaysdropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_88", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_89", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, "Click on Voiceemail module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

				// Backgroundcolor

				sequencepage.elementColor(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Backgroundcolor", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname),
						"Able to display selected day with blue color.",
						"Unable to display selected day with Blue color");

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
	public void GPS_VerifyvoicemailStartEnddropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_89", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_90", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on voicemail module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.customTimeToggle), "Able to click on CustomTime Toggle",
						"Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_1", xlsname), "Able to display dropdown with time AM ",
						"Unable to display dropdown with time AM ");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_2", xlsname), "Able to display dropdown with time PM",
						"Unable to display dropdown with time PM ");

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
	public void GPS_VerifyVoiceMailCustomToggleTime() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_90", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_91", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownVoiceemail, "Able to click on dropdown Voiceemail",
						"Unable to click on dropdown Voiceemail");
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				System.out.println("Danger zone is gone");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.toggleCustomTimeOff);
				System.out.println("Danger zone Entered");
				test.log(LogStatus.INFO,
						"Verify the 'Custom time' toggle button in left box On the 'Sequence Configuration' page");

				isdisplay(sequencepage.toggleCustomTimeON, "CustomTimeOn toggle Sucesfuly",
						"Unable to on the CustomTime Toggle");

				isdisplay(sequencepage.dropDownSchedule, "Able to display schedule dropdown",
						"Unable to display schedule dropdown");

				isdisplay(sequencepage.selectIncludeDays, "Able to display the selected included days",
						"Unable to display the selected included days");

				isdisplay(sequencepage.dropDownStartDate, "Able to display the start date",
						"Unable to display the start date");

				isdisplay(sequencepage.dropDownEndDate, "Able to display the End date",
						"Unable to display the end date");

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
	public void GPS_VerifyMessagerName() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_91", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_92", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Click on Subject and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownMessenger, "Able to click on dropdown Messenger",
						"Unable to click on dropdown Messenger");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textComposeName, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");

				// sequencepage.getText(excelutil.getData("Sequences", "defaultEmailText",
				// xlsname), driver.findElement(sequencepage.textSubject), "Able to display
				// Email 1 as text by default.", "Unable to display Email 1 as text by
				// default.");

				// clear(sequencepage.textComposeName, "Able to clear the default name", "Able
				// to clear the default name");

				sendkeys(sequencepage.textComposeName, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to allow user to enter  data", "Unable to allow user to enter  data ");

				sendkeys(sequencepage.textComposeName, excelutil.getData("Sequences", "characters", xlsname),
						"Able to allow user to enter  data and special characters and alpha numerics",
						"Unable to allow user to enter  data and special characters and alpha numerics");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.textComposeName),
						"Able to allow user to enter maximum of 40 characters in the field",
						"Unable to allow user to enter maximum of 40 characters in the field");

				// textSms

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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

//Waittime
	@Test
	public void GPS_VerifyWaitName() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_92", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_93", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO,
						"1. Select the 'Wait' module in the dropdown. and 2. Click on Wait fields and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownWait, "Able to click on dropdown Wait", "Unable to click on dropdown Wait");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.textWait, "Able to display Wait ", "Unable to display display Wait");

				// sequencepage.getText(excelutil.getData("Sequences", "defaultEmailText",
				// xlsname), driver.findElement(sequencepage.textSubject), "Able to display
				// Email 1 as text by default.", "Unable to display Email 1 as text by
				// default.");

				// clear(sequencepage.textComposeName, "Able to clear the default name", "Able
				// to clear the default name");

				sendkeys(sequencepage.textWait, excelutil.getData("Sequences", "textSms", xlsname),
						"Able to allow user to enter  data", "Unable to allow user to enter  data ");
				/*
				 * sendkeys(sequencepage.textWait, excelutil.getData("Sequences", "characters",
				 * xlsname),
				 * "Able to allow user to enter  data and special characters and alpha numerics"
				 * ,
				 * "Unable to allow user to enter  data and special characters and alpha numerics"
				 * );
				 * 
				 * 
				 * sequencepage.verifyWordsAbove40Characters(40,
				 * driver.findElement(sequencepage.textComposeName),
				 * "Able to allow user to enter maximum of 40 characters in the field",
				 * "Unable to allow user to enter maximum of 40 characters in the field");
				 * 
				 */

				sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Minutes_1", xlsname), "Able to display the Minutes",
						"Unable to display the dropdown Minutes");

				click(sequencepage.dropdownSelectFolder_1);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Minutes_2", xlsname), "Able to display the Hours",
						"Unable to display the dropdown Hours");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "Minutes_3", xlsname), "Able to display the Days",
						"Unable to display the dropdown Days");

				// textSms

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyWaitTimeFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_93", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_94", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on Wait module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownWait, "Able to click on dropdown Wait", "Unable to click on dropdown Wait");
				waitforelement(shortwaitvalue);
				/*
				 * actionclick(driver.findElement(sequencepage.customTimeToggle),
				 * "Able to click on CustomTime Toggle",
				 * "Unable to click on CustomTime Toggle");
				 */
				waitforelement(shortwaitvalue);
				// 1
				click(sequencepage.dropdownMinutes_1);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_1", xlsname).toUpperCase(),
						"Able to display dropdown with time AM ", "Unable to display dropdown with time AM ");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Time_2", xlsname).toUpperCase(),
						"Able to display dropdown with time PM", "Unable to display dropdown with time PM ");

				// 2
				click(sequencepage.dropdownDraft);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "DefaultNumber", xlsname).toUpperCase(),
						"Able to display dropdown with time 00 ", "Unable to display dropdown with time 00");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "DefaultNumber", xlsname).toUpperCase(),
						"Able to display dropdown with time 00", "Unable to display dropdown with time 00 ");

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
	public void GPS_VerifyWaitSelectdaysdropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_94", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_95", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequ.enceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Wait module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownWait, "Able to click on dropdown Wait", "Unable to click on dropdown Wait");
				waitforelement(shortwaitvalue);
				// waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

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
	public void GPS_VerifySmsWithEmptyDetails() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_95", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_96", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown Sms", "Unable to click on dropdown Sms");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				click(sequencepage.buttonSave);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Errormessage_2", xlsname),
						"Able to get the required message succesfully", "Unable to get the required message");
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

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
	public void GPS_VerifySMSBreadCrumbFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_96", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_97", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				click(sequencepage.dropdownCall, "Able to display dropdownCall", "Unable to display dropdownCall");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				clear(sequencepage.textComposeName, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.textComposeName, "Call 1", "Able to enter the details",
						"Unable to enter the details");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(longwaitvalue);

				sequencepage.isDisplay("h3", "Call 1", "Able to get the value saved in the Event Right Side",
						"Unable to get the value saved in the Event Right side");

				test.log(LogStatus.INFO, "Click on Breadcrumb  after entering fields");
				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				String task = driver.findElement(sequencepage.textTask).getAttribute("value");
				sequencepage.getText(task, driver.findElement(sequencepage.textTask), "Able to clear the event",
						"Unable to clear the Event");

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
	public void GPS_VerifyPhoneNumberFunctionalityInSMS() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_97", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_98", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");
				// case 1
				test.log(LogStatus.INFO, "Click on Send without entering number");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSendText);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Errormessage_5", xlsname),
						"Able to display 'This field is required'", "Unable to display 'This field is required'");

				// case 2
				// valid phone nuber
				test.log(LogStatus.INFO, "Click on Send without entering Text");
				sendkeys(sequencepage.textPhoneNumber, excelutil.getData("Sequences", "validmobNumber", xlsname),
						"Able to enter phone number", "Unable to enter phone number");
				click(sequencepage.buttonSendText);
				waitforelement(shortwaitvalue);
//sms text is required
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Errormessage_3", xlsname),
						"Able to get the required message succesfully", "Unable to get the required message");

				// case 3:
				// 4444last digits
				sendkeys(sequencepage.textPhoneNumber, excelutil.getData("Sequences", "invalidmobNumber", xlsname),
						"Able to enter phone number", "Unable to enter phone number");
				click(sequencepage.buttonSendText);
//enter valid phone number and span
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Errormessage_4", xlsname),
						"Able to allow  user to enter only 10 numbers'",
						"Unable to allow  user to enter only 10 numbers'");

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				// 955058
				clear(sequencepage.textPhoneNumber, "Able to clear the phonenumber", "Unable to clear the phonenumber");
				sendkeys(sequencepage.textPhoneNumber, excelutil.getData("Sequences", "validmobNumber", xlsname),
						"Able to enter phone number", "Unable to enter phone number");
				click(sequencepage.buttonSendText);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				;
				// SMS was not sent successfully.
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sucesfullmessage_1", xlsname),
						"Able to  send SMS to the user number which was there in the phone number field",
						"Unable to  send SMS to the user number which was there in the phone number field");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifycustomTimeWithThreeEvents() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_98", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_99", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "validText", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				// case 2 :sms

				test.log(LogStatus.INFO, " Click on SMS module.");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on  Select Existing template dropdown and verify the results");

				click(sequencepage.dropDownSelectExistingTemplate, "Able to click on Existing Template dropDown",
						"Unable to click on Existing Template dropDown");
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to enter the SMS 1 text from the dropdown",
						"Unable to select the sms text from the dropdown");

				sequencepage.elementClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)",
						"Unable to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				clear(sequencepage.smstext, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.smstext, excelutil.getData("Sequences", "intminutes", xlsname),
						"Able to SMS 1 is sent at 8:10 am", "Unable to SMS 1 is sent at 8:10 am");
				waitforelement(shortwaitvalue);

				// case 3
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);

				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "validText", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				click(sequencepage.sendAfter_2);

				sendkeys(sequencepage.searchFolderDropDown, excelutil.getData("Sequences", "After_3", xlsname),
						"Email 2 is sent at 8:10 am", "Unable to send at 8:10 am");
				sequencepage.elementClick(excelutil.getData("Sequences", "validText", xlsname),
						excelutil.getData("Sequences", "After_3", xlsname), "Able to click on Immedaite",
						"Unable to click on Immedaite");

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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

	// 98,99
	@Test
	public void GPS_VerifycustomTimeWithThreeEvents_1() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_99", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_100", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "validText", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				// case 2 :sms

				test.log(LogStatus.INFO, " Click on SMS module.");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on  Select Existing template dropdown and verify the results");

				click(sequencepage.dropDownSelectExistingTemplate, "Able to click on Existing Template dropDown",
						"Unable to click on Existing Template dropDown");
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to enter the SMS 1 text from the dropdown",
						"Unable to select the sms text from the dropdown");

				sequencepage.elementClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)",
						"Unable to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
//intminutes

				clear(sequencepage.smstext, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.smstext, excelutil.getData("Sequences", "intminutes", xlsname),
						"Able to SMS 1 is sent at 8:10 am", "Unable to SMS 1 is sent at 8:10 am");
				waitforelement(shortwaitvalue);

				// case 3
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);

				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				click(sequencepage.sendAfter_2);

				clear(sequencepage.emailtext_1, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.emailtext_1, excelutil.getData("Sequences", "intminutes", xlsname),
						"Able to emailtext_1 1 is sent at 8:10 am", "Unable to emailtext_1 1 is sent at 8:10 am");

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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
	public void GPS_VerifycustomTimeWithFourEvents() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_100", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_101", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				// case 2 :sms

				test.log(LogStatus.INFO, " Click on SMS module.");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on  Select Existing template dropdown and verify the results");

				click(sequencepage.dropDownSelectExistingTemplate, "Able to click on Existing Template dropDown",
						"Unable to click on Existing Template dropDown");
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to enter the SMS 1 text from the dropdown",
						"Unable to select the sms text from the dropdown");

				sequencepage.elementClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)",
						"Unable to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				clear(sequencepage.smstext, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.smstext, excelutil.getData("Sequences", "intminutes", xlsname),
						"Able to SMS 1 is sent at 8:10 am", "Unable to SMS 1 is sent at 8:10 am");
				waitforelement(shortwaitvalue);

				// case 3
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);

				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				clear(sequencepage.emailtext, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.emailtext, excelutil.getData("Sequences", "intminutes", xlsname),
						"Able to SMS 1 is sent at 8:10 am", "Unable to SMS 1 is sent at 8:10 am");
//case 4

				test.log(LogStatus.INFO, " Click on SMS module.");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownSms, "Able to click on dropdown sms", "Unable to click on dropdown sms");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on  Select Existing template dropdown and verify the results");

				click(sequencepage.dropDownSelectExistingTemplate, "Able to click on Existing Template dropDown",
						"Unable to click on Existing Template dropDown");
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.searchTemplate, excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to enter the SMS 1 text from the dropdown",
						"Unable to select the sms text from the dropdown");

				sequencepage.elementClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "DefaultSmsText", xlsname),
						"Able to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)",
						"Unable to allow user to select any one of the template.and Application should display the saved template data in the body with attachments (if any)");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				clear(sequencepage.smstext_1, "Able to clear the text", "Unable to clear the text");
				sendkeys(sequencepage.smstext_1, excelutil.getData("Sequences", "intminutes", xlsname),
						"Able to SMS 1 is sent at 8:10 am", "Unable to SMS 1 is sent at 8:10 am");
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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

//101,102
	@Test
	public void GPS_VerifypublishFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_101", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_102", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Schedule dropdown");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_3", xlsname),
						"Able to allow user to enter data in the text field.and allow user to enter special characters and alpha numericsand select custom value and custom field codes in the whisper message.",
						"Unable to allow user to enter data in the text field. and allow user to enter special characters and alpha numerics select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Tagname_4", xlsname),
						"Able to select custom value and custom field codes in the whisper message.",
						"Unable to select custom value and custom field codes in the whisper message.");
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonSave);
				test.log(LogStatus.INFO, "Click on Copy Sequence if there are events");

				click(sequencepage.buttonCopySequence, "Able to click on copy sequence",
						"Unable to click on copy sequence");
				waitforelement(shortwaitvalue);
				;
				sequencepage.isDisplay("h3", "Copy Sequence", "Able to display the copy sequence",
						"Unable to display the copy sequence");

				isdisplay(sequencepage.buttonCopy, "Copy button is displayed sucesfull",
						"Copy button is not displayed");
				isdisplay(sequencepage.buttonCancel, "Cancel button is displayed sucesfull",
						"Cancel button is not displayed");
				isdisplay(sequencepage.dropdownDestinationAgencies,
						"User able to display a dropdown \"Destination Agencies\" and should display all the organizations",
						"Unable to display a dropdown \"Destination Agencies\" and should display all the organizations");

				/*
				 * sequencepage.getText("TestData",
				 * driver.findElement(sequencepage.textSequenceName),
				 * "Able to display the copied sequence name by default.",
				 * "Unable to display the copied sequence name by default.");
				 */

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				click(sequencepage.dropdownDestinationAgencies,
						"User able to display a dropdown \"Destination Agencies\" and should display all the organizations",
						"Unable to display a dropdown \"Destination Agencies\" and should display all the organizations");

				sendkeys(sequencepage.textSearch_Users, "QA Automation", "Able to enter the Search users",
						"Unable to enter the search users");

				sequencepage.elementClick("span", "QA Automation", "Able to enter the Search users",
						"Unable to enter the search users");

				click(sequencepage.textSequenceName);
				sendkeys(sequencepage.textSequenceName, "Demo", "Able to enter the values ",
						"Unable to enter the values");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonCopy);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage, "Sequence copied successfully.",
						"Able to  save the sequence in the selected organization in sequence module",
						"Unable to  save the sequence in the selected organization in sequence module");
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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

	// error to do now please check-In progress
	// 102,103
	@Test
	public void GPS_VerifyFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_102", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_103", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "Sequence_13", xlsname),
						"Able to search for the sequences", "Unable to search for the sequence");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(extraverylongwaitvalue);

				// textNumber

				sequencepage.elementTextClick(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "textNumber", xlsname), "Able to click on  sequencerunner",
						"Unable to click on sequencerunner");

				// driver.findElement(By.xpath("//span[contains(text(),'19')]")).click();
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				waitforelement(longwaitvalue);

				test.log(LogStatus.INFO, "3. Navigate to Pause button");
//Elementname_5
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Elementname_5", xlsname),
						excelutil.getData("Sequences", "Sequence_5", xlsname), "Able to get the name",
						"Unable to get the name");
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Elementname_6", xlsname),
						excelutil.getData("Sequences", "Sequence_6", xlsname), "Able to get the Contact as header",
						"Unable to get the name");
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Elementname_6", xlsname),
						excelutil.getData("Sequences", "Sequence_7", xlsname), "Able to display start date as header",
						"Unable to display start date as header");
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Elementname_6", xlsname),
						excelutil.getData("Sequences", "Sequence_8", xlsname), "Able to display the status as header",
						"Unable to display the status as header");
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Elementname_6", xlsname),
						excelutil.getData("Sequences", "Sequence_9", xlsname),
						"Able to display the assigned to as header", "Unable to display the assigned to as header");
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Elementname_6", xlsname),
						excelutil.getData("Sequences", "Sequence_10", xlsname),
						"Able to display the progress as header", "Unable to display the progress as header");

				click(sequencepage.toolpick1);

				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				click(sequencepage.pauseaction, "Able to display status as Paused when user clicks on Pause",
						"Unable to display status as Paused when user clicks on Pause");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				waitforelement(mediumwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_11", xlsname),
						"Able to get the cancelled action sucesfully", "Unable to get the cancelled action sucesfully");

				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. Navigate to Run buton");

				sequencepage.ListofElement(sequencepage.restartToggle, "Able to click on Restart Button");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_12", xlsname),
						"Able to get the Replay event action processing.",
						"Unable to get the Replay event action processing.");
				// deleting for not increase of count
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				click(sequencepage.deleteoption);

				waitforelement(shortwaitvalue);
				//

				sendkeys(sequencepage.placeholderDeleteText,
						excelutil.getData("Sequences", "deletesampletext", xlsname), "Able to delete the entered word",
						"Unable to delete the entered word");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSubmit);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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

//21st July
	// 103,104

	@Test
	public void GPS_VerifyIncludeDaysFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_103", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_104", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO,
						"4. Turn on 'Custom time' toggle button in left box on the 'Sequence Configuration' page");
				/*
				 * sendkeys(sequencepage.textSearch, "TestData_Donot Delete",
				 * "Able to search for the sequences", "Unable to search for the sequence");
				 * waitforelement(mediumwaitvalue);
				 */
				sequencepage.elementTextClick("td", "TestData_Donot Delete", "Clicking on the sequence",
						"Unable to click on the sequence");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.toggleCustomTimeOff),
						"Able to turn on 'custom time' toggle button in left box on the sequence configuration page",
						"Unable to turn on 'custom time' toggle button in left box on the sequence configuration page");

				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Verify the 'Included Days*' field");

				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");
				waitforelement(shortwaitvalue);
				// Backgroundcolor

				sequencepage.elementColor(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Backgroundcolor", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname),
						"Able to display selected day with blue color.",
						"Unable to display selected day with Blue color");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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
	// 104,105

	@Test
	public void GPS_VerifyStartTimeFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_104", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_105", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO,
						"4. Turn on 'Custom time' toggle button in left box on the 'Sequence Configuration' page");
				/*
				 * sendkeys(sequencepage.textSearch, "TestData_Donot Delete",
				 * "Able to search for the sequences", "Unable to search for the sequence");
				 * waitforelement(mediumwaitvalue);
				 */
				sequencepage.elementTextClick(excelutil.getData("Sequences", "ElementName_4", xlsname),
						excelutil.getData("Sequences", "SequenceName_2", xlsname), "Clicking on the sequence",
						"Unable to click on the sequence");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");
				waitforelement(shortwaitvalue);
				actionclick(driver.findElement(sequencepage.toggleCustomTimeOff),
						"Able to turn on 'custom time' toggle button in left box on the sequence configuration page",
						"Unable to turn on 'custom time' toggle button in left box on the sequence configuration page");

				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "5. Verify the 'Start Time' dropdown");

				// elementName
			//	String startTime = new DataFormatter().formatCellValue(sheet.getRow(1).getCell(getColumnIndex(sheet.getRow(0), "StartTime")));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						"8:00 am", "Able to get Time format as 00:00 AM",
						"Unable to get time format be 08:00 AM");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "elementName", xlsname),
						"8:00 am",
						"Able to display list of all predefined time/slots with 30 mins difference between each time slot",
						"Unable to display list of all predefined time/slots with 30 mins difference between each time slot");

				test.log(LogStatus.INFO, "5. Verify the 'End Time' dropdown");

				sequencepage.elementDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						"5:00 pm", "Able to get Time format as 00:00 AM",
						"Unable to get time format be 05:00 PM");
				sequencepage.elementTextClick(excelutil.getData("Sequences", "elementName", xlsname),
						"5:00 pm",
						"Able to display list of all predefined time/slots with 30 mins difference between each time slot",
						"Unable to display list of all predefined time/slots with 30 mins difference between each time slot");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
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
	// 105,106

	@Test
	public void GPS_VerifyEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_105", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_106", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Click on Subject and verify the results");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleComposeMail, "Able to display header as Compose email with path",
						"Unable to display display header as Compose email with path");
				waitforelement(shortwaitvalue);
				click(sequencepage.dropDownSelectExistingTemplate,
						"Able to display all the Email templates for the respective Organization.",
						"Unable to display all the Email templates for the respective Organization.");
				waitforelement(shortwaitvalue);

				sequencepage.elementClick(excelutil.getData("Sequences", "ElementName_1", xlsname),
						excelutil.getData("Sequences", "EmailTemplate", xlsname),
						"Able to allow user to select any one of the template and Application should display the saved template data in the body with attachments",
						"Unable to allow user to select any one of the template and Application should display the saved template data in the body with attachments");
waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay("h6", " Sequence Configuration ",
						"Able to navigate to sequence configuration page.",
						"Unable to navigate to sequence configuration page.");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	// 105,106

	@Test
	public void GPS_VerifyFromAddress() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_106", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_107", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "Select the 'Email' button in the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");
				waitforelement(shortwaitvalue);
				/*
				 * isdisplay(sequencepage.titleComposeMail,
				 * "Able to display header as Compose email with path",
				 * "Unable to display display header as Compose email with path");
				 * sendkeys(sequencepage.textComposeName, excelutil.getData("Sequences",
				 * "tagname", xlsname), "Able to  Enter Name as placeholder",
				 * "Unable to  Enter Name as placeholder"); sendkeys(sequencepage.textSubject,
				 * excelutil.getData("Sequences", "tagname", xlsname),
				 * "Able to  Enter Subject as placeholder.",
				 * "Unable to  Enter Subject as placeholder.");
				 * isdisplay(sequencepage.dropDownSelectExistingTemplate,
				 * "Able to dropdown with placeholder as Select Template",
				 * "Unable to dropdown with placeholder as Select Template");
				 * 
				 * isdisplay(sequencepage.textAttachment, "Able to display the Attachment ",
				 * "Unable to display the attachment");
				 * 
				 * isdisplay(sequencepage.buttonSave, "Able to display the save button",
				 * "Unable to display the save button");
				 * 
				 */

				isdisplay(sequencepage.textFromAddressEmail, "Able to display the from Address",
						"Unable to get the from address text");

				isdisplay(sequencepage.textToAddressEmail, "Able to display the to Address",

						"Unable to display the to address text");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "3. Click on Send without entering From and To address");
				isdisplay(sequencepage.buttonSendText, "Able to display the SendText",
						"Unable to display the sendText");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSendText);
				isdisplay(sequencepage.errorFromAddress, "Able to get the error message in from address as excepted",
						"Unable to get the error message in from address as excepted");

				isdisplay(sequencepage.errorToAddress, "Able to get the error message in To address as excepted",
						"Unable to get the error message in To address as excepted");

				test.log(LogStatus.INFO, "3. Enter invalid email id");
				sendkeys(sequencepage.textFromAddressEmail, excelutil.getData("Sequences", "invalidText", xlsname),
						"Able to enter the invalidtext", "Unable to enter the invalid Text");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.erroremail, "Able to get the emailid error when entering the invalid email",
						"Unable to get the emailid error when entering the invalid email");
				test.log(LogStatus.INFO, "2. Click on From and To address fields");
				sendkeys(sequencepage.textFromAddressEmail, excelutil.getData("Sequences", "emailid", xlsname),
						"Able to enter the email id", "Unable to enter the email id");
				waitforelement(shortwaitvalue);
				sendkeys(sequencepage.textToAddressEmail, excelutil.getData("Sequences", "emailid", xlsname),
						"Able to enter the email id ", "Unable to enter the email id");
				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.placeholderFromAddress, "Able to get the placeholder from address",
						"Unable to get the placeholder from address");
				waitforelement(shortwaitvalue);

				isdisplay(sequencepage.placeholderToAddress, "Able to get the placeholder To address",
						"Unable to get the placeholder from address");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyHintLabel() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_107", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_108", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on Call module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
			
				actionclick(driver.findElement(sequencepage.customtime_Toggle_Call),
						"Able to click on CustomTime Toggle", "Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "2. Hover mouse on hint label");

				isdisplay(sequencepage.tooltipInfoIcon, "Able to  display a hint message in tooltip",
						"Unable to  display a hint message in tooltip");
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyDisableVoiceMailDetect() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_108", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_109", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(sequencepage.toggleCustomTimeOff, "By default toggle should be OFF",
						"Toggle is on By Default");

				test.log(LogStatus.INFO, " Click on Call module.and Click on Start and End Time dropdown");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownCall, "Able to click on dropdown Call", "Unable to click on dropdown Call");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				actionclick(driver.findElement(sequencepage.customtime_Toggle_Call),
						"Able to click on CustomTime Toggle", "Unable to click on CustomTime Toggle");
				waitforelement(shortwaitvalue);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "2. Navigate to Disable voicemail detect toggle button");

				isdisplay(sequencepage.disableVoicemailDetect, "Able to OFF the toggle button by default.",
						"Unable to  OFF the toggle button by default");

				waitforelement(shortwaitvalue);
				click(sequencepage.disableVoicemailDetect, "Able to allow user to ON the toggle the button",
						"Unable to allow user to ON the toggle the button");
				waitforelement(shortwaitvalue);
				click(sequencepage.toggleCustomTimeON, "Able to click ", "Unable to click");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				loginpage.logout();

				waitforelement(shortwaitvalue);
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

	// Sequences_109
	@Test
	public void GPS_VerifyWaitsavedropdown() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_109", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_110", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "Click on Wait module and Navigate to Select days");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				click(sequencepage.dropdownWait, "Able to click on dropdown Wait", "Unable to click on dropdown Wait");
				waitforelement(shortwaitvalue);
				// waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_1", xlsname), "Able to allow user to select days.",
						"Unable to allow user to select days.");

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Day_2", xlsname),
						"Able to allow user to select and deselect multiple days",
						"Unable to allow user to select and deselect multiple days");

				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
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

	//

	// Sequences_109
	@Test
	public void GPS_VerifySequence() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_110", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_111", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "5. Click onAdd Event button from RHS. and Select Email.");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);

				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");

				test.log(LogStatus.INFO, "At Compose mail Click on Custom values drop down.");

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "7.Click on Appointment option and verify");
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				waitforelement(mediumwaitvalue);

				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_4", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_4", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_4", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_5", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_5", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_5", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_6", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_6", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_6", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_7", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_7", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_7", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_8", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_8", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_8", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_9", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_9", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_9", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_10", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_10", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_10", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_11", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_11", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_11", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_12", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_12", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_12", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_13", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_13", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_13", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_14", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_14", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_14", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_15", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_15", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_15", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_16", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_16", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_16", xlsname));

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
	public void GPS_VerifyClickSequence() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_111", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_112", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "5. Click onAdd Event button from RHS. and Select Email.");

				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);

				click(sequencepage.dropdownEmail, "Able to click on dropdown Email",
						"Unable to click on dropdown Email");

				test.log(LogStatus.INFO, "At Compose mail Click on Custom values drop down.");

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "7.Click on Appointment option and verify");
				sendkeys(sequencepage.textSubject, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the subject ", "Unable to enter the subject");
				waitforelement(mediumwaitvalue);

				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_4", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_4", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_4", xlsname));

				// New steps

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_5", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_5", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_5", xlsname));

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_6", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_6", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_6", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_7", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_7", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_7", xlsname));

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_8", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_8", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_8", xlsname));

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_9", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_9", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_9", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_10", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_10", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_10", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_11", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_11", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_11", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_12", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_12", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_12", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_13", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_13", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_13", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_14", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_14", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_14", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_15", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_15", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_15", xlsname));

				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Sequences", "Tagname_1", xlsname),
						excelutil.getData("Sequences", "Submenu_16", xlsname),
						"Able to display" + excelutil.getData("Sequences", "Submenu_16", xlsname),
						"Unable to to display" + excelutil.getData("Sequences", "Submenu_16", xlsname));

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
	public void GPS_VerifyStopSequence() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_112", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_113", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Navigate to Stop on Response toggle button");

				isdisplay(sequencepage.toggleStopOnResponse, "Able to  turn on the toggle button.",
						"Unable to turn on the toggle button");

				test.log(LogStatus.INFO, "4. Navigate to Stop on Response toggle button and Off the button");

				click(sequencepage.toggleStopOnResponse);

				isdisplay(sequencepage.toggleStopOnResponse,
						"Able to  trigger events regardless of the response from the contact/lead.",
						"Unable to  trigger events regardless of the response from the contact/lead.");

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
	public void GPS_VerifyAllowMultipleToggle_1() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_113", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_114", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and 4. Click on Folder name dropdown");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Navigate to Stop on Response toggle button");

				isdisplay(sequencepage.toggleAllowDate, "Able to  turn on the toggle button.",
						"Unable to turn on the toggle button");

				GPS_ContactsPage contactpage = new GPS_ContactsPage();

				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_15", xlsname), "Able to click on the contact ",
						"Unable to click on contact");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sendkeys(contactpage.textSearchOrg, excelutil.getData("Sequences", "Sequence_16", xlsname),
						"Able to enter the text", "Unable to enter the text");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				sequencepage.elementClick1(excelutil.getData("Sequences", "Elementname_5", xlsname),
						excelutil.getData("Sequences", "Sequence_16", xlsname), "Able to click on the contact ",
						"Unable to click on contact");
				waitforelement(extraverylongwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				click(contactpage.activeSequence_primary);
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_17", xlsname), "Able to click on select Sequence",
						"Unable to click on Select Sequence");
				waitforelement(shortwaitvalue);
				sendkeys(contactpage.search_selectTagFilter, excelutil.getData("Sequences", "Sequence_13", xlsname),
						"Able to search the tag", "Unable to search the tag");
				waitforelement(shortwaitvalue);

				sequencepage.elementClickWithArrow(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_13", xlsname), "Able to click on TextLink",
						"Unable to click on Text");
				waitforelement(shortwaitvalue);
				;
				click(contactpage.rhsSaveInAddPopUp);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_20", xlsname),
						"Able to get the Restriction message sucesfully", "Unable to get the restriction message");
				click(contactpage.rhsCloseButtonInAppointment);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyAllowMultipleToggle_2() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_114", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_115", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				// Second Scenirio

				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "Sequence_14", xlsname),
						"Able to search for the sequences", "Unable to search for the sequence");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(extraverylongwaitvalue);
				sequencepage.elementTextClick("td", excelutil.getData("Sequences", "Sequence_14", xlsname),
						"Able to click on  sequencerunner", "Unable to click on sequencerunner");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				;
				click(sequencepage.toggleAllowDate, "Able to  turn on the toggle button.",
						"Unable to turn on the toggle button");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonUpdate);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				waitforelement(shortwaitvalue);

				sequencepage.toastMessage(sequencepage.errortoastmessage, "Sequence details updated successfully.",
						"Able to update sucesfully", "Unable to update sucesfully");

				waitforelement(shortwaitvalue);

				GPS_ContactsPage contactpage = new GPS_ContactsPage();
				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_15", xlsname), "Able to click on the contact ",
						"Unable to click on contact");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sendkeys(contactpage.textSearchOrg, excelutil.getData("Sequences", "Sequence_16", xlsname),
						"Able to enter the text", "Unable to enter the text");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				sequencepage.elementClick1(excelutil.getData("Sequences", "Elementname_5", xlsname),
						excelutil.getData("Sequences", "Sequence_16", xlsname), "Able to click on the contact ",
						"Unable to click on contact");
				waitforelement(extraverylongwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
//					sequencepage.elementTextClickWithClass("i", "icon icon-edo-close fs-8 m-l-5 cursor-pointer ng-star-inserted", "TestData_Donot Delete ", "Able to perform action", "Unable to perform action");
			sequencepage.ListofElement(sequencepage.cursortoDelete, "Able to delete the cursor to further proces step");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				click(contactpage.activeSequence_primary);
				waitforelement(shortwaitvalue);
				// excelutil.getData("Sequences", "Elementname_5", xlsname),
				// excelutil.getData("Sequences", "Sequence_16", xlsname)
				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_17", xlsname), "Able to click on select Sequence",
						"Unable to click on Select Sequence");
				waitforelement(shortwaitvalue);
				sendkeys(contactpage.search_selectTagFilter, excelutil.getData("Sequences", "Sequence_14", xlsname),
						"Able to search the tag", "Unable to search the tag");
				waitforelement(shortwaitvalue);

				sequencepage.elementClickWithArrow(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_14", xlsname), "Able to click on TextLink",
						"Unable to click on Text");
				waitforelement(shortwaitvalue);
				;
				click(contactpage.rhsSaveInAddPopUp);
				waitforelement(shortwaitvalue);
				// excelutil.getData("Sequences", "elementName", xlsname)

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_18", xlsname),
						"Able to get the added message sucesfully", "Unable to get the added message");
//				click(contactpage.rhsCloseButtonInAppointment);
				waitforelement(shortwaitvalue);

				// deleting the selected one

				//

				// return back scenirio 1

				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sendkeys(sequencepage.textSearch, excelutil.getData("Sequences", "Sequence_14", xlsname),
						"Able to search for the sequences", "Unable to search for the sequence");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(extraverylongwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Sequences", "Elementname_5", xlsname),
						excelutil.getData("Sequences", "Sequence_14", xlsname), "Able to click on  sequencerunner",
						"Unable to click on sequencerunner");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				;
				click(sequencepage.toggleAllowDate, "Able to  turn on the toggle button.",
						"Unable to turn on the toggle button");

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonUpdate);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				waitforelement(shortwaitvalue);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_19", xlsname), "Able to update sucesfully",
						"Unable to update sucesfully");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
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
	public void GPS_VerifyDoNotDistrub() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_115", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_116", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				GPS_ContactsPage contactpage = new GPS_ContactsPage();
				GPS_SequencePage sequencepage = new GPS_SequencePage();
				sequencepage.elementClick1(excelutil.getData("Sequences", "elementName", xlsname),
						excelutil.getData("Sequences", "Sequence_15", xlsname), "Able to click on the contact ",
						"Unable to click on contact");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sendkeys(contactpage.textSearchOrg, excelutil.getData("Sequences", "Sequence_21", xlsname),
						"Able to enter the text", "Unable to enter the text");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				sequencepage.elementTextClick(excelutil.getData("Sequences", "Elementname_5", xlsname),
						excelutil.getData("Sequences", "Sequence_21", xlsname), "Able to click on the contact ",
						"Unable to click on contact");
				waitforelement(extraverylongwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "5. On the Do Not Disturb Toggle button");

				actionclick(driver.findElement(sequencepage.donotcontactToggle), "Able to click on DNC toggle",
						"Unable to click on DNC");
				waitforelement(mediumwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_23", xlsname), "Able to update sucesfully",
						"Unable to update sucesfully");
				waitforelement(shortwaitvalue);

				click(sequencepage.buttonUpdate);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Sequences", "Sequence_22", xlsname), "Able to update sucesfully",
						"Unable to update sucesfully");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
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
	public void GPS_VerifyPublishFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_116", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_117", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and add the Sequence name");
				isdisplay(sequencepage.titleSequence, "Able to navigate the user to Sequence configuration page.",
						"Unable to navigate the user to Sequence configuration page.");
				click(sequencepage.buttonAddSequence);
				sendkeys(sequencepage.textSequencename, excelutil.getData("Sequences", "TextValid_1", xlsname),
						"Able to enter the sequenceName", "Unable to enter the sequencename");
				click(sequencepage.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				
				test.log(LogStatus.INFO, "3. Select publish from the dropdown");
				click(sequencepage.buttonAddEvent);
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname), excelutil.getData("Sequences", "Sequence_24", xlsname), "Able to display SMS", "Unable to display SMS");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname), excelutil.getData("Sequences", "Sequence_25", xlsname), "Able to display Messenger", "Unable to display Messenger");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),excelutil.getData("Sequences", "Sequence_26", xlsname), "Able to display Email", "Unable to display Email");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname), excelutil.getData("Sequences", "Sequence_27", xlsname), "Able to display Voicemail", "Unable to display Voicemail");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),excelutil.getData("Sequences", "Sequence_28", xlsname), "Able to display Call", "Unable to display Call");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname), excelutil.getData("Sequences", "Sequence_29", xlsname), "Able to display Wait", "Unable to display Wait");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname),excelutil.getData("Sequences", "Sequence_30", xlsname), "Able to display Add Task", "Unable to display Add Task");
				sequencepage.isDisplay(excelutil.getData("Sequences", "elementName", xlsname), excelutil.getData("Sequences", "Sequence_31", xlsname), "Able to display Webhook", "Unable to display Webhook");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
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
	public void GPS_VerifyTextAddtaskDescription_2() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Sequences", "HeaderName_117", xlsname));
		if (!isExecutionAllowed())
			return;
		try {
			if (excelutil.getData("Dashboard", "Sequences_118", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				GPS_LoginPage loginpage = new GPS_LoginPage();
				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("Sequences", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Sequences submenu");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("Sequences", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				isdisplay(sequencepage.titleSequence, "Able to display the title sequence succesfully ",
						"Unable to display the title Sequence");
				test.log(LogStatus.INFO, "Click on 'Add Sequence' button");
				click(sequencepage.buttonAddSequence, "Able to click on Add Sequence Button",
						"Unable to click on Add Sequence Button");
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.titleAddNewSequence, "Able to click on AddNewSequence",
						"Unable to click on AddNewSequence");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on 'Add Sequence' button and enter name and click on add sequence");
				sendkeys(sequencepage.textSequenceName, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to display the text SequenceName", "Unable to display the text Sequence Name");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave, "Able to click on save button", "Unable to click on save button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "1. Select the 'Add task' module in the dropdown.");

				click(sequencepage.buttonAddEvent, "Able to display the buttonAddEvent",
						"Unable to display the buttonAddEvent");

				isdisplay(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				click(sequencepage.dropdownAddTask, "Able to display dropdownAddTask",
						"Unable to display dropdownAddTask");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "2. Click on task description");

				sendkeys(sequencepage.placeHolderTaskDesc, excelutil.getData("Sequences", "validSequence", xlsname),
						"Able to add new Data", "Unable to add new Data");
				waitforelement(shortwaitvalue);

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(sequencepage.placeHolderTaskDesc),
						"Maximum character can be entered is 400 characters only", "Maximum characters exceeded");

				click(sequencepage.placeholderDropDownDueIn);

				click(sequencepage.textDate1, "Able to click on days provided in the dropDown",
						"Unable to display the days provided in the dropDown");
				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				click(sequencepage.iconArrow);				
				sequencepage.elementTextClick(excelutil.getData("Sequences", "elementName", xlsname),excelutil.getData("Sequences", "ButtonYes", xlsname), "Able to click on Yes", "Unable to click on Yes");
				
				sequencepage.elementDisplay(excelutil.getData("Sequences", "header", xlsname),excelutil.getData("Sequences", "Sequnec", xlsname), "Able to title of Sequence", "Unable to get the title of sequence");
				
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
			