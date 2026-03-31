package com.gps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.gps.base.TestBase;

/* ================= INTERFACE ================= */

interface NaukrilandingPageInterface {
    public void clickEditIcon(String sectionName);

}

/* ================= PAGE CLASS ================= */

public class NaukriLandingPage extends TestBase implements NaukrilandingPageInterface {

    WebDriver driver;

    public By iconBell=By.xpath("//span[@class='ni-gnb-icn ni-gnb-icn-bell']");
    public By buttonViewProfile=By.xpath("//div[@class='view-profile-wrapper']");
    public By buttonUpdateResume=By.xpath("//input[@value='Update resume']");
    public By textToday=By.xpath("//span[contains(text(),'Today')]");
    public By iconBars=By.xpath("//img[@alt='naukri user profile img']");
    public By linkLogout=By.xpath("//a[contains(text(),'Logout')]");
  

   @Override 
    public void clickEditIcon(String sectionName) {

        try {
            String xpath = "//span[text()='" + sectionName + "']"
                         + "/following-sibling::span[@class='edit icon']";

            By editIcon = By.xpath(xpath);

            elementhighlight(getDriver().findElement(editIcon));

            getDriver().findElement(editIcon).click();

            test.get().pass("Clicked edit icon for section: " + sectionName);

        } catch (Exception e) {

            test.get().fail("Unable to click edit icon for section: " 
                            + sectionName + " Exception: " + e.getMessage());

            org.testng.Assert.fail("Edit click failed for: " + sectionName);
        }
    }
    
    
    
    
    
   
    
}