package com.seleapi.dashboard;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.seleapi.core.ExecutionContext;

public class DashboardManager {

    public static Logger log =
            Logger.getLogger(
                    DashboardManager.class);

    /*
     * TOTAL TESTS
     */

    private static AtomicInteger totalTests =
            new AtomicInteger(0);

    /*
     * PASSED TESTS
     */

    private static AtomicInteger passedTests =
            new AtomicInteger(0);

    /*
     * FAILED TESTS
     */

    private static AtomicInteger failedTests =
            new AtomicInteger(0);

    /*
     * SKIPPED TESTS
     */

    private static AtomicInteger skippedTests =
            new AtomicInteger(0);

    /*
     * API TESTS
     */

    private static AtomicInteger apiTests =
            new AtomicInteger(0);

    /*
     * UI TESTS
     */

    private static AtomicInteger uiTests =
            new AtomicInteger(0);

    /*
     * API PASSED
     */

    private static AtomicInteger apiPassed =
            new AtomicInteger(0);

    /*
     * API FAILED
     */

    private static AtomicInteger apiFailed =
            new AtomicInteger(0);

    /*
     * TOTAL EXECUTION TIME
     */

    private static long totalExecutionTime;

    /*
     * TOTAL API RESPONSE TIME
     */

    private static long totalApiResponseTime;

    /*
     * TOTAL API REQUESTS
     */

    private static AtomicInteger totalApiRequests =
            new AtomicInteger(0);

    private DashboardManager() {

    }

    /*
     * START TEST
     */

    public static void startTest() {

        totalTests.incrementAndGet();

        String engine =
                ExecutionContext.getEngine();

        if (engine != null) {

            if (engine.equalsIgnoreCase(
                    "API")) {

                apiTests.incrementAndGet();
            }

            else if (engine.equalsIgnoreCase(
                    "SELENIUM")) {

                uiTests.incrementAndGet();
            }
        }

        log.info(
                "Dashboard Test Started");
    }

    /*
     * TEST PASSED
     */

    public static void testPassed() {

        passedTests.incrementAndGet();

        log.info(
                "Dashboard Updated : PASS");
    }

    /*
     * TEST FAILED
     */

    public static void testFailed() {

        failedTests.incrementAndGet();

        log.info(
                "Dashboard Updated : FAIL");
    }

    /*
     * TEST SKIPPED
     */

    public static void testSkipped() {

        skippedTests.incrementAndGet();

        log.info(
                "Dashboard Updated : SKIP");
    }

    /*
     * API PASSED
     */

    public static void apiPassed() {

        apiPassed.incrementAndGet();

        log.info(
                "API Dashboard Updated : PASS");
    }

    /*
     * API FAILED
     */

    public static void apiFailed() {

        apiFailed.incrementAndGet();

        log.info(
                "API Dashboard Updated : FAIL");
    }

    /*
     * ADD EXECUTION TIME
     */

    public static void addExecutionTime(
            long executionTime) {

        totalExecutionTime =
                totalExecutionTime
                + executionTime;
    }

    /*
     * ADD API RESPONSE TIME
     */

    public static void addApiResponseTime(
            long responseTime) {

        totalApiResponseTime += responseTime;

        totalApiRequests.incrementAndGet();

        log.info(
                "API Response Time Added : "
                + responseTime
                + " ms");
    }

    /*
     * GET PASS PERCENTAGE
     */

    public static String getPassPercentage() {

        if (totalTests.get() == 0) {

            return "0%";
        }

        double percentage =

                ((double) passedTests.get()

                        / totalTests.get())

                        * 100;

        return new DecimalFormat(
                "0.00")

                .format(percentage)

                + "%";
    }

    /*
     * GET API PASSED
     */

    public static int getApiPassed() {

        return apiPassed.get();
    }

    /*
     * GET API FAILED
     */

    public static int getApiFailed() {

        return apiFailed.get();
    }

    /*
     * GET API TESTS
     */

    public static int getApiTests() {

        return apiTests.get();
    }

    /*
     * GET UI TESTS
     */

    public static int getUiTests() {

        return uiTests.get();
    }

    /*
     * GET TOTAL TESTS
     */

    public static int getTotalTests() {

        return totalTests.get();
    }

    /*
     * GET PASSED TESTS
     */

    public static int getPassedTests() {

        return passedTests.get();
    }

    /*
     * GET FAILED TESTS
     */

    public static int getFailedTests() {

        return failedTests.get();
    }

    /*
     * GET SKIPPED TESTS
     */

    public static int getSkippedTests() {

        return skippedTests.get();
    }

    /*
     * GET TOTAL EXECUTION TIME
     */

    public static long getTotalExecutionTime() {

        return totalExecutionTime;
    }

    /*
     * GET AVERAGE API RESPONSE TIME
     */

    public static long getAverageApiResponseTime() {

        if (totalApiRequests.get() == 0) {

            return 0;
        }

        return totalApiResponseTime
                / totalApiRequests.get();
    }

    /*
     * PRINT DASHBOARD
     */

    public static void printDashboard() {

        log.info(
                "===================================");

        log.info(
                "ENTERPRISE EXECUTION DASHBOARD");

        log.info(
                "===================================");

        log.info(
                "TOTAL TESTS : "
                + totalTests.get());

        log.info(
                "PASSED TESTS : "
                + passedTests.get());

        log.info(
                "FAILED TESTS : "
                + failedTests.get());

        log.info(
                "SKIPPED TESTS : "
                + skippedTests.get());

        log.info(
                "PASS PERCENTAGE : "
                + getPassPercentage());

        log.info(
                "UI TESTS : "
                + uiTests.get());

        log.info(
                "API TESTS : "
                + apiTests.get());

        log.info(
                "API PASSED : "
                + apiPassed.get());

        log.info(
                "API FAILED : "
                + apiFailed.get());

        log.info(
                "AVG API RESPONSE TIME : "
                + getAverageApiResponseTime()
                + " ms");

        log.info(
                "TOTAL EXECUTION TIME : "
                + totalExecutionTime
                + " ms");

        log.info(
                "ENVIRONMENT : "
                + ExecutionContext
                .getEnvironment());

        log.info(
                "===================================");
    }

    /*
     * RESET DASHBOARD
     */

    public static void resetDashboard() {

        totalTests.set(0);

        passedTests.set(0);

        failedTests.set(0);

        skippedTests.set(0);

        apiTests.set(0);

        uiTests.set(0);

        apiPassed.set(0);

        apiFailed.set(0);

        totalExecutionTime = 0;

        totalApiResponseTime = 0;

        totalApiRequests.set(0);

        log.info(
                "Dashboard Reset Completed");
    }
    
    
    /*
     * AWS REPORT URL
     */

    private static String awsReportUrl;

    /*
     * SET AWS REPORT URL
     */

    public static void setAwsReportUrl(
            String url) {

        awsReportUrl = url;
    }

    /*
     * GET AWS REPORT URL
     */

    public static String getAwsReportUrl() {

        return awsReportUrl;
    }
    
}