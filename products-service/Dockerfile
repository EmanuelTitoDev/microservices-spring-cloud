FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} products-service.jar

ENTRYPOINT ["java", "-jar", "products-service.jar"]