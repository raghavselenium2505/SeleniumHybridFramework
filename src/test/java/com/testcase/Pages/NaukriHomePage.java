package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface homepage{
	public void isdisplayed(By element,String value,String value1);
}
	


public class NaukriHomePage extends TestBase implements homepage



{
	WebDriver driver;
	public By profileClick = By.xpath("//img[@class='nI-gNb-icon-img']");

	public By viewprofile=By.xpath("//a[@class='nI-gNb-info__sub-link']");
	public By profilesummary=By.xpath("/html/body/div[3]/div/div/span/div/div/div/div/div/div[2]/div[3]/div[9]/div/div/div/div[1]/span[2]");
public By profileText=By.xpath("//textarea[@id='profileSummaryTxt']");
	public By saveButton=By.xpath("//button[text()='Save']");
	public By texttoday=By.xpath("//span[text()='Today']");
public NaukriHomePage(WebDriver driver2) {
	this.driver = driver;
	}


@Override
public void actionclick(WebElement element, String value, String failurevalue) {
	// TODO Auto-generated method stub

	super.actionclick(element, value, failurevalue);
}


@Override
public void isdisplayed(By element,String value,String value1) {
	 elementhighlight(driver.findElement(element));
	  if(driver.findElement(element).isDisplayed())
	  {
			test.log(LogStatus.PASS, value);	

	  }
 else
 {
		test.log(LogStatus.FAIL, value1);	}

	  
		
 }


}
