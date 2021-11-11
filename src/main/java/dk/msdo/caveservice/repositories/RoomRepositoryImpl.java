package dk.msdo.caveservice.repositories;
import javax.annotation.Resource;

import dk.msdo.caveservice.common.NowStrategy;
import dk.msdo.caveservice.common.RealNowStrategy;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.exceptions.InvalidCreatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private static final Logger logger = LoggerFactory.getLogger(RoomRepositoryImpl.class);
    private final String hashReference = "Room";

    @Resource(name = "roomTemplate")          // 'redisTemplate' is defined as a Bean in Configuration.java
    private HashOperations<String, String, Room> roomOperations;

    @Override
    public Room updateRoom(String position, Room roomToUpdate) throws InvalidCreatorException {
        //creates one record in Redis DB if record with that Id is not present

        // Get a room - we need to know if it is a new or existing room
        Room existingRoom = getRoom(position);

        if (Objects.isNull(existingRoom)){
            // if room does not exist - it's new
            Room newRoom = new Room();

            // Using a UUID cause i'm lazy and it works
            newRoom.setId(UUID.randomUUID().toString());

            // Set creation time
            NowStrategy n = new RealNowStrategy();
            newRoom.setCreationTimeISO8601(n.now().toString());

            // Set value
            newRoom.setCreatorId(roomToUpdate.getCreatorId());
            newRoom.setDescription(roomToUpdate.getDescription());

            // Store it in the repository
            roomOperations.put(hashReference, position, newRoom);
            return newRoom;
        } else if (roomToUpdate.getCreatorId().equals(existingRoom.getCreatorId())) {
                // Room exists - just update description.
                existingRoom.setDescription(roomToUpdate.getDescription());
                roomOperations.put(hashReference, position, existingRoom);
                return existingRoom;
        } else
            throw new InvalidCreatorException("Creator ID " + existingRoom.getCreatorId() + "does not match that of the existing room at position " + position);
    }

    @Override
    public Room getRoom(String position) {
        return roomOperations.get(hashReference, position);
    }

}
