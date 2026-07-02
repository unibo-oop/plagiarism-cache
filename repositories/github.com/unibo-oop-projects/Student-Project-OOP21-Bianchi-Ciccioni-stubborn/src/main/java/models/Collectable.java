package models;

/**
 * The Collectable Interface is an extension of Entity Interface.
 * It gives contracts in order to create a specific type of entity called "Collectable".
 * Those entities can be picked up by the player to increase its score and are not dangerous.
 */
public interface Collectable extends Entity {

    /**
     * Get the number of points of a specific Collectable, increasing the score of the player.
     * 
     * @return Number of points for picking up the Collectable
     */
     int getPoints();

}
