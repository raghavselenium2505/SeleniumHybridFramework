package com.gps.baseAPI;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostAPIExecutor extends APITestBase {

    public void executePost(String endpoint,
                            String body,
                            int expectedStatus,
                            ExtentTest test) {

        RequestSpecification request = getRequestStatic();

        if (body != null && !body.trim().isEmpty()) {
            request.body(body);
        }

        Response response = request.post(endpoint);

        int actualStatus = response.getStatusCode();

        test.info("POST Request → " + endpoint);
        test.info("Request Body → " + body);
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