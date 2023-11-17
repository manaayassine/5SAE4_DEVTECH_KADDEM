pipeline {
    agent any

stages {
    stage('Git Checkout') {
                    steps {

                        checkout scm
                    }
                }
        stage('Nettoyage et compilation Maven') {
            steps {
                // Cette étape va nettoyer et compiler le projet avec Maven
                sh 'mvn clean compile'
            }
        }
    stage('SonarQube analyse') {
                                 steps {
                                    script {
                                        withSonarQubeEnv(installationName: 'sonar') {
                                            sh 'mvn sonar:sonar'
                                        }
                                    }
                                  }
                             }
    stage('Junit test') {
            steps {
               script {
                       sh 'mvn test'
                      }
                 }
           }
     stage('Nexus') {
            steps {
                script {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }
    }
}
