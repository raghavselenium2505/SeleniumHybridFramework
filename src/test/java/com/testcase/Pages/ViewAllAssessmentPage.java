package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class ViewAllAssessmentPage extends TestBase {

	WebDriver driver;
	public By addAssessment=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/button");
public By dropDownSelectAssesement=By.xpath("//button[contains(text(),'Select Industry Ontolo...')]");
public By dropDownSelect=By.xpath("/html/body/div[2]/div[3]/div/div/form/div/div[2]/div/div/div[1]/button/span");
public By view=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[1]/div[2]/div[3]/button");
public By status_Complete=By.xpath("//span[contains(text(),'Completed')]");
public By buttonSubmit=By.xpath("//button[@id='submit-assessment-answered-questions_button']");
public By buttonSubmitQuestionaire=By.xpath("//button[contains(text(),'Submit Detailed Questionnaire')]");
public By buttonConfirm=By.xpath("//button[contains(text(),'Confirm')]");
public By buttonStartAssessment=By.xpath("//button[contains(text(),'Start Assessment')]");
public By view_FlowChart=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[2]/div[2]/div[3]/button");
public By radioTemplate=By.xpath("//button[contains(text(),'Use Template')]");
public By Captial=By.xpath("//p[contains(text(),'Capital Markets')]");
public By togoleWorkFlow=By.xpath("//input[@name='toggle_is_workflow']");
public By buttonCreateWorkflow=By.xpath("//button[text()='Create workflow']");
public By ischeckPrimary=By.xpath("//input[@id='is_primary']");
public By buttonOk=By.xpath("//button[text()='OK']");
public By workflowcheck=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[7]/div/div/div[1]/div[1]/div/div[1]/div[2]/div/canvas");
public By buttonSaveWorkFlow=By.xpath("//button[text()='Save workflow nodes']");
public By toggleFlowchart=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[2]/span[2]/span[1]/input");
public By clickProcess=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[6]/div/div/div[1]/div[1]/div/div[2]/div[3]/div/canvas");
public By textOrg_id=By.xpath("//div[@id='org_application_id']");
public By dropDownClick=By.xpath("/html/body/div[2]/div[3]/div/div[1]/div/div/div[4]/div/div");
public By activityTime=By.xpath("//input[@name='activity_time']");
public By waitTime=By.xpath("//input[@name='wait_time']");
public By checkboxrework=By.xpath("//input[@name='is_rework_prone']");
public By textRework=By.xpath("//input[@name='rework_percentage']");
public By buttonSubmit_Flowchart=By.xpath("//button[text()='Submit']");
public By buttonPublish=By.xpath("//button[text()='Publish']");
public By buttonOk_Flowchart=By.xpath("//button[text()='Ok']");
public By dropDownClick_1=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[6]/div/div/div[1]/div[1]/div/div[3]/div[3]/div/canvas");
public By checkInvolvesDoc=By.xpath("//span[contains(text(),'Involves Document Processing')]");
public By dropDownCheck_1=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div/div[8]/div/div/div");
public By checkboxAll=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div/div/table/thead/tr/th[1]/span/input");
public By buttonAssign=By.xpath("//button[text()='Assign']");
public By dropDownCheck=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[2]/div/div");
public By textUsers=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[3]/div/div/div/input");
public By textDate=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[4]/div/div/input");
public By textDate_1=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[3]/div/div/input");
public By buttonAssign_1=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[5]/button[1]");
public By buttonAssign_2=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[4]/button[1]");
public By buttonTeamselect=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[2]/div/div/div/input");
public By radioTeam=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div/label[2]/span[1]/input");
public By statusSelect=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div/div[1]/div/div/div");
public By buttonSearch=By.xpath("//button[text()='Search']");
public By AddPipeline=By.xpath("//button[text()='Add to Pipeline']");
public By buttonYes=By.xpath("//button[text()='Yes']");
public By textCompleted=By.xpath("//div[text()='Detailed Questionnaire']/../div[2]/div[1]/div/span[text()='Completed']");
public By texCompleted_1=By.xpath("//div[text()='Flowchart']/../div[2]/div[1]/div/span[text()='Completed']");
public By buttonGeneate=By.xpath("//button[text()='Generate']");
public By submitPreprojectPlan=By.xpath("//button[text()='Submit Pre Project Plan']");
public By buttonBack=By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/button");
public By buttonGeneratePlan=By.xpath("//button[text()='Generate Plan']");
public By buttongoToProjectPlan=By.xpath("//button[text()='Go To Project Plan']");



public void isDisplayed(String value1,String passvalue,String failurevalue)
{
elementHighlight(driver.findElement(By.xpath("//th[text()="+ "'"+value1 +"'"+"]//following::td[5]")));
	sleepMethod(6000);
	if(driver.findElement(By.xpath("//th[contains(text(),"+ "'"+value1 +"'"+")]")).isDisplayed())
	{

	driver.findElement(By.xpath("//th[contains(text(),"+ "'"+value1 +"'"+")]//following::td[5]")).click();
	}
	else
	{
		test.log(LogStatus.FAIL, failurevalue );
	}
	
	//th[text()='test 67']/../td[5]
	
	
}

public void clickDisplay(String value1,String passvalue,String failurevalue)
{
elementHighlight(driver.findElement(By.xpath("//th[text()="+ "'"+value1 +"'"+"]/../td[5]")));
	sleepMethod(6000);
	if(driver.findElement(By.xpath("//th[text()="+ "'"+value1 +"'"+"]/../td[5]")).isDisplayed())
	{

	driver.findElement(By.xpath("//th[text()="+ "'"+value1 +"'"+"]/../td[5]")).click();
	}
	else
	{
		test.log(LogStatus.FAIL, failurevalue );
	}
	
	//th[text()='test 67']/../td[5]
	
	
}


public void clickwithIndexDisplay(String value1,String passvalue,String failurevalue)
{
elementHighlight(driver.findElement(By.xpath("//th[text()="+ "'"+value1 +"'"+"]/../td[4]")));
	sleepMethod(6000);
	if(driver.findElement(By.xpath("//th[text()="+ "'"+value1 +"'"+"]/../td[4]")).getText().contains("Completed"))
	{

		test.log(LogStatus.PASS, passvalue );	}
	else
	{
		test.log(LogStatus.FAIL, failurevalue );
	}
	
	
}
public void questionCheck(String value1,String passvalue,String failvalue)
{
	try {
if(driver.findElement(By.xpath("//span[contains(text(),"+ "'"+value1 +"'"+")]")).isDisplayed())
	
{

	driver.findElement(By.xpath("//span[contains(text(),"+ "'"+value1 +"'"+")]")).click();
	test.log(LogStatus.PASS, passvalue );

}
	}
	catch(Exception e)
	{
		test.log(LogStatus.PASS, failvalue);

	}
	
}
public void ValueAdded(String value1,String passvalue,String failvalue)
{
	try {
if(driver.findElement(By.xpath("//li[contains(text(),"+ "'"+value1 +"'"+")]")).isDisplayed())
	
{

	driver.findElement(By.xpath("//li[contains(text(),"+ "'"+value1 +"'"+")]")).click();
	test.log(LogStatus.PASS, passvalue );

}
	}
	catch(Exception e)
	{
		test.log(LogStatus.PASS, failvalue);

	}
	
}

public void keyListArrowDownEnter()
{
	
    new Actions(driver)
            .sendKeys(Keys.ARROW_DOWN);
    new Actions(driver)
    .sendKeys(Keys.ENTER);

}public ViewAllAssessmentPage(WebDriver driver){
        this.driver = driver;
    }
 
}
