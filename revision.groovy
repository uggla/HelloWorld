import groovy.json.JsonOutput
import groovy.json.JsonSlurper
// class buildHistory {

//     private String historyFile
//     private String currentBuild
//     private String currentCommit
//     private String prevBuild
//     private String prevCommit
//     private def data = [:]

//     buildHistory(String historyFile, String currentCommit, String currentBuild){
//         this.historyFile = historyFile
//         this.currentCommit = currentCommit
//         this.currentBuild = currentBuild
//         this.readHistory()
//         this.currentBuild = this.data.max { it.keys }
//         this.currentCommit = this.data.max { it.keys }.value
//     }

//     void readHistory() {
//         if ( fileExists(this.historyFile)) {
//             lock(resource: "lock_${this.historyFile}", inversePrecedence: true) {
//                     this.data = readJSON(file:this.historyFile)
//             }
//         }
//     }


//     void writeHistory() {
//         lock(resource: "lock_${this.historyFile}", inversePrecedence: true) {
//             writeJSON(file:this.historyFile, json:this.data)
//         }
//     }

//     void updateHistory() {
//         this.data[currentBuild] = this.currentCommit
//         this.writeHistory()
//     }
// }
def build(String historyFile, String currentBuild, String currentCommit) {
    //return(new buildHistory(historyFile, currentCommit, currentBuild));
    println("coucou")
    // h = new buildHistory(historyFile, currentCommit, currentBuild)
    // return(h);
    def historyData = [:]
    historyData["historyFile"] = historyFile
    historyData["currentBuild"] = currentBuild
    historyData["currentCommit"] = currentCommit
    historyData["prevBuild"] = null
    historyData["prevCommit"] = null
    historyData["records"] = null
    historyData["records"] = readHistory(historyData)
    if (historyData["records"]) {
        // def data = JsonOutput.toJson(historyData["records"])
        // def realmap = new JsonSlurper().parseText(data)
        println(historyData["records"].getClass())
        // historyData["prevBuild"] = realmap.max {it.key}
        // historyData["prevCommit"] = realmap.max {it.key}.value
    }
    return historyData
}

def readHistory(Map historyData) {
    def jsonData = null
    if ( fileExists(historyData["historyFile"])) {
        lock(resource: "lock_${historyData["historyFile"]}", inversePrecedence: true) {
            jsonData = readJSON(file:historyData["historyFile"])
        }
    }
    else {
        jsonData = readJSON(text: '{}')
    }
    return jsonData
}

@NonCPS
def convert(Map json) {
        def data = JsonOutput.toJson(json)
        def realmap = new JsonSlurper().parseText(data)
        return realmap
}

def writeHistory(Map historyData) {
    lock(resource: "lock_${historyData["historyFile"]}", inversePrecedence: true) {
        println(historyData)
        writeJSON(file:historyData["historyFile"], json:historyData["records"])
    }
}

def updateHistory(Map historyData) {
    historyData["records"].put(historyData["currentBuild"], historyData["currentCommit"])
    writeHistory(historyData)
}

@NonCPS
def essai() {
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText '''     { "simple": 123,       "fraction": 123.66,       "exponential": 123e12     }'''
    assert object instanceof Map
    assert object.simple == 123
    println(object.max {it.key})
}
// def updateHistory(String toto) {
//     println(toto)
// }

return this;
