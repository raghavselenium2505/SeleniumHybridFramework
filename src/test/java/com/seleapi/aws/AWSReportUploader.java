package com.seleapi.aws;

import java.io.File;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import com.seleapi.utils.ConfigReader;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class AWSReportUploader {

	public static Logger log = Logger.getLogger(AWSReportUploader.class);

	private static S3Client s3Client;

	private AWSReportUploader() {

	}

	/*
	 * INITIALIZE AWS CLIENT
	 */

	static {

		try {

			String region =

					ConfigReader.getProperty("aws.region");

			s3Client =

					S3Client.builder()

							.region(Region.of(region))

							.credentialsProvider(

									DefaultCredentialsProvider.create())

							.build();

			log.info("AWS S3 Client Initialized Successfully");
		}

		catch (Exception e) {

			log.error("Failed To Initialize AWS S3 Client", e);
		}
	}

	/*
	 * UPLOAD FILE
	 */

	public static void uploadFile(String filePath) {

		try {

			boolean uploadEnabled =

					Boolean.parseBoolean(

							ConfigReader.getProperty("aws.upload.enabled"));

			/*
			 * FEATURE FLAG CHECK
			 */

			if (!uploadEnabled) {

				log.warn("AWS Upload Disabled From Config");

				return;
			}

			/*
			 * FILE VALIDATION
			 */

			File file = new File(filePath);

			if (!file.exists()) {

				log.error("File Not Found : " + filePath);

				return;
			}

			/*
			 * BUCKET NAME
			 */

			String bucketName =

					ConfigReader.getProperty("aws.bucketName");

			/*
			 * OBJECT KEY
			 */

			String objectKey =

					"automation-reports/"

							+ System.currentTimeMillis()

							+ "_"

							+ file.getName();

			/*
			 * PUT OBJECT REQUEST
			 */

			PutObjectRequest request =

					PutObjectRequest.builder()

							.bucket(bucketName)

							.key(objectKey)

							.build();

			/*
			 * UPLOAD FILE
			 */

			s3Client.putObject(

					request,

					RequestBody.fromFile(

							Paths.get(filePath)));

			log.info("File Uploaded Successfully To AWS S3");

			log.info("Bucket : " + bucketName);

			log.info("Object Key : " + objectKey);

		}

		catch (Exception e) {

			log.error("Failed To Upload File To AWS", e);
		}
	}

	/*
	 * UPLOAD EXTENT REPORT
	 */

	public static void uploadExtentReport(String reportPath) {

		try {

			log.info("Uploading Extent Report To AWS");

			uploadFile(reportPath);

		}

		catch (Exception e) {

			log.error("Failed To Upload Extent Report", e);
		}
	}

	/*
	 * UPLOAD DASHBOARD
	 */

	public static void uploadDashboard(String dashboardPath) {

		try {

			log.info("Uploading Dashboard To AWS");

			uploadFile(dashboardPath);

		}

		catch (Exception e) {

			log.error("Failed To Upload Dashboard", e);
		}
	}

	/*
	 * CLOSE AWS CLIENT
	 */

	public static void closeClient() {

        try {

            if (s3Client != null) {

                s3Client.close();

                log.info(
                        "AWS S3 Client Closed Successfully");
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Close AWS Client",
                    e);
        }
    }

	/*
	 * public static void uploadSingleFileToS3(
	 * 
	 * String filePath,
	 * 
	 * String s3Key) {
	 * 
	 * try {
	 * 
	 * String bucketName =
	 * 
	 * ConfigReader .getProperty( "aws.bucketName");
	 * 
	 * String region =
	 * 
	 * ConfigReader .getProperty( "aws.region");
	 * 
	 * log.info( "========== AWS SINGLE FILE UPLOAD STARTED ==========");
	 * 
	 * log.info( "Bucket Name : " + bucketName);
	 * 
	 * log.info( "Region : " + region);
	 * 
	 * log.info( "File Path : " + filePath);
	 * 
	 * log.info( "S3 Key : " + s3Key);
	 * 
	 * S3Client s3Client =
	 * 
	 * S3Client.builder()
	 * 
	 * .region( Region.of(region))
	 * 
	 * .build();
	 * 
	 * PutObjectRequest request =
	 * 
	 * PutObjectRequest.builder()
	 * 
	 * .bucket(bucketName)
	 * 
	 * .key(s3Key)
	 * 
	 * .contentType("text/html")
	 * 
	 * .build();
	 * 
	 * s3Client.putObject( request, Paths.get(filePath));
	 * 
	 * log.info( "File Uploaded Successfully To AWS");
	 * 
	 * log.info( "========== AWS SINGLE FILE UPLOAD COMPLETED ==========");
	 * 
	 * }
	 * 
	 * catch (Exception e) {
	 * 
	 * log.error( "Single File Upload Failed", e); } }
	 */

	/*public static void uploadSingleFileToS3(

            String filePath,

            String s3Key) {

        try {

            log.info(
                    "========== AWS UPLOAD STARTED ==========");

            log.info(
                    "File Path : "
                    + filePath);

            log.info(
                    "S3 Key : "
                    + s3Key);

            File file =

                    new File(
                            filePath);

            
             * FILE VALIDATION
             

            if (!file.exists()) {

                log.error(
                        "FILE DOES NOT EXIST : "
                        + filePath);

                return;
            }

            log.info(
                    "File Exists Successfully");

            String bucketName =

                    ConfigReader
                    .getProperty(
                            "aws.bucketName");

            String region =

                    ConfigReader
                    .getProperty(
                            "aws.region");

            log.info(
                    "Bucket Name : "
                    + bucketName);

            log.info(
                    "Region : "
                    + region);

            
             * CREATE S3 CLIENT
             

            S3Client s3Client =

                    S3Client.builder()

                    .region(
                            Region.of(region))

                    .build();

            log.info(
                    "S3 Client Created Successfully");

            
             * CREATE REQUEST
             

            PutObjectRequest request =

                    PutObjectRequest.builder()

                    .bucket(bucketName)

                    .key(s3Key)

                    .contentType("text/html")

                    .build();

            log.info(
                    "PutObjectRequest Created");

            
             * UPLOAD FILE
             

            s3Client.putObject(
                    request,
                    Paths.get(filePath));

            log.info(
                    "FILE UPLOADED SUCCESSFULLY");

            String finalUrl =

                    "https://"
                    + bucketName
                    + ".s3."
                    + region
                    + ".amazonaws.com/"
                    + s3Key;

            log.info(
                    "AWS URL : "
                    + finalUrl);

            log.info(
                    "========== AWS UPLOAD COMPLETED ==========");

        }

        catch (Exception e) {

            log.error(
                    "AWS SINGLE FILE UPLOAD FAILED",
                    e);
        }
    }*/
    
			public static void uploadSingleFileToS3(

			        String filePath,

			        String s3Key) {

			    try {

			        System.out.println(
			                "========== AWS UPLOAD STARTED ==========");

			        System.out.println(
			                "FILE PATH : "
			                + filePath);

			        System.out.println(
			                "S3 KEY : "
			                + s3Key);

			        File file =

			                new File(
			                        filePath);

			        /*
			         * FILE VALIDATION
			         */

			        if (!file.exists()) {

			            System.out.println(
			                    "FILE DOES NOT EXIST");

			            return;
			        }

			        System.out.println(
			                "FILE EXISTS SUCCESSFULLY");

			        String bucketName =

			                ConfigReader
			                .getProperty(
			                        "aws.bucketName");

			        String region =

			                ConfigReader
			                .getProperty(
			                        "aws.region");

			        System.out.println(
			                "BUCKET : "
			                + bucketName);

			        System.out.println(
			                "REGION : "
			                + region);

			        /*
			         * CREATE S3 CLIENT
			         */

			        S3Client s3Client =

			                S3Client.builder()

			                .region(
			                        Region.of(region))

			                .build();

			        System.out.println(
			                "S3 CLIENT CREATED");

			        /*
			         * CREATE REQUEST
			         */

			        PutObjectRequest request =

			                PutObjectRequest.builder()

			                .bucket(bucketName)

			                .key(s3Key)

			                .contentType("text/html")

			                .build();

			        System.out.println(
			                "PUT OBJECT REQUEST CREATED");

			        /*
			         * UPLOAD FILE
			         */

			        s3Client.putObject(
			                request,
			                Paths.get(filePath));

			        System.out.println(
			                "FILE UPLOADED SUCCESSFULLY");

			        String finalUrl =

			                "https://"
			                + bucketName
			                + ".s3."
			                + region
			                + ".amazonaws.com/"
			                + s3Key;

			        System.out.println(
			                "AWS URL : "
			                + finalUrl);

			        System.out.println(
			                "========== AWS UPLOAD COMPLETED ==========");

			    }

			    catch (Exception e) {

			        System.out.println(
			                "AWS UPLOAD FAILED");

			        e.printStackTrace();
			    }
			}
    
}