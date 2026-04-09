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

        // 🔥 CLEAN DASHBOARD ONLY
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

        // 🚀 RUN TESTS
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

        // 🔥 VALIDATE DASHBOARD
        stage('Validate Dashboard') {
            steps {
                bat '''
                if not exist dashboard\\AutomationDashboard.html (
                    echo ❌ Dashboard not generated!
                    exit 1
                )
                '''
            }
        }

        // 🔥 EXTRACT SUMMARY (OPTIONAL SAFE DEFAULT)
        stage('Extract Summary') {
            steps {
                script {

                    // If your framework sets env variables → use them
                    env.PASSED = env.PASSED ?: "0"
                    env.FAILED = env.FAILED ?: "0"
                    env.SKIPPED = env.SKIPPED ?: "0"

                    def total = env.PASSED.toInteger() +
                                env.FAILED.toInteger() +
                                env.SKIPPED.toInteger()

                    env.TOTAL = total.toString()

                    echo "Total: ${env.TOTAL}, Passed: ${env.PASSED}, Failed: ${env.FAILED}, Skipped: ${env.SKIPPED}"
                }
            }
        }
    }

    post {

        always {

            emailext(
                subject: "${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",

                body: """
                <h2>Selenium Automation - ${currentBuild.currentResult}</h2>

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

                <p>📎 Dashboard attached</p>

                Regards,<br>
                Jenkins
                """,

                to: "raghavendra2119818@gmail.com",

                // 🔥 ATTACH DASHBOARD ONLY
                attachmentsPattern: "dashboard/AutomationDashboard.html"
            )
        }
    }
}