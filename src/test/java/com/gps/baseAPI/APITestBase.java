package com.gps.baseAPI;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITestBase {

    protected RequestSpecification request;
    protected Response response;

    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static String reportPath;

    protected static Properties config = new Properties();
    private static final String CONFIG_PATH = System.getProperty("user.dir")
            + "/src/test/resources/properties/Config.properties";

    // ================= SETUP =================

    @BeforeSuite(alwaysRun = true)
    public void startReport() {

        try {
            config.load(new FileInputStream(CONFIG_PATH));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file");
        }

        reportPath = System.getProperty("user.dir") + "/reports/API_Report_"
                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeMethod(alwaysRun = true)
    public void createTest(Method method) {

        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);

        System.out.println("API MODE → No browser launch ✅");
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {

        try {
            extent.flush();
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= REQUEST =================

    public void initializeRequest() {

        String baseURL = config.getProperty("apiBaseUrl");

        if (baseURL == null || baseURL.isEmpty()) {
            throw new RuntimeException("apiBaseUrl is missing in Config.properties");
        }

        request = RestAssured.given()
                .baseUri(baseURL)
                .header("Content-Type", "application/json");

        logInfo("Base URL: " + baseURL);
    }

    // ================= STATIC REQUEST (🔥 FIX) =================

    protected static RequestSpecification getRequestStatic() {

        String baseURL = config.getProperty("apiBaseUrl");

        if (baseURL == null || baseURL.isEmpty()) {
            throw new RuntimeException("apiBaseUrl missing in config");
        }

        return RestAssured.given()
                .baseUri(baseURL)
                .header("Content-Type", "application/json");
    }

    // ================= LOGGER =================

    protected void logInfo(String message) {
        if (test.get() != null) test.get().info(message);
    }

    protected void logPass(String message) {
        if (test.get() != null) test.get().pass(message);
    }

    protected void logFail(String message) {
        if (test.get() != null) test.get().fail(message);
    }

    protected void logFail(String message, Exception e) {
        if (test.get() != null) test.get().fail(message + " | " + e.getMessage());
    }

    // ================= STATIC LOGGER (🔥 FIX) =================

    protected static void logInfoStatic(String message) {
        if (test.get() != null) test.get().info(message);
    }

    protected static void logPassStatic(String message) {
        if (test.get() != null) test.get().pass(message);
    }

    protected static void logFailStatic(String message, Exception e) {
        if (test.get() != null) test.get().fail(message + " | " + e.getMessage());
    }

    // ================= RESPONSE LOGGER =================

    protected void logResponse(String method, String endpoint) {

        logInfo(method + " Request → " + endpoint);
        logInfo("Status Code: " + response.getStatusCode());
        logInfo("Response Time: " + response.getTime() + " ms");
        logInfo("Response Body: " + response.asPrettyString());
    }
    protected String getDataMode(String modeFromXml) {

        if (modeFromXml != null && !modeFromXml.trim().isEmpty()) {
            logInfo("DataMode from XML: " + modeFromXml);
            return modeFromXml;
        }

        String mode = config.getProperty("dataMode");

        if (mode == null || mode.isEmpty()) {
            throw new RuntimeException("dataMode missing in Config.properties");
        }

        logInfo("DataMode from Config: " + mode);

        return mode;
    }  
}