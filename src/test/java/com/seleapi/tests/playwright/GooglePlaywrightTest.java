package com.seleapi.tests.playwright;

import org.testng.annotations.Test;

import com.seleapi.core.BaseTest;
import com.seleapi.playwright.PlaywrightActions;

public class GooglePlaywrightTest
        extends BaseTest {

    @Test

    public void googleSearchTest() {

        /*
         * NAVIGATE
         */

        PlaywrightActions
        .navigate(
                "https://www.google.com");

        /*
         * WAIT FOR SEARCH BOX
         */

        PlaywrightActions
        .waitForElement(
                "textarea[name='q']");

        /*
         * TYPE SEARCH
         */

        PlaywrightActions
        .type(
                "textarea[name='q']",
                "Playwright Java Automation");

        /*
         * PRESS ENTER
         */

        PlaywrightActions
        .pressKey(
                "textarea[name='q']",
                "Enter");

        /*
         * WAIT FOR RESULTS
         */

        PlaywrightActions
        .waitForElement(
                "h3");

        /*
         * VERIFY RESULTS
         */

        boolean visible =

                PlaywrightActions
                .isVisible(
                        "h3");

        System.out.println(
                "SEARCH RESULTS VISIBLE : "
                + visible);

        /*
         * GET PAGE TITLE
         */

        String title =

                PlaywrightActions
                .getTitle();

        System.out.println(
                "PAGE TITLE : "
                + title);

        /*
         * REFRESH PAGE
         */

        PlaywrightActions
        .refreshPage();

        /*
         * NAVIGATE BACK
         */

        PlaywrightActions
        .goBack();
    }
}