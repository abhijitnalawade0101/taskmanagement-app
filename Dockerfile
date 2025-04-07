# First Stage: Build the app
FROM maven:3.9.9-eclipse-temurin-17 AS build


WORKDIR /app
# Copy pom.xml first to download dependencies early

COPY .  .
# Copy source code


# Build the project
RUN mvn package

# Second Stage: Run the app
FROM openjdk:17-slim

WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/simple-crud-1.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

