package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.web.TestBase;

interface Calendar {
	public void scheduleInterviewAlert() throws Exception;

}

public class ETeki_ScheduleInterviewPage extends TestBase implements Calendar {

	public String stringsuccessalerttext = "Interview scheduled successfully.";

	public By manualscheduling = By.xpath("//p[text()='Manual Scheduling']");

	public By selectInterviewer = By.xpath("//div[@class='interviewers-panel'][2]");

	public By buttonNext = By.xpath("//button[text()='Next ']");

	public By calendarMonth_YearButton = By
			.xpath("//button[@role='heading']/strong[contains(text()," + currentyear + ")]");

	public By calendarMonthSelection = By.xpath("//table[@class='uib-monthpicker']");

	public By calendarDaySelection = By.xpath("//table[@class='uib-daypicker']");

	public By inputHours = By.xpath("//table[@name='time']/tbody/tr[2]/td[1]/input[@placeholder='HH']");

	public By inputMinutes = By.xpath("//table[@name='time']/tbody/tr[2]/td[3]/input[@placeholder='MM']");

	public By buttonHoursIncrement = By.xpath("//table[@name='time']/tbody/tr[1]/td/a[@ng-click='incrementHours()']");

	public By inputTimeConvention = By.xpath("//table[@name='time']/tbody/tr[2]/td[6]");

	public By scheduleCountry = By.xpath("//select[@name='country'][@placeholder='Select Country']");

	public By scheduleTimeZone = By.xpath("//select[@name='time_zone']");

	public By buttonScheduleInterview = By.xpath("//button[text()='Schedule Interview']");

	public By alertText = By.xpath("//div[@class='sweet-alert showSweetAlert visible']/p");

	public By buttonOk = By.xpath("//button[@class='confirm'][text()='OK']");

	WebElement verifyScheduleInterview = driver.findElement(By.xpath("//a[@ng-click='selectParentTabs(0)']"));

	WebElement buttonTimeIncrementHours = driver
			.findElement(By.xpath("//table[@name='time']/tbody/tr[1]/td/a[@ng-click='incrementHours()']"));

	WebElement alertTextData = driver.findElement(By.xpath("//div[@class='sweet-alert showSweetAlert visible']/p"));

	@Override
	public void scheduleInterviewAlert() throws Exception {

		int j = 0, k = 1;

		while (j < k) {

			System.out.println("entered into While loop.");

			if (driver.findElement(alertText).getText().equals(stringsuccessalerttext)) {

				System.out.println("entered into While loop - IF condition.");

				waitforelement(shortwaitvalue);

				click(buttonOk, verifyScheduleInterview, "User able to click on OK button in ScheduleInterview.", "User unable to click on OK button in ScheduleInterview.");

				waitforelement(shortwaitvalue);

			} else {

				System.out.println("entered into While loop - Else condition.");

				waitforelement(shortwaitvalue);

				click(buttonOk, buttonTimeIncrementHours, "User able to click on OK button.",
						"User unable to click on OK button.");

				waitforelement(shortwaitvalue);

				click(buttonHoursIncrement, buttonTimeIncrementHours, "User able to click on Hours Increment button.",
						"User unable to click on Hours Increment button.");

				waitforelement(shortwaitvalue);

				click(buttonHoursIncrement, buttonTimeIncrementHours, "User able to click on Hours Increment button.",
						"User unable to click on Hours Increment button.");

				waitforelement(shortwaitvalue);

				click(buttonScheduleInterview, alertTextData, "User able to click on Schedule Interview.",
						"User unable to click on Schedule Interview.");

				waitforelement(mediumwaitvalue);

				k++;
			}
			j++;
		}
		// }
	}

}
