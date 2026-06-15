package com.seleapi.retry;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    public static Logger log =
            Logger.getLogger(RetryAnalyzer.class);

    private int retryCount = 0;

    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            log.warn(
                    "Retrying Test : "
                    + result.getName()
                    + " | Retry Count : "
                    + retryCount);

            return true;
        }

        log.error(
                "Max Retry Reached For Test : "
                + result.getName());

        return false;
    }
}