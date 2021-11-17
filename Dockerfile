#Usage
#
# docker build -t ricoris/caveservice .
# docker push ricoris/caveservice
# 1 docker network create alfa
# 2 docker run --rm  --network=alfa -p 8080:8080  -ti ricoris/caveservice java -jar /caveservice.jar --spring.redis.host=alpha-redis --spring.redis.port=6379
# 3 docker run --name alpha-redis -p 6379:6379 --hostname redisdb --network=alfa --rm -d redis redis-server

# ¤ fakes storage docker run --rm  --network=alfa -p 8080:8080  -ti ricoris/caveservice java -jar /caveservice.jar --spring.redis.host=alpha-redis --spring.redis.port=6379 --spring.profiles.active=fakeStorage

FROM openjdk:14-jdk-alpine

MAINTAINER alfa
COPY build/libs/CaveService-0.0.1-SNAPSHOT.jar caveservice.jar
EXPOSE 8080

 CMD ["java", "-jar","/caveservice.jar"]