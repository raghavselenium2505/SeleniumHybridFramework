package com.gps.development;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.GPS_ActivePipelineOpportunitiesPage;
import com.gps.pages.GPS_CreateOpportunityPage;
import com.gps.pages.GPS_HomePage;
import com.gps.pages.GPS_LoginPage;
import com.gps.pages.GPS_OrgDashboardPage;
import com.gps.pages.GPS_SequencePage;
import com.gps.pages.GPS_TagAutomationPage;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

public class GPS_TagAutomation extends TestBase {

	@Test
	public void GPS_verifyLoginApplication_TC_001() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_1", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				isdisplay(tagautomation.titleTagAutomation, "Able to display \"Tag Automations\" as submenu",
						"Unable to display \"Tag Automations\" as submenu");

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

	@Test
	public void GPS_verifyTagAutomationssettings_TC_003() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_1", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_2", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name_1", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				isdisplay(tagautomation.titleTagAutomation, "Able to display \"Tag Automations\" as submenu",
						"Unable to display \"Tag Automations\" as submenu");

				GPS_ActivePipelineOpportunitiesPage gp = new GPS_ActivePipelineOpportunitiesPage();

				test.log(LogStatus.INFO, "Verify the page when no Tag Automations are present");
				// emptyDisplay

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "header", xlsname),
						excelutil.getData("TagAutomation", "emptyDisplay", xlsname),
						"Able to display display \"There are no items to display\" message ",
						"Unable to display display \"There are no items to display\" message ");

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

	@Test
	public void GPS_verifytextSearch_TC_05_06_07() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_2", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_3", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				test.log(LogStatus.INFO, "When the User enters text into Search text box");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "ValidChar", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				test.log(LogStatus.INFO, "When the User enters \"Alphanumeric characters");

				String copiedText = driver.findElement(tagautomation.textSearch).getAttribute("value");
				// System.out.println("s1"+s1);

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "invalidChar", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				test.log(LogStatus.INFO, "When the User clears the text on search field");
				clear(tagautomation.textSearch, "Able to clear text in search field",
						"Unable to clear text in search field");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "When the user copy and paste the text ");
				sendkeys(tagautomation.textSearch, copiedText, "Able to pasting copied text in the Search field ",
						"Unable to pasting copied text in the search field");
				GPS_SequencePage sequencepage = new GPS_SequencePage();
				sequencepage.getText(copiedText, driver.findElement(tagautomation.textSearch),
						"Able to display copy text in the Search field ",
						"Unable to display copy text in the Search field ");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "If respective text search result was not found");
				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "invalidChar", xlsname),
						"Able to pasting copied text in the Search field ",
						"Unable to pasting copied text in the search field");
				waitforelement(shortwaitvalue);
				// header
				sequencepage.isDisplay(excelutil.getData("TagAutomation", "header", xlsname),
						excelutil.getData("TagAutomation", "Errormatchfound", xlsname),
						"Able to  display \"No result found \"message \"",
						"Unable to  display \"No result found \"message \"");

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

	@Test
	public void GPS_verifyAutomationIcon_TC_04() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_3", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_4", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				test.log(LogStatus.INFO, "When the User enters text into Search text box");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "ValidChar", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				isdisplay(tagautomation.textSearch,
						"Able to display the Search text field before the Add Tag Automations button",
						"Unable to display the Search text field before the Add Tag Automations button");

				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display Add Tag Automation button at the top right side of the page.");

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Header-2", xlsname),
						excelutil.getData("TagAutomation", "Tag_1", xlsname), "Able to display Name",
						"Unable to display Name");

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Header-2", xlsname),
						excelutil.getData("TagAutomation", "Tag_2", xlsname), "Able to display Name",
						"Unable to display Name");

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Header-2", xlsname),
						excelutil.getData("TagAutomation", "Tag_3", xlsname), "Able to display Name",
						"Unable to display Name");

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Header-2", xlsname),
						excelutil.getData("TagAutomation", "Tag_4", xlsname), "Able to display Name",
						"Unable to display Name");
				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Header-2", xlsname),
						excelutil.getData("TagAutomation", "Tag_5", xlsname), "Able to display Name",
						"Unable to display Name");

				isdisplay(tagautomation.iconedit, "Able to  display Edit ", "Unable to display Edit");

				isdisplay(tagautomation.iconDelete, "Able to  display Delete ", "Unable to display Delete");

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

	// Delete -Note no added tag need to add if required

	@Test
	public void GPS_verifyDeleteButton_TC_46_47_48_49_50() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_4", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_5", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display Add Tag Automation button at the top right side of the page.");

				test.log(LogStatus.INFO, "When the User enters text into Search text box");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "characters", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				isdisplay(tagautomation.textSearch,
						"Able to display the Search text field before the Add Tag Automations button",
						"Unable to display the Search text field before the Add Tag Automations button");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				test.log(LogStatus.INFO, "When the user click delete icon ");

				click(tagautomation.iconDelete, "Able to  display Delete ", "Unable to display Delete");
				waitforelement(shortwaitvalue);

				isdisplay(tagautomation.titleDelete,
						"Able to display Please enter word Delete\"\" to continue Heading/ label",
						"Unable to display Please enter word Delete\"\" to continue Heading/ label");

				isdisplay(tagautomation.textDelete, "Able to display Type Delete\" textbox\"",
						"Unable to display Type Delete\" textbox\"");

				test.log(LogStatus.INFO, "Verify the delete button without providing text");

				click(tagautomation.buttonSubmit);

				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Tag_9", xlsname),
						"Able to display  Confirmation text is required validation message",
						"Unable to display Confirmation text is required validation message");

				test.log(LogStatus.INFO, "Verify the delete button by providing invalid text");

				sendkeys(tagautomation.textDelete, excelutil.getData("TagAutomation", "invalidChar", xlsname),
						"Able to enter the invalid text", "Unable to enter the invalid text");
				waitforelement(shortwaitvalue);
				click(tagautomation.buttonSubmit);
				waitforelement(shortwaitvalue);
				// Enter Correct Word
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("TagAutomation", "Tag_8", xlsname),
						"Able to display Enter Correct Word  toast message",
						"Unable to display Enter Correct Word  toast message");
				test.log(LogStatus.INFO, "Verify the delete button by providing  text");
				sendkeys(tagautomation.textDelete, excelutil.getData("TagAutomation", "Tag_7", xlsname),
						"Able to enter the invalid text", "Unable to enter the invalid text");
				waitforelement(shortwaitvalue);
				click(tagautomation.buttonSubmit);
				waitforelement(shortwaitvalue);
				// Tag_6

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("TagAutomation", "Tag_6", xlsname),
						"Able to display deleted message sucesfuly", "Unable to display deleted message sucesfuly");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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
