# CaveService usage
Caveservice for MSDO

* Initial Rooms is created when new storage is used.

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

# testing the service

Using Curl

## Post command
curl -d "{\"description\":\"LirumLarum\", \"creatorID\":\"12\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/v2/room/(0,0,0)
## Get command
curl  -H "Content-Type: application/json" -X GET http://localhost:8080/v2/room/(0,0,0)

## Get exits from Room 

curl -d "{\"direction\":\"NORTH\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/v2/room/(0,0,0)/exits


## Running the JAR file

java -jar build/libs/CaveService-0.0.1-SNAPSHOT.jar --spring.redis.host=localhost --spring.redis.port=6379

## Specifying storage
open application.yml and set storage as follows:

    room: memoryStorage 
    or 
    room: redisStorage
# REST API Specification
This is the specification for the CaveService, which is intended to
handle rooms and exits to those rooms. There are 4 operations in the
CaveService as outlined below.

Versioning of the REST API is done as a part of the URI part of
the contract as described at: https://www.baeldung.com/rest-versioning
section 2.1

## Room
POST	: Update a room

PUT     : Create a room

GET		: Get information about the room at position

### Update a room
POST /v2/room/{position} HTTP/1.1

Content-Type: application/json

	{
		description: "LirumLarum",
		creatorId: 42
	}

Response

	Status: 200 Success
	Location: /v2/room/{position}
	{
			creatorId: 42,
			description: "LirumLarum",
			creationTimeISO8601 = "2021-11-02 15:00:00.000",
			id = 2
	}

	
	Example:
	POST http://localhost/v2/room/(0,0,0)

### Create a room
PUT /v2/room/{position} HTTP/1.1

Content-Type: application/json

	{
		description: "LirumLarum",
		creatorId: 42
	}

Response

	Status: 201 Created
	Location: /v2/room/{position}
	{
			creatorId: 42,
			description: "LirumLarum",
			creationTimeISO8601 = "2021-11-02 15:00:00.000",
			id = 2
	}

	
	Example:
	PUT http://localhost/v2/room/(0,0,0)
### Get information about the room at position
GET /v2/room/{position}

Response

	Status: 200 OK
	{
	    description = "LirumLarum",
		creatorId = 42,
		creationTimeISO8601 = "2021-11-02 15:00:00.000",
		id = 2
	}
	
	Status: 404 Not Found
	(none)

	Example:
	GET http://localhost/v2/room/(0,0,0)
## Exit
GET		: Get all possible exits from room at {position}. Exit directions are specified by NORTH, SOUTH, EAST, WEST, UP, DOWN

### Get all possible exits from room at {position}
GET /v2/room/{position}/exits/

Response

	Status: 200 OK
	[
		NORTH,
		EAST,
		WEST,
		UP
	]	 
	Status: 404 Not Found
	(none)
	
	Example:
	GET http://localhost/v2/room/(0,0,0)/exits/
