FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw && ./mvnw -B -DskipTests dependency:go-offline

COPY src src
RUN ./mvnw -B -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# The application only needs to read the packaged JAR. Use a non-root UID
# for the runtime process; /tmp remains writable for JVM temporary files.
USER 10001:10001

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
