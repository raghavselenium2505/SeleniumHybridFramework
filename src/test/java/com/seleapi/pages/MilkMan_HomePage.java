package com.seleapi.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.seleapi.base.TestBase;
import com.seleapi.reporting.ExtentManager;

interface MilkMan_HomePageInterface {

	public void hiddenElementDisplay(WebElement element, String passValue, String failValue);
}

public class MilkMan_HomePage extends TestBase implements MilkMan_HomePageInterface {
	WebDriver driver;

	public By buttonLogin = By.xpath("//span[contains(text(),'Sign in')]");

	@Override
	public void hiddenElementDisplay(
	        WebElement element,
	        String passValue,
	        String failValue) {

	    try {

	        /*
	         * FETCH ALL MATCHING ELEMENTS
	         */

	        List<WebElement> elements =
	                getDriver()
	                .findElements(buttonLogin);

	        boolean isClicked = false;

	        /*
	         * CLICK ONLY VISIBLE ELEMENT
	         */

	        for (WebElement el : elements) {

	            if (el.isDisplayed()) {

	                elementhighlight(el);

	                el.click();

	                /*
	                 * REPORTING
	                 */

	                ExtentManager.pass(
	                        passValue);

	                /*
	                 * LOGGING
	                 */

	                logger.info(
	                        passValue);

	                isClicked = true;

	                break;
	            }
	        }

	        /*
	         * IF NO VISIBLE ELEMENT FOUND
	         */

	        if (!isClicked) {

	            Exception exception =
	                    new Exception(
	                            "Element not visible : "
	                            + buttonLogin);

	            /*
	             * REPORT FAILURE
	             */

	            ExtentManager.fail(
	                    failValue);

	            /*
	             * LOG FAILURE
	             */

	            logger.error(
	                    failValue,
	                    exception);

	            /*
	             * AI FAILURE LOGGING
	             */

	            logAIFailure(
	                    exception,
	                    failValue);

	            throw new RuntimeException(
	                    "Element not visible : "
	                    + buttonLogin,
	                    exception);
	        }

	    }

	    catch (Exception e) {

	        logger.error(
	                "Hidden Element Handling Failed",
	                e);

	        throw e;
	    }
	}
}
