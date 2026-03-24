package com.gps.baseAPI;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIExecutor extends APITestBase {

    public void executeGet(String endpoint,
                           int expectedStatus,
                           ExtentTest test) {

        RequestSpecification request = getRequestStatic();
        Response response = request.get(endpoint);

        int actualStatus = response.getStatusCode();

        test.info("GET Request → " + endpoint);
        test.info("Status Code: " + actualStatus);
        test.info("Response Time: " + response.getTime() + " ms");

        String pretty = response.asPrettyString();

        test.info("<details><summary>Response Body</summary>"
                + "<pre style='max-height:300px;overflow:auto;'>"
                + pretty
                + "</pre></details>");

        if (actualStatus == expectedStatus) {
            test.pass("Status matched → Expected: " + expectedStatus +
                    " | Actual: " + actualStatus);
        } else {
            test.fail("Status mismatch → Expected: " + expectedStatus +
                    " | Actual: " + actualStatus);
        }
    }
}