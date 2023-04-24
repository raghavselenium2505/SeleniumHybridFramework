//TestListener.java

package com.web.utilities;

import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import org.testng.ITestListener;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.base.web.JiraCreateIssue;

import net.rcarz.jiraclient.JiraException;

public class TestListener implements ITestListener {

	public static WebDriver driver;
	public static ExtentTest test;

	@Override

	public void onTestFailure(ITestResult result) {

		boolean islogIssue = result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(JiraCreateIssue.class).isCreateIssue();
		System.out.println(
				"result" + result.getMethod() + "testName" + result.getClass() + "className" + result.getTestClass());
		if (islogIssue) {
			JiraServiceProvider JiraServiceProvider = new JiraServiceProvider();
			String issueDescription = "Failure Reason from Automation Testing second time\n\n"
					+ result.getThrowable().getMessage()

					+ "\n";

			issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));

			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()

					+ " Failed in Automation Testing";
			try {
				JiraServiceProvider.createJiraIssue("Task", issueSummary, issueDescription, "d raghavendra");
			} catch (JiraException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "test execution Completed");

	}

	@Override

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		if(driver!=null)
		{
			driver.quit();
		}
				

	}

	@Override

	public void onStart(ITestContext context) {

	}

	@Override

	public void onFinish(ITestContext context) {
		if(driver!=null)
		{
			driver.quit();
		}
				

	}

	@Override

	public void onTestStart(ITestResult result) {
		if(driver!=null)
		{
			driver.quit();
		}
				

	}

	@Override

	public void onTestSuccess(ITestResult result) {
		if(driver!=null)
		{
			driver.quit();
		}
				
		
	}

}
