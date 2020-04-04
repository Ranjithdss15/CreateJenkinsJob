import groovy.*
import groovy.xml.XmlUtil
node {
    /*
    stage("Get config"){
        sh 'curl http://18.232.144.156:8080/job/TestJob/config.xml -u admin:111f188371615e4779b9598eb94c5c0f16 > config.xml'
        sh 'pwd && ls'
    } 
    stage("Open Config.xml"){
        sh 'pwd && ls'
        def path = sh(script: "pwd", returnStdout: true).trim() as String
        def xml = new XmlSlurper().parse("${path}/config.xml")
        print "${xml}"
        xml.appendNode {
        foo(bar: "bar value")
        }   
        println "github url: ${xml.url}"
        println "userRemoteConfigs: ${xml.userRemoteConfigs}"
        println "keepDependencies: ${xml.keepDependencies}"
        println "github url: ${xml.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url}"
    } */
    stage("Prepare"){
        checkout scm
    }
    stage("Create Folder"){
        def path = sh(script: "pwd", returnStdout: true).trim() as String
        println "Creating folder"
        //def create = sh(script: "curl -X POST  http://18.232.144.156:8080/job/CreateJob/createItem?name=SmokeTest   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @configFolder.xml", returnStdout: false)
        sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/createItem?name=SmokeTest   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @configFolder.xml"
        sleep 5
    } 
    stage("create Build Job"){
        gitURL = "https://github.com/Ranjithdss15/CreateJenkinsJob.git"
        gitCredID = "github"
        gitbranch = "*/master"
        def path = sh(script: "pwd", returnStdout: true).trim() as String
        def xmlBuild = new XmlSlurper().parse("${path}/configBuild.xml")
        xmlBuild.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url = "${gitURL}"
        xmlBuild.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.credentialsId = "${gitCredID}"
        xmlBuild.definition.scm.branches.'hudson.plugins.git.BranchSpec'.name = "${gitbranch}"
        def writer = new FileWriter("${path}/configBuild.xml")
        XmlUtil.serialize(xmlBuild, writer)
        println "Creating Build Job"
        sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/SmokeTest/createItem?name=BuildSmoke   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @${path}/configBuild.xml"
        sleep 5
    }
    stage("Create Deploy Job"){
        gitURL = "https://github.com/Ranjithdss15/CreateJenkinsJob.git"
        gitCredID = "github"
        gitbranch = "*/master"
        def path = sh(script: "pwd", returnStdout: true).trim() as String
        def xmlDeploy = new XmlSlurper().parse("${path}/configDeploy.xml")
        xmlDeploy.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.url = "${gitURL}"
        xmlDeploy.definition.scm.userRemoteConfigs.'hudson.plugins.git.UserRemoteConfig'.credentialsId = "${gitCredID}"
        xmlDeploy.definition.scm.branches.'hudson.plugins.git.BranchSpec'.name = "${gitbranch}"
        xmlDeploy.properties.'hudson.model.ParametersDefinitionProperty'.parameterDefinitions.'hudson.model.StringParameterDefinition'.defaultValue[1] = "${gitbranch}"
        def writer = new FileWriter("${path}/configDeploy.xml")
        XmlUtil.serialize(xmlDeploy, writer)
        println "Creating Deploy Job"
        sh "curl -X POST  http://18.232.144.156:8080/job/CreateJob/SmokeTest/createItem?name=DeploySmoke   -u admin:111f188371615e4779b9598eb94c5c0f16 -H Content-Type:application/xml -d @${path}/configDeploy.xml"
          
    }

}                                   