FROM openjdk:8-jdk-alpine
ADD target/leave-docker.jar leave-docker.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "leave-docker.jar"]