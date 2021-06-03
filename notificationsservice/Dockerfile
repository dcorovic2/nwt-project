FROM openjdk:8-jdk-alpine
ADD target/notification-docker.jar notification-docker.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "notification-docker.jar"]