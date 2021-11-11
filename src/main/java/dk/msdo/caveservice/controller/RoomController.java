package dk.msdo.caveservice.controller;

import com.google.gson.Gson;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.exceptions.InvalidCreatorException;
import dk.msdo.caveservice.repositories.RoomRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
public class RoomController {

    private final RoomRepositoryImpl roomRepository;

    public RoomController(RoomRepositoryImpl roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping(path="/room/{position}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> getRoom(@PathVariable(value = "position") String position   )
    {
        Room room = roomRepository.getRoom(position);
        if (Objects.isNull(room)) {
            return new ResponseEntity<>( "Room not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (new Gson().toJson(room),HttpStatus.OK);
    }

    @PostMapping(path="/room/{position}",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> updateRoom(@PathVariable String position, @RequestBody Room room) {

        try {
            room = roomRepository.updateRoom(position, room);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

            return ResponseEntity.created(location).body(new Gson().toJson(room));
        } catch (InvalidCreatorException e) {
            return new ResponseEntity<>( e.toString() ,HttpStatus.UNAUTHORIZED);
        }
    }
}
