FROM openjdk:8-alpine
VOLUME /tmp
CMD mkdir /tmp/configs/
EXPOSE 8100
ADD target/*.jar app.jar
COPY docker/id_rsa /root/.ssh/
COPY docker/config /root/.ssh/
RUN apk update; apk --no-cache add curl; apk add openssh; chmod 600 /root/.ssh/id_rsa
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]