//			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");
		}
	}

	@Test
	public void GPS_AddAutomationButton_TC_18() { 
		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_5", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_6", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				test.log(LogStatus.INFO,
						"When the User click Add Tag Automation button at the top right side of the  Automation button table.");

				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");
				waitforelement(shortwaitvalue);

				GPS_SequencePage sequencepage = new GPS_SequencePage();

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Field_1", xlsname), "Able to display the Add Sequencepage",
						"Unable to display the title Add Sequencepage");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_2", xlsname),
						excelutil.getData("TagAutomation", "Field_2", xlsname), "Able to display the Add Sequencepage",
						"Unable to display the title Add Sequencepage");
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_2", xlsname),
						excelutil.getData("TagAutomation", "Field_3", xlsname), "Able to display the Add Sequencepage",
						"Unable to display the title Add Sequencepage");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_2", xlsname),
						excelutil.getData("TagAutomation", "Field_4", xlsname), "Able to display the Add Sequencepage",
						"Unable to display the title Add Sequencepage");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_2", xlsname),
						excelutil.getData("TagAutomation", "Field_5", xlsname), "Able to display the Add Sequencepage",
						"Unable to display the title Add Sequencepage");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_2", xlsname),
						excelutil.getData("TagAutomation", "Field_6", xlsname), "Able to display the Add Sequencepage",
						"Unable to display the title Add Sequencepage");

				click(tagautomation.buttonCancel);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_statusColumn_TC_13() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_6", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_7", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display Add Tag Automation button at the top right side of the page.");

				test.log(LogStatus.INFO, "Verify the status column");
				GPS_SequencePage sequencepage = new GPS_SequencePage();
				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_1", xlsname),
						"Able to display status column  like Queued", "Unable to display status column like Queued");

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_2", xlsname),
						"Able to display status column  like Finished",
						"Unable to display status column like Finished");

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_3", xlsname),
						"Able to display status column  like Paused", "Unable to display status column like Paused");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_MouseOverSymbol_TC_17() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_7", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_8", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Verify the mouse over symbol to the Add Tag automation button");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display Add Tag Automation button at the top right side of the page.");

				mousepointer(driver.findElement(tagautomation.buttonAddSequenceLauncher),
						excelutil.getData("TagAutomation", "CursorPoint", xlsname),
						"Able to get display 'Hand' symbol on Add Tag automation button",
						"Unable to  display 'Hand' symbol on Add Tag automation button");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_TagAutomationName_TC_19_20_21_22_23_24_25() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_8", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_9", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");
				test.log(LogStatus.INFO,
						"When the User click on the Tag Automation field and again click on the outside field.");

				click(tagautomation.buttonSave);
				// Element_1
				// Elementrequired

				sequencepage.isDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired", xlsname),
						"Able to display the validation message like Tag Automation name is required",
						"Unable to display the validation message like Tag Automation name is required");

				test.log(LogStatus.INFO, "When the User click and enters the text in Tag Automation field ");

				tagautomation.Placeholder("Sequence Launcher Name",
						driver.findElement(tagautomation.textSequenceLauncher),
						"Able to  display Placeholder  Tag Automation Name",
						"Unable to  display Placeholder  Tag Automation Name");

				sendkeys(tagautomation.textSequenceLauncher, excelutil.getData("TagAutomation", "Validchar_1", xlsname),
						"Able to display the user to enter text in Tag Automation field",
						"Unable to display the user to enter text in Tag Automation field");

				sequencepage.getText(excelutil.getData("TagAutomation", "Validchar_1", xlsname),
						driver.findElement(tagautomation.textSequenceLauncher),
						"Able to display entered text in Tag Automation field ",
						"Unable to display entered text in Tag Automation field ");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO,
						"When User enters 'Alphanumeric & Special  characters' into the Tag Automation  field ");

				sendkeys(tagautomation.textSequenceLauncher, excelutil.getData("TagAutomation", "characters", xlsname),
						"Able to display the to enter Alphanumeric characters and special Characters data",
						"Unable to display the enter Alphanumeric characters and special Characters data");

				test.log(LogStatus.INFO, "When User enters \"100 characters data\" into the Tag Automation field  ");

				sequencepage.verifyWordsAbove40Characters(100, driver.findElement(tagautomation.textSequenceLauncher),
						"Able to enter not more than 100 ", "Unable to enter not more than 100");

				test.log(LogStatus.INFO, "When the User clears the text on Tag Automation field ");
				clear(tagautomation.textSequenceLauncher, "Able to display user to clear text in Tag Automation field ",
						"Unable to user to clear text in Tag Automation field ");

				test.log(LogStatus.INFO, "When the user copy and paste the text in the Tag Automation field ");

				String copiedText = driver.findElement(tagautomation.textSequenceLauncher).getAttribute("value");
				sendkeys(tagautomation.textSequenceLauncher, copiedText,
						"Able to pasting copied text in the Search field ",
						"Unable to pasting copied text in the search field");

				sequencepage.getText(copiedText, driver.findElement(tagautomation.textSequenceLauncher),
						"Able to display copy text in the Search field ",
						"Unable to display copy text in the Search field ");

				click(tagautomation.buttonCancel);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifySequenceDropDown_TC_26_27_28_29() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_9", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_10", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				test.log(LogStatus.INFO,
						"When the user click Sequence drop down and again click outside of the field  ");

				click(tagautomation.dropdownSequence);
				waitforelement(shortwaitvalue);
				click(tagautomation.buttonSave);

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired_1", xlsname),
						"Able to display the validation message like sequence  is required",
						"Unable to display the validation message like Sequence  is required");

				test.log(LogStatus.INFO, "When the user  click on Sequence dropdown ");
				click(tagautomation.dropdownSequence);
				waitforelement(shortwaitvalue);

				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to send details ", "Unable to send details");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display dropdown elements all the Sequence list created in the Sequence module.",
						"Unable to display dropdown elements all the Sequence list created in the Sequence module. ");

				test.log(LogStatus.INFO, "When the user selects the Sequence from the drop down");

				sequencepage.elementTextClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display user to select single sequence from the drop down.",
						"Unable to display user to select single sequence from the drop down.");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display selected Sequence from the drop down",
						"Unable to display selected Sequence from the drop down");

				click(sequencepage.buttonCancel);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifySaveOptionWithEmptyData_TC_40() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_10", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_11", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				test.log(LogStatus.INFO,
						"When the User without enters text in the mandatory fields and click on save button ");

				click(tagautomation.buttonSave);

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired", xlsname),
						"Able to display the validation message like"
								+ excelutil.getData("TagAutomation", "Elementrequired", xlsname),
						"Unable to display the validation message like "
								+ excelutil.getData("TagAutomation", "Elementrequired", xlsname));
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired_1", xlsname),
						"Able to display the validation message like"
								+ excelutil.getData("TagAutomation", "Elementrequired_1", xlsname),
						"Unable to display the validation message like "
								+ excelutil.getData("TagAutomation", "Elementrequired_1", xlsname));
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired_2", xlsname),
						"Able to display the validation message like"
								+ excelutil.getData("TagAutomation", "Elementrequired_2", xlsname),
						"Unable to display the validation message like "
								+ excelutil.getData("TagAutomation", "Elementrequired_2", xlsname));

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired_3", xlsname),
						"Able to display the validation message like "
								+ excelutil.getData("TagAutomation", "Elementrequired_3", xlsname),
						"Unable to display the validation message like"
								+ excelutil.getData("TagAutomation", "Elementrequired_3", xlsname));

				click(tagautomation.buttonCancel);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifySaveOptionWithValidData_TC_39() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_11", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_12", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				test.log(LogStatus.INFO, "When the user enters text into respective fields and click on save button ");

				sendkeys(tagautomation.textSequenceLauncher, excelutil.getData("TagAutomation", "characters", xlsname),
						"Able to display the to enter Alphanumeric characters and special Characters data",
						"Unable to display the enter Alphanumeric characters and special Characters data");

				click(tagautomation.dropdownSequence);
				waitforelement(shortwaitvalue);
