FROM amazoncorretto:17
ADD build/libs/*SNAPSHOT.jar song-service.jar
ENTRYPOINT ["java","-jar","song-service.jar"]