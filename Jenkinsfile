pipeline {
    agent any
    tools {
        maven 'Maven'
    }

   environment {
    SONAR_HOST_URL = "http://localhost:9002"
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
                withSonarQubeEnv('sonarqube') {
                    withCredentials([string(credentialsId: 'sonarqube-token', variable: 'sonarqube')]) {
                        sh '''
                        mvn clean verify sonar:sonar \
                          -Dsonar.projectKey=spring-test \
                          -Dsonar.projectName="spring-test" \
                          -Dsonar.host.url=${SONAR_HOST_URL} \
                          -Dsonar.login=${SONAR_TOKEN}
                        '''
                        echo 'SonarQube Analysis Completed'
                    }
                }
            }
        }
    }
}
