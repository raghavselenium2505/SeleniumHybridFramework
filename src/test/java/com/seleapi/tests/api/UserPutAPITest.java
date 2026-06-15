package com.seleapi.tests.api;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.seleapi.api.APIAssertions;
import com.seleapi.api.APIRequestManager;
import com.seleapi.core.BaseTest;
import com.seleapi.reporting.ExtentManager;

import io.restassured.response.Response;

public class UserPutAPITest
        extends BaseTest {

    public static Logger log =
            Logger.getLogger(
                    UserPutAPITest.class);

    @Test

    public void getUsersTest() {

        try {

            log.info(
                    "========== API TEST STARTED ==========");

            /*
             * EXTENT INFO
             */

            ExtentManager.info(
                    "Executing GET Users API Test");

            /*
             * EXECUTE API REQUEST
             */

            Response response =

                   APIRequestManager.put("/users/1", "{\r\n"
                   		+ " \"firstName\":\"Raghav\",\r\n"
                   		+ " \"lastName\":\"Tester\",\r\n"
                   		+ " \"age\":35\r\n"
                   		+ "}");	
            log.info(
                    "API Response Received");

            /*
             * LOG RESPONSE
             */

            log.info(
                    "Response Status Code : "
                    + response.getStatusCode());

            log.info(
                    "Response Body : "
                    + response.asPrettyString());

            ExtentManager.info(
                    "Response Status Code : "
                    + response.getStatusCode());

            /*
             * ASSERTIONS
             */

            APIAssertions.verifyStatusCode(
                    response,
                    200);

            APIAssertions.verifyResponseContains(
                    response,
                    "Raghav");

            APIAssertions.verifyResponseContains(
                    response,
                    "Tester");
            APIAssertions.verifyResponseTime(
                    response,
                    5000);

            /*
             * TEST PASS
             */

            ExtentManager.pass(
                    "GET Users API Test Passed Successfully");

            log.info(
                    "========== API TEST PASSED ==========");

        }

        catch (Exception e) {

            /*
             * EXTENT FAIL
             */

            ExtentManager.fail(
                    e);

            log.error(
                    "========== API TEST FAILED ==========",
                    e);

            throw e;
        }
    }
}