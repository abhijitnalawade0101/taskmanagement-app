pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'abhijeet820/project-taskmanagement-app'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS_ID = 'docker-hub-login'  // This must match the ID you used in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/abhijitnalawade0101/taskmanagement-app.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Run docker compose') {
            steps {
                sh 'docker compose up -d'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                    script {
                        sh "echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin"
                        sh "docker tag taskmanagement-app-image:latest $DOCKER_HUB_USERNAME/taskmanagement-app-image:$DOCKER_TAG"
                        sh "docker push $DOCKER_HUB_USERNAME/taskmanagement-app-image:$DOCKER_TAG"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            sh 'docker compose down'  // Always stop containers after build
            sh "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG}"  // Remove local image
        }
    }
}

