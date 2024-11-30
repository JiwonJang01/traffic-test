
FROM gradle:8.11.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM openjdk:17.0.1-jdk-slim
COPY --from=build build/libs/statistics.jar statistics.jar
ENTRYPOINT ["java","-jar","statistics.jar"]


#FROM openjdk:17.0.1-jdk-slim
#COPY build/libs/statistics.jar statistics.jar
#ENTRYPOINT ["java","-jar","statistics.jar"]
