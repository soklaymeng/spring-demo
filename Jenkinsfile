pipeline {
    agent any

    tools {
        maven 'Maven' // Make sure this matches your Jenkins Maven installation name
    }

    environment {
        SONAR_HOST_URL = "http://localhost:9000"
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], 
                                extensions: [], 
                                userRemoteConfigs: [[url: 'https://github.com/soklaymeng/spring-demo.git']])
                echo 'Git Checkout Completed'
            }
        }

        stage('Build & SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube1') { // Ensure this matches your SonarQube configuration in Jenkins
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) { // Use Jenkins credentials
                        sh '''
                        ls -la # List files in the workspace to debug directory issue
                        if [ ! -f "pom.xml" ]; then
                            echo "pom.xml not found in $(pwd), checking subdirectories..."
                            find . -name "pom.xml"
                        fi
                        
                        mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=spring-demo \
                            -Dsonar.projectName="spring-demo" \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.login=$SONAR_TOKEN
                        '''
                        echo 'SonarQube Analysis Completed'
                    }
                }
            }
        }

    }
}
