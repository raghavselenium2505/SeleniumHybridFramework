package com.seleapi.config;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.seleapi.core.FrameworkConstants;

public class ConfigManager {

    public static Logger log =
            Logger.getLogger(ConfigManager.class);

    private static Properties properties =
            new Properties();

    public static void loadProperties() {

        try {

            FileInputStream fis =
                    new FileInputStream(
                            FrameworkConstants.CONFIG_PATH);

            properties.load(fis);

            log.info("Configuration Properties Loaded Successfully");

        } catch (Exception e) {

            log.error("Failed To Load Config Properties", e);

            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}