package com.seleapi.api;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.seleapi.reporting.ExtentManager;

public class APIExecutionLogger {

    public static Logger log =
            Logger.getLogger(
                    APIExecutionLogger.class);

    private APIExecutionLogger() {

    }

    /*
     * LOG REQUEST
     */

    public static void logRequest(

            String method,

            String endpoint,

            String requestBody) {

        log.info(
                "================================");

        log.info(
                "API REQUEST");

        log.info(
                "METHOD : "
                + method);

        log.info(
                "ENDPOINT : "
                + endpoint);

        log.info(
                "REQUEST BODY : ");

        log.info(requestBody);

        log.info(
                "================================");

        ExtentManager.info(

                "API REQUEST"
                + "<br><b>METHOD :</b> "
                + method
                + "<br><b>ENDPOINT :</b> "
                + endpoint
                + "<br><b>REQUEST :</b><pre>"
                + requestBody
                + "</pre>");
    }

    /*
     * LOG RESPONSE
     */

    public static void logResponse(

            int statusCode,

            String responseBody,

            long responseTime) {

        log.info(
                "================================");

        log.info(
                "API RESPONSE");

        log.info(
                "STATUS CODE : "
                + statusCode);

        log.info(
                "RESPONSE TIME : "
                + responseTime
                + " ms");

        log.info(
                "RESPONSE BODY : ");

        log.info(responseBody);

        log.info(
                "================================");

        ExtentManager.info(

                "API RESPONSE"
                + "<br><b>STATUS CODE :</b> "
                + statusCode
                + "<br><b>RESPONSE TIME :</b> "
                + responseTime
                + " ms"
                + "<br><b>RESPONSE :</b><pre>"
                + responseBody
                + "</pre>");
    }

    /*
     * LOG FAILURE
     */

    public static void logFailure(
            Exception e) {

        log.error(
                "API EXECUTION FAILED",
                e);

        ExtentManager.fail(
                e);
    }
}