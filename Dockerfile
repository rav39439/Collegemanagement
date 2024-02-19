# FROM maven:3.6.3-jdk-11 AS build
# COPY . .
# RUN maven clean package -DskipTests
# # Set the working directory in the container
# WORKDIR /app

FROM openjdk:11-jre-slim

# Copy the JAR file into the container at /app
COPY target/jwtauthdemo-0.0.1-SNAPSHOT.jar /app/

# Expose the port that the application runs on
EXPOSE 8080

# Specify the command to run on container start
ENTRYPOINT ["java", "-jar", "jwtauthdemo-0.0.1-SNAPSHOT.jar"]