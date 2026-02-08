package com.gps.api.development;

import org.testng.annotations.Test;

import com.gps.api.apibase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class LoginAPI extends TestBase {
	@Test

	public void loginAPI() {
		test = report.startTest("Login API Positive Test");

		String baseUrl = "https://ta-qa.mapstechnologies.com";
		String endpoint = "api/access/login";

		// Perform login with valid credentials
		long startTime = System.currentTimeMillis(); // Start timer
		Response response = login("raghavendra.damaraju@gmail.com", "Amzur@432", baseUrl, endpoint);
		long endTime = System.currentTimeMillis(); // End timer

		long responseTime = endTime - startTime; // Calculate response time in milliseconds

		// Log response code and response time in Extent report
		test.log(LogStatus.INFO, "Response Code: " + response.getStatusCode());
		test.log(LogStatus.INFO, "Response Time: " + responseTime + " ms");

		// Validate positive response
		if (response.getStatusCode() == 200) {
			test.log(LogStatus.PASS, "Login successful for valid credentials");
		} else {
			test.log(LogStatus.FAIL, "Login failed for valid credentials");
		}

		// Optional: log response body for reference
		test.log(LogStatus.INFO, "Response Body: " + response.getBody().asString());

		logger.info("Response Code: " + response.getStatusCode());
		logger.info("Response Time: " + responseTime + " ms");
	}

}
