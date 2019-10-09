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

return new buildHistory();
