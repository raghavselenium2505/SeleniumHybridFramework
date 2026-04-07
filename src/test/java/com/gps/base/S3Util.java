package com.gps.base;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

public class S3Util {

    private static final String BUCKET_NAME = "raghav-automation-reports";
    private static final String ACCESS_KEY = "YOUR_ACCESS_KEY";
    private static final String SECRET_KEY = "YOUR_SECRET_KEY";

    private static final Region REGION = Region.AP_SOUTH_1;

    private static final S3Client s3Client = S3Client.builder()
            .region(REGION)
            .credentialsProvider(
                    StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
            .build();

    public static String uploadFile(String filePath, String key) {	

        try {

            File file = new File(filePath);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(key)
                    .build();

            s3Client.putObject(request, file.toPath());

            return "https://" + BUCKET_NAME + ".s3." + REGION.id()
                    + ".amazonaws.com/" + key;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}