package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;

public class QuestionairePage extends TestBase{
	
	WebDriver driver;
	public By buttonAddQuestionaire=By.xpath("//button[contains(text(),'Add Question')]");
public By buttonEdit=By.cssSelector(".sc-lkgTHX:nth-child(1) > .sc-jlRLRk > .sc-furwcr > .sc-iqseJM > path");
	
	
	
	public QuestionairePage(WebDriver driver){
        this.driver = driver;
    }
}