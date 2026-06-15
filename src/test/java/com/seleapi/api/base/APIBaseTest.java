package com.seleapi.api.base;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.seleapi.core.ExecutionContext;
import com.seleapi.dashboard.DashboardManager;
import com.seleapi.dashboard.DashboardReportGenerator;
import com.seleapi.reporting.ExtentManager;
import com.seleapi.utils.ConfigReader;

public class APIBaseTest {

    public static Logger log =
            Logger.getLogger(
                    APIBaseTest.class);

    /*
     * BEFORE SUITE
     */

    @BeforeSuite(alwaysRun = true)

    public void beforeSuite() {

        try {

            log.info(
                    "========== API BEFORE SUITE STARTED ==========");

            /*
             * LOAD CONFIG
             */

            ConfigReader.loadProperties();

            log.info(
                    "Config Properties Loaded Successfully");

            /*
             * INITIALIZE EXTENT REPORT
             */

            ExtentManager.initializeReport();

            log.info(
                    "Extent Report Initialized");

            /*
             * RESET DASHBOARD
             */

            DashboardManager.resetDashboard();

            log.info(
                    "Dashboard Reset Completed");

            log.info(
                    "========== API FRAMEWORK INITIALIZED ==========");
        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING API BEFORE SUITE",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * BEFORE METHOD
     */

    @BeforeMethod(alwaysRun = true)

    public void setUp(
            Method method) {

        try {

            log.info(
                    "========== API TEST SETUP STARTED ==========");

            /*
             * CREATE EXTENT TEST
             */

            ExtentManager.createTest(
                    method.getName());

            log.info(
                    "Extent Test Created : "
                    + method.getName());

            /*
             * START DASHBOARD TEST
             */

            DashboardManager.startTest();

            log.info(
                    "Dashboard Test Started");

            /*
             * STORE EXECUTION CONTEXT
             */

            ExecutionContext.setTestName(
                    method.getName());

            ExecutionContext.setEngine(
                    "API");

            ExecutionContext.setEnvironment(

                    ConfigReader
                    .getProperty(
                            "env"));

            log.info(
                    "Execution Context Initialized");

            log.info(
                    "Engine : API");

            log.info(
                    "Environment : "
                    + ConfigReader
                    .getProperty(
                            "env"));

            log.info(
                    "========== API TEST SETUP COMPLETED ==========");
        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING API TEST SETUP",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * AFTER METHOD
     */

    @AfterMethod(alwaysRun = true)

    public void tearDown(
            ITestResult result) {

        try {

            String testName =

                    result.getMethod()
                          .getMethodName();

            log.info(
                    "========== API TEST TEARDOWN STARTED ==========");

            /*
             * TEST PASSED
             */

            if (result.getStatus()
                    == ITestResult.SUCCESS) {

                ExtentManager.pass(
                        "API Test Passed");

                DashboardManager
                .testPassed();

                DashboardManager
                .apiPassed();

                log.info(
                        "API TEST PASSED : "
                        + testName);
            }

            /*
             * TEST FAILED
             */

            else if (result.getStatus()
                    == ITestResult.FAILURE) {

                ExtentManager.fail(
                        result.getThrowable());

                DashboardManager
                .testFailed();

                DashboardManager
                .apiFailed();

                log.error(
                        "API TEST FAILED : "
                        + testName,
                        result.getThrowable());
            }

            /*
             * TEST SKIPPED
             */

            else if (result.getStatus()
                    == ITestResult.SKIP) {

                ExtentManager.skip(
                        "API Test Skipped");

                DashboardManager
                .testSkipped();

                log.warn(
                        "API TEST SKIPPED : "
                        + testName);
            }

        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING API TEST TEARDOWN",
                    e);
        }

        finally {

            /*
             * CLEAR EXECUTION CONTEXT
             */

            ExecutionContext.unload();

            log.info(
                    "Execution Context Cleared");

            log.info(
                    "========== API TEST TEARDOWN COMPLETED ==========");
        }
    }

    /*
     * AFTER SUITE
     */

    @AfterSuite(alwaysRun = true)

    public void afterSuite() {

        try {

            log.info(
                    "========== API AFTER SUITE STARTED ==========");

            /*
             * FLUSH EXTENT REPORT
             */

            ExtentManager.flushReport();

            log.info(
                    "Extent Report Flushed Successfully");

            /*
             * GENERATE DASHBOARD
             */

            DashboardReportGenerator
            .generateDashboard();

            log.info(
                    "Dashboard Generated Successfully");

            log.info(
                    "========== API SUITE COMPLETED ==========");
        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING API AFTER SUITE",
                    e);

            throw new RuntimeException(e);
        }
    }
}