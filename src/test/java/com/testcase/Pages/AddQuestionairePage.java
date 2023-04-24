package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddQuestionairePage {
WebDriver driver;
public By textQuestion=By.xpath("//input[@class='sc-jOxtWs sc-jQrDum bCcagP evwVWg MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall']");

public By textDescription=By.xpath("/html/body/div[4]/div[3]/div/div/div[1]/div[1]/div[2]/div/div/div/textarea[1]");
	
public By option1=By.xpath("//input[@class='sc-jOxtWs sc-eLwHnm eOLSXA MuiInput-input MuiInputBase-input']");
public By dropDownlist=By.xpath("/html/body/div[4]/div[3]/div/div/div[1]/div[2]/div/table/tbody/tr/td[3]/div/div/div/div");
public By buttonSubmit=By.xpath("//button[contains(text(),'Submit')]");
public By checkBoxRedFlag=By.xpath("//input[@class='sc-dkYRCH kFfHAC PrivateSwitchBase-input']");
public By dropDownveryrel=By.xpath("//li[contains(text(),'Has very little relevance')]");
public By dropDownsomeverrel=By.xpath("//li[contains(text(),'Somewhat Relevant')]");
public By dropDownRel=By.xpath("//li[contains(text(),'Relevant')][3]");
public By dropDownhighRel=By.xpath("//li[contains(text(),'Highly Relevant')]");
public By dropDownDetFac=By.xpath("//li[contains(text(),'Determining Factor')]");
	public AddQuestionairePage(WebDriver driver){
        this.driver = driver;
    }
}

