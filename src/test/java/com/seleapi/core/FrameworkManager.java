package com.seleapi.core;

import org.apache.log4j.Logger;

import com.seleapi.config.ConfigManager;
import com.seleapi.reporting.ExtentManager;

public class FrameworkManager {

    public static Logger log =
            Logger.getLogger(FrameworkManager.class);

    private static boolean initialized = false;

    public static void initializeFramework() {

        if (!initialized) {

            log.info("Initializing SeleAPI Framework");

            ConfigManager.loadProperties();

            ExtentManager.initializeReport();

            initialized = true;

            log.info("SeleAPI Framework Initialized Successfully");
        }
    }
}