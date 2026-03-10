pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/ZoroHunter-007/MicroServiceProject.git'
            }
        }

        stage('Build Auth Application') {
            steps {
                dir('AuthApplication') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Product Application') {
            steps {
                dir('ProductApplication') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Stop Old Containers') {
            steps {
                bat 'docker compose down --remove-orphans || exit 0'
            }
        }

        stage('Run Docker Compose') {
            steps {
                bat 'docker compose up -d --build'
            }
        }
    }
}