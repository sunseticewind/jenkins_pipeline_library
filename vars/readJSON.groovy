import groovy.json.JsonSlurper

def call(sourceFile){
  new JsonSlurper().parseText(sourceFile)
}
