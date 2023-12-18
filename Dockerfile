FROM openjdk:21
FROM maven:3.9-amazoncorretto-21
RUN rm -rf /target
COPY src /src
COPY ../* .
RUN mvn package spring-boot:repackage
RUN mvn -f ./pom.xml clean package
ENTRYPOINT ["java", "-jar", "target/cataasClientBE-0.0.1-SNAPSHOT.jar"]

EXPOSE 8082