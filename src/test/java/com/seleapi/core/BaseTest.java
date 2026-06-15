package com.seleapi.core;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.seleapi.aws.AWSReportUploader;
import com.seleapi.config.EnvironmentManager;
import com.seleapi.dashboard.DashboardManager;
import com.seleapi.dashboard.DashboardReportGenerator;
import com.seleapi.driver.DriverManager;
import com.seleapi.engines.EngineExecutionManager;
import com.seleapi.history.ExecutionHistoryManager;
import com.seleapi.reporting.ExtentManager;
import com.seleapi.utils.ConfigReader;

public class BaseTest {

    public static Logger log =
            Logger.getLogger(
                    BaseTest.class);

    /*
     * BEFORE SUITE
     */

    @BeforeSuite(alwaysRun = true)

    public void beforeSuite() {

        try {

            log.info(
                    "========== BEFORE SUITE STARTED ==========");

            /*
             * LOAD CONFIG FILE
             */

            ConfigReader.loadProperties();

            log.info(
                    "Config Properties Loaded Successfully");

            /*
             * INITIALIZE FRAMEWORK
             */

            FrameworkManager.initializeFramework();

            log.info(
                    "Framework Initialized Successfully");

            /*
             * INITIALIZE EXTENT REPORT
             */

            ExtentManager.initializeReport();

            log.info(
                    "Extent Report Initialized Successfully");

            /*
             * RESET DASHBOARD
             */

            //DashboardManager.resetDashboard();

            log.info(
                    "Dashboard Reset Completed");

            log.info(
                    "========== FRAMEWORK INITIALIZATION COMPLETED ==========");

        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING BEFORE SUITE",
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
                    "========== TEST SETUP STARTED ==========");

            /*
             * CREATE EXTENT TEST
             */

            ExtentManager.createTest(
                    method.getName());

            log.info(
                    "Extent Test Created : "
                    + method.getName());

            /*
             * FETCH VALUES FROM CONFIG
             */

            String finalBrowser =

                    ConfigReader
                    .getProperty(
                            "browser");

            String finalEngine =

                    ConfigReader
                    .getProperty(
                            "engine");

            String finalUrl =

                    EnvironmentManager
                    .getApplicationUrl();

            log.info(
                    "Browser : "
                    + finalBrowser);

            log.info(
                    "Engine : "
                    + finalEngine);

            log.info(
                    "Application URL : "
                    + finalUrl);

            /*
             * ENGINE VALIDATION
             */

            String packageName =

                    method.getDeclaringClass()
                          .getPackage()
                          .getName();

            /*
             * API VALIDATION
             */

            if (packageName.contains(".api")

                    && !finalEngine.equalsIgnoreCase(
                            "api")) {

                String errorMessage =

                        "INVALID EXECUTION : "
                        + "API Tests Require engine=api";

                log.error(
                        errorMessage);

                ExtentManager.fail(
                        errorMessage);

                throw new RuntimeException(
                        errorMessage);
            }

            /*
             * UI VALIDATION
             */

            if (packageName.contains(".ui")

                    && !finalEngine.equalsIgnoreCase(
                            "selenium")) {

                String errorMessage =

                        "INVALID EXECUTION : "
                        + "UI Tests Require engine=selenium";

                log.error(
                        errorMessage);

                ExtentManager.fail(
                        errorMessage);

                throw new RuntimeException(
                        errorMessage);
            }

            /*
             * INITIALIZE ENGINE
             */

            EngineExecutionManager
            .initializeEngine(

                    finalEngine,

                    finalBrowser,

                    finalUrl);

            log.info(
                    "Engine Initialized Successfully");

            /*
             * OPEN APPLICATION
             */

            if (finalEngine.equalsIgnoreCase(
                    "selenium")) {

                DriverManager
                        .getDriver()
                        .get(finalUrl);

                log.info(
                        "Application Launched Successfully");
            }

            /*
             * API ENGINE
             */

            else if (finalEngine.equalsIgnoreCase(
                    "api")) {

                log.info(
                        "API Engine Selected - Browser Launch Skipped");
            }

            /*
             * DASHBOARD START TEST
             */

            DashboardManager.startTest();

            log.info(
                    "Dashboard Test Started");

            /*
             * STORE EXECUTION CONTEXT
             */

            ExecutionContext.setTestName(
                    method.getName());

            ExecutionContext.setBrowser(
                    finalBrowser);

            ExecutionContext.setEngine(
                    finalEngine);

            ExecutionContext.setEnvironment(

                    ConfigReader
                    .getProperty(
                            "env"));

            log.info(
                    "Execution Context Stored Successfully");

            log.info(
                    "========== TEST SETUP COMPLETED ==========");

        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING TEST SETUP",
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
                    "========== TEST TEARDOWN STARTED ==========");

            /*
             * TEST PASSED
             */

