package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
	WebDriver driver;
	
	
	public By ideacount = By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/div/div/h2/div");


	
	
	public DashboardPage(WebDriver driver){
        this.driver = driver;
    }
}
