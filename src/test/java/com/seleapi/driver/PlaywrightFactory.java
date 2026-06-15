package com.seleapi.driver;

import org.apache.log4j.Logger;

public class PlaywrightFactory {

    public static Logger log =
            Logger.getLogger(PlaywrightFactory.class);

    private PlaywrightFactory() {

    }

    public static void initializePlaywright() {

        log.info("Playwright Engine Initialized");
    }
}