pipeline {
    agent any
    tools {
        maven 'Maven' // Ensure 'Maven' matches the Maven tool configured in Jenkins
    }

    environment {
        SONAR_HOST_URL = "http://localhost:9000" // SonarQube URL
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/soklaymeng/spring-demo.git']])
                echo 'Git Checkout Completed'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube1') { // Ensure 'SonarQube1' matches your Jenkins global tool configuration
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) { // Use the SonarQube token from Jenkins credentials
                        sh '''
                        mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=spring-demo \
                            -Dsonar.projectName="spring-demo" \
                            -Dsonar.host.url=${SONAR_HOST_URL} \
                            -Dsonar.login=${SONAR_TOKEN}  // Use the token from Jenkins credentials
                        '''
                        echo 'SonarQube Analysis Completed'
                    }
                }
            }
        }
    }
}
