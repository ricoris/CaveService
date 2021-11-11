package dk.msdo.caveservice.repositories;
import javax.annotation.Resource;

import dk.msdo.caveservice.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    private static final Logger logger = LoggerFactory.getLogger(RoomRepositoryImpl.class);

    private final String hashReference = "Room";

    @Resource(name = "roomTemplate")          // 'redisTemplate' is defined as a Bean in Configuration.java
    private HashOperations<String, String, Room> hashOperations;

    @Override
    public void updateRoom(String position, Room room) {
        //creates one record in Redis DB if record with that Id is not present
        logger.info("Update room redis");

        hashOperations.put(hashReference, position, room);
    }

    @Override
    public Room getRoom(String position) {
        return hashOperations.get(hashReference, position);
    }

}
