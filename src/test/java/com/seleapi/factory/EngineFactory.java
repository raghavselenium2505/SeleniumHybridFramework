package com.seleapi.factory;

import com.seleapi.engines.AutomationEngine;

import com.seleapi.engines.api.APIEngine;

import com.seleapi.engines.playwright
.PlaywrightEngine;

import com.seleapi.engines.selenium
.SeleniumEngine;

public class EngineFactory {

    private EngineFactory() {

    }

    /*
     * GET ENGINE
     */

    public static AutomationEngine
    getEngine(String engineName) {

        switch(engineName.toLowerCase()) {

        case "selenium":

            return new SeleniumEngine();

        case "playwright":

            return new PlaywrightEngine();

        case "api":

            return new APIEngine();

        default:

            throw new RuntimeException(
                    "Invalid Engine : "
                    + engineName);
        }
    }
}