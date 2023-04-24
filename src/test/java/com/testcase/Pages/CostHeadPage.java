package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CostHeadPage extends TestBase{
	
	WebDriver driver;

	public By titleCostHeads = By.xpath("//h2[contains(text(),'Add Cost Heads')]");
	public By buttonCostHead=By.xpath("//span[@class='sc-bBHxTw iDxzmK MuiButton-startIcon MuiButton-iconSizeMedium']");
    public By dropDownCostProfile=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div/div/div");//this element dynamicaly change
    public By dropdownChargeType=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[2]/div/div/div");
    public By dropdownCurrency=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[3]/div/div/div");
    public By textCost=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[4]/div/div/input");
    public By buttonSubmit=By.xpath("//button[@class='sc-furwcr jlMgdK MuiLoadingButton-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root sc-fKVqWL eZfENA sc-bXTejn klXREA sc-ctqQKy hAxrvw']");
	 
  
	
	public void dropdownDynamic(String value)
	{
		
		actionCilck(driver.findElement(By.xpath("//li[contains(text(),"+ "'"+value +"'"+")]")), "clicked on"+value, "unable to click on value");
	}
	
	
	public void valueCheck(String value,String passValue,String failureValue)
	{
try {
		if(driver.findElement(By.xpath("//td[contains(text(),"+ "'"+value +"'"+")]")).isDisplayed())
		
			{
		test.log(LogStatus.PASS,passValue);
		
			}	

	} catch (Exception e) {
		test.log(LogStatus.FAIL, failureValue+ e);

	}

	}
	
	
	
	public CostHeadPage(WebDriver driver){
        this.driver = driver;
    }

}
