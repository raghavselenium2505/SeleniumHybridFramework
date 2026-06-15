package com.seleapi.config;

import org.apache.log4j.Logger;

import com.seleapi.utils.ConfigReader;

public class EnvironmentManager {

    public static Logger log =
            Logger.getLogger(
                    EnvironmentManager.class);

    private EnvironmentManager() {

    }

    /*
     * GET CURRENT ENVIRONMENT
     */

    public static String getEnvironment() {

        String env =
                ConfigReader
                .getProperty("env");

        if (env == null
                || env.trim().isEmpty()) {

            env = "qa";
        }

        log.info(
                "Current Environment : "
                + env);

        return env.toLowerCase();
    }

    /*
     * GET ENVIRONMENT URL
     */

    public static String getApplicationUrl() {

        String env =
                getEnvironment();

        String url =
                ConfigReader
                .getProperty(
                        env + ".url");

        log.info(
                "Environment URL : "
                + url);

        return url;
    }

    /*
     * GET ENVIRONMENT USERNAME
     */

    public static String getUserName() {

        String env =
                getEnvironment();

        String userName =
                ConfigReader
                .getProperty(
                        env + ".username");

        log.info(
                "Environment Username Loaded");

        return userName;
    }

    /*
     * GET ENVIRONMENT PASSWORD
     */

    public static String getPassword() {

        String env =
                getEnvironment();

        String password =
                ConfigReader
                .getProperty(
                        env + ".password");

        log.info(
                "Environment Password Loaded");

        return password;
    }
}