package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface GPS_ScheduleAppointment {
	
}

public class GPS_ScheduleAppointmentPage extends TestBase implements GPS_ScheduleAppointment {
	
	public By labelScheduleAppointment_ScheduleAppointment= By.xpath("//span[text()='Schedule Appointment']");
	
	public By labelCalendar_ScheduleAppointment= By.xpath("//label[@for='calendar']");
	
	public By labelDay_ScheduleAppointment= By.xpath("//label[text()='Day']");
	
	public By labelSlot_ScheduleAppointment= By.xpath("//label[text()='Slot *']");
	
	public By labelMeetingLocation_ScheduleAppointment= By.xpath("//label[text()='Meeting Location']");
	
	public By labelAdditionalNotes_ScheduleAppointment= By.xpath("//label[text()='Additional Notes']");
	
	public By buttonSave_ScheduleAppointment= By.xpath("//span[text()='Save']");
	
	public By buttonClose_ScheduleAppointment= By.xpath("//span[text()='Close']");
	
	public By xCancelIcon_ScheduleAppointment= By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	
	
	
	
	
}
