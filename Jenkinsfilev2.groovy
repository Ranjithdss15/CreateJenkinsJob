import groovy.*
import groovy.xml.XmlUtil
node {
    
    stage("Prepare"){
        deleteDir()
        checkout scm
    }
    stage("Create Folder"){
        def path = sh(script: "pwd", returnStdout: true).trim() as String
        println "Creating folder"
        sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/createItem?name=SmokeTest   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @configFolder.xml"
        sleep 5
    } 
    stage("Modify config.xml"){
        gitURL = "https://github.com/Ranjithdss15/CreateJenkinsJob.git"
        gitCredID = "github"
        gitbranch = "*/master"

        def path = sh(script: "pwd", returnStdout: true).trim() as String
        def xmlBuild = new XmlSlurper().parse("${path}/configBuild.xml")
        xmlBuild.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url = "${gitURL}"
        xmlBuild.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.credentialsId = "${gitCredID}"
        xmlBuild.definition.scm.branches.'hudson.plugins.git.BranchSpec'.name = "${gitbranch}"
        def writerBuild = new FileWriter("${path}/configBuild.xml")
        XmlUtil.serialize(xmlBuild, writerBuild)
        writerBuild.close()

        def xmlDeploy = new XmlSlurper().parse("${path}/configDeploy.xml")
        xmlDeploy.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url = "${gitURL}"
        xmlDeploy.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.credentialsId = "${gitCredID}"
        xmlDeploy.definition.scm.branches.'hudson.plugins.git.BranchSpec'.name = "${gitbranch}"
        xmlDeploy.properties.'hudson.model.ParametersDefinitionProperty'.parameterDefinitions.'hudson.model.StringParameterDefinition'.defaultValue[1] = "${gitbranch}"
        def writerDeploy = new FileWriter("${path}/configDeploy.xml")
        XmlUtil.serialize(xmlDeploy, writerDeploy)
        writerDeploy.close()
       
    }
    stage("Create Jobs"){
        def path = sh(script: "pwd", returnStdout: true).trim() as String
        println "Creating Build Job"
        sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/SmokeTest/createItem?name=BuildSmoke   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @${path}/configBuild.xml"
        
        println "Creating Deploy Job"
        sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/SmokeTest/createItem?name=DeploySmoke   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @${path}/configDeploy.xml"
          
    }

}                                   