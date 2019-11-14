FROM gradle:jdk10 as builder
COPY --chown=gradle:gradle . /demo
WORKDIR /demo
RUN gradle bootJar

FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG LIBS=demo/build/libs
COPY --from=builder ${LIBS}/ /demo/build/lib
ENTRYPOINT ["java","-jar","./demo/build/lib/demo-0.0.1-SNAPSHOT.jar"]
