package com.web.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class AIRetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 3; // retry only once

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount >= MAX_RETRY) {
            return false;
        }

        Throwable throwable = result.getThrowable();
        if (throwable == null) {
            return false;
        }

        Exception exception =
                (throwable instanceof Exception)
                        ? (Exception) throwable
                        : new Exception(throwable);

        // 🔥 AI analysis
        String aiSuggestion = AITestAnalyzer.analyze(exception);

        // Retry ONLY for recoverable / flaky issues
        if (aiSuggestion.toLowerCase().contains("timeout")
                || aiSuggestion.toLowerCase().contains("stale")
                || aiSuggestion.toLowerCase().contains("blocked")
                || aiSuggestion.toLowerCase().contains("dom")) {

            retryCount++;
            return true;
        }

        return false;
    }
}
