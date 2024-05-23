pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Test') {
            steps {
                script {
                    catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                        withCredentials([usernamePassword(
                                credentialsId: 'REST_BOOKER_CREDS',
                                usernameVariable: 'RESTBOOKER_USERNAME',
                                passwordVariable: 'RESTBOOKER_PASSWORD'
                        )]) {
                            sh "mvn test -Pregression"
                        }
                    }
                }
            }
        }

        stage('Reports') {
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }
}