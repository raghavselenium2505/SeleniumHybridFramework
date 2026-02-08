package com.gps.pages;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_CreateOpportunity {

	public void KeyRelease(By elementpath, String passvalue, String failValue) throws AWTException;

	public void calendarDateSelection(By Year, By Month, By Right, String yearValue, String monthValue, String dayValue,
			String passvalue, String failValue);
	public void checkUpdatedStatusNames();

}

public class GPS_CreateOpportunityPage extends TestBase implements GPS_CreateOpportunity {

	public By selectCreateOpportunity = By.xpath("//span[text()='Create Opportunity']");

	public By validatationCreateOpportunity = By.xpath("//h3[text()='Create Opportunity']");

	public By validatationContactInformation = By.xpath("//h4[text()='Contact Information']");

	public By validatationContactName_Opportunity = By.xpath("//label[text()='Contact Name*']");

	public By validatationBusinessName_Opportunity = By.xpath("//label[text()='Business Name']");

	public By validatationEmail_Opportunity = By.xpath("//label[text()='Email']");

	public By validatationPhoneNumber_Opportunity = By.xpath("//label[text()='Phone Number']");

	public By validatationExtension_Opportunity = By.xpath("//label[text()='Extension']");

	public By validatationTags_Opportunity = By.xpath("//label[text()='Tags']");

	public By validatationOpportunityInformation = By.xpath("//h4[text()='Opportunity Information']");

	public By validatationOpportunityName_Opportunity = By.xpath("//label[text()='Opportunity Name*']");

	public By validatationPipeline_Opportunity = By.xpath("//label[text()='Pipeline*']");

	public By validatationStage_Opportunity = By.xpath("//label[text()='Stage*']");

	public By validatationLeadValue_Opportunity = By.xpath("//label[text()='Lead Value']");

	public By validatationAssignedUser_Opportunity = By.xpath("//label[text()='Assigned User']");

	public By validatationSource_Opportunity = By.xpath("//label[text()='Source']");

	public By validatationStatus_Opportunity = By.xpath("//label[text()='Status*']");

	public By buttonSave_Opportunity = By.xpath("//span[text()='Save']");

	public By buttonCancel_Opportunity = By.xpath("//span[text()='Cancel']");

	public By textopportunityName = By.id("opportunityName");

	public By textLeadValue_Opportunity = By.xpath("//input[@placeholder='Enter Lead Value']");

	public By textSource_Opportunity = By.xpath("//input[@placeholder='Enter Source']");

	public By pipelineClearIcon = By.xpath("//i[contains(@class,'ui-dropdown-clear-icon')]");

	public By validationOpportunityNameRequired_Opportunity = By
			.xpath("//span[text()=' Opportunity Name is required. ']");

	public By validationPipelineRequired_Opportunity = By.xpath("//span[text()=' Pipeline is required. ']");

	public By validationStageRequired_Opportunity = By.xpath("//span[text()=' Stage is required. ']");

	public By validationStatusRequired_Opportunity = By.xpath("//span[text()=' Status is required. ']");

	public By selectAddAppointment_Appointment = By.xpath("//span[text()='Add Appointment']");

	public By labelCalendar_Appointment = By.xpath("//label[text()='Calendar *']");

	public By labelDay_Appointment = By.xpath("//label[text()='Day']");

	public By labelSlot_Appointment = By.xpath("//label[text()='Slot *']");

	public By labelMeetingLocation_Appointment = By.xpath("//label[text()='Meeting Location']");

	public By labelAdditionalNotes_Appointment = By.xpath("//label[text()='Additional Notes']");

	public By cancelCalendar_Appointment = By
			.xpath("//p-dropdown[@placeholder='Select Calendar']//i[contains(@class,'ui-dropdown-clear-icon')]");

	public By calendarDropdown_Appointment = By.xpath(
			"//p-dropdown[@placeholder='Select Calendar']//span[@class='ui-dropdown-trigger-icon ui-clickable pi pi-chevron-down']");

