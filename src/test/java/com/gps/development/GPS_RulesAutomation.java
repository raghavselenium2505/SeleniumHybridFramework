package com.gps.development;

import java.util.UUID;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.GPS_HomePage;
import com.gps.pages.GPS_LoginPage;
import com.gps.pages.GPS_OrgDashboardPage;
import com.gps.pages.GPS_RulesPage;
import com.gps.pages.GPS_SequencePage;
import com.gps.pages.GPS_TagAutomationPage;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

public class GPS_RulesAutomation extends TestBase {

	@Test
	public void GPS_VerifyAutomationLHS() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName", xlsname));
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

	@Test
	public void GPS_VerifyTheRulesModule() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_1", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_2", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "3. Click on Rules");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");

				GPS_RulesPage rulespage = new GPS_RulesPage();

				isdisplay(rulespage.titleRules, "Able to display header as Rules.",
						"Unable to display header as Rules.");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "option1", xlsname), "Able to display 'status' dropdown",
						"Unable to display 'status' dropdown");

				isdisplay(rulespage.buttonAddNewFolder, "Able to display AddNewFolder Button.",
						"Unable to display AddNewFolder Button.");
				isdisplay(rulespage.buttonAddNewRule, "Able to display AddNewRule Button.",
						"Unable to display AddNewRule button.");

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
	public void GPS_VerifyRulesPage() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_2", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_3", xlsname).equalsIgnoreCase("N")) {

				test.log(LogStatus.INFO, "User should be able to login into GPS Successfully.");

				loginpage.login(config.getProperty("userName"), config.getProperty("password"));

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				GPS_HomePage homepage = new GPS_HomePage();

				waitforelement(longwaitvalue);
				test.log(LogStatus.INFO, "Click on any Organization.");

				homepage.chooseOrganzation1(excelutil.getData("Rules", "Organization Name_1", xlsname),
						"User able to select Organization.", "User unable to select Organization.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				GPS_OrgDashboardPage orgdashboardpage = new GPS_OrgDashboardPage();
				GPS_SequencePage sequencepage = new GPS_SequencePage();
				test.log(LogStatus.INFO, "3. Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "4. Click on Rules");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_2", xlsname),
						excelutil.getData("Rules", "noitemsDisplay", xlsname),
						"Able to display" + excelutil.getData("Rules", "noitemsDisplay", xlsname)
								+ "as a message when no rules were added by admin",
						"Unable to display" + excelutil.getData("Rules", "noitemsDisplay", xlsname)
								+ "as a message when no rules were added by admin");

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
	public void GPS_VerifyRulesPageWithOrganization() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_3", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_4", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "4. Click on Rules");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "RuleName", xlsname),
						"Able to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin",
						"Unable to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin");

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

//4,5
	@Test
	public void GPS_VerifyAddNewFolderFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_4", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_5", xlsname).equalsIgnoreCase("N")) {

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
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "4. Click on Rules");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "RuleName", xlsname),
						"Able to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin",
						"Unable to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin");

				test.log(LogStatus.INFO, "5. Click on Add New Folder Button");

				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewFolder, "Able to click on AddNewFolder", "Unable to click on AddNewFolder");
				waitforelement(shortwaitvalue);

				isdisplay(rules.titleAddNewFolder, "Able to open a pop up with header as 'Add New Folder'",
						"Unable to open a pop up with header as 'Add New Folder'");
				waitforelement(shortwaitvalue);
				isdisplay(rules.labelFolderName, "Able to  display label as Folder name with a text box.",
						"Unable to display label as Folder name with a text box.");
				waitforelement(shortwaitvalue);
				isdisplay(rules.buttonSave, "Able to  display buttonSave", "Unable to display buttonSave");
				waitforelement(shortwaitvalue);
				isdisplay(rules.buttonClose, "Able to  display buttonClose", "Unable to display buttonClose");
				test.log(LogStatus.INFO, "Application will display a pop up");
				click(rules.buttonSave);
				waitforelement(shortwaitvalue);
				isdisplay(rules.errorFolderName, "Able to mark the field as mandatory",
						"Unable to mark the field as mandatory");

				sendkeys(rules.inputfolderName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				clear(rules.inputfolderName, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");

				sendkeys(rules.inputfolderName, excelutil.getData("Rules", "characters", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				sequencepage.verifyWordsAbove40Characters(100, driver.findElement(rules.inputfolderName),
						"Maximum character can be entered is 100 characters only", "Maximum characters exceeded");

				test.log(LogStatus.INFO, "6. Enter Name and Click on Save");

				clear(rules.inputfolderName, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");
				waitforelement(shortwaitvalue);

				sendkeys(rules.inputfolderName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				click(rules.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "textSms", xlsname),
						"Able to save the folder as" + excelutil.getData("Rules", "textSms", xlsname)
								+ "and displayed in the folder listed page",
						"Unable to save folder as" + excelutil.getData("Rules", "textSms", xlsname)
								+ "and displayed in the folder listed page");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				waitforelement(shortwaitvalue);
				rules.ListofElement(rules.iconArrow, "Able to display an arrow as prefix for every folder");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	// 5,6
	@Test
	public void GPS_VerifyTriangleFolderIcon() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_5", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_6", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "4. Click on Rules");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "RuleName", xlsname),
						"Able to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin",
						"Unable to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin");

				test.log(LogStatus.INFO, "When the user click  three dots as a prefix for every folder ");
				GPS_RulesPage rules = new GPS_RulesPage();
				waitforelement(shortwaitvalue);
				// elementname
				// Field_1

				rules.ListofElement(rules.iconthreedots, "Able to display three dots as a prefix for every folder  ");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Field_1", xlsname),
						"Able to  display Copy Rule option under three dots for every folder",
						"Unable to display the copy rule option under three dobts for every folder");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

//6,7
	@Test
	public void GPS_VerifyCopyRuleButtonFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_6", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_7", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "4. Click on Rules and application will display listing page.");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on three dots and application will display copy rule option");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "RuleName", xlsname),
						"Able to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin",
						"Unable to display" + excelutil.getData("Rules", "RuleName", xlsname)
								+ "as a message when no rules were added by admin");

				GPS_RulesPage rules = new GPS_RulesPage();
				sequencepage.ListofElement(rules.iconthreedots,
						"Able to display three dots as a prefix for every folder  ");
				waitforelement(shortwaitvalue);
				rules.elementTextClickForThreedots(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "RuleName", xlsname), "Able to click on 3 dots ",
						"Unable to click on 3 dots");

				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "6. Click on Copy rule option");
				// test.log(LogStatus.INFO, "6. Click on Copy rule option");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Field_1", xlsname), "Able to click on copy rule sucesfully",
						"Unable to click on copy rule sucesfully");
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_2", xlsname),
						excelutil.getData("Rules", "Field_1", xlsname),
						"Able to display  pop up with a header as Copy Rule",
						"Unable to display pop up with a header as Copy Rule");
				waitforelement(shortwaitvalue);

				sequencepage.getText(
						excelutil.getData("Rules", "RuleName", xlsname)
								+ excelutil.getData("Rules", "Field_2", xlsname),
						driver.findElement(rules.textNewRuleName), "Able to display the rule name with Copy as Sufix.",
						"Unable to display the rule name with Copy as Sufix.");
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_4", xlsname),
						excelutil.getData("Rules", "textHeader", xlsname), "Able to display  Destination organization ",
						"Unable to display Destination organization");

				click(rules.dropDownSelectOrg);
				waitforelement(shortwaitvalue);
				// Organization Name_1

				sendkeys(rules.textOrganzation, excelutil.getData("Rules", "Organization Name_1", xlsname),
						"Able to enter the details", "Unable to enter the details");
				sequencepage.elementClickWithArrow(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Organization Name_1", xlsname),
						"Able to display all the organizations in the dropdown. ",
						"Unable to display all the organizations in the dropdown.");
				waitforelement(shortwaitvalue);
				click(rules.textNewRuleName);
				isdisplay(rules.buttonCancel, "Able to display Cancel Button", "Unable to display Cancel Button");

				waitforelement(shortwaitvalue);
				isdisplay(rules.buttonCopy, "Able to display Copy Button", "Unable to display Copy Button");
				waitforelement(shortwaitvalue);

				click(rules.buttonCancel);

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	// 7,8

	@Test
	public void GPS_VerifyNewRuleFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_7", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_8", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");

				isdisplay(rules.titleAddNewRule, "Able to  open a pop up with header as 'Add New Rule'",
						"Unable to  open a pop up with header as 'Add New Rule'");

				isdisplay(rules.labelRuleName, "Able to  display a text box with a label rule name.",
						"Unable to  display a text box with a label rule name.");

				isdisplay(rules.dropdownSelectFolder, "Able to display a dropdown with a label 'Select Folder'",
						"Unable to display a dropdown with a label 'Select Folder'");

				isdisplay(rules.buttonClose, "Able to display Close button", "Unable to display Close button");

				isdisplay(rules.buttonSave, "Able to display Save button", "Unable to display Save button");

				test.log(LogStatus.INFO, "Click on Save button without entering rule name");
				click(rules.buttonSave);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "error1", xlsname),
						"Able to display the error message as" + excelutil.getData("Rules", "error1", xlsname),
						"Unable to display the error message as" + excelutil.getData("Rules", "error1", xlsname));

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button and application will display a pop up");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "characters", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				sequencepage.verifyWordsAbove40Characters(40, driver.findElement(rules.textRuleName),
						"Maximum character can be entered is 40 characters only", "Maximum characters exceeded");

				clear(rules.textRuleName, "Able to clear the default text ,it is editable field",
						"Unable to clear the default text ,it is not editable field");
				waitforelement(shortwaitvalue);

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");

				test.log(LogStatus.INFO, "7. Click on Close button or Close icon");

				click(rules.buttonClose, "Able to click on Close Button ", "Unable to click on Close Button");

				waitforelement(shortwaitvalue);

				click(rules.buttonAddNewRule,
						"Able to  close the pop up and the name which was entered should be cleared, when admin clicks on Add New Rule next time",
						"Unable to  close the pop up and the name which was entered should be cleared, when admin clicks on Add New Rule next time");
				test.log(LogStatus.INFO, "6. Click on the folder dropdown");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				/*
				 * click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
				 * "Unable to click on dropdown FolderName");
				 * 
				 * waitforelement(shortwaitvalue);
				 * 
				 * sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2",
				 * xlsname), "Able to display all the folders created in Rules page.",
				 * "Unable to display all the folders created in Rules page.");
				 * 
				 * sequencepage.elementTextClick(excelutil.getData("Rules", "elementname",
				 * xlsname), excelutil.getData("Rules", "Value_2", xlsname),
				 * "Able to  allow only one folder to select a time",
				 * "Unable to allow only one folder to select a time");
				 */
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyHelpFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_8", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_9", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"6. Enter Rule name and click on Save button and application will navigate the user to add new rule page");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Text_1", xlsname),
						"Able to  display a message stating “This rule will not run until status is changed from Draft to Active.”.",
						"Unable to  display a message stating “This rule will not run until status is changed from Draft to Active.”.");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAddNewRuleFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_9", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_10", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO,
						"6. Enter Rule name and click on Save button and application will navigate the user to add new rule page");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");
				waitforelement(shortwaitvalue);
				/*
				 * sequencepage.elementTextClick(excelutil.getData("Rules", "elementname",
				 * xlsname), excelutil.getData("Rules", "Value_2", xlsname),
				 * "Able to  allow only one folder to select a time",
				 * "Unable to allow only one folder to select a time");
				 */
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				// Elementname_3-h5

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_3", xlsname), "Add New Rule",
						"Able to display header of the page as 'Add New Rule'.",
						"Unable to display header of the page as 'Add New Rule'.");
				// Elementname_4-label
				isdisplay(rules.iconBack, "Able to  display the Back button beside Header.",
						"Unable to display the Back button beside Header.");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_4", xlsname), "Rule Name *",
						"Able to display Rule Name as label with text field",
						"Unable to display Rule Name as label with text field");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_3", xlsname),
						"1. When this happens...", "Able to display 'When this happens...' as title",
						"Unable to display 'When this happens...' as title");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_3", xlsname),
						"2. Perform these actions.",
						"Able to display 'Perform these actions.' Tile with 'Add Action' button.",
						"Unable to display 'Perform these actions.' Tile with 'Add Action' button.");
				// dropdownStatus

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "dropdownStatus", xlsname), "Able to display Status Dropdown",
						"Unable to display Status Dropdown");

				isdisplay(rules.buttonSave, "Able to display Save Dropdown", "Unable to display Save Dropdown");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Text_1", xlsname),
						"Able to  display a message stating “This rule will not run until status is changed from Draft to Active.”.",
						"Unable to  display a message stating “This rule will not run until status is changed from Draft to Active.”.");

				test.log(LogStatus.INFO, "Under “When this happens?  label");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_4", xlsname),
						excelutil.getData("Rules", "elementname_6", xlsname),
						"Able to display 'What condition should cause this automation to run?'",
						"Unable to display 'What condition should cause this automation to run?'");

				test.log(LogStatus.INFO, "Under What condition should cause this automation to run? label drop down");
				// excelutil.getData("Rules", "elementname_4", xlsname)
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to display dropdown placeholder as 'Select an Event'.",
						"Unable to display dropdown placeholder as 'Select an Event'.");

				test.log(LogStatus.INFO, "When the user click outside of the field ");

				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click on SelectAnEvent sucesfull", "Unable to click on SelectAnEvent");

				waitforelement(shortwaitvalue);
				click(rules.textRuleName);
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Validation_1", xlsname),
						"Able to display valdation message as " + excelutil.getData("Rules", "Validation_1", xlsname),
						"Unable to display valdation message as" + excelutil.getData("Rules", "Validation_1", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyBackButtonFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_10", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_11", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO,
						"6. Enter Rule name and click on Save button and application will navigate the user to actions page");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				/*
				 * sequencepage.elementTextClick(excelutil.getData("Rules", "elementname",
				 * xlsname), excelutil.getData("Rules", "Value_2", xlsname),
				 * "Able to  allow only one folder to select a time",
				 * "Unable to allow only one folder to select a time");
				 */
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "Click on Back Icon");

				actionclick(driver.findElement(rules.iconBack), "Able to click on Back Button",
						"Unable to click on Back Button");

				isdisplay(rules.buttonYes, "Able to display Are you sure pop up with Yes ",
						"Unable to display Are you sure pop up with Yes");

				isdisplay(rules.buttonNo, "Able to display Are you sure pop up with No ",
						"Unable to display Are you sure pop up with No");
				click(rules.buttonYes);
				waitforelement(shortwaitvalue);

				// Rules
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_8", xlsname),
						excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to navigate the user to Rules listing page when clicked on Yes",
						"Unable to navigate the user to Rules listing page when clicked on Yes");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyBackButtonFunctionality_1() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_11", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_12", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO,
						"6. Enter Rule name and click on Save button and application will navigate the user to actions page");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				/*
				 * sequencepage.elementTextClick(excelutil.getData("Rules", "elementname",
				 * xlsname), excelutil.getData("Rules", "Value_2", xlsname),
				 * "Able to  allow only one folder to select a time",
				 * "Unable to allow only one folder to select a time");
				 */
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Click on Back Icon");

				actionclick(driver.findElement(rules.iconBack), "Able to click on Back Button",
						"Unable to click on Back Button");

				isdisplay(rules.buttonYes, "Able to display Are you sure pop up with Yes ",
						"Unable to display Are you sure pop up with Yes");

				isdisplay(rules.buttonNo, "Able to display Are you sure pop up with No ",
						"Unable to display Are you sure pop up with No");
				click(rules.buttonNo);
				waitforelement(shortwaitvalue);

				// Rules
				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "error2", xlsname),
						"Able to navigate the user to Rules listing page when clicked on Yes",
						"Unable to navigate the user to Rules listing page when clicked on Yes");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRuleTileFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_12", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_13", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "6. Navigate to What should rule this rule? tile");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "Select any of the event in the drodown");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Customfields", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Customfields", xlsname),
						"Able to  display Custom fields under custom fiilters which are there in the organization",
						"Unable to   display Custom fields under custom fiilters which are there in the organization");
				test.log(LogStatus.INFO, "Select Add Sequence as an event and click on Add Condition");

				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to enter the value in sequence", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to display  on the dropdown define conditions",
						"Unable to display on the dropdown define conditions");

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_14", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_14", xlsname),
						"Able to display  on the dropdown define conditions",
						"Unable to display on the dropdown define conditions");

				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_15", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_15", xlsname),
						"Able to display  on the dropdown define conditions",
						"Unable to display on the dropdown define conditions");

				waitforelement(shortwaitvalue);

				// 3
				test.log(LogStatus.INFO, "Select In Campaign as a condition");

				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_16", xlsname),
						"Able to click  on the sequence dropdown", "Unable to click on the sequence dropdown");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_17", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_17", xlsname),
						"Able to  display all sequences in the respective agency.",
						"Unable to  display all sequences in the respective agency.");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_17", xlsname),
						"Able to allow admin to select only one condition at a time",
						"Unable to allow admin to select only one condition at a time");
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "9.  Select Does'nt have Tags as a condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

