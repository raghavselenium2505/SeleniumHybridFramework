package com.gps.utilities;

import java.io.FileReader;
import java.lang.reflect.Method;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gps.base.TestBase;

public class DynamicDataProvider extends TestBase {

    @DataProvider(name = "dynamicData")
    public Object[][] getData(Method method)
            throws Exception {

        Test testAnnotation =
                method.getAnnotation(Test.class);

        String[] groups =
                testAnnotation.groups();

        for (String group : groups) {

            if (group.equalsIgnoreCase("json")) {
                return getJsonData(
                        method.getName());
            }

            if (group.equalsIgnoreCase("excel")) {
                return getExcelData(
                        method.getName());
            }
        }

        throw new RuntimeException(
                "No data source defined");
    }

    // ---------------- JSON ----------------

    private Object[][] getJsonData(
            String testName)
            throws Exception {

        JSONParser parser =
                new JSONParser();

        Object obj =
                parser.parse(
                        new FileReader(
                        		"D:\\Repos\\src\\test\\resources\\excel\\testdata.json"));

        JSONObject jsonObject =
                (JSONObject) obj;

        JSONObject testData =
                (JSONObject) jsonObject.get(testName);

        return new Object[][]{
                {
                        testData.get("username")
                                .toString(),
                        testData.get("password")
                                .toString(),
                        testData.get("search")
                                .toString()
                }
        };
    }

    // ---------------- EXCEL ----------------

    private Object[][] getExcelData(
            String testName) {

        return excelutil.getSheetDataByTestCaseName(
                "Signin",
                testName,
                "Gps_Rules.xls");
    }
}