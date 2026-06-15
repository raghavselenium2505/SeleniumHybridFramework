package com.seleapi.driver;

import org.apache.log4j.Logger;

public class APIFactory {

    public static Logger log =
            Logger.getLogger(APIFactory.class);

    private APIFactory() {

    }

    public static void initializeAPIEngine() {

        log.info("API Engine Initialized");
    }
}