	public By cancelSlot_Appointment = By
			.xpath("//i[@class='ui-dropdown-clear-icon pi pi-times ng-tns-c8-754 ng-star-inserted']");

	public By textselectCaledra_Appointment = By
			.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By selectCalendraDropdown_Appointment = By.xpath("//span[@class='ng-star-inserted']");

	public By textSlot_Appointment = By.xpath("//span[text()='Select a Slot']");

	public By appointment_Day = By.id("day");
	
	public By calendarMonth_YearButton = By.xpath("//span[@class='ui-datepicker-year ng-tns-c23-89 ng-star-inserted']");

	public By calendarDaySelection = By.xpath("//span[text()='February']");

	public By textMeetingLocation_Appointment = By.id("meetingLocation");

	public By textAdditionalNotes_Appointment = By.id("additionalNotes");

	public By buttonSave_Appointment = By.xpath("//span[text()='Save']");

	public By buttonCancel_Appointment = By.xpath("//span[text()='Cancel']");

	public By validationCalendarRequired_Appointment = By.xpath("//span[text()=' Calendar is required. ']");

	public By validationSlotRequired_Appointment = By
			.xpath("//span[text()='There are no slots available for the selected day']");

	public By selectTasks_Tasks = By.xpath("//span[text()='Tasks']");

	public By labelTitle_Tasks = By.xpath("//label[text()='Title*']");

	public By labelDescription_Tasks = By.xpath("//label[text()='Description']");

	public By labelDueDate_Tasks = By.xpath("//label[text()='Due Date*']");

	public By labelAssignedTo_Tasks = By.xpath("//label[text()='Assigned to*']");

	public By buttonSave_Tasks = By.xpath("//span[text()='Save']");

	public By buttonCancel_Tasks = By.xpath("//span[text()='Cancel']");
	
	public By iconCancel_Tasks = By.xpath("//div[@class='ui-dialog-titlebar-icons']");	


	public By textTitle_Tasks = By.id("title");

	public By textDescription_Tasks = By.id("description");

	public By selectAssignedTo_Tasks = By.xpath("//span[text()='Select a User']");

	public By searchAssignedTo_Tasks = By
			.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By selectAssignedToDropdown_Tasks = By.xpath("//li[@role='option']//span[@class='ng-star-inserted']");

	public By validationTitleRequired_Tasks = By.xpath("//span[text()=' Title is required. ']");

	public By validationDueDateRequired_Tasks = By.xpath("//span[text()=' Due Date is required. ']");

	public By validationAssignedToRequired_Tasks = By.xpath("//span[text()=' Assigned to is required. ']");

	public By selectNotes_Notes = By.xpath("//span[text()='Notes']");

	public By labelNote_Notes = By.xpath("//label[text()='Note']");

	public By validationNoteRequired_Notes = By.xpath("//span[text()=' Note is required. ']");

	public By textNote_Notes = By.id("note");

	public By buttonSave_Notes = By.xpath("//span[text()='Save']");

	public By buttonCancel_Notes = By.xpath("//span[text()='Cancel']");

	public By buttonEdit_Notes = By.xpath("//a[@class='icon icon-edo-edit']");

	public By buttonDelete_Notes = By.xpath("//a[@class='icon icon-edo-trash m-l-15']");

	public By buttonUpdate_Notes = By.xpath("//span[text()='Update']");

	public By buttonSubmit_Notes = By.xpath("//span[text()='Submit']");

	public By textDelete_Notes = By.id("exampleInputName");

	public By validationDeleteRequired_Notes = By.xpath("//span[text()=' Confirmation text is required. ']");

	public By validationDeletePopUP_Notes = By
			.xpath("//h3[@class='m-b-10 fs-17 fw-500 confirm-text word-break-word p-t-5']");

	public By tasks_DueDate = By.id("calendardueDate");

	public By calendarYearValue = By.xpath("//span[contains(@class, 'ui-datepicker-year')]");

