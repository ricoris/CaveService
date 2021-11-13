package dk.msdo.caveservice.repositories;
import dk.msdo.caveservice.domain.Room;
import dk.msdo.caveservice.repositories.exceptions.RoomRepositoryException;

import java.util.ArrayList;

/**
 * Interface for the Room Repository
 *
 * Author: Team Alpha
 */
public interface RoomRepository {
    String WILL_CROWTHER_ID = "0";

    /**
     * Initialize the cave - if room position (0,0,0) does not exist, create initial rooms
     */

    void initialize() ;

    /**
     * Update a room at position.
     *
     * If it is a new room it is validated if it is adjacent to
     * an existing room before creation.
     *
     * If it is an existing room, it is validated that it is the creator of the room
     * whom updates the room and if so, the description is updated.
     *
     * Input:  position (0,0,0) and RoomRecord from which to create or update the room
     * Output: Updated RoomRecord
     ***/
    Room updateRoom(String position, Room roomToUpdate) throws RoomRepositoryException;

    /**
     * Get room at position
     *
     * Input:  positionString (0,0,0)
     * Output: requested room if it exists otherwise it returns null
     */
    Room getRoom(String position);

    /**
     * Returns all exits at position
     *   Input:  positionString (0,0,0)
     *   Output: Exits at postion in the format of ArrayList<String>
     *
     *           Example ["NORTH", "SOUTH", "EAST"]
     */
    ArrayList<String> getAllExitsAtPosition(String position);

}