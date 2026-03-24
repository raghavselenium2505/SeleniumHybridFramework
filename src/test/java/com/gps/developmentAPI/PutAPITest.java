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
import com.gps.baseAPI.PutAPIExecutor;
import com.gps.utilities.ExcelUtil;

public class PutAPITest extends APITestBase {

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
                .getDataAsMap("RunManagerAPI", "putAPITest"); // 🔥 IMPORTANT
    }

    // ================= MAIN TEST =================

    @Test
    @Parameters({"dataMode"})
    public void runPutTests(@Optional("") String mode) {

        // 🔥 USE BASE CLASS METHOD
        String finalMode = getDataMode(mode).trim().toLowerCase();

        System.out.println("=========== FINAL DATA MODE =========== " + finalMode);

        PutAPIExecutor api = new PutAPIExecutor();
        boolean isExecuted = false;

        // ================= EXCEL =================

        if ("excel".equals(finalMode)) {

            System.out.println(">>> EXECUTING FROM EXCEL <<<");

            List<Map<String, String>> dataList;

            try {
                dataList = getExcelData();
            } catch (Exception e) {
                throw new RuntimeException("❌ Excel mode selected but no data found", e);
            }

            for (Map<String, String> row : dataList) {

                String method = row.get("method");
                if (method == null || !method.equalsIgnoreCase("PUT")) continue;

                String runMode = row.get("RunMode");
                if (runMode == null || runMode.equalsIgnoreCase("no")) {
                    test.get().log(Status.INFO, "⛔ Skipped → RunMode NO (EXCEL)");
                    continue;
                }

                try {

                    api.executePut(
                            row.get("endpoint"),
                            row.get("body") == null ? "" : row.get("body"),
                            Integer.parseInt(row.get("expectedStatusCode")),
                            test.get()
                    );

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

            if (arr == null || arr.isEmpty()) {
                throw new RuntimeException("❌ JSON mode selected but no data found");
            }

            for (Object obj : arr) {

                JSONObject json = (JSONObject) obj;

                String method = String.valueOf(json.get("method"));
                if (!"PUT".equalsIgnoreCase(method)) continue;

                String runMode = String.valueOf(json.get("runMode"));
                if ("no".equalsIgnoreCase(runMode)) {
                    test.get().log(Status.INFO, "⛔ Skipped → RunMode NO (JSON)");
                    continue;
                }

                try {

                    api.executePut(
                            String.valueOf(json.get("endpoint")),
                            json.get("body") == null ? "" : json.get("body").toString(),
                            Integer.parseInt(String.valueOf(json.get("expectedStatusCode"))),
                            test.get()
                    );

                    isExecuted = true;

                } catch (Exception e) {
                    test.get().log(Status.FAIL, "❌ JSON Execution Failed: " + e.getMessage());
                }
            }
        }

        // ================= INVALID =================

        else {
            throw new RuntimeException("❌ Invalid dataMode: " + finalMode + " (use json/excel)");
        }

        // ================= FINAL CHECK =================

        if (!isExecuted) {
            throw new SkipException("No PUT tests executed for mode: " + finalMode);
        }
    }
}