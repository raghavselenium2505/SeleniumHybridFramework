package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.gps.base.TestBase;

/* ================= INTERFACE ================= */

interface NaukriResumeHeadingInterface {

}

/* ================= PAGE CLASS ================= */

public class NaukriResumeHeadingPage extends TestBase implements NaukriResumeHeadingInterface {

    WebDriver driver;

    public By textResumeheader=By.xpath("//textarea[@placeholder='Enter your resume headline...']");
    public By buttonSave=By.xpath("//button[text()='Save']");
  

    
    
    
   
    
}