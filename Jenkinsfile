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

        stage('Run Selenium Tests') {
            steps {
                echo "Running Selenium Tests..."

                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat """
                    set JAVA_HOME=${JAVA_HOME}
                    set PATH=%JAVA_HOME%\\bin;%PATH%

                    "${MAVEN_HOME}\\bin\\mvn.cmd" clean test
                    """
                }
            }
        }

        // 🔍 DEBUG - VERY IMPORTANT
        stage('DEBUG - Find HTML Reports') {
            steps {
                bat '''
                echo ===============================
                echo WORKSPACE LOCATION
                cd

                echo ===============================
                echo ALL HTML FILES IN WORKSPACE
                dir /s /b *.html
                echo ===============================
                '''
            }
        }

        // 🔥 GET LATEST REPORT (SAFE)
        stage('Get Latest Report') {
            steps {
                script {

                    def output = bat(
                        script: 'dir /s /b /o-d *.html',
                        returnStdout: true
                    ).trim()

                    if (!output) {
                        echo "⚠️ No report found in workspace"
                        env.REPORT_FILE = ""
                    } else {
                        def files = output.split("\\r?\\n")
                        def latestFile = files[0]

                        def workspace = env.WORKSPACE.replace("\\", "/")
                        latestFile = latestFile.replace("\\", "/").replace(workspace + "/", "")

                        env.REPORT_FILE = latestFile

                        echo "✅ Latest Report Found: ${env.REPORT_FILE}"
                    }
                }
            }
        }

        // 📊 SUMMARY (SAFE DEFAULTS)
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

                    def passPercent = total > 0 ? (passed * 100 / total) : 0
                    env.PASS_PERCENT = passPercent.toString()

                    echo "SUMMARY → Total: ${env.TOTAL}, Passed: ${env.PASSED}, Failed: ${env.FAILED}, Skipped: ${env.SKIPPED}, Pass%: ${env.PASS_PERCENT}"
                }
            }
        }
    }

    post {

        always {

            emailext(
                subject: "Automation Report | ${currentBuild.currentResult} | ${env.JOB_NAME} #${env.BUILD_NUMBER}",

                body: """
                <html>
                <body style="font-family:Segoe UI, Arial;">

                <h2 style="color:${currentBuild.currentResult == 'SUCCESS' ? '#2e7d32' : '#c62828'};">
                    Automation Execution Report - ${currentBuild.currentResult}
                </h2>

                <p><b>Project:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>

                <h3>Test Summary</h3>

                <table border="1" cellpadding="6" cellspacing="0" style="border-collapse:collapse;">
                    <tr style="background-color:#f2f2f2;">
                        <th>Total</th>
                        <th>Passed</th>
                        <th>Failed</th>
                        <th>Skipped</th>
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

                <p><b>Report:</b><br>
                ${env.REPORT_FILE ? "Attached in email" : "⚠️ Report not generated"}</p>

                <br>
                <p>Regards,<br><b>QA Automation Team</b></p>

                </body>
                </html>
                """,

                to: "raghavendra2119818@gmail.com",

                attachmentsPattern: "${env.REPORT_FILE}"
            )
        }
    }
}