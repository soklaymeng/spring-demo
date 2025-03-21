ipeline {
    agent any 
    
    stages { 
        stage('SCM Checkout') {
            steps{
           git branch: 'main', url: 'https://github.com/soklaymeng/spring-demo.git'
            }
        }
        // run sonarqube test
        stage('Run Sonarqube') {
            environment {
                scannerHome = tool 'SonarQube1';
            }
            steps {
              withSonarQubeEnv(credentialsId: 'sonar-token', installationName: 'spring-demo') {
                sh "${scannerHome}/bin/sonar-scanner"
              }
            }
        }
    }
}
