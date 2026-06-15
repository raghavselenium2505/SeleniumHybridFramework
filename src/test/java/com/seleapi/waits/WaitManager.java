package com.seleapi.waits;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.seleapi.driver.DriverManager;

public class WaitManager {

    public static Logger log =
            Logger.getLogger(WaitManager.class);

    private WaitManager() {

    }

    public static WebElement waitForElementVisible(
            By locator,
            int timeout) {

        try {

            log.info(
                    "Waiting For Element Visibility : "
                    + locator);

            WebDriverWait wait =
                    new WebDriverWait(
                            DriverManager.getDriver(),
                            Duration.ofSeconds(timeout));

            return wait.until(
                    ExpectedConditions
                    .visibilityOfElementLocated(locator));

        } catch (Exception e) {

            log.error(
                    "Element Not Visible : "
                    + locator,
                    e);

            throw e;
        }
    }

    public static WebElement waitForElementClickable(
            By locator,
            int timeout) {

        try {

            log.info(
                    "Waiting For Element Clickable : "
                    + locator);

            WebDriverWait wait =
                    new WebDriverWait(
                            DriverManager.getDriver(),
                            Duration.ofSeconds(timeout));

            return wait.until(
                    ExpectedConditions
                    .elementToBeClickable(locator));

        } catch (Exception e) {

            log.error(
                    "Element Not Clickable : "
                    + locator,
                    e);

            throw e;
        }
    }

    public static boolean waitForUrlContains(
            String partialUrl,
            int timeout) {

        try {

            log.info(
                    "Waiting For URL Contains : "
                    + partialUrl);

            WebDriverWait wait =
                    new WebDriverWait(
                            DriverManager.getDriver(),
                            Duration.ofSeconds(timeout));

            return wait.until(
                    ExpectedConditions
                    .urlContains(partialUrl));

        } catch (Exception e) {

            log.error(
                    "URL Validation Failed",
                    e);

            return false;
        }
    }

    public static boolean waitForTitleContains(
            String title,
            int timeout) {

        try {

            log.info(
                    "Waiting For Title : "
                    + title);

            WebDriverWait wait =
                    new WebDriverWait(
                            DriverManager.getDriver(),
                            Duration.ofSeconds(timeout));

            return wait.until(
                    ExpectedConditions
                    .titleContains(title));

        } catch (Exception e) {

            log.error(
                    "Title Validation Failed",
                    e);

            return false;
        }
    }
}