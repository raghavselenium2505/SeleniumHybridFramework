package com.testcase.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AddIdeaPage extends TestBase {
	WebDriver driver;

	public By buttonAddIdea = By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/div/button");
	public By textTitle = By.xpath("/html/body/div[2]/div[3]/div/div/div/form/div/div[1]/div/div[1]/div/input");

	public By textDescription=By.xpath("/html/body/div[2]/div[3]/div/div/div/form/div/div[2]/div/div[1]/div/textarea[1]");
	public By ideasubmission=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/div/div/h2/div");
	public By submitButon=By.xpath("//button[text()='Submit']");
	public By dropDownClick=By.xpath("/html/body/div[2]/div[3]/div/div/div/form/div/div[3]/div/div[1]/button/span");
	public By ideaApproval=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div/h2/div");
	public By ideaRejection=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[3]/div/div/div/h2/div");
	public By addToPipeline=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[4]/div/div/div/h2/div");
	
	public AddIdeaPage(WebDriver driver) {
		this.driver = driver;
	}
	                               
}
