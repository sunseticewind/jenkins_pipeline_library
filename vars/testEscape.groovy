import java.nio.file.Path; 
import java.nio.file.Paths; 

def call(server_ip, exec_command) {
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        withCredentials([sshUserPrivateKey(
            credentialsId: server_ip, 
            keyFileVariable: 'SSH_KEY',
            usernameVariable: 'SSH_USER')]) 
            {
                def filePath = this.class.classLoader.getResourceLoader().loadGroovySource(this.class.name).toURI()
                def SSH_COMMAND = "./${filePath}/../../org/foo/testEscape.sh ${SSH_KEY} ${SSH_USER} ${server_ip} '${exec_command}'' ${env.BUILD_ID}"
                
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