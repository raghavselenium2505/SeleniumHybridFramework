package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class UserListPage extends TestBase {

	WebDriver driver;

	public By buttonAddUser = By.xpath("//button[contains(text(),'Add User')]");
	public By textFirstName=By.xpath("//form/div/div/div/div/div/input");
	public By textLastName=By.xpath("//div[2]/div/div/div/input");
	public By textEmail=By.xpath("//div[3]/div/div/div/input");
	public By dropDownDepartment=By.xpath("//div[contains(text(),'HR')]");
	public By dropDownDesignation=By.xpath("//div[contains(text(),'Business Analyst')]");
	public By dropDownUserRole=By.xpath("//div[6]/div/div/div/input");
	public By buttonSubmit=By.xpath("//button[contains(text(),'Discard')]/../button[2]");
	public By buttonDelete=By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[4]/div/div[1]/table/tbody/tr[2]/td[6]/button[3]");
	public By deleteConfirm=By.xpath("//button[contains(text(),'Confirm')]");
	public By textEmailSearch=By.xpath("//input[@class='sc-jOxtWs sc-jQrDum bCcagP evwVWg MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall']");
	public By dropDownSelectUser=By.xpath("//div[@class='sc-hBUSln hGjOLE MuiBackdrop-root MuiBackdrop-invisible sc-fFeiMQ cxbxJs']");
	public By buttonSearch=By.xpath("//button[contains(text(),'Search')]");
	public By iconEdit=By.cssSelector(".dOYcdd:nth-child(1) > .sc-iqseJM");
	public By editTextEmail=By.xpath("/html/body/div[3]/div[3]/div/div/div/form/div/div[3]/div/div/input");
	public By iconDelete=By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div/div[1]/table/tbody/tr[1]/td[6]/button[3]");
	public By buttonConfirm=By.xpath("//button[contains(text(),'Confirm')]");
	public By textAnnualWorkingDays=By.xpath("//input[@id='outlined-adornment-per-year-working-days']");
	public By textDailyWorkingHours=By.xpath("//input[@id='outlined-adornment-per-day-working-hours']");
	public By textPayment=By.xpath("//input[@id='outlined-adornment-hourly-payment']");
	public By iconSetting=By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[3]/div/div[1]/table/tbody/tr[1]/td[6]/button[2]");
	public By buttonDiscard=By.xpath("//button[contains(text(),'Discard')]");
	public By EmailValidation=By.xpath("//p[contains(text(),'Email already exists')]");

	public UserListPage(WebDriver driver) {
		this.driver = driver;
	}
	public void keyListArrowDownEnter()
	{
		
        new Actions(driver)
                .sendKeys(Keys.ARROW_DOWN);
        new Actions(driver)
        .sendKeys(Keys.ENTER);

	}
	
	public void dropdownDynamic(String value,String value1,String value2)
	{
		
		try {
	driver.findElement(By.xpath("//li[contains(text(),"+ "'"+value +"'"+")]")).click();
	//	actionCilck(driver.findElement(By.xpath("//li[contains(text(),"+ "'"+value +"'"+")]")), value1, value2);
	test.log(LogStatus.PASS,value1);


			Thread.sleep(3000);
		logger.info("dropdown is  selected");
		}
		catch(Exception e)
		{

		//	test.log(LogStatus.FAIL,value2);

			logger.info("dropdown is not selected");

		}
	
		
	


}
	@Deprecated
	public void dropdownDynamicDiv(String value,String value1,String value2)
	{
		
		try {
		actionCilck(driver.findElement(By.xpath("//div[contains(text(),"+ "'"+value +"'"+")]")), value1, value2);
		System.out.println("valueakdjakdjakdj"+driver.findElement(By.xpath("//div[contains(text(),"+ "'"+value +"'"+")]")));
		System.out.println(value);
		logger.info("dropdown is  selected");
		System.out.println("ysercakye"+value);

		}
		catch(Exception e)
		{
			logger.info("dropdown is not selected");
		}
	
		
	


}	
}



