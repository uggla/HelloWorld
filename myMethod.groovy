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
        try {
            result = sh(script: 'set +e ; git push', returnStdout:true).trim()
        }
        catch (Exception ex){
            println("Here")
            println(result)
        }
    }
}

return this;
