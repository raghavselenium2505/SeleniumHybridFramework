package com.seleapi.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.seleapi.base.TestBase;

interface MilkMan_LoginPageInterface {
	
}

public class MilkMan_LoginPage extends TestBase implements MilkMan_LoginPageInterface {
	WebDriver driver;

    public By linkSignUp=By.xpath("//a[contains(text(),'Sign up')]");
	
	
	
	
	
}
