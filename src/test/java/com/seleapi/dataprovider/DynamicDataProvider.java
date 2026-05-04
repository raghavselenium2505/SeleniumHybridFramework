package com.seleapi.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seleapi.utils.ExcelUtil;

import org.testng.ITestContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DynamicDataProvider {

    private static final Logger logger = LogManager.getLogger(DynamicDataProvider.class);

    // ================= MAIN DATAPROVIDER =================

    @DataProvider(name = "dynamicData")
    public Object[][] getData(Method method, ITestContext context) throws Exception {

        Test testAnnotation = method.getAnnotation(Test.class);
        String[] groups = testAnnotation.groups();

        String filePath = getFilePath(context);

        logger.info("Fetching data for test: " + method.getName());
        logger.info("Using data file: " + filePath);

        for (String group : groups) {

            if (group.equalsIgnoreCase("MilkManSignupTests")) {
                return getJsonData(method.getName(), filePath);
            }

            if (group.equalsIgnoreCase("excel")) {
                return getExcelData(method.getName(), filePath);
            }
        }

        throw new RuntimeException("No valid group defined for test: " + method.getName());
    }

    // ================= FILE PATH LOGIC =================

    private String getFilePath(ITestContext context) throws Exception {

        // 1️⃣ From testng.xml
        String filePath = context.getCurrentXmlTest().getParameter("testDataPath");

        if (filePath != null && !filePath.trim().isEmpty()) {
            logger.info("Using file path from testng.xml: " + filePath);
            return filePath;
        }

        // 2️⃣ From config.properties
        Properties config = new Properties();

        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config.properties");

        if (is != null) {
            config.load(is);
            logger.info("Loaded config.properties from classpath");
        } else {
            String path = System.getProperty("user.dir") + "/src/test/resources/properties/config.properties";
            logger.warn("Classpath failed, trying fallback path: " + path);

            File file = new File(path);
            if (!file.exists()) {
                throw new RuntimeException("config.properties not found");
            }

            config.load(new FileInputStream(file));
        }

        filePath = config.getProperty("testDataPath");

        if (filePath == null || filePath.trim().isEmpty()) {
            throw new RuntimeException("testDataPath not found in XML or config.properties");
        }

        logger.info("Using file path from config.properties: " + filePath);

        return filePath;
    }

    // ================= JSON =================

    private Object[][] getJsonData(String testName, String filePath) throws Exception {

        logger.info("Reading JSON for test: " + testName);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        List<Object[]> dataList = new ArrayList<>();

        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;

            String testCase = jsonObject.get("testCase").toString();

            if (testCase.equalsIgnoreCase(testName)) {

                Map<String, String> map = new HashMap<>();

                for (Object key : jsonObject.keySet()) {

                    String value = jsonObject.get(key) != null
                            ? jsonObject.get(key).toString()
                            : "";

                    map.put(key.toString(), value);
                }

                // 🔥 DEBUG (VERY IMPORTANT FOR YOU)
                logger.info("Loaded JSON Data: " + map);

                // 🔥 Confirm manualTime exists
                String manualTime = map.get("manualTime");
                logger.info("manualTime value: " + manualTime);

                dataList.add(new Object[]{map});
                break;
            }
        }

        if (dataList.isEmpty()) {
            throw new RuntimeException("No JSON data found for: " + testName);
        }

        logger.info("JSON data loaded successfully for test: " + testName);

        return dataList.toArray(new Object[0][]);
    }

    // ================= EXCEL =================

    private Object[][] getExcelData(String testName, String filePath) {

        logger.info("Reading Excel for test: " + testName);

        ExcelUtil excelUtil = new ExcelUtil(filePath);

        Object[][] data = excelUtil.getSheetDataByTestCaseName("Signin", testName);

        if (data.length == 0) {
            throw new RuntimeException("No Excel data found for: " + testName);
        }

        logger.info("Excel data loaded successfully for test: " + testName);

        return data;
    }
}