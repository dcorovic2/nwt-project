FROM openjdk:8-jdk-alpine
ADD target/employee-docker.jar employee-docker.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "employee-docker.jar"]