FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/football-standing-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} football-standing-image.jar
ENTRYPOINT ["java","-jar","/football-standing-image.jar"]