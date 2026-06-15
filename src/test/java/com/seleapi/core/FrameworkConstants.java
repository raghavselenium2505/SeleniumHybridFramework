package com.seleapi.core;

public class FrameworkConstants {

    private FrameworkConstants() {

    }

    public static final String CONFIG_PATH =
            System.getProperty("user.dir")
            + "/src/test/resources/properties/Config.properties";

    public static final String REPORT_PATH =
            System.getProperty("user.dir")
            + "/reports/";

    public static final int DEFAULT_TIMEOUT = 20;

    public static final String PLATFORM_NAME =
            "SeleAPI";
}