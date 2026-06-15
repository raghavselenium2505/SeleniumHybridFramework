package com.seleapi.engines.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.seleapi.browser.BrowserManager;
import com.seleapi.engines.AutomationEngine;

public class SeleniumEngine
implements AutomationEngine {

    public static Logger log =

            Logger.getLogger(
                    SeleniumEngine.class);

    WebDriver driver;

    @Override
    public void start() {

        driver =
                BrowserManager.getDriver();

        log.info(
                "Starting Selenium Engine");
    }

    @Override
    public void stop() {

        BrowserManager.quitBrowser();

        log.info(
                "Stopping Selenium Engine");
    }

    @Override
    public void launchApplication(String url) {

        driver.get(url);

        log.info(
                "Launching Application : "
                + url);
    }
}