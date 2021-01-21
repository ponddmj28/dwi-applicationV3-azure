FROM openjdk:8
VOLUME /tmp
CMD mkdir /tmp/batch/
CMD mkdir /tmp/batch/files
EXPOSE 8200
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]