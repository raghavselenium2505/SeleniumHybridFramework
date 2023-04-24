package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ViewAllIdeaPage extends TestBase {

	WebDriver driver;
	public By rejectButton = By.xpath("//button[text()='REJECT']");
	public By buttonYes = By.xpath("//button[text()='Yes']");
	public By approveButton = By.xpath("//button[text()='APPROVE']");
	public By textRejected = By.xpath("//span[text()='Rejected']");
	public By textApprove = By.xpath("//span[text()='Approved']");
	

	public void isDisplayed(String value1, String passvalue, String failurevalue) {
		elementHighlight(driver.findElement(By.xpath("//td[text()=" + "'" + value1 + "'"
				+ "]//following::td[6]/button[@class='sc-furwcr jlMgdK MuiButtonBase-root sc-pVTFL eZFsEU MuiIconButton-root MuiIconButton-sizeLarge'][1]")));
		sleepMethod(6000);
		if (driver.findElement(By.xpath("//td[text()=" + "'" + value1 + "'"
				+ "]//following::td[6]/button[@class='sc-furwcr jlMgdK MuiButtonBase-root sc-pVTFL eZFsEU MuiIconButton-root MuiIconButton-sizeLarge'][1]"))
				.isDisplayed()) {

			driver.findElement(By.xpath("//td[text()=" + "'" + value1 + "'"
					+ "]//following::td[6]/button[@class='sc-furwcr jlMgdK MuiButtonBase-root sc-pVTFL eZFsEU MuiIconButton-root MuiIconButton-sizeLarge'][1]"))
					.click();
		} else {
			test.log(LogStatus.FAIL, failurevalue);
		}
	}

	public ViewAllIdeaPage(WebDriver driver) {
		this.driver = driver;
	}

}
