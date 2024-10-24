FROM eclipse-temurin:21-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/vota-e-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
LABEL authors="Pedro de Oliveira"
ENTRYPOINT ["java", "-jar", "/app.jar"]