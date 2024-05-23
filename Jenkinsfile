pipeline {
    agent any

//    tools {
//        maven 'Maven'
//    }

    tools {
        dockerTool 'Docker'
    }

    stages {
        stage('Build') {
            steps {
//                sh "mvn clean install -DskipTests"
                sh "docker build -t rest-assured-testng ."
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
//                            sh "mvn test -P${profile}"
                            sh "docker run -v \$(pwd)/allure-results:/app/allure-results " +
                                    "-e RESTBOOKER_USERNAME -e RESTBOOKER_PASSWORD -e MAVEN_PROFILE=${profile} rest-assured-testng"
                        }
                    }
                }
            }
        }

        stage('Reports') {
            steps {
                script {
                    allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                }
            }
        }
    }
}