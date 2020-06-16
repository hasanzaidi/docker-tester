# Based on JDK 8 running in Linux Alpine
FROM openjdk:8-jdk-alpine

VOLUME /tmp

# Copy the fat/uber JAR file we have built into the container (and rename it)
COPY target/*.jar docker-spring-boot.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/docker-spring-boot.jar"]
