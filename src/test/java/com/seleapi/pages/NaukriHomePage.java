package com.seleapi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.seleapi.base.TestBase;

/* ================= INTERFACE ================= */

interface NaukriHomePageInterface {
}

/* ================= PAGE CLASS ================= */

public class NaukriHomePage extends TestBase implements NaukriHomePageInterface {

    WebDriver driver;

    public By buttonLogin = By.xpath("//a[contains(text(),'Login')]");
    public By titleLogin = By.xpath("//div[@class='login-layer']/div[contains(text(),'Login')]");
    public By signinGoogle=By.xpath("//span[contains(text(),'Sign in with Google')]");
    public By inputtextEmail=By.xpath("//input[@type='email']");
    public By buttonNext=By.xpath("//span[contains(text(),'Next')]");
    public By textEmailId=By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
    public By textpassword=By.xpath("//input[@placeholder='Enter your password']");
    public By buttonSubmit=By.xpath("//button[@type='submit']");

    
    
    
    
    
    
    
   
    
}