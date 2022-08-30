def call(server_ip, exec_command) {
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        withCredentials([sshUserPrivateKey(
            credentialsId: server_ip, 
            keyFileVariable: 'SSH_KEY',
            usernameVariable: 'SSH_USER')]) 
            {
                // def filePath = this.class.classLoader.getResourceLoader().loadGroovySource(this.class.name).toURI().toString()
                def filePath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()
                def folderPathList = filePath.split("/")
                def folderPath = ""
                for(int i=0;i<folderPathList.size()-2;i++){
                    folderPath+=folderPathList[i]
                }
                def SSH_COMMAND = """./var/lib/jenkins/jobs/${env.JOB_NAME}/builds/${env.BUILD_ID}/libs/bb0229ee42451e1589df9f49cda70c2d8477facdc462c762fcdcd9b160a6b8a2/resources/org/foo/testEscape.sh ${SSH_KEY} ${SSH_USER} ${server_ip} '${exec_command}' ${env.BUILD_ID}"""
                
                rtnStatus = sh (
                    script: SSH_COMMAND,
                    returnStatus: true
                )
                
                rtnMsg = sh (
                    script: "cat ${env.BUILD_ID}.txt",
                    returnStdout: true
                ).trim()
                
                return [rtnStatus, rtnMsg]
            }
    }
}