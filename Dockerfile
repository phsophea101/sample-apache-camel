FROM openjdk:11
COPY build/libs/camel-0.0.1-SNAPSHOT.jar app.jar
#gradle clean build
ENTRYPOINT ["java","-jar","/app.jar"]