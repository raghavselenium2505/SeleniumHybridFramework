package com.gps.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.gps.base.TestBase;

public class ScreenshotUtil extends TestBase {

	public String takeScreenshot(WebDriver driver) {

	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
	            .format(new Date());

	    String filePath = System.getProperty("user.dir")
	            + "\\screenshots\\Screenshot_" + timestamp + ".png";

	    new File(System.getProperty("user.dir") + "\\screenshots\\").mkdirs();

	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        File destination = new File(filePath);
	        FileUtils.copyFile(source, destination);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return filePath;
	}
}