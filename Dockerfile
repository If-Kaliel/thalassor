# ==============================
# BUILD STAGE
# ==============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# ==============================
# RUNTIME STAGE
# ==============================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/quarkus-app/lib/ ./lib/
COPY --from=build /app/target/quarkus-app/*.jar ./
COPY --from=build /app/target/quarkus-app/app/ ./app/
COPY --from=build /app/target/quarkus-app/quarkus/ ./quarkus/

EXPOSE 10000

CMD ["sh", "-c", "java -Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=${PORT:-10000} -jar quarkus-run.jar"]