//Testdata1
				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to send sequenceSearch Details ", "Unable to sequenceSearch Details");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display dropdown elements all the Sequence list created in the Sequence module.",
						"Unable to display dropdown elements all the Sequence list created in the Sequence module. ");
				waitforelement(shortwaitvalue);
				click(tagautomation.textSelectDateAndTime, "Able to click on SelectDateAndTime",
						"Unable to click on SelectDateand Time");
				waitforelement(shortwaitvalue);
				click(tagautomation.iconcorrect);

				click(tagautomation.dropdownSelectAContactType, "Able to click on SelectAContactType Sucesfully",
						"Unable to click on SelectAContactType");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to click on Dropdown sucesfully", "Unable to click on dropdown");

				click(tagautomation.dropdownSelectaTag, "Able to click on dropdownSelectATag sucesfully",
						"Unable to click on dropdownSelectATag");

				sendkeys(tagautomation.textSearchsequence, "Chandra", "Able to able to enter the tag",
						"Unable to able to enter the tag");
				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to click on Dropdown sucesfully", "Unable to click on dropdown");

				click(tagautomation.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("TagAutomation", "ToastMessage_1", xlsname),
						"Able to get Sucesfull message as"
								+ excelutil.getData("TagAutomation", "ToastMessage_1", xlsname),
						"Unable to get sucesfull message as"
								+ excelutil.getData("TagAutomation", "ToastMessage_1", xlsname));

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyContactTypeDropDown_TC_32_33_34_35() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_12", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_13", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				test.log(LogStatus.INFO, "When the user  click on contact type dropdown ");

				click(tagautomation.dropdownSelectAContactType, "Able to click on SelectAContactType Sucesfully",
						"Unable to click on SelectAContactType");

				// sequencepage.elementClick("span", "All", "Able to click on Dropdown
				// sucesfully", "Unable to click on dropdown");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_3", xlsname),
						"Able to display dropdown elements like All", "Unable to display dropdown elements like All");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_4", xlsname),
						"Able to display dropdown elements like Member",
						"Able to display dropdown elements like Member");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_5", xlsname),
						"Able to display dropdown elements like Non-Member",
						"Unable to display dropdown elements like non-member");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_6", xlsname),
						"Able to display dropdown elements like Lead", "UnAble to display dropdown elements like Lead");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_7", xlsname),
						"Able to display dropdown elements like Membershiplead",
						"Unable to display dropdown elements like MembershipLead");

				test.log(LogStatus.INFO,
						"When the user click contact type drop down and again click outside of the field  ");

				click(tagautomation.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "Elementrequired_2", xlsname),
						"Able to display the validation message like Tag Automation name is required",
						"Unable to display the validation message like Tag Automation name is required");
				test.log(LogStatus.INFO, "When the user selects the contact type from the drop down");

				click(tagautomation.dropdownSelectAContactType);
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("TagAutomation", "Element_1", xlsname),
						excelutil.getData("TagAutomation", "DropDown_3", xlsname),
						"Able to allow the user to select single contact type from the drop down.",
						"Unable to allow the user to select single contact type from the drop down.");

				click(sequencepage.buttonCancel);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyTagDropDown_TC_36_37_38() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_13", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_14", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				test.log(LogStatus.INFO, "When the user  click on tag dropdown ");

				click(tagautomation.dropdownSelectaTag, "Able to click on tag drop down ",
						"Unable to click on tag drop down");

				test.log(LogStatus.INFO,
						"When the user selects the tags from the drop down and When the user selects the tags from the drop down");

				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to able to enter the tag", "Unable to able to enter the tag");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to allow the user to select single tag from the drop down sucesfully",
						"Unable to allow the user to select single tag from the drop down sucesfully");

				click(tagautomation.buttonCancel);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_verifyEditButton_TC_42_43() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_14", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_15", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display  Tag Automation button at the top right side of the page.");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "SequenceName_2", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				isdisplay(tagautomation.textSearch,
						"Able to display the Search text field before the Add Tag Automations button",
						"Unable to display the Search text field before the Add Tag Automations button");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "When the user click Edit icon on Tag automation page");
				waitforelement(shortwaitvalue);
				click(tagautomation.iconedit);
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(tagautomation.titleEditSequenceLauncher,
						"Able to  display title of the Header name as Edit Tag Automation",
						"Unable to  display title of the Header name as Edit Tag Automation");

				waitforelement(shortwaitvalue);
				isdisplay(tagautomation.textSequenceLauncher, "Able to display sequenceLauncher.",
						"Unable to display sequenceLauncher");

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_3", xlsname), "Able to display ContactType",
						"Unable to display ContactType.");

				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname), "Able to display Tag",
						"Unable to display Tag");
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname), "Able to display Sequencedropdown.",
						"Unable to display Sequencedropdown");

				isdisplay(tagautomation.textSelectDateAndTime, "Able to display textSelectDateAndTime.",
						"Unable to display textSelectDateAndTime");

				isdisplay(tagautomation.buttonCancel, "Able to display buttonCancel", "Unable to display buttonCancel");

				isdisplay(tagautomation.buttonUpdate, "Able to display buttonUpdate", "Unable to display buttonUpdate");

				test.log(LogStatus.INFO, "When the user click Edit icon on Tag automation page ");

				sequencepage.isDisabledWithLocator(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display  read only fields like Sequence..",
						"Unable to display  read only fields like Sequence.");

				sequencepage.isDisabledWithLocator(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_3", xlsname),
						"Able to display read only fields like Contact type dropdowns..",
						"Unable to display  read only fields like Contact type dropdowns..");

				sequencepage.isDisabledWithLocator(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to display  read only fields like Tags.",
						"Unable to display  read only fields like Tags.");

				click(tagautomation.buttonCancel);

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_verifyUpdateButton_TC_44_TC_45() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_15", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_16", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display Add Tag Automation button at the top right side of the page.");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "SequenceName_2", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				isdisplay(tagautomation.textSearch,
						"Able to display the Search text field before the Add Tag Automations button",
						"Unable to display the Search text field before the Add Tag Automations button");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO,
						"When the user enters the text into respective fields and click on Update button  and When the user without enters the text into respective fields and click on Update button ");
				waitforelement(shortwaitvalue);
				click(tagautomation.iconedit);
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				isdisplay(tagautomation.titleEditSequenceLauncher,
						"Able to  display title of the Header name as Edit Tag Automation",
						"Unable to  display title of the Header name as Edit Tag Automation");

				waitforelement(shortwaitvalue);

				String text = driver.findElement(tagautomation.textSequenceLauncher).getAttribute("value");
				clear(tagautomation.textSequenceLauncher, "Able to clear the textSequenceLauncher",
						"Unable to clear the textSequenceLauncher");
				sendkeys(tagautomation.textSequenceLauncher, text, "Able to text in sequenceLauncher",
						"Unable to enter the text");
				waitforelement(shortwaitvalue);

				click(tagautomation.buttonUpdate);
				waitforelement(shortwaitvalue);
				GPS_SequencePage sequencepage = new GPS_SequencePage();
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("TagAutomation", "ToastMessage_3", xlsname),
						"Able to get Sucesfull message as"
								+ excelutil.getData("TagAutomation", "ToastMessage_3", xlsname),
						"Unable to get sucesfull message as"
								+ excelutil.getData("TagAutomation", "ToastMessage_3", xlsname));

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyCloseButton_TC_41() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_16", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_17", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				test.log(LogStatus.INFO, "When the User enters text into respective fields and click on Close button");

				sendkeys(tagautomation.textSequenceLauncher, excelutil.getData("TagAutomation", "Validchar_1", xlsname),
						"Able to display the to enter Alphanumeric characters and special Characters data",
						"Unable to display the enter Alphanumeric characters and special Characters data");

				click(tagautomation.dropdownSequence);
				waitforelement(shortwaitvalue);
