package model.map;

import java.util.List;

import model.utils.Direction;

/**
 * 
 * Represents the map of the game, made up of all the GameRooms created.
 *
 */
public interface Map {

    /**
     * Change the current room based on the door provided as direction. It
     * represents the door the player have passed through.
     * 
     * @param c
     *            The direction where the door is.
     */
    void changeRoomByDoor(Direction c);

    /**
     * Get all the rooms created in the map.
     * 
     * @return An unmodifiable list of Room.
     */
    List<Room> getAllRooms();

    /**
     * Get the current room.
     * 
     * @return The current Room.
     */
    Room getCurrentRoom();

    /**
     * Check if the current room is the boss room.
     * 
     * @return A boolean. True if the current room is the boss room, false
     *         otherwise.
     */
    boolean isBossRoom();

    /**
     * Check if the boss is alive.
     * 
     * @return A boolean. True if the boss is dead, false otherwise.
     */
    boolean isBossDead();

}