FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]
