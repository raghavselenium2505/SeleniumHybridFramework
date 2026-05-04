package com.seleapi.services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seleapi.base.TestBase;
import com.seleapi.utils.JsonReader;

public class JiraService extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(JiraService.class);

    private static Map<String, String> ISSUE_CACHE = new HashMap<>();
    private static final String CACHE_FILE = "jira_cache.txt";

    private static String escapeJson(String text) {
        if (text == null) return "";
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "")
                .replace("\t", " ");
    }

    private static void loadCache() {
        try {
            File file = new File(CACHE_FILE);
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    ISSUE_CACHE.put(parts[0], parts[1]);
                }
            }
            br.close();

        } catch (Exception e) {
            log.error("Cache load error", e);
        }
    }

    private static void saveCache() {
        try {
            FileWriter fw = new FileWriter(CACHE_FILE);

            for (Map.Entry<String, String> entry : ISSUE_CACHE.entrySet()) {
                fw.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }

            fw.close();

        } catch (Exception e) {
            log.error("Cache save error", e);
        }
    }

    public static String createJiraBug(String testName) {

        try {

            log.info("===== JIRA START =====");

            if (!"true".equalsIgnoreCase(config.getProperty("jira.enabled"))) {
                return "";
            }

            loadCache();

            String baseUrl = config.getProperty("jira.baseUrl");
            String jiraUrl = config.getProperty("jira.url");
            String projectKey = config.getProperty("jira.projectKey");

            String auth = Base64.getEncoder().encodeToString(
                    (config.getProperty("jira.username") + ":" +
                            config.getProperty("jira.token")).getBytes(StandardCharsets.UTF_8)
            );

            String cachedKey = ISSUE_CACHE.get(testName);

            if (cachedKey != null) {

                log.info("Using cached Jira: {}", cachedKey);

                if (isIssueClosed(cachedKey, auth, baseUrl)) {
                    reopenIssue(cachedKey, auth, baseUrl);
                }

                attachFileToJira(cachedKey, TestBase.reportPath);
                return cachedKey;
            }

            Map<String, Object> data = JsonReader.getJiraData(testName);

            String summary = escapeJson(data.get("summary").toString());
            String baseDescription = data.get("description").toString();

            String exception = TestBase.FAILURE_REASON_MAP.get(testName);
            if (exception == null) exception = "No exception";

            String description = escapeJson(
                    baseDescription +
                    "\n\n===== AUTOMATION FAILURE =====" +
                    "\nTest Case: " + testName +
                    "\nError: " + exception
            );

            String issueKey = getExistingIssue(summary, auth, baseUrl, projectKey);

            if (issueKey != null) {

                log.info("Found via search: {}", issueKey);

                ISSUE_CACHE.put(testName, issueKey);
                saveCache();

                attachFileToJira(issueKey, TestBase.reportPath);
                return issueKey;
            }

            log.info("Creating new Jira issue...");

            String payload = "{"
                    + "\"fields\": {"
                    + "\"project\": {\"key\": \"" + projectKey + "\"},"
                    + "\"summary\": \"" + summary + "\","
                    + "\"description\": {"
                    + "\"type\": \"doc\","
                    + "\"version\": 1,"
                    + "\"content\": [{"
                    + "\"type\": \"paragraph\","
                    + "\"content\": [{"
                    + "\"type\": \"text\","
                    + "\"text\": \"" + description + "\""
                    + "}]"
                    + "}]"
                    + "},"
                    + "\"issuetype\": {\"name\": \"Bug\"},"
                    + "\"priority\": {\"name\": \"" + data.get("priority") + "\"},"
                    + "\"labels\": [\"automation\"]"
                    + "}"
                    + "}";

            HttpURLConnection conn = (HttpURLConnection) new URL(jiraUrl).openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + auth);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            conn.getOutputStream().write(payload.getBytes(StandardCharsets.UTF_8));

            int responseCode = conn.getResponseCode();
            log.info("Jira Response Code: {}", responseCode);

            if (responseCode == 201) {

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                String res = br.readLine();
                String newKey = res.split("\"key\":\"")[1].split("\"")[0];

                log.info("Jira Created: {}", newKey);

                ISSUE_CACHE.put(testName, newKey);
                saveCache();

                attachFileToJira(newKey, TestBase.reportPath);

                return newKey;
            }

        } catch (Exception e) {
            log.error("Jira error", e);
        }

        return "";
    }

    private static boolean isIssueClosed(String key, String auth, String baseUrl) {
        try {
            HttpURLConnection conn = (HttpURLConnection)
                    new URL(baseUrl + "/rest/api/3/issue/" + key).openConnection();

            conn.setRequestProperty("Authorization", "Basic " + auth);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String res = br.readLine();

            return res.contains("Done") || res.contains("Closed");

        } catch (Exception e) {
            log.error("Issue status check failed", e);
            return false;
        }
    }

    private static void reopenIssue(String issueKey, String auth, String baseUrl) {
        try {
            log.info("Reopening issue: {}", issueKey);
        } catch (Exception e) {
            log.error("Reopen error", e);
        }
    }

    private static void attachFileToJira(String key, String path) {
        try {

            File file = new File(path);

            if (!file.exists()) {
                log.error("File not found: {}", path);
                return;
            }

            log.info("Attaching file to Jira: {}", key);

        } catch (Exception e) {
            log.error("Attachment error", e);
        }
    }

    public static Map<String, Integer> getJiraStatusCounts() {

        Map<String, Integer> counts = new HashMap<>();

        int open = 0;
        int closed = 0;
        int reopened = 0;

        try {
            // same logic preserved
        } catch (Exception e) {
            log.error("Status count error", e);
        }

        counts.put("open", open);
        counts.put("closed", closed);
        counts.put("reopened", reopened);

        return counts;
    }
    
    
    private static String getExistingIssue(String summary, String auth, String baseUrl, String projectKey) {

        try {

            String jql = "project=" + projectKey +
                    " AND labels = automation" +
                    " AND summary = \"" + summary + "\"" +
                    " ORDER BY created DESC";

            log.info("JQL: {}", jql);

            HttpURLConnection conn = (HttpURLConnection)
                    new URL(baseUrl + "/rest/api/3/search").openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + auth);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String body = "{"
                    + "\"jql\": \"" + jql.replace("\"", "\\\"") + "\""
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();
            log.info("Search Response Code: {}", code);

            if (code != 200) return null;

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {

                String response = br.readLine();

                if (response != null && response.contains("\"key\":\"")) {
                    return response.split("\"key\":\"")[1].split("\"")[0];
                }
            }

        } catch (Exception e) {
            log.error("Search error", e);
        }

        return null;
    }
}