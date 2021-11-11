#Usage
#
# docker build -t ricoris/caveservice .
#linux  docker run --rm -ti ricoris/caveservice  --network="host" -p 8080:8080  java -jar /caveservice.jar
#winx  docker run --rm -ti ricoris/caveservice  --add-host host.docker.internal:host-gateway -p 8080:8080  java -jar /caveservice.jar
# EVT docker run --name alpha-redis --rm  -p 6379:6379  -d redis redis-server
FROM openjdk:14-jdk-alpine

MAINTAINER alfa
COPY build/libs/CaveService-0.0.1-SNAPSHOT.jar caveservice.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/caveservice.jar"]