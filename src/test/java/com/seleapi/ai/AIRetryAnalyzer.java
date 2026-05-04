package com.seleapi.ai;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class AIRetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount >= MAX_RETRY) {
            return false;
        }

        Throwable t = result.getThrowable();

        if (t == null) {
            return false;
        }

        // 🔥 AI analysis
        String aiMessage = AITestAnalyzer.analyze(t).toLowerCase();

        // 🔥 Retry ONLY if AI says it's recoverable
        if (isRecoverable(aiMessage)) {
            retryCount++;
            return true;
        }

        return false;
    }

    private boolean isRecoverable(String msg) {

        return msg.contains("timeout") ||
               msg.contains("stale") ||
               msg.contains("blocked") ||
               msg.contains("dom") ||
               msg.contains("not loaded");
    }
}