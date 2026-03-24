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

            // 🔥 SAFE COUNT (NO SANDBOX ISSUE)
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