package com.seleapi.engines;

import org.apache.log4j.Logger;

import com.seleapi.driver.DriverFactory;

public class EngineExecutionManager {

    public static Logger log =
            Logger.getLogger(
                    EngineExecutionManager.class);

    private EngineExecutionManager() {

    }

    /*
     * INITIALIZE ENGINE
     */

    public static void initializeEngine(

            String engine,

            String browser,

            String url) {

        try {

            /*
             * NULL CHECK
             */

            if (engine == null
                    || engine.trim().isEmpty()) {

                engine = "selenium";

                log.warn(
                        "Engine Was Null - Defaulting To Selenium");
            }

            log.info(
                    "========== ENGINE INITIALIZATION STARTED ==========");

            log.info(
                    "Selected Engine : "
                    + engine);

            /*
             * SELENIUM ENGINE
             */

            if (engine.equalsIgnoreCase(
                    "selenium")) {

                log.info(
                        "Initializing Selenium Engine");

                DriverFactory.initializeDriver(
                        browser,
                        engine);

                log.info(
                        "Selenium Driver Initialized Successfully");
            }

            /*
             * API ENGINE
             */

            else if (engine.equalsIgnoreCase(
                    "api")) {

                log.info(
                        "Initializing API Engine");

                log.info(
                        "API Engine Does Not Require Browser Initialization");

                log.info(
                        "API Engine Initialized Successfully");
            }

            /*
             * PLAYWRIGHT ENGINE
             */

            else if (engine.equalsIgnoreCase(
                    "playwright")) {

                log.info(
                        "Initializing Playwright Engine");

                com.seleapi.playwright
                .PlaywrightFactory
                .initializeBrowser();

                log.info(
                        "Playwright Engine Initialized Successfully");
            }
            /*
             * INVALID ENGINE
             */

            else {

                throw new RuntimeException(
                        "Invalid Engine : "
                        + engine);
            }

            log.info(
                    "========== ENGINE INITIALIZATION COMPLETED ==========");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Initialize Engine",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * QUIT ENGINE
     */

    public static void quitEngine(
            String engine) {

        try {

            /*
             * NULL CHECK
             */

            if (engine == null
                    || engine.trim().isEmpty()) {

                log.warn(
                        "Engine Is Null - Cleanup Skipped");

                return;
            }

            log.info(
                    "========== ENGINE TEARDOWN STARTED ==========");

            /*
             * SELENIUM ENGINE
             */

            if (engine.equalsIgnoreCase(
                    "selenium")) {

                DriverFactory.quitDriver();

                log.info(
                        "Selenium Driver Closed Successfully");
            }

            /*
             * API ENGINE
             */

            else if (engine.equalsIgnoreCase(
                    "api")) {

                log.info(
                        "API Engine Does Not Require Driver Cleanup");
            }

            /*
             * PLAYWRIGHT ENGINE
             */

            else if (engine.equalsIgnoreCase(
                    "playwright")) {

                com.seleapi.playwright
                .PlaywrightFactory
                .quitBrowser();

                log.info(
                        "Playwright Closed Successfully");
            }

            log.info(
                    "========== ENGINE TEARDOWN COMPLETED ==========");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Quit Engine",
                    e);

            throw new RuntimeException(e);
        }
    }
}