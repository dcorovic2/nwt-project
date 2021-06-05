# Out of office

Out of office is a web application created for leave tracking of an employee at the company. 

## Installation

1. Java installation and setup
2. Lombok instalation and setup in Eclipse
3. Angular installation

```bash
git clone https://github.com/dcorovic2/nwt-project.git
```

## Usage backend

```python
import project in Eclipse (optinal IDE)
Run as Java application:
    - config-server ("https://github.com/nudiey/configuration-server") --> Configuration server is used for setting up main configuration properties (server port on eureka,database configuration) of all microservcies
    - eureka service discovery --> http://localhost:8761/
    - system-events (optinal) --> system events is used for tracking microservices activities
    - auth-server --> API Gateway --> http://localhost:8089/swagger-ui.html
    - employee MS 
    - leave request MS
    - notification MS
    - holiday MS
```

## Usage frontend
```python
import project in Visual Studio Code (optinal IDE)
Run on terminal:
    - npm install -g @angular/cli
    - npm install ng-zorro-antd
    - npm install
    - npm start
Open application on http://localhost:4200/

Due to some potentional issues with Zorro components, you can run next commands:
    - npm cache clean --force
    - ng serve --prod
```

## Issues with Docker
All microservices have Docker files, and corresponding filenames in pom.xml. However, docker-compose.yml has unresolved issues.
After exhaustive research, we didn't resolve this issue. Issue report is in the following section:
1.  Microservices are unable to fetch configuration in Config-server using Docker 
2.  Regarding to the previous statement, microservices runs on default port 8080 instead of defined port
3.  Consequently, microservices are unable to register on Eureka

We have tried a lot of different potentional solutions, as follows:
1. We have add exposed variable names instead of localhost in urls on Config-server and application.properties
2. Externalising Spring Boot properties when deploying to Docker using bootstrap.properties/bootstrap.yml
3. Set the deafult network betwork between containers 
4. Adding retry property into application.properties of microservices
5. Adding  deploy|restart_policy|condition: on-failure|max_attempts: 5
6. Adding environment variables as EUREKA_CLIENT_SERVICEURL_DEFAULTZONE: http://eureka:8761/eureka and CONFIG_SERVER_URI: optional:configserver:http://config-server:8888

.. and a lot of different proposed variation. This issue is a future work on this project.
**All of the other tasks are successfully completed!**

## Demo video

Link: https://drive.google.com/file/d/1R1PI3_cmBs6gUIL53H4soysgqGu8bR-R/view?usp=sharing

## Contributors
- Dalila Ćorović
- Esma Herenda
- Nudžejma Pozder
- Belmin Divjan
