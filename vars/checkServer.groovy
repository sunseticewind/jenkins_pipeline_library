def call(){
    pre_check_service.each{
        k,v->
        ports = v.split(',')
        for (String p : ports){
            cmd = "nc -vz ${k} ${p} -w 1"
            sh (cmd)
        }
    }
    json_str = libraryResource 'org/foo/checkServerList.json'
    jsonSlurper = new JsonSlurper()
    json_arr = jsonSlurper.parseText(json_str)
    failed_list = []
    for(json_obj in json_arr){
        try{
            for (port in json_obj["PORT"]){
                cmd = "nc -vz ${json_obj["HOST"]} ${port} -w 1"
                sh (cmd)
            }
        }catch(Exception e){
            is_failed=true
            failed_list.add(json_obj["HOST"])
        }
    }
    return failed_list
}