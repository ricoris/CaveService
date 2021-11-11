package dk.msdo.caveservice.controller;

import com.google.gson.Gson;
import dk.msdo.caveservice.domain.Direction;
import dk.msdo.caveservice.domain.Exit;
import dk.msdo.caveservice.repositories.ExitsRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
public class ExitController {
    private final ExitsRepositoryImpl exitsRepository;

    public ExitController(ExitsRepositoryImpl exitsRepository) {
        this.exitsRepository = exitsRepository;
    }

    @GetMapping(path="/room/{position}/exits", produces = "application/json")
    public ResponseEntity <String> getRoomExitsAtPosition(@PathVariable(value = "position") String position   )
    {
        Set<String> e =  exitsRepository.getAllExitsAtPosition(position);
        return new ResponseEntity<>(e.toString(),HttpStatus.OK);
    }

    @PostMapping(path="/room/{position}/exits",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity <String> addExitAtPosition(@PathVariable String position, @RequestBody Exit exit) {

        if (exitsRepository.addExitAtPosition(position, exit.getDirection())) {

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    //.path("/{position}")
                    //.buildAndExpand(position)
                    .build()
                    .toUri();

            return ResponseEntity.created(location).body(new Gson().toJson(exit));
        } else {
            return ResponseEntity.badRequest().body("Direction not valid");
                    //new ResponseEntity<String>( "Direction not valid",HttpStatus.BAD_REQUEST);
        }
    }

}
