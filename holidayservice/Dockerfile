FROM openjdk:8-jdk-alpine
ADD target/holiday-docker.jar holiday-docker.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "holiday-docker.jar"]