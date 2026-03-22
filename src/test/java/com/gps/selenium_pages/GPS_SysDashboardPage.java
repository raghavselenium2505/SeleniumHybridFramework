package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;

interface GPS_SysDashboard {

	
}
public class GPS_SysDashboardPage extends TestBase implements GPS_SysDashboard {
	
	public By sysOrganizations = By.xpath("(//span[text()='Organizations'])[1]");
	
	public By sysLogs = By.xpath("(//span[text()='Logs'])[1]");
	
	public By sysSettings = By.xpath("(//span[text()='Settings'])[1]");

}
