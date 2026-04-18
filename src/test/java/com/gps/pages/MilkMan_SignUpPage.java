package com.gps.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gps.base.TestBase;

interface MilkMan_SignUpInterface {
	
}

public class MilkMan_SignUpPage extends TestBase implements MilkMan_SignUpInterface {
	WebDriver driver;

    public By textFirstName=By.xpath("//input[@name='firstName']");
    public By textLastName=By.xpath("//input[@name='lastName']");
    public By textEmail=By.xpath("//input[@name='email']");
    public By textMobileNumber=By.xpath("//input[@name='phoneNumber']");
    public By textpassword=By.xpath("//input[@name='password']");
    public By dropDownSelectOption=By.xpath("//button[contains(text(),'Please select an option')]");
    public By checkBoxoptout=By.xpath("//input[@name='optOutEmail']");
    public By buttonCreateAccount=By.xpath("//button[contains(text(),'Create my account')]");
    public By titleModernMilk=By.xpath("//h1[contains(text(),'The Modern Milkman Sign Up')]");
	
	
	
	
	
}
