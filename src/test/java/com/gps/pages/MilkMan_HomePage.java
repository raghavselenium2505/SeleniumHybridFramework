package com.gps.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.gps.base.TestBase;

interface MilkMan_HomePageInterface {

	public void hiddenElementDisplay(WebElement element, String passValue, String failValue);
}

public class MilkMan_HomePage extends TestBase implements MilkMan_HomePageInterface {
	WebDriver driver;

	public By buttonLogin = By.xpath("//span[contains(text(),'Sign in')]");

	@Override
	public void hiddenElementDisplay(WebElement element, String passValue, String failValue) {

		List<WebElement> elements = getDriver().findElements(buttonLogin);

		boolean isClicked = false;

		for (WebElement el : elements) {
			if (el.isDisplayed()) {
				el.click();
				test.get().log(Status.PASS, passValue);
				isClicked = true;
				break;
			}
		}

		if (!isClicked) {

		    Exception e = new Exception("Element not visible: " + buttonLogin.toString());

		    logAIFailure(e, failValue);

		    throw new RuntimeException("Element not visible: " + buttonLogin.toString(), e);
		}
	}

}
