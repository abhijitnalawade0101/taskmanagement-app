pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'taskmanagement-app-image'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git(
                    branch: 'master',
                    url: 'https://github.com/abhijitnalawade0101/taskmanagement-app.git',
                    credentialsId: 'github-login' // Use your GitHub credential ID here
                )
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
    }
}

