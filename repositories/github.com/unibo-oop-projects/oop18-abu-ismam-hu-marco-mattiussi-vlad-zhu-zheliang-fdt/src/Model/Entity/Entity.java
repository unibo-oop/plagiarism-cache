package model.entity;

import utilityclasses.Pair;
/**
 * interface for every entity in the game.
 */
public interface Entity {
    /**
     *  Method to get the position of the entity.
     * @return a Pair that describes the position of the entity
     */
    Pair<Integer, Integer> getLocation();

    /**
     * 
     * Each entity has its own way to "update" itself,
     * and this method describes its behaviour.
     */
    void update();

    /**
     * 
     * @return true if the entity should be removed, false otherwise
     */
    boolean shouldBeRemoved();
}
