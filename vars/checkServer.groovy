import groovy.json.*

def call(){
    json_str = libraryResource 'org/foo/checkServerList.json'
    json_arr = readJSON(json_str)
    failed_list = []
    for(json_obj in json_arr){
        for (port in json_obj["PORT"]){
            try{
                cmd = "nc -vz ${json_obj["HOST"]} ${port} -w 1"
                sh (cmd)
            }catch(Exception e){
                is_failed=true
                failed_list.add(json_obj["HOST"]+":"+port)
            }
        }
        
    }
    println(failed_list)
}