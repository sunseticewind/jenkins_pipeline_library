def call(exec_command){
    jenkins.model.Jenkins.get().computers.each { c ->
        node{
            label "${c.node.selfLabel.name}"
        }
        stage(c.node.selfLabel.name){
            sh exec_command
        }
    }
}