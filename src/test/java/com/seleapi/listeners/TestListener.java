package com.seleapi.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.seleapi.cloud.CloudUploadManager;
import com.seleapi.reporting.VideoManager;
import com.seleapi.utils.ConfigReader;

public class TestListener implements ITestListener {

    private static final Logger logger =
            LoggerFactory.getLogger(TestListener.class);

    public static List<String> FAILED_TESTS =
            new ArrayList<>();

    ThreadLocal<Boolean> isRecording =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getMethod()
                      .getMethodName();

        try {

            String videoFlag =
                    ConfigReader.getProperty(
                            "videoRecording");

            logger.info(
                    "Video Recording Flag : {}",
                    videoFlag);

            if ("true".equalsIgnoreCase(videoFlag)) {

                VideoManager.startRecording(
                        testName);

                isRecording.set(true);

                logger.info(
                        "Video Recording Started : {}",
                        testName);

            } else {

                isRecording.set(false);

                logger.info(
                        "Video Recording Disabled");
            }

        } catch (Exception e) {

            logger.error(
                    "Error Starting Video Recording",
                    e);
        }
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        handleStop(result, false);
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        String testName =
                result.getMethod()
                      .getMethodName();

        FAILED_TESTS.add(testName);

        logger.error(
                "Test Failed : {}",
                testName);

        handleStop(result, true);
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        handleStop(result, false);
    }

    private void handleStop(
            ITestResult result,
            boolean isFailure) {

        String testName =
                result.getMethod()
                      .getMethodName();

        try {

            if (Boolean.TRUE.equals(
                    isRecording.get())) {

                VideoManager.stopRecording();

                logger.info(
                        "Video Recording Stopped : {}",
                        testName);

                Thread.sleep(2000);

                String videoPath =
                        VideoManager.getVideoPath();

                logger.info(
                        "Execution Video Path : {}",
                        videoPath);

                File videoFile =
                        new File(videoPath);

                if (videoFile.exists()) {

                    String s3VideoUrl =
                            CloudUploadManager
                            .uploadVideo(
                                    videoFile,
                                    testName);

                    logger.info(
                            "Video Uploaded To S3 : {}",
                            s3VideoUrl);

                } else {

                    logger.warn(
                            "Video File Not Found");
                }
            }

        } catch (Exception e) {

            logger.error(
                    "Error During Video Stop/Upload",
                    e);
        }
    }
}