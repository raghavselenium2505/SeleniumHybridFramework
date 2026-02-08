/**
 * 
 */
package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author RaghavendraD
 *
 */
interface homePage{
	
	public void chooseOrganzation(String organizationName,String passValue,String failValue);
	public void selectQAOrganization(String organizationName,String passValue,String failValue);

	
}



public class GPS_HomePage extends TestBase implements homePage{
	
	
	public By orgSettings = By.xpath("(//span[text()='Settings'])[1]");

		@Override
	public void chooseOrganzation(String organizationName, String passValue, String failValue) {
try {
		WaitUtils.click(By.xpath("//h3[text()='"+organizationName+"']"), By.xpath("(//span[text()='Lead Qualification'])[1]"));	
	//actionclick(driver.findElement(By.xpath("//h3[text()='"+organizationName+"']")), passValue, failValue);
				
		//test.log(LogStatus.PASS, passValue);

		WaitUtils.waitVisibilityByRef(By.xpath("//h3[text()='"+organizationName+"']"),100);

	actionclick(driver.findElement(By.xpath("//h3[text()='"+organizationName+"']")), passValue, failValue);
	
	}

catch (Exception e) {
	test.log(LogStatus.FAIL, failValue,
			"<html><body>"
					+ "<p>Error occurred while executing the script. Please check the exception below:</p>"
					+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
					+ screenshotutil.captureScreenshot(value)
					+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

	e.printStackTrace();
	screenshotutil.captureScreenshot(value);
}

		}	
	

	@Override
	public void selectQAOrganization(String organizationName,String passValue,String failValue)
	{
		try {
		WaitUtils.click(By.xpath("//h3[contains(text(),'" + organizationName + "')]/preceding::div[contains(@class,'agent-details')]"),orgSettings);
		test.log(LogStatus.PASS, passValue);
		
		}
		catch(Exception e) {

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
			e.printStackTrace();
		}
	}
	

	
	public void chooseOrganzation1(String organizationName, String passValue, String failValue) {
        try {
                WaitUtils.click(By.xpath("//h3[text()='"+organizationName+"']"), By.xpath("(//span[text()='Lead Qualification'])[1]"));    
            //actionclick(driver.findElement(By.xpath("//h3[text()='"+organizationName+"']")), passValue, failValue);
                        
                test.log(LogStatus.PASS, passValue);
            }

        catch (Exception e) {
            test.log(LogStatus.FAIL, failValue,
                    "<html><body>"
                            + "<p>Error occurred while executing the script. Please check the exception below:</p>"
                            + "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
                            + screenshotutil.captureScreenshot(value)
                            + "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

            e.printStackTrace();
            screenshotutil.captureScreenshot(value);
        }

            
            }
	
	
	
	
}
