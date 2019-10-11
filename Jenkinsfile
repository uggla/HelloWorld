def IMAGENAME='named-your-image'
def VMDEV='188.166.48.108'

node {
    cleanWs()
    def git = checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/ci_debug']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/uggla/HelloWorld']]]
    def myMethod = load("myMethod.groovy")
    /* def myClass = load("myClass.groovy") */
    def revision = load("revision.groovy")
    /* String build = "build-" + env.BUILD_NUMBER */
    /* def historyData = revision.build("revision.json", build , git.GIT_COMMIT) */
    /* println(historyData) */
    /* revision.updateHistory(historyData) */
    s = revision.essai()
    revision.writeData(s)
    sh "cat essai.json"
    sh "ls -al"
    sh "cat revision.json"
    println(git)
    stage('Build') {
        echo 'Hello world!'
        echo 'Coucou !'
        echo 'Welcome !'
        echo sh(script: 'env|sort', returnStdout: true)
        echo "Commit: ${git.GIT_COMMIT}"
        echo "Branch: ${git.GIT_BRANCH}"
        //println myClass.doStuff()
        /* println("buildHistory: ${myClass.sayTruc()}") */
        myMethod.sayHello()
        sh 'echo "Coucou" > bidule.txt'
        myMethod.pushFileToGit("bidule.txt")
        sh 'echo "Coucou" > machin.txt'
        myMethod.pushFileToGit("machin.txt")
        myMethod.pushFileToGit("revision.json")
        }
}
