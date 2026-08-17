FROM eclipse-temurin:21-jdk-jammy
COPY build/libs/*.jar telemetry-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/telemetry-service.jar"]