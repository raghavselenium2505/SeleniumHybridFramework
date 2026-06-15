package com.seleapi.base;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.seleapi.ai.AITestAnalyzer;
import com.seleapi.services.JiraService;
import com.seleapi.utils.ExcelUtil;
import com.seleapi.utils.ScreenshotUtil;
import com.seleapi.utils.VideoRecorderUtil;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

/* ================= INTERFACE ================= */

interface baseMethods {

	void click(By element, String passvalue, String failValue);

	void actionclick(WebElement element, String passValue, String failValue);

	void clear(By element, String passvalue, String failValue);

	void sendkeys(By element, String value, String passvalue, String failValue);

	void elementhighlight(WebElement element);

	void waitforelement(int milliSeconds);

	int randomNumberGeneration(int value);

	String getTextAndVerify(By element, String expectedText, String passValue, String failValue);

	String getParentWindow(String passValue, String failValue);

	void switchToChildWindow(String parentWindow, String passValue, String failValue);

	void switchToParentWindow(String parentWindow, String passValue, String failValue);

	void waitForElementVisible(By element, int timeout, String passValue, String failValue);

	void waitForElementClickable(By element, int timeout, String passValue, String failValue);

}

/* ================= TEST BASE ================= */

public class TestBase implements baseMethods {

	/* ================= THREAD SAFE DRIVER ================= */

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static Set<String> browsers = new HashSet<>();
	private static Set<String> appUrls = new HashSet<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	private void setDriver(WebDriver d) {
		driver.set(d);
	}

	private void removeDriver() {
		driver.remove();
	}

	/* ================= EXTENT REPORT ================= */

	protected static ExtentReports extent;
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static ThreadLocal<Map<String, String>> currentTestData = new ThreadLocal<>();
	public static String reportPath;

	public static AtomicInteger passCount = new AtomicInteger(0);
	public static AtomicInteger failCount = new AtomicInteger(0);
	public static AtomicInteger skipCount = new AtomicInteger(0);

	public static long suiteStartTime;
	/* ================= CONFIG & UTILITIES ================= */

	public static Properties config = new Properties();
	public static ScreenshotUtil screenshotutil = new ScreenshotUtil();

	static String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/Gps_Rules.xls";

	public static ExcelUtil excelUtil;
	// public static int totalManualTime = 0;
	public static Logger logger = Logger.getLogger("devpinoyLogger");
	// ================= ADD THESE VARIABLES =================
	public static AtomicLong totalExecutionTime = new AtomicLong(0);
	public static AtomicInteger totalManualTime = new AtomicInteger(0);
	private ThreadLocal<Long> testStartTime = new ThreadLocal<>();
	private static final String CONFIG_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/properties/Config.properties";
	public static List<String> FAILED_TESTS = new ArrayList<>();
	public static Map<String, String> FAILURE_REASON_MAP = new HashMap<>();
	public static List<String> CREATED_ISSUES = new ArrayList<>();
	String videoCards = "";
	public static String s3VideoUrl = "";
	/* ================= START EXTENT REPORT ================= */

	 @BeforeSuite(alwaysRun = true)
	    public synchronized void startReport() {

	        PropertyConfigurator.configure(
	                System.getProperty("user.dir")
	                        + "/src/test/resources/properties/log4j.properties");

	        suiteStartTime = System.currentTimeMillis();
	        logger.info("Suite Start Time: " + suiteStartTime);

	        if (extent == null) {

	            reportPath = System.getProperty("user.dir")
	                    + "/src/test/resources/Reports/Extentreport/AutomationReport_"
	                    + new SimpleDateFormat("yyyyMMdd_HHmmss")
	                    .format(new Date()) + ".html";

logger.info("🔥 Report Path: " + reportPath);

	            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	            spark.config().setReportName("Automation Execution Report");
	            spark.config().setDocumentTitle("Execution Report");

	            extent = new ExtentReports();
	            extent.attachReporter(spark);

	            extent.setSystemInfo("User", System.getProperty("user.name"));
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("OS", System.getProperty("os.name"));
	        }
	    }

