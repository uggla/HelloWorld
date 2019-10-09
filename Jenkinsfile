def IMAGENAME='named-your-image'
def VMDEV='188.166.48.108'

node {
    cleanWs()
    def git = checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/ci_debug']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/uggla/HelloWorld']]]
    def myMethod = load("myMethod.groovy")
    def myClass = load("myClass.groovy")
    println(git)
    stage('Build') {
        echo 'Hello world!'
        echo 'Coucou !'
        echo 'Welcome !'
        echo sh(script: 'env|sort', returnStdout: true)
        echo "Commit: ${git.GIT_COMMIT}"
        echo "Branch: ${git.GIT_BRANCH}"
        //println myClass.doStuff()
        t = new myClass()
        myMethod.sayHello()
        sh 'echo "Coucou" > bidule.txt'
        myMethod.pushFileToGit("bidule.txt")
        }
}
