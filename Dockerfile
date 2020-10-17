FROM openjdk:8-alpine

COPY target/uberjar/todo-app.jar /todo-app/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/todo-app/app.jar"]
