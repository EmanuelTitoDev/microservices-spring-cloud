FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} shopping-cart-service.jar

ENTRYPOINT ["java", "-jar", "shopping-cart-service.jar"]