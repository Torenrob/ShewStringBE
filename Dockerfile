FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src/ src/
RUN ./mvnw clean package

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/ShewStringBE-1.0.0-alpha.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
