pipeline {
    agent any

    environment {
        MAIN_BRANCH = "Feb_2026_API"
        GIT_REPO = "https://github.com/raghavselenium2505/SeleniumHybridFramework.git"

        JAVA_HOME = "C:\\Program Files\\Java\\jdk-25.0.2"
        MAVEN_HOME = "D:\\apache-maven-3.9.14"
    }

    stages {

        // 🔥 CHECKOUT CODE
        stage('Checkout Code') {
            steps {
                cleanWs()
                git branch: "${MAIN_BRANCH}", url: "${GIT_REPO}"
            }
        }

        // 🔥 DELETE OLD REPORTS
        stage('Clean Old Reports') {
            steps {
                echo "Deleting old reports..."
                bat '''
                if exist reports (
                    del /q reports\\*.html
                )
                '''
            }
        }

        // 🔥 RUN API TESTS
        stage('Run API Tests') {
            steps {
                echo "Running API tests using testngAPI.xml"

                bat """
                set JAVA_HOME=${JAVA_HOME}
                set PATH=%JAVA_HOME%\\bin;%PATH%

                "${MAVEN_HOME}\\bin\\mvn.cmd" clean test ^
                -DsuiteXmlFile=src/test/resources/runner/testngAPI.xml
                """
            }
        }

        // 🔥 VALIDATE REPORT
        stage('Validate Report') {
            steps {
                bat '''
                if not exist reports\\*.html (
                    echo ❌ No report generated!
                    exit 1
                )
                '''
            }
        }

        // 🔥 FINAL FIXED SUMMARY STAGE
        stage('Extract Extent Summary') {
            steps {
                script {

                    def reportFile = bat(
                        script: '@dir /b reports\\*.html',
                        returnStdout: true
                    ).trim().split("\r?\n")[-1]

                    echo "Report File Detected: ${reportFile}"

                    def reportPath = "reports/${reportFile}"

                    def content = readFile(reportPath)

                    def passed = (content =~ /status pass/).count
                    def failed = (content =~ /status fail/).count
                    def skipped = (content =~ /status skip/).count

                    def total = passed + failed + skipped

                    env.PASSED = passed.toString()
                    env.FAILED = failed.toString()
                    env.SKIPPED = skipped.toString()
                    env.TOTAL = total.toString()

                    echo "Total: ${total}, Passed: ${passed}, Failed: ${failed}, Skipped: ${skipped}"
                }
            }
        }
    }

    post {
        always {
            emailext(
                subject: "${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",

                body: """
                <h2>API Automation Execution - ${currentBuild.currentResult}</h2>

                <p><b>Job:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>

                <h3>Test Summary 📊</h3>
                <table border="1" cellpadding="5">
                <tr>
                    <th>Total</th>
                    <th style="color:green;">Passed</th>
                    <th style="color:red;">Failed</th>
                    <th style="color:orange;">Skipped</th>
                </tr>
                <tr>
                    <td>${env.TOTAL}</td>
                    <td style="color:green;">${env.PASSED}</td>
                    <td style="color:red;">${env.FAILED}</td>
                    <td style="color:orange;">${env.SKIPPED}</td>
                </tr>
                </table>

                <p><a href="${env.BUILD_URL}">View Build</a></p>
                """,

                to: "raghavendra2119818@gmail.com",

                attachmentsPattern: "reports/*.html"
            )
        }
    }
}