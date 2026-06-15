package com.seleapi.api.auth;

import org.apache.log4j.Logger;

import com.seleapi.utils.ConfigReader;

import io.restassured.specification.RequestSpecification;

public class AuthenticationManager {

    public static Logger log =
            Logger.getLogger(
                    AuthenticationManager.class);

    private AuthenticationManager() {

    }

    /*
     * APPLY AUTHENTICATION
     */

    public static RequestSpecification applyAuthentication(

            RequestSpecification request) {

        try {

            String authType =

                    ConfigReader
                    .getProperty(
                            "api.auth.type");

            log.info(
                    "Authentication Type : "
                    + authType);

            /*
             * NO AUTH
             */

            if (authType == null
                    || authType.equalsIgnoreCase(
                            "none")) {

                log.info(
                        "No Authentication Applied");

                return request;
            }

            /*
             * BEARER TOKEN
             */

            if (authType.equalsIgnoreCase(
                    "bearer")) {

                String token =

                        ConfigReader
                        .getProperty(
                                "api.token");

                request.header(
                        "Authorization",
                        "Bearer " + token);

                log.info(
                        "Bearer Token Applied");
            }

            /*
             * BASIC AUTH
             */

            else if (authType.equalsIgnoreCase(
                    "basic")) {

                String username =

                        ConfigReader
                        .getProperty(
                                "api.username");

                String password =

                        ConfigReader
                        .getProperty(
                                "api.password");

                request.auth()
                       .preemptive()
                       .basic(
                               username,
                               password);

                log.info(
                        "Basic Authentication Applied");
            }

            /*
             * API KEY AUTH
             */

            else if (authType.equalsIgnoreCase(
                    "apikey")) {

                String apiKey =

                        ConfigReader
                        .getProperty(
                                "api.key");

                request.header(
                        "x-api-key",
                        apiKey);

                log.info(
                        "API Key Authentication Applied");
            }

            else {

                throw new RuntimeException(

                        "Unsupported Authentication Type : "
                        + authType);
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Apply Authentication",
                    e);

            throw new RuntimeException(e);
        }

        return request;
    }
}