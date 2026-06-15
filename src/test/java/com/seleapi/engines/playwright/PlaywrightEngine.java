package com.seleapi.engines.playwright;

import org.apache.log4j.Logger;

import com.microsoft.playwright.Page;

import com.seleapi.engines.AutomationEngine;

import com.seleapi.playwright
.PlaywrightFactory;

import com.seleapi.playwright
.PlaywrightManager;

public class PlaywrightEngine
implements AutomationEngine {

    public static Logger log =

            Logger.getLogger(
                    PlaywrightEngine.class);

    Page page;

    @Override
    public void start() {

        /*
         * INITIALIZE PLAYWRIGHT
         */

        PlaywrightFactory
        .initializeBrowser();

        /*
         * GET PAGE
         */

        page =

                PlaywrightManager
                .getPage();

        log.info(
                "Starting Playwright Engine");
    }

    @Override
    public void stop() {

        /*
         * CLOSE PLAYWRIGHT
         */

        PlaywrightFactory
        .quitBrowser();

        log.info(
                "Stopping Playwright Engine");
    }

    @Override
    public void launchApplication(String url) {

        page.navigate(url);

        log.info(
                "Launching Application : "
                + url);
    }
}