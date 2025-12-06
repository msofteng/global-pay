FROM maven:3.9.11-amazoncorretto-11 AS build

WORKDIR /app

COPY . .

RUN mvn clean package

FROM amazoncorretto:11-alpine-jdk

WORKDIR /app

COPY --chown=185 --from=build /app/target/global-pay-1.0.0.jar service.jar

EXPOSE 8080
USER 185

ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-Xmx512m", "-jar", "service.jar"]