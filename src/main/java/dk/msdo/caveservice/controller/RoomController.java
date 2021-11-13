package dk.msdo.caveservice.controller;

import com.google.gson.Gson;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.exceptions.RoomRepositoryException;
import dk.msdo.caveservice.repositories.RoomRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Rest controller for the Room part of the CaveService
 *
 * Author: Team Alpha
 */
@RestController
public class RoomController {

    private final RoomRepositoryImpl roomRepository;

    public RoomController(RoomRepositoryImpl roomRepository) {
        this.roomRepository = roomRepository;
        this.roomRepository.initialize();
    }

    /**
     * Get all exits at position
     *
     * EXAMPLE:
     *
     * GET http://localhost:8080/v2/room/{1,1,1}/Exits
     */
    @GetMapping(path="/v2/room/{position}/exits", produces = "application/json")
    public ResponseEntity <String> getRoomExitsAtPosition(@PathVariable(value = "position") String position   )
    {
        ArrayList<String> e =  roomRepository.getAllExitsAtPosition(position);
        return new ResponseEntity<>(e.toString(),HttpStatus.OK);
    }

    /**
     * Get room at position
     *
     * EXAMPLE:
     *
     * GET http://localhost:8080/v2/room/{1,1,1}
     */
    @GetMapping(path="/v2/room/{position}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> getV2(@PathVariable(value = "position") String position   )
    {
        Room room = roomRepository.getRoom(position);
        if (Objects.isNull(room)) {
            return new ResponseEntity<>( "Room not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (new Gson().toJson(room),HttpStatus.OK);
    }
    /**
     * Post room at position
     *
     * EXAMPLE:
     *
     * POST http://localhost:8080/v2/room/(1,0,0)
     * Content-Type: application/json
     * Accept: application/json
     *
     * {
     *    "description" : "Yppiekieae",
     *    "creatorId" : "PHG"
     * }
     *
     */
    @PostMapping(path="/v2/room/{position}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> postV2(@PathVariable String position, @RequestBody Room room) {

        try {
            room = roomRepository.updateRoom(position, room);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

            return ResponseEntity.created(location).body(new Gson().toJson(room));
        } catch (RoomRepositoryException e) {
            return new ResponseEntity<>( e.toString() ,HttpStatus.UNAUTHORIZED);
        }
    }



    /**
     * Get request for room at position
     *
     * EXAMPLE:
     *
     * GET http://localhost:8080/v1/room/{1,1,1}
     */
    @Deprecated
    @GetMapping(path="/v1/room/{position}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> getRoom(@PathVariable(value = "position") String position   )
    {
        Room room = roomRepository.getRoom(position);
        if (Objects.isNull(room)) {
            return new ResponseEntity<>( "Room not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (new Gson().toJson(room),HttpStatus.OK);
    }

    /**
     * Post room at position
     *
     * EXAMPLE:
     *
     * POST http://localhost:8080/v1/room/(1,0,0)
     * Content-Type: application/json
     * Accept: application/json
     *
     * {
     *    "description" : "Yppiekieae",
     *    "creatorId" : "PHG"
     * }
     *
     */
    @Deprecated
    @PostMapping(path="/v1/room/{position}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> updateRoom(@PathVariable String position, @RequestBody Room room) {

        try {
            room = roomRepository.updateRoom(position, room);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

            return ResponseEntity.created(location).body(new Gson().toJson(room));
        } catch (RoomRepositoryException e) {
            return new ResponseEntity<>( e.toString() ,HttpStatus.UNAUTHORIZED);
        }
    }
}
