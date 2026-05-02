/*
 * // TestListener.java
 * 
 * package com.web.utilities;
 * 
 * import org.apache.commons.lang.exception.ExceptionUtils; import
 * org.openqa.selenium.WebDriver; import org.testng.ITestContext; import
 * org.testng.ITestListener; import org.testng.ITestResult;
 * 
 * import com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.Status; import com.base.web.JiraCreateIssue;
 * import com.gps.base.TestBase;
 * 
 * public class TestListener extends TestBase implements ITestListener {
 * 
 * public static WebDriver driver; public static ExtentTest test;
 * 
 * @Override public void onTestFailure(ITestResult result) {
 * 
 * try {
 * 
 * boolean isLogIssue = false;
 * 
 * JiraCreateIssue jiraAnnotation = result.getMethod() .getConstructorOrMethod()
 * .getMethod() .getAnnotation(JiraCreateIssue.class);
 * 
 * if (jiraAnnotation != null) { isLogIssue = jiraAnnotation.isCreateIssue(); }
 * 
 * if (isLogIssue) {
 * 
 * JiraServiceProvider jiraServiceProvider = new JiraServiceProvider();
 * 
 * String testName = result.getMethod() .getConstructorOrMethod() .getMethod()
 * .getName();
 * 
 * String errorMessage = "";
 * 
 * if (result.getThrowable() != null) { errorMessage =
 * result.getThrowable().getMessage(); }
 * 
 * String fullStackTrace = "";
 * 
 * if (result.getThrowable() != null) { fullStackTrace =
 * ExceptionUtils.getFullStackTrace( result.getThrowable()); }
 * 
 * String issueSummary = testName + " Failed in Automation Testing";
 * 
 * String issueDescription = "Failure Reason from Automation Testing\n\n" +
 * "Test Case : " + testName + "\nError Message : " + errorMessage +
 * "\n\nStack Trace :\n" + fullStackTrace + "\nEnvironment : QA";
 * 
 * jiraServiceProvider.createJiraIssue( "Bug", issueSummary, issueDescription,
 * "d raghavendra");
 * 
 * System.out.println( "Jira Bug Created Successfully"); }
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * if (test != null) { test.log(Status.FAIL, result.getName() + " Test Failed");
 * } }
 * 
 * @Override public void onTestSkipped(ITestResult result) {
 * 
 * if (test != null) { test.log(Status.SKIP, result.getName() +
 * " Test Skipped"); } }
 * 
 * @Override public void onTestFailedButWithinSuccessPercentage( ITestResult
 * result) {
 * 
 * }
 * 
 * @Override public void onStart(ITestContext context) {
 * 
 * }
 * 
 * @Override public void onFinish(ITestContext context) {
 * 
 * if (driver != null) { driver.quit(); } }
 * 
 * @Override public void onTestStart(ITestResult result) {
 * 
 * }
 * 
 * @Override public void onTestSuccess(ITestResult result) {
 * 
 * if (test != null) { test.log(Status.PASS, result.getName() + " Test Passed");
 * } } }
 */