FROM alpine

RUN apk add openjdk11\
    maven\
    curl

VOLUME /home/lessonapp

WORKDIR /home/lessonapp

COPY ./ /home/lessonapp

ARG JAR_FILE=target/docker_lesson-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ADD ${JAR_FILE} test.jar

CMD java -jar test.jar


