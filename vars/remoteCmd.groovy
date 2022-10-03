import hudson.util.RemotingDiagnostics;

def call(groovy_cmd){
    for (slave in hudson.model.Hudson.instance.slaves) {
        println slave.name;
        println RemotingDiagnostics.executeGroovy(groovy_cmd, slave.getChannel());
    }
}