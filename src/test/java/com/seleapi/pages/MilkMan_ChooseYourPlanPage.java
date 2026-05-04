package com.seleapi.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.seleapi.base.TestBase;

interface MilkMan_ChooseYourPlanPageInterface {

}

public class MilkMan_ChooseYourPlanPage extends TestBase implements MilkMan_ChooseYourPlanPageInterface {
	WebDriver driver;

	public By titleChooseYourPlan = By.xpath("//h1[contains(text(),'Choose your plan')]");
	public By radioButtonMonthly = By.xpath("//p[contains(text(),'Monthly')]");
	public By radioButtonAnnual = By.xpath("//p[contains(text(),'Annual')]");
	public By radioButtonWeekly = By.xpath("//p[contains(text(),'Weekly')]");
	public By buttonCheckOut = By.xpath("//button[contains(text(),'Checkout')]");
	public By textWelcomeRound = By.xpath("//h2[contains(text(),'Welcome to The Round')]");
	public By buttonOkay = By.xpath("//button[contains(text(),'Okay')]");

}
