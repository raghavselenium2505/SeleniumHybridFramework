package com.seleapi.browser;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.seleapi.utils.ConfigReader;

public class BrowserManager {

    public static Logger log =
            Logger.getLogger(
                    BrowserManager.class);

    private static WebDriver driver;

    private BrowserManager() {

    }

    /*
     * GET DRIVER
     */

    public static WebDriver getDriver() {

        if (driver == null) {

            String browser =

                    ConfigReader
                    .getProperty("browser");

            switch(browser.toLowerCase()) {

            case "chrome":

                driver =
                        new ChromeDriver(

                                BrowserOptionsManager
                                .getChromeOptions());

                log.info(
                        "Chrome Browser Launched");

                break;

            case "firefox":

                driver =
                        new FirefoxDriver(

                                BrowserOptionsManager
                                .getFirefoxOptions());

                log.info(
                        "Firefox Browser Launched");

                break;

            case "edge":

                driver =
                        new EdgeDriver(

                                BrowserOptionsManager
                                .getEdgeOptions());

                log.info(
                        "Edge Browser Launched");

                break;

            default:

                throw new RuntimeException(
                        "Invalid Browser");
            }
        }

        return driver;
    }

    /*
     * QUIT BROWSER
     */

    public static void quitBrowser() {

        if(driver != null) {

            driver.quit();

            log.info(
                    "Browser Closed");
        }
    }
}