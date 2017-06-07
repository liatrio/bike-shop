pipeline {
    agent none
    stages {
       stage('Build maven artifact') {
           agent {
               docker {
                   image 'maven:3.5.0'
                   args '--network=demodeploymentpipeline_default'
               }
           }
           steps {
               configFileProvider(
                       [configFile(fileId: 'nexus', variable: 'MAVEN_SETTINGS')]) {
                   sh 'mvn -s $MAVEN_SETTINGS clean deploy -DskipTests=true -B'
               }
           }
       }
       stage('Build container') {
           agent any
            steps {
                sh 'docker build -t bike-shop .'
            }
       }
       stage('Run container') {
           agent any
            steps {
                sh 'docker rm -f bike-shop-liberty || true'
                sh 'docker run -p 9080:9080 -d --network=demodeploymentpipeline_default --name bike-shop-liberty bike-shop'
                echo 'Should be accessible at http://localhost:9080/bike-shop'
            }
       }
       stage('Smoke-test') {
           agent {
                docker {
                    image 'liatrio/selenium-firefox'
                    args '--network=demodeploymentpipeline_default'
                }
            }
            steps {
                sh 'sleep 25'
                sh 'ruby bike_spec.rb http://bike-shop-liberty:9080'
            }
       }
   }
 }
