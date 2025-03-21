pipeline {
    agent any
    tools {
        maven 'Maven'
    }

    environment {
        SONAR_HOST_URL = "http://localhost:9000" 
        SONAR_HOST_URL = "http://192.168.68.69:9000"
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
                    withCredentials([string(credentialsId: 'jenkins-sonar', variable: 'SONAR_TOKEN')]) { // Add your SonarQube token in Jenkins Credentials
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
