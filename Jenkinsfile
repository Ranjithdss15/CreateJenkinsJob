import groovy.*
node {
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
    }
    stage("Modify xml"){
        def editxml = new XmlSlurper().parse("${path}/config.xml")
        println "${editxml}"
        editxml.each {it.'keepDependencies'.value="edited"}
        println "${editxml}"
    }
}                                   