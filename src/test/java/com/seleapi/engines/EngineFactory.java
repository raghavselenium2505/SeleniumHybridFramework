package com.seleapi.engines;

import org.apache.log4j.Logger;

import com.seleapi.engines.api.APIEngine;
import com.seleapi.engines.playwright.PlaywrightEngine;
import com.seleapi.engines.selenium.SeleniumEngine;

public class EngineFactory {

    public static Logger log =
            Logger.getLogger(EngineFactory.class);

    private EngineFactory() {

    }

    public static AutomationEngine getEngine(String engine) {

        log.info("Requested Engine : " + engine);

        if (engine == null || engine.trim().isEmpty()) {

            log.error("Engine name is NULL or EMPTY");

            throw new RuntimeException(
                    "Engine cannot be null or empty");
        }

        switch (engine.toLowerCase().trim()) {

            case "selenium":

                log.info("Initializing Selenium Engine");

                return new SeleniumEngine();

            case "playwright":

                log.info("Initializing Playwright Engine");

                return new PlaywrightEngine();

            case "api":

                log.info("Initializing API Engine");

                return new APIEngine();

            default:

                log.error("Unsupported Engine : " + engine);

                throw new RuntimeException(
                        "Unsupported Engine : " + engine);
        }
    }
}