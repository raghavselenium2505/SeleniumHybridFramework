package com.gps.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.io.FileReader;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.gps.utilities.ExcelUtil;
import com.gps.utilities.ScreenshotUtil;
import com.web.utilities.AITestAnalyzer;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

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
	private static String reportPath;

	/* ================= CONFIG & UTILITIES ================= */

	public static Properties config = new Properties();
	public static ScreenshotUtil screenshotutil = new ScreenshotUtil();

	static String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/Gps_Rules.xls";

	public static ExcelUtil excelUtil;

	public static Logger logger = Logger.getLogger("devpinoyLogger");

	private static final String CONFIG_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/properties/Config.properties";

	/* ================= START EXTENT REPORT ================= */

	@BeforeSuite(alwaysRun = true)
	public void startReport() {

		if (extent == null) {

			String path = System.getProperty("user.dir") + "/Reports/Extentreport/AutomationReport_"
					+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";

			ExtentSparkReporter spark = new ExtentSparkReporter(path);
			spark.config().setTheme(Theme.DARK);
			spark.config().setReportName("Automation Execution Report");
			spark.config().setDocumentTitle("Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			extent.setSystemInfo("User", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("OS", System.getProperty("os.name"));

			reportPath = path;
		}
	}
	/* ================= BROWSER SETUP ================= */

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser", "url" })
	public void setUp(@Optional("") String browserFromXml, @Optional("") String urlFromXml, Method method)
			throws Exception {

		config.load(new FileInputStream(CONFIG_PATH));

		// ===== Browser Selection =====
		String browser;

		if (browserFromXml != null && !browserFromXml.trim().isEmpty()) {
			browser = browserFromXml.trim();
		} else {
			browser = config.getProperty("browser");
		}

		System.out.println("Launching Browser : " + browser);

		WebDriver localDriver;

		if (browser.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			localDriver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Edge")) {

			System.setProperty("webdriver.edge.driver",
					"D:\\Raghavendra's documents\\edgedriver_win64\\msedgedriver.exe");
			localDriver = new EdgeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			localDriver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			localDriver = new InternetExplorerDriver();

		} else {

			throw new RuntimeException("Unsupported browser: " + browser);
		}

		// ===== Set Driver =====
		setDriver(localDriver);

		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();

		// ===== Capture Browser Info for Report =====
		Capabilities caps = ((RemoteWebDriver) getDriver()).getCapabilities();
		browsers.add(caps.getBrowserName());

		// ===== URL Selection =====
		String url;

		if (urlFromXml != null && !urlFromXml.trim().isEmpty()) {
			url = urlFromXml.trim();
		} else {
			url = config.getProperty("testsiteurl");
		}

		System.out.println("Opening URL : " + url);

		appUrls.add(url);

		getDriver().get(url);

		// ===== Create Extent Test =====
		ExtentTest extentTest = extent.createTest(method.getName());
		test.set(extentTest);
	}

	/* ================= TEST RESULT CAPTURE ================= */

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		if (test.get() != null) {

			if (result.getStatus() == ITestResult.SUCCESS) {

				logger.info("testcases completed sucesfully");

			} else if (result.getStatus() == ITestResult.FAILURE) {

				test.get().fail(result.getThrowable());

			} else if (result.getStatus() == ITestResult.SKIP) {

				if (result.getThrowable() != null) {

					test.get().skip(result.getThrowable().getMessage());

				} else {

					test.get().skip("Test Skipped");
				}
			}
		}

		if (getDriver() != null) {

			getDriver().quit();
		}

		removeDriver();
	}

	private void uploadFolderToS3(File folder, String s3BasePath) {

		if (folder == null || !folder.exists()) {
			logger.warn("Folder not found: " + folder);
			return;
		}

		File[] files = folder.listFiles();
		if (files == null)
			return;

		String accessKey = config.getProperty("aws.accessKey");
		String secretKey = config.getProperty("aws.secretKey");
		String bucketName = config.getProperty("aws.bucketName");
		String region = config.getProperty("aws.region");

		// 🔥 Create client ONCE
		software.amazon.awssdk.services.s3.S3Client s3Client = software.amazon.awssdk.services.s3.S3Client.builder()
				.region(software.amazon.awssdk.regions.Region.of(region))
				.credentialsProvider(software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(
						software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(accessKey, secretKey)))
				.build();

		uploadRecursive(folder, s3BasePath, s3Client, bucketName);
	}

	// ================= ONLY CHANGED PART BELOW =================
	private void uploadRecursive(File folder, String s3BasePath,
	        software.amazon.awssdk.services.s3.S3Client s3Client,
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
	                software.amazon.awssdk.services.s3.model.PutObjectRequest.Builder builder =
	                        software.amazon.awssdk.services.s3.model.PutObjectRequest.builder()
	                                .bucket(bucketName)
	                                .key(key);

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

	    if (fileName.endsWith(".html")) return "text/html";
	    if (fileName.endsWith(".png")) return "image/png";
	    if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";
	    if (fileName.endsWith(".gif")) return "image/gif";

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
	public void endReport() {

	    try {

	        if (extent != null) {

	            extent.setSystemInfo("Browser", String.join(", ", browsers));

	            StringBuilder urlList = new StringBuilder();
	            int count = 1;

	            for (String url : appUrls) {
	                urlList.append(count++)
	                       .append(". ")
	                       .append(url)
	                       .append("\n");
	            }

	            extent.setSystemInfo("Application URL", urlList.toString());

	            // 🔥 STEP 1: Flush report
	            extent.flush();

	            // 🔥 STEP 2: Wait for file ready
	            waitForReportToBeReady(reportPath);

	            String finalUrl = "";

	            // ✅ FEATURE FLAG CHECK
	            if (isAwsUploadEnabled()) {

	                try {

	                    String bucketName = config.getProperty("aws.bucketName");
	                    String region = config.getProperty("aws.region");

	                    // 🔥 Unique file name
	                    String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
	                            .format(new java.util.Date());

	                    String s3Key = "reports/latest/AutomationReport_" + timeStamp + ".html";

	                    // 🔥 Upload ONLY report
	                    uploadSingleFileToS3(reportPath, s3Key);

	                    finalUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + s3Key;

	                    logger.info("AWS Report URL: " + finalUrl);

	                } catch (Exception ex) {
	                    logger.error("S3 upload failed", ex);
	                }

	                // ✅ ADD URL IN REPORT
	                extent.setSystemInfo("AWS Report URL", finalUrl);

	                if (test.get() != null) {
	                    test.get().info(
	                        "<b style='color:blue;'>AWS Report URL:</b> " +
	                        "<a href='" + finalUrl + "' target='_blank'>" + finalUrl + "</a>"
	                    );
	                }

	                // ✅ EMAIL (OPTIONAL FLAG)
	                if (isEmailEnabled()) {

	                    String emailBody =
	                            "<h3>Automation Execution Report</h3>" +
	                            "<p>Execution completed.</p>" +
	                            "<p><b>Report:</b> <a href='" + finalUrl + "'>" + finalUrl + "</a></p>";

	                    sendEmailViaSES(
	                            config.getProperty("aws.toEmail"),
	                            "Automation Report",
	                            emailBody
	                    );
	                }

	            } else {
	                logger.info("AWS Upload Disabled → Skipping S3 & Email");
	            }

	            // 🔥 FINAL FLUSH
	            extent.flush();

	            if (Desktop.isDesktopSupported()) {
	                Desktop.getDesktop().browse(new File(reportPath).toURI());
	            }
	        }

	    } catch (Exception e) {
	        logger.error("Error in endReport()", e);
	    }
	}
	
	
	
	
	private void uploadSingleFileToS3(String filePath, String s3Key) {

	    try {

	        String accessKey = config.getProperty("aws.accessKey");
	        String secretKey = config.getProperty("aws.secretKey");
	        String bucketName = config.getProperty("aws.bucketName");
	        String region = config.getProperty("aws.region");

	        software.amazon.awssdk.services.s3.S3Client s3Client =
	                software.amazon.awssdk.services.s3.S3Client.builder()
	                        .region(software.amazon.awssdk.regions.Region.of(region))
	                        .credentialsProvider(
	                                software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(
	                                        software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(
	                                                accessKey,
	                                                secretKey)))
	                        .build();

	        File file = new File(filePath);

	        // 🔥 IMPORTANT: Dynamic content type
	        String contentType = getContentType(file.getName());

	        software.amazon.awssdk.services.s3.model.PutObjectRequest request =
	                software.amazon.awssdk.services.s3.model.PutObjectRequest.builder()
	                        .bucket(bucketName)
	                        .key(s3Key)
	                        .contentType(contentType) // 🔥 FIXED
	                        .build();

	        s3Client.putObject(request, file.toPath());

	        logger.info("Uploaded to S3: " + s3Key + " | Type: " + contentType);

	    } catch (Exception e) {
	        logger.error("Failed to upload to S3", e);
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
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    logger.info("Report file is stable and ready for upload");
	}

	private void deleteS3Folder(String s3BasePath) {

		try {

			String accessKey = config.getProperty("aws.accessKey");
			String secretKey = config.getProperty("aws.secretKey");
			String bucketName = config.getProperty("aws.bucketName");
			String region = config.getProperty("aws.region");

			software.amazon.awssdk.services.s3.S3Client s3Client = software.amazon.awssdk.services.s3.S3Client.builder()
					.region(software.amazon.awssdk.regions.Region.of(region))
					.credentialsProvider(software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(
							software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(accessKey, secretKey)))
					.build();

			String continuationToken = null;

			do {

				software.amazon.awssdk.services.s3.model.ListObjectsV2Request.Builder listReqBuilder = software.amazon.awssdk.services.s3.model.ListObjectsV2Request
						.builder().bucket(bucketName).prefix(s3BasePath);

				if (continuationToken != null) {
					listReqBuilder.continuationToken(continuationToken);
				}

				software.amazon.awssdk.services.s3.model.ListObjectsV2Response listResponse = s3Client
						.listObjectsV2(listReqBuilder.build());

				if (listResponse.contents() != null && !listResponse.contents().isEmpty()) {

					for (software.amazon.awssdk.services.s3.model.S3Object s3Object : listResponse.contents()) {

						software.amazon.awssdk.services.s3.model.DeleteObjectRequest deleteRequest = software.amazon.awssdk.services.s3.model.DeleteObjectRequest
								.builder().bucket(bucketName).key(s3Object.key()).build();

						s3Client.deleteObject(deleteRequest);

						logger.info("Deleted: " + s3Object.key());
					}
				}

				continuationToken = listResponse.nextContinuationToken();

			} while (continuationToken != null);

		} catch (Exception e) {
			logger.error("Failed to clean S3 folder", e);
		}
	}
	/* ================= AI FAILURE LOGGER ================= */

	/* ================= AI FAILURE LOGGER ================= */

	protected void logAIFailure(Exception e, String failValue) {

	    String aiSuggestion = AITestAnalyzer.analyze(e);
	    String screenshotPath = null;

	    try {
	        screenshotPath = screenshotutil.takeScreenshot(getDriver());
	    } catch (Exception ex) {
	        logger.warn("Screenshot capture failed");
	    }

	    if (test.get() != null) {

	        // 🔴 Log failure step
	        test.get().log(Status.FAIL, failValue);

	        if (screenshotPath != null && !screenshotPath.isEmpty()) {

	            try {

	                File file = new File(screenshotPath);
	                String screenshotName = file.getName();

	                String bucketName = config.getProperty("aws.bucketName");
	                String region = config.getProperty("aws.region");

	                // 🔥 ALWAYS SAME STRUCTURE
	                String s3Key = "reports/latest/screenshots/" + screenshotName;

	                // 🔥 UPLOAD FILE
	                uploadSingleFileToS3(screenshotPath, s3Key);

	                // 🔥 FINAL S3 URL (NO MISMATCH)
	                String s3ScreenshotUrl = "https://" + bucketName + ".s3." + region
	                        + ".amazonaws.com/" + s3Key;

	                // 🔥 CLICKABLE IMAGE (BEST PRACTICE)
	                test.get().log(Status.FAIL,
	                        aiSuggestion + "<br><br>" +
	                        "<b>Failure Screenshot:</b><br>" +

	                        "<a href='" + s3ScreenshotUrl + "' target='_blank'>" +
	                        "<img src='" + s3ScreenshotUrl + "' height='150' " +
	                        "style='border:1px solid #ccc;border-radius:5px;'/>" +
	                        "</a>"
	                );

	            } catch (Exception ex) {

	                logger.error("Screenshot upload failed", ex);

	                test.get().log(Status.FAIL,
	                        aiSuggestion + "<br><b>Screenshot upload failed</b>");
	            }

	        } else {

	            test.get().log(Status.FAIL,
	                    aiSuggestion + "<br><b>Screenshot not available</b>");
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

			// 🔥 Mask value (applies to EVERYTHING)
			String logValue;
			if (value == null || value.length() <= 1) {
				logValue = "*";
			} else if (value.length() == 2) {
				logValue = "**";
			} else {
				logValue = value.charAt(0) + "*".repeat(value.length() - 2) + value.charAt(value.length() - 1);
			}

			// Log
			test.get().log(Status.PASS, passValue + " | Entered Value: " + logValue);

		} catch (Exception e) {
			test.get().log(Status.FAIL, failValue + " | Exception: " + e.getMessage());
			Assert.fail("SendKeys failed due to exception: " + e.getMessage());
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
			test.get().log(Status.FAIL, failMsg + " Exception: " + e.getMessage());
			org.testng.Assert.fail(failMsg);
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

	        software.amazon.awssdk.services.ses.SesClient sesClient =
	                software.amazon.awssdk.services.ses.SesClient.builder()
	                        .region(software.amazon.awssdk.regions.Region.of(region))
	                        .credentialsProvider(
	                                software.amazon.awssdk.auth.credentials.StaticCredentialsProvider.create(
	                                        software.amazon.awssdk.auth.credentials.AwsBasicCredentials.create(
	                                                accessKey, secretKey)))
	                        .build();

	        software.amazon.awssdk.services.ses.model.SendEmailRequest request =
	                software.amazon.awssdk.services.ses.model.SendEmailRequest.builder()
	                        .source(fromEmail)
	                        .destination(
	                                software.amazon.awssdk.services.ses.model.Destination.builder()
	                                        .toAddresses(toEmail)
	                                        .build()
	                        )
	                        .message(
	                                software.amazon.awssdk.services.ses.model.Message.builder()
	                                        .subject(
	                                                software.amazon.awssdk.services.ses.model.Content.builder()
	                                                        .data(subject)
	                                                        .build()
	                                        )
	                                        .body(
	                                                software.amazon.awssdk.services.ses.model.Body.builder()
	                                                        .html(
	                                                                software.amazon.awssdk.services.ses.model.Content.builder()
	                                                                        .data(body)
	                                                                        .build()
	                                                        )
	                                                        .build()
	                                        )
	                                        .build()
	                        )
	                        .build();

	        sesClient.sendEmail(request);

	        logger.info("Email sent successfully");

	    } catch (Exception e) {

	        logger.error("❌ SES EMAIL FAILED: " + e.getMessage(), e);
	    }
	}
}