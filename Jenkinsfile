pipeline {
    agent any

    environment {
        MAIN_BRANCH = "Feb_2026"
        GIT_REPO = "https://github.com/raghavselenium2505/SeleniumHybridFramework.git"

        JAVA_HOME = "C:\\Program Files\\Java\\jdk-25.0.2"
        MAVEN_HOME = "D:\\apache-maven-3.9.14"
    }

    stages {

        stage('Checkout Code') {
            steps {
                cleanWs()
                git branch: "${MAIN_BRANCH}", url: "${GIT_REPO}"
            }
        }

        // 🔥 CLEAN OLD REPORTS
        stage('Clean Old Reports') {
            steps {
                echo "Cleaning old dashboard..."
                bat '''
                if exist dashboard (
                    del /q dashboard\\*.html
                )
                '''
            }
        }

        // 🚀 RUN TESTS (DO NOT FAIL PIPELINE)
        stage('Run Selenium Tests') {
            steps {
                echo "🚀 Running Selenium UI automation"

                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat """
                    set JAVA_HOME=${JAVA_HOME}
                    set PATH=%JAVA_HOME%\\bin;%PATH%

                    "${MAVEN_HOME}\\bin\\mvn.cmd" clean test
                    """
                }
            }
        }

        // 🔥 GET LATEST DASHBOARD (NO findFiles USED)
        stage('Get Latest Report') {
            steps {
                script {

                    def output = bat(
                        script: '''
                        if not exist dashboard (
                            echo NO_FOLDER
                        ) else (
                            dir /b /o-d dashboard\\*.html
                        )
                        ''',
                        returnStdout: true
                    ).trim()

                    if (output.contains("NO_FOLDER") || output == "") {
                        error "❌ Dashboard folder or report not found!"
                    }

                    def files = output.split("\\r?\\n")
                    def latestFile = files[0]

                    env.REPORT_FILE = "dashboard\\" + latestFile

                    echo "✅ Latest Report: ${env.REPORT_FILE}"
                }
            }
        }

        // 🔥 EXTRACT SUMMARY
        stage('Extract Summary') {
            steps {
                script {

                    env.PASSED  = env.PASSED  ?: "0"
                    env.FAILED  = env.FAILED  ?: "0"
                    env.SKIPPED = env.SKIPPED ?: "0"

                    int passed  = env.PASSED.toInteger()
                    int failed  = env.FAILED.toInteger()
                    int skipped = env.SKIPPED.toInteger()

                    int total = passed + failed + skipped
                    env.TOTAL = total.toString()

                    // ✅ PASS %
                    def passPercent = total > 0 ? (passed * 100 / total) : 0
                    env.PASS_PERCENT = passPercent.toString()

                    echo "Total: ${env.TOTAL}, Passed: ${env.PASSED}, Failed: ${env.FAILED}, Skipped: ${env.SKIPPED}, Pass%: ${env.PASS_PERCENT}"
                }
            }
        }
    }

    post {

        always {

            emailext(
                subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} [#${env.BUILD_NUMBER}]",

                body: """
                <html>
                <body style="font-family: Arial, sans-serif;">

                <h2 style="color:${currentBuild.currentResult == 'SUCCESS' ? 'green' : 'red'};">
                    Selenium Automation Report - ${currentBuild.currentResult}
                </h2>

                <p><b>Job Name:</b> ${env.JOB_NAME}</p>
                <p><b>Build ID:</b> ${env.BUILD_NUMBER}</p>

                <h3>Execution Summary</h3>

                <table border="1" cellpadding="8" cellspacing="0" style="border-collapse: collapse;">
                    <tr style="background-color:#f2f2f2;">
                        <th>Total Tests</th>
                        <th style="color:green;">Passed</th>
                        <th style="color:red;">Failed</th>
                        <th style="color:orange;">Skipped</th>
                        <th>Pass %</th>
                    </tr>
                    <tr>
                        <td align="center">${env.TOTAL}</td>
                        <td align="center" style="color:green;">${env.PASSED}</td>
                        <td align="center" style="color:red;">${env.FAILED}</td>
                        <td align="center" style="color:orange;">${env.SKIPPED}</td>
                        <td align="center"><b>${env.PASS_PERCENT}%</b></td>
                    </tr>
                </table>

                <br>

                <p><b>Build URL:</b><br>
                <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>

                <p>📎 Detailed report is attached.</p>

                <br>
                <p>Regards,<br>
                <b>Automation Team</b></p>

                </body>
                </html>
                """,

                to: "raghavendra2119818@gmail.com",
                attachmentsPattern: "${env.REPORT_FILE}"
            )
        }
    }
}