package com.seleapi.core;

public class ExecutionContext {

    /*
     * TEST NAME
     */

    private static ThreadLocal<String> testName =
            new ThreadLocal<>();

    /*
     * ENGINE
     */

    private static ThreadLocal<String> engine =
            new ThreadLocal<>();

    /*
     * BROWSER
     */

    private static ThreadLocal<String> browser =
            new ThreadLocal<>();

    /*
     * ENVIRONMENT
     */

    private static ThreadLocal<String> environment =
            new ThreadLocal<>();

    /*
     * API ENDPOINT
     */

    private static ThreadLocal<String> apiEndpoint =
            new ThreadLocal<>();

    /*
     * SET TEST NAME
     */

    public static void setTestName(
            String name) {

        testName.set(name);
    }

    public static String getTestName() {

        return testName.get();
    }

    /*
     * SET ENGINE
     */

    public static void setEngine(
            String executionEngine) {

        engine.set(
                executionEngine);
    }

    public static String getEngine() {

        return engine.get();
    }

    /*
     * SET BROWSER
     */

    public static void setBrowser(
            String executionBrowser) {

        browser.set(
                executionBrowser);
    }

    public static String getBrowser() {

        return browser.get();
    }

    /*
     * SET ENVIRONMENT
     */

    public static void setEnvironment(
            String env) {

        environment.set(env);
    }

    public static String getEnvironment() {

        return environment.get();
    }

    /*
     * API ENDPOINT
     */

    public static void setApiEndpoint(
            String endpoint) {

        apiEndpoint.set(endpoint);
    }

    public static String getApiEndpoint() {

        return apiEndpoint.get();
    }

    /*
     * CLEAR THREAD
     */

    public static void unload() {

        testName.remove();

        engine.remove();

        browser.remove();

        environment.remove();

        apiEndpoint.remove();
    }
}