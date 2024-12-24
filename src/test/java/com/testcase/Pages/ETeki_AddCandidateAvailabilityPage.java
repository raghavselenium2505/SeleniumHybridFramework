package com.testcase.Pages;

import org.openqa.selenium.By;

import com.base.web.TestBase;

public class ETeki_AddCandidateAvailabilityPage extends TestBase {

	public By slotSelection_1 = By
			.xpath("//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[14]/td[2]");

	public By slotSelection_2 = By
			.xpath("//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[18]/td[2]");

	public By slotSelection_3 = By
			.xpath("//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[2]/table/tbody/tr[22]/td[2]");
	public By successMessage = By.xpath("//p[text()='Candidate added successfully']");


	public By radioButtonCandidate = By.xpath("//label[text()=' Candidate ']");

	public By radioButtonRecruiter = By.xpath("//label[text()=' Recruiter ']");

	public By buttonSubmit = By.xpath("//button[text()='Submit']");

	public By buttonSkip = By.xpath("//button[text()='Skip Candidate Availability'][@class='btn btn-default']");

	public By buttonOk = By.xpath("//button[@class='confirm'][text()='OK']");

	public By titleCandidateAvailablity=By.xpath("//h3[contains(text(),'Provide Candidate Availability ')]");
}
