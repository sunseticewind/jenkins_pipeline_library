import org.foo.testBar

def call(x,y,z){
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        def tb = new testBar(x, y, z)
        sh "echo ${tb.volume()}"
    }
}
