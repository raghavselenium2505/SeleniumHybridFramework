package com.gps.development;

import java.time.Duration;

import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.utilities.DynamicDataProvider;


public class AmazonLogin_TC001 extends TestBase {
	
	
	
	@Test(
	    groups = "json",
	    dataProvider = "dynamicData",
	    dataProviderClass = DynamicDataProvider.class
	)
	public void jsonLoginTest(String username,
	                          String password,
	                          String search) {

	    System.out.println(username + " | " + password + " | " + search);
	}
	@Test(
	    groups = "excel",
	    dataProvider = "dynamicData",
	    dataProviderClass = com.gps.utilities.DynamicDataProvider.class
	)
	public void excelLoginTest(String username, String password, String search) {

	    System.out.println("Excel Test Running");
	    System.out.println(username + " | " + password + " | " + search);
	}}