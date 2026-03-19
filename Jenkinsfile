pipeline {
    agent any

    environment {
        MAIN_BRANCH = "Feb_2026"
        FEATURE_BRANCH = "feature_auto_${new Date().format('yyyyMMddHHmmss')}"
        GIT_REPO = "https://github.com/raghavselenium2505/SeleniumHybridFramework.git"
        GITHUB_API = "https://api.github.com"

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
                echo "Running Selenium TestNG automation"

                bat '''
                set JAVA_HOME=C:\\Program Files\\Java\\jdk-25.0.2
                set PATH=%JAVA_HOME%\\bin;%PATH%
                "D:\\apache-maven-3.9.14\\bin\\mvn.cmd" clean test
                '''
            }
        }
    }

    post {

        success {
            emailext(
                subject: "SUCCESS: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                <h2>Automation Execution - SUCCESS ✅</h2>

                <p><b>Job:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Status:</b> SUCCESS</p>

                <p><b>Build URL:</b><br>
                <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>

                <p>Please find the Extent Report attached.</p>

                Regards,<br>
                Jenkins
                """,
                to: "raghavendra2119818@gmail.com",
                attachmentsPattern: "**/ExtentReport.html"
            )
        }

        failure {
            emailext(
                subject: "FAILURE: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                <h2>Automation Execution - FAILURE ❌</h2>

                <p><b>Job:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Status:</b> FAILURE</p>

                <p><b>Build URL:</b><br>
                <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>

                <p>Please check the attached Extent Report.</p>

                Regards,<br>
                Jenkins
                """,
                to: "raghavendra2119818@gmail.com",
                attachmentsPattern: "**/ExtentReport.html"
            )
        }
    }
}