FROM openjdk:17
COPY target/desafio-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "desafio-0.0.1-SNAPSHOT.jar"]