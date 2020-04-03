import groovy.*
node {
    stage("Get config"){
        sh 'curl http://18.232.144.156:8080/job/TestJob/config.xml -u admin:111f188371615e4779b9598eb94c5c0f16 > config.xml'
        sh 'pwd && ls'
    }

    stage("Open Config.xml"){
        sh 'pwd && ls'
        def xml = new XmlSlurper().parse('config.xml')
        print "${xml}"
    }
}