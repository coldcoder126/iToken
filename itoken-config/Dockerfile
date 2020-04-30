FROM openjdk:8-jre

RUN mkdir /app

COPY itoken-config-1.0.0-SNAPSHOT.jar /app/

CMD java -jar /app/itoken-config-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod

EXPOSE 8888