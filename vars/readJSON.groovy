import groovy.json.JsonSlurperClassic

def call(sourceFile){
  new JsonSlurperClassic().parseText(sourceFile)
}
