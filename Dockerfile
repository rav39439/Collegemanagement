FROM maven:3.6.3-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
# # Set the working directory in the container
# WORKDIR /app

# FROM openjdk:11-jre-slim

# VOLUME /tmp

# ARG JAR_FILE

# # Copy the JAR file into the container at /app
# COPY target/*.jar jwtauthdemo-0.0.1-SNAPSHOT.jar

# # Expose the port that the application runs on
# EXPOSE 9000

# # Specify the command to run on container start
# ENTRYPOINT ["java", "-jar", "jwtauthdemo-0.0.1-SNAPSHOT.jar"]


FROM openjdk:11-jre-slim

# VOLUME /tmp

# ARG JAR_FILE=target/*.jar

# Copy the JAR file into the container at /app
COPY --from=build /target/jwtauthdemo-0.0.1-SNAPSHOT.jar demo.jar

# Expose the port that the application runs on
EXPOSE 9000

# Specify the command to run on container start
ENTRYPOINT ["java", "-jar", "jwtauthdemo-0.0.1-SNAPSHOT.jar"]