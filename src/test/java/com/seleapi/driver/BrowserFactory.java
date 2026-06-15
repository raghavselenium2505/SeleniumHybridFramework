package com.seleapi.driver;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.seleapi.browser.BrowserOptionsManager;
import com.seleapi.utils.ConfigReader;

public class BrowserFactory {

    public static Logger log =
            Logger.getLogger(
                    BrowserFactory.class);

    private BrowserFactory() {

    }

    /*
     * GET BROWSER
     */

    public static WebDriver getBrowser(
            String browser) {

        WebDriver driver = null;

        try {

            /*
             * GRID ENABLED?
             */

            boolean gridEnabled =

                    Boolean.parseBoolean(

                            ConfigReader
                            .getProperty(
                                    "grid.enabled"));

            /*
             * GRID URL
             */

            String gridUrl =

                    ConfigReader
                    .getProperty(
                            "grid.url");

            log.info(
                    "Launching Browser : "
                    + browser);

            /*
             * GRID EXECUTION
             */

            if (gridEnabled) {

                log.info(
                        "Executing On Selenium Grid");

                switch (browser.toLowerCase()) {

                    /*
                     * CHROME GRID
                     */

                    case "chrome":

                        driver =
                                new RemoteWebDriver(

                                        new URL(gridUrl),

                                        BrowserOptionsManager
                                        .getChromeOptions());

                        break;

                    /*
                     * FIREFOX GRID
                     */

                    case "firefox":

                        driver =
                                new RemoteWebDriver(

                                        new URL(gridUrl),

                                        BrowserOptionsManager
                                        .getFirefoxOptions());

                        break;

                    /*
                     * EDGE GRID
                     */

                    case "edge":

                        driver =
                                new RemoteWebDriver(

                                        new URL(gridUrl),

                                        BrowserOptionsManager
                                        .getEdgeOptions());

                        break;

                    default:

                        throw new RuntimeException(

                                "Unsupported Browser : "
                                + browser);
                }

                log.info(
                        "Grid Driver Initialized Successfully");
            }

            /*
             * LOCAL EXECUTION
             */

            else {

                log.info(
                        "Executing On Local Machine");

                switch (browser.toLowerCase()) {

                    /*
                     * CHROME
                     */

                    case "chrome":

                        driver =
                                new ChromeDriver(

                                        BrowserOptionsManager
                                        .getChromeOptions());

                        break;

                    /*
                     * FIREFOX
                     */

                    case "firefox":

                        driver =
                                new FirefoxDriver(

                                        BrowserOptionsManager
                                        .getFirefoxOptions());

                        break;

                    /*
                     * EDGE
                     */

                    case "edge":

                        driver =
                                new EdgeDriver(

                                        BrowserOptionsManager
                                        .getEdgeOptions());

                        break;

                    default:

                        throw new RuntimeException(

                                "Unsupported Browser : "
                                + browser);
                }

                log.info(
                        "Local Driver Initialized Successfully");
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Initialize Browser",
                    e);

            throw new RuntimeException(
                    e);
        }

        return driver;
    }
}