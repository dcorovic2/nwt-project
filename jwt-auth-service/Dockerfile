FROM openjdk:8-jdk-alpine
ADD target/auth-docker.jar auth-docker.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "auth-docker.jar"]