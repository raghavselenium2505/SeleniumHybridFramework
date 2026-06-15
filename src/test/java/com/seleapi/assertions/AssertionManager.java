package com.seleapi.assertions;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.seleapi.reporting.ExtentManager;

public class AssertionManager {

    public static Logger log =
            Logger.getLogger(AssertionManager.class);

    private AssertionManager() {

    }

    public static void verifyEquals(
            String actual,
            String expected,
            String message) {

        try {

            Assert.assertEquals(actual, expected);

            log.info(
                    "ASSERTION PASSED : "
                    + message);

            ExtentManager.pass(
                    "ASSERTION PASSED : "
                    + message);

        } catch (AssertionError e) {

            log.error(
                    "ASSERTION FAILED : "
                    + message);

            ExtentManager.fail(
                    "ASSERTION FAILED : "
                    + message);

            throw e;
        }
    }

    public static void verifyTrue(
            boolean condition,
            String message) {

        try {

            Assert.assertTrue(condition);

            log.info(
                    "ASSERTION PASSED : "
                    + message);

            ExtentManager.pass(
                    "ASSERTION PASSED : "
                    + message);

        } catch (AssertionError e) {

            log.error(
                    "ASSERTION FAILED : "
                    + message);

            ExtentManager.fail(
                    "ASSERTION FAILED : "
                    + message);

            throw e;
        }
    }

    public static void verifyFalse(
            boolean condition,
            String message) {

        try {

            Assert.assertFalse(condition);

            log.info(
                    "ASSERTION PASSED : "
                    + message);

            ExtentManager.pass(
                    "ASSERTION PASSED : "
                    + message);

        } catch (AssertionError e) {

            log.error(
                    "ASSERTION FAILED : "
                    + message);

            ExtentManager.fail(
                    "ASSERTION FAILED : "
                    + message);

            throw e;
        }
    }

    public static void verifyNotNull(
            Object object,
            String message) {

        try {

            Assert.assertNotNull(object);

            log.info(
                    "ASSERTION PASSED : "
                    + message);

            ExtentManager.pass(
                    "ASSERTION PASSED : "
                    + message);

        } catch (AssertionError e) {

            log.error(
                    "ASSERTION FAILED : "
                    + message);

            ExtentManager.fail(
                    "ASSERTION FAILED : "
                    + message);

            throw e;
        }
    }
}