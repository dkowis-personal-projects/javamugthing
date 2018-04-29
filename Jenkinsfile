    node {
      def image = docker.image("registry.light.kow.is/java-elm:latest")
      def appImage

      stage("Update image") {
        image.pull()
      }

      stage("Clean") {
        deleteDir()
      }

      stage("Checkout") {
        checkout scm
      }

      stage("Build project") {
        image.inside() {
          sh "pwd"
          sh "whoami"
          sh "echo $HOME"
        }
        //TODO: maybe do a sidecar postgresql container for tests
        image.inside('-v $HOME/.gradle:/$HOME/.gradle -v $HOME/.elm:/$HOME/.elm') {
          sh "./gradlew --no-daemon build"
        }
      }

/*
      if(env.BRANCH_NAME == "master") {
        stage("Build Container") {
          docker.withRegistry('https://registry.light.kow.is', 'dkowis-creds') {
            appImage = docker.build("registry.light.kow.is/monopolytracker:latest")
          }
        }
        stage("Deploy Container") {
          docker.withRegistry('https://registry.light.kow.is', 'dkowis-creds') {
            appImage.push()
          }
        }
      }
      */
    }