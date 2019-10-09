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

def String sayTruc() {
    h = new buildHistory()
    println(h)
    return(h);
}

return this;
