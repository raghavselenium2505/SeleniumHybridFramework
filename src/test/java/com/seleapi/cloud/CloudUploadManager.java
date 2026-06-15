package com.seleapi.cloud;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seleapi.utils.ConfigReader;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class CloudUploadManager {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    CloudUploadManager.class);

    private CloudUploadManager() {

    }

    public static String uploadVideo(
            File videoFile,
            String testName) {

        if (videoFile == null
                || !videoFile.exists()) {

            logger.error(
                    "Video File Not Found");

            return null;
        }

        try {

            String bucketName =
                    ConfigReader.getProperty(
                            "aws.bucketName");

            String region =
                    ConfigReader.getProperty(
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

            String key =
                    "executions/videos/"
                    + testName
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
                    "Video Uploaded Successfully : {}",
                    videoUrl);

            return videoUrl;

        } catch (Exception e) {

            logger.error(
                    "Failed To Upload Video",
                    e);

            return null;
        }
    }

    private static String getContentType(
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
}