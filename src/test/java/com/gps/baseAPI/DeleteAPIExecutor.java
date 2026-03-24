package com.gps.baseAPI;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteAPIExecutor extends APITestBase {

    public void executeDelete(String endpoint,
                              int expectedStatus,
                              ExtentTest test) {

        RequestSpecification request = getRequestStatic();

        Response response = request.delete(endpoint);

        int actualStatus = response.getStatusCode();

        test.info("DELETE Request → " + endpoint);
        test.info("Status Code: " + actualStatus);
        test.info("Response Time: " + response.getTime() + " ms");
        test.info("Response Body: " + response.asPrettyString());

        if (actualStatus == expectedStatus) {
            test.pass("Status matched → Expected: " + expectedStatus + " | Actual: " + actualStatus);
        } else {
            test.fail("Status mismatch → Expected: " + expectedStatus + " | Actual: " + actualStatus);
        }
    }
}