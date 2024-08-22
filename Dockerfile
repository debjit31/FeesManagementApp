# Use an official OpenJDK 17 runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY build/libs/*.jar /app/app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 61001

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
