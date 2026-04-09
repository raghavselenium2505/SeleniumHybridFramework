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
import java.util.concurrent.atomic.AtomicInteger;
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
	private static String reportPath;

	public static AtomicInteger passCount = new AtomicInteger(0);
	public static AtomicInteger failCount = new AtomicInteger(0);
	public static AtomicInteger skipCount = new AtomicInteger(0);
	
	public static long suiteStartTime;
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
	public synchronized void startReport() {

	    if (extent == null) {

	        String path = System.getProperty("user.dir")
	                + "/src/test/resources/Reports/Extentreport/AutomationReport_"
	                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";

	        System.out.println("🔥 Report Path: " + path);

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

	    try {

	        if (test.get() != null) {

	            if (result.getStatus() == ITestResult.SUCCESS) {

	                passCount.incrementAndGet();  // ✅ FIX
	                test.get().pass("Test Passed");

	            } else if (result.getStatus() == ITestResult.FAILURE) {

	                failCount.incrementAndGet();  // ✅ FIX
	                test.get().fail(result.getThrowable());

	            } else if (result.getStatus() == ITestResult.SKIP) {

	                skipCount.incrementAndGet();  // ✅ FIX
	                test.get().skip("Test Skipped");
	            }
	        }

	    } catch (Exception e) {
	        logger.error("Error in tearDown()", e);
	    } finally {

	        if (getDriver() != null) {
	            getDriver().quit();
	        }

	        removeDriver();
	    }
	}
	private void uploadFolderToS3(File folder, String s3BasePath) {

	    if (folder == null || !folder.exists()) return;

	    String bucketName = config.getProperty("aws.bucketName");
	    String region = config.getProperty("aws.region");

	    AwsBasicCredentials credentials = AwsBasicCredentials.create(
	            System.getenv("AWS_ACCESS_KEY_ID"),
	            System.getenv("AWS_SECRET_ACCESS_KEY"));

	    S3Client s3Client = S3Client.builder()
	            .region(Region.of(region))
	            .credentialsProvider(StaticCredentialsProvider.create(credentials))
	            .build();

	    File[] files = folder.listFiles();
	    if (files == null) return;

	    for (File file : files) {

	        if (file.isDirectory()) {
	            uploadFolderToS3(file, s3BasePath + "/" + file.getName());
	        } else {
	            try {

	                String key = s3BasePath + "/" + file.getName();

	                PutObjectRequest request = PutObjectRequest.builder()
	                        .bucket(bucketName)
	                        .key(key)
	                        .contentType(getContentType(file.getName()))
	                        .build();

	                s3Client.putObject(request, file.toPath());

	                logger.info("Uploaded screenshot: " + key);

	            } catch (Exception e) {
	                logger.error("Screenshot upload failed", e);
	            }
	        }
	    }
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

	        if (extent == null) {
	            logger.error("Extent is NULL");
	            return;
	        }

	        // ==============================
	        // ✅ FINALIZE EXTENT REPORT
	        // ==============================
	        extent.flush();
	        waitForReportToBeReady(reportPath);

	        String finalUrl = "";

	        // ==============================
	        // ✅ AWS UPLOAD
	        // ==============================
	        if (isAwsUploadEnabled()) {

	            try {

	                String bucketName = config.getProperty("aws.bucketName");
	                String region = config.getProperty("aws.region");

	                String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
	                        .format(new java.util.Date());

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

	        // ==============================
	        // ✅ FETCH COUNTS (THREAD SAFE)
	        // ==============================
	        int pass = passCount.get();
	        int fail = failCount.get();
	        int skip = skipCount.get();

	        int total = pass + fail + skip;

	        logger.info("Execution Summary -> Total: " + total +
	                " Pass: " + pass +
	                " Fail: " + fail +
	                " Skip: " + skip);

	        // ==============================
	        // ✅ EXECUTION TIME
	        // ==============================
	        long duration = System.currentTimeMillis() - suiteStartTime;

	        // ==============================
	        // 🚀 GENERATE EXTERNAL DASHBOARD (BEST APPROACH)
	        // ==============================
	        generateDashboardHtml(pass, fail, skip, finalUrl, duration);

	        // ==============================
	        // ✅ OPEN EXTENT REPORT
	        // ==============================
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

	        AwsBasicCredentials credentials = AwsBasicCredentials.create(
	                System.getenv("AWS_ACCESS_KEY_ID"),
	                System.getenv("AWS_SECRET_ACCESS_KEY"));

	        S3Client s3Client = S3Client.builder()
	                .region(Region.of(region))
	                .credentialsProvider(StaticCredentialsProvider.create(credentials))
	                .build();

	        PutObjectRequest request = PutObjectRequest.builder()
	                .bucket(bucketName)
	                .key(s3Key)
	                .contentType(getContentType(file.getName()))
	                .build();

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

	        AwsBasicCredentials credentials = AwsBasicCredentials.create(
	                System.getenv("AWS_ACCESS_KEY_ID"),
	                System.getenv("AWS_SECRET_ACCESS_KEY"));

	        S3Client s3Client = S3Client.builder()
	                .region(Region.of(region))
	                .credentialsProvider(StaticCredentialsProvider.create(credentials))
	                .build();

	        String continuationToken = null;

	        do {

	            ListObjectsV2Request.Builder requestBuilder = ListObjectsV2Request.builder()
	                    .bucket(bucketName)
	                    .prefix(prefix);

	            if (continuationToken != null) {
	                requestBuilder.continuationToken(continuationToken);
	            }

	            ListObjectsV2Response response = s3Client.listObjectsV2(requestBuilder.build());

	            for (S3Object obj : response.contents()) {

	                s3Client.deleteObject(DeleteObjectRequest.builder()
	                        .bucket(bucketName)
	                        .key(obj.key())
	                        .build());

	                System.out.println("Deleted: " + obj.key());
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
	        screenshotPath = screenshotutil.takeScreenshot(getDriver());
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
	                test.get().log(Status.FAIL,
	                        aiSuggestion + "<br><br>" +
	                        "<a href='" + s3Url + "' target='_blank' " +
	                        "style='color:red;font-weight:bold;'>👉 View Screenshot</a>");

	            } catch (Exception ex) {

	                // 🔥 IF UPLOAD FAILS
	                test.get().log(Status.FAIL,
	                        aiSuggestion + "<br><br>" +
	                        "<b style='color:red;'>Screenshot upload failed</b>");

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
	
	public void generateDashboardHtml(int pass, int fail, int skip, String finalUrl, long durationMillis) {

	    try {

	    	String baseDir = System.getProperty("user.dir") + "/dashboard";

	    	File folder = new File(baseDir);
	    	if (!folder.exists()) {
	    	    folder.mkdirs();
	    	}

	    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    	String path = baseDir + "/AutomationDashboard_" + timeStamp + ".html";
	        int total = pass + fail + skip;

	        int passPer = total == 0 ? 0 : (pass * 100 / total);
	        int failPer = total == 0 ? 0 : (fail * 100 / total);
	        int skipPer = total == 0 ? 0 : (skip * 100 / total);

	        long seconds = durationMillis / 1000;
	        long minutes = seconds / 60;
	        seconds = seconds % 60;

	        String status = (fail > 0) ? "FAILED ❌" : "PASSED ✅";
	        String statusColor = (fail > 0) ? "#ff6b6b" : "#4ade80";

	        String html =
	        "<html><head><title>Automation Dashboard</title>" +

	        "<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>" +

	        "<style>" +

	        "body{font-family:Segoe UI;background:#0f172a;color:white;padding:20px;text-align:center}" +

	        ".tabs{margin-bottom:20px}" +
	        ".tab{cursor:pointer;padding:10px 20px;background:#1e293b;border-radius:8px;margin-right:10px;display:inline-block}" +
	        ".tab:hover{background:#334155}" +

	        ".card{background:#1e293b;padding:20px;border-radius:10px;margin-top:20px}" +

	        ".chart-row{display:flex;justify-content:center;gap:40px;flex-wrap:wrap}" +

	        ".chart-container{" +
	        "width:300px;" +
	        "height:300px;" +
	        "}" +

	        ".hidden{display:none}" +

	        "</style>" +

	        "<script>" +
	        "function showTab(tab){" +
	        "document.getElementById('summary').style.display='none';" +
	        "document.getElementById('charts').style.display='none';" +
	        "document.getElementById(tab).style.display='block';" +
	        "}" +
	        "</script>" +

	        "</head><body>" +

	        "<h1>🚀 Automation Dashboard</h1>" +

	        "<div class='tabs'>" +
	        "<span class='tab' onclick=\"showTab('summary')\">Summary</span>" +
	        "<span class='tab' onclick=\"showTab('charts')\">Charts</span>" +
	        "</div>" +

	        // ================= SUMMARY =================
	        "<div id='summary' class='card'>" +

	        "<h2 style='color:" + statusColor + "'>Build Status: " + status + "</h2>" +
	        "<p>Total Tests: " + total + "</p>" +
	        "<p>Execution Time: " + minutes + "m " + seconds + "s</p>" +

	        "<p>✔ Passed: " + pass + " (" + passPer + "%)</p>" +
	        "<p>❌ Failed: " + fail + " (" + failPer + "%)</p>" +
	        "<p>⚠ Skipped: " + skip + " (" + skipPer + "%)</p>" +

	        "<br>" +
	        "<a href='" + finalUrl + "' target='_blank' " +
	        "style='background:#22c55e;color:black;padding:10px 20px;border-radius:8px;text-decoration:none'>" +
	        "Open Full Report</a>" +

	        "</div>" +

	        // ================= CHARTS =================
	        "<div id='charts' class='card hidden'>" +

	        "<div class='chart-row'>" +

	        "<div class='chart-container'><canvas id='pieChart'></canvas></div>" +
	        "<div class='chart-container'><canvas id='barChart'></canvas></div>" +
	        "<div class='chart-container'><canvas id='horizontalChart'></canvas></div>" +

	        "</div>" +

	        "</div>" +

	        "<script>" +

	        "document.getElementById('summary').style.display='block';" +

	        // DOUGHNUT (BEST LOOK)
	        "new Chart(document.getElementById('pieChart'), {" +
	        "type:'doughnut'," +
	        "data:{labels:['Passed','Failed','Skipped']," +
	        "datasets:[{data:[" + pass + "," + fail + "," + skip + "]," +
	        "backgroundColor:['#22c55e','#ef4444','#facc15']}]}," +
	        "options:{responsive:true,maintainAspectRatio:false}" +
	        "});" +

	        // BAR
	        "new Chart(document.getElementById('barChart'), {" +
	        "type:'bar'," +
	        "data:{labels:['Passed','Failed','Skipped']," +
	        "datasets:[{data:[" + pass + "," + fail + "," + skip + "]," +
	        "backgroundColor:['#22c55e','#ef4444','#facc15']}]}," +
	        "options:{responsive:true,maintainAspectRatio:false}" +
	        "});" +

	        // HORIZONTAL BAR
	        "new Chart(document.getElementById('horizontalChart'), {" +
	        "type:'bar'," +
	        "data:{labels:['Passed','Failed','Skipped']," +
	        "datasets:[{data:[" + pass + "," + fail + "," + skip + "]," +
	        "backgroundColor:['#22c55e','#ef4444','#facc15']}]}," +
	        "options:{indexAxis:'y',responsive:true,maintainAspectRatio:false}" +
	        "});" +

	        "</script>" +

	        "</body></html>";

	        java.nio.file.Files.write(java.nio.file.Paths.get(path), html.getBytes());

	        logger.info("Dashboard generated: " + path);

	        if (Desktop.isDesktopSupported()) {
	            Desktop.getDesktop().browse(new File(path).toURI());
	        }

	    } catch (Exception e) {
	        logger.error("Dashboard generation failed", e);
	    }
	}
	
	
}