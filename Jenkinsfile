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
	stage('Build Docker Image') {
    	  steps {
        	script {
            	    def workspace = pwd()
            	    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                	sh 'docker build -t skanderbelhassen/devopsimage .'
            		}
        	}
    	    }
	}

stage('Push Docker Image to Docker Hub') {
    steps {
        script {
            def workspace = pwd()
            dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                sh 'docker login -u skanderbelhassen -p 191JMT1928a'
                sh 'docker tag skanderbelhassen/devopsimage skanderbelhassen/devopsimage:latest'
                sh 'docker push skanderbelhassen/devopsimage:latest'
            }
        }
    }
}

stage('Docker Compose') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        sh 'docker compose up -d'
                    }
                }
            }
        
        }
	stage('Send Email') {
            steps {
                script {
                    emailext body: 'Voici le rapport de votre build.', 
                             subject: 'Rapport de build Jenkins', 
                             to: 'skander.belhassen@esprit.tn',
                             attachLog: true, 
                             mimeType: 'text/html',
                             from: 'projecttest425@gmail.com'
                }
            }
        }
stage('grafana') {
            steps {
                sh "docker restart prometheus"
                sh "docker restart grafana"
            }
        } 

         
        
    }
}