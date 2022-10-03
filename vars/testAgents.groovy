def call(){
    def rtn_msg_list = []
    msg = sh (
        script: "docker images -f \"dangling=true\" -q",
        returnStdout: true
    ).trim()
    jenkins.model.Jenkins.get().computers.each { c ->
        node{
            label "${c.node.selfLabel.name}"
        }
        when {
            expression { msg != "" && msg != null }
        }
        stage(c.node.selfLabel.name){
            rtn_msg = sh (
                script: "docker rmi -f \$(docker images -f \"dangling=true\" -q)",
                returnStdout: true
            ).trim()
            rtn_msg_list.append(rtn_msg)
        }
    }
    return rtn_msg_list
}