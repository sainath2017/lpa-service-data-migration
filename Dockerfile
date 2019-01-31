FROM java:8
VOLUME /tmp
ADD lpa-service-data-migration-1.0.0.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Duser.timezone=America/New_York","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
