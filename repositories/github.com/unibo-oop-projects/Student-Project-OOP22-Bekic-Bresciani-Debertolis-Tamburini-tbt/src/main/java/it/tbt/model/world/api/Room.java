package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

import java.util.Set;

/**
 * Interface for a Room which is to be able to store entities and permit operations on them.
 */

public interface Room {
    /**
     * @param entity to be added to the Room.
     */
    void addEntity(SpatialEntity entity);

    /**
     * @return the entities in the Room as a Set.
     */
    Set<SpatialEntity> getEntities();

    /**
     * @return Height of the room.
     */
    int getHeight();

    /**
     * @return Width of the room.
     */
    int getWidth();

    /**
     * @param xCenter
     * @param yCenter
     * @param width
     * @param height
     * @return true if the rectangle formed by the four params does not exceed the Room boundaries.
     */
    Boolean isValidCoordinates(int xCenter, int yCenter, int width, int height);

}
