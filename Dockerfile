FROM openjdk:11 AS build
EXPOSE 8081
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve
COPY src src
RUN ./mvnw package

FROM openjdk:11
WORKDIR library
COPY --from=build target/*.jar library.jar
ENTRYPOINT ["java", "-jar", "library.jar"]