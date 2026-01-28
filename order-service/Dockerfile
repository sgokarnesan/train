FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy root and module-specific files so Maven can resolve the parent POM
COPY pom.xml .
COPY order-service/pom.xml order-service/pom.xml
COPY order-service/src order-service/src

# Build only the order-service module (and any dependencies) inside the multi-module project
RUN mvn -q -pl order-service -am -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR for the order-service module
COPY --from=build /app/order-service/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]

