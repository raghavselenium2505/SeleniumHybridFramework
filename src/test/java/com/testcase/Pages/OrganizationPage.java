package com.testcase.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.web.TestBase;

public class OrganizationPage extends TestBase {
	WebDriver driver;

	public By buttonAddOrganization = By.xpath("//button[contains(text(),'Add Organization')]");
	
	public By buttonDelete=By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td[7]/button[2]");
									
public By buttonConfirm=By.xpath("//button[contains(text(),'Confirm')]");
	
	
	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
	}

}
