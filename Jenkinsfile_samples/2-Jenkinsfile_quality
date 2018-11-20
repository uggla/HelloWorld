pipeline {
    agent any

    stages {
        stage('Build') {
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
            }
        }
        stage('Quality check') {
            steps {
                script {    
                  docker.withTool('latest') {
                    docker.image('maven:3-jdk-8-slim').inside("--network ci_cinet -v $WORKSPACE:/usr/src/myproject:rw -v $HOME/.m2:/root/.m2:rw -w /usr/src/myproject") { c ->
                      withSonarQubeEnv('sonar') {
                        sh 'mvn sonar:sonar'
                    }
                  }
                }
              }
            }
	}
    }
}
