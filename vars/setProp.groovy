def call() {
    properties([
        buildDiscarder(logRotator(numToKeepStr: '100'))
        timeout(time: 10, unit: 'MINUTES')
    ])
}