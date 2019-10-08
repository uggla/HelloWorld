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
                echo sh(script: 'env|sort', returnStdout: true)
                echo env.GIT_COMMIT
            }
        }
    }
}
