def IMAGENAME='named-your-image'
def VMDEV='188.166.48.108'

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
        stage('Package app') {
            steps {
                script {    
                  docker.withTool('latest') {
                    docker.withRegistry('https://registry.uggla.fr/') {
                      def myImg = docker.build("${IMAGENAME}:${env.BUILD_ID}")
                      myImg.push()
                    }
                  }
                }
            }
        }
        stage('Deploy app') {
            steps {
                script {    
                  withCredentials([sshUserPrivateKey(credentialsId:'ssh', keyFileVariable:'pkey', passphraseVariable:'', usernameVariable:'user')]){
                    def remote = [:]
                    remote.name = "vmdev"
                    remote.host = VMDEV
                    remote.allowAnyHosts = true
                    remote.user = user
                    remote.identyFile = pkey
                    sshCommand remote: remote, command: "docker run -ti registry.uggla.fr/${IMAGENAME}:${env.BUILD_ID}"
	          }
                }
            }
        }
    }
}
