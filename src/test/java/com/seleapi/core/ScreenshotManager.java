package com.seleapi.core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.seleapi.driver.DriverManager;

public class ScreenshotManager {

    public static Logger log =
            Logger.getLogger(ScreenshotManager.class);

    private ScreenshotManager() {

    }

    public static String captureScreenshot(
            String testName) {

        String screenshotPath = "";

        try {

            String timestamp =
                    new SimpleDateFormat(
                            "yyyyMMdd_HHmmss")
                            .format(new Date());

            screenshotPath =
                    System.getProperty("user.dir")
                    + "/screenshots/"
                    + testName
                    + "_"
                    + timestamp
                    + ".png";

            File sourceFile =
                    ((TakesScreenshot)
                            DriverManager
                            .getDriver())
                            .getScreenshotAs(
                                    OutputType.FILE);

            File destinationFile =
                    new File(screenshotPath);

            FileUtils.copyFile(
                    sourceFile,
                    destinationFile);

            log.info(
                    "Screenshot Captured : "
                    + screenshotPath);

        } catch (Exception e) {

            log.error(
                    "Failed To Capture Screenshot",
                    e);
        }

        return screenshotPath;
    }
}