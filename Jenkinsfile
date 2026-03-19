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

        always {

            echo "Sending Email with Extent Report"

            emailext(
                subject: "${env.BUILD_STATUS}: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: '''
                Hello Team,

                Please find the attached automation execution report.

                Job Name: ${JOB_NAME}
                Build Number: ${BUILD_NUMBER}
                Status: ${BUILD_STATUS}

                Build URL:
                ${BUILD_URL}

                Regards,
                Jenkins
                ''',
                to: "raghavendra2119818@gmail.com",

                attachmentsPattern: "test-output/**/*.html"
            )
        }
    }
}