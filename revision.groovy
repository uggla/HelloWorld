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

def build(String historyFile, String currentCommit, String currentBuild) {
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
    historyData["records"] = [:]
    historyData["records"] = readHistory(historyData)
    return historyData
}

def readHistory(Map historyData) {
    def jsonData = [:]
    if ( fileExists(historyData["historyFile"])) {
        lock(resource: "lock_${historyData["historyFile"]}", inversePrecedence: true) {
            jsonData = readJSON(file:historyData["historyFile"])
        }
    }
    return jsonData
}

def writeHistory(Map historyData) {
    lock(resource: "lock_${historyData["historyFile"]}", inversePrecedence: true) {
        writeJSON(file:historyFile, json:historyData["records"])
    }
}

def updateHistory(Map historyData) {
    historyData["records"].put(historyData["currentBuild"], historyData["currentCommit"])
    writeHistory(historyData["historyFile"])
}

// def updateHistory(String toto) {
//     println(toto)
// }

return this;
