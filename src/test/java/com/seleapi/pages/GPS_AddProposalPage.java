package com.seleapi.pages;

import org.openqa.selenium.By;

import com.seleapi.base.TestBase;

interface GPS_AddProposal{
	
	
}

public class GPS_AddProposalPage extends TestBase implements GPS_AddProposal {
	
	public By labelAddProposal = By.xpath("//span[text()='Add Proposal']");

	public By labelProposalName = By.xpath("//label[text()='Proposal Name *']");
	
	public By labelProposalTemplate = By.xpath("//label[text()='Proposal Template *']");
	
	public By validationProposalNameRequired = By.xpath("//span[text()=' Proposal Name is required. ']");
	
	public By validationProposalTemplateRequired = By.xpath("//span[text()=' Proposal Template is required. ']");
	
	public By buttonSave_AddProposal = By.xpath("//span[text()='Save']");
	
	public By buttonClose_AddProposal = By.xpath("//span[text()='Close']");
	
	public By textProposalName = By.id("proposalName");
	
	public By textProposalTemplate = By.xpath("//span[text()='Select a Template']");
	
	public By searchProposalTemplate = By.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");
	
	public By selectProposalTemplate = By.xpath("//li[@class='ui-dropdown-item ui-corner-all']");
	
	
	

	
	
	
	
}
