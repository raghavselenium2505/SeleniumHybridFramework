package com.base.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstSelenium {
	
	public static void main(String args[])
	{
		WebDriver driver = null;
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\RaghavendraD\\git\\SeleniumHybridFramework\\src\\test\\resources\\executables\\chromedriver.exe");
		/*
		 * driver=new ChromeDriver(); //driver.get("https://www.amazon.com/");
		 * driver.navigate().to("https://www.amazon.com/");
		 * 
		 * //driver.navigate().forward(); driver.navigate().back();
		 * driver.navigate().refresh();
		 */
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
//		logger.info("browser launched" + config.getProperty("browser"));
		
	try {
		Thread.sleep(42000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
