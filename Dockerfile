FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

FROM openjdk:17-jdk-slim

RUN /bin/sh -c apt-get install maven -y
RUN mvn clean install

EXPOSE 8080

COPY --from=build /target/todolist_rocketSeat-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]