	public By calendarMonthValue = By.xpath("//span[contains(@class, 'ui-datepicker-month')]");

	public By calendarRightChevron = By.xpath("//span[@class='ui-datepicker-next-icon pi pi-chevron-right']");

	public By calendarLeftChevron = By.xpath("//span[@class='ui-datepicker-prev-icon pi pi-chevron-left']");

	//Sphurthi
	public By selectStatusDDExpandFromOpporPopup=By.xpath("//p-dropdown[contains(@placeholder,'Select Status')]//span[contains(@class,'trigger')]");
	public By statusListFromDropdown=By.xpath("//ul[contains(@class,'dropdown-items')]//span");
	public By cancelButton = By.xpath("//span[text()='Cancel']");
	
	//Raghavendra
	
	public By textSearch=By.xpath("//input[@class='form-control ng-pristine ng-valid ng-touched']");
	
	@Override
	public void KeyRelease(By element, String passvalue, String failValue) throws AWTException {
		WebElement textBox = driver.findElement(element);
		textBox.clear();
		Robot robot = new Robot();
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);

	}

	@Override
	public void calendarDateSelection(By Year, By Month, By RightChevron, String yearValue, String monthValue,
			String dayValue, String passvalue, String failValue) {
		
		Boolean dayselectionflag = true;

		do {

			if (driver.findElement(Year).getText().equals(yearValue)
					&& driver.findElement(Month).getText().equals(monthValue)) {

				WebElement daystable = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody"));

				List<WebElement> daysrow = daystable.findElements(By.tagName("tr"));

				waitforelement(shortwaitvalue);

				System.out.println("days row count: " + daysrow.size());

				for (int drow = 0; drow < daysrow.size(); drow++) {

					if (dayflag == 0) {

						// For selecting days column

						List<WebElement> dayscolumn = daysrow.get(drow).findElements(By.tagName("td"));

						waitforelement(mediumwaitvalue);

						System.out.println("days column count: " + dayscolumn.size());

						// Select corresponding date in the month

						for (int dcolumn = 0; dcolumn < dayscolumn.size(); dcolumn++) {

							if (dayscolumn.get(dcolumn).getText().equals(dayValue)) {

								dayscolumn.get(dcolumn).click();

								System.out.println("day selected");

								dayflag = 1;

								break;
							}
						}
					}
				}
				
				dayselectionflag = false;

			} else {
				click(RightChevron);
			}
		} while (dayselectionflag);
	}
	
	//Sphurthi
	@Override
	//To check status names in create opportunity popup
	public void checkUpdatedStatusNames()
	{
		
		   String firstName=GPS_SettingsPipelinesPage.firstStatusName;
		   String secondName=GPS_SettingsPipelinesPage.secondStatusName;
		   String thirdName=GPS_SettingsPipelinesPage.thirdStatusName;
		   String fourthName=GPS_SettingsPipelinesPage.fourthStatusName;
		
      click(selectStatusDDExpandFromOpporPopup, "Able to click status dropdown in create opportunity popup", "Unable to click status dropdown in create opportunity popup");
		
		List<WebElement> l1=WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown,20);
		
		logger.info("Status dropdown has");
		//test.log(LogStatus.PASS, "Status dropdown has");
	    
	    for (WebElement ele : l1)
		{
			String statusName = ele.getText();
			logger.info(statusName);
			
			if(statusName.equals(firstName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");

			}
			
			else if(statusName.equals(secondName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");

			}
			else if(statusName.equals(thirdName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");
			}
			else if(statusName.equals(fourthName))
			{
				test.log(LogStatus.PASS, "Updated status name "+statusName+ " is reflected in status dropdown of create opportunity popup");
			}
			
			else
			{
				test.log(LogStatus.FAIL, "Status names are NOT Updated");

			}

	
		}

		click(cancelButton, "Able to click on cancel button and create opportunity popup is closed",
				"Unable to click om cancel button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

		
	}

}
