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
                echo "Commit: ${env.GIT_COMMIT}"
                script {
                    h = new buildHistory()
                    println(h)
                }
                sh "echo toto>truc"
                sh "git add truc"
                sh 'git commit -m "machin"'
                sh 'git config --global user.email "uggla@free.fr"'
                sh 'git config --global user.name "Uggla"'
                sh 'git push'
            }
        }
    }
}

class buildHistory {

    String commit

    buildHistory(){
        this.commit = "bla"
    }

    @NonCPS
    public String toString() {
        return this.commit
    }

}
