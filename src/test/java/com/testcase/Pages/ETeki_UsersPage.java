package com.testcase.Pages;

import org.openqa.selenium.By;
import com.base.web.TestBase;

public class ETeki_UsersPage extends TestBase {

	public By buttonActions = By.xpath("//button[@type='button']/following::button[7]");

	public By optionCreateJob = By.xpath("//a[text()=' Create Job']");

}
