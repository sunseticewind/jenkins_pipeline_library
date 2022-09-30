import hudson.tasks.LogRotator

def call(isConcurrentBuild, isOverwriteBD){
    def item = Jenkins.instance.getItemByFullName(env.JOB_NAME) 
    item.setConcurrentBuild(isConcurrentBuild)
    if(isOverwriteBD){
        //LogRotator(int daysToKeep, int numToKeep, int artifactDaysToKeep, int artifactNumToKeep) 
        item.setBuildDiscarder(new LogRotator(-1,100,-1,-1));
    }
    item.save()
}