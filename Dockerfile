FROM alpine

RUN apk --no-cashe add openjdk11 --repository=http://dlcdn.alpinelinux.org/alpine/edge/community\
    maven\
    curl

VOLUME /home/lessonapp

WORKDIR /home/lessonapp

COPY ./ /home/lessonapp

ARG JAR_FILE=target/docker_lesson-0.0.1-SNAPSHOT.jar

EXPOSE 8090

ADD ${JAR_FILE} test.jar

CMD java -jar test.jar


