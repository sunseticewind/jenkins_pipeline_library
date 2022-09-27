def call(){
    def item = Jenkins.instance.getItemByFullName(env.JOB_NAME) 
    item.setConcurrentBuild(true)
    item.setDisabled(true)
    item.save()
}