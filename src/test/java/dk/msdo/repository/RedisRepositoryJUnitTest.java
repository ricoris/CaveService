package dk.msdo.repository;

import dk.msdo.caveservice.Configuration;
import dk.msdo.caveservice.domain.Direction;
import dk.msdo.caveservice.domain.Point3;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.RedisDBRoomRepositoryImpl;
import dk.msdo.caveservice.repositories.RoomRepository;
import dk.msdo.caveservice.repositories.exceptions.RoomRepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static dk.msdo.caveservice.repositories.RoomRepository.p000;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


/** TDD of RoomRepository interface and
 * driving the MemoryRepository implementation.
 *
 * @author Team Alpha, Aarhus University.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest( classes = {RedisDBRoomRepositoryImpl.class,
                            Configuration.class},
                 properties = {"storage.room=redisStorage"})
class RedisRepositoryJUnitTest extends RedisTestContainer {

  @Autowired
  RedisDBRoomRepositoryImpl storage;

  @Test
  public void shouldReadInitialRoomsInStorage() throws RoomRepositoryException {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldReadInitialRoomsInStorage();
  }

  @Test
  public void shouldNotCreatedUnConnectedRoomPosition() throws RoomRepositoryException {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldNotCreatedUnConnectedRoomPosition();
  }

  @Test
  public void shouldNotCreatedOnExistingPosition() {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldNotCreatedOnExistingPosition();
  }


  @Test
  public void shouldCreateAndUpdateRoom() throws RoomRepositoryException {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldCreateAndUpdateRoom();
  }

  @Test
  public void shouldRejectInvalidPositionString() {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldRejectInvalidPositionString();
  }

  @Test
  public void shouldNotGetExitSet() {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldNotGetExitSet();
  }

  @Test
  public void shouldGetExitSet() {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldGetExitSet();
  }

  @Test
  public void shouldNotAllowUpdateOfNonExistingRoom() {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldNotAllowUpdateOfNonExistingRoom();
  }

  @Test
  public void shouldNotAllowNonCreatorToUpdateRoom() throws RoomRepositoryException {
    RepositoryJUnitTest junit = new RepositoryJUnitTest(storage);
    junit.shouldNotAllowNonCreatorToUpdateRoom();
  }
}
