FROM openjdk:17-jdk-alpine
MAINTAINER pontos-service
COPY target/pontos-0.0.1-SNAPSHOT.jar pontos-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/pontos-0.0.1-SNAPSHOT.jar"]
