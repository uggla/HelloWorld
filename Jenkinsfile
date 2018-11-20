pipeline {
    agent any

    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
                echo 'Coucou !' 
                echo 'Welcome !'
                script {    
                  docker.withTool('latest') {
                    docker.image('maven:3-jdk-8-slim').inside("-v $WORKSPACE:/usr/src/myproject:rw -v $HOME/.m2:/root/.m2:rw -w /usr/src/myproject") { c ->
                      sh "mvn clean package"
                    }
                  }
                }
                sh "docker tag maven:3-jdk-8-slim registry.uggla.fr/maven:3-jdk-8-slim"
                sh "docker push registry.uggla.fr/maven:3-jdk-8-slim"
            }
        }
    }
}
