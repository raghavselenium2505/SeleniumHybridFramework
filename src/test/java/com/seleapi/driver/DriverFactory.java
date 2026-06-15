package com.seleapi.driver;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static Logger log =
            Logger.getLogger(DriverFactory.class);

    private DriverFactory() {

    }

    public static void initializeDriver(
            String browser,
            String engine) {

        log.info(
                "Initializing Driver");

        log.info(
                "Engine : " + engine);

        log.info(
                "Browser : " + browser);

        try {

            if (engine.equalsIgnoreCase(
                    "selenium")) {

                WebDriver driver =
                        BrowserFactory
                        .getBrowser(browser);

                /*
                 * IMPORTANT FIX
                 */

                DriverManager.setDriver(driver);

                driver.manage()
                        .window()
                        .maximize();

                driver.manage()
                        .timeouts()
                        .implicitlyWait(
                                Duration.ofSeconds(10));

                log.info(
                        "Selenium Driver Initialized");
            }

            else if (engine.equalsIgnoreCase(
                    "playwright")) {

                PlaywrightFactory
                        .initializePlaywright();

                log.info(
                        "Playwright Initialized");
            }

            else if (engine.equalsIgnoreCase(
                    "api")) {

                APIFactory
                        .initializeAPIEngine();

                log.info(
                        "API Engine Initialized");
            }

            else {

                throw new RuntimeException(
                        "Unsupported Engine : "
                        + engine);
            }

        }

        catch (Exception e) {

            log.error(
                    "Driver Initialization Failed",
                    e);

            throw e;
        }
    }

    public static void quitDriver() {

        try {

            if (DriverManager.getDriver()
                    != null) {

                DriverManager
                        .getDriver()
                        .quit();

                log.info(
                        "Driver Closed Successfully");
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Close Driver",
                    e);
        }

        finally {

            DriverManager.unload();
        }
    }
}