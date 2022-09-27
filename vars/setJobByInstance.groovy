def call(description){
    item = Jenkins.instance.getItemByFullName(env.JOB_NAME) 
    obj = readJSON(item)
    print(obj)
}