package com.seleapi.engines.api;

import org.apache.log4j.Logger;

import com.seleapi.engines.AutomationEngine;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIEngine
implements AutomationEngine {

    public static Logger log =

            Logger.getLogger(
                    APIEngine.class);

    @Override
    public void start() {

        log.info(
                "Starting API Engine");
    }

    @Override
    public void stop() {

        log.info(
                "Stopping API Engine");
    }

    @Override
    public void launchApplication(String url) {

        Response response =

                RestAssured
                .get(url);

        log.info(
                "API Status Code : "
                + response.statusCode());

        log.info(
                "API Response : "
                + response.asPrettyString());
    }
}