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
	stage('Build Docker Image') {
    	  steps {
        	script {
            	    def workspace = pwd()
            	    dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                	//sh 'docker build -t helmi07/devopsimage .'
            		}
        	}
    	    }
	}

	stage('Push Docker Image to Docker Hub') {
    steps {
        script {
            def workspace = pwd()
            dir("${workspace}/DevOps_Project-main/DevOps_Project") {
                //sh 'docker login -u helmi07 -p Halminos17'
                //sh 'docker tag helmi07/devopsimage helmi07/devopsimage:latest'
                //sh 'docker push helmi07/devopsimage:latest'
            }
        }
    }
}

	stage('Docker Compose') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                        //sh 'docker compose up -d'
                    }
                }
            }
        
        }
	stage('Email Notification') {
            steps {
                script {
                    dir('DevOps_Project-main/DevOps_Project') {
                       	mail bcc: '', body: '''$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:
			Check console output at $BUILD_URL to view the results.
			This is an auto generated email . Don\'t reply''', cc: '', from: '', replyTo: '', subject: 'Jenkins steps', to: 'helmi.dridi11@gmail.com'
                    }
                }
            }
        
        }
      
        
    }
}
        
        
