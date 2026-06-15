package com.seleapi.api;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.seleapi.dashboard.DashboardManager;
import com.seleapi.reporting.ExtentManager;

import io.restassured.response.Response;

public class APIAssertions {

    public static Logger log =
            Logger.getLogger(
                    APIAssertions.class);

    private APIAssertions() {

    }

    /*
     * VERIFY STATUS CODE
     */

    public static void verifyStatusCode(

            Response response,

            int expectedStatusCode) {

        try {

            int actualStatusCode =

                    response.getStatusCode();

            /*
             * STATUS MATCH
             */

            if (actualStatusCode
                    == expectedStatusCode) {

                DashboardManager
                .apiPassed();

                log.info(
                        "Status Code Validation Passed");

                log.info(
                        "Expected Status Code : "
                        + expectedStatusCode);

                log.info(
                        "Actual Status Code : "
                        + actualStatusCode);

                ExtentManager.pass(
                        "Status Code Validation Passed : "
                        + actualStatusCode);
            }

            /*
             * STATUS FAILED
             */

            else {

                DashboardManager
                .apiFailed();

                log.error(
                        "Status Code Validation Failed");

                log.error(
                        "Expected Status Code : "
                        + expectedStatusCode);

                log.error(
                        "Actual Status Code : "
                        + actualStatusCode);

                ExtentManager.fail(
                        "Status Code Validation Failed");

				/*
				 * Assert.fail( "Expected Status Code : " + expectedStatusCode
				 * 
				 * + " But Found : " + actualStatusCode);
				 */
                
                System.out.println(
                	    "STATUS CODE VALIDATION FAILED");
            }

        }

        catch (Exception e) {

            DashboardManager
            .apiFailed();

            log.error(
                    "Exception During Status Code Validation",
                    e);

            ExtentManager.fail(
                    e);

            throw e;
        }
    }

    /*
     * VERIFY RESPONSE CONTAINS
     */

    public static void verifyResponseContains(

            Response response,

            String expectedValue) {

        try {

            String responseBody =

                    response.asString();

            /*
             * RESPONSE MATCH
             */

            if (responseBody.contains(
                    expectedValue)) {

                log.info(
                        "Response Validation Passed");

                log.info(
                        "Expected Value Found : "
                        + expectedValue);

                ExtentManager.pass(
                        "Response Contains : "
                        + expectedValue);
            }

            /*
             * RESPONSE FAILED
             */

            else {

                log.error(
                        "Response Validation Failed");

                log.error(
                        "Expected Value Missing : "
                        + expectedValue);

                ExtentManager.fail(
                        "Response Does Not Contain : "
                        + expectedValue);

                Assert.fail(
                        "Response Does Not Contain : "
                        + expectedValue);
            }

        }

        catch (Exception e) {

            log.error(
                    "Exception During Response Validation",
                    e);

            ExtentManager.fail(
                    e);

            throw e;
        }
    }

    /*
     * VERIFY RESPONSE TIME
     */

    public static void verifyResponseTime(

            Response response,

            long expectedResponseTime) {

        try {

            long actualResponseTime =

                    response.getTime();

            /*
             * ADD RESPONSE TIME
             */

            DashboardManager
            .addApiResponseTime(
                    actualResponseTime);

            /*
             * RESPONSE TIME MATCH
             */

            if (actualResponseTime
                    <= expectedResponseTime) {

                log.info(
                        "Response Time Validation Passed");

                log.info(
                        "Actual Response Time : "
                        + actualResponseTime
                        + " ms");

                ExtentManager.pass(
                        "Response Time : "
                        + actualResponseTime
                        + " ms");
            }

            /*
             * RESPONSE TIME FAILED
             */

            else {

                log.error(
                        "Response Time Validation Failed");

                log.error(
                        "Expected Response Time : "
                        + expectedResponseTime
                        + " ms");

                log.error(
                        "Actual Response Time : "
                        + actualResponseTime
                        + " ms");

                ExtentManager.fail(
                        "Response Time Exceeded");

                Assert.fail(
                        "Expected Response Time : "
                        + expectedResponseTime

                        + " ms But Found : "

                        + actualResponseTime
                        + " ms");
            }

        }

        catch (Exception e) {

            log.error(
                    "Exception During Response Time Validation",
                    e);

            ExtentManager.fail(
                    e);

            throw e;
        }
    }
}