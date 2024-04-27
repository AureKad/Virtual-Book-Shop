FROM openjdk:22-jdk-alpine
VOLUME /tmp
MAINTAINER kadzys.com
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]