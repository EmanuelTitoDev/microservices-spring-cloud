FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} eureka-server.jar

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
