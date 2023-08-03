FROM openjdk:latest
ADD target/my-crud-project.jar app.jar
ENTRYPOINT ["java","-jar","app.jar" ]
EXPOSE 8080