package com.seleapi.api;

import java.util.Map;

import org.apache.log4j.Logger;

import com.seleapi.api.auth.AuthenticationManager;
import com.seleapi.utils.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class APIRequestBuilder {

    public static Logger log =
            Logger.getLogger(
                    APIRequestBuilder.class);

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

    /*
     * CONTENT TYPE
     */

    private static final String CONTENT_TYPE =

            ConfigReader
            .getProperty(
                    "api.content.type");

    private APIRequestBuilder() {

    }

    /*
     * DEFAULT REQUEST
     */

    public static RequestSpecification getRequest() {

        try {

            RequestSpecBuilder builder =
                    new RequestSpecBuilder();

            /*
             * BASE URI
             */

            builder.setBaseUri(
                    BASE_URL);

            /*
             * CONTENT TYPE
             */

            if (CONTENT_TYPE.equalsIgnoreCase(
                    "application/json")) {

                builder.setContentType(
                        ContentType.JSON);
            }

            /*
             * REQUEST CONFIG
             */

            builder.setConfig(

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
            );

            RequestSpecification request =

                    builder.build();

            /*
             * APPLY AUTHENTICATION
             */

            request =

                    AuthenticationManager
                    .applyAuthentication(
                            request);

            log.info(
                    "API Request Builder Initialized");

            return request;

        }

        catch (Exception e) {

            log.error(
                    "Failed To Create API Request Builder",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * ADD HEADERS
     */

    public static RequestSpecification addHeaders(

            RequestSpecification request,

            Map<String, String> headers) {

        try {

            request.headers(headers);

            log.info(
                    "Headers Added Successfully");

            return request;

        }

        catch (Exception e) {

            log.error(
                    "Failed To Add Headers",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * ADD QUERY PARAMS
     */

    public static RequestSpecification addQueryParams(

            RequestSpecification request,

            Map<String, String> queryParams) {

        try {

            request.queryParams(queryParams);

            log.info(
                    "Query Parameters Added Successfully");

            return request;

        }

        catch (Exception e) {

            log.error(
                    "Failed To Add Query Parameters",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * ADD PATH PARAMS
     */

    public static RequestSpecification addPathParams(

            RequestSpecification request,

            Map<String, String> pathParams) {

        try {

            request.pathParams(pathParams);

            log.info(
                    "Path Parameters Added Successfully");

            return request;

        }

        catch (Exception e) {

            log.error(
                    "Failed To Add Path Parameters",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * ADD REQUEST BODY
     */

    public static RequestSpecification addRequestBody(

            RequestSpecification request,

            String requestBody) {

        try {

            request.body(requestBody);

            log.info(
                    "Request Body Added Successfully");

            return request;

        }

        catch (Exception e) {

            log.error(
                    "Failed To Add Request Body",
                    e);

            throw new RuntimeException(e);
        }
    }
}