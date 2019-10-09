def sayHello() {
    println("hello !!!")
}

def pushFileToGit(String file) {
    sh 'git config --global user.email "uggla@free.fr"'
    sh 'git config --global user.name "Uggla"'
    sh 'git checkout ci_debug'
    sh "git add ${file}"
    sh 'git commit -m "new machin"'
    withCredentials([sshUserPrivateKey(credentialsId: 'uggla', keyFileVariable: 'pkey', passphraseVariable: 'passphrase', usernameVariable: 'user')]) {
        sh 'git config core.sshCommand "ssh -i ${pkey} -F /dev/null -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null"'
        sh 'git remote set-url origin git@github.com:uggla/HelloWorld.git'
        result = sh 'git push'
        println(result)
    }
}

return this;
