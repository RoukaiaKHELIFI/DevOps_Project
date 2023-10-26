
pipeline {
    agent any

    stages {
        stage('GIT') {
            steps {
                echo 'Pulling...'
                git branch: 'main',
                url: 'https://github.com/RoukaiaKHELIFI/DevOps_Project.git'
            }
        }
        stage('MAVEN VERSION') {
            steps {
                sh 'mvn -version'
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
        stage('Maven Build') {
            steps {
                script {
                    def workspace = pwd()
                    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                        sh 'mvn clean package'
                    }
                }
            }
        }
       stage('Maven Test') {
            steps {
                script {
                    def workspace = pwd()
                    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                        sh 'mvn test'
                    }
                }
            }
        }
        stage('Maven SonarQube') {
    steps {
        script {
            withSonarQubeEnv('sonarqube-8.9'){
              sh 'mvn sonar:sonar'
            }
        }
    }
}
       
       }
       
    post {
        failure {
            // emailext body: 'Failure Code', subject: 'Failure', to: 'roukaia.khelifi@esprit.tn'
            echo 'Failed Execution'
        }
    }
}

