import java.nio.file.Path; 
import java.nio.file.Paths; 

@com.cloudbees.groovy.cps.NonCPS
def call(server_ip, exec_command) {
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        withCredentials([sshUserPrivateKey(
            credentialsId: server_ip, 
            keyFileVariable: 'SSH_KEY',
            usernameVariable: 'SSH_USER')]) 
            {
                def filePath = this.class.classLoader.getResourceLoader().loadGroovySource(this.class.name).toURI()
                Path path = Paths.get(filePath)
                while (dirname = path.getFileName().toString()) {
                    if ("vars".equals(dirname) || "src".equals(dirname)) 
                        break
                    path = path.getParent()
                }
                def resourcesFolder = path.getParent().resolve('resources').toString()
                def SSH_COMMAND = "./${resourcesFolder}/org/foo/testEscape.sh ${SSH_KEY} ${SSH_USER} ${server_ip} ${exec_command} ${env.BUILD_ID}"
                
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