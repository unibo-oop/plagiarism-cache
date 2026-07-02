package spacesurvival.model.collision;

import spacesurvival.model.World;
import spacesurvival.model.worldevent.WorldEvent;

/**
 * Implementing this interface allows the object to handle a collision with another object.
 */
public interface Collideable {
    /**
     * This method handle the collision based on the world event.
     * 
     * @param world the world instance of the game
     * @param worldEvent the event that launched this method
     */
    void collided(World world, WorldEvent worldEvent);
}
