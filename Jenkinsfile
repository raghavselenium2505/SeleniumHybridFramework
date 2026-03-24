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

        // ✅ NEW (from API pipeline)
        stage('Clean Old Reports') {
            steps {
                echo "Cleaning old reports..."
                bat '''
                if exist reports (
                    del /q reports\\*.html
                )
                '''
            }
        }

        // ✅ SAME UI EXECUTION
        stage('Run Selenium Tests') {
            steps {
                echo "🚀 Running Selenium UI automation"

                bat """
                set JAVA_HOME=${JAVA_HOME}
                set PATH=%JAVA_HOME%\\bin;%PATH%

                "${MAVEN_HOME}\\bin\\mvn.cmd" clean test
                """
            }
        }

        // ✅ NEW (important validation)
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

        // ✅ IMPROVED (generic + cleaner)
        stage('Keep Only Latest Report') {
            steps {
                bat '''
                cd reports
                setlocal enabledelayedexpansion

                for /f "delims=" %%i in ('dir /b /o-d *.html') do (
                    set latest=%%i
                    goto done
                )

                :done

                for %%f in (*.html) do (
                    if not "%%f"=="!latest!" del %%f
                )
                '''
            }
        }

        // ✅ FIXED (SAFE - NO SANDBOX ISSUE)
        stage('Extract Extent Summary') {
            steps {
                script {

                    def reportFile = bat(
                        script: '@dir /b reports\\*.html',
                        returnStdout: true
                    ).trim().split("\\r?\\n")[-1]

                    echo "Using Report: ${reportFile}"

                    def reportPath = "reports/${reportFile}"
                    def content = readFile(reportPath)

                    // ✅ SAFE COUNTING (no regex sandbox issue)
                    def passed = content.split("status pass").length - 1
                    def failed = content.split("status fail").length - 1
                    def skipped = content.split("status skip").length - 1

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
                <h2>Selenium UI Automation - ${currentBuild.currentResult}</h2>

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

                <p><b>Build URL:</b><br>
                <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>

                <p>📎 Latest Extent Report attached</p>

                Regards,<br>
                Jenkins
                """,

                to: "raghavendra2119818@gmail.com",
                attachmentsPattern: "reports/*.html"
            )
        }
    }
}