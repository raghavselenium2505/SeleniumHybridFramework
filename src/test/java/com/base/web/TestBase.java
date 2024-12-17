package com.base.web;

import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Admin
 *
 */
interface baseMethods {
	public void click(By element, WebElement element_webelement, String value, String failurevalue);

	public void actionclick(WebElement element, String value, String failurevalue);

	public void clear(By element, String passvalue, String failValue);

	public void sendkeys(By element, String value, String passvalue, String failurevalue);

	public void sendReport(String to, final String user, final String password, String host, String port,
			String protocols, String fileLink);

	public void emailOption();

	public void elementhighlight(WebElement element);

	public void jsclick();

	public void waitforelement(int value);

	public String getScreenshot();

	public void selectDropdownValue(By elementPath, String dropdownValue, String passvalue, String failurevalue);

	public void chooseFile(By fileWebElement, WebElement Element, String fileLocation, String passvalue,
			String failurevalue) throws Exception;

	public void alert(By alertElement);

	public int randomNumberGeneration();

	public void calendarSelection(By month_year, By month, By day, String monthvalue, String dayvalue, String passvalue,
			String failurevalue);

	public void selectTime(By inputhours, By inputminutes, By timeconvention, String hours, String minutes,
			String TimeConvention, String passvalue, String failurevalue) throws Exception;

}

public class TestBase implements baseMethods {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties EMAIL = new Properties();
	public static Properties Report = new Properties();
	public static Properties Excel = new Properties();
	public static Properties JiraProp = new Properties();
	public static Properties ExtReport = new Properties();
	public static JSONParser parser = new JSONParser();

	public static Object obj;
	public static JSONObject jsonObject = (JSONObject) obj;

	public static FileInputStream fis;
	public static String browserlaunch;
	public static String browser;
	public static ExtentTest test;
	public static String value_extentreport = "ExtentReport";
	public static ExtentReports report;
	// public static String screenshotPath;
	public static String screenshotName = "ErrorFile";
	public static int passcount = 0;
	public static int executioncount = 0;
	public static int failcount = 0;
	public static int skipCount = 0;
	public static String Name;
	public static int i;

	public int monthflag, dayflag;

	public static int shortwaitvalue;
	public static int mediumwaitvalue;
	public static int longwaitvalue;
	public static int verylongwaitvalue;
	public static int extraverylongwaitvalue;
	public static String pathImage;
	public static String fileName_path;
	public int randomValue;

	public int currentyear = Year.now().getValue();

