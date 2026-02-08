package com.gps.base;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.reporter.converters.ExtentHtmlReporterConverter;
import com.gps.pages.GPS_OrgDashboardPage;
import com.gps.pages.GPS_SettingsPage;
import com.gps.pages.GPS_SettingsPipelinesPage;
import com.gps.utilities.ExcelUtil;
import com.gps.utilities.OpenCVScreenRecorder;
import com.gps.utilities.ScreenshotUtil;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.LocalDate;

/**
 * @author Admin
 *
 */
interface baseMethods {

	public void click(By element, String passvalue, String failValue);

	public void actionclick(WebElement element, String passValue, String failValue);

	public void clear(By element, String passvalue, String failValue);

	public void sendkeys(By element, String value, String passvalue, String failValue);

	public void elementhighlight(WebElement element);

	public void waitforelement(int milliSeconds);

	public void selectDropdownValue(By elementPath, String dropdownValue, String passvalue, String failValue);

	public void chooseFile(By fileWebElement, WebElement Element, String fileLocation, String passvalue,
			String failValue);

	public void alert(By alertElement);

	public int randomNumberGeneration(int value);

	public void calendarSelection(By month_year, By month, By day, String monthvalue, String dayvalue, String passvalue,
			String failValue);

	public void selectTime(By inputhours, By inputminutes, By timeconvention, String hours, String minutes,
			String TimeConvention, String passvalue, String failValue);

	public void isdisplay(By element, String passValue, String failValue);

	public void implicitwaitforelement(int milliSeconds);

	public void waitforvisibilityOfElement(By element, int milliSeconds);

	public WebElement waitForWebElement(By byRef, int timeInSec);

	public String getText(By webElement);
	
	
	public  void timetakenStep(String stepName, Runnable action);

}

public class TestBase implements baseMethods {
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

	public  int shortwaitvalue;
	public  int mediumwaitvalue;
	public  int longwaitvalue;
	public  int verylongwaitvalue;
	public  int extraverylongwaitvalue;
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

