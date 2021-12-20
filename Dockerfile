#Usage
#
# docker build -t locally/caveservice .
# docker push ricoris/caveservice
# 1 docker network create alfa
# 2 docker run --rm  --network=alfa -p 8080:8080  -ti locally/caveservice java -jar /caveservice.jar --storage.room=memoryStorage
# --spring.redis.host=alpha-redis --spring.redis.port=6379
# 3 docker run --name alpha-redis -p 6379:6379 --hostname redisdb --network=alfa --rm -d redis redis-server
# docker scan locally/caveservice -f Dockerfile
# docker history locally/caveservice
# Connecto to container
# docker container exec -it <containerID> bash

#Sample for how NOT to build docker images
FROM gradle:6.9.1-jdk11

#Not copy all

# Setup workdir to ensure gradle is run from the right location
WORKDIR /root/caveservice
COPY . .
# Cleanup build directory
RUN gradle clean

# Create the server daemon jar file to be used in the image
RUN gradle bootJar
COPY /build/libs/CaveService-0.0.1-SNAPSHOT.jar /root/caveservice/caveservice.jar

EXPOSE 8080

CMD ["java", "-jar","/root/caveservice/caveservice.jar", "--storage.room=memoryStorage"]
