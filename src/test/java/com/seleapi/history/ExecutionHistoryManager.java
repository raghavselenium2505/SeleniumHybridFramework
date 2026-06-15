package com.seleapi.history;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.seleapi.dashboard.DashboardManager;
import com.seleapi.utils.ConfigReader;

public class ExecutionHistoryManager {

    public static Logger log =
            Logger.getLogger(
                    ExecutionHistoryManager.class);

    private ExecutionHistoryManager() {

    }

    /*
     * SAVE EXECUTION HISTORY
     */

    public static void saveExecutionHistory() {

        try {

            log.info(
                    "========== SAVING EXECUTION HISTORY ==========");

            /*
             * HISTORY PATH
             */

            String historyPath =

                    System.getProperty(
                            "user.dir")

                    + "/Reports/history/roi-history.json";

            /*
             * CREATE DIRECTORY
             */

            File folder =

                    new File(

                            System.getProperty(
                                    "user.dir")

                            + "/Reports/history");

            if (!folder.exists()) {

                folder.mkdirs();

                log.info(
                        "History Folder Created Successfully");
            }

            /*
             * JSON ARRAY
             */

            JSONArray historyArray;

            /*
             * CHECK FILE EXISTS
             */

            File historyFile =
                    new File(historyPath);

            if (historyFile.exists()) {

                String content =

                        new String(

                                Files.readAllBytes(

                                        Paths.get(
                                                historyPath)));

                if (content.trim().isEmpty()) {

                    historyArray =
                            new JSONArray();
                }

                else {

                    historyArray =
                            new JSONArray(
                                    content);
                }

            }

            else {

                historyArray =
                        new JSONArray();
            }

            /*
             * CREATE EXECUTION JSON
             */

            JSONObject execution =
                    new JSONObject();

            execution.put(
                    "executionTime",

                    new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss")

                    .format(new Date()));

            execution.put(
                    "environment",

                    ConfigReader
                    .getProperty(
                            "env"));

            execution.put(
                    "engine",

                    ConfigReader
                    .getProperty(
                            "engine"));

            execution.put(
                    "browser",

                    ConfigReader
                    .getProperty(
                            "browser"));

            execution.put(
                    "totalTests",

                    DashboardManager
                    .getTotalTests());

            execution.put(
                    "passedTests",

                    DashboardManager
                    .getPassedTests());

            execution.put(
                    "failedTests",

                    DashboardManager
                    .getFailedTests());

            execution.put(
                    "skippedTests",

                    DashboardManager
                    .getSkippedTests());

            execution.put(
                    "passPercentage",

                    DashboardManager
                    .getPassPercentage());

            execution.put(
                    "apiTests",

                    DashboardManager
                    .getApiTests());

            execution.put(
                    "apiPassed",

                    DashboardManager
                    .getApiPassed());

            execution.put(
                    "apiFailed",

                    DashboardManager
                    .getApiFailed());

            execution.put(
                    "averageApiResponseTime",

                    DashboardManager
                    .getAverageApiResponseTime());

            /*
             * ADD TO ARRAY
             */

            historyArray.put(
                    execution);

            /*
             * WRITE JSON
             */

            FileWriter writer =

                    new FileWriter(
                            historyPath);

            writer.write(
                    historyArray.toString(
                            4));

            writer.flush();

            writer.close();

            log.info(
                    "Execution History Saved Successfully");

            log.info(
                    "History Path : "
                    + historyPath);

            log.info(
                    "========== EXECUTION HISTORY COMPLETED ==========");

        }

        catch (Exception e) {

            log.error(
                    "Failed To Save Execution History",
                    e);

            throw new RuntimeException(e);
        }
    }
}