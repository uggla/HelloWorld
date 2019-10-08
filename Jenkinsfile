def IMAGENAME='named-your-image'
def VMDEV='188.166.48.108'

node {
    def myMethod = load("myMethod.groovy")
    stage('Build') {
        echo 'Hello world!' 
        echo 'Coucou !' 
        echo 'Welcome !'
        echo sh(script: 'env|sort', returnStdout: true)
        echo "Commit: ${env.GIT_COMMIT}"
        h = new buildHistory()
        println(h)
        myMethod.commitPush()
        sh 'git config --global user.email "uggla@free.fr"'
        sh 'git config --global user.name "Uggla"'
        sh 'git checkout ci_debug'
        sh "echo toto>truc"
        sh "git add truc"
        sh 'git commit -m "machin"'
        withCredentials([sshUserPrivateKey(credentialsId: 'uggla', keyFileVariable: 'pkey', passphraseVariable: 'passphrase', usernameVariable: 'user')]) {
            sh 'git config core.sshCommand "ssh -i ${pkey} -F /dev/null -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null"'
            sh 'git remote set-url origin git@github.com:uggla/HelloWorld.git'
            sh 'git push'

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