	@BeforeTest
	public void setUp() throws Exception {
		
		if (driver == null) {

			try {

				fis = new FileInputStream(

						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

			} catch (FileNotFoundException e) {

				logger.error("Exception thrown" + e);

				e.printStackTrace();

			}

			try {

				config.load(fis);

				logger.debug("Config properties file loaded");

			} catch (IOException e) {

				e.printStackTrace();

				logger.error("Exception thrown" + e);

			}

			try {

				fis = new FileInputStream(

						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\EMAIL.properties");

			} catch (FileNotFoundException e) {

				e.printStackTrace();

			}

			try {

				EMAIL.load(fis);

				logger.debug("Email file loaded !!!");

			} catch (IOException e) {

				e.printStackTrace();

			}

			/*
			 * try {
			 * 
			 * fis = new FileInputStream(
			 * 
			 * System.getProperty("user.dir") +
			 * "\\src\\test\\resources\\properties\\Excel.properties");
			 * 
			 * } catch (FileNotFoundException e) {
			 * 
			 * e.printStackTrace();
			 * 
			 * }
			 */
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");

			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser1", browser);

			if (!config.getProperty("browser").equals("")) {

				if (config.getProperty("browser").equals("firefox")) {

					// driver = new FirefoxDriver();

					WebDriverManager.firefoxdriver().setup();

					driver = new FirefoxDriver();

				} else if (config.getProperty("browser").equals("chrome")) {

					logger.info("chrome  browser launched");

					ChromeOptions options = new ChromeOptions();

					WebDriverManager.chromedriver().setup();
					
					  options.addArguments("--window-size=1920,1080");
					  
					  options.addArguments("--disable-extensions");
					  
					  options.addArguments("--proxy-server='direct://'");
					  
					  options.addArguments("--proxy-bypass-list=*");
					  
					  options.addArguments("--disable-gpu");
					  
					  options.addArguments("--proxy-bypass-list=*");
					  options.addArguments("--guest");
					  
					  driver = new ChromeDriver(options);

				} else if (config.getProperty("browser").equals("ie")) {

					System.setProperty("webdriver.ie.driver",

							System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");

					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
					logger.info("browser launched" + config.getProperty("browser"));

					logger.warn("Using" + config.getProperty("browser") + "cannot close the browser");

				} else if (config.getProperty("browser").equals("edge")) {

					EdgeOptions edgeoption = new EdgeOptions();

					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver(edgeoption);
					edgeoption.addArguments("--disable-web-security");
					edgeoption.addArguments("--no-sandbox");
					edgeoption.addArguments("--disable-dev-shm-usage");

					logger.info("browser launched" + config.getProperty("browser"));

				} else if (config.getProperty("browser").equals("chromeheadless")) {

					logger.info("chrome headless browser launched");

					ChromeOptions options = new ChromeOptions();

					WebDriverManager.chromedriver().setup();

					options.addArguments("--headless");

					options.addArguments("--window-size=1920,1080");

					options.addArguments("--disable-extensions");

					options.addArguments("--proxy-server='direct://'");

					options.addArguments("--proxy-bypass-list=*");

					options.addArguments("--disable-gpu");

					options.addArguments("--proxy-bypass-list=*");

					options.addArguments("--proxy-bypass-list=*");
					driver = new ChromeDriver(options);

				} else if (config.getProperty("browser").equals("incognito")) {

					logger.info("Incognito browser launched");
					ChromeOptions options = new ChromeOptions();

					WebDriverManager.chromedriver().setup();

					options.addArguments("incognito");

					driver = new ChromeDriver(options);

				}

				else if (config.getProperty("browser").equals("edgeheadless")) {

					EdgeOptions edgeoption = new EdgeOptions();

					WebDriverManager.edgedriver().setup();

					edgeoption.addArguments("--headless");

					edgeoption.addArguments("--window-size=1920,1080");

					edgeoption.addArguments("--disable-extensions");

					edgeoption.addArguments("--proxy-server='direct://'");

					edgeoption.addArguments("--proxy-bypass-list=*");

					edgeoption.addArguments("--disable-gpu");

					edgeoption.addArguments("--proxy-bypass-list=*");

					edgeoption.addArguments("--proxy-bypass-list=*");

					driver = new EdgeDriver(edgeoption);

				} else {

					System.out.println("cannot move forward as browser not launched");

				}

				driver.get(config.getProperty("testsiteurl"));

				logger.info("browser launched " + config.getProperty("browser") + "Navigated to : "

						+ config.getProperty("testsiteurl"));

				driver.manage().window().maximize();
				// driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(mediumwaitvalue));

				logger.info("browser maximaized ");
				// xlsname= config.getProperty("xlsName");

				if (config.getProperty("Recording").contains("Y")) {
					recorder_path = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\VideoRecorder\\"
							+ value_video
							+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".mp4";
					recorder = new OpenCVScreenRecorder();
					try {
						recorder.startRecording(recorder_path);

					} catch (org.bytedeco.javacv.FrameGrabber.Exception
							| org.bytedeco.javacv.FrameRecorder.Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

	@AfterTest
	public void flushTest() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() throws Exception {

		report.endTest(test);
		report.flush();
		//Desktop.getDesktop().browse(new File(pathImage).toURI());
fileopen();
		stopRecording();
		// emailOption();
		driver.quit();

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

	@Override
	public void click(By element, String passValue, String failValue) {

		try {
			elementhighlight(WaitUtils.waitClickByRef(element));
			WaitUtils.waitClickByRef(element).click();
			test.log(LogStatus.PASS, passValue);

		} catch (Exception e) {
		
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
			e.printStackTrace();
			
			// screenshotutil.captureScreenshot(value);
		}
	}
	public void click(By element) {

		try {
			elementhighlight(driver.findElement(element));
			driver.findElement(element).click();

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
			// screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void actionclick(WebElement element, String passValue, String failValue) {
		try {
			elementhighlight(element);
			waitforelement(shortwaitvalue);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			test.log(LogStatus.PASS, passValue);
		} catch (Exception e) {
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><b><p><a href=" + screenshotutil.captureScreenshot(value) + ">"
					+ failValue + "</p></body></html>");

			// test.log(LogStatus.FAIL, failValue);
			e.printStackTrace();
			// screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void clear(By element, String passvalue, String failValue) {
		try {
			elementhighlight(driver.findElement(element));
	WaitUtils.waitClickByRef(element,20).clear();
	//		driver.findElement(element).clear();
			test.log(LogStatus.PASS, passvalue);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
			e.printStackTrace();
//			screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void sendkeys(By element, String inputValue, String passvalue, String failValue) {
		try {
			elementhighlight(WaitUtils.waitClickByRef(element));
		
			WaitUtils.waitClickByRef(element,50).sendKeys(inputValue);;

//			driver.findElement(element).sendKeys(inputValue);
			logger.info("passed click statement");
			test.log(LogStatus.PASS, passvalue);
		} catch (Exception e) {
			logger.info("issue with some error");
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
//			screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void elementhighlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: black; border: 3px solid blue;");
	}

	@Override
	public void waitforelement(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectDropdownValue(By elementPath, String dropdownValue, String passvalue, String failValue) {

		try {
			WebElement dropdownSelect = driver.findElement(elementPath);
			Select areasOfExpertiseValue = new Select(dropdownSelect);
			areasOfExpertiseValue.selectByVisibleText(dropdownValue);
			waitforelement(mediumwaitvalue);
			test.log(LogStatus.PASS, passvalue);

		} catch (Exception e) {
			// test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			e.printStackTrace();
			// screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void chooseFile(By fileWebElement, WebElement Element, String fileLocation, String passvalue,
			String failValue) {
		try {
			Actions action = new Actions(driver);
			action.click(driver.findElement(fileWebElement)).build().perform();
			waitforelement(mediumwaitvalue);

			Robot robot = new Robot();
			StringSelection select = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			waitforelement(mediumwaitvalue);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			waitforelement(mediumwaitvalue);
			test.log(LogStatus.PASS, passvalue);

		} catch (Exception e) {
//			test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			e.printStackTrace();
			// screenshotutil.captureScreenshot(value);
		}

	}

	@Override
	public int randomNumberGeneration(int value) {
		Random random = new Random();
		return randomValue = random.nextInt(value);
	}

	@Override
	public void alert(By alertElement) {
		if (!driver.findElement(alertElement).isDisplayed()) {
			Alert a = driver.switchTo().alert();
			a.dismiss();
		}
	}

	@Override
	public void selectTime(By inputhours, By inputminutes, By timeconvention, String hours, String minutes,
			String TimeConvention, String passvalue, String failValue) {

		try {
			Robot robot = new Robot();
			driver.findElement(inputhours).click();
			waitforelement(shortwaitvalue);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			waitforelement(shortwaitvalue);
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			waitforelement(mediumwaitvalue);
			driver.findElement(inputhours).sendKeys(hours);

			waitforelement(mediumwaitvalue);
			driver.findElement(inputminutes).click();
			waitforelement(shortwaitvalue);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			waitforelement(shortwaitvalue);
			robot.keyPress(KeyEvent.VK_BACK_SPACE);
			robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			waitforelement(mediumwaitvalue);
			driver.findElement(inputminutes).click();
			waitforelement(shortwaitvalue);
			driver.findElement(inputminutes).sendKeys(minutes);
			waitforelement(mediumwaitvalue);
			if (driver.findElement(timeconvention).getText().equals(TimeConvention)) {
				waitforelement(shortwaitvalue);
				driver.findElement(timeconvention).click();
			}
		} catch (Exception e) {
//			test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			e.printStackTrace();
			screenshotutil.captureScreenshot(value);
		}

	}

	@Override
	public void calendarSelection(By month_year, By month, By day, String monthvalue, String dayvalue, String passvalue,
			String failValue) {

		click(month_year);

		waitforelement(shortwaitvalue);

		WebElement monthstable = driver.findElement(month);

		waitforelement(shortwaitvalue);

		// For selecting months row

		List<WebElement> monthsrow = monthstable.findElements(By.tagName("tr"));

		waitforelement(mediumwaitvalue);

		System.out.println("months row count: " + monthsrow.size());

		for (int mrow = 0; mrow < monthsrow.size(); mrow++) {

			if (monthflag == 0) {

				// For selecting months column

				List<WebElement> monthscolumn = monthsrow.get(mrow).findElements(By.tagName("td"));

				waitforelement(shortwaitvalue);

				System.out.println("months column count: " + monthscolumn.size());

				// Select corresponding month in Year

				for (int mcolumn = 0; mcolumn < monthscolumn.size(); mcolumn++) {

					if (monthscolumn.get(mcolumn).getText().equals(monthvalue)) {

						monthscolumn.get(mcolumn).click();

						System.out.println("month selected");

						monthflag = 1;

						break;
					}
				}
			}
		}
		WebElement daystable = driver.findElement(By.xpath("//table[@class='uib-daypicker']/tbody"));

		waitforelement(shortwaitvalue);

		// For selecting days row

		List<WebElement> daysrow = daystable.findElements(By.tagName("tr"));

		waitforelement(shortwaitvalue);

		System.out.println("days row count: " + daysrow.size());

		for (int drow = 0; drow < daysrow.size(); drow++) {

			if (dayflag == 0) {

				// For selecting days column

				List<WebElement> dayscolumn = daysrow.get(drow).findElements(By.tagName("td"));

				waitforelement(mediumwaitvalue);

				System.out.println("days column count: " + dayscolumn.size());

				// Select corresponding date in the month

				for (int dcolumn = 0; dcolumn < dayscolumn.size(); dcolumn++) {

					if (dayscolumn.get(dcolumn).getText().equals(dayvalue)) {

						dayscolumn.get(dcolumn).click();

						System.out.println("day selected");

						dayflag = 1;

						break;
					}
				}
			}
		}
	}

	@Override
	public void isdisplay(By element, String passValue, String failValue) {
		if (WaitUtils.waitClickByRef(element).isDisplayed()) {
			elementhighlight(driver.findElement(element));
			test.log(LogStatus.PASS, passValue);

		} else {
//			test.log(LogStatus.FAIL, failValue);
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			// screenshotutil.captureScreenshot(value);
		}
	}

	@Override
	public void implicitwaitforelement(int milliSeconds) {

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(milliSeconds));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void waitforvisibilityOfElement(By element, int milliSeconds) {

		try {
			// Initialize WebDriverWait (Explicit wait for given Milliseconds)
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(milliSeconds));

			// Explicit Wait: Wait until the element is visible before clicking it
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public WebElement waitForWebElement(By byRef, int timeInSec) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));

		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(byRef));

		return ele;

	}

	@Override
	public String getText(By webElement) {
		String str = driver.findElement(webElement).getText();
		return str;
	}

	public boolean isExecutionAllowed() {

		if (config.getProperty("isTimestampEnabled").equalsIgnoreCase("Y")) {
			
			
			
			test.log(LogStatus.INFO,
					"<span style='color:darkorange; font-weight:bold;'>Timestamp validation enabled. Checking if the system time is within the allowed execution window.</span>");

			if (currentTime.isBefore(startTime) || currentTime.isAfter(endTime)&& today != DayOfWeek.SATURDAY && today != DayOfWeek.SUNDAY) {
				test.log(LogStatus.SKIP,
						"<span style='color:red; font-weight:bold;'>Test execution blocked: Allowed time is between 10 AM - 11 PM and also on Saturday and Sunday, Current system time: "
								+ currentTime +"Day:   "+today+ "</span>");
				return false; // Block execution if outside allowed time
			} else {
				test.log(LogStatus.INFO,
						"<span style='color:darkgreen; font-weight:bold;'>Execution allowed: Current system time ("
								+ currentTime + ") is within the allowed window (10 AM - 11 PM).</span>");
			}
		} else {
			test.log(LogStatus.INFO,
					"<span style='color:purple; font-weight:bold;'>Timestamp validation is disabled. The test will proceed without time restrictions.</span>");
		}
		return true; // Execution is allowed }
	}
	
	public void isdisplay1(WebElement webElement, String passValue, String failValue) {

		if (webElement.isDisplayed()) {
			elementhighlight(webElement);
			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

			screenshotutil.captureScreenshot(value);
		}
	}

	public void clickAssert(By element, String passValue, String failValue, By postClickExpectedElement) {
		try {
			WebElement target = driver.findElement(element);
			elementhighlight(target);
			target.click();
			waitforelement(shortwaitvalue);

			// Assertion: Verify if the expected element appears after clicking
			boolean isDisplayed = driver.findElements(postClickExpectedElement).size() > 0;
			if (isDisplayed) {
				test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
						+ ">click here for screenshot</p></body></html>");
				Assert.fail(failValue);
			}

			test.log(LogStatus.PASS, passValue);

		} catch (Exception e) {

			test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");
			e.printStackTrace();
		}
	}
	
	
	
	
	//Fetches records in page and adds to ArrayList,Ex: By ref is assigned user names
		public ArrayList<String> toGetElementListFromEachPage(By nameInPage) {
			// Fetch elements
			List<WebElement> elements = WaitUtils.waitVisibilityOfDropdownElements(nameInPage, 50);
			ArrayList<String> pageEleArray=new ArrayList<String>();

			// iterating each element and adding to arraylist
			for (WebElement ele : elements) {
				pageEleArray.add(ele.getText());
				
			}
			logger.info("size is " +pageEleArray.size());
			logger.info(pageEleArray);
			
			elementsArrayList.addAll(pageEleArray);
			
			logger.info("size is " +elementsArrayList.size());
			logger.info(elementsArrayList);
	      return elementsArrayList;
			
		}
		
		
		//To get total records from each page to last page,By ref is assigned user names
		public ArrayList<String> toGetElementsTillLastPage(By nameInPage,By nextArrow) {
			elementsArrayList.clear();
			while (true) {
				
				toGetElementListFromEachPage(nameInPage);
				
				// loop breaks if class has disabled attribute
				if (WaitUtils.waitClickByRef(nextArrow).getAttribute("class").contains("disabled")) {
					
					break;
				}

				// if class does not has disabled attribute then it runs else block
				else {
					
					WaitUtils.waitClickByRef(nextArrow).click();
					WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

				}

			}

			 return elementsArrayList;
		}
		
		//To get assigned user from Settings > User management
		public ArrayList<String> toGetassignedUserFromUserManagement(By assignedUserNamesFromUserManage,By nextArrow)
		{
			
			GPS_SettingsPage settingspage = new GPS_SettingsPage();

			//click on user management
			WaitUtils.waitClickByRef(settingspage.tabUserManagement).click();
			test.log(LogStatus.PASS, "User is able to click on Usermanagment Tab");
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
			
			//To get assigned user from user management
			ArrayList<String> assignedUserFromUserManagementArray=toGetElementsTillLastPage(assignedUserNamesFromUserManage,nextArrow);

		return assignedUserFromUserManagementArray;
		}
		
		
		//To get pipelines from settings > pipeline
		public ArrayList<String> toGetpipelinesFromSettingsPipelines(By pipelineName,By nextArrow)
		{
			
			//click on pipelines tab
			GPS_SettingsPipelinesPage settingsPipelinesPage = new GPS_SettingsPipelinesPage();
			settingsPipelinesPage.clickPipelinesTab();
			
			WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
			
			//Getting all elements from 1st page to last page in settings > Pipelines
			ArrayList<String> totalPipelinesFromSettingsArray=settingsPipelinesPage.toGetElementsTillLastPage(pipelineName,nextArrow);
			
			logger.info("Pipelines from settings> pipelines "+totalPipelinesFromSettingsArray);
			
			return totalPipelinesFromSettingsArray;
			
		}
		
	
public void mousepointer(WebElement element,String mousevalue,String passValue,String failValue)
{
	Actions action=new Actions(driver);
	action.moveToElement(element).perform();
	
	if(element.getCssValue("cursor").equalsIgnoreCase(mousevalue))
	{
		test.log(LogStatus.PASS, passValue);

	}else {

		test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
				+ ">click here for screenshot</p></body></html>");
	
	}
}

				@Override
				public void timetakenStep(String stepName, Runnable action)
				
					{
	 
		    long start = System.currentTimeMillis();
		    action.run();
		    long end = System.currentTimeMillis();
			test.log(LogStatus.INFO,stepName+(end-start)+ " ms" );

				    }
}
		
		

