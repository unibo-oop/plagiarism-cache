package it.unibo.isaccoop.model.spawn;

import java.util.List;

import it.unibo.isaccoop.model.common.MapElement;

/**
 * Spawn interface to model entities delegates to spawn other entities.
 */
public interface Spawn {

    /**
     * Method that sets the positions of the elements inside the room.
     * @param elementsToSpawn
     * @param width of room
     * @param height of room
     */
    void setPosition(List<MapElement> elementsToSpawn, int width, int height);
}
