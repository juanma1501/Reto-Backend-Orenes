FROM gradle:7.4.2-jdk17-alpine as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootjar --no-daemon

FROM amazoncorretto:17.0.3-alpine
WORKDIR /home/app
COPY --from=build /home/gradle/src/build/libs/*.jar /home/app/application.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]