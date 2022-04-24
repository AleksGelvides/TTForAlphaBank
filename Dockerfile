FROM gradle:jdk17 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle test build

FROM openjdk:17

EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/for_alpha-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java", "-jar", "for_alpha-0.0.1-SNAPSHOT.jar"]