pipeline {
  agent any

  tools {
    // Configure em Manage Jenkins > Tools:
    // "jdk-17" aponta para seu JDK 17; "maven-3.9" para seu Maven
    jdk 'jdk-17'
    maven 'maven-3.9'
  }

  options {
    timestamps()
  }

  triggers {
    // Se o Jenkins NÃO estiver acessível pela internet, use polling.
    pollSCM('H/5 * * * *') // verifica mudanças a cada ~5 min
    // Se você expuser um webhook (ex: ngrok), prefira webhook e remova o pollSCM.
  }

  environment {
    MAVEN_OPTS = '-Xmx512m'
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

  stage('Build & Test') {
    steps {
      dir('inf335prj4') {
        sh 'mvn -B -ntp -DskipTests=false clean verify'   // on Windows: bat 'mvn ...'
      }
    }
    post {
      always {
        // post runs at workspace root; use the full path
        junit 'inf335prj4/target/surefire-reports/*.xml'
      }
    }
  }

  stage('Package') {
    steps {
      dir('inf335prj4') {
        sh 'mvn -B -ntp -DskipTests package'              // on Windows: bat 'mvn ...'
      }
    }
    post {
      success {
        archiveArtifacts artifacts: 'inf335prj4/target/*.jar, inf335prj4/target/*.war',
                          fingerprint: true
      }
    }
  }
  }

  post {
    always { cleanWs() }
  }
}
