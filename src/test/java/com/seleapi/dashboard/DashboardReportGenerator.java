package com.seleapi.dashboard;

import java.awt.Desktop;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.seleapi.utils.ConfigReader;

public class DashboardReportGenerator {

    public static Logger log =
            Logger.getLogger(
                    DashboardReportGenerator.class);

    private DashboardReportGenerator() {

    }

    /*
     * GENERATE DASHBOARD
     */

    public static void generateDashboard() {

        try {

            /*
             * FOLDER PATH
             */

            String folderPath =

                    System.getProperty(
                            "user.dir")

                    + "/src/test/resources/Reports/Dashboard";

            File folder =
                    new File(folderPath);

            if (!folder.exists()) {

                folder.mkdirs();
            }

            /*
             * DASHBOARD PATH
             */

            String dashboardPath =

                    folderPath

                    + "/Dashboard_"

                    + new SimpleDateFormat(
                            "yyyyMMdd_HHmmss")

                    .format(new Date())

                    + ".html";

            /*
             * DASHBOARD DATA
             */

            int totalTests =
                    DashboardManager
                    .getTotalTests();

            int passedTests =
                    DashboardManager
                    .getPassedTests();

            int failedTests =
                    DashboardManager
                    .getFailedTests();

            int skippedTests =
                    DashboardManager
                    .getSkippedTests();

            int apiTests =
                    DashboardManager
                    .getApiTests();

            int apiPassed =
                    DashboardManager
                    .getApiPassed();

            int apiFailed =
                    DashboardManager
                    .getApiFailed();

            int uiTests =
                    DashboardManager
                    .getUiTests();

            long avgApiResponseTime =
                    DashboardManager
                    .getAverageApiResponseTime();

            String passPercentage =
                    DashboardManager
                    .getPassPercentage();

            /*
             * CURRENT ENGINE
             */

            String currentEngine =

                    ConfigReader
                    .getProperty(
                            "engine");
            /*
             * AWS REPORT URL
             */

            String awsReportUrl =

                    DashboardManager
                    .getAwsReportUrl();
            
            
            /*
             * HTML START
             */

            String html =

                    "<html>"

                    + "<head>"

                    + "<title>"
                    + "SeleAPI Dashboard"
                    + "</title>"

                    + "<meta charset='UTF-8'>"

                    + "<meta name='viewport' "
                    + "content='width=device-width, initial-scale=1.0'>"

                    + "<script src='https://cdn.jsdelivr.net/npm/chart.js'></script>"

                    + "<style>"

                    + "body{"
                    + "font-family:Segoe UI;"
                    + "background:#0f172a;"
                    + "color:white;"
                    + "padding:20px;"
                    + "text-align:center"
                    + "}"

                    + ".tabs{"
                    + "display:flex;"
                    + "justify-content:center;"
                    + "gap:15px;"
                    + "margin-bottom:25px;"
                    + "flex-wrap:wrap"
                    + "}"

                    + ".tab{"
                    + "padding:12px 22px;"
                    + "border-radius:10px;"
                    + "cursor:pointer;"
                    + "font-weight:bold;"
                    + "color:white"
                    + "}"

                    + ".summary{background:#3b82f6}"

                    + ".api{background:#06b6d4}"

                    + ".charts{background:#22c55e}"

                    + ".card{"
                    + "background:#1e293b;"
                    + "padding:20px;"
                    + "border-radius:15px;"
                    + "margin-top:20px"
                    + "}"

                    + ".hidden{display:none}"

                    + ".chart-container{"
                    + "width:400px;"
                    + "margin:auto"
                    + "}"

                    + "</style>"

                    + "<script>"

                    + "function showTab(tab){"

                    + "document.getElementById('summary').style.display='none';"

                    + "document.getElementById('api').style.display='none';"

                    + "document.getElementById('charts').style.display='none';"

                    + "document.getElementById(tab).style.display='block';"

                    + "}"

                    + "</script>"

                    + "</head>"

                    + "<body>"

                    + "<h1>"
                    + "🚀 SeleAPI Enterprise Dashboard"
                    + "</h1>"

                    /*
                     * TABS
                     */

                    + "<div class='tabs'>"

                    + "<span class='tab summary' "
                    + "onclick=\"showTab('summary')\">"
                    + "Summary"
                    + "</span>"

                    + "<span class='tab api' "
                    + "onclick=\"showTab('api')\">"
                    + "API Dashboard"
                    + "</span>"

                    + "<span class='tab charts' "
                    + "onclick=\"showTab('charts')\">"
                    + "Charts"
                    + "</span>"

                    + "</div>"

                    /*
                     * SUMMARY TAB
                     */
                    + "<div id='summary' class='card'>"

                    + "<h2>"
                    + "Execution Summary"
                    + "</h2>"

                    + "<p>Total Tests : "
                    + totalTests
                    + "</p>"

                    + "<p>Passed Tests : "
                    + passedTests
                    + "</p>"

                    + "<p>Failed Tests : "
                    + failedTests
                    + "</p>"

                    + "<p>Skipped Tests : "
                    + skippedTests
                    + "</p>"

                    + "<p>Pass Percentage : "
                    + passPercentage
                    + "</p>"

                    + "<p>UI Tests : "
                    + uiTests
                    + "</p>"

                    + "<p>API Tests : "
                    + apiTests
                    + "</p>"

                    + "<p>Environment : "
                    + ConfigReader
                    .getProperty(
                            "env")
                    + "</p>"

                    + "<p>Current Engine : "
                    + currentEngine
                    + "</p>"

                    + "<div style='margin-top:25px;"
                    + "padding:20px;"
                    + "background:#172554;"
                    + "border-radius:15px;"
                    + "text-align:left;'>"

                    + "<h2 style='color:#93c5fd;'>"
                    + "Execution Guidance"
                    + "</h2>"

                    + "<p>"
                    + "To execute UI automation tests : "
                    + "<b style='color:#22c55e;'>"
                    + "engine=selenium"
                    + "</b>"
                    + "</p>"

                    + "<p>"
                    + "To execute API automation tests : "
                    + "<b style='color:#06b6d4;'>"
                    + "engine=api"
                    + "</b>"
                    + "</p>"

                    + "<p>"
                    + "To execute future Playwright tests : "
                    + "<b style='color:#a855f7;'>"
                    + "engine=playwright"
                    + "</b>"
                    + "</p>"

                    + "<p>"
                    + "Update engine value inside : "
                    + "<b>config.properties</b>"
                    + "</p>"

                    + "</div>"

                    /*
                     * EXTENT REPORT BUTTON
                     */

                    + "<div style='margin-top:30px;'>"

                    + "<a href='"

                    + awsReportUrl

                    + "' target='_blank' "

                    + "style='padding:14px 24px;"
                    + "background:#22c55e;"
                    + "color:white;"
                    + "text-decoration:none;"
                    + "border-radius:10px;"
                    + "font-weight:bold;"
                    + "font-size:16px;"
                    + "display:inline-block;"
                    + "box-shadow:0px 4px 12px rgba(0,0,0,0.3);'>"

                    + "📄 View Extent Report"

                    + "</a>"

                    + "</div>"

                    + "</div>"

                    /*
                     * API TAB
                     */

                    + "<div id='api' "
                    + "class='card hidden'>"

                    + "<h2>"
                    + "API Dashboard"
                    + "</h2>"

                    + "<p>Total API Tests : "
                    + apiTests
                    + "</p>"

                    + "<p>API Passed : "
                    + apiPassed
                    + "</p>"

                    + "<p>API Failed : "
                    + apiFailed
                    + "</p>"

                    + "<p>Average API Response Time : "
                    + avgApiResponseTime
                    + " ms</p>"

                    + "<p>Execution Engine : API</p>"

                    + "<div class='chart-container'>"

                    + "<canvas id='apiChart'></canvas>"

                    + "</div>"

                    + "</div>"

                    /*
                     * CHARTS TAB
                     */

                    + "<div id='charts' "
                    + "class='card hidden'>"

                    + "<h2>"
                    + "Execution Analytics"
                    + "</h2>"

                    + "<div class='chart-container'>"

                    + "<canvas id='executionChart'></canvas>"

                    + "</div>"

                    + "</div>"

                    /*
                     * CHART SCRIPT
                     */

                    + "<script>"

                    /*
                     * EXECUTION CHART
                     */

                    + "new Chart("
                    + "document.getElementById('executionChart'),"

                    + "{"

                    + "type:'doughnut',"

                    + "data:{"

                    + "labels:['Passed','Failed','Skipped'],"

                    + "datasets:[{"

                    + "data:["
                    + passedTests
                    + ","
                    + failedTests
                    + ","
                    + skippedTests
                    + "],"

                    + "backgroundColor:["
                    + "'#22c55e',"
                    + "'#ef4444',"
                    + "'#facc15'"
                    + "]"

                    + "}]"

                    + "}"

                    + "});"

                    /*
                     * API CHART
                     */

                    + "new Chart("
                    + "document.getElementById('apiChart'),"

                    + "{"

                    + "type:'pie',"

                    + "data:{"

                    + "labels:['API Passed','API Failed'],"

                    + "datasets:[{"

                    + "data:["
                    + apiPassed
                    + ","
                    + apiFailed
                    + "],"

                    + "backgroundColor:["
                    + "'#06b6d4',"
                    + "'#ef4444'"
                    + "]"

                    + "}]"

                    + "}"

                    + "});"

                    + "</script>"

                    + "</body>"

                    + "</html>";

            /*
             * WRITE HTML
             */

            Files.write(

                    Paths.get(
                            dashboardPath),

                    html.getBytes(
                            StandardCharsets.UTF_8));

            log.info(
                    "Dashboard Generated : "
                    + dashboardPath);

            /*
             * OPEN DASHBOARD
             */

            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop()
                       .browse(

                               new File(
                                       dashboardPath)

                               .toURI());
            }

        }

        catch (Exception e) {

            log.error(
                    "Failed To Generate Dashboard",
                    e);

            throw new RuntimeException(e);
        }
    }
}