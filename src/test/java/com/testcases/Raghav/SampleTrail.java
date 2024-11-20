package com.testcases.Raghav;

import org.testng.annotations.Test;

import com.base.web.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.MT_HomePage;

public class SampleTrail extends TestBase{
	@Test
	public void MT_Smoke() throws Exception {
		test = report.startTest(getData("SmokeTest", "Jira_Story_Name", xlsname));

		if (getData("DashBoard", "smokeTest", xlsname).equalsIgnoreCase("N")) {
			

			
			MT_HomePage home=new MT_HomePage();
home.getTitle("value","Able to vuew the title","Unable to view the title");



click(home.headerHotels, "able to click on the header hotel", "Unable to click on hotel header");




			
		}
		
			else {
				test.log(LogStatus.INFO, "Please check the testcase  Skip condition");
				test.log(LogStatus.SKIP, "Testcase got skipped");

			}
		}

	}

	
	


