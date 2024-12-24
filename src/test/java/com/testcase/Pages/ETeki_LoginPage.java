package com.testcase.Pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
//import org.testng.Assert;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;

interface VerifyInput {
	public void verifyInput(Boolean getValue, String passValue, String failValue);

	public void verifyTitle(String verifyTitle, String passValue, String failVallue);
	
	public void login(String username, String password, String usernamePassValue, String usernameFailValue, String passwordPassValue, String passwordFailValue);
	
	public String futureDate(int futurevalue);
}

public class ETeki_LoginPage extends TestBase implements VerifyInput {
	
	public String dashboardURL = "https://staging.eteki.com/admin/pending_activities";
	
	public String messageValidation = "Invalid email or password.";
	
	public String usernamevalidationmessage = "Email can't be blank";
	
	public String passwordvalidationmessage = "Password can't be blank";

	public By inputUsername = By.id("username");
	
	public By validationUsername = By.xpath("//span[@class='help-block'][contains(text(), 'Email')]");

	public By inputPassword = By.id("password");
	
	public By validationPassword = By.xpath("//span[@class='help-block'][contains(text(), 'Password')]");

	public By buttonSignIn = By.xpath("//button[@type='submit']");
	
	public By validationmessage = By.xpath("//div[contains(text(),'Invalid email or password.')]");

	@Override
	public void verifyInput(Boolean getValue, String passValue, String failValue) {
		try {
			if (getValue) {
				test.log(LogStatus.PASS, passValue);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, e);

		}
	}

	@Override
	public void verifyTitle(String verifyTitle, String passValue, String failValue) {
		try {
			if (driver.getCurrentUrl().equals(verifyTitle)) {
				test.log(LogStatus.PASS, passValue);
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, e);

		}
	}

	@Override
    public void login(String username, String password, String usernamePassValue, String usernameFailValue, String passwordPassValue, String passwordFailValue) {
        
        sendkeys(inputUsername, username, usernamePassValue, usernameFailValue);
        
        waitforelement(mediumwaitvalue);

        sendkeys(inputPassword, password, passwordPassValue, passwordFailValue);
        
        waitforelement(mediumwaitvalue);
        
        SubmitClick(buttonSignIn, "User able to click on Sign IN button.", "User unable to click on Sign IN button.");

        waitforelement(mediumwaitvalue);

    }

	
	@Override
	public String futureDate(int futurevalue) {
				
		 // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Add 10 days to the current date
        LocalDate newDate = currentDate.plusDays(futurevalue);

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        // Format the new date
        String formattedDate = newDate.format(formatter);

        // Print the result
        
        return formattedDate;
	}
}
