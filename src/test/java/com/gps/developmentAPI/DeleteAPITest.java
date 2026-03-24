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
import com.gps.baseAPI.DeleteAPIExecutor;
import com.gps.utilities.ExcelUtil;

public class DeleteAPITest extends APITestBase {

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
        return new ExcelUtil(
                System.getProperty("user.dir") + "/src/test/resources/excel/Gps_Rules.xls")
                .getDataAsMap("RunManagerAPI", "deleteAPITest"); // 🔥 IMPORTANT
    }

    // ================= MAIN TEST =================

    @Test
    @Parameters({"dataMode"})
    public void runDeleteTests(@Optional("") String mode) {

        String finalMode = getDataMode(mode).trim().toLowerCase();

        System.out.println("=========== FINAL DATA MODE =========== " + finalMode);

        DeleteAPIExecutor api = new DeleteAPIExecutor();
        boolean isExecuted = false;

        // ================= EXCEL =================

        if ("excel".equals(finalMode)) {

            System.out.println(">>> EXECUTING FROM EXCEL <<<");

            List<Map<String, String>> dataList = getExcelData();

            for (Map<String, String> row : dataList) {

                String method = row.get("method");
                if (method == null || !method.equalsIgnoreCase("DELETE")) continue;

                String runMode = row.get("RunMode");

                if (runMode == null || runMode.equalsIgnoreCase("no")) {
                    test.get().log(Status.INFO, "⛔ Skipped → RunMode NO (EXCEL)");
                    continue;
                }

                String endpoint = row.get("endpoint");
                String statusStr = row.get("expectedStatusCode");

                if (endpoint == null || statusStr == null) {
                    logInfo("Skipping invalid Excel row");
                    continue;
                }

                try {

                    int expectedStatus = Integer.parseInt(statusStr);

                    api.executeDelete(endpoint, expectedStatus, test.get());

                    isExecuted = true;

                } catch (Exception e) {
                    test.get().log(Status.FAIL, "❌ EXCEL Execution Failed: " + e.getMessage());
                }
            }
        }

        // ================= JSON =================

        else if ("json".equals(finalMode)) {

            System.out.println(">>> EXECUTING FROM JSON <<<");

            JSONArray arr = getJsonArray();

            for (Object obj : arr) {

                JSONObject json = (JSONObject) obj;

                String method = json.get("method") == null ? "" : json.get("method").toString();
                if (!method.equalsIgnoreCase("DELETE")) continue;

                String runMode = json.get("runMode") == null ? "" : json.get("runMode").toString();

                if (runMode.equalsIgnoreCase("no")) {
                    test.get().log(Status.INFO, "⛔ Skipped → RunMode NO (JSON)");
                    continue;
                }

                String endpoint = json.get("endpoint") == null ? "" : json.get("endpoint").toString();
                String statusStr = json.get("expectedStatusCode") == null ? "0"
                        : json.get("expectedStatusCode").toString();

                if (endpoint.isEmpty() || statusStr.equals("0")) {
                    logInfo("Skipping invalid JSON row → " + json);
                    continue;
                }

                try {

                    int expectedStatus = Integer.parseInt(statusStr);

                    api.executeDelete(endpoint, expectedStatus, test.get());

                    isExecuted = true;

                } catch (Exception e) {
                    test.get().log(Status.FAIL, "❌ JSON Execution Failed: " + e.getMessage());
                }
            }
        }

        // ================= INVALID =================

        else {
            throw new RuntimeException("❌ Invalid dataMode: " + finalMode);
        }

        // ================= FINAL CHECK =================

        if (!isExecuted) {
            throw new SkipException("No DELETE tests executed for mode: " + finalMode);
        }
    }
}