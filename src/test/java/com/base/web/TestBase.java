package com.base.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testcase.Pages.NaukriLoginPage;


public class TestBase {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties EMAIL = new Properties();
	public static Properties Report = new Properties();
	public static Properties Excel = new Properties();
	public static Properties JiraProp = new Properties();
	


	public static FileInputStream fis;
	public static String browser;
	public static ExtentTest test;
	public static String value = "ExtentReport";
	public static ExtentReports report;
	// public static String screenshotPath;
	public static String screenshotName = "ErrorFile";
	public static int passcount = 0;
	public static int executioncount = 0;
	public static int failcount = 0;
	public static int skipCount = 0;
	public static String Name;
	
	public String xlsname = "ExcelSheet_1.xls";
	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hhmmss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

	@BeforeSuite
	public void ReportGeneration() throws Exception {
		
		  report = new ExtentReports(
		  "C:\\Automation_Sele\\Selenium\\src\\test\\resources\\Reports\\Extentreport\\"
		  + value + new
		  SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())
		  + ".html");
	//	report.loadConfig(new File(System.getProperty("user. dir") + "\\extent-config.xml"));

	}

	@AfterTest
	public void flushTest() {
		try {
			/*
			 * if(driver.findElement(home.buttonAccount).isDisplayed()) {
			 * Click(home.buttonAccount,"clicked on account button"
			 * ,"unable to click on account button"); sleepMethod(3000);
			 * actionCilck(driver.findElement(home.linkSignoff),"signoff sucesful"
			 * ,"Unable to do signoff"); }
			 */
			
		}catch(Exception e)
		{
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
				// debug("Config properties file loaded");
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
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\jiraProp.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				JiraProp.load(fis);
				logger.debug("Jira file  loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			if (config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				logger.info("browser launched" + config.getProperty("browser"));
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				logger.info("browser launched" + config.getProperty("browser"));
				logger.warn("Using" + config.getProperty("browser") + "cannot close the browser");
			} else if (config.getProperty("browser").equals("edge")) {

				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\msedgedriver.exe");
				driver = new EdgeDriver();
				logger.info("browser launched" + config.getProperty("browser"));
			} else if (config.getProperty("browser").equals("headless")) {
				driver = new HtmlUnitDriver();
				logger.info("browser launched" + config.getProperty("browser"));

			}
			driver.get(config.getProperty("testsiteurl"));
			logger.info("browser launched " + config.getProperty("browser") + "Navigated to : "
					+ config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			logger.info("browser maximaized ");
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);

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

	public void SendMailReport(String to, final String user, final String password, String host, String port,
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
			message.setSubject("Logger Report And Automation Report"+EMAIL.getProperty("EmailTextName")
					+ new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime())

			);

			// 3) create MimeBodyPart object and set your message content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			EMAIL.getProperty("PicText");
		//	messageBodyPart1.setText(EMAIL.getProperty("PicText"));
			messageBodyPart1.setText("execution report for the testcase report follows" + "\n" + "passcount:"
					+ passcount + "\n" + "failCount:" + failcount + "\n" + "skipCount :" + skipCount + "\n"
					+ "executionCount:"+ executioncount+"\n");
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

	public void EmailOption() {

		if (EMAIL.getProperty("EMAILSendTEXT").equalsIgnoreCase("Y")) {

			if (EMAIL.getProperty("logFileStatus").equals("Y")) {
				SendMailReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"),
						EMAIL.getProperty("passWord"), EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"),
						EMAIL.getProperty("protocols"), EMAIL.getProperty("logFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for logFileStatus");
			} else if (EMAIL.getProperty("htmlFileStatus").equals("Y")) {
				SendMailReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"),
						EMAIL.getProperty("passWord"), EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"),
						EMAIL.getProperty("protocols"), EMAIL.getProperty("htmlFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for htmlFileStatus");
			} else if (EMAIL.getProperty("textFileStatus").equals("Y")) {
				SendMailReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"),
						EMAIL.getProperty("passWord"), EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"),
						EMAIL.getProperty("protocols"), EMAIL.getProperty("textFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for textFileStatus");

			} else if (EMAIL.getProperty("excelFileStatus").equals("Y")) {
				SendMailReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"),
						EMAIL.getProperty("passWord"), EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"),
						EMAIL.getProperty("protocols"), EMAIL.getProperty("excelFile"));
				test.log(LogStatus.PASS, "EMAIL trigered for excelFileStatus");

			} else if (EMAIL.getProperty("extentReportStatus").equals("Y")) {
				SendMailReport(EMAIL.getProperty("toMail"), EMAIL.getProperty("fromMail"),
						EMAIL.getProperty("passWord"), EMAIL.getProperty("smtpServer"), EMAIL.getProperty("port"),
						EMAIL.getProperty("protocols"), EMAIL.getProperty("extentReport"));
				test.log(LogStatus.PASS, "EMAIL trigered for extentReportStatus");

			}
		}

	}

	public void elementHighlight(WebElement webElement) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement,
				"color: Yellow; border: 3px solid red;");

	}

	public void Click(By element,String value,String failurevalue) {

		try {
			elementHighlight(driver.findElement(element));
			driver.findElement(element).click();

			test.log(LogStatus.PASS, value);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, failurevalue + e);

		}
	}
	
	public void Click(By element, String failurevalue) {
		try {
			elementHighlight(driver.findElement(element));
			driver.findElement(element).click();

			logger.info("passed click statement");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, failurevalue + e);

		}

	}

	
	
	
	public void jsClick(By element,String value) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(element));
			test.log(LogStatus.PASS,value);
			logger.info("passed click statement");


		} catch (Exception e) {
			logger.info("failed click statement");
			test.log(LogStatus.FAIL, value + e);
			

		}
	}

	public void SendKeys(By element, String value,String passvalue,String failurevalue) {
		try {
			elementHighlight(driver.findElement(element));
			driver.findElement(element).sendKeys(value);
			logger.info("passed click statement");

			test.log(LogStatus.PASS, passvalue);

		} catch (Exception e) {
			logger.info("failed with some reason");
			test.log(LogStatus.FAIL, failurevalue + e);

		}
	}

	public void actionCilck(WebElement element,String value,String failurevalue) {
		try {
			elementHighlight(element);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			test.log(LogStatus.PASS,value);

		} catch (Exception e) {
			test.log(LogStatus.FAIL, failurevalue+ e);

		}
	}

	public void sleepMethod(int value) {
		try {
			Thread.sleep(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear(By element) {
		try {
		
				driver.findElement(element).sendKeys(value);

			} catch (Exception e) {
			
			e.printStackTrace();
			}
		}

			
	public void VerifyTitle(String verifyTitle)
	{
		try {
			if(driver.getTitle().equals(verifyTitle))
			{
			test.log(LogStatus.PASS, "Login Successful");
			}
		}catch(Exception e) {
			
			{
				test.log(LogStatus.FAIL, "Unable to navigate the page");
			}
		}
		}
		
	
public void isDisabled(By element,String value)
{
	if(!driver.findElement(element).isEnabled())
	{
		test.log(LogStatus.FAIL, value);
	}
}


public void SelectAllByKeys(By element)
{
	elementHighlight(driver.findElement(element));
	driver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"));
	driver.findElement(element).sendKeys(Keys.chord(Keys.DELETE));
}
public void isEnabled(By element,By element1,String passValue,String failurevalue)
{
	try {
	if(driver.findElement(element).isEnabled())
	{
Click(element1, "Clicked on the button sucesfully");
test.log(LogStatus.PASS, passValue);

	}
	else
	{

		test.log(LogStatus.FAIL, failurevalue);	}
	}
	catch(Exception e)
	{
		System.out.println("Unable to click on the button");
		test.log(LogStatus.FAIL, failurevalue + e);

	}
}

  public void isDisplayed(By element,String value,String value1)
  {
	  elementHighlight(driver.findElement(element));
	  if(driver.findElement(element).isDisplayed())
	  {
			test.log(LogStatus.PASS, value);	

	  }
  else
  {
		test.log(LogStatus.FAIL, value1);	}

	  
		
  }
 
public void actiontabsWithText(String value1,String passvalue,String failurevalue)
{
	Actions action=new Actions(driver);
	
	if(driver.findElement(By.xpath("//p[contains(text(),"+ "'"+value1 +"'"+")]")).isDisplayed())
	{
		WebElement element=driver.findElement(By.xpath("//p[contains(text(),"+ "'"+value1 +"'"+")]"));
		sleepMethod(4000);
		action.moveToElement(element).click().perform();
		test.log(LogStatus.PASS, passvalue);
	}
	else
	{
		test.log(LogStatus.FAIL, failurevalue );
	}
	
}
	
	  @AfterSuite
	  public void tearDown() throws Exception { 
		report.endTest(test);
			report.flush();	 
			EmailOption();
			driver.close();

			
}
}
