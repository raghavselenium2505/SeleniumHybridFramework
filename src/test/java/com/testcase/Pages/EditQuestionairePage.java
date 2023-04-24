package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditQuestionairePage {
	WebDriver driver;
	public By textquestion=By.xpath("/html/body/div[4]/div[3]/div/div/div[1]/div[1]/div[1]/div/div/div/input");

	
	public EditQuestionairePage(WebDriver driver){
        this.driver = driver;
    }
}
