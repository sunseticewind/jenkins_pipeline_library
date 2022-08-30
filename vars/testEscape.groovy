def call(server_ip, exec_command) {
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        withCredentials([sshUserPrivateKey(
            credentialsId: server_ip, 
            keyFileVariable: 'SSH_KEY',
            usernameVariable: 'SSH_USER')]) 
            {
                
                def SSH_COMMAND = "./${libraryResource('resources/org/foo/testEscape.sh')} ${SSH_KEY} ${SSH_USER} ${server_ip} ${exec_command} ${env.BUILD_ID}"
                
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