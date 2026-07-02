package frogger.model.interfaces;

import java.util.List;

import frogger.model.implementations.Eagle;

/**
 * Model the concept of a level in the game.
 */
public interface Level {

    /**
     * Get the list of lanes.
     * @return the list of lanes
     */
    List<Lane> getLanes();

    /**
     * Get all the obstacles in the level.
     * @return a list of obstacles
     */
    List<MovingObject> getAllObstacles();

    /**
     * Add a lane to the list.
     * @param lane the lane to add
     */
    void addLane(Lane lane);

    /**
     * Add an eagle to the list.
     * @param eagle the eagle to add
     */
    void addEagle(Eagle eagle);

    /**
     * Get the list of eagles.
     * @return the list of eagles
     */
    List<Eagle> getEagles();

    /**
     * Get the list of pickable objects present in the current level.
     * @return the list
     */
    List<PickableObject> getPickableObjects();

    /**
     * Add a pickable objects to the list.
     * @param p the pickable objects to be added
     */
    void addPickableObject(PickableObject p);

    /**
     * Remove a PickableObject from the list.
     * @param p PickableObject up to be removed
     */
    void removePickableObject(PickableObject p);
}
