package com.web.utilities;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gps.base.TestBase;

public class JiraService extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(JiraService.class);

    // ================= CACHE =================
    private static Map<String, String> ISSUE_CACHE = new HashMap<>();
    private static final String CACHE_FILE = "jira_cache.txt";

    // ================= ESCAPE =================
    private static String escapeJson(String text) {
        if (text == null) return "";
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "")
                .replace("\t", " ");
    }

    // ================= LOAD CACHE =================
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

    // ================= SAVE CACHE =================
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

    // ================= MAIN =================
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

            // ================= CACHE FIRST =================
            String cachedKey = ISSUE_CACHE.get(testName);

            if (cachedKey != null) {

                log.info("Using cached Jira: {}", cachedKey);

                if (isIssueClosed(cachedKey, auth, baseUrl)) {
                    reopenIssue(cachedKey, auth, baseUrl);
                }

                attachFileToJira(cachedKey, TestBase.reportPath);
                return cachedKey;
            }

            // ================= JSON =================
            Map<String, Object> data = JsonReader.getJiraData(testName);

            String summary = escapeJson(data.get("summary").toString());
            String baseDescription = data.get("description").toString();

            String exception = TestBase.FAILURE_REASON_MAP.get(testName);
            if (exception == null) exception = "No exception";

            String description = baseDescription +
                    "\n\n===== AUTOMATION FAILURE =====" +
                    "\nTest Case: " + testName +
                    "\nError: " + exception;

            description = escapeJson(description);

            // ================= SEARCH FIXED =================
            String issueKey = getExistingIssue(summary, auth, baseUrl, projectKey);

            if (issueKey != null) {

                log.info("Found via search: {}", issueKey);

                ISSUE_CACHE.put(testName, issueKey);
                saveCache();

                attachFileToJira(issueKey, TestBase.reportPath);
                return issueKey;
            }

            // ================= CREATE =================
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

    // ================= FIXED SEARCH =================
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

            conn.getOutputStream().write(body.getBytes(StandardCharsets.UTF_8));

            int code = conn.getResponseCode();
            log.info("Search Response Code: {}", code);

            if (code != 200) return null;

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String response = br.readLine();

            if (response != null && response.contains("\"key\":\"")) {
                return response.split("\"key\":\"")[1].split("\"")[0];
            }

        } catch (Exception e) {
            log.error("Search error", e);
        }

        return null;
    }

    // ================= STATUS =================
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
            return false;
        }
    }

    // ================= REOPEN =================
    private static void reopenIssue(String issueKey, String auth, String baseUrl) {

        try {

            // 🔥 STEP 1: GET TRANSITIONS
            String url = baseUrl + "/rest/api/3/issue/" + issueKey + "/transitions";

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic " + auth);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String response = br.readLine();
            log.info("Transitions: {}", response);

            // 🔥 STEP 2: FIND OPEN / TO DO
            String transitionId = null;

            if (response.contains("\"name\":\"To Do\"")) {
                transitionId = response.split("\"id\":\"")[1].split("\"")[0];
            } else if (response.contains("\"name\":\"Open\"")) {
                transitionId = response.split("\"id\":\"")[1].split("\"")[0];
            } else if (response.contains("\"name\":\"Reopen\"")) {
                transitionId = response.split("\"id\":\"")[1].split("\"")[0];
            }

            if (transitionId == null) {
                log.warn("No valid reopen transition found!");
                return;
            }

            log.info("Using transition ID: {}", transitionId);

            // 🔥 STEP 3: APPLY TRANSITION
            HttpURLConnection postConn = (HttpURLConnection) new URL(url).openConnection();

            postConn.setRequestMethod("POST");
            postConn.setRequestProperty("Authorization", "Basic " + auth);
            postConn.setRequestProperty("Content-Type", "application/json");
            postConn.setDoOutput(true);

            String payload = "{ \"transition\": { \"id\": \"" + transitionId + "\" } }";

            postConn.getOutputStream().write(payload.getBytes());

            int responseCode = postConn.getResponseCode();

            log.info("Reopen Response Code: {}", responseCode);

        } catch (Exception e) {
            log.error("Reopen error", e);
        }
    }

    // ================= ATTACH =================
    private static void attachFileToJira(String key, String path) {
        try {

            File file = new File(path);

            if (!file.exists()) {
                log.error("File not found: {}", path);
                return;
            }

            String boundary = "----Boundary";

            HttpURLConnection conn = (HttpURLConnection)
                    new URL(config.getProperty("jira.baseUrl") +
                            "/rest/api/3/issue/" + key + "/attachments").openConnection();

            conn.setRequestMethod("POST");

            String auth = config.getProperty("jira.username") + ":" + config.getProperty("jira.token");

            conn.setRequestProperty("Authorization", "Basic " +
                    Base64.getEncoder().encodeToString(auth.getBytes()));

            conn.setRequestProperty("X-Atlassian-Token", "no-check");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true);

            writer.append("--" + boundary + "\r\n");
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n");
            writer.append("Content-Type: text/html\r\n\r\n").flush();

            FileInputStream fis = new FileInputStream(file);

            byte[] buffer = new byte[4096];
            int bytes;

            while ((bytes = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytes);
            }

            os.flush();
            fis.close();

            writer.append("\r\n--" + boundary + "--\r\n").flush();

            log.info("Attachment Response: {}", conn.getResponseCode());

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

            String baseUrl = config.getProperty("jira.baseUrl");
            String email = config.getProperty("jira.username");
            String token = config.getProperty("jira.token");

            String auth = Base64.getEncoder()
                    .encodeToString((email + ":" + token).getBytes("UTF-8"));

            for (String key : CREATED_ISSUES) {

                String url = baseUrl + "/rest/api/3/issue/" + key;

                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Basic " + auth);

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                String response = br.readLine();

                if (response.contains("\"status\":{\"name\":\"Done\"") ||
                    response.contains("\"status\":{\"name\":\"Closed\"")) {

                    closed++;

                } else if (response.contains("Reopened")) {

                    reopened++;

                } else {

                    open++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        counts.put("open", open);
        counts.put("closed", closed);
        counts.put("reopened", reopened);

        return counts;
    }
}