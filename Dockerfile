ARG REGISTRY=""
FROM openjdk:17-jdk-slim


ENV PROJECT_NAME=productservice
ENV PROJECT_VERSION=0.0.1-SNAPSHOT

COPY build/libs/${PROJECT_NAME}-${PROJECT_VERSION}.jar productservice.jar

EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar /app.jar --server.port=8080"]