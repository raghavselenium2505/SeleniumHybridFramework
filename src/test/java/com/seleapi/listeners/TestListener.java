package com.seleapi.listeners;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.seleapi.base.TestBase;
import com.seleapi.utils.VideoRecorderUtil;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class TestListener extends TestBase implements ITestListener {

    private static final Logger logger =
            LoggerFactory.getLogger(TestListener.class);

    public static List<String> FAILED_TESTS =
            new ArrayList<>();

    ThreadLocal<Boolean> isRecording =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("LISTENER STARTED");

        String testName =
                result.getMethod()
                      .getMethodName();

        try {

            String flag =
                    config.getProperty(
                            "videoRecording");

            System.out.println(
                    "VIDEO FLAG : "
                    + flag);

            if (true) {

                System.out.println(
                        "STARTING VIDEO...");

                VideoRecorderUtil
                        .startRecording(testName);

                System.out.println(
                        "VIDEO STARTED");

                isRecording.set(true);

                logger.info(
                        "🎥 Recording started: {}",
                        testName);

            } else {

                isRecording.set(false);
            }

        } catch (Exception e) {

            e.printStackTrace();

            logger.error(
                    "Error starting recording",
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
                "❌ Test failed: {}",
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

                VideoRecorderUtil.stopRecording();

                logger.info(
                        "🛑 Recording stopped: {}",
                        testName);

                Thread.sleep(2000);

                String videoPath =
                        VideoRecorderUtil
                        .getVideoPath();

                logger.info(
                        "📹 Video Path : {}",
                        videoPath);

                File videoFile =
                        new File(videoPath);

                if (videoFile.exists()) {
                //	DashboardData.s3VideoUrl = s3VideoUrl;
                    String s3VideoUrl =
                    		uploadVideoToS3(
                                    videoFile,
                                    "executions/videos/"
                                    + testName);

                    logger.info(
                            "☁️ Uploaded to S3 : {}",
                            s3VideoUrl);

                    // OPTIONAL:
                    // Save URL into JSON / DB

                } else {

                    logger.warn(
                            "⚠️ Video file not found");
                }
            }

        } catch (Exception e) {

            logger.error(
                    "Error stopping/uploading video",
                    e);
        }
    }

    private String uploadVideoToS3(
            File videoFile,
            String s3BasePath) {

        if (videoFile == null
                || !videoFile.exists())
            return null;

        String bucketName =
                config.getProperty(
                        "aws.bucketName");

        String region =
                config.getProperty(
                        "aws.region");

        AwsBasicCredentials credentials =
                AwsBasicCredentials.create(
                        System.getenv(
                                "AWS_ACCESS_KEY_ID"),
                        System.getenv(
                                "AWS_SECRET_ACCESS_KEY"));

        S3Client s3Client =
                S3Client.builder()
                        .region(
                                Region.of(region))
                        .credentialsProvider(
                                StaticCredentialsProvider
                                .create(credentials))
                        .build();

        try {

            String key =
                    s3BasePath
                    + "/"
                    + videoFile.getName();

            PutObjectRequest request =
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .contentType(
                                    getContentType(
                                            videoFile.getName()))
                            .build();

            s3Client.putObject(
                    request,
                    videoFile.toPath());

            String videoUrl =
                    "https://"
                    + bucketName
                    + ".s3."
                    + region
                    + ".amazonaws.com/"
                    + key;

            logger.info(
                    "✅ Video uploaded : {}",
                    videoUrl);

            return videoUrl;

        } catch (Exception e) {

            logger.error(
                    "❌ Video upload failed",
                    e);

            return null;
        }
    }

    private String getContentType(
            String fileName) {

        if (fileName.endsWith(".mp4")) {
            return "video/mp4";
        }

        if (fileName.endsWith(".webm")) {
            return "video/webm";
        }

        if (fileName.endsWith(".mov")) {
            return "video/quicktime";
        }

        return "application/octet-stream";
    }

    private void deleteVideo(
            String testName) {

        File folder =
                new File(
                        System.getProperty(
                                "user.dir")
                        + "/Videos/");

        File[] files =
                folder.listFiles();

        if (files != null) {

            for (File file : files) {

                if (file.getName()
                        .contains(testName)) {

                    file.delete();
                }
            }
        }
    }

    public static boolean isJenkinsRun() {

        return System.getenv(
                "JENKINS_HOME")
                != null;
    }
}