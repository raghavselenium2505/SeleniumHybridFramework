package com.seleapi.playwright;

import org.apache.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;

public class PlaywrightActions {

    public static Logger log =

            Logger.getLogger(
                    PlaywrightActions.class);

    private PlaywrightActions() {

    }

    /*
     * GET PAGE
     */

    private static Page getPage() {

        return PlaywrightFactory
                .getPage();
    }

    /*
     * NAVIGATE
     */

    public static void navigate(
            String url) {

        try {

            log.info(
                    "Navigating To URL : "
                    + url);

            getPage()
            .navigate(
                    url);

            log.info(
                    "Navigation Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Navigate",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * CLICK
     */

    public static void click(
            String locator) {

        try {

            log.info(
                    "Clicking Element : "
                    + locator);

            getPage()
            .locator(locator)
            .click();

            log.info(
                    "Element Clicked Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Click Element",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * DOUBLE CLICK
     */

    public static void doubleClick(
            String locator) {

        try {

            log.info(
                    "Double Clicking Element : "
                    + locator);

            getPage()
            .locator(locator)
            .dblclick();

            log.info(
                    "Double Click Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Double Click",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * RIGHT CLICK
     */

    public static void rightClick(
            String locator) {

        try {

            log.info(
                    "Right Clicking Element : "
                    + locator);

            getPage()
            .locator(locator)
            .click(

                    new Locator
                    .ClickOptions()

                    .setButton(
                            MouseButton.RIGHT));

            log.info(
                    "Right Click Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Right Click",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * TYPE
     */

    public static void type(
            String locator,
            String text) {

        try {

            log.info(
                    "Typing Text : "
                    + text);

            getPage()
            .locator(locator)
            .fill(text);

            log.info(
                    "Text Entered Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Type Text",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * CLEAR
     */

    public static void clear(
            String locator) {

        try {

            log.info(
                    "Clearing Element : "
                    + locator);

            getPage()
            .locator(locator)
            .clear();

            log.info(
                    "Element Cleared Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Clear Element",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * GET TEXT
     */

    public static String getText(
            String locator) {

        try {

            String text =

                    getPage()
                    .locator(locator)
                    .textContent();

            log.info(
                    "Element Text : "
                    + text);

            return text;
        }

        catch (Exception e) {

            log.error(
                    "Failed To Get Text",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * GET TITLE
     */

    public static String getTitle() {

        try {

            String title =

                    getPage()
                    .title();

            log.info(
                    "Page Title : "
                    + title);

            return title;
        }

        catch (Exception e) {

            log.error(
                    "Failed To Get Page Title",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * IS VISIBLE
     */

    public static boolean isVisible(
            String locator) {

        try {

            boolean visible =

                    getPage()
                    .locator(locator)
                    .isVisible();

            log.info(
                    "Element Visibility : "
                    + visible);

            return visible;
        }

        catch (Exception e) {

            log.error(
                    "Failed To Check Visibility",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * WAIT FOR ELEMENT
     */

    public static void waitForElement(
            String locator) {

        try {

            log.info(
                    "Waiting For Element : "
                    + locator);

            getPage()
            .locator(locator)
            .waitFor();

            log.info(
                    "Element Available");
        }

        catch (Exception e) {

            log.error(
                    "Failed Waiting For Element",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * HOVER
     */

    public static void hover(
            String locator) {

        try {

            log.info(
                    "Hovering On Element : "
                    + locator);

            getPage()
            .locator(locator)
            .hover();

            log.info(
                    "Hover Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Hover",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * SCROLL INTO VIEW
     */

    public static void scrollIntoView(
            String locator) {

        try {

            log.info(
                    "Scrolling To Element : "
                    + locator);

            getPage()
            .locator(locator)
            .scrollIntoViewIfNeeded();

            log.info(
                    "Scroll Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Scroll",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * PRESS KEY
     */

    public static void pressKey(
            String locator,
            String key) {

        try {

            log.info(
                    "Pressing Key : "
                    + key);

            getPage()
            .locator(locator)
            .press(key);

            log.info(
                    "Key Press Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Press Key",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * SELECT DROPDOWN
     */

    public static void selectDropdown(
            String locator,
            String value) {

        try {

            log.info(
                    "Selecting Dropdown Value : "
                    + value);

            getPage()
            .locator(locator)
            .selectOption(value);

            log.info(
                    "Dropdown Selection Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Select Dropdown",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * CHECK CHECKBOX
     */

    public static void checkCheckbox(
            String locator) {

        try {

            log.info(
                    "Checking Checkbox : "
                    + locator);

            getPage()
            .locator(locator)
            .check();

            log.info(
                    "Checkbox Checked Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Check Checkbox",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * UNCHECK CHECKBOX
     */

    public static void uncheckCheckbox(
            String locator) {

        try {

            log.info(
                    "Unchecking Checkbox : "
                    + locator);

            getPage()
            .locator(locator)
            .uncheck();

            log.info(
                    "Checkbox Unchecked Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Uncheck Checkbox",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * UPLOAD FILE
     */

    public static void uploadFile(
            String locator,
            String filePath) {

        try {

            log.info(
                    "Uploading File : "
                    + filePath);

            getPage()
            .locator(locator)
            .setInputFiles(
                    java.nio.file.Paths.get(filePath));

            log.info(
                    "File Uploaded Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Upload File",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * REFRESH PAGE
     */

    public static void refreshPage() {

        try {

            log.info(
                    "Refreshing Page");

            getPage()
            .reload();

            log.info(
                    "Page Refreshed Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Refresh Page",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * GO BACK
     */

    public static void goBack() {

        try {

            log.info(
                    "Navigating Back");

            getPage()
            .goBack();

            log.info(
                    "Back Navigation Successful");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Navigate Back",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * CLOSE PAGE
     */

    public static void closePage() {

        try {

            getPage()
            .close();

            log.info(
                    "Page Closed Successfully");
        }

        catch (Exception e) {

            log.error(
                    "Failed To Close Page",
                    e);
        }
    }
}