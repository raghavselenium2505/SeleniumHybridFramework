pipeline {
    agent any

    environment {
        MAIN_BRANCH = "Feb_2026"
        FEATURE_BRANCH = "feature_auto_${new Date().format('yyyyMMddHHmmss')}"
        GIT_REPO = "https://github.com/raghavselenium2505/SeleniumHybridFramework.git"
        GITHUB_API = "https://api.github.com"
        GITHUB_CREDENTIALS = "github-token"
    }

    stages {

        stage('Checkout Main Branch') {
            steps {
                cleanWs()
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${MAIN_BRANCH}"]],
                    userRemoteConfigs: [[url: "${GIT_REPO}"]]
                ])
            }
        }

        stage('Create Feature Branch') {
            steps {
                script {
                    echo "Creating new branch ${FEATURE_BRANCH}"
                    bat "git checkout -b ${FEATURE_BRANCH}"
                }
            }
        }

        stage('Make Dummy Code Change') {
            steps {
                script {
                    writeFile file: 'auto.txt', text: "Auto commit at ${new Date()}"

                    bat '''
                    git config user.email "jenkins@local"
                    git config user.name "Jenkins"
                    git add .
                    git commit -m "Auto commit from Jenkins"
                    '''
                }
            }
        }

        stage('Push Feature Branch') {
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
                          "body": "This PR was created automatically by Jenkins."
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
}