package com.gps.pages;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface pipelinesDashboardPage {
	public void selectPipeline(String colName);

	public void checkUpdatedStatusNames();

	public void checkUpdatedStatusNamesInTable();
}

public class GPS_PipelinesDashboardPage extends TestBase implements pipelinesDashboardPage {

	public By pipelineDropdownExpand = By.xpath(
			"//p-dropdown[contains(@placeholder,'Select a Pipeline')]//span[contains(@class,'dropdown-trigger')]");
	public By pipelineList = By.xpath("//p-dropdownitem//span");
	public By pipelineOpprCountToTable = By.xpath("//*[name()='g' and @role='menuitem']");
	// public By beginText=By.xpath("//text()[contains(.,'begin')]/parent::*");
	public By beginText = By.xpath("//*[name()='g' and @aria-label='begin']");
	public By proceedText = By.xpath("//*[name()='g' and @aria-label='Proceed']");
	public By goneText = By.xpath("//*[name()='g' and @aria-label='gone']");
	public By rejectedText = By.xpath("//*[name()='g' and @aria-label='rejected']");
	public By tableStatus = By.xpath("//td[@tooltipposition='top']");

	@Override
	public void selectPipeline(String colName) {
		click(pipelineDropdownExpand, "Able to click on pipeline dropdown", "Unable to click on pipeline dropdown");

		List<WebElement> pipelineslist = WaitUtils.waitVisibilityOfDropdownElements(pipelineList, 20);

		for (WebElement pipelineName : pipelineslist) {
			String pname = excelutil.getData("PipelinesDashboard", colName, xlsname);
			if (pipelineName.getText().equals(pname)) {
				pipelineName.click();
				test.log(LogStatus.PASS, "Able to select "+pname+ " from pipeline dropdown");
				break;
			}

		}

		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	}

	@Override
	public void checkUpdatedStatusNamesInTable() {
		String firstName = GPS_SettingsPipelinesPage.firstStatusName;
		String secondName = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdName = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthName = GPS_SettingsPipelinesPage.fourthStatusName;

		List<String> validNames = Arrays.asList(firstName, secondName, thirdName, fourthName);

		click(pipelineOpprCountToTable, "Able to click on pipeline opportunities count",
				"Unable to click on pipeline opportunities count");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		List<WebElement> l1 =WaitUtils.waitVisibilityOfDropdownElements(tableStatus,20);
		Set<String> s1 = new HashSet<>();

		for (WebElement ele : l1) {
			String statusName = ele.getText();
			logger.info("Table elements " + statusName);

			for (String validName : validNames) {
				// logger.info("valid names are "+validName);
				if (statusName.equals(validName)) {
					s1.add(statusName);
					test.log(LogStatus.PASS, statusName);
				}
			}
			if (validNames.size() == s1.size()) {
				break;
			}

		}

		logger.info("Set is " + s1);
		if (validNames.size() == s1.size()) {
			test.log(LogStatus.PASS, "Updated status names are displayed in table");
		}

		else {
			test.log(LogStatus.FAIL, "Updated status names are NOT displayed in table");
		}

	}

	@Override
	public void checkUpdatedStatusNames() {
		assertTrue(WaitUtils.waitClickByRef(beginText).getText()
				.equals(excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname)));
		test.log(LogStatus.PASS,
				excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName1", xlsname) + " is displayed beside donut graph");

		//isdisplay(proceedText, "Proceed status name is displayed", "Updated status name is not reflected");
		assertTrue(WaitUtils.waitClickByRef(proceedText).getText()
				.equals(excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName2", xlsname)));
		test.log(LogStatus.PASS,
				excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName2", xlsname) + " is displayed beside donut graph");

		//isdisplay(goneText, "Gone status name is displayed", "Updated status name is not reflected");
		assertTrue(WaitUtils.waitClickByRef(goneText).getText()
				.equals(excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName3", xlsname)));
		test.log(LogStatus.PASS,
				excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName3", xlsname) + " is displayed beside donut graph");

		//isdisplay(rejectedText, "Reject status name is displayed", "Updated status name is not reflected");

		assertTrue(WaitUtils.waitClickByRef(rejectedText).getText()
				.equals(excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName4", xlsname)));
		test.log(LogStatus.PASS,
				excelutil.getData("Settings_PipelinesTab", "ModifiedStatusName4", xlsname) + " is displayed beside donut graph");
		test.log(LogStatus.PASS, "Updated status names are reflected");
	}

}
