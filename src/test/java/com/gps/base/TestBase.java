package com.gps.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
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

	        String path = System.getProperty("user.dir") + "/reports/AutomationReport_"
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
	    @Parameters({"browser","url"})
	    public void setUp(@Optional("") String browserFromXml,
	                      @Optional("") String urlFromXml,
	                      Method method) throws Exception  {

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
	                                      "C:\\Users\\RAGHAVENDRA\\Downloads\\edgedriver_win64\\msedgedriver.exe");
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

	            test.get().pass("Test Passed");

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
	/* ================= EXTENT REPORT END ================= */
	@AfterSuite(alwaysRun = true)
	public void endReport() {

	    try {

	        if (extent != null) {

	            // Browser remains comma separated
	            extent.setSystemInfo("Browser", String.join(", ", browsers));

	            // Convert URLs into numbered format
	            StringBuilder urlList = new StringBuilder();
	            int count = 1;

	            for (String url : appUrls) {
	                urlList.append(count++)
	                       .append(". ")
	                       .append(url)
	                       .append("\n");
	            }

	            extent.setSystemInfo("Application URL", urlList.toString());

	            extent.flush();

	            Desktop.getDesktop().browse(new File(reportPath).toURI());
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

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

			test.get().log(Status.FAIL,
					aiSuggestion + "<br>" + "<a href='" + screenshotPath + "' target='_blank'>Click here for screenshot</a>");
		}
	}

	/* ================= BASE METHODS ================= */

	@Override
	public void click(By element, String passValue, String failValue) {
		try {
			getDriver().findElement(element).click();
			test.get().log(Status.PASS, passValue);
		} catch (Exception e) {
			logAIFailure(e, failValue);
		}
	}

	@Override
	public void actionclick(WebElement element, String passValue, String failValue) {
		try {
			new Actions(getDriver()).moveToElement(element).click().perform();
			test.get().log(Status.PASS, passValue);
		} catch (Exception e) {
			logAIFailure(e, failValue);
		}
	}

	@Override
	public void clear(By element, String passvalue, String failValue) {
		try {
			getDriver().findElement(element).clear();
		} catch (Exception e) {
			logAIFailure(e, failValue);
		}
	}

	@Override
	public void sendkeys(By element, String value, String passvalue, String failValue) {
		try {
			getDriver().findElement(element).sendKeys(value);
		} catch (Exception e) {
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
}