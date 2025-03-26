ARG REGISTRY=""
FROM openjdk:17-jdk-slim


ENV PROJECT_NAME=product-service
ENV PROJECT_VERSION=0.0.1-SNAPSHOT

COPY build/libs/${PROJECT_NAME}-${PROJECT_VERSION}.jar productservice.jar

EXPOSE 8090

ENTRYPOINT ["sh","-c","java -jar /productservice.jar --server.port=8090"]