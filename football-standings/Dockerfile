# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/football-standings-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
