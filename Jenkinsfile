
pipeline {
    agent any
    tools {
        maven 'Maven'
    }

    environment {
        SONAR_HOST_URL = "http://localhost:9000" 
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
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) { // Add your SonarQube token in Jenkins Credentials
                        sh '''
                        mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=spring-demo \
                            -Dsonar.projectName="spring-demo" \
                            -Dsonar.host.url="http://localhost:9000" \
                            -Dsonar.login=squ_78ae43da673205b378582064f6f0f7e625d94fa2
                        '''
                        echo 'SonarQube Analysis Completed'
                    }
                }
            }
        }
    }
}
