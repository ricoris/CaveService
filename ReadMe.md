
# Usage for testing with Curl

gradlew bootJar

curl -d "{\"description\":\"LirumLarum\", \"creatorID\":\"12\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/v1/room/(0,0,0)

curl  -H "Content-Type: application/json" -X GET http://localhost:8080/v1/room/(0,0,0)
## Direction changing
// x axis  West -1, East +1
// y axis North +1, South -1
// z axis Up +1, Down -1

rdcli -h 87.63.205.10  -p 63079


## Dig room
curl -d "{\"direction\":\"NORTH\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/v1/room/(0,0,0)/exits
curl  -H "Content-Type: application/json" -X GET http://localhost:8080/v1/room/(0,1,0)