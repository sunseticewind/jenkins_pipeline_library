import groovy.json.*

def call(){
    json_str = libraryResource 'org/foo/checkServerList.json'
    json_arr = readJson(json_str)
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