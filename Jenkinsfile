pipeline {
  agent any
  stages {
    stage("Verify tooling") {
      steps {
        sh '''
          docker version
          docker info
          docker-compose version
          curl --version
          java -version
        '''
      }
    }

    stage('Cloning Git') {
        steps {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/giovannic96/e-commerce-app.git']]])
        }
    }

    stage("Prune Docker data") {
        steps {
            sh 'docker system prune -a --volumes -f'
        }
    }

    stage('Start containers') {
        steps {
            sh 'docker-compose up -d'
            sh 'docker-compose ps'
        }
    }
  }
}