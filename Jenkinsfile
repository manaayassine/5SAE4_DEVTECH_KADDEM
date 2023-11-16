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
}
