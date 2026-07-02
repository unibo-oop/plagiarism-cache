package it.tbt.model.world.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface for objects which model a World, entity which can contain Room and permit operations on them.
 */
public interface World {
    /**
     * @param room the Room that shall be added to the World.
     */
    void addRoom(Room room);
    /**
     * @return the Room(s) as a List.
     */
    List<Room> getListRoom();
    /**
     * @return The Room that is the entry-point in the World.
     */
    Optional<Room> getStartRoom();
    /**
     * @param room the room that is to be the entry-point in the World.
     */
    void setStartRoom(Room room);
}
