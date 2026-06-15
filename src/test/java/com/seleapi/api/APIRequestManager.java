package com.seleapi.api;

import org.apache.log4j.Logger;

import com.seleapi.api.auth.AuthenticationManager;
import com.seleapi.reporting.ExtentManager;
import com.seleapi.utils.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;

public class APIRequestManager {

    public static Logger log =
            Logger.getLogger(
                    APIRequestManager.class);

    /*
     * BASE URL
     */

    private static final String BASE_URL =

            ConfigReader
            .getProperty(
                    "api.base.url");

    /*
     * API TIMEOUT
     */

    private static final int API_TIMEOUT =

            Integer.parseInt(

                    ConfigReader
                    .getProperty(
                            "api.timeout"));

    private APIRequestManager() {

    }

    /*
     * GET REQUEST
     */

    public static Response get(
            String endpoint) {

        try {

            long startTime =
                    System.currentTimeMillis();

            /*
             * FINAL URL
             */

            String finalUrl =
                    BASE_URL + endpoint;

            /*
             * LOG REQUEST
             */

            APIExecutionLogger.logRequest(

                    "GET",

                    finalUrl,

                    "NO REQUEST BODY");

            /*
             * EXECUTE REQUEST
             */

            Response response =

                    AuthenticationManager
                    .applyAuthentication(

                            RestAssured
                            .given())

                    .config(

                            RestAssured.config()

                            .httpClient(

                                    HttpClientConfig
                                    .httpClientConfig()

                                    .setParam(

                                            "http.connection.timeout",

                                            API_TIMEOUT * 1000)

                                    .setParam(

                                            "http.socket.timeout",

                                            API_TIMEOUT * 1000)
                            )
                    )

                    .when()

                    .get(finalUrl)

                    .then()

                    .extract()

                    .response();

            long endTime =
                    System.currentTimeMillis();

            long responseTime =
                    endTime - startTime;

            /*
             * LOG RESPONSE
             */

            APIExecutionLogger.logResponse(

                    response.statusCode(),

                    response.getBody()
                            .asPrettyString(),

                    responseTime);

            ExtentManager.pass(
                    "GET Request Successful");

            return response;
        }

        catch (Exception e) {

            APIExecutionLogger
            .logFailure(e);

            throw new RuntimeException(e);
        }
    }

    /*
     * POST REQUEST
     */

    public static Response post(

            String endpoint,

            String requestBody) {

        try {

            long startTime =
                    System.currentTimeMillis();

            /*
             * FINAL URL
             */

            String finalUrl =
                    BASE_URL + endpoint;

            /*
             * LOG REQUEST
             */

            APIExecutionLogger.logRequest(

                    "POST",

                    finalUrl,

                    requestBody);

            /*
             * EXECUTE REQUEST
             */

            Response response =

                    AuthenticationManager
                    .applyAuthentication(

                            RestAssured
                            .given())

                    .config(

                            RestAssured.config()

                            .httpClient(

                                    HttpClientConfig
                                    .httpClientConfig()

                                    .setParam(

                                            "http.connection.timeout",

                                            API_TIMEOUT * 1000)

                                    .setParam(

                                            "http.socket.timeout",

                                            API_TIMEOUT * 1000)
                            )
                    )

                    .header(
                            "Content-Type",
                            "application/json")

                    .body(requestBody)

                    .when()

                    .post(finalUrl)

                    .then()

                    .extract()

                    .response();

            long endTime =
                    System.currentTimeMillis();

            long responseTime =
                    endTime - startTime;

            /*
             * LOG RESPONSE
             */

            APIExecutionLogger.logResponse(

                    response.statusCode(),

                    response.getBody()
                            .asPrettyString(),

                    responseTime);

            ExtentManager.pass(
                    "POST Request Successful");

            return response;
        }

        catch (Exception e) {

            APIExecutionLogger
            .logFailure(e);

            throw new RuntimeException(e);
        }
    }

    /*
     * PUT REQUEST
     */

    public static Response put(

            String endpoint,

            String requestBody) {

        try {

            long startTime =
                    System.currentTimeMillis();

            String finalUrl =
                    BASE_URL + endpoint;

            APIExecutionLogger.logRequest(

                    "PUT",

                    finalUrl,

                    requestBody);

            Response response =

                    AuthenticationManager
                    .applyAuthentication(

                            RestAssured
                            .given())

                    .config(

                            RestAssured.config()

                            .httpClient(

                                    HttpClientConfig
                                    .httpClientConfig()

                                    .setParam(

                                            "http.connection.timeout",

                                            API_TIMEOUT * 1000)

                                    .setParam(

                                            "http.socket.timeout",

                                            API_TIMEOUT * 1000)
                            )
                    )

                    .header(
                            "Content-Type",
                            "application/json")

                    .body(requestBody)

                    .when()

                    .put(finalUrl)

                    .then()

                    .extract()

                    .response();

            long endTime =
                    System.currentTimeMillis();

            long responseTime =
                    endTime - startTime;

            APIExecutionLogger.logResponse(

                    response.statusCode(),

                    response.getBody()
                            .asPrettyString(),

                    responseTime);

            ExtentManager.pass(
                    "PUT Request Successful");

            return response;
        }

        catch (Exception e) {

            APIExecutionLogger
            .logFailure(e);

            throw new RuntimeException(e);
        }
    }

    /*
     * DELETE REQUEST
     */

    public static Response delete(
            String endpoint) {

        try {

            long startTime =
                    System.currentTimeMillis();

            String finalUrl =
                    BASE_URL + endpoint;

            APIExecutionLogger.logRequest(

                    "DELETE",

                    finalUrl,

                    "NO REQUEST BODY");

            Response response =

                    AuthenticationManager
                    .applyAuthentication(

                            RestAssured
                            .given())

                    .config(

                            RestAssured.config()

                            .httpClient(

                                    HttpClientConfig
                                    .httpClientConfig()

                                    .setParam(

                                            "http.connection.timeout",

                                            API_TIMEOUT * 1000)

                                    .setParam(

                                            "http.socket.timeout",

                                            API_TIMEOUT * 1000)
                            )
                    )

                    .when()

                    .delete(finalUrl)

                    .then()

                    .extract()

                    .response();

            long endTime =
                    System.currentTimeMillis();

            long responseTime =
                    endTime - startTime;

            APIExecutionLogger.logResponse(

                    response.statusCode(),

                    response.getBody()
                            .asPrettyString(),

                    responseTime);

            ExtentManager.pass(
                    "DELETE Request Successful");

            return response;
        }

        catch (Exception e) {

            APIExecutionLogger
            .logFailure(e);

            throw new RuntimeException(e);
        }
    }
}