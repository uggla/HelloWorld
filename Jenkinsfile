pipeline {
    agent {
        docker { image 'maven:3-jdk-8-slim' }
    }

    tools{
        docker 'latest'
    }

    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
                echo 'Coucou !' 
                echo 'Welcome !'
                sh 'mvn clean package'
            }
        }
    }
}
