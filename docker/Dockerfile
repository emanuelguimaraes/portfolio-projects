FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/portfolio-projects-*.jar

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

WORKDIR /app

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]