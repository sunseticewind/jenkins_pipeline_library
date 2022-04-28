def call(params){
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        sh "echo ${params}"
    }
}
