FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY target/show-me-the-money-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080
EXPOSE 8081

CMD ["java", "-jar", "app.jar","server", "/config/config.yml"]