package com.testcase.Pages;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.base.web.TestBase;

interface propetiesfiles {
	public void login(String userName,String password);
}

public class NaukriLoginPage extends TestBase implements propetiesfiles {
	WebDriver driver;
	public By textemailid = By.xpath("//input[@id='usernameField']");

	public By textpassword=By.xpath("//input[@id='passwordField']");
	public By buttonlogin=By.xpath("//button[contains(text(),'Login')][1]");

	public NaukriLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void login(String userName,String password) {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		elementhighlight(driver.findElement(textemailid));
		sendkeys(textemailid,userName, "able to enter username in email",
				"unable to enter username in email");// config.getProperty("userName")
		elementhighlight(driver.findElement(textpassword));
		sendkeys(textpassword, password, "Able to enter password",
				"unable to enter password");//
		click(buttonlogin,"Able to click on login", "unable to click on login");
waitforelement(4000);
	}

}
