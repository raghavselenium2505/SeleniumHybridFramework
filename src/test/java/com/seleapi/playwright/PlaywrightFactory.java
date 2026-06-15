package com.seleapi.playwright;

import org.apache.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    public static Logger log =

            Logger.getLogger(
                    PlaywrightFactory.class);

    /*
     * PLAYWRIGHT OBJECTS
     */

    private static Playwright playwright;

    private static Browser browser;

    private static BrowserContext context;

    private static Page page;

    private PlaywrightFactory() {

    }

    /*
     * INITIALIZE PLAYWRIGHT
     */

    public static void initializeBrowser() {

        try {

            log.info(
                    "========== PLAYWRIGHT INITIALIZATION STARTED ==========");

            /*
             * CREATE PLAYWRIGHT
             */

            playwright =

                    Playwright.create();

            log.info(
                    "Playwright Instance Created");

            /*
             * LAUNCH CHROMIUM
             */

            browser =

                    playwright.chromium()

                    .launch(

                            new BrowserType
                            .LaunchOptions()

                            .setHeadless(false));

            log.info(
                    "Chromium Browser Launched Successfully");

            /*
             * CREATE CONTEXT
             */

            context =

                    browser.newContext();

            log.info(
                    "Browser Context Created Successfully");

            /*
             * CREATE PAGE
             */

            page =

                    context.newPage();

            log.info(
                    "Playwright Page Created Successfully");

            /*
             * STORE PAGE
             */

            PlaywrightManager
            .setPage(
                    page);

            log.info(
                    "Playwright Page Stored Successfully");

            log.info(
                    "========== PLAYWRIGHT INITIALIZATION COMPLETED ==========");

        }

        catch (Exception e) {

            log.error(
                    "Failed To Initialize Playwright",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * GET PAGE
     */

    public static Page getPage() {

        return PlaywrightManager
                .getPage();
    }

    /*
     * GET CONTEXT
     */

    public static BrowserContext getContext() {

        return context;
    }

    /*
     * GET BROWSER
     */

    public static Browser getBrowser() {

        return browser;
    }

    /*
     * QUIT PLAYWRIGHT
     */

    public static void quitBrowser() {

        try {

            log.info(
                    "========== PLAYWRIGHT TEARDOWN STARTED ==========");

            /*
             * CLOSE PAGE
             */

            if (page != null) {

                page.close();

                log.info(
                        "Playwright Page Closed Successfully");
            }

            /*
             * CLOSE CONTEXT
             */

            if (context != null) {

                context.close();

                log.info(
                        "Browser Context Closed Successfully");
            }

            /*
             * CLOSE BROWSER
             */

            if (browser != null) {

                browser.close();

                log.info(
                        "Playwright Browser Closed Successfully");
            }

            /*
             * CLOSE PLAYWRIGHT
             */

            if (playwright != null) {

                playwright.close();

                log.info(
                        "Playwright Closed Successfully");
            }

            /*
             * CLEAR THREAD LOCAL
             */

            PlaywrightManager
            .unload();

            log.info(
                    "Playwright ThreadLocal Cleared");

            log.info(
                    "========== PLAYWRIGHT TEARDOWN COMPLETED ==========");

        }

        catch (Exception e) {

            log.error(
                    "Failed To Close Playwright",
                    e);
        }
    }
}