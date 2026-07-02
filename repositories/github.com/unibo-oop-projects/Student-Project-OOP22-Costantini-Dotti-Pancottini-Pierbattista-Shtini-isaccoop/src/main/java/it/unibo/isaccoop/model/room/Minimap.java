package it.unibo.isaccoop.model.room;

import java.util.List;

/**
 * Interface that models the in-game minimap,
 * that represents the layout of the {@link Level}.
 */
public interface Minimap {

    /**
     * Get the current room (where the player is).
     * @return the current room
     */
    Room getCurrentRoom();

    /**
     * Get an unmodifiable list of all completed rooms.
     * @return an unmodifiable list containing all completed rooms
     */
    List<Room> getCompletedRooms();

    /**
     * Get an unmodifiable list of all uncompleted rooms.
     * @return an unmodifiable list containing all uncompleted rooms
     */
    List<Room> getUncompletedRooms();
}
