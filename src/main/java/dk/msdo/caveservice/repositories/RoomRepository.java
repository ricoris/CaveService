package dk.msdo.caveservice.repositories;
import dk.msdo.caveservice.domain.Room;

public interface RoomRepository {
    public void updateRoom(String position, Room newRoom) ;
    public Room getRoom(String position);
}