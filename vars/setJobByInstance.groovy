import hudson.tasks.LogRotator

def call(){
    def item = Jenkins.instance.getItemByFullName(env.JOB_NAME) 
    item.setConcurrentBuild(false)
    // item.setDisabled(true)
    item.setBuildDiscarder(new LogRotator(-1,100,-1,-1));
    item.save()
}