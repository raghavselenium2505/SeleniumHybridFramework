package com.gps.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.*;

public class DynamicDataProvider {

    private static final String CONFIG_PATH = System.getProperty("user.dir")
            + "/src/test/resources/properties/Config.properties";

    private Properties config = new Properties();

    // ================= EXISTING UI DATAPROVIDER (UNCHANGED) =================

    @DataProvider(name = "dynamicData")
    public Object[][] getData(Method method) throws Exception {

        Test testAnnotation = method.getAnnotation(Test.class);
        String[] groups = testAnnotation.groups();

        for (String group : groups) {

            if (group.equalsIgnoreCase("json")) {
                return getJsonData(method.getName());
            }

            if (group.equalsIgnoreCase("excel")) {
                return getExcelData(method.getName());
            }
        }

        throw new RuntimeException("No valid group defined for test: " + method.getName());
    }

    // ================= EXISTING JSON (UI - UNCHANGED) =================

    private Object[][] getJsonData(String testName) throws Exception {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/excel/testdata.json";

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        List<Object[]> dataList = new ArrayList<>();

        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;

            String testCase = jsonObject.get("testCase").toString();

            if (testCase.equalsIgnoreCase(testName)) {

                dataList.add(new Object[]{
                        jsonObject.get("username").toString(),
                        jsonObject.get("password").toString()
                });
            }
        }

        return dataList.toArray(new Object[0][]);
    }

    // ================= EXISTING EXCEL (UI - UNCHANGED) =================

    private Object[][] getExcelData(String testName) {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/excel/Gps_Rules.xls";

        ExcelUtil excelUtil = new ExcelUtil(filePath);

        Object[][] data = excelUtil.getSheetDataByTestCaseName("Signin", testName);

        if (data.length == 0) {
            throw new RuntimeException("No Excel data found for: " + testName);
        }

        return data;
    }

    // ============================================================
    // 🔥 NEW API DATAPROVIDER (NO IMPACT TO UI)
    // ============================================================

    @DataProvider(name = "apiData")
    public Object[][] getApiData(Method method, ITestContext context) throws Exception {

        config.load(new FileInputStream(CONFIG_PATH));

        // 🔥 PRIORITY: XML → System → Config → Default
        String dataType = context.getCurrentXmlTest().getParameter("dataType");

        if (dataType == null || dataType.isEmpty()) {
            dataType = System.getProperty("dataType");
        }

        if (dataType == null || dataType.isEmpty()) {
            dataType = config.getProperty("dataType");
        }

        if (dataType == null || dataType.isEmpty()) {
            dataType = "json";
        }

        System.out.println("API Data Type: " + dataType);

        if (dataType.equalsIgnoreCase("json")) {
            return getApiJsonData(method.getName());
        }

        if (dataType.equalsIgnoreCase("excel")) {
            return getApiExcelData(method.getName());
        }

        throw new RuntimeException("Invalid API dataType: " + dataType);
    }

    // ================= API JSON =================

    private Object[][] getApiJsonData(String testName) throws Exception {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/excel/apiTestData.json";

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        List<Object[]> dataList = new ArrayList<>();

        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;

            String testCase = jsonObject.get("testCase").toString();

            if (testCase.equalsIgnoreCase(testName)) {

                dataList.add(new Object[]{jsonObject});
            }
        }

        if (dataList.isEmpty()) {
            throw new RuntimeException("No API JSON data found for: " + testName);
        }

        return dataList.toArray(new Object[0][]);
    }

    // ================= API EXCEL =================

    private Object[][] getApiExcelData(String testName) {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/excel/Gps_Rules.xls";

        ExcelUtil excelUtil = new ExcelUtil(filePath);

        List<Map<String, String>> data =
                excelUtil.getDataAsMap("Sheet1", testName);

        if (data.isEmpty()) {
            throw new RuntimeException("No API Excel data found for: " + testName);
        }

        return data.stream()
                .map(d -> new Object[]{d})
                .toArray(Object[][]::new);
    }
}