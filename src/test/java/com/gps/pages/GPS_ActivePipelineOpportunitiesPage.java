package com.gps.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_ActivePipelineOpportunities {
	
	public void KeyRelease(By element, String passvalue, String failValue) throws AWTException;
	
	public void retrieveUpdatedActivePipelineFieldValues();
	
	public void retrieveActivePipelineFieldValues();
	
	
	
}

public class GPS_ActivePipelineOpportunitiesPage extends TestBase implements GPS_ActivePipelineOpportunities {
	
	
	public By labelCreateOpportunity_ActivePipeline= By.xpath("//h3[text()='Create Opportunity']");
	
	public By labelOpportunityInformation_ActivePipeline= By.xpath("//h3[text()='Opportunity Information']");
	
	public By labelOpportunityName_ActivePipeline= By.xpath("//label[text()='Opportunity Name*']");
	
	public By labelPipeline_ActivePipeline= By.xpath("//label[text()='Pipeline*']");
	
	public By labelStage_ActivePipeline= By.xpath("//label[text()='Stage*']");
	
	public By labelLeadValue_ActivePipeline= By.xpath("//label[text()='Lead Value']");
	
	public By labelAssignedUser_ActivePipeline= By.xpath("//label[text()='Assigned user']");
	
	public By labelSource_ActivePipeline= By.xpath("//label[text()='Source']");
	
	public By labelStatus_ActivePipeline= By.xpath("//label[text()='Status*']");
	
	public By textOpportunityName_ActivePipeline= By.xpath("//input[@placeholder='Enter Opportunity Name']");
	
	public By selectPipeline_ActivePipeline= By.xpath("//span[text()='Select a Pipeline']");
	
	public By searchPipeline_ActivePipeline= By.xpath("//input[contains(@class,'ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all')]");
	
	public By selectPipelineDropdown_ActivePipeline= By.xpath("//span[@class='ng-star-inserted']");
	
	public By selectStage_ActivePipeline= By.xpath("//span[text()='Select Stage']");
	
	public By searchStage_ActivePipeline= By.xpath("//input[contains(@class,'ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all')]");

	public By selectStageDropdown_ActivePipeline= By.xpath("//span[@class='ng-star-inserted']");
	
	public By textLeadVaalue_ActivePipeline= By.xpath("//input[@placeholder='Enter Lead Value']");
	
	public By selectAssignedUser_ActivePipeline= By.xpath("//span[text()='Assign To']");
	
	public By searchAssignedUser_ActivePipeline= By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By selectAssignedUserDropdown_ActivePipeline= By.xpath("//span[@class='ng-star-inserted']");
	
	public By textSource_ActivePipeline= By.xpath("//input[@id='source1']");
	
	public By selectStatus_ActivePipeline= By.xpath("//span[text()='Select Status']");
	
	public By buttonSave_ActivePipeline= By.xpath("//span[text()='Save']");
	
	public By buttonCancel_ActivePipeline= By.xpath("//span[text()='Cancel']");
	
	public By clearIcon_ActivePipeline= By.xpath("//i[contains(@class,'ui-dropdown-clear-icon')]");
	
	public By validationOpportunityNameRequired_ActivePipeline = By.xpath("//span[text()=' Opportunity Name is required. ']");
	
	public By validationPipelineRequired_ActivePipeline = By.xpath("//span[text()=' Pipeline is required. ']");

	public By validationStageRequired_ActivePipeline = By.xpath("//span[text()=' Stage is required. ']");
	
	public By validationStatusRequired_ActivePipeline = By.xpath("//span[text()=' Status is required. ']");
	
	public By buttonDelete_ActivePipeline= By.xpath("//span[text()='Delete']");
	
	public By buttonUpdate_ActivePipeline= By.xpath("//span[text()='Update']");
	
	public By textDelete_ActivePipeline= By.xpath("//input[@id='exampleInputName']");
	
	public By xicon_ActivePipeline= By.xpath("//div[@class='ui-dialog-titlebar-icons']");
	
	public By noResultsFound_ActivePipeline= By.xpath("//li[contains(@class,'ui-dropdown-empty-message')]");
	
	
	
	@Override
	  public void retrieveActivePipelineFieldValues() {
	        try {
	            // Retrieve text from each active pipeline field
	            String opportunityName = driver.findElement(textOpportunityName_ActivePipeline).getAttribute("value").trim();
	            	           
	            String leadValue = driver.findElement(textLeadVaalue_ActivePipeline).getAttribute("value").trim();
	           
	            String source = driver.findElement(textSource_ActivePipeline).getAttribute("value").trim();
	            

	            // Log all retrieved values
	            test.log(LogStatus.INFO, "Opportunity Name: " + opportunityName);
	           	            
	            test.log(LogStatus.INFO, "Lead Value: " + leadValue);
	            
	            test.log(LogStatus.INFO, "Source: " + source);
	           
	     
	        } catch (Exception e) {
	            test.log(LogStatus.ERROR, "Failed to retrieve one or more field values from Active Pipeline: " + e.getMessage());
	        }
	    }
	  @Override
	  public void retrieveUpdatedActivePipelineFieldValues() {
	        try {
	            // Retrieve text from each active pipeline field
	            String opportunityName = driver.findElement(textOpportunityName_ActivePipeline).getAttribute("value").trim();
	            	           
	            String leadValue = driver.findElement(textLeadVaalue_ActivePipeline).getAttribute("value").trim();
	           
	            String source = driver.findElement(textSource_ActivePipeline).getAttribute("value").trim();
	            
                // Log all retrieved values
	            test.log(LogStatus.INFO, "Updated Opportunity Name: " + opportunityName);
	           
	            test.log(LogStatus.INFO, "Updated Lead Value: " + leadValue);
	            
	            test.log(LogStatus.INFO, "Updated Source: " + source);
	           
	     
	        } catch (Exception e) {
	            test.log(LogStatus.ERROR, "Failed to retrieve one or more field values from Active Pipeline: " + e.getMessage());
	        }
	    }
	  @Override
		public void KeyRelease(By element, String passvalue, String failValue) throws AWTException {
			WebElement textBox = driver.findElement(element);
			textBox.clear();
			Robot robot = new Robot();
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
	}
	
}


