pipeline {
    agent any
    tools {
        maven 'Maven'
    }

<<<<<<< HEAD
     environment {
         SONAR_HOST_URL = "http://localhost:9000"
     }
=======
    environment {
        // Change to the container name or IP
        SONAR_HOST_URL = "http://localhost:9000"
    }
>>>>>>> cd62850934452884419fd2476663692966505e1c

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
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
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
