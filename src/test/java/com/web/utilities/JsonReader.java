package com.web.utilities;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gps.base.TestBase;

public class JsonReader extends TestBase{

    public static Map<String, Object> getJiraData(String testName) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            // 🔥 Read path from config (NO leading slash)
            String path = config.getProperty("testDataPath");

            List<Map<String, Object>> dataList = mapper.readValue(
                    new File(path),
                    new TypeReference<List<Map<String, Object>>>() {}
            );

            for (Map<String, Object> testData : dataList) {
                String tcName = testData.get("testCase").toString();

                if (tcName.equalsIgnoreCase(testName)) {
                    return testData;
                }
            }

            System.out.println("⚠️ Test data not found for: " + testName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}