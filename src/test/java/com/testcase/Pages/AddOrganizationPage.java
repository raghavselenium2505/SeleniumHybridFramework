package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class AddOrganizationPage extends TestBase {
	WebDriver driver;
	public By textOrganisationName = By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[2]/div/div/div[1]/div/div[1]/div/input");

	public By textAddress=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[2]/div/div/div[3]/div/div[1]/div/input");
	public By dropDownCountry=By.xpath("//div[contains(text(),'China')]");
	public By dropDownTimezone=By.xpath("//div[contains(text(),'Africa/Abidjan')]");
	public By dropDowncategory=By.xpath("//div[contains(text(),'Banking and Financial Services')]");
	public By dropDownOrganizationType=By.xpath("//div[contains(text(),'Tenant')]");
	public By dropDownCurrency=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[2]/div/div/div[8]/div/div/div/div");
	public By dropDownPackage=By.xpath("//div[contains(text(),'Trial')]");
	public By textFirstName=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[3]/div/div/div[1]/div/div[1]/div/input");
	public By textLastName=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[3]/div/div/div[2]/div/div[1]/div/input");
	public By textDesignation=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[3]/div/div/div[3]/div/div[1]/div/input");
	public By textEmailId=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[3]/div/div/div[4]/div/div[1]/div/input");
	public By dropDownCountryCode=By.xpath("//input[contains(@value,'+1')]");
	public By textNumber=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[3]/div/div/div[5]/div/div/input");
	public By tabSpoc=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[1]/div/div/div/button[2]");
	public By buttonSubmit=By.xpath("//button[@class='sc-furwcr jlMgdK MuiLoadingButton-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root sc-fbyfCU foETEY sc-bXTejn iBXESR sc-ctqQKy hAxrvw']");
	public By buttonDelete=By.cssSelector(".sc-lkgTHX:nth-child(1) .sc-furwcr > .sc-furwcr path");
public By buttonConfirm=By.xpath("//button[contains(text(),'Confirm')]");
	public void dropdownDynamic(String value,String value1,String value2)
	{
		
		try {
			driver.findElement(By.xpath("//li[contains(text(),"+ "'"+value +"'"+")]")).click();
			test.log(LogStatus.PASS,value1);
			Thread.sleep(3000);
			logger.info("dropdown is  selected");
		}
		catch(Exception e)
		{
	logger.info("dropdown is not selected");
		}
		}
	
	public void textDisplayed(String value,String value1,String value2)
	{
		
		try {
			driver.findElement(By.xpath("//td[contains(text(),"+ "'"+value +"'"+")]")).isDisplayed();
			test.log(LogStatus.PASS,value1);

		}
		catch(Exception e)
		{
	logger.info("dropdown is not selected");
	test.log(LogStatus.FAIL,value2);

		}
		}
		

	
	public AddOrganizationPage(WebDriver driver) {
		this.driver = driver;
	}
}
