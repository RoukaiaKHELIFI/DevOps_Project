pipeline {
    agent any

      stages {
        stage('GIT Checkout') { steps { script { echo 'getting from git' } } }
        

          stage('Maven Clean') {
            steps {
                script {
                    def workspace = pwd()
                    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                        sh 'mvn clean'
                    }
                }
            }
        }
        stage('Maven Compile') {
            steps {
                script {
                    def workspace = pwd()
                    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                        sh 'mvn compile'
                    }
                }
            }
        }
         
         stage('Maven SonarQube') {
    steps {
        script {
            def workspace = pwd()
            dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                
                    sh 'mvn verify sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
                
            }
        }
    }
         }
         stage('Deploy Nexus') {
            steps {
                script {
                    def workspace = pwd()
                    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                        sh "mvn deploy -Dnexus.username=admin -Dnexus.password=admin -DskipTests"
                    }
                }
            }
        }
         
        
    }
}