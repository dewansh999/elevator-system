FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/elevator-system-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]

