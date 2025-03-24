pipeline {
    agent any
    tools {
        maven 'Maven'
    }

   environment {
    SONAR_HOST_URL = "http://localhost:9000"
    SONAR_TOKEN = "squ_78ae43da673205b378582064f6f0f7e625d94fa2"
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
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'sonarqube')]) {
                        sh '''
                        mvn clean verify sonar:sonar \
                          -Dsonar.projectKey=spring-demo \
                          -Dsonar.projectName="spring-demo" \
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