//Testdata1
				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to send sequenceSearch Details ", "Unable to sequenceSearch Details");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display dropdown elements all the Sequence list created in the Sequence module.",
						"Unable to display dropdown elements all the Sequence list created in the Sequence module. ");
				waitforelement(shortwaitvalue);
				click(tagautomation.textSelectDateAndTime, "Able to click on SelectDateAndTime",
						"Unable to click on SelectDateand Time");
				waitforelement(shortwaitvalue);
				click(tagautomation.iconcorrect);

				click(tagautomation.dropdownSelectAContactType, "Able to click on SelectAContactType Sucesfully",
						"Unable to click on SelectAContactType");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to click on Dropdown sucesfully", "Unable to click on dropdown");

				click(tagautomation.dropdownSelectaTag, "Able to click on dropdownSelectATag sucesfully",
						"Unable to click on dropdownSelectATag");

				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to able to enter the tag", "Unable to able to enter the tag");
				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to click on Dropdown sucesfully", "Unable to click on dropdown");

				click(tagautomation.buttonCancel);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				waitforelement(shortwaitvalue);

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "Validchar_1", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");

				isdisplay(tagautomation.errorNoMatchFound,
						"Able to navigate back to the tag automation listing page  and the entered data should be cleared.",
						"Unable to navigate back to the tag automation listing page  and the entered data should be cleared.");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	/**
	 * 
	 */
	@Test
	public void GPS_VerifyScheduleDateColumn_TC_30_31_11_12() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_17", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_18", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				click(tagautomation.buttonAddSequenceLauncher, "Able to click on AddSequenceLauncher Sucesfully",
						"Unabel to click on AddSequenceLauncer");

				sendkeys(tagautomation.textSequenceLauncher, excelutil.getData("TagAutomation", "Validchar_3", xlsname),
						"Able to display the to enter Alphanumeric characters and special Characters data",
						"Unable to display the enter Alphanumeric characters and special Characters data");

				click(tagautomation.dropdownSequence);
				waitforelement(shortwaitvalue);

				// Testdata1

				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to send sequenceSearch Details ", "Unable to sequenceSearch Details");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "Testdata1", xlsname),
						"Able to display dropdown elements all the Sequence list created in the Sequence module.",
						"Unable to display dropdown elements all the Sequence list created in the Sequence module. ");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Verify the schedule date");

				click(tagautomation.dropdownSelectAContactType, "Able to click on SelectAContactType Sucesfully",
						"Unable to click on SelectAContactType");

				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to click on Dropdown sucesfully", "Unable to click on dropdown");

				click(tagautomation.dropdownSelectaTag, "Able to click on dropdownSelectATag sucesfully",
						"Unable to click on dropdownSelectATag");

				sendkeys(tagautomation.textSearchsequence, excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to able to enter the tag", "Unable to able to enter the tag");
				sequencepage.elementClick(excelutil.getData("TagAutomation", "Tag_10", xlsname),
						excelutil.getData("TagAutomation", "DropDown_2", xlsname),
						"Able to click on Dropdown sucesfully", "Unable to click on dropdown");

				click(tagautomation.textSelectDateAndTime);

				GPS_CreateOpportunityPage createopportunitypage = new GPS_CreateOpportunityPage();
				createopportunitypage.calendarDateSelection(createopportunitypage.calendarYearValue,
						createopportunitypage.calendarMonthValue, createopportunitypage.calendarRightChevron,
						excelutil.getData("TagAutomation", "TaskYearValue", xlsname),
						excelutil.getData("TagAutomation", "TaskMonthValue", xlsname),
						excelutil.getData("TagAutomation", "TaskDayValue", xlsname),
						"User able to select given date from the Calendar.",
						"User unable to select given date from the Calendar.");
				click(tagautomation.iconcorrect);
				waitforelement(shortwaitvalue);

				String value1 = driver.findElement(tagautomation.textSequenceDate).getAttribute("value");

				logger.info(value1);
				tagautomation.dateFormat(driver.findElement(tagautomation.textSequenceDate),
						excelutil.getData("TagAutomation", "dateFormat", xlsname), value1,
						"Able to  display a date picker calendar which should allow user to select Date & Time interval  Am/Pm",
						"Unable to  display a date picker calendar which should allow user to select Date & Time interval  Am/Pm");

				test.log(LogStatus.INFO,
						"When the user without selecting scheduled date and click on outside of the field.");

				click(tagautomation.textSelectDateAndTime);
				waitforelement(shortwaitvalue);
				click(tagautomation.buttonClear);
				waitforelement(shortwaitvalue);
				click(tagautomation.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("TagAutomation", "ToastMessage_1", xlsname),
						"Able to  not to  display any validation message.",
						"Unable to  not display any validation message.");

				test.log(LogStatus.INFO,
						"When the user schedule date field was not selected on the add tag automation popup.");
				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "Validchar_3", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Dropdown_8", xlsname),
						"Able to display starts immediately text under schedule column",
						"Unable to display starts immediately text under schedule column");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				click(tagautomation.iconDelete);
				waitforelement(shortwaitvalue);
				sendkeys(tagautomation.textDelete, excelutil.getData("TagAutomation", "Tag_7", xlsname),
						"Able to enter the invalid text", "Unable to enter the invalid text");
				waitforelement(shortwaitvalue);
				click(tagautomation.buttonSubmit);
				waitforelement(shortwaitvalue);
				// Tag_6

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("TagAutomation", "Tag_6", xlsname),
						"Able to display deleted message sucesfuly", "Unable to display deleted message sucesfuly");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyPagination_TC_08_09_10() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_17", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_18", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the Tag Automations records in \"Table\" format are more.");

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();

				GPS_SequencePage sequencepage = new GPS_SequencePage();
				tagautomation.ListSizeOfElement(10, tagautomation.iconedit,
						"Able to  display Pagination to follow in table view with 10 records on each screen.",
						"Unable to  display Pagination to follow in table view with 10 records on each screen.");

				isdisplay(tagautomation.iconArrowForward,
						"Able to display Next arrow  at the bottom right corner of the page.",
						"Unable to display Next arrow  at the bottom right corner of the page.");

				isdisplay(tagautomation.iconArrowBackward,
						"Able to display Previous arrow buttons at the bottom right corner of the page.",
						"Unable to display Previous arrow buttons at the bottom right corner of the page.");
				test.log(LogStatus.INFO, "When the Current Tag Automations record page number ");

				sequencepage.elementColor(excelutil.getData("TagAutomation", "Elementname_1", xlsname),
						excelutil.getData("TagAutomation", "Backgroundcolor", xlsname),
						excelutil.getData("TagAutomation", "Pagination", xlsname),
						"Able to display \"Current \"page number highlighted with \"Blue color\"",
						"Unable to display \"Current \"page number highlighted with \"Blue color\"");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(mediumwaitvalue);
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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_verifyplayAndPauseButton_TC_13_14_15_16() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("TagAutomation", "HeaderName_18", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "TagAutomation_19", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				homepage.chooseOrganzation1(excelutil.getData("TagAutomation", "Organization Name", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "When the user Click on any agency card on the landing page");
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink1", xlsname),
						"User able to click on Sequences module.", "User unable to click on Sequences module.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "3. When the user Click on Automation menu on the LHS of the page");

				orgdashboardpage.chooseModuleName(excelutil.getData("TagAutomation", "SideLink2", xlsname),
						"Able to display Sequence Launcher", "Unable to display Sequence Launcher");

				waitforelement(shortwaitvalue);
				;
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				isdisplay(tagautomation.buttonAddSequence,
						"Able to display Add Tag Automation button at the top right side of the page.",
						"Unable to display  Tag Automation button at the top right side of the page.");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "SequenceName_2", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				isdisplay(tagautomation.textSearch,
						"Able to display the Search text field before the Add Tag Automations button",
						"Unable to display the Search text field before the Add Tag Automations button");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "When the user click Play Button on Tag Automation page ");

				click(tagautomation.iconPlayButton, "Able to click on Playbutton", "Unable to click on PlayButton");
				waitforelement(shortwaitvalue);
				GPS_SequencePage sequencepage = new GPS_SequencePage();

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						"Tag Automation process started successfully.", "Able to display deleted message sucesfuly",
						"Unable to display deleted message sucesfuly");

				waitforelement(mediumwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_4", xlsname),
						"Able to  display Status column in" + excelutil.getData("TagAutomation", "Status_4", xlsname)
								+ "against this Tag Automation",
						"Unable to display Status column in" + excelutil.getData("TagAutomation", "Status_4", xlsname)
								+ "against this Tag Automation");

				test.log(LogStatus.INFO, "When the user click Pause Button on Tag Automation page ");

				click(tagautomation.iconPauseButton, "Able to click on Pause Button",
						"Unable to click on pause Button");
				waitforelement(mediumwaitvalue);//Status_5

				sequencepage.toastMessage(sequencepage.errortoastmessage, excelutil.getData("TagAutomation", "Status_5", xlsname),
						"Able to display Status column in "+excelutil.getData("TagAutomation", "Status_3", xlsname)+"against this Tag Automation", "Unable to display"+excelutil.getData("TagAutomation", "Status_3", xlsname)+"against this Tag Automation");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_3", xlsname),
						"Able to  display Status column in" + excelutil.getData("TagAutomation", "Status_3", xlsname)
								+ "against this Tag Automation",
						"Unable to  display Status column in" + excelutil.getData("TagAutomation", "Status_3", xlsname)
								+ "against this Tag Automation");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				test.log(LogStatus.INFO, "When the user click Restart button on Tag Automation page ");
				
				clear(tagautomation.textSearch, "Able to clear the text", "Unable to clear the text");

				sendkeys(tagautomation.textSearch, excelutil.getData("TagAutomation", "SequenceName_5", xlsname),
						"Able to enter text into search text box", "Unable to enter text into search text box ");
				//finished state
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_2", xlsname),
						"Able to  display Status column in" + excelutil.getData("TagAutomation", "Status_2", xlsname)
								+ "against this Tag Automation",
						"Unable to  display Status column in" + excelutil.getData("TagAutomation", "Status_2", xlsname)
								+ "against this Tag Automation");
				waitforelement(shortwaitvalue);
				
				click(tagautomation.iconRestartButton, "Able to click on Restart Button",
						"Unable to click on Restart Button");
				waitforelement(mediumwaitvalue);//Status_6
				sequencepage.toastMessage(sequencepage.errortoastmessage, excelutil.getData("TagAutomation", "Status_6", xlsname),
						"Able to display deleted message sucesfuly", "Unable to display deleted message sucesfuly");
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_1", xlsname),
						"Able to  display Status column in" + excelutil.getData("TagAutomation", "Status_1", xlsname)
								+ "against this Tag Automation",
						"Unable to  display Status column in" + excelutil.getData("TagAutomation", "Status_1", xlsname)
								+ "against this Tag Automation");

				click(tagautomation.iconPauseButton, "Able to click on Paused Button",
						"Unable to click on Pause Button");
				waitforelement(mediumwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("TagAutomation", "Element_3", xlsname),
						excelutil.getData("TagAutomation", "Status_3", xlsname),
						"Able to  display Status column in" + excelutil.getData("TagAutomation", "Status_3", xlsname)
								+ "against this Tag Automation",
						"Unable to  display Status column in" + excelutil.getData("TagAutomation", "Status_3", xlsname)
								+ "against this Tag Automation");
				click(tagautomation.iconPlayButton);
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

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

			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

}