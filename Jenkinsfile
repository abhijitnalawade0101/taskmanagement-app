pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'taskmanagement-app-image'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS_ID = 'docker-hub-login'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Run Containers using Docker Compose') {
            steps {
                script {
                    sh 'docker compose up -d'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDENTIALS_ID}", usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                    script {
                        sh "echo \$DOCKER_HUB_PASSWORD | docker login -u \$DOCKER_HUB_USERNAME --password-stdin"
                        sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} \$DOCKER_HUB_USERNAME/${DOCKER_IMAGE}:${DOCKER_TAG}"
                        sh "docker push \$DOCKER_HUB_USERNAME/${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up containers, images, and volumes...'
            script {
                // Stop and remove containers and volumes created by docker-compose
                sh 'docker compose down --volumes'

                // Remove all images
                sh "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG} || true"

                // Remove all stopped containers (optional)
                sh 'docker container prune -f || true'

                // Remove unused volumes (optional)
                sh 'docker volume prune -f || true'
            }
        }
    }
}