	/* ================= BROWSER SETUP ================= */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "url" })
	public void setUp(@Optional("") String browserFromXml, @Optional("") String urlFromXml, Method method)
			throws Exception {

		try {
			
		

			// 🔥 START TIMER
			testStartTime.set(System.currentTimeMillis());

			config.load(new FileInputStream(CONFIG_PATH));

			// ================= ROI MANUAL TIME =================
			Map<String, String> data = currentTestData.get();

			if (data != null) {

				String runMode = data.get("runMode");

				if (runMode != null && runMode.equalsIgnoreCase("no")) {

					throw new SkipException("RunMode set to NO for test: " + method.getName());
				}
			}
			if (data != null) {

				String manualTime = data.get("manualTime");

				if (manualTime != null && !manualTime.trim().isEmpty()) {

					try {

						int minutes = 0;
						int seconds = 0;

						// ✅ FIX: handle "3:50"
						if (manualTime.contains(":")) {

							String[] parts = manualTime.split(":");

							minutes = Integer.parseInt(parts[0].trim());
							seconds = Integer.parseInt(parts[1].trim());

						}
						// optional fallback (if someone uses 3.50)
						else if (manualTime.contains(".")) {

							String[] parts = manualTime.split("\\.");

							minutes = Integer.parseInt(parts[0].trim());
							seconds = Integer.parseInt(parts[1].trim());
						}

						int totalSeconds = (minutes * 60) + seconds;

						totalManualTime.addAndGet(totalSeconds);

						logger.info("Manual time added: " + manualTime + " → " + totalSeconds + " sec");

					} catch (Exception ex) {
						logger.info("Invalid manualTime format: " + manualTime);
					}
				}
			}
			VideoRecorderUtil
	        .startRecording(method.getName());

	logger.info(
	        "🎥 Video recording started : {}"+
	        method.getName());
			// ===== BROWSER =====
			String browser = (browserFromXml != null && !browserFromXml.isEmpty()) ? browserFromXml
					: config.getProperty("browser");

			WebDriver localDriver;

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				localDriver = new ChromeDriver();

			} else if (browser.equalsIgnoreCase("Edge")) {
				localDriver = new EdgeDriver();

			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				localDriver = new FirefoxDriver();

			} else {
				throw new RuntimeException("Unsupported browser: " + browser);
			}

			setDriver(localDriver);

			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			getDriver().manage().window().maximize();

			String url = (urlFromXml != null && !urlFromXml.isEmpty()) ? urlFromXml : config.getProperty("testsiteurl");

			getDriver().get(url);

			ExtentTest extentTest = extent.createTest(method.getName());
			test.set(extentTest);

		} catch (Exception e) {
			logAIFailure(e, "Setup failed");
			throw e;
		}
	}

	/* ================= TEST RESULT CAPTURE ================= */
	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

	    try {
	    	VideoRecorderUtil.stopRecording();

	    	logger.info(
	    	        "🛑 Video recording stopped");

	    	String videoPath =
	    	        VideoRecorderUtil
	    	        .getVideoPath();

	    	logger.info(
	    	        "📹 Video Path : {}"+
	    	        videoPath);

	    	File videoFile =
	    	        new File(videoPath);

	    	if (videoFile.exists()) {

	    	    s3VideoUrl =
	    	            uploadVideoToS3(
	    	                    videoFile,
	    	                    "executions/videos");

	    	    logger.info(
	    	            "☁️ Video uploaded to S3 : {}"+
	    	            s3VideoUrl);

	    	} else {

	    	    logger.warn(
	    	            "⚠️ Video file not found");
	    	}
	        // 🔥 CAPTURE EXECUTION TIME
	        if (testStartTime.get() != null) {

	            long duration = result.getEndMillis() - result.getStartMillis();
	            totalExecutionTime.addAndGet(duration);

	            logger.info("Test Execution Time(ms): " + duration);
	        }

	        if (test.get() != null) {

	            if (result.getStatus() == ITestResult.SUCCESS) {

	                passCount.incrementAndGet();
	                test.get().pass("Test Passed");

	            } else if (result.getStatus() == ITestResult.FAILURE) {

	                failCount.incrementAndGet();
	                test.get().fail(result.getThrowable());

	                String testName = result.getMethod().getMethodName();

	                // ✅ STORE FAILED TEST
	                FAILED_TESTS.add(testName);

	                // 🔥 STORE EXCEPTION (IMPORTANT)
	                String errorMessage = "No exception available";

	                if (result.getThrowable() != null) {

	                    StringWriter sw = new StringWriter();
	                    PrintWriter pw = new PrintWriter(sw);

	                    result.getThrowable().printStackTrace(pw);

	                    errorMessage = sw.toString();
	                }

	                FAILURE_REASON_MAP.put(testName, errorMessage);

	                logger.info("Captured failure: " + testName);

	            } else if (result.getStatus() == ITestResult.SKIP) {

	                skipCount.incrementAndGet();
	                test.get().skip("Test Skipped");
	            }
	        }

	    } catch (Exception e) {
	        logger.error("Error in tearDown()", e);
	    } finally {

	        // 🔥 GUARANTEED DRIVER CLOSE
	        try {
	            if (getDriver() != null) {
	                getDriver().quit();
	                logger.info("Browser closed successfully");
	            }
	        } catch (Exception e) {
	            logger.error("Error while closing browser", e);
	        }

	        removeDriver();
	    }
	}
	private String uploadVideoToS3(File videoFile, String s3BasePath) {

		if (videoFile == null || !videoFile.exists())
			return null;

		String bucketName =
				config.getProperty("aws.bucketName");

		String region =
				config.getProperty("aws.region");

		AwsBasicCredentials credentials =
				AwsBasicCredentials.create(
						System.getenv("AWS_ACCESS_KEY_ID"),
						System.getenv("AWS_SECRET_ACCESS_KEY"));

		S3Client s3Client =
				S3Client.builder()
						.region(Region.of(region))
						.credentialsProvider(
								StaticCredentialsProvider.create(credentials))
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
							.contentType("video/mp4")
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
					"Video uploaded successfully : "
					+ videoUrl);

			return videoUrl;

		} catch (Exception e) {

			logger.error(
					"Video upload failed",
					e);

			return null;
		}
	}
	
	private void uploadFolderToS3(File folder, String s3BasePath) {

		if (folder == null || !folder.exists())
			return;

		String bucketName = config.getProperty("aws.bucketName");
		String region = config.getProperty("aws.region");

		AwsBasicCredentials credentials = AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY_ID"),
				System.getenv("AWS_SECRET_ACCESS_KEY"));

		S3Client s3Client = S3Client.builder().region(Region.of(region))
				.credentialsProvider(StaticCredentialsProvider.create(credentials)).build();

		File[] files = folder.listFiles();
		if (files == null)
			return;

		for (File file : files) {

			if (file.isDirectory()) {
				uploadFolderToS3(file, s3BasePath + "/" + file.getName());
			} else {
				try {

					String key = s3BasePath + "/" + file.getName();

					PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(key)
							.contentType(getContentType(file.getName())).build();

					s3Client.putObject(request, file.toPath());

					logger.info("Uploaded screenshot: " + key);

				} catch (Exception e) {
					logger.error("Screenshot upload failed", e);
				}
			}
		}
	}

	// ================= ONLY CHANGED PART BELOW =================
	private void uploadRecursive(File folder, String s3BasePath, software.amazon.awssdk.services.s3.S3Client s3Client,
			String bucketName) {

		File[] files = folder.listFiles();
		if (files == null)
			return;

		for (File file : files) {

			if (file.isDirectory()) {

				uploadRecursive(file, s3BasePath + "/" + file.getName(), s3Client, bucketName);

			} else {

				try {

					if (!(file.getName().endsWith(".html") || file.getPath().contains("screenshots"))) {
						continue;
					}

					String key = s3BasePath + "/" + file.getName();

					// 🔥 CLEAN FIX (BREAK INTO STEPS)
					software.amazon.awssdk.services.s3.model.PutObjectRequest.Builder builder = software.amazon.awssdk.services.s3.model.PutObjectRequest
							.builder().bucket(bucketName).key(key);

					// 🔥 Add content type safely
					builder.contentType(getContentType(file.getName()));

					software.amazon.awssdk.services.s3.model.PutObjectRequest request = builder.build();

					s3Client.putObject(request, file.toPath());

					logger.info("Uploaded: " + key);

				} catch (Exception e) {
					logger.error("Upload failed: " + file.getName(), e);
				}
			}
		}
	}

	private String getContentType(String fileName) {

		fileName = fileName.toLowerCase();

		if (fileName.endsWith(".html"))
			return "text/html";
		if (fileName.endsWith(".png"))
			return "image/png";
		if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))
			return "image/jpeg";
		if (fileName.endsWith(".gif"))
			return "image/gif";

		return "application/octet-stream";
	}

	private boolean isAwsUploadEnabled() {
		return Boolean.parseBoolean(config.getProperty("aws.upload.enabled", "false"));
	}

	private boolean isEmailEnabled() {
		return Boolean.parseBoolean(config.getProperty("aws.email.enabled", "false"));
	}

	/* ================= EXTENT REPORT END ================= */
	public static String s3BaseUrl = "";

	@AfterSuite(alwaysRun = true)
	public synchronized void endReport() {

	    try {

	        if (extent == null) {
	            logger.error("Extent is NULL");
	            return;
	        }

	        extent.flush();
	        waitForReportToBeReady(reportPath);

	        String finalUrl = "";

	        // ================= AWS =================
	        if (isAwsUploadEnabled()) {

	            try {

	                String bucketName = config.getProperty("aws.bucketName");
	                String region = config.getProperty("aws.region");

	                String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());

	                String fileName = "DashboardReport_" + timeStamp + ".html";
	                String s3Key = "reports/latest/" + fileName;

	                logger.info("Uploading report to S3...");

	                uploadSingleFileToS3(reportPath, s3Key);

	                finalUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + s3Key;

	                logger.info("AWS URL: " + finalUrl);

	            } catch (Exception ex) {
	                logger.error("S3 upload failed", ex);
	            }
	        }

	        // ================= 🔥 JIRA (ONLY ADDITION) =================
	        try {

	            logger.info("===== JIRA EXECUTION START =====");
	            logger.info("Failed Tests Count: " + FAILED_TESTS.size());

	            for (String test : FAILED_TESTS) {

	                logger.info("Creating Jira for: " + test);

	                JiraService.createJiraBug(test);
	            }

	            logger.info("===== JIRA EXECUTION END =====");

	        } catch (Exception e) {
	            logger.error("Error while creating Jira issues", e);
	        }
	        // ===========================================================

	        // ================= COUNTS =================
	        int pass = passCount.get();
	        int fail = failCount.get();
	        int skip = skipCount.get();
	        int total = pass + fail + skip;

	        logger.info(
	                "Execution Summary -> Total: " + total + " Pass: " + pass + " Fail: " + fail + " Skip: " + skip);

	        // ================= EXECUTION TIME =================
	        long duration = System.currentTimeMillis() - suiteStartTime;

	        long totalSeconds = duration / 1000;
	        long hours = totalSeconds / 3600;
	        long minutes = (totalSeconds % 3600) / 60;
	        long seconds = totalSeconds % 60;

	        logger.info("Execution Time: " + hours + "h " + minutes + "m " + seconds + "s");

	        // ================= ROI =================
	        int manualSeconds = totalManualTime.get();
	        int automationSeconds = (int) (totalExecutionTime.get() / 1000);

	        int saved = Math.max(0, manualSeconds - automationSeconds);

	        double efficiency = manualSeconds > 0 ? (saved * 100.0) / manualSeconds : 0;

	        String manualTimeStr = (manualSeconds / 60) + "m " + (manualSeconds % 60) + "s";
	        String autoTimeStr = (automationSeconds / 60) + "m " + (automationSeconds % 60) + "s";
	        String savedTimeStr = (saved / 60) + "m " + (saved % 60) + "s";

	        int jiraBugs = JiraService.CREATED_ISSUES.size();

	        saveROIHistory(manualSeconds, automationSeconds, saved, efficiency,
	                pass, fail, skip, jiraBugs);
	        logger.info("========= AUTOMATION ROI =========");
	        logger.info("Manual Time: " + manualTimeStr);
	        logger.info("Automation Time: " + autoTimeStr);
	        logger.info("Time Saved: " + savedTimeStr);
	        logger.info("Efficiency: " + String.format("%.2f", efficiency) + "%");
	        logger.info("=================================");

	        // ================= CONSOLE =================
	        logger.info("\n========= AUTOMATION ROI =========");
	        logger.info("Manual Time: " + manualTimeStr);
	        logger.info("Automation Time: " + autoTimeStr);
	        logger.info("Time Saved: " + savedTimeStr);
	        logger.info("Efficiency: " + String.format("%.2f", efficiency) + "%");
	        logger.info("=================================\n");

	        // ================= DASHBOARD =================
	        generateDashboardHtml(pass, fail, skip, finalUrl, totalExecutionTime.get());

	        // ================= OPEN REPORT =================
	        try {

	            File reportFile = new File(reportPath);

	            if (reportFile.exists() && Desktop.isDesktopSupported()) {
	                Desktop.getDesktop().browse(reportFile.toURI());
	                logger.info("Opened Extent report");
	            }

	        } catch (Exception e) {
	            logger.warn("Unable to open report");
	        }

	    } catch (Exception e) {
	        logger.error("Error in endReport()", e);
	    }
	}

	private void uploadSingleFileToS3(String filePath, String s3Key) {

		try {

			File file = new File(filePath);

			if (!file.exists()) {
				throw new RuntimeException("File not found: " + filePath);
			}

			String bucketName = config.getProperty("aws.bucketName");
			String region = config.getProperty("aws.region");

			AwsBasicCredentials credentials = AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY_ID"),
					System.getenv("AWS_SECRET_ACCESS_KEY"));

			S3Client s3Client = S3Client.builder().region(Region.of(region))
					.credentialsProvider(StaticCredentialsProvider.create(credentials)).build();

			PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(s3Key)
					.contentType(getContentType(file.getName())).build();

			s3Client.putObject(request, file.toPath());

			logger.info("Uploaded: " + s3Key);

		} catch (Exception e) {
			logger.error("Upload failed", e);
		}
	}

	private void waitForReportToBeReady(String reportPath) {

		File file = new File(reportPath);

		long lastSize = -1;
		int stableCount = 0;

		while (stableCount < 3) {

			long currentSize = file.length();

			if (currentSize == lastSize && currentSize > 0) {
				stableCount++;
			} else {
				stableCount = 0;
			}

			lastSize = currentSize;

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		logger.info("Report file is stable and ready for upload");
	}

	private void deleteS3Folder(String prefix) {

		try {

			String bucketName = config.getProperty("aws.bucketName");
			String region = config.getProperty("aws.region");

			AwsBasicCredentials credentials = AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY_ID"),
					System.getenv("AWS_SECRET_ACCESS_KEY"));

			S3Client s3Client = S3Client.builder().region(Region.of(region))
					.credentialsProvider(StaticCredentialsProvider.create(credentials)).build();

			String continuationToken = null;

			do {

				ListObjectsV2Request.Builder requestBuilder = ListObjectsV2Request.builder().bucket(bucketName)
						.prefix(prefix);

				if (continuationToken != null) {
					requestBuilder.continuationToken(continuationToken);
				}

				ListObjectsV2Response response = s3Client.listObjectsV2(requestBuilder.build());

				for (S3Object obj : response.contents()) {

					s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(obj.key()).build());

					logger.info("Deleted: " + obj.key());
				}

				continuationToken = response.nextContinuationToken();

			} while (continuationToken != null);

		} catch (Exception e) {
			logger.error("Delete failed", e);
		}
	}
	/* ================= AI FAILURE LOGGER ================= */

	/* ================= AI FAILURE LOGGER ================= */

	protected void logAIFailure(Exception e, String failValue) {

		String aiSuggestion = AITestAnalyzer.analyze(e);
		String screenshotPath = null;

		try {
			screenshotPath = ScreenshotUtil.takeScreenshot(getDriver());
		} catch (Exception ex) {
			logger.warn("Screenshot capture failed");
		}

		if (test.get() != null) {

			test.get().log(Status.FAIL, failValue);

			if (screenshotPath != null && isAwsUploadEnabled()) {

				try {

					String bucketName = config.getProperty("aws.bucketName");
					String region = config.getProperty("aws.region");

					String fileName = new File(screenshotPath).getName();
					String s3Key = "reports/latest/screenshots/" + fileName;

					// 🔥 TRY UPLOAD
					uploadSingleFileToS3(screenshotPath, s3Key);

					String s3Url = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + s3Key;

					// 🔥 ONLY LINK (NO IMAGE)
					test.get().log(Status.FAIL, aiSuggestion + "<br><br>" + "<a href='" + s3Url + "' target='_blank' "
							+ "style='color:red;font-weight:bold;'>👉 View Screenshot</a>");

				} catch (Exception ex) {

					// 🔥 IF UPLOAD FAILS
					test.get().log(Status.FAIL,
							aiSuggestion + "<br><br>" + "<b style='color:red;'>Screenshot upload failed</b>");

					logger.error("Screenshot upload failed", ex);
				}

			} else {
				test.get().log(Status.FAIL, aiSuggestion);
			}
		}
	}

	/* ================= BASE METHODS ================= */

	@Override
	public void click(By element, String passValue, String failValue) {
		try {
			elementhighlight(getDriver().findElement(element));

			getDriver().findElement(element).click();
			test.get().log(Status.PASS, passValue);
		} catch (Exception e) {

			logAIFailure(e, failValue);
		}
	}

	@Override
	public void actionclick(WebElement element, String passValue, String failValue) {
		try {

			// ✅ Wait until element is clickable
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Highlight
			elementhighlight(element);

			// ✅ Scroll into view (VERY IMPORTANT)
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

			// Small wait (for stability)
			Thread.sleep(300);

			// 🔥 Try normal click first
			try {
				element.click();
			} catch (Exception e) {

				// 🔥 Fallback to Actions
				try {
					new Actions(getDriver()).moveToElement(element).pause(Duration.ofMillis(200)).click().perform();
				} catch (Exception ex) {

					// 🔥 Final fallback → JS click (MOST POWERFUL)
					((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
				}
			}

			test.get().log(Status.PASS, passValue);

		} catch (Exception e) {
			test.get().log(Status.FAIL, failValue + " | Exception: " + e.getMessage());
			Assert.fail("Action click failed: " + e.getMessage());
		}
	}

	@Override
	public void clear(By element, String passvalue, String failValue) {
		try {
			elementhighlight(getDriver().findElement(element));

			getDriver().findElement(element).clear();
		} catch (Exception e) {
			logAIFailure(e, failValue);
		}
	}

	@Override
	public void sendkeys(By element, String value, String passValue, String failValue) {
		try {
			WebElement ele = getDriver().findElement(element);

			// Highlight
			elementhighlight(ele);

			// Assertions
			Assert.assertTrue(ele.isDisplayed(), "Element is not displayed");
			Assert.assertTrue(ele.isEnabled(), "Element is not enabled");

			// Action
			ele.clear();
			ele.sendKeys(value);

			// Validation
			String enteredText = ele.getAttribute("value");
			Assert.assertEquals(enteredText, value, "Entered value mismatch");

			// ✅ Log actual value (no masking)
			test.get().log(Status.PASS, passValue + " | Entered Value: " + value);

		} catch (Exception e) {
			test.get().log(Status.FAIL, failValue + " | Exception: " + e.getMessage());

			logAIFailure(e, failValue);

		}
	}

	@Override
	public void elementhighlight(WebElement element) {
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='3px solid blue'", element);
		} catch (Exception e) {
			test.get().log(Status.WARNING, "AI Insight: " + AITestAnalyzer.analyze(e));
		}
	}

	@Override
	public void waitforelement(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception ignored) {
		}
	}

	@Override
	public int randomNumberGeneration(int value) {
		return new Random().nextInt(value);
	}

	public String getExcelRunMode(String testName) {
		ExcelUtil excelUtil = new ExcelUtil(System.getProperty("user.dir") + "/src/test/resources/excel/Gps_Rules.xls");

		return excelUtil.getSingleCellValue("RunManager", testName);
	}

	public JSONArray getJsonArray() {
		try {
			return (JSONArray) new JSONParser()
					.parse(new FileReader(System.getProperty("user.dir") + "/src/test/resources/excel/testdata.json"));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void waitForElementVisible(By element, int timeout, String passValue, String failValue) {
		try {
			elementhighlight(getDriver().findElement(element));
			new WebDriverWait(getDriver(), Duration.ofMillis(timeout))
					.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception e) {
			logAIFailure(e, failValue);
			throw e;
		}
	}

	@Override
	public void waitForElementClickable(By element, int timeout, String passValue, String failValue) {
		try {
			elementhighlight(getDriver().findElement(element));

			new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))
					.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			logAIFailure(e, failValue);
			throw e;
		}
	}

	/* ================= TEXT ================= */

	@Override
	public String getTextAndVerify(By element, String expectedText, String passValue, String failValue) {

		String actual = getDriver().findElement(element).getText().trim();

		Assert.assertTrue(actual.contains(expectedText), "Expected text not found. Actual: " + actual);

		logger.info(passValue);
		test.get().pass(passValue);

		return actual;
	}
	/* ================= WINDOW ================= */

	@Override
	public String getParentWindow(String passValue, String failValue) {
		try {
			String parent = getDriver().getWindowHandle();
			logger.info(passValue);
			test.get().pass(passValue);
			return parent;
		} catch (Exception e) {
			logAIFailure(e, failValue);
			throw e;
		}
	}

	@Override
	public void switchToChildWindow(String parentWindow, String passValue, String failValue) {

		try {
			// Wait inside method
			new WebDriverWait(getDriver(), Duration.ofSeconds(10))
					.until(driver -> driver.getWindowHandles().size() > 1);

			for (String win : getDriver().getWindowHandles()) {
				if (!win.equals(parentWindow)) {
					getDriver().switchTo().window(win);

					logger.info(passValue);
					test.get().pass(passValue);
					return;
				}
			}

			logger.error(failValue);
			test.get().fail(failValue);

		} catch (Exception e) {
			logAIFailure(e, failValue);
			throw e;
		}
	}

	@Override
	public void switchToParentWindow(String parentWindow, String passValue, String failValue) {
		try {
			getDriver().close();
			getDriver().switchTo().window(parentWindow);

			logger.info(passValue);
			test.get().pass(passValue);

		} catch (Exception e) {
			logAIFailure(e, failValue);
			throw e;
		}
	}

	public void verifyElementDisplayed(By element, String passMsg, String failMsg) {
		try {
			boolean status = getDriver().findElement(element).isDisplayed();

			if (status) {
				// test.get().log(Status.PASS, passMsg);
			} else {
				test.get().log(Status.FAIL, failMsg);
				org.testng.Assert.fail(failMsg);
			}

		} catch (Exception e) {
			/*
			 * test.get().log(Status.FAIL, failMsg + " Exception: " + e.getMessage());
			 * org.testng.Assert.fail(failMsg);
			 */
			logAIFailure(e, failMsg);
		}
	}

	public void assertElementDisplayed(By element, String passMessage, String failMessage) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
			WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(element));

			Assert.assertTrue(ele.isDisplayed(), failMessage);

			test.get().log(Status.PASS, passMessage);

		} catch (Exception e) {
			test.get().log(Status.FAIL, failMessage + " | Exception: " + e.getMessage());
			Assert.fail(failMessage + " | Exception: " + e.getMessage());
		}

	}

	private void sendEmailViaSES(String toEmail, String subject, String body) {

		try {

			String accessKey = config.getProperty("aws.accessKey");
			String secretKey = config.getProperty("aws.secretKey");
			String region = config.getProperty("aws.region");
			String fromEmail = config.getProperty("aws.fromEmail");

			logger.info("Sending email via SES...");
			logger.info("From: " + fromEmail + " | To: " + toEmail + " | Region: " + region);

			software.amazon.awssdk.services.ses.SesClient sesClient = software.amazon.awssdk.services.ses.SesClient
					.builder().region(software.amazon.awssdk.regions.Region.of(region))
					.credentialsProvider(software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(
							software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(accessKey, secretKey)))
					.build();

			software.amazon.awssdk.services.ses.model.SendEmailRequest request = software.amazon.awssdk.services.ses.model.SendEmailRequest
					.builder().source(fromEmail)
					.destination(software.amazon.awssdk.services.ses.model.Destination.builder().toAddresses(toEmail)
							.build())
					.message(software.amazon.awssdk.services.ses.model.Message.builder()
							.subject(software.amazon.awssdk.services.ses.model.Content.builder().data(subject).build())
							.body(software.amazon.awssdk.services.ses.model.Body.builder().html(
									software.amazon.awssdk.services.ses.model.Content.builder().data(body).build())
									.build())
							.build())
					.build();

			sesClient.sendEmail(request);

			logger.info("Email sent successfully");

		} catch (Exception e) {

			logger.error("❌ SES EMAIL FAILED: " + e.getMessage(), e);
		}
	}

	public void generateDashboardHtml(int pass, int fail, int skip, String finalUrl, long durationMillis) {

		try {

			String folderPath = System.getProperty("user.dir") + "/src/test/resources/Reports/DashBoard";

			File folder = new File(folderPath);

			if (!folder.exists()) {
				folder.mkdirs();
			}

			String path = folderPath + "/DashboardReport_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
					+ ".html";

			int total = pass + fail + skip;

			int passPer = total == 0 ? 0 : (pass * 100 / total);
			int failPer = total == 0 ? 0 : (fail * 100 / total);
			int skipPer = total == 0 ? 0 : (skip * 100 / total);

			long seconds = durationMillis / 1000;
			long minutes = seconds / 60;
			seconds = seconds % 60;

			int manualSeconds = totalManualTime.get();

			int automationSeconds = (int) (durationMillis / 1000);

			int saved = Math.max(0, manualSeconds - automationSeconds);

			double efficiency = manualSeconds > 0 ? (saved * 100.0) / manualSeconds : 0.0;

			String manualTimeStr = (manualSeconds / 60) + "m " + (manualSeconds % 60) + "s";
			String autoTimeStr = (automationSeconds / 60) + "m " + (automationSeconds % 60) + "s";
			String savedTimeStr = (saved / 60) + "m " + (saved % 60) + "s";
			// ================= JIRA DATA =================
			Map<String, Integer> jiraCounts = JiraService.getJiraCounts();

			int currentBugs = JiraService.CREATED_ISSUES.size();
			int weeklyBugs = jiraCounts.getOrDefault("weekly", 0);
			int monthlyBugs = jiraCounts.getOrDefault("monthly", 0);

			String jiraBaseUrl = config.getProperty("jira.baseUrl");

			StringBuilder jiraLinks = new StringBuilder();

			for (String key : JiraService.CREATED_ISSUES) {
			    jiraLinks.append("<a href='")
			             .append(jiraBaseUrl).append("/browse/").append(key)
			             .append("' target='_blank'>")
			             .append(key)
			             .append("</a><br>");
			}
			String roiLabel;
			String roiColor;

			if (efficiency >= 70) {

				roiLabel = "High ROI";
				roiColor = "#22c55e";

			} else if (efficiency >= 40) {

				roiLabel = "Moderate ROI";
				roiColor = "#facc15";

			} else {

				roiLabel = "Low ROI";
				roiColor = "#ef4444";
			}

			String status = fail > 0 ? "FAILED ❌" : "PASSED ✅";
			String statusColor = fail > 0 ? "#ef4444" : "#22c55e";

			Map<String, Object> weekly = getWeeklyROI();

			double weeklyROI = (double) weekly.get("weeklyROI");
			double avgROI = (double) weekly.get("avgROI");
			double manualHours = (double) weekly.get("manualHours");
			double automationHours = (double) weekly.get("automationHours");
			double savedHours = (double) weekly.get("savedHours");

			Map<String, Object> health = getWeeklyTestHealth();

			int weeklyPass = (int) health.get("pass");
			int weeklyFail = (int) health.get("fail");
			int weeklySkip = (int) health.get("skip");
			int weeklyTotal = (int) health.get("totalTests");
			double weeklyPassPercent = (double) health.get("passPercent");

			Map<String, Object> monthlyHealth = getMonthlyTestHealth();

			int monthlyPass = (int) monthlyHealth.get("pass");
			int monthlyFail = (int) monthlyHealth.get("fail");
			int monthlySkip = (int) monthlyHealth.get("skip");
			int monthlyTotal = (int) monthlyHealth.get("totalTests");
			double monthlyPassPercent = (double) monthlyHealth.get("passPercent");

			Map<String, Object> monthlyROIData = getMonthlyROI();

			double monthlyAvgROI = (double) monthlyROIData.get("avgROI");
			double monthlyROI = (double) monthlyROIData.get("monthlyROI");
			double monthlyManualHours = (double) monthlyROIData.get("manualHours");
			double monthlyAutomationHours = (double) monthlyROIData.get("automationHours");
			double monthlySavedHours = (double) monthlyROIData.get("savedHours");

			String healthLabel;
			String healthColor;

			if (monthlyPassPercent >= 80) {

				healthLabel = "Excellent";
				healthColor = "#22c55e";

			} else if (monthlyPassPercent >= 60 && monthlyPassPercent <= 80) {

				healthLabel = "Moderate";
				healthColor = "#facc15";

			} else if (monthlyPassPercent >= 0 && monthlyPassPercent <= 60) {

				healthLabel = "Low";
				healthColor = "#facc15";

			} else {

				healthLabel = "Needs Attention";
				healthColor = "#ef4444";
			}

			// ================= CHART DATA =================
			Map<String, Double> manualMap = new LinkedHashMap<>();
			Map<String, Double> autoMap = new LinkedHashMap<>();
			Map<String, Double> savedMap = new LinkedHashMap<>();
			Map<String, Double> roiMap = new LinkedHashMap<>();
			Map<String, Integer> countMap = new LinkedHashMap<>();

			String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

			File roiFile = new File(roiPath);

			if (roiFile.exists()) {

				JSONParser parser = new JSONParser();

				JSONArray historyJson = (JSONArray) parser.parse(new FileReader(roiFile));

				LocalDate today = LocalDate.now();

				LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM");

				for (Object obj : historyJson) {

					JSONObject row = (JSONObject) obj;

					LocalDate runDate = LocalDate.parse(row.get("date").toString());

					if (runDate.isBefore(weekStart) || runDate.isAfter(today)) {
						continue;
					}

					String key = runDate.format(formatter);

					double manual = Double.parseDouble(row.get("manualSeconds").toString()) / 3600.0;
					double auto = Double.parseDouble(row.get("automationSeconds").toString()) / 3600.0;
					double savedValue = Double.parseDouble(row.get("savedSeconds").toString()) / 3600.0;
					double roiValue = Double.parseDouble(row.get("roi").toString());

					manualMap.put(key, manualMap.getOrDefault(key, 0.0) + manual);
					autoMap.put(key, autoMap.getOrDefault(key, 0.0) + auto);
					savedMap.put(key, savedMap.getOrDefault(key, 0.0) + savedValue);
					roiMap.put(key, roiMap.getOrDefault(key, 0.0) + roiValue);
					countMap.put(key, countMap.getOrDefault(key, 0) + 1);
				}
			}

			StringBuilder labels = new StringBuilder("[");
			StringBuilder manualBuilder = new StringBuilder("[");
			StringBuilder autoBuilder = new StringBuilder("[");
			StringBuilder savedBuilder = new StringBuilder("[");
			StringBuilder roiBuilder = new StringBuilder("[");

			int index = 0;
			int size = manualMap.size();

			for (String key : manualMap.keySet()) {

				labels.append("'").append(key).append("'");

				manualBuilder.append(String.format("%.2f", manualMap.get(key)));
				autoBuilder.append(String.format("%.2f", autoMap.get(key)));
				savedBuilder.append(String.format("%.2f", savedMap.get(key)));

				double dailyAvgROI = roiMap.get(key) / countMap.get(key);

				roiBuilder.append(String.format("%.2f", dailyAvgROI));

				if (index < size - 1) {

					labels.append(",");
					manualBuilder.append(",");
					autoBuilder.append(",");
					savedBuilder.append(",");
					roiBuilder.append(",");
				}

				index++;
			}

			labels.append("]");
			manualBuilder.append("]");
			autoBuilder.append("]");
			savedBuilder.append("]");
			roiBuilder.append("]");

			String weekLabels = manualMap.isEmpty() ? "['No Data']" : labels.toString();
			String manualData = manualMap.isEmpty() ? "[0]" : manualBuilder.toString();
			String autoData = manualMap.isEmpty() ? "[0]" : autoBuilder.toString();
			String savedData = manualMap.isEmpty() ? "[0]" : savedBuilder.toString();
			String roiData = manualMap.isEmpty() ? "[0]" : roiBuilder.toString();

			String pieData = "[" + pass + "," + fail + "," + skip + "]";

			String reportLink = (finalUrl != null && !finalUrl.isEmpty()) ? finalUrl : "#";
			String videoCards = "";

			if (s3VideoUrl != null
			        && !s3VideoUrl.isEmpty()) {

			    videoCards +=

			        "<div style='background:#334155;"
			        + "padding:15px;"
			        + "border-radius:12px;"
			        + "width:340px;"
			        + "box-shadow:0 4px 12px rgba(0,0,0,0.3)'>"

			        + "<h3 style='color:#38bdf8'>"
			        + "Execution Video"
			        + "</h3>"

			        + "<video width='300' controls "
			        + "style='border-radius:10px'>"

			        + "<source src='"
			        + s3VideoUrl
			        + "' type='video/mp4'>"

			        + "</video>"

			        + "<br><br>"

			        + "<a href='"
			        + s3VideoUrl
			        + "' target='_blank' "

			        + "style='background:#22c55e;"
			        + "padding:10px 16px;"
			        + "border-radius:8px;"
			        + "text-decoration:none;"
			        + "color:black;"
			        + "font-weight:bold'>"

			        + "Open Video"
			     
								/*
								 * + (reportLink.equals("#")
								 * 
								 * ? "<span style='background:#64748b;" + "padding:10px 16px;" +
								 * "border-radius:8px;" + "display:inline-block'>"
								 * 
								 * + "Report Not Available"
								 * 
								 * + "</span>"
								 * 
								 * : "<a href='" + reportLink + "' target='_blank' "
								 * 
								 * + "style='background:#38bdf8;" + "padding:10px 16px;" + "border-radius:8px;"
								 * + "text-decoration:none;" + "color:black;" + "font-weight:bold;" +
								 * "margin-left:10px'>"
								 * 
								 * + "Open Extent Report"
								 * 
								 * + "</a>") + "</a>"
								 */

			        + "</div>";
			}

			String html = "<html><head>"

			+ "<meta charset='UTF-8'>"
			+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
			+ "<title>Automation Dashboard</title>"

			+ "<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>"

			+ "<style>"
			+ "body{font-family:Segoe UI,Arial,sans-serif;background:#0f172a;color:white;padding:20px;margin:0;text-align:center}"
			+ ".tabs{margin-bottom:20px;display:flex;justify-content:center;gap:12px;flex-wrap:wrap}"
			+ ".tab{cursor:pointer;padding:12px 24px;border-radius:10px;display:inline-block;font-weight:bold;color:white;transition:0.3s;box-shadow:0 4px 10px rgba(0,0,0,0.25)}"
			+ ".tab:hover{transform:translateY(-2px);opacity:0.9}"
			+ ".summary-tab{background:#3b82f6}"
			+ ".charts-tab{background:#22c55e}"
			+ ".health-tab{background:#f59e0b}"
			+ ".card{background:#1e293b;padding:20px;border-radius:12px;margin-top:20px}"
			+ ".hidden{display:none}"
			+ ".chart-row{display:flex;justify-content:center;gap:25px;flex-wrap:wrap}"
			+ ".chart-container{width:100%;max-width:380px;height:auto}"
			+ "canvas{max-width:100%!important;height:auto!important}"
			+ "p{margin:8px 0}"
			+ "h2{color:#38bdf8}"
			+ "hr{border:1px solid #334155;margin:20px 0}"
			+ "@media(max-width:768px){body{padding:12px}.tab{width:100%;max-width:220px}.chart-container{max-width:100%}}"
			+ "</style>"

			+ "<script>"
			+ "function showTab(tab){"
			+ "document.getElementById('summary').style.display='none';"
			+ "document.getElementById('charts').style.display='none';"
			+ "document.getElementById('health').style.display='none';"
			+ "document.getElementById('videos').style.display='none';"
			+ "document.getElementById(tab).style.display='block';"
			+ "}"
			+ "</script>"

			+ "</head><body>"

			+ "<h1>&#128640; Automation Dashboard</h1>"

			+ "<div class='tabs'>"

			+ "<span class='tab summary-tab' onclick=\"showTab('summary')\">Summary</span>"

			+ "<span class='tab charts-tab' onclick=\"showTab('charts')\">Charts</span>"

			+ "<span class='tab health-tab' onclick=\"showTab('health')\">Health Report</span>"

			+ "<span class='tab' style='background:#8b5cf6' onclick=\"showTab('videos')\">Execution Videos</span>"
			+ "<span class='tab' style='background:#8b5cf9' onclick=\"showTab('videos')\">JiraIssues</span>"

			+ "</div>"

			/* ================= SUMMARY ================= */

			+ "<div id='summary' class='card'>"

			+ "<h2 style='color:" + statusColor + "'>" + status + "</h2>"

			+ "<p>Total Tests: " + total + "</p>"

			+ "<p>Execution Time: " + minutes + "m " + seconds + "s</p>"

			+ "<p>Passed: " + pass + " (" + passPer + "%)</p>"

			+ "<p>Failed: " + fail + " (" + failPer + "%)</p>"

			+ "<p>Skipped: " + skip + " (" + skipPer + "%)</p>"

			+ "<hr>"

			+ "<h2>Current Run ROI</h2>"

			+ "<p>Manual Time: " + manualTimeStr + "</p>"

			+ "<p>Automation Time: " + autoTimeStr + "</p>"

			+ "<p>Saved Time: " + savedTimeStr + "</p>"

			+ "<p>Efficiency: " + String.format("%.2f", efficiency) + "%</p>"

			+ "<div style='margin-top:10px;'>"

			+ "<span style='background:" + roiColor + ";padding:8px 18px;border-radius:20px;color:black;font-weight:bold;'>"

			+ roiLabel

			+ "</span></div>"

			+ "<hr>"

			+ "<h2>Weekly ROI</h2>"

			+ "<p>Manual Hours: " + String.format("%.2f", manualHours) + " hrs</p>"

			+ "<p>Automation Hours: " + String.format("%.2f", automationHours) + " hrs</p>"

			+ "<p>Hours Saved: " + String.format("%.2f", savedHours) + " hrs</p>"

			+ "<p>Efficiency Gain: " + String.format("%.2f", weeklyROI) + "%</p>"

			+ "<hr>"

			+ "<h2>Monthly ROI</h2>"

			+ "<p>Manual Hours: " + String.format("%.2f", monthlyManualHours) + " hrs</p>"

			+ "<p>Automation Hours: " + String.format("%.2f", monthlyAutomationHours) + " hrs</p>"

			+ "<p>Hours Saved: " + String.format("%.2f", monthlySavedHours) + " hrs</p>"

			+ "<p>Efficiency Gain: " + String.format("%.2f", monthlyROI) + "%</p>"

			+ "</div>"

			/* ================= CHARTS ================= */

			+ "<div id='charts' class='card hidden'>"

			+ "<h2>Execution Analytics</h2>"

			+ "<div class='chart-row'>"

			+ "<div class='chart-container'><canvas id='pieChart'></canvas></div>"

			+ "<div class='chart-container'><canvas id='weekChart'></canvas></div>"

			+ "<div class='chart-container'><canvas id='roiChart'></canvas></div>"

			+ "</div>"

			+ "</div>"

			/* ================= HEALTH ================= */

			+ "<div id='health' class='card hidden'>"

			+ "<h2>Weekly Health Report</h2>"

			+ "<p>Passed This Week: " + weeklyPass + "</p>"

			+ "<p>Failed This Week: " + weeklyFail + "</p>"

			+ "<p>Skipped This Week: " + weeklySkip + "</p>"

			+ "<p>Total Test Cases: " + weeklyTotal + "</p>"

			+ "<p>Pass %: " + String.format("%.2f", weeklyPassPercent) + "%</p>"

			+ "<p>Average ROI This Week: " + String.format("%.2f", avgROI) + "%</p>"

			+ "<hr>"

			+ "<h2>Monthly Health Report</h2>"

			+ "<p>Passed This Month: " + monthlyPass + "</p>"

			+ "<p>Failed This Month: " + monthlyFail + "</p>"

			+ "<p>Skipped This Month: " + monthlySkip + "</p>"

			+ "<p>Total Test Cases: " + monthlyTotal + "</p>"

			+ "<p>Pass %: " + String.format("%.2f", monthlyPassPercent) + "%</p>"

			+ "<p>Average ROI: " + String.format("%.2f", monthlyAvgROI) + "%</p>"

			+ "<div style='margin-top:15px;'>"

			+ "<span style='background:" + healthColor + ";padding:8px 18px;border-radius:20px;color:black;font-weight:bold;'>"

			+ healthLabel

			+ "</span></div>"

			+ "<br><br>"

			+ (reportLink.equals("#")

			? "<span style='background:#64748b;padding:10px 20px;border-radius:8px;'>Report Link Not Available</span>"

			: "<a href='" + reportLink + "' target='_blank' style='background:#22c55e;color:black;padding:10px 20px;border-radius:8px;text-decoration:none'>Open Full Report</a>")

			+ "</div>"

			/* ================= VIDEOS ================= */

			+ "<div id='videos' class='card hidden'>"

			+ "<h2>Execution Videos</h2>"

			+ "<div style='display:flex;flex-wrap:wrap;gap:20px;justify-content:center'>"

			+ videoCards

			+ "</div>"

			+ "</div>"

			/* ================= JS ================= */

			+ "<script>"

			+ "document.getElementById('summary').style.display='block';"

			+ "new Chart(document.getElementById('pieChart'),{responsive:true,type:'doughnut',data:{labels:['Pass','Fail','Skip'],datasets:[{data:"
			+ pieData
			+ ",backgroundColor:['#22c55e','#ef4444','#facc15']}]}});"

			+ "new Chart(document.getElementById('weekChart'),{responsive:true,type:'bar',data:{labels:"
			+ weekLabels
			+ ",datasets:[{label:'Manual',data:"
			+ manualData
			+ ",backgroundColor:'#22c55e'},{label:'Automation',data:"
			+ autoData
			+ ",backgroundColor:'#f59e0b'},{label:'Saved',data:"
			+ savedData
			+ ",backgroundColor:'#3b82f6'}]}});"

			+ "new Chart(document.getElementById('roiChart'),{responsive:true,type:'line',data:{labels:"
			+ weekLabels
			+ ",datasets:[{label:'ROI %',data:"
			+ roiData
			+ ",borderColor:'#22c55e',fill:false,tension:0.4}]}});"

			+ "</script>"

			+ "</body></html>";
			Files.write(Paths.get(path), html.getBytes(StandardCharsets.UTF_8));

			logger.info("Dashboard generated: " + path);

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new File(path).toURI());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Dashboard failed", e);
		}
	}
	// Need to add to @override

	public void switchToFrame(By locator, String frameName) {

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

		try {
			logger.info("Switching to frame: " + frameName);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

			test.get().log(Status.PASS, "Switched to frame: " + frameName);

		} catch (Exception e) {

			logAIFailure(e, frameName);
		}

	}

	public Map<String, Object> getWeeklyTestHealth() {

		Map<String, Object> result = new HashMap<>();

		try {

			String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

			File file = new File(roiPath);

			// ================= FILE NOT FOUND =================
			if (!file.exists()) {

				result.put("pass", 0);
				result.put("fail", 0);
				result.put("skip", 0);
				result.put("totalTests", 0);
				result.put("passPercent", 0.0);

				return result;
			}

			JSONParser parser = new JSONParser();

			JSONArray history = (JSONArray) parser.parse(new FileReader(file));

			// ================= CURRENT WEEK =================
			// Last Sunday to Today

			LocalDate today = LocalDate.now();

			LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			LocalDate weekEnd = today;

			logger.info("Today      : " + today);
			logger.info("Week Start : " + weekStart);
			logger.info("Week End   : " + weekEnd);

			int totalPass = 0;
			int totalFail = 0;
			int totalSkip = 0;

			// ================= CURRENT WEEK DATA =================
			for (Object obj : history) {

				JSONObject row = (JSONObject) obj;

				LocalDate runDate = LocalDate.parse(row.get("date").toString());

				// ONLY CURRENT WEEK
				if (runDate.isBefore(weekStart) || runDate.isAfter(weekEnd)) {
					continue;
				}

				int passCount = row.get("passCount") == null ? 0 : Integer.parseInt(row.get("passCount").toString());

				int failCount = row.get("failCount") == null ? 0 : Integer.parseInt(row.get("failCount").toString());

				int skipCount = row.get("skipCount") == null ? 0 : Integer.parseInt(row.get("skipCount").toString());

				logger.info("Counting -> " + runDate + " | Pass:" + passCount + " Fail:" + failCount + " Skip:"
						+ skipCount);

				totalPass += passCount;
				totalFail += failCount;
				totalSkip += skipCount;
			}

			int totalRuns = totalPass + totalFail + totalSkip;

			double passPercent = totalRuns > 0 ? (totalPass * 100.0) / totalRuns : 0.0;

			logger.info("TOTAL PASS : " + totalPass);

			logger.info("TOTAL FAIL : " + totalFail);

			logger.info("TOTAL SKIP : " + totalSkip);

			logger.info("TOTAL TEST : " + totalRuns);

			logger.info("PASS %     : " + String.format("%.2f", passPercent));

			result.put("pass", totalPass);
			result.put("fail", totalFail);
			result.put("skip", totalSkip);
			result.put("totalTests", totalRuns);
			result.put("passPercent", passPercent);

		} catch (Exception e) {

			e.printStackTrace();

			result.put("pass", 0);
			result.put("fail", 0);
			result.put("skip", 0);
			result.put("totalTests", 0);
			result.put("passPercent", 0.0);
		}

		return result;
	}

	public void saveROIHistory(int manualSeconds, int automationSeconds, int savedSeconds, double roi,
	        int passCount, int failCount, int skipCount, int jiraBugs) {

	    try {

	        String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

	        File file = new File(roiPath);

	        File parent = file.getParentFile();

	        if (parent != null && !parent.exists()) {
	            parent.mkdirs();
	        }

	        JSONArray history = new JSONArray();

	        if (file.exists() && file.length() > 0) {

	            JSONParser parser = new JSONParser();

	            try (FileReader fr = new FileReader(file)) {

	                history = (JSONArray) parser.parse(fr);
	            }
	        }

	        JSONObject obj = new JSONObject();

	        obj.put("date", LocalDate.now().toString());

	        obj.put("manualSeconds", manualSeconds);

	        obj.put("automationSeconds", automationSeconds);

	        obj.put("savedSeconds", savedSeconds);

	        obj.put("roi", roi);

	        obj.put("passCount", passCount);

	        obj.put("failCount", failCount);

	        obj.put("skipCount", skipCount);

	        obj.put("status", failCount > 0 ? "FAIL" : "PASS");

	        // ================= 🔥 NEW FIELD (JIRA BUG COUNT) =================
	        obj.put("jiraBugs", jiraBugs);

	        history.add(obj);

	        try (FileWriter fw = new FileWriter(file)) {

	            fw.write(history.toJSONString());

	            fw.flush();
	        }

	        logger.info("ROI History updated successfully");

	    } catch (Exception e) {

	        logger.error("Failed to save ROI history", e);
	    }
	}

	public Map<String, Object> getWeeklyROI() {

		Map<String, Object> result = new HashMap<>();

		try {

			String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

			File file = new File(roiPath);

			if (!file.exists()) {

				result.put("manualHours", 0.0);
				result.put("automationHours", 0.0);
				result.put("savedHours", 0.0);
				result.put("weeklyROI", 0.0);
				result.put("avgROI", 0.0);

				return result;
			}

			JSONParser parser = new JSONParser();

			JSONArray history = (JSONArray) parser.parse(new FileReader(file));

			// ================= CURRENT WEEK =================
			// Sunday to Saturday

			LocalDate today = LocalDate.now();

			LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

			LocalDate weekEnd = weekStart.plusDays(6);

			int totalManual = 0;
			int totalAutomation = 0;

			double roiTotal = 0.0;
			int runCount = 0;

			for (Object obj : history) {

				JSONObject row = (JSONObject) obj;

				LocalDate runDate = LocalDate.parse(row.get("date").toString());

				// ONLY CURRENT WEEK
				if (runDate.isBefore(weekStart) || runDate.isAfter(weekEnd)) {
					continue;
				}

				int manualSeconds = Integer.parseInt(row.get("manualSeconds").toString());

				int automationSeconds = Integer.parseInt(row.get("automationSeconds").toString());

				double roi = Double.parseDouble(row.get("roi").toString());

				totalManual += manualSeconds;
				totalAutomation += automationSeconds;

				roiTotal += roi;
				runCount++;
			}

			int saved = Math.max(0, totalManual - totalAutomation);

			double weeklyROI = totalManual > 0 ? (saved * 100.0) / totalManual : 0.0;

			double avgROI = runCount > 0 ? roiTotal / runCount : 0.0;

			result.put("manualHours", totalManual / 3600.0);

			result.put("automationHours", totalAutomation / 3600.0);

			result.put("savedHours", saved / 3600.0);

			result.put("weeklyROI", weeklyROI);

			result.put("avgROI", avgROI);

		} catch (Exception e) {

			result.put("manualHours", 0.0);
			result.put("automationHours", 0.0);
			result.put("savedHours", 0.0);
			result.put("weeklyROI", 0.0);
			result.put("avgROI", 0.0);
		}

		return result;
	}

	// ======================================================
	// MONTHLY TEST HEALTH
	// ======================================================
	public Map<String, Object> getMonthlyTestHealth() {

		Map<String, Object> result = new HashMap<>();

		try {

			String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

			File file = new File(roiPath);

			if (!file.exists()) {

				result.put("pass", 0);
				result.put("fail", 0);
				result.put("skip", 0);
				result.put("totalTests", 0);
				result.put("passPercent", 0.0);

				return result;
			}

			JSONParser parser = new JSONParser();

			JSONArray history = (JSONArray) parser.parse(new FileReader(file));

			// ===== GET LATEST DATE FROM JSON =====
			LocalDate today = LocalDate.MIN;

			for (Object obj : history) {

				JSONObject row = (JSONObject) obj;

				LocalDate runDate = LocalDate.parse(row.get("date").toString());

				if (runDate.isAfter(today)) {
					today = runDate;
				}
			}

			LocalDate monthStart = today.withDayOfMonth(1);

			int totalPass = 0;
			int totalFail = 0;
			int totalSkip = 0;

			for (Object obj : history) {

				JSONObject row = (JSONObject) obj;

				LocalDate runDate = LocalDate.parse(row.get("date").toString());

				if (runDate.isBefore(monthStart) || runDate.isAfter(today)) {
					continue;
				}

				int passCount = row.get("passCount") == null ? 0 : Integer.parseInt(row.get("passCount").toString());

				int failCount = row.get("failCount") == null ? 0 : Integer.parseInt(row.get("failCount").toString());

				int skipCount = row.get("skipCount") == null ? 0 : Integer.parseInt(row.get("skipCount").toString());

				totalPass += passCount;
				totalFail += failCount;
				totalSkip += skipCount;
			}

			int totalTests = totalPass + totalFail + totalSkip;

			double passPercent = totalTests > 0 ? (totalPass * 100.0) / totalTests : 0.0;

			result.put("pass", totalPass);
			result.put("fail", totalFail);
			result.put("skip", totalSkip);
			result.put("totalTests", totalTests);
			result.put("passPercent", passPercent);

		} catch (Exception e) {

			result.put("pass", 0);
			result.put("fail", 0);
			result.put("skip", 0);
			result.put("totalTests", 0);
			result.put("passPercent", 0.0);
		}

		return result;
	}

	// ======================================================
	// MONTHLY ROI
	// ======================================================
	public Map<String, Object> getMonthlyROI() {

		Map<String, Object> result = new HashMap<>();

		try {

			String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

			File file = new File(roiPath);

			if (!file.exists()) {

				result.put("manualHours", 0.0);
				result.put("automationHours", 0.0);
				result.put("savedHours", 0.0);
				result.put("monthlyROI", 0.0);
				result.put("avgROI", 0.0);

				return result;
			}

			JSONParser parser = new JSONParser();

			JSONArray history = (JSONArray) parser.parse(new FileReader(file));

			// ===== GET LATEST DATE FROM JSON =====
			LocalDate today = LocalDate.MIN;

			for (Object obj : history) {

				JSONObject row = (JSONObject) obj;

				LocalDate runDate = LocalDate.parse(row.get("date").toString());

				if (runDate.isAfter(today)) {
					today = runDate;
				}
			}

			LocalDate monthStart = today.withDayOfMonth(1);

			int totalManual = 0;
			int totalAutomation = 0;

			double roiTotal = 0.0;
			int runCount = 0;

			for (Object obj : history) {

				JSONObject row = (JSONObject) obj;

				LocalDate runDate = LocalDate.parse(row.get("date").toString());

				if (runDate.isBefore(monthStart) || runDate.isAfter(today)) {
					continue;
				}

				int manualSeconds = Integer.parseInt(row.get("manualSeconds").toString());

				int automationSeconds = Integer.parseInt(row.get("automationSeconds").toString());

				double roi = Double.parseDouble(row.get("roi").toString());

				totalManual += manualSeconds;
				totalAutomation += automationSeconds;

				roiTotal += roi;
				runCount++;
			}

			int saved = Math.max(0, totalManual - totalAutomation);

			double monthlyROI = totalManual > 0 ? (saved * 100.0) / totalManual : 0.0;

			double avgROI = runCount > 0 ? roiTotal / runCount : 0.0;

			result.put("manualHours", totalManual / 3600.0);

			result.put("automationHours", totalAutomation / 3600.0);

			result.put("savedHours", saved / 3600.0);

			result.put("monthlyROI", monthlyROI);

			result.put("avgROI", avgROI);

		} catch (Exception e) {

			result.put("manualHours", 0.0);
			result.put("automationHours", 0.0);
			result.put("savedHours", 0.0);
			result.put("monthlyROI", 0.0);
			result.put("avgROI", 0.0);
		}

		return result;
	}
	
	public static Map<String, Integer> getJiraCounts() {

	    Map<String, Integer> counts = new HashMap<>();

	    int weekly = 0;
	    int monthly = 0;

	    try {

	        File file = new File("jira_history.csv");

	        if (!file.exists()) {
	            counts.put("weekly", 0);
	            counts.put("monthly", 0);
	            return counts;
	        }

	        BufferedReader br = new BufferedReader(new FileReader(file));
	        String line;

	        java.time.LocalDate now = java.time.LocalDate.now();

	        while ((line = br.readLine()) != null) {

	            String[] parts = line.split(",");
	            java.time.LocalDate date = java.time.LocalDate.parse(parts[0]);

	            if (date.isAfter(now.minusDays(7))) weekly++;

	            if (date.getMonth() == now.getMonth()) monthly++;
	        }

	        br.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    counts.put("weekly", weekly);
	    counts.put("monthly", monthly);

	    return counts;
	}
	
	private static void saveJiraHistory(String issueKey) {
	    try {
	        FileWriter fw = new FileWriter("jira_history.csv", true);

	        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

	        fw.write(date + "," + issueKey + "\n");
	        fw.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private String getJiraHistoryLinks() {

	    StringBuilder links = new StringBuilder();

	    try {

	        String roiPath = System.getProperty("user.dir") + "/" + config.getProperty("roiHistoryPath");

	        File file = new File(roiPath);

	        if (!file.exists()) return "";

	        JSONParser parser = new JSONParser();

	        JSONArray arr;

	        try (FileReader reader = new FileReader(file)) {
	            arr = (JSONArray) parser.parse(reader);
	        }

	        String baseUrl = config.getProperty("jira.baseUrl");

	        for (int i = arr.size() - 1; i >= 0; i--) {

	            JSONObject obj = (JSONObject) arr.get(i);

	            String date = (String) obj.get("date");

	            JSONArray keys = (JSONArray) obj.get("jiraKeys");

	            if (keys != null && keys.size() > 0) {

	                links.append("<p><b>").append(date).append("</b><br>");

	                for (Object k : keys) {

	                    String key = (String) k;

	                    links.append("<a href='")
	                         .append(baseUrl).append("/browse/").append(key)
	                         .append("' target='_blank'>")
	                         .append(key)
	                         .append("</a><br>");
	                }

	                links.append("</p>");
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return links.toString();
	}
	
	public Map<String, Integer> getTestTypeCounts(String filePath) {

	    Map<String, Integer> counts = new HashMap<>();

	    counts.put("Smoke", 0);
	    counts.put("Sanity", 0);
	    counts.put("Regression", 0);
	    counts.put("Functional", 0);

	    try {
	        JSONParser parser = new JSONParser();
	        JSONArray arr = (JSONArray) parser.parse(new FileReader(filePath));

	        for (Object o : arr) {
	            JSONObject obj = (JSONObject) o;

	            String type = (String) obj.get("type");

	            if (counts.containsKey(type)) {
	                counts.put(type, counts.get(type) + 1);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return counts;
	}
	

}