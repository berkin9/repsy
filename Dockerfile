# Step 1: Build the application using Maven
FROM maven:3.8.6-openjdk-11-slim as build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Package the application as a jar file
RUN mvn clean package -DskipTests

# Step 2: Run the application in a slim JDK container
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file from the build container to the final image
COPY --from=build /app/target/repsy-storage-api-1.0-SNAPSHOT.jar /app/repsy-storage-api.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "repsy-storage-api.jar"]
