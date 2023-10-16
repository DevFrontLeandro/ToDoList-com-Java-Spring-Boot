FROM ubuntu:latest AS build
FROM openjdk:11-jdk

# Instalando o Maven
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .
COPY . /app
WORKDIR /app
RUN apt-get install maven -y
RUN mvn clean install
EXPOSE 8080

COPY --from=build /target/todolist_rocketSeat-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]