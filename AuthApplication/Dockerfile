FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/AuthApplication-0.0.1-SNAPSHOT.jar app.jar

ADD https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh wait-for-it.sh

RUN chmod +x wait-for-it.sh

EXPOSE 8081

ENTRYPOINT ["./wait-for-it.sh", "mysql-db:3306", "--", "java", "-jar", "app.jar"]