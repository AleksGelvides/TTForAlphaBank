FROM gradle:7.3.2-jdk17
ADD --chown=gradle . /code
WORKDIR /code
CMD ["gradle", "build"]
CMD ["java", "-jar", "code/for_alpha-0.0.1-SNAPSHOT.jar"]