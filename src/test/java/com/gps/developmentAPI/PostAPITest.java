package com.gps.developmentAPI;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.SkipException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.gps.baseAPI.APITestBase;
import com.gps.baseAPI.PostAPIExecutor;
import com.gps.utilities.ExcelUtil;

public class PostAPITest extends APITestBase {

    // ================= JSON READER =================

    public JSONArray getJsonArray() {
        try {
            return (JSONArray) new JSONParser().parse(
                    new FileReader(System.getProperty("user.dir")
                            + "/src/test/resources/excel/apiTestData.json"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }

    // ================= EXCEL READER =================

    public List<Map<String, String>> getExcelData() {
        return new ExcelUtil(System.getProperty("user.dir")
                + "/src/test/resources/excel/Gps_Rules.xls")
                .getDataAsMap("RunManagerAPI", "postAPITest");
    }

    // ================= MODE RESOLUTION =================

    private String resolveMode(String testNGMode) {

        String finalMode = null;

        // 1️⃣ JVM (Highest priority → Jenkins / Maven)
        finalMode = System.getProperty("dataMode");

        // 2️⃣ config.properties (your current setup 🔥)
        if (finalMode == null || finalMode.isEmpty()) {
            finalMode = System.getProperty("dataMode");
        }

        // 3️⃣ TestNG parameter
        if (finalMode == null || finalMode.isEmpty()) {
            finalMode = testNGMode;
        }

        // 4️⃣ Validation (NO default confusion)
        if (finalMode == null || finalMode.trim().isEmpty()) {
            throw new RuntimeException("❌ dataMode not provided (json/excel required)");
        }

        return finalMode.trim().toLowerCase();
    }

    // ================= MAIN TEST =================
    @Test
    @Parameters({"dataMode"})
    public void runPostTests(@Optional("") String mode) {

        // 🔥 USE BASE CLASS METHOD (CLEANEST)
        String finalMode = getDataMode(mode).trim().toLowerCase();

        System.out.println("=========== FINAL DATA MODE =========== " + finalMode);

        PostAPIExecutor api = new PostAPIExecutor();
        boolean isExecuted = false;

        // ================= EXCEL =================

        if ("excel".equals(finalMode)) {

            System.out.println(">>> EXECUTING FROM EXCEL <<<");

            List<Map<String, String>> dataList = getExcelData();

            for (Map<String, String> row : dataList) {

                String method = row.get("method");
                if (method == null || !method.equalsIgnoreCase("POST")) continue;

                String runMode = row.get("RunMode");
                if (runMode == null || runMode.equalsIgnoreCase("no")) {
                    test.get().log(Status.INFO, "⛔ Skipped → RunMode NO (EXCEL)");
                    continue;
                }

                api.executePost(
                        row.get("endpoint"),
                        row.get("body") == null ? "" : row.get("body"),
                        Integer.parseInt(row.get("expectedStatusCode")),
                        test.get()
                );

                isExecuted = true;
            }
        }

        // ================= JSON =================

        else if ("json".equals(finalMode)) {

            System.out.println(">>> EXECUTING FROM JSON <<<");

            JSONArray arr = getJsonArray();

            for (Object obj : arr) {

                JSONObject json = (JSONObject) obj;

                String method = String.valueOf(json.get("method"));
                if (!"POST".equalsIgnoreCase(method)) continue;

                String runMode = String.valueOf(json.get("runMode"));
                if ("no".equalsIgnoreCase(runMode)) {
                    test.get().log(Status.INFO, "⛔ Skipped → RunMode NO (JSON)");
                    continue;
                }

                api.executePost(
                        String.valueOf(json.get("endpoint")),
                        json.get("body") == null ? "" : json.get("body").toString(),
                        Integer.parseInt(String.valueOf(json.get("expectedStatusCode"))),
                        test.get()
                );

                isExecuted = true;
            }
        }

        else {
            throw new RuntimeException("❌ Invalid dataMode: " + finalMode);
        }

        if (!isExecuted) {
            throw new SkipException("No POST tests executed for mode: " + finalMode);
        }
    }
}