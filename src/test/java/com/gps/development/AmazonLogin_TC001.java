package com.gps.development;

import java.io.FileReader;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.gps.base.TestBase;
import com.gps.pages.AmazonHomePage;
import com.gps.pages.AmazonLoginPage;
import com.gps.utilities.ExcelUtil;

public class AmazonLogin_TC001 extends TestBase {


    // ================= JSON TEST =================

    @Test(
            groups = "json",
            dataProvider = "dynamicData",
            dataProviderClass = com.gps.utilities.DynamicDataProvider.class
    )
    public void jsonLoginTest(String username, String password) {

        JSONArray jsonArray = getJsonArray();

        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;

            String runMode = jsonObject.get("runMode").toString();

            if (runMode.equalsIgnoreCase("no")) {
            	test.get().skip(
            	        "<span style='color:orange;font-weight:bold;'>⚠ Test skipped because RunMode is NO in JSON file</span>"
            	    );

            	    throw new SkipException("RunMode NO");
            }
        }

        System.out.println("Running JSON Test");
        System.out.println(username + " | " + password);

		/*
		 * AmazonHomePage home = new AmazonHomePage();
		 * 
		 * getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 * 
		 * click( home.linkAccount, "Able to click on link account",
		 * "Unable to click on link account" );
		 * 
		 * AmazonLoginPage login = new AmazonLoginPage(getDriver());
		 * 
		 * login.login( username, password, "Able to login", "Unable to login" );
		 */
    }

    // ================= EXCEL TEST =================

    @Test(
            groups = "excel",
            dataProvider = "dynamicData",
            dataProviderClass = com.gps.utilities.DynamicDataProvider.class
    )
    public void excelLoginTest(String username, String password, String search) {

        String testName = "AmazonLogin_TC001";

        String runMode = getExcelRunMode(testName);

        if (runMode.equalsIgnoreCase("NO")) {
        	test.get().skip(
        	        "<span style='color:orange;font-weight:bold;'>⚠ Test skipped because RunMode is NO in JSON file</span>"
        	    );

        	    throw new SkipException("RunMode NO");
        }

        System.out.println("Running Excel Test");
        System.out.println(username + " | " + password + " | " + search);

		/*
		 * AmazonHomePage home = new AmazonHomePage();
		 * 
		 * getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 * 
		 * click( home.linkAccount, "Able to click on link account",
		 * "Unable to click on link account" );
		 * 
		 * AmazonLoginPage login = new AmazonLoginPage(getDriver());
		 * 
		 * login.login( username, password, "Able to login", "Unable to login" );
		 */
    }
}