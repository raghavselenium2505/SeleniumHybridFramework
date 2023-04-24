package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;

public class EditOrganizationPage extends TestBase {
	WebDriver driver;
	public By textFirstName=By.xpath("//input[@name='first_name']");
	public By editEmail=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[7]/button[1]");
	public By orgName=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[2]/div/div/div[1]/div/div[1]/div/input");
	public By addressName=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[2]/div/div/div[3]/div/div[1]/div/input");
	public By tabSpoc=By.xpath("/html/body/div[2]/div[3]/div/div/div/div[1]/div[1]/div/div/div/button[2]");
	public By buttonSubmit=By.xpath("//button[@class='sc-furwcr jlMgdK MuiLoadingButton-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButtonBase-root sc-fbyfCU foETEY sc-bXTejn iBXESR sc-ctqQKy hAxrvw']");

	
	public EditOrganizationPage(WebDriver driver) {
		this.driver = driver;
	}
}

