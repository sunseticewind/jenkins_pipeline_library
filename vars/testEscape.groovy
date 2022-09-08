def call(server_ip, exec_command) {
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        withCredentials([sshUserPrivateKey(
            credentialsId: server_ip, 
            keyFileVariable: 'SSH_KEY',
            usernameVariable: 'SSH_USER')]) 
            {
                def filePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
                def folderPathList = filePath.split("/")
                def folderPath = ""
                for(int i=0;i<folderPathList.size()-2;i++){
                    folderPath+=folderPathList[i]+"/"
                }
                sh "chmod +x ${folderPath}resources/org/foo/testEscape.sh"
                writeFile file: "command.sh", text: exec_command, encoding: "UTF-8"
                def SSH_COMMAND = "${folderPath}resources/org/foo/testEscape.sh '${SSH_KEY}' ${SSH_USER} ${server_ip} ${env.BUILD_NUMBER}"
                
                rtnStatus = sh (
                    script: SSH_COMMAND,
                    returnStatus: true
                )
                
                rtnMsg = readFile "${env.WORKSPACE}/${env.BUILD_NUMBER}.txt"
                rtnMsg = rtnMsg.trim()
                println(rtnMsg)
                
                return [rtnStatus, rtnMsg]
            }
    }
}