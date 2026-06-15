package com.seleapi.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.seleapi.utils.ConfigReader;

public class BrowserOptionsManager {

    public static Logger log =
            Logger.getLogger(
                    BrowserOptionsManager.class);

    private BrowserOptionsManager() {

    }

    /*
     * CHROME OPTIONS
     */

    public static ChromeOptions getChromeOptions() {

        ChromeOptions options =
                new ChromeOptions();

        /*
         * HEADLESS MODE
         */

        boolean headless =
                Boolean.parseBoolean(

                        ConfigReader
                        .getProperty(
                                "headless"));

        if (headless) {

            options.addArguments(
                    "--headless=new");

            log.info(
                    "Chrome Running In Headless Mode");
        }

        /*
         * COMMON OPTIONS
         */

        options.addArguments(
                "--start-maximized");

        options.addArguments(
                "--disable-notifications");

        options.addArguments(
                "--remote-allow-origins=*");

        options.addArguments(
                "--disable-popup-blocking");

        options.addArguments(
                "--incognito");

        log.info(
                "Chrome Options Configured");

        return options;
    }

    /*
     * FIREFOX OPTIONS
     */

    public static FirefoxOptions getFirefoxOptions() {

        FirefoxOptions options =
                new FirefoxOptions();

        boolean headless =
                Boolean.parseBoolean(

                        ConfigReader
                        .getProperty(
                                "headless"));

        if (headless) {

            options.addArguments(
                    "--headless");

            log.info(
                    "Firefox Running In Headless Mode");
        }

        log.info(
                "Firefox Options Configured");

        return options;
    }

    /*
     * EDGE OPTIONS
     */

    public static EdgeOptions getEdgeOptions() {

        EdgeOptions options =
                new EdgeOptions();

        boolean headless =
                Boolean.parseBoolean(

                        ConfigReader
                        .getProperty(
                                "headless"));

        if (headless) {

            options.addArguments(
                    "--headless=new");

            log.info(
                    "Edge Running In Headless Mode");
        }

        options.addArguments(
                "--start-maximized");

        options.addArguments(
                "--disable-notifications");

        log.info(
                "Edge Options Configured");

        return options;
    }
}