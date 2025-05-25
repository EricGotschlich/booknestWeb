FROM gradle:jdk21-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build --no-daemon

FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /home/gradle/project/build/libs/*SNAPSHOT*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
