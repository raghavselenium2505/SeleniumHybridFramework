package com.testcase.Pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;




public class SampleTest   {

WebDriver driver;
@org.testng.annotations.Test
public void Test() throws Exception
{
	
	
	driver=new ChromeDriver();
	driver.get("https://staging.eteki.com/admin/");
	
	driver.manage().window().maximize();
Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@id='username']")).sendKeys("amanda.cole@mailinator.com");
	
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
	
	Thread.sleep(3000);
	
	Robot robot =new Robot();
	robot.keyRelease(KeyEvent.VK_ESCAPE);
	
	
	driver.findElement(By.xpath("//a[@class='navbar-brand']")).click();
	Thread.sleep(3000);
	
	
	driver.findElement(By.xpath("//ul[@class='nav nav-pills visible-sm visible-xs visible-md visible-lg nav-stacked nav-justified']/li[3]/a[contains(text(),'Users')]")).click();
	
	Thread.sleep(3000);
	
	
	System.out.println("text"+driver.findElement(By.xpath("//p[@class='designation-email']/../h5")).getText());
	System.out.println("value"+driver.findElement(By.xpath("//p[@class='designation-email']/../h5")).getAttribute("value"));
	
	driver.findElement(By.xpath("//button[@class='btn btn-primary dropdown-toggle']")).click();
	
	
	Thread.sleep(4000);
	
}
	
	
	
	
	
	
}
