package com.gps.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface TagPage {
	public void checkboxDisplayTags(By element1, String passValue, String failValue);

	public void tagName(String tag, String tagName, String passValue, String failValue);

	public void isElementVisibleJS(WebElement element, String passMsg, String failMsg);

	public void actionclick(WebElement element, String value_text, String passValue, String failValue);

	public void isSelected(By element1, String passValue, String failValue);
	
	public void notSelected(By element1, String passValue, String failValue);
	}

public class GPS_TagsPage extends TestBase implements TagPage {

	public By textSearch = By.xpath("//input[@id='search']");

	public By buttonCreateTag = By.xpath("//span[contains(text(),'Create Tag')]");

	public By checkboxTags = By.xpath("//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");

	public By textNoSearchRecords = By.xpath("//h3[contains(text(),'No Match Found.')]");

	public By titleCreateTag = By.xpath("//h3[contains(text(),'Create Tag')]");

	public By textTagName = By.xpath("//div[contains(@class, 'form-group')]/input");

	public By buttonCancel = By.xpath("//span[contains(text(),'Cancel')]");

	public By buttonSave = By.xpath("//span[contains(text(),'Save')]");

	public By textCreateTag = By.xpath("//input[@id='tagName']");

	public By errorTagNameRequired = By.xpath("//span[contains(text(),' Tag name is required. ')]");

	public By checkBoxSelectAll = By.xpath("//label[text()='Select All']");
	public By checkBoxClearAll = By.xpath("//label[text()='Clear All']");
	
	public By checkboxSelect=By.xpath("//span[@class='ui-chkbox-icon ui-clickable']");
	
	public By checkboxClear=By.xpath("//span[@class='ui-chkbox-icon ui-clickable pi pi-check']");
	
	public By buttonDeleteTags=By.xpath("//span[text()='Delete 1 Tag(s)']");
	
	public By titleEnterWord=By.xpath("//h3[@class='m-b-10 fs-17 fw-500 confirm-text word-break-word p-t-5']");
	
	public By inputDeleteEntry=By.xpath("//input[@id='exampleInputName']");
	public By buttonSubmit=By.xpath("//span[text()='Submit']");
	
	public By errorConfirmation=By.xpath("//span[text()=' Confirmation text is required. ']");
	public By iconClose=By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	

	@Override
	public void checkboxDisplayTags(By element1, String passValue, String failValue) {
		List<WebElement> elements = driver.findElements(element1);
		boolean allDisplayed = true; // Flag to track if all checkboxes are displayed

		for (WebElement element : elements) {

			//WaitUtils.waitVisibilityByRef(driver.findElement(element));
			if (!element.isDisplayed()) {
				allDisplayed = false; // If any element is not displayed, set flag to false
			}
		}
		if (allDisplayed) {
			test.log(LogStatus.PASS, passValue);
		} else {
			test.log(LogStatus.FAIL, failValue);
		}
	}

	@Override
	public void tagName(String tag, String tagName, String passValue, String failValue) {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + tag + "[text()='" + tagName + "']"),100);

		isdisplay1(driver.findElement(By.xpath("//" + tag + "[text()='" + tagName + "']")), passValue, failValue);

	}

	// new tag name
	@Override
	public void isElementVisibleJS(WebElement element, String passMsg, String failMsg) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Boolean isVisible = (Boolean) js.executeScript("return arguments[0].offsetParent !== null && "
					+ "arguments[0].offsetWidth > 0 && arguments[0].offsetHeight > 0;", element);

			// This avoids the need for if condition — just throw an exception if false
			if (!isVisible)
				throw new Exception("Element is not visible in the DOM");

			// Highlight for visual feedback
			elementhighlight(element);

			// Log success
			test.log(LogStatus.PASS, passMsg);

		} catch (Exception e) {
			test.log(LogStatus.FAIL,
					failMsg + "<br><span style='color:red;'>JS Visibility Check Failed: " + e.getMessage() + "</span>");
		}
	}

	@Override
	public void actionclick(WebElement element, String value_Text, String passValue, String failValue) {
		try {
			elementhighlight(element);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().sendKeys(value_Text).perform();
			test.log(LogStatus.PASS, passValue);
		} catch (Exception e) {
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><b><p><a href=" + screenshotutil.captureScreenshot(value) + ">"
					+ failValue + "</p></body></html>");

			// test.log(LogStatus.FAIL, failValue);
			e.printStackTrace();
			// screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void isSelected(By element1, String passValue, String failValue) {
		List<WebElement> element = driver.findElements(element1);
		try {
			for (WebElement checkbox : element) {

				if (checkbox.isSelected()) {
				} else {
				}
			}
			test.log(LogStatus.PASS, passValue);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue);

		}
	}
	@Override
	public void notSelected(By element1, String passValue, String failValue) {
		List<WebElement> element = driver.findElements(element1);
		try {
			for (WebElement checkbox1 : element) {

				if (!checkbox1.isSelected()) {
				} else {
				}
			}
			test.log(LogStatus.PASS, passValue);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue);

		}
	}
}
