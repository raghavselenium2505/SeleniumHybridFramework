package com.testcase.testsuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.JiraCreateIssue;
import com.base.web.TestBase;
import com.testcase.Pages.ParabankHomePage;

public class ParabankAdminPage extends TestBase {
	
	
	static int var;

	 @JiraCreateIssue(isCreateIssue = true)
	@Test
	public void SignInForMagento() throws Exception {
		test = report.startTest(getData("Signin", "Jira_Story_Name", xlsname));

		String skip = getData("DashBoard", "SignInMagento", xlsname);
		try {
			if (skip.equalsIgnoreCase("N")) {
				ParabankHomePage homepage=new ParabankHomePage(driver);
				
				actionclick(driver.findElement(homepage.linkAdminPage), "hello world", "By world");
Thread.sleep(3000);
actionclick(driver.findElement(homepage.linkRegister), "hello world", "By world");


			}
			else
			{
				System.out.println("hello world");
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}
}
