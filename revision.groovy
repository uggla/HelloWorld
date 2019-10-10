class buildHistory {

    private String historyFile
    private String currentBuild
    private String currentCommit
    private String prevBuild
    private String prevCommit
    private def data = [:]

    buildHistory(String historyFile, String currentCommit, String currentBuild){
        this.historyFile = historyFile
        this.currentCommit = currentCommit
        this.currentBuild = currentBuild
        this.readHistory()
        this.currentBuild = this.data.max { it.keys }
        this.currentCommit = this.data.max { it.keys }.value
    }

    void readHistory() {
        if ( fileExists(this.historyFile)) {
            lock(resource: "lock_${this.historyFile}", inversePrecedence: true) {
                    this.data = readJSON(file:this.historyFile)
            }
        }
    }


    void writeHistory() {
        lock(resource: "lock_${this.historyFile}", inversePrecedence: true) {
            writeJSON(file:this.historyFile, json:this.data)
        }
    }

    void updateHistory() {
        this.data[currentBuild] = this.currentCommit
        this.writeHistory()
    }
}

def build(String historyFile, String currentCommit, String currentBuild) {
    //return(new buildHistory(historyFile, currentCommit, currentBuild));
    println("coucou")
    // h = new buildHistory(historyFile, currentCommit, currentBuild)
    // return(h);
    def data = [:]
    data["historyFile"] = historyFile
    data["currentBuild"] = currentBuild
    data["currentCommit"] = currentCommit
    data["prevBuild"] = null
    data["prevCommit"] = null
    data["data"] = readHistory(historyFile)
    return data
}

def readHistory(String historyFile) {
    def jsonData = [:]
    if ( fileExists(historyFile)) {
        lock(resource: "lock_${historyFile}", inversePrecedence: true) {
            jsonData = readJSON(file:historyFile)
        }
    }
    return jsonData
}

String writeHistory(String historyFile) {
    lock(resource: "lock_${historyFile}", inversePrecedence: true) {
        writeJSON(file:historyFile, json:data)
    }
}

return this;
