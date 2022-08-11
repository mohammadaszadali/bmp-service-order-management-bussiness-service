pipeline {
    agent any
    tools {
        maven 'mavendev'
    }
    stages{
        stage("Code checkout"){
            steps {
              git credentialsId: 'github',branch: 'main', url: 'https://github.com/mohammadaszadali/bmp-service-order-management-bussiness-service.git' 
            }
        }
        stage("Build the code") {
            steps{
                sh 'mvn clean install'
            }
        }
        stage("Deploy Artifacts"){
            steps{
                sh 'mvn deploy'
            }
        }
    }
}
