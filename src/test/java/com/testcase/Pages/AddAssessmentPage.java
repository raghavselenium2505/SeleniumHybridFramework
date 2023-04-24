package com.testcase.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AddAssessmentPage extends TestBase {

	WebDriver driver;
public By dropDownSelect=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[2]/div/div/div[1]/button/span");
	
	public By Captial=By.xpath("//p[contains(text(),'Capital Markets')]");
	public By value=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[3]/div/div[1]/div/input");
	public By dropDownClick=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[6]/div/div/div/div");
	public By textDescription=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[4]/div/div[1]/div/textarea[1]");
	public By dropDownSelect_Users=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[6]/div/div/div/div");
	public By dropDownSelect_Dep=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[7]/div/div/div/div");
	public By radioButtonDepartment=By.xpath("//input[@value='department']");
	public By dropDownDepartment=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[6]/div/div/div/div");
	public By radioButtonTeams=By.xpath("//input[@value='teams']");
	public By dropDownTeam=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[6]/div/div/div/div");
	public By dropDownSelect_User=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[7]/div/div/div/div");
	
	public void clickwithli(String value1,String passvalue,String failurevalue)

	{
	try {
		Actions action=new Actions(driver);
		WebElement element=driver.findElement(By.xpath("//li[text()="+ "'"+value1 +"'"+"]"));
		sleepMethod(4000);
		action.moveToElement(element).click().perform();
		test.log(LogStatus.PASS, passvalue);
		
		/*
		 * WebElement element=driver.findElement(By.xpath("//li[text()="+ "'"+value1
		 * +"'"+"]")); elementHighlight(element); element.click();
		 * test.log(LogStatus.PASS,passvalue);
		 */
	}
	catch(Exception e)
	{
		test.log(LogStatus.FAIL,failurevalue);

	}
	
	
	}
	
	
	
	
	
	
	
	
	public AddAssessmentPage(WebDriver driver){
        this.driver = driver;
    }
 
}
