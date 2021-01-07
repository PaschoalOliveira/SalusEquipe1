FROM openjdk:11-jdk
WORKDIR /app

ARG APP_JAR_NAME=SALUS
ARG APP_JAR_VERSION=0.0.1

EXPOSE 8080

COPY target/${APP_JAR_NAME}-${APP_JAR_VERSION}-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]