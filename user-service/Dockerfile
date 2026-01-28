FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy root and module-specific files so Maven can resolve the parent POM
COPY pom.xml .
COPY user-service/pom.xml user-service/pom.xml
COPY user-service/src user-service/src

# Build only the user-service module (and any dependencies) inside the multi-module project
RUN mvn -q -pl user-service -am -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built JAR for the user-service module
COPY --from=build /app/user-service/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]

