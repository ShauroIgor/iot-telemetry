FROM openjdk:21-jdk-slim
COPY build/libs/*.jar telemetry-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/telemetry-service.jar"]