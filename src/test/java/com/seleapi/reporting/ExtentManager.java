package com.seleapi.reporting;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static Logger log =
            Logger.getLogger(
                    ExtentManager.class);

    /*
     * EXTENT OBJECT
     */

    private static ExtentReports extent;

    /*
     * THREAD SAFE TEST
     */

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    /*
     * REPORT PATH
     */

    private static String reportPath;

    private ExtentManager() {

    }

    /*
     * INITIALIZE REPORT
     */
    public static void initializeReport() {

        try {

            if (extent == null) {

                /*
                 * TIMESTAMP
                 */

                String timeStamp =

                        new SimpleDateFormat(
                                "yyyyMMdd_HHmmss")
                                .format(new Date());

                /*
                 * ENGINE
                 */

                String engine =

                        com.seleapi.utils.ConfigReader
                        .getProperty(
                                "engine");

                /*
                 * REPORT DIRECTORY
                 */

                String reportDirectory;

                /*
                 * API REPORT
                 */

                if (engine.equalsIgnoreCase(
                        "api")) {

                    reportDirectory =

                            System.getProperty(
                                    "user.dir")

                            + "/Reports/API";

                    System.out.println(
                            "API REPORT DIRECTORY SELECTED");
                }

                /*
                 * UI REPORT
                 */

                else {

                    reportDirectory =

                            System.getProperty(
                                    "user.dir")

                            + "/Reports/UI";

                    System.out.println(
                            "UI REPORT DIRECTORY SELECTED");
                }

                /*
                 * CREATE DIRECTORY
                 */

                File folder =
                        new File(reportDirectory);

                if (!folder.exists()) {

                    folder.mkdirs();

                    System.out.println(
                            "REPORT DIRECTORY CREATED");
                }

                /*
                 * FINAL REPORT PATH
                 */

                reportPath =

                        reportDirectory

                        + "/AutomationReport_"

                        + timeStamp

                        + ".html";

                System.out.println(
                        "REPORT PATH : "
                        + reportPath);

                /*
                 * SPARK REPORTER
                 */

                ExtentSparkReporter spark =

                        new ExtentSparkReporter(
                                reportPath);

                spark.config().setReportName(
                        "SeleAPI Automation Report");

                spark.config().setDocumentTitle(
                        "Execution Report");

                /*
                 * EXTENT OBJECT
                 */

                extent =
                        new ExtentReports();

                extent.attachReporter(
                        spark);

                /*
                 * SYSTEM INFO
                 */

                extent.setSystemInfo(
                        "Framework",
                        "SeleAPI");

                extent.setSystemInfo(
                        "Execution Type",
                        "Enterprise Automation");

                extent.setSystemInfo(
                        "Tester",
                        "Raghav");

                extent.setSystemInfo(
                        "Engine",
                        engine);

                System.out.println(
                        "EXTENT REPORT INITIALIZED SUCCESSFULLY");
            }

        }

        catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    /*
     * CREATE TEST
     */

    public static void createTest(
            String testName) {

        try {

            if (extent == null) {

                initializeReport();
            }

            ExtentTest extentTest =

                    extent.createTest(
                            testName);

            test.set(
                    extentTest);

            log.info(
                    "Extent Test Created : "
                    + testName);

        }

        catch (Exception e) {

            log.error(
                    "Failed To Create Extent Test",
                    e);

            throw new RuntimeException(e);
        }
    }

    /*
     * PASS
     */

    public static void pass(
            String message) {

        if (test.get() != null) {

            test.get().log(
                    Status.PASS,
                    message);
        }
    }

    /*
     * FAIL
     */

    public static void fail(
            String message) {

        if (test.get() != null) {

            test.get().log(
                    Status.FAIL,
                    message);
        }
    }

    /*
     * FAIL WITH EXCEPTION
     */

    public static void fail(
            Throwable throwable) {

        if (test.get() != null) {

            test.get().fail(
                    throwable);
        }
    }

    /*
     * INFO
     */

    public static void info(
            String message) {

        if (test.get() != null) {

            test.get().log(
                    Status.INFO,
                    message);
        }
    }

    /*
     * SKIP
     */

    public static void skip(
            String message) {

        if (test.get() != null) {

            test.get().log(
                    Status.SKIP,
                    message);
        }
    }

    /*
     * ATTACH SCREENSHOT
     */

    public static void attachScreenshot(
            String screenshotPath) {

        try {

            if (test.get() != null) {

                test.get().fail(

                        "Screenshot",

                        MediaEntityBuilder
                        .createScreenCaptureFromPath(
                                screenshotPath)
                        .build());
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Attach Screenshot",
                    e);
        }
    }

    /*
     * ATTACH FAILURE SCREENSHOT
     */

    public static void attachFailureScreenshot(
            String screenshotPath) {

        attachScreenshot(
                screenshotPath);
    }

    /*
     * FLUSH REPORT
     */

    public static void flushReport() {

        try {

            if (extent != null) {

                extent.flush();

                log.info(
                        "Extent Report Flushed Successfully");

                /*
                 * OPEN REPORT
                 */

                File reportFile =
                        new File(reportPath);

                if (reportFile.exists()) {

                    Desktop.getDesktop()
                           .browse(
                                   reportFile.toURI());

                    log.info(
                            "Extent Report Opened Successfully");
                }

                else {

                    log.error(
                            "Extent Report File Not Found");
                }
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Flush/Open Report",
                    e);
        }
    }

    /*
     * GET TEST
     */

    public static ExtentTest getTest() {

        return test.get();
    }

    /*
     * GET REPORT PATH
     */

    public static String getReportPath() {

        return reportPath;
    }
}