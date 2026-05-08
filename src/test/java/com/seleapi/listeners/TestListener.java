package com.seleapi.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.seleapi.utils.VideoRecorderUtil;
import com.seleapi.base.TestBase;

public class TestListener extends TestBase implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    public static List<String> FAILED_TESTS = new ArrayList<>();

    ThreadLocal<Boolean> isRecording = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        try {
            String flag = config.getProperty("videoRecording");

            if ("Y".equalsIgnoreCase(flag) || isJenkinsRun()) {

                VideoRecorderUtil.startRecording(testName);
                isRecording.set(true);

                logger.info("🎥 Recording started: {}", testName);

            } else {
                isRecording.set(false);
            }

        } catch (Exception e) {
            logger.error("Error starting recording", e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        handleStop(result, false);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        FAILED_TESTS.add(testName);

        logger.error("❌ Test failed: {}", testName);

        try {
            // Start recording ONLY on failure
            VideoRecorderUtil.startRecording(testName);

            Thread.sleep(3000); // capture 3 sec after failure

            VideoRecorderUtil.stopRecording();

            logger.info("🎥 Failure clip saved: {}", VideoRecorderUtil.getVideoPath());

        } catch (Exception e) {
            logger.error("Recording failed", e);
        }
    }

    private void handleStop(ITestResult result, boolean isFailure) {

        String testName = result.getMethod().getMethodName();

        try {
            if (Boolean.TRUE.equals(isRecording.get())) {

                VideoRecorderUtil.stopRecording();

                logger.info("🛑 Recording stopped: {}", testName);

                if (!isFailure && !isJenkinsRun()) {

                    deleteVideo(testName);
                    logger.info("🗑️ Deleted video (PASS): {}", testName);

                } else {
                    logger.info("💾 Saved video: {}", VideoRecorderUtil.getVideoPath());
                }
            }

        } catch (Exception e) {
            logger.error("Error stopping recording", e);
        }
    }

    private void deleteVideo(String testName) {

        File folder = new File(System.getProperty("user.dir") + "/Videos/");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().contains(testName)) {
                    file.delete();
                }
            }
        }
    }

    public static boolean isJenkinsRun() {
        return System.getenv("JENKINS_HOME") != null;
    }
}