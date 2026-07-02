package ballblast.model.physics;

import java.util.List;

/**
 * Represents the manager of the collision between objects during the game
 * session.
 */
public interface CollisionManager {

    /**
     * The main method to check if there are collision between GameObjects.
     */
    void checkLoop();

    /**
     * Add a new {@link Collidable} object to the list.
     * 
     * @param coll the {@link Collidable} object to add in the list.
     */
    void addCollidable(Collidable coll);

    /**
     * Remove a {@link Collidable} object from the list.
     * 
     * @param coll the {@link Collidable} object to remove form the list.
     */
    void removeCollidable(Collidable coll);

    /**
     * The getter for the attached collidables list.
     * 
     * @return the {@link List} of {@link Collidable} objects.
     */
    List<Collidable> getCollidables();

}
