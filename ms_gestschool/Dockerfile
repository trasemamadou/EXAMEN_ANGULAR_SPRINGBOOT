FROM maven:3.8.6-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
COPY src ./src
EXPOSE 8083
CMD ["mvn", "spring-boot:run"]
