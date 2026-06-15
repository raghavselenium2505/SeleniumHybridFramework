package com.seleapi.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestNG;

public class DynamicTestExecutor {

    private static final Logger logger =
            LogManager.getLogger(DynamicTestExecutor.class);

    public static void executeTest(String className) {

        try {

            logger.info("Loading Class : {}",
                    className);

            Class<?> testClass =
                    Class.forName(className);

            TestNG testng =
                    new TestNG();

            testng.setTestClasses(
                    new Class[] { testClass });

            logger.info("Executing TestNG Test");

            testng.run();

            logger.info("Execution Completed");

        } catch (Exception e) {

            logger.error("Execution Failed");

            e.printStackTrace();
        }
    }
}