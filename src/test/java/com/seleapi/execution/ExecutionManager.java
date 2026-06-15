package com.seleapi.execution;

import com.seleapi.engines.AutomationEngine;

import com.seleapi.factory.EngineFactory;

public class ExecutionManager {

    public void execute(String engineName,
                        String url) {

        /*
         * GET ENGINE
         */

        AutomationEngine engine =

                EngineFactory
                .getEngine(engineName);

        /*
         * COMMON FLOW
         */

        engine.start();

        engine.launchApplication(url);

        engine.stop();
    }
}