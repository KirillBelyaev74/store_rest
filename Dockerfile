FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/store_rest-0.0.1.jar
COPY ${JAR_FILE} rest.jar
ENTRYPOINT ["java","-jar","rest.jar", "-Dspring.config.location=classpath:application.properties"]