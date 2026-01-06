FROM gradle:8-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar track-cash-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "track-cash-backend.jar"]
