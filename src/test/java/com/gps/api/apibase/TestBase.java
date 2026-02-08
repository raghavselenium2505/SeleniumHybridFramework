package com.gps.api.apibase;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.gps.utilities.ExcelUtil;
import com.gps.utilities.OpenCVScreenRecorder;
import com.gps.utilities.ScreenshotUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

interface apiBase
{
	
}






public class TestBase implements apiBase {

	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties EMAIL = new Properties();
	public static Properties Report = new Properties();
	public static Properties ExtReport = new Properties();
	public static ScreenshotUtil screenshotutil = new ScreenshotUtil();
	public static ExcelUtil excelutil = new ExcelUtil();
	

	public static Object obj;

	public static FileInputStream fis;
	public static String browserlaunch;
	public static String browser;
	public static ExtentTest test;
	public static String value = "ExtentReport";
	public static String value_video = "VideoRecorder";
	public static ExtentReports report;
	// public static String screenshotPath;
	public static String screenshotName = "ErrorFile";
	public static int passcount = 0;
	public static int executioncount = 0;
	public static int failcount = 0;
	public static int skipCount = 0;
	public static String Name;
	public static int i;
	// fixed the issue

	public int monthflag, dayflag;

	public static int shortwaitvalue;
	public static int mediumwaitvalue;
	public static int longwaitvalue;
	public static int verylongwaitvalue;
	public static int extraverylongwaitvalue;
	public static String pathImage;
	public static String recorder_path;
	public int randomValue;
	public static String tagName;
	public static String pathPdf;

	OpenCVScreenRecorder recorder;

	
	
	
	public ArrayList<String> elementsArrayList = new ArrayList<String>();
	public int currentyear = Year.now().getValue();

	// public String xlsname = "TestDataConfiguration.xls";
public String xlsname = "Gps_Rules.xls";
//public String xlsname = "GpsTestData_For Demo_Updated.xls";
	public LocalTime currentTime = LocalTime.now();
	

	// Define allowed execution window (9 AM - 9 PM)
	public LocalTime startTime = LocalTime.of(9, 0);
	public LocalTime endTime = LocalTime.of(23, 0);
public     DayOfWeek today = LocalDate.now().getDayOfWeek();



	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hhmmss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

	@BeforeSuite
	public void ReportGeneration() throws Exception {
		System.out.println("in suite before");
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\extReport.properties");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ExtReport.load(fis);
			logger.debug("extent report properties file  loaded !!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ExtReport.getProperty("jenkins").equals("Y")) {

			pathImage = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\Extentreport\\" + value
					+ ".html";
			report = new ExtentReports(pathImage, true);

			report.addSystemInfo("Host Name", ExtReport.getProperty("HostName"))
					.addSystemInfo("Environment", ExtReport.getProperty("Env"))
					.addSystemInfo("User Name", ExtReport.getProperty("User"))
					.addSystemInfo("email triggered", ExtReport.getProperty("emailTriggered"));
			report.loadConfig(new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));

		} else if (ExtReport.getProperty("jenkins").equals("N")) {
			pathImage = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\Extentreport\\" + value
					+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".html";
			report = new ExtentReports(pathImage, true);

			report.addSystemInfo("Host Name", ExtReport.getProperty("HostName"))
					.addSystemInfo("Environment", ExtReport.getProperty("Env"))
					.addSystemInfo("User Name", ExtReport.getProperty("User"))
					.addSystemInfo("email triggered", ExtReport.getProperty("emailTriggered"));
			report.loadConfig(new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		}
	}

	public static Logger logger = Logger.getLogger("devpinoyLogger");
	@AfterSuite
	public void tearDown() throws Exception {

		report.endTest(test);
		report.flush();
		//Desktop.getDesktop().browse(new File(pathImage).toURI());
//fileopen();
	//	stopRecording();
		// emailOption();
	//	driver.quit();

	}
	public void stopRecording() {
		if (config.getProperty("Recording").contains("Y")) {
			try {
				recorder.stopRecording();

				Desktop.getDesktop().open(new File(recorder_path));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public void fileopen()
	{
		if(config.getProperty("isJenkins").contains("N"))
		{
			try {
				Desktop.getDesktop().browse(new File(pathImage).toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	 public static Response login(String username, String password,String baseurl,String endpoint) {
	        Map<String, String> credentials = new HashMap<>();
	        credentials.put("username", username);
	        credentials.put("password", password);

	        return RestAssured
	            .given()
	                .baseUri(baseurl) // Replace with actual base URI
	                .contentType(ContentType.JSON)
	                .body(credentials)
	            .when()
	                .post(endpoint); // Replace with actual login endpoint
	    }
	
	
	
	
}
