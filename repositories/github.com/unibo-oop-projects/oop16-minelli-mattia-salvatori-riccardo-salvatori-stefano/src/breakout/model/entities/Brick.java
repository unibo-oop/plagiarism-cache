package breakout.model.entities;

import breakout.model.physics.GameObject;

/**
 * An Interface that describes a Brick and it's behavior.
 *
 */

public interface Brick extends GameObject {

    /**
     * @return the brick's life minus the number of hits taken.
     */
    int getRemainingLife();

    /**
     * @return the point given by the destruction of the brick.
     */
    int getBrickValue();

    /**
     * @return a brick type specified in the enum {@link BrickType}.
     */
    BrickType getType();

    /**
     * A method that must be called when an object collides with the brick.
     */
    void hit();

    /**
     * Determine if the brick is destroyed or not.
     * 
     * @return true if the brick is destoryed.<br/>
     *         false otherwise.
     */
    boolean isDead();

}
