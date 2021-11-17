# CaveService

Caveservice for MSDO

* Initial Rooms is created when new storage is used.

# Specifying storeage 
open application.yml and set spring, active profiles as follows:

    active: fakeStorage 
    or 
    active: redisStorage
# Usage for testing with Curl

## Build file

Create the Java file
gradlew bootJar

## Create container
Create Docker file with these commands

### Example of build and push
docker build -t ricoris/caveservice .
docker push ricoris/caveservice

### Running the service locally
Testing Docker images with network for Redis DB
1. docker network create alfa
2. docker run --rm  --network=alfa -p 8080:8080  -ti ricoris/caveservice java -jar /caveservice.jar --spring.redis.host=alpha-redis --spring.redis.port=6379
3. docker run --name alpha-redis -p 6379:6379 --hostname redisdb --network=alfa --rm -d redis redis-server
or with FakeStorage
4. docker run --rm  --network=alfa -p 8080:8080  -ti ricoris/caveservice java -jar /caveservice.jar --spring.redis.host=alpha-redis --spring.redis.port=6379 --spring.profiles.active=fakeStorage
# testing the service

Using Curl

## Post command
curl -i -d "{\"description\":\"LirumLarum\", \"creatorId\":\"12\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/v2/room/(0,0,0)
## Get command
curl  -H "Content-Type: application/json" -X GET http://localhost:8080/v1/room/(0,0,0)

## Get exits from Room 

curl -d "{\"direction\":\"NORTH\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/v1/room/(0,0,0)/exits



## Running the JAR file

java -jar build/libs/CaveService-0.0.1-SNAPSHOT.jar --spring.redis.host=localhost --spring.redis.port=6379 --spring.profiles.active=fakeStorage

# API specifications

[API Specification file](CAveService%20REST%20API%20v2.txt)

# Team Alfa

