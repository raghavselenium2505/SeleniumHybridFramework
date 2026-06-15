package com.seleapi.actions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.seleapi.driver.DriverManager;
import com.seleapi.reporting.ExtentManager;
import com.seleapi.waits.WaitManager;

public class ElementActions {

    public static Logger log =
            Logger.getLogger(ElementActions.class);

    private ElementActions() {

    }

    public static void click(By locator) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementClickable(
                            locator,
                            50);

            element.click();

            log.info(
                    "Clicked Element : "
                    + locator);

            ExtentManager.info(
                    "Clicked Element : "
                    + locator);

        } catch (Exception e) {

            log.error(
                    "Failed To Click Element : "
                    + locator,
                    e);

            ExtentManager.fail(
                    "Failed To Click Element : "
                    + locator);

            throw e;
        }
    }

    public static void type(By locator,
                            String value) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementVisible(
                            locator,
                            20);

            element.clear();

            element.sendKeys(value);

            log.info(
                    "Entered Value : "
                    + value);

            ExtentManager.info(
                    "Entered Value : "
                    + value);

        } catch (Exception e) {

            log.error(
                    "Failed To Enter Value",
                    e);

            ExtentManager.fail(
                    "Failed To Enter Value");

            throw e;
        }
    }

    public static String getText(By locator) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementVisible(
                            locator,
                            20);

            String text =
                    element.getText();

            log.info(
                    "Fetched Text : "
                    + text);

            return text;

        } catch (Exception e) {

            log.error(
                    "Failed To Fetch Text",
                    e);

            throw e;
        }
    }

    public static boolean isDisplayed(By locator) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementVisible(
                            locator,
                            20);

            return element.isDisplayed();

        } catch (Exception e) {

            log.error(
                    "Element Not Displayed",
                    e);

            return false;
        }
    }

    public static void jsClick(By locator) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementClickable(
                            locator,
                            20);

            JavascriptExecutor js =
                    (JavascriptExecutor)
                    DriverManager.getDriver();

            js.executeScript(
                    "arguments[0].click();",
                    element);

            log.info(
                    "JS Click Performed : "
                    + locator);

        } catch (Exception e) {

            log.error(
                    "JS Click Failed",
                    e);

            throw e;
        }
    }

    public static void hover(By locator) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementVisible(
                            locator,
                            20);

            Actions actions =
                    new Actions(
                            DriverManager.getDriver());

            actions.moveToElement(element)
                    .perform();

            log.info(
                    "Hover Performed : "
                    + locator);

        } catch (Exception e) {

            log.error(
                    "Hover Failed",
                    e);

            throw e;
        }
    }

    public static void scrollIntoView(By locator) {

        try {

            WebElement element =
                    WaitManager
                    .waitForElementVisible(
                            locator,
                            20);

            JavascriptExecutor js =
                    (JavascriptExecutor)
                    DriverManager.getDriver();

            js.executeScript(
                    "arguments[0].scrollIntoView(true);",
                    element);

            log.info(
                    "Scrolled To Element : "
                    + locator);

        } catch (Exception e) {

            log.error(
                    "Scroll Failed",
                    e);

            throw e;
        }
    }
}