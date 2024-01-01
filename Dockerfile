FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/store_rest-1.0.0.jar
COPY ${JAR_FILE} rest.jar
ENTRYPOINT ["java","-jar","rest.jar", "-Dvertx.disableDnsResolver=true", "-Dspring.config.location=classpath:application.properties"]