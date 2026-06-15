package com.seleapi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigReader {

    public static Logger log =
            Logger.getLogger(ConfigReader.class);

    /*
     * PROPERTIES OBJECT
     */

    private static Properties properties =
            new Properties();

    /*
     * CONFIG FILE PATH
     */

    private static final String CONFIG_PATH =

            System.getProperty("user.dir")

            + "/src/test/resources/properties/Config.properties";

    /*
     * STATIC BLOCK
     */

    static {

        loadProperties();
    }

    /*
     * PRIVATE CONSTRUCTOR
     */

    private ConfigReader() {

    }

    /*
     * LOAD CONFIG PROPERTIES
     */

    public static void loadProperties() {

        try (FileInputStream fis =
                     new FileInputStream(CONFIG_PATH)) {

            properties.load(fis);

            log.info(
                    "Config Properties Loaded Successfully");

        }

        catch (IOException e) {

            log.error(
                    "Failed To Load Config Properties",
                    e);

            throw new RuntimeException(
                    "Unable To Load Config Properties",
                    e);
        }
    }

    /*
     * GET PROPERTY
     */

    public static String getProperty(
            String key) {

        String value =
                properties.getProperty(key);

        log.info(
                "Fetching Config Value : "
                + key
                + " = "
                + value);

        return value;
    }

    /*
     * GET PROPERTY WITH DEFAULT
     */

    public static String getProperty(
            String key,
            String defaultValue) {

        String value =
                properties.getProperty(
                        key,
                        defaultValue);

        log.info(
                "Fetching Config Value : "
                + key
                + " = "
                + value);

        return value;
    }

    /*
     * XML VALUE OVERRIDE SUPPORT
     * XML VALUE HAS HIGHER PRIORITY
     */

    public static String getPropertyOrDefault(
            String xmlValue,
            String configKey) {

        if (xmlValue != null
                && !xmlValue.trim().isEmpty()) {

            log.info(
                    "Using XML Value For : "
                    + configKey
                    + " = "
                    + xmlValue);

            return xmlValue;
        }

        String configValue =
                getProperty(configKey);

        log.info(
                "Using Config Value For : "
                + configKey
                + " = "
                + configValue);

        return configValue;
    }

    /*
     * GET BOOLEAN PROPERTY
     */

    public static boolean getBooleanProperty(
            String key) {

        return Boolean.parseBoolean(
                getProperty(key));
    }

    /*
     * GET INTEGER PROPERTY
     */

    public static int getIntProperty(
            String key) {

        return Integer.parseInt(
                getProperty(key));
    }

    /*
     * RELOAD CONFIG
     */

    public static void reloadProperties() {

        properties.clear();

        loadProperties();

        log.info(
                "Config Properties Reloaded Successfully");
    }
}