pipeline {
    agent {
      any
    }

    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
                echo 'Coucou !' 
                echo 'Welcome !'
                script {    
                  docker.withTool('latest') {
                    docker.image('maven:3-jdk-8-slim').inside("-v $WORKSPACE:/usr/src/myproject -w /usr/src/myproject") { c ->
                      sh "mvn clean package"
                    }
                  }
                }
            }
        }
    }
}
