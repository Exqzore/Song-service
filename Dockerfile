FROM amazoncorretto:17
ADD build/libs/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]