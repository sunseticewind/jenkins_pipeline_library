def call(){
    def item = Jenkins.instance.getItemByFullName(env.JOB_NAME) 
    item.setConcurrentBuild(false)
    item.setDisabled(true)
    item.save()
}