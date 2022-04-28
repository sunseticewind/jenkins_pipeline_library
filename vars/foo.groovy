import org.foo.testBar

def call(x,y,z){
    withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']){
        def tb = new testBar()
        tb.setLength(x)
        tb.setWidth(y)
        tb.setHeight(z)
        sh "echo ${tb.volume()}"
    }
}
