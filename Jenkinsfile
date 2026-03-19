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

        always {
            emailext(
                subject: "${currentBuild.currentResult}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
                <h2>Automation Execution - ${currentBuild.currentResult}</h2>

                <p><b>Job:</b> ${env.JOB_NAME}</p>
                <p><b>Build Number:</b> ${env.BUILD_NUMBER}</p>

                <p><b>Build URL:</b><br>
                <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>

                <p>Automation Report attached.</p>

                Regards,<br>
                Jenkins
                """,
                to: "raghavendra2119818@gmail.com",

                // ✅ Matches your dynamic timestamp format
                attachmentsPattern: "reports/AutomationReport_*.html"
            )
        }
    }
}