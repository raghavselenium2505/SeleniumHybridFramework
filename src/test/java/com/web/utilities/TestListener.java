package com.web.utilities;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // ✅ CORRECT PLACE (class level)
    public static List<String> FAILED_TESTS = new ArrayList<>();

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        FAILED_TESTS.add(testName);

        System.out.println("Captured failure: " + testName);
    }
}