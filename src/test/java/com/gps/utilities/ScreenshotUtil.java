package com.gps.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.gps.base.TestBase;
public class ScreenshotUtil extends TestBase{
	public File captureScreenshot(String fileName) {
		
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" + fileName
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + ".jpg");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return destFile;
	}
		
}