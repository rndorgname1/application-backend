FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 80 5672
CMD mvn package
COPY /target/application-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]