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
	        ((JavascriptExecutor) getDriver())
	                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

	        // Small wait (for stability)
	        Thread.sleep(300);

	        // 🔥 Try normal click first
	        try {
	            element.click();
	        } catch (Exception e) {

	            // 🔥 Fallback to Actions
	            try {
	                new Actions(getDriver())
	                        .moveToElement(element)
	                        .pause(Duration.ofMillis(200))
	                        .click()
	                        .perform();
	            } catch (Exception ex) {

	                // 🔥 Final fallback → JS click (MOST POWERFUL)
	                ((JavascriptExecutor) getDriver())
	                        .executeScript("arguments[0].click();", element);
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
	            logValue = value.charAt(0)
	                    + "*".repeat(value.length() - 2)
	                    + value.charAt(value.length() - 1);
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
	        ExcelUtil excelUtil = new ExcelUtil(System.getProperty("user.dir")
	                + "/src/test/resources/excel/Gps_Rules.xls");

	        return excelUtil.getSingleCellValue("RunManager", testName);
	    }
	   
	   public JSONArray getJsonArray() {
	        try {
	            return (JSONArray) new JSONParser().parse(new FileReader(
	                    System.getProperty("user.dir") + "/src/test/resources/excel/testdata.json"));
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

	        Assert.assertTrue(actual.contains(expectedText), 
	            "Expected text not found. Actual: " + actual);

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
	             //   test.get().log(Status.PASS, passMsg);
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
	   

} }