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
    
        gitURL = "https://github.com/Ranjithdss15/CreateJenkinsJob.git"
        gitCredID = "github"
        gitbranch = "*/master"
        //def path = sh(script: "pwd", returnStdout: true).trim() as String
        def xmlBuild = new XmlSlurper().parse("configBuild.xml")
        xmlBuild.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url = "${gitURL}"
        xmlBuild.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.credentialsId = "${gitCredID}"
        xmlBuild.definition.scm.branches.'hudson.plugins.git.BranchSpec'.name = "${gitbranch}"
        def writer = new FileWriter("configBuild.xml")
        XmlUtil.serialize(xmlBuild, writer)
        writer.close()
        //println "Creating Build Job"
        def execute = sh(script: "curl -X POST  http://18.232.144.156:8080/job/CreateJob/SmokeTest/createItem?name=BuildSmoke   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @configBuild.xml", returnStdout: true)
        //sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/SmokeTest/createItem?name=BuildSmoke   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @configBuild.xml"

    
    
    
    /*
    def updatedXml = groovy.xml.XmlUtil.serialize(xml)
    xml.write(updatedXml).wait()
    def stringWriter = new StringWriter()
    new XmlNodePrinter(new PrintWriter(stringWriter)).println(xml)
    def newXml = stringWriter.toString()
    println "Modified $newXml"
    */