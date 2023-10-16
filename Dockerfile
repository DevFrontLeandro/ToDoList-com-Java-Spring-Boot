FROM ubuntu:latest AS build
FROM openjdk:17-jdk

# Instalando o Maven
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . /app
WORKDIR /app
RUN apt-get install maven -y
RUN mvn clean install
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app.jar" ]