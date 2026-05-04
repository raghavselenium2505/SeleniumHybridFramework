package com.seleapi.listeners;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    // ✅ CORRECT PLACE (class level)
    public static List<String> FAILED_TESTS = new ArrayList<>();

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        FAILED_TESTS.add(testName);

        // ✅ Replaced System.out
        logger.error("Captured failure: {}", testName);
    }
}