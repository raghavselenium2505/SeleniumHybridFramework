package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends TestBase{
	
	WebDriver driver;

	public By headerDashBoard = By.xpath("//h2[contains(text(),'Dashboard')]");
	public By headerSubmitAnDashBoard=By.xpath("//button[@class='sc-furwcr jlMgdK MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeLarge MuiButton-containedSizeLarge MuiButtonBase-root sc-fKVqWL ewSOCd']");
	public By buttonAccount=By.xpath("//button[@id='navbar-user-action-item_dropdown']");
	public By linkSignoff=By.xpath("//li[contains(text(),'Sign out')]");
	public By emailText = By.xpath("//span[contains(text(),'Configuration')]");
	public By linkCostHead=By.xpath("//span[contains(text(),'Cost Heads')]");
	public By addCostHeads = By.xpath("//*[@id='root']/div[2]/div[1]/div[2]/div/div/div[1]/ul/div/div[2]/div[1]");
	public By linkUserManagement=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/div/div[1]/ul/div/div[3]/div[3]/div/span");
	public By linkquestionaire=By.xpath("//span[contains(text(),'Questionnaire')]");
	public By viewAll=By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/div/div[1]/ul/div/div[2]/div[4]/div/div/div/a/div/span");
	public By linkAssessments=By.xpath("//span[contains(text(),'Assessments')]");
	public By linkViewAllAssessment=By.xpath("//a[@href='/manage-assessment']");
											  	
	
	public By analytics = By.xpath("//span[text()='Analytics']");
public By ideaPage=By.xpath("//span[text()='Ideas']");
public By viewAll_1=By.xpath("//span[text()='View All']");
	
	public By linkUser=By.xpath("//span[contains(text(),'Users')]");
	public By linkOrganization=By.xpath("//span[contains(text(),'Organization')]");
	

	
	public void isdisplayed(By WebElement)
	{
		
		if(driver.findElement(WebElement).isDisplayed())
		{
			elementHighlight(driver.findElement(home.headerDashBoard));
			test.log(LogStatus.PASS,"Element is displayed sucesfully");

		}
		else
		{
			test.log(LogStatus.FAIL,"Element is not displayed sucesfully");
		}
	}
	
	
	


	
	
	
	
	public HomePage(WebDriver driver){
        this.driver = driver;
    }
 

}
