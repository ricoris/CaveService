package dk.msdo.caveservice.repositories;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.exceptions.InvalidCreatorException;

public interface RoomRepository {
    Room updateRoom(String position, Room roomToUpdate) throws InvalidCreatorException;
    Room getRoom(String position);
}