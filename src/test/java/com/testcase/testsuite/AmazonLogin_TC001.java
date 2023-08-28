package com.testcase.testsuite;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.testcase.Pages.AmazonHomePage;
import com.testcase.Pages.AmazonLoginPage;

public class AmazonLogin_TC001 extends TestBase {
	
	
	
	@Test
	public void Amazon_Login_TC001() throws Exception
	{
		test = report.startTest(getData("Signin", "Jira_Story_Name", xlsname));
		System.out.println("login to the amazon");
		AmazonHomePage home=new AmazonHomePage(driver);
	try {
		Thread.sleep(4000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		click(home.linkAccount, "able to click on link account", "unable to click ");
		AmazonLoginPage login =new AmazonLoginPage(driver);
		login.login(config.getProperty("userName"), config.getProperty("passWord"), "Able to login", "Unable to lgoin");
		Thread.sleep(4000);
	String s="N";
			
			
		if(s.equals("Y"))
		{
			  Object obj = parser.parse(new FileReader("C:\\Users\\RaghavendraD\\Desktop\\sample.json"));
		       JSONObject jsonObject = (JSONObject)obj;
		       String name = (String)jsonObject.get("name");
		     // String phonenumber = (String)jsonObject.get("phonenumber");
		    
		      driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(name);
		      Thread.sleep(5000);
		}
		else if(s.equals("N"))
		{
			  driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(getData("Signin", "Jira_Story_Name", xlsname));
		Thread.sleep(6000);	    
		}
		else
		{
			System.out.println("please configure datasheet option in config properties file");
		}
			
		
		
		
		
		
	}

}
