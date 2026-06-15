package com.seleapi.playwright;

import com.microsoft.playwright.Page;

public class PlaywrightManager {

    private PlaywrightManager() {

    }

    /*
     * THREAD LOCAL PAGE
     */

    private static ThreadLocal<Page> tlPage =

            new ThreadLocal<>();

    /*
     * SET PAGE
     */

    public static void setPage(Page page) {

        tlPage.set(page);
    }

    /*
     * GET PAGE
     */

    public static Page getPage() {

        return tlPage.get();
    }

    /*
     * REMOVE PAGE
     */

    public static void unload() {

        tlPage.remove();
    }
}