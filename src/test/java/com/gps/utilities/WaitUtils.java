package com.gps.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;




	public class WaitUtils extends TestBase{
		
		public static By spinner = By.xpath("//div[contains(@class,'spinner')]");

		
		public static void waitInvisibleSpinner(By ref, int timeInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(ref));

		}
		

		public static List<WebElement> waitVisibilityOfDropdownElements(By ref, int timeInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ref));

		}
		
		public static void waitInVisibleElement(By ref, int timeInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(ref));

		}


		
		public static WebElement waitClickByRef(By ref, int timeInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			return wait.until(ExpectedConditions.elementToBeClickable(ref));

		}

		
		public static WebElement waitClickWebElement(WebElement ele, int timeInSec) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			return wait.until(ExpectedConditions.elementToBeClickable(ele));

		}

		
		public static WebElement waitVisibilityByRef(By ref, int timeInSec) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(ref));
		}

		
		public static WebElement waitClickByRef(By ref) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			return wait.until(ExpectedConditions.elementToBeClickable(ref));
		}

		
		
		public static void waitInvisibleSpinner(By ref) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(ref));

		}

		
		public static WebElement waitVisibilityByRef(By ref) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(ref));
		}

		
		//waits till the by locator contains given text(partial text)
		public static void waitTextIsPresent(By ref,String textInput) 
		{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.textToBePresentInElementLocated(ref, textInput));
		
		}
		
		public static WebElement click(By ref1,By ref2)
		{
			WebElement ele=null;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			try
			{
			
			 ele=wait.until(ExpectedConditions.elementToBeClickable(ref1));
			 ele.click();
			 logger.info("click worked");
			
			}
			catch(ElementClickInterceptedException | StaleElementReferenceException e)
			{
				try {
				ele=wait.until(ExpectedConditions.elementToBeClickable(ref1));
				Actions act=new Actions(driver);
				act.moveToElement(ele).click().perform();
				
				wait.until(ExpectedConditions.elementToBeClickable(ref2));
				logger.info(e.getMessage());
				logger.info("Action click worked");
				
				}
				catch(Exception e1)
				{
					try {
					ele=wait.until(ExpectedConditions.elementToBeClickable(ref1));
					JavascriptExecutor js=(JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", ele);
					
					logger.info("JS click worked");
					}
					catch(Exception e2)
					{
						logger.info(e1.getMessage());
						test.log(LogStatus.FAIL, "Unable to click" + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
						+ ">click here for screenshot</p></body></html>");

				e.printStackTrace();
					}
				}
				
				
			}
			
			return ele;
			
		}

		
	  
	}



