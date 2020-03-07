FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Data Platform
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/kafka-ingress-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "kafka-ingress-0.0.1-SNAPSHOT.jar"]
