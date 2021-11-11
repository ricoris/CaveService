package dk.msdo.caveservice.repositories;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.exceptions.RoomRepositoryException;

public interface RoomRepository {
    Room updateRoom(String position, Room roomToUpdate) throws RoomRepositoryException;
    Room getRoom(String position);
}