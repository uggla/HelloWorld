class buildHistory {

    String commit

    buildHistory(){
        this.commit = "bla"
    }

    @NonCPS
    public String toString() {
        return this.commit
    }

    public def doStuff() {
        return "HI"
    }
}

def sayTruc() {
    h = new buildHistory()
    println(h)
}

return this;
