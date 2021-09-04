def generateAllure() {
    allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
}

pipeline {

    agent { label 'master' }

    stages {
        stage('initialize') {
            steps {
                script {
                    checkout scm
                    mvnHome = tool 'maven3.6.2'
                }
            }
        }

        stage('Pet Store Tests') {
            steps {
                script {
                    def myCommand = "$MVN_HOME/bin/mvn clean test -Dtest=\"src/test/java/services/petStore/\""
                    sh(myCommand)
                }
            }
        }
        post {
            always {
                generateAllure()
            }
        }
    }
}
