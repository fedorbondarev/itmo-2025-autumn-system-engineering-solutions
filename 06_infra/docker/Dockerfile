FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY 05_app/pom.xml ./pom.xml
RUN mvn -q -e -DskipTests package || true
COPY 05_app/src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre-jammy
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=build /app/target/preorder-1.0.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