//				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_15", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_15", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_18", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
//elementname_19

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to  display all Tags in the respective agency",
						"Unble to  display all Tags in the respective agency");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to  allow admin to select only one condition at a time",
						"Unable to  allow admin to select only one condition at a time");

				// Has Tag

				test.log(LogStatus.INFO, "9. Select has tag as a condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_14", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_14", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_18", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to  display all Tags in the respective agency",
						"Unble to  display all Tags in the respective agency");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to  allow admin to select only one condition at a time",
						"Unable to  allow admin to select only one condition at a time");

				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

				test.log(LogStatus.INFO, "Click on Delete button");

				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAddAppointmenteventConditionFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_13", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_14", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "6. Navigate to What should rule this rule? tile");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_4", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_4", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_4", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_4", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_4", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_4", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				// case 1

				test.log(LogStatus.INFO, "Select Add Appointment as an event and click on Add Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");

//				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_22", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_22", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_22", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency.");

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_23", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_23", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_23", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_23", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency");

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_24", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_24", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_24", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_24", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency.");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_24", xlsname),
						"Able to  allow admin to select only one condition at a time",
						"Unable to  allow admin to select only one condition at a time");

				// case 2

				test.log(LogStatus.INFO, "9. Select Appointment status is' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");

//				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				// elementname_23
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_23", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_23", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_23", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_23", xlsname),
						"Able to click on" + excelutil.getData("Rules", "elementname_23", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_23", xlsname));

				// excelutil.getData("Rules", "elementname_23", xlsname)
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_29", xlsname),
						"Able to click  on " + excelutil.getData("Rules", "elementname_29", xlsname),
						"Unable to click on " + excelutil.getData("Rules", "elementname_29", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_30", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_30", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_30", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_30", xlsname),
						"Able to display the Status" + excelutil.getData("Rules", "elementname_30", xlsname),
						"Unable to display the status" + excelutil.getData("Rules", "elementname_30", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_31", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_31", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_31", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_31", xlsname),
						"Able to display the status" + excelutil.getData("Rules", "elementname_31", xlsname),
						"Unable to display the status" + excelutil.getData("Rules", "elementname_31", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_32", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_32", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_32", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_32", xlsname),
						"Able to display the status" + excelutil.getData("Rules", "elementname_32", xlsname),
						"Unable to display the status" + excelutil.getData("Rules", "elementname_32", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_33", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_33", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_33", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_33", xlsname),
						"Able to display on " + excelutil.getData("Rules", "elementname_33", xlsname),
						"Unable to display on " + excelutil.getData("Rules", "elementname_33", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_34", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_34", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_34", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_34", xlsname),
						"Able to display on " + excelutil.getData("Rules", "elementname_34", xlsname),
						"Unable to display on " + excelutil.getData("Rules", "elementname_34", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_34", xlsname),
						"Able to click  on " + excelutil.getData("Rules", "elementname_34", xlsname),
						"Unable to click on " + excelutil.getData("Rules", "elementname_34", xlsname));

				// case 2

				test.log(LogStatus.INFO, " Select 'Has Tag' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

//				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_24", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_24", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_24", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_24", xlsname),
						"Able to  display another drodown with 'Select Condition' as place holder",
						"Unable to  display another drodown with 'Select Condition' as place holder");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_26", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_25", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_25", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_25", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_25", xlsname),
						"Able to display all Tags in the respective agency.",
						"Unable to display all Tags in the respective agency.");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_25", xlsname),
						"Able to allow admin to select only one condition at a time",
						"Unable to allow admin to select only one condition at a time");

				// case3

				test.log(LogStatus.INFO, "Select In Calendar as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

//				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_22", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_22", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_22", xlsname));
				waitforelement(shortwaitvalue);
				// elementname_22
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_22", xlsname),
						"Able to  click on " + excelutil.getData("Rules", "elementname_22", xlsname),
						"Unable to  click on " + excelutil.getData("Rules", "elementname_22", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_28", xlsname),
						"Able to display another drodown with 'Select Condition' as place holder",
						"Unable to display another drodown with 'Select Condition' as place holder");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_27", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_27", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_27", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_27", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency.");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_27", xlsname),
						"Able to allow admin to select only one condition at a time",
						"Unable to allow admin to select only one condition at a time");

				// case4

				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Select condition",
						"Unable to click on Select condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_22", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_22", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_22", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

				test.log(LogStatus.INFO, "Click on Delete button");

				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyEmailOrSMSConditionFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_14", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_15", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "6. Navigate to What should rule this rule? tile");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_5", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_5", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_5", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_5", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_5", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_5", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				// case 2

				test.log(LogStatus.INFO, "8. Select 'Email/SMS notification' as an event and click on Add Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to display on Select  condition",
						"Unable to display on Select condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_13", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_13", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency.");

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_35", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_35", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_35", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_35", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency");

				// Case 3

				test.log(LogStatus.INFO, "Select In Sequence as a condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to display on Select  condition",
						"Unable to display on Select condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_13", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_13", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_13", xlsname),
						"Able to display " + excelutil.getData("Rules", "elementname_13", xlsname),
						"Unable to display another drodown with 'Select Condition' as place holder");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_36", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_36", xlsname),
						"Unable to click on " + excelutil.getData("Rules", "elementname_36", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_17", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_17", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_17", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_17", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency");

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_17", xlsname),
						"Able to display all Calendars in the respective agency.",
						"Unable to display all Calendars in the respective agency");

				// 3

				test.log(LogStatus.INFO, "9. Select 'Event' as a condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to display on Select  condition",
						"Unable to display on Select condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_35", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_35", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_35", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_35", xlsname),
						"Able to display " + excelutil.getData("Rules", "elementname_35", xlsname),
						"Unable to display another drodown with 'Select Condition' as place holder");
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_42", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_42", xlsname),
						"Unable to click on " + excelutil.getData("Rules", "elementname_42", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_37", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_37", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_37", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_37", xlsname),
						"Able to  display dropdown value" + excelutil.getData("Rules", "elementname_37", xlsname),
						"Unable to  display dropdown value" + excelutil.getData("Rules", "elementname_37", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_38", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_38", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_38", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_38", xlsname),
						"Able to  display dropdown value" + excelutil.getData("Rules", "elementname_38", xlsname),
						"Unable to  display dropdown value" + excelutil.getData("Rules", "elementname_38", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_39", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_39", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_39", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_39", xlsname),
						"Able to  display dropdown value" + excelutil.getData("Rules", "elementname_39", xlsname),
						"Unable to  display dropdown value" + excelutil.getData("Rules", "elementname_39", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_40", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_40", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_40", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_40", xlsname),
						"Able to  display dropdown value" + excelutil.getData("Rules", "elementname_40", xlsname),
						"Unable to  display dropdown value" + excelutil.getData("Rules", "elementname_40", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_41", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_41", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_41", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_41", xlsname),
						"Able to  display dropdown value" + excelutil.getData("Rules", "elementname_41", xlsname),
						"Unable to  display dropdown value" + excelutil.getData("Rules", "elementname_41", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_41", xlsname),
						"Able to  display dropdown value" + excelutil.getData("Rules", "elementname_41", xlsname),
						"Unable to  display dropdown value" + excelutil.getData("Rules", "elementname_41", xlsname));

