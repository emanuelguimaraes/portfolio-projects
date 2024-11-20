FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY ../ .
RUN mvn clean install -DskipTests=true

FROM tomcat:9.0-jdk17

WORKDIR /usr/local/tomcat

COPY --from=build /app/target/portfolio-projects.war webapps/portfolio-projects.war

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -jar /usr/local/tomcat/webapps/portfolio-projects.war" ]