	// public String xlsname = "TestDataConfiguration.xls";
	public String xlsname = "ETekiTestDataConfiguration.xls";
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
		pathImage = System.getProperty("user.dir") + "\\src\\test\\resources\\Reports\\Extentreport\\"
				+ value_extentreport + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())
				+ ".html";
		report = new ExtentReports(pathImage, true);

		report.addSystemInfo("Host Name", ExtReport.getProperty("HostName"))
				.addSystemInfo("Environment", ExtReport.getProperty("Env"))
				.addSystemInfo("User Name", ExtReport.getProperty("User"))
				.addSystemInfo("email triggered", ExtReport.getProperty("emailTriggered"));
		report.loadConfig(new File(
				"C:\\Users\\RaghavendraD\\git\\SeleniumHybridFramework\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));

	}

	@AfterTest
	public void flushTest() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Logger logger = Logger.getLogger("devpinoyLogger");

	@BeforeTest
	public void setUp() {
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

			try {

				fis = new FileInputStream(

						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Excel.properties");

			} catch (FileNotFoundException e) {

				e.printStackTrace();

			}

			try {

				Excel.load(fis);

				logger.debug("Excel file loaded !!!");

			} catch (IOException e) {

				e.printStackTrace();

			}

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

					/*
					 * ChromeOptions chromeOptions = new ChromeOptions();
					 * 
					 * WebDriverManager.chromedriver().clearDriverCache().setup();
					 * WebDriverManager.chromedriver().setup();
					 * 
					 * WebDriverManager.chromedriver().clearResolutionCache().setup();
					 * 
					 * driver = new ChromeDriver(chromeOptions);
					 */
					driver = new ChromeDriver();

					logger.info("browser launched" + config.getProperty("browser"));

				} else if (config.getProperty("browser").equals("ie")) {

					System.setProperty("webdriver.ie.driver",

							System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");

					WebDriverManager.iedriver().setup();

					driver = new InternetExplorerDriver();

					logger.info("browser launched" + config.getProperty("browser"));

					logger.warn("Using" + config.getProperty("browser") + "cannot close the browser");

				} else if (config.getProperty("browser").equals("edge")) {
					i++;

					logger.info("incremental count" + i++);

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

				logger.info("browser maximaized ");

			}
		}

	}

	public static String getData(String SheetName, String ColName, String excelName) throws Exception {
		String returnValue = "";
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\" + excelName);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sh = wb.getSheet(SheetName);
			int rowCount = sh.getLastRowNum();
			for (int i = 0; i <= rowCount; i++) {
				String val = sh.getRow(i).getCell(0).getStringCellValue();
				if (val.equalsIgnoreCase(ColName)) {
					returnValue = sh.getRow(i).getCell(1).getStringCellValue();
					System.out.println(returnValue);
					break;

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return returnValue;
	}

	public void getDatajson(String jsonValue) {
		// String returnJson = "";
		Object obj;
		try {
			obj = parser.parse(new FileReader("C:\\Users\\RaghavendraD\\Desktop\\sample.json"));

			JSONObject jsonObject = (JSONObject) obj;
			String jsonval = (String) jsonObject.get(jsonValue);
			System.out.println(jsonval);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void isdisplay(By element1, String passValue, String failureValue) {
		if (driver.findElement(element1).isDisplayed()) {
			elementhighlight(driver.findElement(element1));

			Assert.assertTrue(driver.findElement(element1).isDisplayed(), "Able to enter the login page");

			test.log(LogStatus.PASS, passValue);

		} else {
			test.log(LogStatus.FAIL, failureValue);

		}
	}

	@AfterSuite
	public void tearDown() throws Exception {
		report.endTest(test);
		report.flush();
		Desktop.getDesktop().browse(new File(pathImage).toURI());
//		Desktop.getDesktop().browse(new File(fileName_path).toURI());

		emailOption();
		driver.quit();

	}

	@Override
	public void click(By element, WebElement element_webelement, String value, String failurevalue) {

		try {
			elementhighlight(driver.findElement(element));
			driver.findElement(element).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofMillis(verylongwaitvalue));

			Assert.assertTrue(element_webelement.isDisplayed(), "Result element is not visible after form submission");
			test.log(LogStatus.PASS, value);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failurevalue);

			captureScreenshot(value_extentreport);
		}
	}

	public void click(By element) {

		try {
			elementhighlight(driver.findElement(element));
			driver.findElement(element).click();

		} catch (Exception e) {
			e.printStackTrace();
			// logger.log(Status.FAIL, captureScreenshot("file.jpg"));

		}
	}

	/*
	 * @AfterMethod public void Method(ITestResult result) throws IOException
	 * 
	 * {
	 * 
	 * if(result.getStatus()==ITestResult.FAILURE) { String
	 * temp=Utility.getScreenshot();
	 * 
	 * 
	 * logger.fatal(result.getThrowable().getMessage()); } }
	 */ @Override
	public void actionclick(WebElement element, String value, String failurevalue) {
		try {
			elementhighlight(element);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			test.log(LogStatus.PASS, value);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failurevalue + e);

		}
	}

	@Override
	public void clear(By element, String passvalue, String failValue) {
		try {
			elementhighlight(driver.findElement(element));

			driver.findElement(element).clear();
			test.log(LogStatus.PASS, passvalue);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failValue);
			e.printStackTrace();
		}
	}

	@Override
	public void sendkeys(By element, String value, String passvalue, String failurevalue) {
		try {
			elementhighlight(driver.findElement(element));
			driver.findElement(element).sendKeys(value);
			logger.info("passed click statement");
			test.log(LogStatus.PASS, passvalue);
			Assert.assertTrue(driver.findElement(element).getAttribute("value").contains(value),
					"Able to enter the login page");
		} catch (Exception e) {
			logger.info("issue with some error");
			// e.printStackTrace();
			test.log(LogStatus.FAIL, failurevalue);

			captureScreenshot("test.png");
			Assert.assertTrue(driver.findElement(element).getAttribute("value").contains(value),
					"Able to enter the login page");

		}
	}

	@Override
	public void sendReport(String to, final String user, final String password, String host, String port,
			String protocols, String fileLink) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);// change accordingly
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", port); // default port 25
		properties.put("mail.smtp.starttls.enable", true);
		properties.setProperty("mail.smtp.ssl.protocols", protocols);
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			/* new InternetAddress(to)); */
			// message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
			message.setSubject("Logger Report And Automation Report" + EMAIL.getProperty("EmailTextName")
					+ new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime())

			);

			// 3) create MimeBodyPart object and set your message content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			EMAIL.getProperty("PicText");
			// messageBodyPart1.setText(EMAIL.getProperty("PicText"));
			messageBodyPart1.setText("execution report for the testcase report follows" + "\n" + "passcount:"
					+ passcount + "\n" + "failCount:" + failcount + "\n" + "skipCount :" + skipCount + "\n"
					+ "executionCount:" + executioncount + "\n");
			// 4) create new MimeBodyPart object and set DataHa00ler object to this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			File dir = new File(fileLink);
			File[] files = dir.listFiles();
			File lastModifiedFile = files[0];
			for (int i = 1; i < files.length; i++) {
				if (lastModifiedFile.lastModified() < files[i].lastModified()) {
					lastModifiedFile = files[i];
				}
			}
			String[] filName = lastModifiedFile.getName().split("\"");
			String filename = filName[filName.length - 1];// change accordingly
			DataSource source = new FileDataSource(lastModifiedFile.getAbsolutePath());
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			System.out.println(filename);

			// 5) create Multipart object and add MimeBodyPart objects to this object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message
			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void emailOption() {

		if (EMAIL.getProperty("EMAILSendTEXT").equalsIgnoreCase("Y")) {

			if (EMAIL.getProperty("logFileStatus").equals("Y")) {
				sendReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"), EMAIL.getProperty("passWord"),
						EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"), EMAIL.getProperty("protocols"),
						EMAIL.getProperty("logFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for logFileStatus");
			} else if (EMAIL.getProperty("htmlFileStatus").equals("Y")) {
				sendReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"), EMAIL.getProperty("passWord"),
						EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"), EMAIL.getProperty("protocols"),
						EMAIL.getProperty("htmlFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for htmlFileStatus");
			} else if (EMAIL.getProperty("textFileStatus").equals("Y")) {
				sendReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"), EMAIL.getProperty("passWord"),
						EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"), EMAIL.getProperty("protocols"),
						EMAIL.getProperty("textFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for textFileStatus");

			} else if (EMAIL.getProperty("excelFileStatus").equals("Y")) {
				sendReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"), EMAIL.getProperty("passWord"),
						EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"), EMAIL.getProperty("protocols"),
						EMAIL.getProperty("excelFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for excelFileStatus");

			} else if (EMAIL.getProperty("extentReportStatus").equals("Y")) {
				sendReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"), EMAIL.getProperty("passWord"),
						EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"), EMAIL.getProperty("protocols"),
						EMAIL.getProperty("extentReport"));
				test.log(LogStatus.PASS, "EMAIL trigered for extentReportStatus");

			}
		}

	}

	@Override
	public void elementhighlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: black; border: 3px solid blue;");
	}

	@Override
	public void jsclick() {
		// TODO Auto-generated method stub

	}

	public void waitforelement(int value) {
		try {
			Thread.sleep(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getScreenshot() {

		String s = "<html>\r\n" + "<head>\r\n" + "   <title>HTML Image as link</title>\r\n" + "</head>\r\n"
				+ "<body>\r\n" + "<p><a href=\"html_images.asp\">HTML Images</a></p>\r\n" + "\r\n" + "</body>\r\n"
				+ "</html>";
		System.out.println(s);
		return s;
	}

	@Override
	public void selectDropdownValue(By elementPath, String dropdownValue, String passvalue, String failurevalue) {
		WebElement AreasOfExpertise = driver.findElement(elementPath);
		Select areasOfExpertiseValue = new Select(AreasOfExpertise);
		areasOfExpertiseValue.selectByVisibleText(dropdownValue);
		waitforelement(mediumwaitvalue);

		Assert.assertEquals(dropdownValue, dropdownValue, "The selected option is incorrect!");

	}

	@Override
	public void chooseFile(By fileWebElement, WebElement Element, String fileLocation, String passvalue,
			String failurevalue) throws Exception {
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
			Assert.assertTrue(Element.isDisplayed(), "File upload failed!");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(Element.isDisplayed(), "File upload failed!");

		}

	}

	@Override
	public int randomNumberGeneration() {
		Random random = new Random();
		return randomValue = random.nextInt(10000);
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
			String TimeConvention, String passvalue, String failurevalue) throws Exception {

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

	}

	@Override
	public void calendarSelection(By month_year, By month, By day, String monthvalue, String dayvalue, String passvalue,
			String failurevalue) {

		driver.findElement(month_year).click();

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

	public static String captureScreenshot_base64() {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;

		String baseCode = takescreenshot.getScreenshotAs(OutputType.BASE64);

		return baseCode;
	}

	public static File captureScreenshot(String fileName_screenshot) {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;

		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		fileName_path = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\" + fileName_screenshot
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".jpg";
		File destFile = new File(fileName_path);

		try {
			FileUtils.copyFile(sourceFile, destFile);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return destFile;
	}


	public void SubmitClick(By element, String value, String failurevalue) {

		try {
			elementhighlight(driver.findElement(element));
			driver.findElement(element).click();
			WebElement element_webelement = driver.findElement(element);
			Assert.assertTrue(element_webelement.isDisplayed(), "Result element is not visible after form submission");
			test.log(LogStatus.PASS, value);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failurevalue + e);
			captureScreenshot(value_extentreport);
		}
	}

}