//Case 4				  
				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add  condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click on Select Condition condition", "Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_35", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

				test.log(LogStatus.INFO, "Click on Delete button");

				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyContactDNDEventConditionsFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_15", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_16", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select 'Event' as a condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_6", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_6", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_6", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_6", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_6", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_6", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_44", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_44", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_44", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_44", xlsname),
						"Able to click on the status" + excelutil.getData("Rules", "elementname_44", xlsname),
						"Unable to click on Status" + excelutil.getData("Rules", "elementname_44", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_29", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_45", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_45", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_45", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_45", xlsname),
						"Able to display the list of DND flags of the agency"
								+ excelutil.getData("Rules", "elementname_46", xlsname),
						"Unable to display the list of DND flags of the agency"
								+ excelutil.getData("Rules", "elementname_46", xlsname));
				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the existing value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_46", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_46", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_46", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_46", xlsname),
						"Able to display the list of DND flags of the agency"
								+ excelutil.getData("Rules", "elementname_46", xlsname),
						"Unable to display the list of DND flags of the agency"
								+ excelutil.getData("Rules", "elementname_46", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_46", xlsname),
						"Able to  allow admin to select only one condition at a time"
								+ excelutil.getData("Rules", "elementname_46", xlsname),
						"Unable to  allow admin to select only one condition at a time"
								+ excelutil.getData("Rules", "elementname_46", xlsname));

				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add  condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click on Select Condition condition", "Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_44", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

				test.log(LogStatus.INFO, "Click on Delete button");

				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyCustomerReplyEventFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_16", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_17", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select Customer Replied as an event and click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_7", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_7", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_7", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_7", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_7", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_7", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_47", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_47", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_47", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_47", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_47", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_47", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_48", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_48", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_48", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_48", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_48", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_48", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_49", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_49", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_49", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_49", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_49", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_49", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_50", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_50", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_50", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_50", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_50", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_50", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_51", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_51", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_51", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_51", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_51", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_51", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_52", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_53", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_53", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_53", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_53", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_53", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_53", xlsname));

				test.log(LogStatus.INFO, "10. Select 'Reply Channel' as condition in the event");
				clear(rules.textFolderName, "Able to clear the existing values", "Unable to clear the existing values");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_48", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_48", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_48", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_48", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_48", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_48", xlsname));

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_54", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_54", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_54", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_55", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_55", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_55", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_55", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_55", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_55", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_56", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_56", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_56", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_56", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_56", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_56", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_57", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_57", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_57", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_57", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_57", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_57", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_58", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_58", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_58", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_58", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_58", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_58", xlsname));

				clear(rules.textFolderName, "Able to clear the value", "Unable to clear the value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_59", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_59", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_59", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_59", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_59", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_59", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_59", xlsname),
						"Able to  allow admin to select only one condition at a time"
								+ excelutil.getData("Rules", "elementname_59", xlsname),
						"Unable to  allow admin to select only one condition at a time"
								+ excelutil.getData("Rules", "elementname_59", xlsname));

				test.log(LogStatus.INFO, "10. Select 'Contains Phrase' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_49", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_49", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_49", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_49", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_49", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_49", xlsname));

				// waitforelement(extraverylongwaitvalue);
				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				// elementname_60
				tagautomation.Placeholder(excelutil.getData("Rules", "elementname_60", xlsname),
						driver.findElement(rules.textEnterPhase), "Able to get the text field with placeholder as",
						"Unable to get the text field with placeholder as");

				sendkeys(rules.textEnterPhase, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow admin to enter characters in the field",
						"Unable to allow admin to enter characters in the field");

				// 10. Select 'Exact match phrase' as condition in the event

				test.log(LogStatus.INFO, "Select 'Exact match phrase' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_50", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_50", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_50", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_50", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_50", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_50", xlsname));

				// waitforelement(extraverylongwaitvalue);
				waitforelement(shortwaitvalue);

				tagautomation.Placeholder(excelutil.getData("Rules", "elementname_60", xlsname),
						driver.findElement(rules.textEnterPhase), "Able to get the text field with placeholder as",
						"Unable to get the text field with placeholder as");

				sendkeys(rules.textEnterPhase_1, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow admin to enter characters in the field",
						"Unable to allow admin to enter characters in the field");

				test.log(LogStatus.INFO, "10. Select 'Intent Type' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_51", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_51", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_51", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_51", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_51", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_51", xlsname));

				// waitforelement(extraverylongwaitvalue);
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_63", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_63", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_63", xlsname));
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_61", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_61", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_61", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_61", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_61", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_61", xlsname));

				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_62", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_62", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_62", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_62", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_62", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_62", xlsname));

				test.log(LogStatus.INFO, "10. Select 'has tag' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_52", xlsname));

				// waitforelement(extraverylongwaitvalue);
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_18", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_18", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_18", xlsname));
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_19", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_19", xlsname));

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_19", xlsname));

				test.log(LogStatus.INFO, "10. Select 'doesn't have tag' as condition in the event");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_53", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_53", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_53", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_53", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_53", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_53", xlsname));

				// waitforelement(extraverylongwaitvalue);
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_18", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_18", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_18", xlsname));
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_19", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_19", xlsname));

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_19", xlsname));

				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add  condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click on Select Condition condition", "Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_50", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

				test.log(LogStatus.INFO, "Click on Delete button");

				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyPipelineStageChangeEventFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_17", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_18", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select 'Pipeline Stage changed' as an event and click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_8", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_8", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_8", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_8", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_8", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_8", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_64", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_64", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_64", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_64", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_64", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_64", xlsname));
				clear(rules.textFolderName, "Able to clear the existing value", "Unable to clear the existing value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_52", xlsname));
				test.log(LogStatus.INFO, "10. Select In Has Tag as a condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_52", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_52", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_52", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_52", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_18", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_18", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_18", xlsname));
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_19", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_19", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to click on the status" + excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to click on Status" + excelutil.getData("Rules", "elementname_19", xlsname));
				test.log(LogStatus.INFO, "10. Select In Pipeline as a condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_12", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_64", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_64", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_64", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_64", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_64", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_64", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_66", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_66", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_66", xlsname));
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_65", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_65", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_65", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_65", xlsname),
						"Able to display on the status" + excelutil.getData("Rules", "elementname_65", xlsname),
						"Unable to display on Status" + excelutil.getData("Rules", "elementname_65", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_65", xlsname),
						"Able to click on the status" + excelutil.getData("Rules", "elementname_65", xlsname),
						"Unable to click on Status" + excelutil.getData("Rules", "elementname_65", xlsname));
				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add  condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click on Select Condition condition", "Unable to click on Add condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_64", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");
				test.log(LogStatus.INFO, "Click on Delete button");
				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyFormSubmittedEventFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_18", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_19", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select 'Pipeline Stage changed' as an event and click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_9", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_9", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_9", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_9", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_9", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_9", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_67", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_67", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_67", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_67", xlsname),
						"Able to display all the forms in the agency"
								+ excelutil.getData("Rules", "elementname_67", xlsname),
						"Unable to display all the forms in the agency"
								+ excelutil.getData("Rules", "elementname_67", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_67", xlsname),
						"Able to  allow admin to select only one condition at a time"
								+ excelutil.getData("Rules", "elementname_67", xlsname),
						"Unable to  allow admin to select only one condition at a time"
								+ excelutil.getData("Rules", "elementname_67", xlsname));
				test.log(LogStatus.INFO, "Select any of the listed conditions and click on Add Condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add  condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click on Select Condition condition", "Unable to click on Add condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_64", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

				test.log(LogStatus.INFO, "Click on Delete button");
				rules.ListofElement(rules.buttonDelete,
						"Able to remove the respective condition and should allow admin to add the conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyFBLeadForms() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_19", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_20", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select 'Pipeline Stage changed' as an event and click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_10", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_10", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_10", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_10", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_10", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_10", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Select  condition",
						"Unable to click on Select condition");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_68", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_68", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_68", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_68", xlsname),
						"Able to display all the forms in the agency"
								+ excelutil.getData("Rules", "elementname_68", xlsname),
						"Unable to display all the forms in the agency"
								+ excelutil.getData("Rules", "elementname_68", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_69", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_69", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_69", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display already selected condition in the dropdown.and  displaying the remaining conditions",
						"Able to display already selected condition in the dropdown.and  displaying the remaining conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyFBLeadForms_1() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_20", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_21", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select 'Pipeline Stage changed' as an event and click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_9", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_9", xlsname));
				waitforelement(shortwaitvalue);
				click(rules.buttonSave);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Validation_1", xlsname),
						"Able to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "Validation_1", xlsname),
						"Unable to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "Validation_1", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_10", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_10", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_10", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_10", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_10", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click the dropdown" + excelutil.getData("Rules", "elementname_10", xlsname));
				click(rules.buttonSave);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_70", xlsname),
						"Able to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "elementname_70", xlsname),
						"Unable to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "elementname_70", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAddActionNegative() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_21", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_22", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "9. Select Add to notes as Action");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				waitforelement(shortwaitvalue);
				click(rules.buttonSave);
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_71", xlsname),
						"Able to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "elementname_71", xlsname),
						"Unable to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "elementname_71", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_AddToSequenceFunctionality() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_22", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_23", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "Enter Notes and Click on Save");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to clicked on Select Actions dropdown",
						"Unable to to clicked on Select Actions dropdown");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_73", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_73", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_73", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_73", xlsname),
						"Able to display all the forms in the agency"
								+ excelutil.getData("Rules", "elementname_73", xlsname),
						"Unable to display all the forms in the agency"
								+ excelutil.getData("Rules", "elementname_73", xlsname));
				click(sequencepage.titleCustomValues, "Able to click on customvalues ",
						"Unable to click on customvalues");

				sequencepage.elementClick(excelutil.getData("Rules", "Tagname_1", xlsname),
						excelutil.getData("Rules", "Submenu_3", xlsname), "Able to clicked on Custom values.",
						"Unable to to clicked on Custom values");

				sequencepage.elementTextClick(excelutil.getData("Rules", "Tagname_1", xlsname),
						excelutil.getData("Rules", "Submenu_6", xlsname),
						"Able to display" + excelutil.getData("Rules", "Submenu_6", xlsname),
						"Unable to to display" + excelutil.getData("Rules", "Submenu_6", xlsname));

				click(rules.buttonSave);
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Validation_1", xlsname),
						"Able to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "Validation_1", xlsname),
						"Unable to display validation when admin clicks on Save without selecting any event as "
								+ excelutil.getData("Rules", "Validation_1", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "elementname_74", xlsname),
						"Able to display Enter Correct Word  toast message",
						"Unable to display Enter Correct Word  toast message");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_AddToSequenceFunctionality_1() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_23", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_24", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				test.log(LogStatus.INFO, "Enter Notes and Click on Save");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_4", xlsname),
						excelutil.getData("Rules", "elementname_77", xlsname),
						"Able to display title as " + excelutil.getData("Rules", "elementname_77", xlsname),
						"Unable to  display title as " + excelutil.getData("Rules", "elementname_77", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_3", xlsname),
						excelutil.getData("Rules", "elementname_78", xlsname),
						"Able to display title as" + excelutil.getData("Rules", "elementname_78", xlsname),
						"Unable to  display title as  " + excelutil.getData("Rules", "elementname_78", xlsname));
				isdisplay(rules.buttonClose_1, "Able to display the close button",
						"Unable to display the close button");
				waitforelement(shortwaitvalue);
				isdisplay(rules.buttonAddActions, "Able to display AddActions below  the second tile ",
						"Unable to display AddActions below  the second tile");
				waitforelement(shortwaitvalue);
				click(rules.buttonSave);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_71", xlsname),
						"Able to display valiation as Select an Action item when admin clicks on save without selecting it"
								+ excelutil.getData("Rules", "elementname_71", xlsname),
						"Unable to display valiation as Select an Action item when admin clicks on save without selecting it"
								+ excelutil.getData("Rules", "elementname_71", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_PerformTheseActions() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_24", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_25", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				test.log(LogStatus.INFO, "Enter Notes and Click on Save");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));

				waitforelement(shortwaitvalue);

				// Add Notes

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_80", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_80", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_80", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_80", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_80", xlsname),
						"Unable to  display the following fields in the dropdown. "
								+ excelutil.getData("Rules", "Elementname_80", xlsname));

				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_81", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_81", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_81", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_81", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_81", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_81", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_82", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_82", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_82", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_82", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_82", xlsname),
						"Unable to  display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_82", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_83", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_83", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_83", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_83", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_83", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_83", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_84", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_84", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_84", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_84", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_84", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_84", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_85", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_85", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_85", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_85", xlsname),
						"Able to display the following fields in the dropdown. "
								+ excelutil.getData("Rules", "Elementname_85", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_85", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_86", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_86", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_86", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_86", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_86", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_86", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_87", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_87", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_87", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_87", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_87", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_87", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_88", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_88", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_88", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_88", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_88", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_88", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_89", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_89", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_89", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_89", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_89", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_89", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_90", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_90", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_90", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_90", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_90", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_90", xlsname));
				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_91", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_91", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_91", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_91", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_91", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_91", xlsname));

				clear(rules.textFolderName, "Able to clear the text FolderName", "Unable to clear the text FolderName");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_92", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_92", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_92", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_92", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_92", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_92", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_AddContactTag() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_25", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_26", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_81", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_81", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_81", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_81", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_81", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_81", xlsname));
				waitforelement(shortwaitvalue);

				//
				test.log(LogStatus.INFO, "10. Click on Save without entering tag");
				click(rules.buttonSave);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_93", xlsname),
						"Able to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_93", xlsname),
						"Unable to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_93", xlsname));

				isdisplay(rules.textTagName, "Able to display text field beow dropdown.",
						"Unable to text field beow dropdown.");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textTagName, excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to allow admin to enter characters in the field.",
						"Unable to allow admin to enter characters in the field.");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to suggest the list of tags when user enters the related alphabets."
								+ excelutil.getData("Rules", "Elementname_19", xlsname),
						"Unable to suggest the list of tags when user enters the related alphabets."
								+ excelutil.getData("Rules", "Elementname_19", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_19", xlsname),
						"Able to add the selected tag below the text field."
								+ excelutil.getData("Rules", "elementname_19", xlsname),
						"Unable to add the selected tag below the text field."
								+ excelutil.getData("Rules", "elementname_19", xlsname));

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				isdisplay(rules.iconClose, "Able to display text field beow dropdown.",
						"Unable to text field beow dropdown.");
				click(rules.iconClose, "Able to display a cross mark beside name of the tag.",
						"Unable to display a cross mark beside name of the tag.");

				String randonValue = excelutil.getData("Rules", "textSms", xlsname)
						+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
				sendkeys(rules.textTagName, randonValue, "Able to allow admin to enter characters in the field.",
						"Unable to allow admin to enter characters in the field.");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname), randonValue,
						"Able to display display'+(name)' at the bottom of the suggestion list" + randonValue,
						"Unable to display display'+(name)' at the bottom of the suggestion list" + randonValue);

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_AssignToUser() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_25", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_26", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_84", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_84", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_84", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_84", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_84", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_84", xlsname));
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Save without selecting the user");
				click(rules.buttonSave);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_94", xlsname),
						"Able to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_94", xlsname),
						"Unable to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_94", xlsname));
				// actionclick(driver.findElement(rules.toggleOff), "Able to provide a toggle
				// button below the second dropdown", "Unable to provide a toggle button below
				// the second dropdown");

				test.log(LogStatus.INFO, "On/Off the toggle button");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_4", xlsname),
						excelutil.getData("Rules", "elementname_97", xlsname),
						"Able to Toggle turned on: this rule should be applied to only unassigned contacts. "
								+ excelutil.getData("Rules", "elementname_97", xlsname),
						"Unable to Toggle turned on: this rule should be applied to only unassigned contacts. "
								+ excelutil.getData("Rules", "elementname_97", xlsname));

				click(rules.textRuleName);
				click(rules.toggleOn, "Able to provide a toggle button below the second dropdown",
						"Unable to provide a toggle button below the second dropdown");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_4", xlsname),
						excelutil.getData("Rules", "Elementname_109", xlsname),
						"Able to Toggle turned on: this rule should be applied to only unassigned contacts. "
								+ excelutil.getData("Rules", "elementname_98", xlsname),
						"Unable to Toggle turned on: this rule should be applied to only unassigned contacts. "
								+ excelutil.getData("Rules", "elementname_98", xlsname));

				test.log(LogStatus.INFO, "9. Select Assign to user as Action.");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_95", xlsname),
						"Able to display a place holder for the dropdown as "
								+ excelutil.getData("Rules", "elementname_95", xlsname),
						"Unable to display a place holder for the dropdown as"
								+ excelutil.getData("Rules", "elementname_95", xlsname));

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_95", xlsname),
						"Able to click on " + excelutil.getData("Rules", "elementname_95", xlsname),
						"Unable to click on " + excelutil.getData("Rules", "elementname_95", xlsname));

				sendkeys(rules.textOrganzation, excelutil.getData("Rules", "elementname_96", xlsname),
						"Able to displauy the list of existing agency users for that particular agency.",
						"Unable to displauy the list of existing agency users for that particular agency.");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_96", xlsname),
						"Able to displauy the list of existing agency users for that particular agency. "
								+ excelutil.getData("Rules", "elementname_96", xlsname),
						"Unable to displauy the list of existing agency users for that particular agency."
								+ excelutil.getData("Rules", "elementname_96", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_96", xlsname),
						"Able to displauy the list of existing agency users for that particular agency. "
								+ excelutil.getData("Rules", "elementname_96", xlsname),
						"Unable to displauy the list of existing agency users for that particular agency."
								+ excelutil.getData("Rules", "elementname_96", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_AddOppurnityPipeline() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_26", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_27", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_83", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_83", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_83", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_83", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_83", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_83", xlsname));
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Save without selecting the user");
				click(rules.buttonSave);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_99", xlsname),
						"Able to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_99", xlsname),
						"Unable to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_99", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_100", xlsname),
						"Able to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_100", xlsname),
						"Unable to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_100", xlsname));

				test.log(LogStatus.INFO, "9. Select Add/Update opportunity pipeline as Action.");
				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				tagautomation.Placeholder(excelutil.getData("Rules", "Elementname_101", xlsname),
						driver.findElement(rules.textOpportunityName),
						"Able to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_101", xlsname),
						"Unable to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_101", xlsname));
				isdisplay(rules.dropDownSelectAPipeline,
						"Able to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_102", xlsname),
						"Unable to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_102", xlsname));
				isdisplay(rules.dropDownSelectStage,
						"Able to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_103", xlsname),
						"Unable to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_103", xlsname));
				tagautomation.Placeholder(excelutil.getData("Rules", "Elementname_104", xlsname),
						driver.findElement(rules.textLeadValue),
						"Able to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_104", xlsname),
						"Unable to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_104", xlsname));
				tagautomation.Placeholder(excelutil.getData("Rules", "Elementname_105", xlsname),
						driver.findElement(rules.textEnterSource),
						"Able to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_105", xlsname),
						"Unable to display the following fields below the dropdown"
								+ excelutil.getData("Rules", "Elementname_105", xlsname));

				test.log(LogStatus.INFO, "9. Select Add/Update opportunity pipeline as Action.");
				click(rules.dropDownSelectAPipeline);
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_106", xlsname),
						"Able to display the list of pipelines in the respective agency.",
						"Unable to display the list of pipelines in the respective agency.");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_106", xlsname),
						"Able to allow user to select one pipeline at one time"
								+ excelutil.getData("Rules", "Elementname_106", xlsname),
						"Unable to allow user to select one pipeline at one time"
								+ excelutil.getData("Rules", "Elementname_106", xlsname));

				test.log(LogStatus.INFO, "11. Click on Select Stage");

				sequencepage.isEditableWithLocator(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_107", xlsname),
						"Able to enable the stage dropdown only when pipeline is selected.",
						"Unable to enable the stage dropdown only when pipeline is selected.");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_107", xlsname), "Able to click on dropdown",
						"Unable to click on dropdown");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_107", xlsname),
						"Able to display the list of pipelines in the respective agency.",
						"Unable to display the list of pipelines in the respective agency.");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_107", xlsname),
						"Able to display the selected pipeline stages only."
								+ excelutil.getData("Rules", "Elementname_107", xlsname),
						"Unable to display the selected pipeline stages only."
								+ excelutil.getData("Rules", "Elementname_107", xlsname));

				test.log(LogStatus.INFO, "11. Click on Opportunity Name");

				sendkeys(rules.textOpportunityName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow admin to enter data in the text field.",
						"Unable to allow admin to enter data in the text fields");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textOpportunityName, excelutil.getData("Rules", "characters", xlsname),
						"Able to allow uset to enter special characters and alpha numberics.",
						"Unable to allow uset to enter special characters and alpha numberics.");

				sequencepage.verifyWordsAbove40Characters(100, driver.findElement(rules.textOpportunityName),
						"Able to restrict the user to 100 characters.. ",
						"Unable to restrict the user to 100 characters..");

				test.log(LogStatus.INFO, "11. Click on Opportunity Source");

				sendkeys(rules.textEnterSource, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow admin to enter data in the text field.",
						"Unable to allow admin to enter data in the text fields");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textEnterSource, excelutil.getData("Rules", "characters", xlsname),
						"Able to allow uset to enter special characters and alpha numberics.",
						"Unable to allow uset to enter special characters and alpha numberics.");

				sequencepage.verifyWordsAbove40Characters(100, driver.findElement(rules.textEnterSource),
						"Able to restrict the user to 100 characters.. ",
						"Unable to restrict the user to 100 characters..");
				test.log(LogStatus.INFO, "11. Click on Lead Value");

				isdisplay(rules.textDollar, "Able to display a text field with a Dollar as prefix to the field.",
						"Unable to display a text field with a Dollar as prefix to the field.");
				sendkeys(rules.textLeadValue, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow admin to enter data in the text field.",
						"Unable to allow admin to enter data in the text fields");

				test.log(LogStatus.INFO, "11. Select and enter the required fields");

				click(rules.buttonSave, "Able to save Button", "Unable to save Button");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_108", xlsname),
						"Able to display Enter Correct Word  toast message",
						"Unable to display Enter Correct Word  toast message");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(shortwaitvalue);
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRemoveSequences() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_26", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_27", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_110", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_110", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_110", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_110", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_110", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_110", xlsname));

				test.log(LogStatus.INFO, "Click on Save without selecting the user");
				click(rules.buttonSave);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_112", xlsname),
						"Able to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_112", xlsname),
						"Unable to display empty data validation as "
								+ excelutil.getData("Rules", "elementname_112", xlsname));

				test.log(LogStatus.INFO, "Add the Actions");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_76", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_76", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_76", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_76", xlsname),
						"Able to click the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_76", xlsname),
						"Unable to Click the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_76", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_111", xlsname),
						"Able to display the list of Sequences(Campaigns) in the dropdown."
								+ excelutil.getData("Rules", "Elementname_111", xlsname),
						"Unable to display the list of Sequences(Campaigns) in the dropdown."
								+ excelutil.getData("Rules", "Elementname_111", xlsname));

				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_111", xlsname),
						"Able to allow admin to select only one Sequence at a time"
								+ excelutil.getData("Rules", "Elementname_111", xlsname),
						"Unable to allow admin to select only one Sequence at a time"
								+ excelutil.getData("Rules", "Elementname_111", xlsname));

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");
				waitforelement(shortwaitvalue);
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifySendEmail() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_27", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_28", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_113", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_113", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_113", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_114", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_114", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_114", xlsname));

				waitforelement(shortwaitvalue);

				isdisplay(rules.textFromEmail, "Able to display the field 'From' Address as Text box.",
						"Unable to display the field 'From' Address as Text box");
				isdisplay(rules.textToEmail, "Able to display the field 'TO' Address as Text box.",
						"Unable to display the field 'To' Address as Text box");
				isdisplay(rules.textSubject, "Able to display the field 'Subject' Address as Text box.",
						"Unable to display the field 'Subject' Address as Text box");

				waitforelement(shortwaitvalue);

				sequencepage.MoveToFrame(sequencepage.frameElement);
				System.out.println("entering the zone---> InDanger");
				isdisplay(rules.textfieldContentEditingTools,
						"Able to display the field 'fieldContent' Address as Text box.",
						"Unable to display the field 'fieldContent' Address as Text box");
				System.out.println("entering the zone---> OUT OF Danger");
				sequencepage.defaultContent();
				isdisplay(rules.tableCustomValues, "Able to display the following fields Custom values",
						"Unable to display the following fields CustomValues");
				waitforelement(shortwaitvalue);
				isdisplay(rules.linkAttachment, "Able to display the field Attachment",
						"Unable to display the field Attachment");
				waitforelement(shortwaitvalue);

				isdisplay(rules.buttonSendTest, "Able to click on ButtonSendTest", "Unable to click on ButtonSendText");

				test.log(LogStatus.INFO, "10. Click on Send button without entering From and To email address");

				click(rules.buttonSendTest, "Able to click on SendTest sucesfuly",
						"Unable to click on SendTest Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_118", xlsname),
						"Able to display a error as" + excelutil.getData("Rules", "Elementname_118", xlsname),
						"Unable to display a error as" + excelutil.getData("Rules", "Elementname_118", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_119", xlsname),
						"Able to display a error as" + excelutil.getData("Rules", "Elementname_119", xlsname),
						"Unable to display a error as" + excelutil.getData("Rules", "Elementname_119", xlsname));
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "10. Enter invalid From and To email addresses");

				sendkeys(rules.textFromEmail, excelutil.getData("Rules", "textSms", xlsname),
						"Able to user to enter text in the text boxes" + excelutil.getData("Rules", "textSms", xlsname),
						"Unable to user to enter text in the text boxes"
								+ excelutil.getData("Rules", "textSms", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_120", xlsname),
						"Able to display a error as" + excelutil.getData("Rules", "Elementname_120", xlsname),
						"Unable to display a error as" + excelutil.getData("Rules", "Elementname_120", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textToEmail, excelutil.getData("Rules", "textSms", xlsname),
						"Able to user to enter text in the text boxes" + excelutil.getData("Rules", "textSms", xlsname),
						"Unable to user to enter text in the text boxes"
								+ excelutil.getData("Rules", "textSms", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_121", xlsname),
						"Able to display a error as" + excelutil.getData("Rules", "Elementname_121", xlsname),
						"Unable to display a error as" + excelutil.getData("Rules", "Elementname_121", xlsname));

				test.log(LogStatus.INFO, "10. Click on From and TO  email address");
				isdisplay(rules.textFromEmail, "Able to display text boxes each.", "Unable to display text boxes each");
				waitforelement(shortwaitvalue);

				GPS_TagAutomationPage tagautomation = new GPS_TagAutomationPage();
				tagautomation.Placeholder(excelutil.getData("Rules", "Elementname_115", xlsname),
						driver.findElement(rules.textFromEmail),
						"Able to display the placeholder as a text"
								+ excelutil.getData("Rules", "Elementname_115", xlsname),
						"Unable to display the following fields with placeholder as a text"
								+ excelutil.getData("Rules", "Elementname_115", xlsname));
				tagautomation.Placeholder(excelutil.getData("Rules", "Elementname_116", xlsname),
						driver.findElement(rules.textToEmail),
						"Able to display the placeholder as a text"
								+ excelutil.getData("Rules", "Elementname_116", xlsname),
						"Unable to display the following fields with placeholder as a text"
								+ excelutil.getData("Rules", "Elementname_116", xlsname));
				clear(rules.textFromEmail, "Able to clear the text ", "Unable to clear the text");
				sendkeys(rules.textFromEmail, excelutil.getData("Rules", "Elementname_117", xlsname),
						"Able to user to enter text in the text boxes"
								+ excelutil.getData("Rules", "Elementname_117", xlsname),
						"Unable to user to enter text in the text boxes"
								+ excelutil.getData("Rules", "characters", xlsname));
				clear(rules.textToEmail, "Able to clear the text ", "Unable to clear the text");
				sendkeys(rules.textToEmail, excelutil.getData("Rules", "Elementname_117", xlsname),
						"Able to user to enter text in the text boxes"
								+ excelutil.getData("Rules", "Elementname_117", xlsname),
						"Unable to user to enter text in the text boxes"
								+ excelutil.getData("Rules", "Elementname_117", xlsname));

				test.log(LogStatus.INFO, "10. Click on Subject");
				sendkeys(rules.textSubject, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow admin to enter data in the text field.",
						"Unable to allow admin to enter data in the text fields");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textSubject, excelutil.getData("Rules", "characters", xlsname),
						"Able to allow uset to enter special characters and alpha numberics.",
						"Unable to allow uset to enter special characters and alpha numberics.");
				sequencepage.verifyWordsAbove40Characters(100, driver.findElement(rules.textSubject),
						"Able to restrict the user to 100 characters.. ",
						"Unable to restrict the user to 100 characters..");

				test.log(LogStatus.INFO, "10. Select any of the content tools");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_122", xlsname),
						"Able to display Default font: " + excelutil.getData("Rules", "Elementname_122", xlsname),
						"Unable to display Default font: " + excelutil.getData("Rules", "Elementname_122", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_123", xlsname),
						"Able to display default font size" + excelutil.getData("Rules", "Elementname_123", xlsname),
						"Unable to display default font size" + excelutil.getData("Rules", "Elementname_123", xlsname));

				test.log(LogStatus.INFO, "10. Click on Custom values");

				click(rules.tableCustomValues, "Able to click on Custom values", "Unable to click on CustomValues");
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_124", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_124", xlsname),
						"Unable to display " + excelutil.getData("Rules", "Elementname_123", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_124", xlsname),
						"Able to display" + excelutil.getData("Rules", "Elementname_124", xlsname),
						"Unable to display " + excelutil.getData("Rules", "Elementname_124", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_125", xlsname),
						"Able to display" + excelutil.getData("Rules", "Elementname_125", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_125", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_126", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_126", xlsname),
						"Unable to display " + excelutil.getData("Rules", "Elementname_126", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_127", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_127", xlsname),
						"Unable to display " + excelutil.getData("Rules", "Elementname_127", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_128", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_128", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_128", xlsname));

				test.log(LogStatus.INFO, "11. Hover or Click on Contacts");

				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_124", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_124", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_124", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_129", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_129", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_129", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_130", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_130", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_130", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_131", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_131", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_131", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_132", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_132", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_132", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_133", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_133", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_133", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_134", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_134", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_134", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_135", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_135", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_135", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_136", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_136", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_136", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_137", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_137", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_137", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_138", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_138", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_138", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_139", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_139", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_139", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_140", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_140", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_140", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_141", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_141", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_141", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_142", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_142", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_142", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_143", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_143", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_143", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_144", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_144", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_144", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_145", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_145", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_145", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_146", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_146", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_146", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_147", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_147", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_147", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_148", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_148", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_148", xlsname));

				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_148", xlsname),
						"Able allow user to select any of the field from the list"
								+ excelutil.getData("Rules", "Elementname_148", xlsname),
						"Unable to allow user to select any of the field from the list"
								+ excelutil.getData("Rules", "Elementname_148", xlsname));
				test.log(LogStatus.INFO, "11. Hover or Click on User");

				click(rules.tableCustomValues, "Able to click on Custom values", "Unable to click on CustomValues");
				waitforelement(shortwaitvalue);

				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_125", xlsname),
						"Able to display" + excelutil.getData("Rules", "Elementname_125", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_125", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_129", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_129", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_129", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_130", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_130", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_130", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_131", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_131", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_131", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_132", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_132", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_132", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_149", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_149", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_149", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_150", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_150", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_150", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_151", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_151", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_151", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_152", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_152", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_152", xlsname));
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_152", xlsname),
						"Able to allow user to select any of the field from the list "
								+ excelutil.getData("Rules", "Elementname_152", xlsname),
						"Unable to allow user to select any of the field from the list"
								+ excelutil.getData("Rules", "Elementname_152", xlsname));

				test.log(LogStatus.INFO, "11. Hover or Click on Appointments");

				click(rules.tableCustomValues, "Able to click on Custom values", "Unable to click on CustomValues");
				waitforelement(shortwaitvalue);

				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_126", xlsname),
						"Able to display" + excelutil.getData("Rules", "Elementname_126", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_126", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_153", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_153", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_153", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_154", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_154", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_154", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_155", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_155", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_155", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_156", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_156", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_156", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_157", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_157", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_157", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_158", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_158", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_158", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_159", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_159", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_159", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_160", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_160", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_160", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_161", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_161", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_161", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_162", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_162", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_162", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_163", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_163", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_163", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_164", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_164", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_164", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_164", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_164", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_164", xlsname));

				test.log(LogStatus.INFO, "11. Hover or Click on Organization");

				click(rules.tableCustomValues, "Able to click on Custom values", "Unable to click on CustomValues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_127", xlsname),
						"Able to display" + excelutil.getData("Rules", "Elementname_127", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_127", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_165", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_165", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_165", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_166", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_166", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_166", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_167", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_167", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_167", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_168", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_168", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_168", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_169", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_169", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_169", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_170", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_170", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_170", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_171", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_171", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_171", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_172", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_172", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_172", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_173", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_173", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_173", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_174", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_174", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_174", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_175", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_175", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_175", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_176", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_176", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_176", xlsname));
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_177", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_177", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_177", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_178", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_178", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_178", xlsname));
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_178", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_178", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_178", xlsname));

				test.log(LogStatus.INFO, "11. Hover or Click on Custom values");
				click(rules.tableCustomValues, "Able to click on Custom values", "Unable to click on CustomValues");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_128", xlsname),
						"Able to display" + excelutil.getData("Rules", "Elementname_128", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_128", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_179", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_179", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_179", xlsname));
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_179", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_179", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_179", xlsname));

				test.log(LogStatus.INFO, "6. Click on Body and Observe the customized options");

				isdisplay(rules.iconUndo, "Able to display iconUndo", "Unable to display iconUndo");

				isdisplay(rules.iconRedo, "Able to display iconRedo", "Unable to display iconRedo");

				isdisplay(rules.linkeditlink, "Able to display edit link", "Unable to display editlink");

				isdisplay(rules.linkeditimage, "Able to display editimage", "Unable to display editimage");

				test.log(LogStatus.INFO, "Click on Attachements icon and application will display a pop up");

				click(rules.linkeditimage);

				waitforelement(shortwaitvalue);
				click(rules.linkUpload, "Able to click on upload button", "Unable to click on upload button");
				waitforelement(shortwaitvalue);

				isdisplay(rules.buttonBrowseForImage, "Able to display  Select and Cancel options in the pop up.",
						"Unable to  Select and Cancel options in the pop up.");

				click(rules.buttonCancel_1);

				test.log(LogStatus.INFO, "10. Click on Text field");

				sequencepage.MoveToFrame(sequencepage.frameElement);

				sendkeys(rules.textfieldContentEditingTools, excelutil.getData("Rules", "textSms", xlsname),
						"Able to allow user to enter text manually..", "Unable to allow user to enter text manually.");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textfieldContentEditingTools, excelutil.getData("Rules", "characters", xlsname),
						"Able to allow user to enter special characters and alpha numerics..",
						"Unable to allow user to enter special characters and alpha numerics.");

				sequencepage.defaultContent();
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "10. Enter all mandatory fields and non mandatory fields");

				click(rules.buttonSave, "Able to click on save button", "Unable to click on save button");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "elementname_74", xlsname),
						"Able to display send the email to that particular email address with all the content which includes attachements(if any)",
						"Unable to send the email to that particular email address with all the content which includes attachements(if any)");

				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				waitforelement(shortwaitvalue);
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAttachmentFiles() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_28", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_29", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"When the user click  What condition should cause this automation to run?Event dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_113", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_113", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_113", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_114", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_114", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_114", xlsname));

				waitforelement(shortwaitvalue);

				isdisplay(rules.textFromEmail, "Able to display the field 'From' Address as Text box.",
						"Unable to display the field 'From' Address as Text box");
				isdisplay(rules.textToEmail, "Able to display the field 'TO' Address as Text box.",
						"Unable to display the field 'To' Address as Text box");
				isdisplay(rules.textSubject, "Able to display the field 'Subject' Address as Text box.",
						"Unable to display the field 'Subject' Address as Text box");

				waitforelement(shortwaitvalue);

				// Starting from here

				test.log(LogStatus.INFO, "Click on Attachements icon");

				click(rules.linkeditimage, "Able to click on Attachment icon", "Unable to click on Attachment icon");
				waitforelement(shortwaitvalue);
				click(rules.linkUpload);

				chooseFile(rules.buttonBrowseForImage, driver.findElement(rules.buttonBrowseForImage),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\" + "DSL.jpg",
						"User  display a pop up to the user to select the images from personal computer",
						"User  display a pop up to the user to select the images from personal computer");

				click(rules.linkUpload);

				chooseFile(rules.buttonBrowseForImage, driver.findElement(rules.buttonBrowseForImage),
						System.getProperty("user.dir") + "\\src\\test\\resources\\Files_Upload\\" + "Morethan2.5.jpg",
						"User  display a pop up to the user to select the images from personal computer",
						"User  display a pop up to the user to select the images from personal computer");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_109", xlsname),
						excelutil.getData("Rules", "Elementname_180", xlsname),
						"Able to display a validation as 'Image size should not be more than 2.5mb'"
								+ excelutil.getData("Rules", "Elementname_180", xlsname),
						"Unable to display a validation as 'Image size should not be more than 2.5mb'"
								+ excelutil.getData("Rules", "Elementname_180", xlsname));

				click(rules.buttonOk);

				waitforelement(shortwaitvalue);
				click(rules.buttonCancel_1);
				test.log(LogStatus.INFO, "User should be able to Logout GPS Successfully.");

				waitforelement(shortwaitvalue);
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_CopyRule_158_159_160_161_162_163_164_165_166_167() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_29", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_30", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click on the dropdown" + excelutil.getData("Rules", "elementname_7", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_3", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Value_3", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Value_3", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Value_3", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Value_3", xlsname));

				test.log(LogStatus.INFO, "Click on Save button application will navigate to add new rule page");

				click(rules.buttonSave, "Able to click on Save Button to save the rule",
						"Unable to click on Save Button to save the rule");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "elementname_74", xlsname),
						"Able to get the toast message for sucesfully creation",
						"Unable to get the toast message for sucesfully creation");
				waitforelement(shortwaitvalue);

				isdisplay(rules.buttonCopyRule, "Able to display Copy rule button only after the Rule was added",
						"Unable to display Copy rule button only after the Rule was added.");
				waitforelement(shortwaitvalue);
				isdisplay(rules.buttonDeleteRule, "Able to  display delete button only after the Rule was added.",
						"Unable to  display delete button only after the Rule was added.");

				test.log(LogStatus.INFO, "6. Click on Copy rule button");

				click(rules.buttonCopyRule, "Able to click on Copy Rules", "Unable to click on Copy Rules");

				waitforelement(shortwaitvalue);
				clear(rules.textNewRuleName, "Able to clear the newRuleName", "Unable to clear the newRuleName");
				String randomValue = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6)
						+ excelutil.getData("Rules", "Elementname_182", xlsname);
				sendkeys(rules.textNewRuleName, randomValue, "Able to allow user to edit the rule name.",
						"Unable to allow user to edit the rule name.");

				click(rules.dropDownSelectOrg);
				waitforelement(shortwaitvalue);
				// Organization Name_1

				sendkeys(rules.textOrganzation, excelutil.getData("Rules", "Organization Name_1", xlsname),
						"Able to enter the details", "Unable to enter the details");
				sequencepage.elementClickWithArrow(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Organization Name_1", xlsname),
						"Able to display all the organizations in the dropdown. ",
						"Unable to display all the organizations in the dropdown.");

				click(rules.textNewRuleName);
				waitforelement(shortwaitvalue);
				click(rules.buttonCopy, "Able to click on button Copy Sucesfully", "Unable to click on button Copy");
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_183", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

				test.log(LogStatus.INFO, "9. Navigate to status dropdown");
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "dropdownStatus", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "dropdownStatus", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "dropdownStatus", xlsname));

				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Text_1", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Text_1", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Text_1", xlsname));

				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Text_1", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Text_1", xlsname),
						"Unable to  display define conditions " + excelutil.getData("Rules", "Text_1", xlsname));

				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "dropdownStatus", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "dropdownStatus", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "dropdownStatus", xlsname));

				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_186", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_186", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_186", xlsname));

				test.log(LogStatus.INFO, "click on Delete");
				waitforelement(shortwaitvalue);
				click(rules.buttonDeleteRule, "Able to click on delete icon", "Able to click on delete icon");
				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.headerDelete,
						"Able to  display a popup with header as  Enter word delete to remove the event",
						"Unable to  display a popup with header as  Enter word delete to remove the event");

				isdisplay(sequencepage.placeholderDeleteText, "Able to display text field with placeholder Enter word",
						"Unable to display text field with placeholder Enter word");

				test.log(LogStatus.INFO, "7. Click on Submit without giving the word");
				isdisplay(sequencepage.buttonSubmit, "Able to display text field with buttonSubmit",
						"Unable to display text field with buttonSubmit");
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.errorConfirmationRequired, "Able to display validation Enter correct word",
						"Unable to display validation Enter correct word");

