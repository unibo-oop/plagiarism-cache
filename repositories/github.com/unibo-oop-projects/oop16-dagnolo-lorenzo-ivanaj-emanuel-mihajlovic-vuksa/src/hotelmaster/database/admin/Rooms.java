package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.exceptions.RoomRemovalException;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.structure.Room;
/**
 * Manages the operations on the rooms (administrator level).
 */
public interface Rooms {

    /**
     * Insert a new {@link Room} into the database.
     * @param r the room to be inserted
     * @throws RoomRemovalException if the extra is already associated with that room
     * @throws IllegalArgumentException if the room already exists 
     */
    void createRoom(Room r) throws RoomRemovalException, IllegalArgumentException;

    /**
     * Modify the room's type.
     * @param r the room that has to be modified
     * @throws SQLException if a database access error occurs
     */
    void modifyTypeOfRoom(Room r);

    /**
     * Associate an existing extra with a room. 
     * @param r the room
     * @param extra the extra to be inserted
     * @throws IllegalArgumentException if the extra cannot be associated with that room
     */
    void addExtra(Room r, RoomExtraPriceDescriber extra) throws IllegalArgumentException;

    /**
     * Remove an existing room from the database.
     * @param r the room to be removed
     * @throws RoomRemovalException if the room cannot be removed
     */
    void removeRoom(Room r) throws RoomRemovalException;
    /**
     * Remove an extra associated with a room.
     * @param r the room
     * @param extra the extra to be removed
     * @throws RoomRemovalException if that extra cannot be removed from the room
     */
    void removeExtra(Room r, RoomExtraPriceDescriber extra) throws RoomRemovalException;

}