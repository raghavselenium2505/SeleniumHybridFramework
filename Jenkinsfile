pipeline {
    agent any

    environment {
        MAIN_BRANCH = "Feb_2026"
        GIT_REPO = "https://github.com/raghavselenium2505/SeleniumHybridFramework.git"

        JAVA_HOME = "C:\\Program Files\\Java\\jdk-25.0.2"
        MAVEN_HOME = "D:\\apache-maven-3.9.14"

        // 🔥 S3 CONFIG
        S3_BUCKET = "your-bucket-name"
        AWS_REGION = "ap-south-1"
    }

    stages {

        stage('Checkout Code') {
            steps {
                cleanWs()
                git branch: "${MAIN_BRANCH}", url: "${GIT_REPO}"
            }
        }

        // 🚀 RUN TESTS
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

        // 🔥 GET LATEST REPORT
        stage('Get Latest Report') {
            steps {
                script {

                    def reportDir = "src\\test\\resources\\Reports\\DashBoard"

                    def output = bat(
                        script: """
                        if not exist "${reportDir}" (
                            echo NO_FOLDER
                        ) else (
                            dir /b /o-d "${reportDir}\\*.html"
                        )
                        """,
                        returnStdout: true
                    ).trim()

                    if (output.contains("NO_FOLDER") || output == "") {
                        error "❌ No report found!"
                    }

                    def files = output.split("\\r?\\n")
                    def latestFile = files[0]

                    env.REPORT_FILE = "${reportDir}\\" + latestFile
                    env.REPORT_NAME = latestFile

                    echo "✅ Report: ${env.REPORT_FILE}"
                }
            }
        }

        // ☁️ UPLOAD TO S3
        stage('Upload to S3') {
            steps {
                script {

                    def s3Path = "reports/${env.JOB_NAME}/${env.BUILD_NUMBER}/${env.REPORT_NAME}"

                    bat """
                    aws s3 cp "${env.REPORT_FILE}" s3://${S3_BUCKET}/${s3Path} --region ${AWS_REGION}
                    """

                    env.S3_URL = "https://${S3_BUCKET}.s3.${AWS_REGION}.amazonaws.com/${s3Path}"

                    echo "✅ S3 URL: ${env.S3_URL}"
                }
            }
        }

        // 📊 SUMMARY
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
                <body style="font-family: Arial;">

                <h2 style="color:${currentBuild.currentResult == 'SUCCESS' ? 'green' : 'red'};">
                    Selenium Automation Report - ${currentBuild.currentResult}
                </h2>

                <p><b>Job Name:</b> ${env.JOB_NAME}</p>
                <p><b>Build ID:</b> ${env.BUILD_NUMBER}</p>

                <h3>Execution Summary</h3>

                <table border="1" cellpadding="8">
                    <tr>
                        <th>Total</th>
                        <th style="color:green;">Passed</th>
                        <th style="color:red;">Failed</th>
                        <th style="color:orange;">Skipped</th>
                        <th>Pass %</th>
                    </tr>
                    <tr>
                        <td>${env.TOTAL}</td>
                        <td style="color:green;">${env.PASSED}</td>
                        <td style="color:red;">${env.FAILED}</td>
                        <td style="color:orange;">${env.SKIPPED}</td>
                        <td><b>${env.PASS_PERCENT}%</b></td>
                    </tr>
                </table>

                <br>

                <p><b>📊 View Report:</b><br>
                <a href="${env.S3_URL}">${env.S3_URL}</a></p>

                <br>
                <p>Regards,<br><b>Automation Team</b></p>

                </body>
                </html>
                """,

                to: "raghavendra2119818@gmail.com",
                attachmentsPattern: "${env.REPORT_FILE}"
            )
        }
    }
}