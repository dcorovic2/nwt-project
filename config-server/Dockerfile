FROM openjdk:8-jdk-alpine
ADD target/config-docker.jar config-docker.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "config-docker.jar"]