            if (result.getStatus()
                    == ITestResult.SUCCESS) {

                ExtentManager.pass(
                        "Test Passed");

                DashboardManager
                .testPassed();

                log.info(
                        "TEST PASSED : "
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

                log.error(
                        "TEST FAILED : "
                        + testName,
                        result.getThrowable());

                /*
                 * CAPTURE SCREENSHOT
                 */

                if (ExecutionContext
                        .getEngine()
                        != null

                        && ExecutionContext
                        .getEngine()
                        .equalsIgnoreCase(
                                "selenium")

                        && DriverManager.getDriver()
                        != null) {

                    String screenshotPath =

                            ScreenshotManager
                            .captureScreenshot(
                                    testName);

                    ExtentManager
                    .attachFailureScreenshot(
                            screenshotPath);

                    log.info(
                            "Failure Screenshot Attached Successfully");
                }
            }

            /*
             * TEST SKIPPED
             */

            else if (result.getStatus()
                    == ITestResult.SKIP) {

                ExtentManager.skip(
                        "Test Skipped");

                DashboardManager
                .testSkipped();

                log.warn(
                        "TEST SKIPPED : "
                        + testName);
            }

        }

        catch (Exception e) {

            log.error(
                    "ERROR DURING TEST TEARDOWN",
                    e);
        }

        finally {

            /*
             * QUIT ENGINE
             */

            try {

                EngineExecutionManager
                .quitEngine(
                        ExecutionContext
                        .getEngine());

                log.info(
                        "ENGINE CLEANUP COMPLETED");

            }

            catch (Exception e) {

                log.error(
                        "ENGINE CLEANUP FAILED",
                        e);
            }

            /*
             * FLUSH EXTENT REPORT
             */

            try {

                ExtentManager.flushReport();

                log.info(
                        "EXTENT REPORT FLUSHED");

            }

            catch (Exception e) {

                log.error(
                        "EXTENT REPORT FLUSH FAILED",
                        e);
            }

            /*
             * AWS REPORT UPLOAD
             */

            try {

                String bucketName =

                        ConfigReader
                        .getProperty(
                                "aws.bucketName");

                String region =

                        ConfigReader
                        .getProperty(
                                "aws.region");

                String timeStamp =

                        new java.text.SimpleDateFormat(
                                "yyyyMMdd_HHmmss")

                        .format(
                                new java.util.Date());

                String fileName =

                        "API_ExtentReport_"
                        + timeStamp
                        + ".html";

                String s3Key =

                        "reports/api/"
                        + fileName;

                /*
                 * CURRENT EXECUTION REPORT
                 */

                String reportPath =

                        ExtentManager
                        .getReportPath();

                log.info(
                        "CURRENT REPORT PATH : "
                        + reportPath);

                /*
                 * AWS UPLOAD
                 */

                AWSReportUploader
                .uploadSingleFileToS3(
                        reportPath,
                        s3Key);

                /*
                 * FINAL URL
                 */

                String finalUrl =

                        "https://"
                        + bucketName
                        + ".s3."
                        + region
                        + ".amazonaws.com/"
                        + s3Key;

                log.info(
                        "AWS REPORT URL : "
                        + finalUrl);
                
                DashboardManager
                .setAwsReportUrl(
                        finalUrl);

            }

            catch (Exception e) {

                log.error(
                        "AWS UPLOAD FAILED",
                        e);
            }

            /*
             * CLEAR EXECUTION CONTEXT
             */

            try {

                ExecutionContext.unload();

                log.info(
                        "EXECUTION CONTEXT CLEARED");

            }

            catch (Exception e) {

                log.error(
                        "EXECUTION CONTEXT CLEAR FAILED",
                        e);
            }

            log.info(
                    "========== TEST TEARDOWN COMPLETED ==========");
        }
    }
    /*
     * AFTER SUITE
     */

    /*
     * AFTER SUITE
     */

    /*
     * AFTER SUITE
     */

    @AfterSuite(alwaysRun = true)

    public void afterSuite() {

        log.info(
                "========== AFTER SUITE STARTED ==========");

        /*
         * FLUSH EXTENT REPORT
         */

        try {

            ExtentManager.flushReport();

            log.info(
                    "Extent Report Flushed Successfully");

        }

        catch (Exception e) {

            log.error(
                    "Extent Report Flush Failed",
                    e);
        }

        /*
         * GENERATE DASHBOARD
         */

        try {

            DashboardReportGenerator
            .generateDashboard();

            log.info(
                    "Dashboard Generated Successfully");

        }

        catch (Exception e) {

            log.error(
                    "Dashboard Generation Failed",
                    e);
        }

        /*
         * SAVE EXECUTION HISTORY
         */

        try {

            ExecutionHistoryManager
            .saveExecutionHistory();

            log.info(
                    "Execution History Saved Successfully");

        }

        catch (Exception e) {

            log.error(
                    "Execution History Save Failed",
                    e);
        }

        /*
         * UPLOAD EXTENT REPORT
         */

        /*
         * AUTO DETECT GENERATED REPORT
         */
        File reportFolder =

                new File(

                        System.getProperty(
                                "user.dir")

                        + "/Reports/API");

        File[] reportFiles =

                reportFolder.listFiles(

                        (dir, name)

                        -> name.toLowerCase()
                               .endsWith(".html"));

        if (reportFiles == null
                || reportFiles.length == 0) {

            throw new RuntimeException(
                    "No Extent HTML Report Found");
        }

        /*
         * PICK LATEST REPORT
         */

        File latestReport =

                reportFiles[0];

        for (File file : reportFiles) {

            if (file.lastModified()
                    > latestReport.lastModified()) {

                latestReport = file;
            }
        }

        String reportPath =

                latestReport.getAbsolutePath();

        log.info(
                "Detected Extent Report : "
                + reportPath);
    }
}