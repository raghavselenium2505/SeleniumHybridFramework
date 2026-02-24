package com.gps.development;

import java.io.FileReader;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.web.utilities.*;
import com.aventstack.extentreports.Status;
import com.gps.base.TestBase;
import com.gps.pages.AmazonHomePage;
import com.gps.pages.AmazonLoginPage;

public class AmazonLogin_TC001 extends TestBase {

    private final String xlsname = "Gps_Rules.xls";
    private final JSONParser parser = new JSONParser();

    @Test(retryAnalyzer = com.web.utilities.AIRetryAnalyzer.class)
    public void Amazon_Login_TC001() {

        try {

            System.out.println("Login to Amazon");

            AmazonHomePage home = new AmazonHomePage();

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

            click(
                    home.linkAccount,
                    "Able to click on link account",
                    "Unable to click on link account"
            );
            

            AmazonLoginPage login = new AmazonLoginPage(getDriver());

            login.login(
                    config.getProperty("userName"),
                    config.getProperty("passWord"),
                    "Able to login",
                    "Unable to login"
            );

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

            String s = "N";

            if (s.equalsIgnoreCase("Y")) {

                Object obj = parser.parse(
                        new FileReader("C:\\Users\\RaghavendraD\\Desktop\\sample.json"));

                JSONObject jsonObject = (JSONObject) obj;
                String name = (String) jsonObject.get("name");

                getDriver()
                        .findElement(By.id("twotabsearchtextbox"))
                        .sendKeys(name);

            } else if (s.equalsIgnoreCase("N")) {

                getDriver()
                        .findElement(By.id("twotabsearchtextbox"))
                        .sendKeys(
                                getDataFromExcel("Signin", "Jira_Story_Name", xlsname)
                        );

            } else {
                System.out.println("Please configure datasheet option");
            }

            Thread.sleep(6000);

        } catch (Exception e) {

            System.out.println("❌ Test Failed: " + e.getMessage());

            // 🔥 AI Analysis
            //String aiSuggestion = AITestAnalyzer.analyze(e);
         //   String screenshotPath = screenshotutil.captureScreenshot(getDriver());

           // test.get().log(
			/*
			 * Status.FAIL, aiSuggestion + "<br>" + "<a href='" + screenshotPath +
			 * "' target='_blank'>" + "Click here for screenshot" + "</a>" );
			 */

            throw new RuntimeException(e);

        }
    }

    private String getDataFromExcel(String sheetName, String columnName, String xls) {
        return excelutil.getData(sheetName, columnName, xls);
    }
}
