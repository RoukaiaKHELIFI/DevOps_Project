pipeline {
    agent any
    
    stages {
        
        
        stage('GIT Checkout') {
            steps {
                script {
                    git branch: 'Helmi', url: 'https://github.com/RoukaiaKHELIFI/DevOps_Project.git'
		    credentialsId: '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'
                }
            }
        }
        
        stage('MVN CLEAN') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        sh 'mvn clean'
                    }
                }
            }
        }
        
        stage('MVN COMPILE') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        sh 'mvn compile'
                    }
                }
            }
        }
        
        stage('MVN SONARQUBE') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        sh 'mvn verify sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                    }
                }
            }
        }
        
        stage('MVN TEST') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        sh 'mvn test'
                    }
                }
            }
        }
    }
}