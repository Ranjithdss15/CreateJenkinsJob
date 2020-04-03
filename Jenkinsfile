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
        println "github url: ${xml.url}"
        println "userRemoteConfigs: ${xml.userRemoteConfigs}"
        println "keepDependencies: ${xml.keepDependencies}"
        println "properties: ${xml.properties}"
        println "Inside properties: ${xml.properties[numToKeep]}"
        println "Inside properties 2: ${xml.properties.numToKeep.text()}"
    }
}                                   