# Out of office

Out of office is a web application created for leave tracking of an employee at the company. 

## Installation

1. Java installation and setup
2. Lombok instalation and setup in Eclipse

```bash
git clone https://github.com/dcorovic2/nwt-project.git
```

## Usage

```python
import project in eclipse
Run as Java application:
    - config-server ("https://github.com/nudiey/configuration-server") --> Configuration server is used for setting up main configuration properties (server port on eureka,database configuration) of all microservcies
    - eureka service discovery --> http://localhost:8761/
    - system-events (optinal) --> system events is used for tracking microservices activities
    - zuul-server --> API Gateway --> http://localhost:8762/micro_service_name/route (http://localhost:8762/notification/all_notification_types)
    - auth-server --> http://localhost:8089/swagger-ui.html
    - employee MS 
    - leave request MS
    - notification MS
    - holiday MS
```

## Contributors
- Nudžejma Pozder
