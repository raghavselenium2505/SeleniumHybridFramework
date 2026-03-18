pipeline {
    agent any

    environment {
        MAIN_BRANCH = "Feb_2026"
        FEATURE_BRANCH = "feature_auto_${new Date().format('yyyyMMddHHmmss')}"
        GIT_REPO = "https://github.com/raghavselenium2505/SeleniumHybridFramework.git"
        GITHUB_API = "https://api.github.com"
        GITHUB_CREDENTIALS = "github-token"

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

        stage('Create Feature Branch') {
            steps {
                script {
                    bat "git checkout -b ${FEATURE_BRANCH}"
                }
            }
        }

        stage('Commit Changes') {
            steps {
                script {

                    writeFile file: 'auto.txt', text: "Auto commit ${new Date()}"

                    bat '''
                    git config user.email "jenkins@local"
                    git config user.name "Jenkins"
                    git add .
                    git commit -m "Auto commit from Jenkins"
                    '''
                }
            }
        }

        stage('Push Branch') {
            steps {
                withCredentials([string(credentialsId: "github-token", variable: 'TOKEN')]) {
                    bat "git push https://${TOKEN}@github.com/raghavselenium2505/SeleniumHybridFramework.git ${FEATURE_BRANCH}"
                }
            }
        }

        stage('Create Pull Request') {
            steps {
                withCredentials([string(credentialsId: "github-token", variable: 'TOKEN')]) {

                    script {

                        def prData = """
                        {
                          "title": "Auto PR from Jenkins",
                          "head": "${FEATURE_BRANCH}",
                          "base": "${MAIN_BRANCH}",
                          "body": "Created automatically by Jenkins"
                        }
                        """

                        writeFile file: 'pr.json', text: prData

                        bat """
                        curl -X POST ^
                        -H "Authorization: token %TOKEN%" ^
                        -H "Accept: application/vnd.github.v3+json" ^
                        ${GITHUB_API}/repos/raghavselenium2505/SeleniumHybridFramework/pulls ^
                        -d @pr.json
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline executed successfully"
        }
        failure {
            echo "Pipeline failed"
        }
    }
}