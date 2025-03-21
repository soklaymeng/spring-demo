# BUILD STAGE
FROM maven:3.9-amazoncorretto-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# RUN STAGE
FROM eclipse-temurin:23-jre-ubi9-minimal
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8082
CMD ["java", "-jar", "/app/app.jar"]
