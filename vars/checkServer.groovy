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
    if (failed_list.size()>0){
        sender = "SEG_JENKINS@htc.com"
        receivers = "SEG_DEVOPS@htc.com"
        subject = "$JOB_NAME - Build # $BUILD_NUMBER - Failed!"
        content = "$JOB_NAME - Build # $BUILD_NUMBER - Failed: Check console output at $BUILD_URL to view the results. Pre-check server on ${failed_list} failed."
        // SendMail(sender, receivers, subject, content)
        error("Pre-check server on ${failed_list} failed.")
    }
}