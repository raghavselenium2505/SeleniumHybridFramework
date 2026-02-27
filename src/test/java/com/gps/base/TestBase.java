package com.gps.base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
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

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    private void setDriver(WebDriver d) {
        driver.set(d);
    }

    private void removeDriver() {
        driver.remove();
    }

    private static ThreadLocal<ExtentReports> extent = new ThreadLocal<>();
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<String> reportPath = new ThreadLocal<>();

    public static Properties config = new Properties();
    public static ScreenshotUtil screenshotutil = new ScreenshotUtil();
    static String filePath = System.getProperty("user.dir")
            + "/src/test/resources/excel/Gps_Rules.xls";

  public static   ExcelUtil excelUtil = new ExcelUtil(filePath);
	/* public static ExcelUtil excelUtil = new ExcelUtil("Gps_Rules.xls"); */
    public static Logger logger = Logger.getLogger("devpinoyLogger");

    private static final String CONFIG_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/properties/Config.properties";

    /* ================= SETUP ================= */

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("") String browserFromXml, Method method) throws Exception {

        config.load(new FileInputStream(CONFIG_PATH));

        String browser = (browserFromXml != null && !browserFromXml.isEmpty())
                ? browserFromXml
                : config.getProperty("browser");

        WebDriver localDriver;

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            localDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("selenium.manager.disable", "true");
            System.setProperty(
                    "webdriver.edge.driver",
                    "C:\\Users\\RAGHAVENDRA\\Downloads\\edgedriver_win64\\msedgedriver.exe"
            );
            localDriver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            localDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            localDriver = new InternetExplorerDriver();
        } else {
            throw new RuntimeException("Unsupported browser");
        }

        setDriver(localDriver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(config.getProperty("testsiteurl"));

        String path =
                System.getProperty("user.dir") + "/reports/AutomationReport_"
                        + browser + "_"
                        + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
                        + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Automation Report - " + browser.toUpperCase());
        spark.config().setDocumentTitle("Execution Report");

        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(spark);

        Capabilities caps =
                ((RemoteWebDriver) getDriver()).getCapabilities();

        extentReport.setSystemInfo("Browser", caps.getBrowserName());
        extentReport.setSystemInfo("Browser Version", caps.getBrowserVersion());
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));

        extent.set(extentReport);
        reportPath.set(path);

        test.set(
                extentReport.createTest(
                        method.getName() + " | " + browser.toUpperCase()
                )
        );
    }

    /* ================= TEARDOWN ================= */

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
        } finally {
            removeDriver();
            try {
                if (extent.get() != null) {
                    extent.get().flush();
                    Desktop.getDesktop()
                            .browse(new File(reportPath.get()).toURI());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* ================= AI FAILURE LOGGER (ONLY CHANGE) ================= */

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

            test.get().log(
                    Status.FAIL,
                    aiSuggestion + "<br>"
                            + "<a href='" + screenshotPath + "' target='_blank'>"
                            + "Click here for screenshot"
                            + "</a>"
            );
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
            ((JavascriptExecutor) getDriver())
                    .executeScript("arguments[0].style.border='3px solid blue'", element);
        } catch (Exception e) {
            test.get().log(
                    Status.WARNING,
                    "🧠 AI Insight: " + AITestAnalyzer.analyze(e)
            );
        }
    }

    @Override
    public void waitforelement(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (Exception ignored) {}
    }

    @Override
    public int randomNumberGeneration(int value) {
        return new Random().nextInt(value);
    }
}