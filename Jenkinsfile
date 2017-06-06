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
       stage('Deploy war to static liberty container') {
           agent {
               docker {
                   image 'alpine'
               }
           }
           steps {
               sh 'cp target/bike-shop.war /usr/share/jenkins/ref/liberty/bike-shop.war'
           }
        }
       stage('Static container smoke-test') {
           agent {
                docker {
                    image 'liatrio/selenium-firefox'
                    args '--network=demodeploymentpipeline_default'
                }
            }
            steps {
                sh 'sleep 25'
                sh 'ruby bike_spec.rb http://liberty:9080'
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
            }
       }
       stage('Dynamic container smoke-test') {
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
