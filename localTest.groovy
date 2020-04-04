import groovy.*
import groovy.xml.XmlUtil
//def path = sh(script: "pwd", returnStdout: true).trim() as String
def xml = new XmlSlurper().parse("configDeploy.xml")
// xml.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url = 'giturlNew'
// println "github url: ${xml.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url}"
// def writer = new FileWriter("configNew.xml")
// XmlUtil.serialize(xml, writer)
// // Close file
// writer.close()

println "URL: ${xml.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url}"
println "Cred: ${xml.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.credentialsId}"
println "Branch: ${xml.definition.scm.branches.'hudson.plugins.git.BranchSpec'.name}"
println "Parameter: ${xml.properties.'hudson.model.ParametersDefinitionProperty'.parameterDefinitions.'hudson.model.StringParameterDefinition'.defaultValue[1]}"
    
    
    
    
    
    /*
    def updatedXml = groovy.xml.XmlUtil.serialize(xml)
    xml.write(updatedXml).wait()
    def stringWriter = new StringWriter()
    new XmlNodePrinter(new PrintWriter(stringWriter)).println(xml)
    def newXml = stringWriter.toString()
    println "Modified $newXml"
    */