//				isdisplay(sequencepage.iconCross, "Able to display Cross mark", "Unable to display Cross mark");

				test.log(LogStatus.INFO, "Click on Submit by giving incorrect word");

				sendkeys(sequencepage.placeholderDeleteText, excelutil.getData("Rules", "Elementname_182", xlsname),
						"Able to send the text sucesfully", "Unable to send the text");

				click(sequencepage.buttonSubmit);

				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_185", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

				test.log(LogStatus.INFO, "Click on Submit by giving correct word");
				sendkeys(sequencepage.placeholderDeleteText, excelutil.getData("Rules", "deletesampletext", xlsname),
						"Able to enter the delete text sucesfully", "Unable to enter the delete text");
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_184", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_CopyRule_171_172_173_174_175_176_177_178_179_180() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_30", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_31", xlsname).equalsIgnoreCase("N")) {

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

				test.log(LogStatus.INFO, "Click on Automation at LHS");
				waitforelement(shortwaitvalue);
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(mediumwaitvalue);

				test.log(LogStatus.INFO, "Add a folder in the page");

				GPS_RulesPage rules = new GPS_RulesPage();

				click(rules.buttonAddNewFolder, "Able to click on AddNewFolder button",
						"Unable to click on ADdNewFolder button");

				waitforelement(shortwaitvalue);
				String value_folder = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 2)
						+ excelutil.getData("Rules", "textSms", xlsname);

				sendkeys(rules.inputfolderName, value_folder, "Able to enter the text", "Unable to enter the text");
				waitforelement(shortwaitvalue);
				click(rules.buttonSave);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_187", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

				rules.isdisplayed(excelutil.getData("Rules", "elementname", xlsname), value_folder,
						excelutil.getData("Rules", "Elementname_188", xlsname), "Able to display edit button",
						"Unable to display edit button");

				rules.isdisplayed(excelutil.getData("Rules", "elementname", xlsname), value_folder,
						excelutil.getData("Rules", "Elementname_189", xlsname), "Able to display delete button",
						"Unable to display delete button");

				test.log(LogStatus.INFO, "6. Click on Edit button");

				rules.elementclick(excelutil.getData("Rules", "elementname", xlsname), value_folder,
						excelutil.getData("Rules", "Elementname_188", xlsname), "Able to display edit button",
						"Unable to display edit button");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_192", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_192", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_192", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_193", xlsname),
						"Able to display " + excelutil.getData("Rules", "Elementname_193", xlsname),
						"Unable to display" + excelutil.getData("Rules", "Elementname_193", xlsname));
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "6. Click on Edit button and click on Close button or Close icon");

				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_192", xlsname),
						" Able to close the pop up and should display the previous folder name only."
								+ excelutil.getData("Rules", "Elementname_192", xlsname),
						"Unable to  close the pop up and should display the previous folder name only."
								+ excelutil.getData("Rules", "Elementname_192", xlsname));

				test.log(LogStatus.INFO, "6. Click on Edit button and change the value and click on submit");

				rules.elementclick(excelutil.getData("Rules", "elementname", xlsname), value_folder,
						excelutil.getData("Rules", "Elementname_188", xlsname), "Able to display edit button",
						"Unable to display edit button");

				String value_folder_1 = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 3)
						+ excelutil.getData("Rules", "textSms", xlsname);

				clear(rules.inputfolderName, "Able to clear the text", "Unable to clear the text");
				waitforelement(shortwaitvalue);
				sendkeys(rules.inputfolderName, value_folder_1, "Able to enter the text", "Unable to enter the text");
				waitforelement(shortwaitvalue);
				click(rules.buttonUpdate);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);
				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_194", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname), value_folder_1,
						" Able to update the folder" + value_folder_1, "Unable to  Update the folder" + value_folder_1);

				test.log(LogStatus.INFO, "click on Delete");
				waitforelement(shortwaitvalue);
