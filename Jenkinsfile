pipeline {
    agent any
    tools {
        maven 'Maven' // Ensure 'Maven' matches the Maven tool configured in Jenkins
    }

    environment {
        SONAR_HOST_URL = "http://192.168.68.69:9000" // Use the correct IP address
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
                            -Dsonar.login=${SONAR_TOKEN}  
                        '''
                        echo 'SonarQube Analysis Completed'
                    }
                }
            }
        }
    }
}
