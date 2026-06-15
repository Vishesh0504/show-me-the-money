FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY target/show-me-the-money-1.0-SNAPSHOT.jar app.jar

EXPOSE 18080
EXPOSE 18081

CMD ["java", "-jar", "app.jar","server", "/config/config.yml"]