//				click(rules.buttonDeleteRule, "Able to click on delete icon", "Able to click on delete icon");
				rules.elementclick(excelutil.getData("Rules", "elementname", xlsname), value_folder,
						excelutil.getData("Rules", "Elementname_189", xlsname), "Able to display delete button",
						"Unable to display delete button");

				waitforelement(mediumwaitvalue);
				isdisplay(sequencepage.headerDelete,
						"Able to  display a popup with header as  Enter word delete to remove the event",
						"Unable to  display a popup with header as  Enter word delete to remove the event");

				isdisplay(sequencepage.placeholderDeleteText, "Able to display text field with placeholder Enter word",
						"Unable to display text field with placeholder Enter word");

				test.log(LogStatus.INFO, "7. Click on Submit without giving the word");
				isdisplay(sequencepage.buttonSubmit, "Able to display text field with buttonSubmit",
						"Unable to display text field with buttonSubmit");
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				isdisplay(sequencepage.errorConfirmationRequired, "Able to display validation Enter correct word",
						"Unable to display validation Enter correct word");

//				isdisplay(sequencepage.iconCross, "Able to display Cross mark", "Unable to display Cross mark");

				test.log(LogStatus.INFO, "Click on Submit by giving incorrect word");

				sendkeys(sequencepage.placeholderDeleteText, excelutil.getData("Rules", "Elementname_182", xlsname),
						"Able to send the text sucesfully", "Unable to send the text");

				click(sequencepage.buttonSubmit);

				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_185", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

				test.log(LogStatus.INFO, "Click on Submit by giving correct word");
				sendkeys(sequencepage.placeholderDeleteText, excelutil.getData("Rules", "deletesampletext", xlsname),
						"Able to enter the delete text sucesfully", "Unable to enter the delete text");
				click(sequencepage.buttonSubmit);
				waitforelement(shortwaitvalue);
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_190", xlsname), "Able to get the mesage sucesfully",
						"Unable to get the message sucesfully");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRule_225_226_231_238() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_31", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_32", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"6. Click on Radio buttons custom filter in the dropdown and application will display another dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_195", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_195", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_195", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_195", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				test.log(LogStatus.INFO, "5. Select Appointment from the dropdown");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display the Add filter button as Add Condition",
						"Unable to display the Add filter button as Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_4", xlsname),
						excelutil.getData("Rules", "Elementname_197", xlsname),
						"Able to display the Add filter button as Add Condition",
						"Unable to display the Add filter button as Add Condition");

				test.log(LogStatus.INFO, "6. Select filters and application will display another field/dropdown");

				click(rules.buttonSave);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_70", xlsname),
						"Able to display the Add filter button as Add Condition",
						"Unable to display the Add filter button as Add Condition");

				test.log(LogStatus.INFO, "6. Click on dropdown and navigate to custom filters");

				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_196", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_196", xlsname),
						"Able to display Paragraph text custom field in the dropdown",
						"Unable to display Paragraph text custom field in the dropdown");

				test.log(LogStatus.INFO, "9. Select has tag as a condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				clear(rules.textFolderName, "Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_14", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_14", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRule_239() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_32", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_33", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"6. Click on Radio buttons custom filter in the dropdown and application will display another dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_198", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_198", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_198", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_198", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				test.log(LogStatus.INFO,
						"6. Click on the filter dropdown and select replied to Channel and application will display another dropdown.");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				// clear(rules.textFolderName, "Able to enter the value", "Unable to enter the
				// value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_199", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_199", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_16", xlsname),
						"Able to display a dropdown with a place holder 'Select a Sequence' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Sequence' with label as Define Condition");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyReplyToSequence_240() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_33", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_34", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"6. Click on Radio buttons custom filter in the dropdown and application will display another dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_198", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_198", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_198", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_198", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				test.log(LogStatus.INFO,
						"6. Click on the filter dropdown and select replied to Channel and application will display another dropdown.");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				// clear(rules.textFolderName, "Able to enter the value", "Unable to enter the
				// value");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_200", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_200", xlsname),
						"Able to click  on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_200", xlsname),
						"Unable to click on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_200", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_201", xlsname),
						"Able to display a dropdown with a place holder 'Select a Channel' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Channel' with label as Define Condition");
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_201", xlsname),
						"Able to click a dropdown with a place holder 'Select a Channel' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Channel' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_202", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_202", xlsname),
						"Able to click  on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_202", xlsname),
						"Unable to click on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_202", xlsname));

				clear(rules.textFolderName, "Able to clear the existing data", "Unable to clear the existing data");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_203", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_203", xlsname),
						"Able to display  on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_203", xlsname),
						"Unable to click on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_203", xlsname));

				clear(rules.textFolderName, "Able to clear the existing data", "Unable to clear the existing data");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_204", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_204", xlsname),
						"Able to display  on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_204", xlsname),
						"Unable to click on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_204", xlsname));

				clear(rules.textFolderName, "Able to clear the existing data", "Unable to clear the existing data");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_205", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_205", xlsname),
						"Able to display  on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_205", xlsname),
						"Unable to click on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_205", xlsname));

				clear(rules.textFolderName, "Able to clear the existing data", "Unable to clear the existing data");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_206", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.isDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_206", xlsname),
						"Able to display  on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_206", xlsname),
						"Unable to click on the dropdown define conditions"
								+ excelutil.getData("Rules", "Elementname_206", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAddFilterLabel_241_242_243() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_34", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_35", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO,
						"6. Click on Radio buttons custom filter in the dropdown and application will display another dropdown.");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_207", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_207", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_207", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_207", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				test.log(LogStatus.INFO,
						"6. Click on the filter dropdown and select replied to Channel and application will display another dropdown.");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	// Started on 08-10-2025
	@Test
	public void GPS_VerifyLabelFormSubmitted_244_245_246() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_35", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_36", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select Form Submittef from the dropdown");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_208", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_208", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_208", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_208", xlsname));
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO,
						"Select FormSubmitted changed from the dropdown and application will display filter button");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display the following fields in the tile"
								+ excelutil.getData("Rules", "elementname_10", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname), "Able to click on Add condition",
						"Unable to click on Add condition");

				test.log(LogStatus.INFO,
						"6. Click on the filter dropdown and select replied to Channel and application will display another dropdown.");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				test.log(LogStatus.INFO,
						"6. Click on Add Filter and application will display a dropdown and click on the dropdown");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_67", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_67", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "elementname_67", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "elementname_67", xlsname));
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyFacebookLead_247_248() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_36", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_37", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select Facebook lead form is submitted from the dropdown");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_209", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_209", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_209", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_209", xlsname));
				waitforelement(shortwaitvalue);

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_81", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_81", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_81", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_81", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_81", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_81", xlsname));

				test.log(LogStatus.INFO, "6. Click on Save/Update with empty tag");
				click(sequencepage.buttonSave);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_210", xlsname),
						"Able to display validation as" + excelutil.getData("Rules", "Elementname_210", xlsname),
						"Unable to display validation as" + excelutil.getData("Rules", "Elementname_210", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAddNotes_249_250() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_37", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_38", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select Facebook lead form is submitted from the dropdown");

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click  on the dropdown define conditions",
						"Unable to click on the dropdown define conditions");
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_209", xlsname),
						"Able to enter the value", "Unable to enter the value");
				waitforelement(shortwaitvalue);
				sequencepage.elementClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_209", xlsname),
						"Able to display define conditions " + excelutil.getData("Rules", "Elementname_209", xlsname),
						"Unable to  display define conditions "
								+ excelutil.getData("Rules", "Elementname_209", xlsname));
				waitforelement(shortwaitvalue);

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "elementname_73", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "elementname_73", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "elementname_73", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_73", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_73", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_73", xlsname));

				test.log(LogStatus.INFO, "6. Click on Save/Update with empty tag");
				click(sequencepage.buttonSave);

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				sequencepage.toastMessage(sequencepage.errortoastmessage,
						excelutil.getData("Rules", "Elementname_211", xlsname),
						"Able to error message as" + excelutil.getData("Rules", "Elementname_211", xlsname),
						"Unable to get error message as " + excelutil.getData("Rules", "Elementname_211", xlsname));
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAssignUser_251_252() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_38", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_39", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_212", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_212", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_212", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_212", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_212", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_212", xlsname));

				test.log(LogStatus.INFO, "6. Toggle Off/ON the button");

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_4", xlsname),
						excelutil.getData("Rules", "elementname_213", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_213", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_213", xlsname));
				waitforelement(shortwaitvalue);

				click(rules.toggleOn);
				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "Elementname_109", xlsname),
						excelutil.getData("Rules", "elementname_214", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_214", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_214", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyAddOpportunity_253() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_39", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_40", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_215", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_215", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_215", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_215", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_215", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_215", xlsname));
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRemoveUser_255() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_40", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_41", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_85", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_85", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_85", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_85", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_85", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_85", xlsname));
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRemoveUser_256() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_41", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_42", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_89", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_89", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_89", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_89", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_89", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_89", xlsname));

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_79", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_79", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_79", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRemovefromallSequences_256() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_41", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_43", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_216", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_216", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_216", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_216", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_216", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_216", xlsname));
				/*
				 * waitforelement(shortwaitvalue); click(sequencepage.buttonSave);
				 * waitforelement(shortwaitvalue);
				 * sequencepage.elementDisplay(excelutil.getData("Rules", "elementname",
				 * xlsname), excelutil.getData("Rules", "elementname_79", xlsname),
				 * "Able to display a place holder as" + excelutil.getData("Rules",
				 * "elementname_79", xlsname), "Unable to display a place holder as" +
				 * excelutil.getData("Rules", "elementname_79", xlsname));
				 */
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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRemovefromspecificSequences_257_258() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_42", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_44", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_216", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_216", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_216", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_216", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_216", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_216", xlsname));

				waitforelement(shortwaitvalue);
				click(sequencepage.buttonSave);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_112", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "Elementname_112", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "Elementname_112", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyRemoveoppurnityPipeline_259() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_43", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_45", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_88", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_88", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_88", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_88", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_88", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_88", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifySendEmail_260() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_44", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_45", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				click(rules.buttonAddActions, "Able to click on AddActions Sucesfully",
						"Unable to click on AddActions Sucesfully");
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_72", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_72", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_72", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_72", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_114", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_114", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_114", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_114", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_114", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_114", xlsname));

				test.log(LogStatus.INFO, "6. Click on from and to email address");

				sendkeys(rules.textFromEmail, "text@gmail.com",
						"Able to allow user to enter system field codes in from email address",
						"Unable to allow  user to enter system field codes in from email address");
				sendkeys(rules.textToEmail, "text@gmail.com",
						"Able to allow user to enter system field codes in to email address",
						"Unable to allow  user to enter system field codes in to email address");
				sendkeys(rules.textToEmail, "text@gmail.com,text1@gmail.com",
						"Able to allow user to enter multiple email address seperated by commas",
						"Unable to allow user to enter multiple email address seperated by commas");

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_VerifyTags_292_293_294_295_296_297_298_299() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_45", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_47", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "5. Select any event and select Assign to user as actions.");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_7", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_217", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_217", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_217", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_217", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_217", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_217", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display as " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);
				test.log(LogStatus.INFO, "6. Select Tag Changed in the dropdown and click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_219", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_219", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_219", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_219", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_219", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_219", xlsname));

				clear(rules.textFolderName, "Able to clear the text for further use",
						"Unable to clear the text for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_218", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_218", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_218", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_218", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_218", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_218", xlsname));

				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_218", xlsname),
						"Able to click the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_218", xlsname),
						"Unable to click the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_218", xlsname));
				test.log(LogStatus.INFO, "7. Select Tag Added");
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_18", xlsname),
						"Able to click the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_18", xlsname),
						"Unable to click the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_18", xlsname));

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_220", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_220", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_220", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_220", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_220", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_220", xlsname));
				test.log(LogStatus.INFO, "7. Select Tag Removed/Tag Added again click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_219", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_219", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_219", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementClick1(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_219", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_219", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_219", xlsname));

				test.log(LogStatus.INFO, "7. Select Tag Removed/Tag Added again click on Add Condition");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to display a dropdown with a place holder 'Select a Condition' with label as Define Condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_218", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_218", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_218", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname_21", xlsname),
						excelutil.getData("Rules", "elementname_20", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_20", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_20", xlsname));

				click(rules.buttonSave);
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_221", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_221", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_221", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_70", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_70", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "elementname_70", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_BirthdayRemainder_304() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_45", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_48", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "6. Select Birthday Reminder in the dropdown");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_7", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_222", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_222", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_222", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_222", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_222", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_222", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display as " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on  " + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_223", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_223", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_223", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_223", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_223", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_223", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_224", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_224", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_225", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_225", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_225", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_225", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_225", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_225", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_226", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_226", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_226", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_226", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_226", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_226", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_227", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_227", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_227", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_227", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_227", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_227", xlsname));

				waitforelement(shortwaitvalue);

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_223", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_223", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_223", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_223", xlsname),
						"Able to click on " + excelutil.getData("Rules", "Elementname_223", xlsname),
						"Unable to click on  " + excelutil.getData("Rules", "Elementname_223", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_228", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_228", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_228", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_228", xlsname),
						"Able to click on" + excelutil.getData("Rules", "Elementname_228", xlsname),
						"Unable to click on  " + excelutil.getData("Rules", "Elementname_228", xlsname));

				/*
				 * sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_228",
				 * xlsname), "Able to enter the value" + excelutil.getData("Rules",
				 * "Elementname_228", xlsname), "Unable to enter the value" +
				 * excelutil.getData("Rules", "Elementname_228", xlsname));
				 * 
				 * sequencepage.elementDisplay(excelutil.getData("Rules", "elementname",
				 * xlsname), excelutil.getData("Rules", "Elementname_228", xlsname),
				 * "Able to display as " + excelutil.getData("Rules", "Elementname_228",
				 * xlsname), "Unable to display as  " + excelutil.getData("Rules",
				 * "Elementname_228", xlsname));
				 */

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_229", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_229", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_229", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_229", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_229", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_229", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_230", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_230", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_230", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_230", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_230", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_230", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_231", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_231", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_231", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_231", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_231", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_231", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_232", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_232", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_232", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_232", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_232", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_232", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_233", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_233", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_233", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_233", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_233", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_233", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_234", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_234", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_234", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_234", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_234", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_234", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_235", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_235", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_235", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_235", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_235", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_235", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_236", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_236", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_236", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_236", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_236", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_236", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_237", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_237", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_237", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_237", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_237", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_237", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_238", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_238", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_238", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_238", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_238", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_238", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_239", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_239", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_239", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_239", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_239", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_239", xlsname));

				clear(rules.textFolderName, "Able to clear the value for further use",
						"Unable to clear the value for further use");
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_240", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_240", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_240", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_240", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_240", xlsname),
						"Unable to display as  " + excelutil.getData("Rules", "Elementname_240", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_240", xlsname),
						"Able to click on  " + excelutil.getData("Rules", "Elementname_240", xlsname),
						"Unable to click on   " + excelutil.getData("Rules", "Elementname_240", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on  " + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_224", xlsname));

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to click on " + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to click on  " + excelutil.getData("Rules", "Elementname_224", xlsname));

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
			loginpage.logoutForFailure("Failed due to exception so logout for next testcase run");

		}
	}

	@Test
	public void GPS_BirthdayRemainder_303() {

		shortwaitvalue = Integer.valueOf(config.getProperty("shortwait"));
		mediumwaitvalue = Integer.valueOf(config.getProperty("mediumwait"));
		longwaitvalue = Integer.valueOf(config.getProperty("longwait"));
		verylongwaitvalue = Integer.valueOf(config.getProperty("verylongwait"));
		extraverylongwaitvalue = Integer.valueOf(config.getProperty("extraverylongwait"));

		test = report.startTest(excelutil.getData("Rules", "HeaderName_45", xlsname));
		if (!isExecutionAllowed())
			return;
		GPS_LoginPage loginpage = new GPS_LoginPage();
		try {
			if (excelutil.getData("Dashboard", "Rules_48", xlsname).equalsIgnoreCase("N")) {

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
				test.log(LogStatus.INFO, "Click on Automation at LHS");

				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink1", xlsname),
						"User able to click on Automation Menu .", "User unable to click on Automation menu.");

				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "Click on Rules and application will display listing page.");
				orgdashboardpage.chooseModuleName(excelutil.getData("Rules", "SideLink2", xlsname),
						"Able to click on Rules Module", "Unable to click on Rules Modules");
				WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
				waitforelement(shortwaitvalue);

				test.log(LogStatus.INFO, "5. Click on Add New Rule Button");
				GPS_RulesPage rules = new GPS_RulesPage();
				click(rules.buttonAddNewRule, "Able to click on Add new Rule Button",
						"Unable to click on Add New Rule Button");

				test.log(LogStatus.INFO, "5. Enter Rule name in add new rule pop up and click on Save button");

				sendkeys(rules.textRuleName, excelutil.getData("Rules", "textSms", xlsname),
						"Able to enter special character and alphanumberic",
						"Unable to enter special character and alphanumberic");
				click(rules.dropdownSelectFolder, "Able to click on dropdown FolderName",
						"Unable to click on dropdown FolderName");

				waitforelement(shortwaitvalue);
				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Value_2", xlsname),
						"Able to display all the folders created in Rules page.",
						"Unable to display all the folders created in Rules page.");

				click(rules.buttonSave, "Able to  save the rule in the selected folder once admin clicks on save",
						"Unable to  save the rule in the selected folder once admin clicks on save");

				test.log(LogStatus.INFO, "6. Select Birthday Reminder in the dropdown");

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to display a place holder as" + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to display a place holder as" + excelutil.getData("Rules", "elementname_7", xlsname));
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_7", xlsname),
						"Able to click as " + excelutil.getData("Rules", "elementname_7", xlsname),
						"Unable to click  as " + excelutil.getData("Rules", "elementname_7", xlsname));
				waitforelement(shortwaitvalue);

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_222", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_222", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_222", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname_11", xlsname),
						excelutil.getData("Rules", "Elementname_222", xlsname),
						"Able to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_222", xlsname),
						"Unable to display the following fields in the dropdown."
								+ excelutil.getData("Rules", "Elementname_222", xlsname));

				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to display as " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_10", xlsname),
						"Able to click  on " + excelutil.getData("Rules", "elementname_10", xlsname),
						"Unable to click on  " + excelutil.getData("Rules", "elementname_10", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "elementname_12", xlsname),
						"Able to click a dropdown with a place holder 'Select a Condition' with label as Define Condition",
						"Unable to click a dropdown with a place holder 'Select a Condition' with label as Define Condition");

				sendkeys(rules.textFolderName, excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to enter the value" + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to enter the value" + excelutil.getData("Rules", "Elementname_224", xlsname));

				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_224", xlsname));
				waitforelement(shortwaitvalue);
				sequencepage.elementDisplay(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_224", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_224", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_224", xlsname));
				waitforelement(shortwaitvalue);

				sequencepage.elementTextClick(excelutil.getData("Rules", "elementname", xlsname),
						excelutil.getData("Rules", "Elementname_241", xlsname),
						"Able to display as " + excelutil.getData("Rules", "Elementname_241", xlsname),
						"Unable to display  as " + excelutil.getData("Rules", "Elementname_241", xlsname));
				waitforelement(shortwaitvalue);

				rules.Datecheck(rules.textFolderName, 30, "Able to click", "Unable to click ");

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