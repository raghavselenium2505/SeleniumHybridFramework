package com.seleapi.core;

import org.apache.log4j.Logger;

public class SuiteManager {

    public static Logger log =
            Logger.getLogger(SuiteManager.class);

    public static void startSuite() {

        log.info("========== SUITE EXECUTION STARTED ==========");
    }

    public static void endSuite() {

        log.info("========== SUITE EXECUTION COMPLETED ==========");
    }
}