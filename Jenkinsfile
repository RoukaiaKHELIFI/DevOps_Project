pipeline {
    agent any
    
    stages {
        
        
        stage('GIT') {
            steps {
                script {
                    echo " Getting Project from Git "
		    
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
         stage('Deploy Nexus') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        sh 'mvn deploy -Dnexus.username=admin -Dnexus.password=admin -DskipTests'
                    }
                }
            }
        }
      
        
    }
}
        
        
