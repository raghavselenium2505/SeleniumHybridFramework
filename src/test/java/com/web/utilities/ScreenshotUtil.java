package com.web.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
	 public static String captureScreenshot(WebDriver driver, String testName) {
	        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
	        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        try {
	            FileUtils.copyFile(source, new File(filePath));
	        } catch (IOException e) {
	            System.err.println("Error while capturing screenshot: " + e.getMessage());
	        }

	        return filePath;
	    }
	
}
 