def call() {
  properties([
    buildDiscarder(logRotator(numToKeepStr: '5'))
  ])
}