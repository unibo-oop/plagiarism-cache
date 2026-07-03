package model.entities;

/**
 * An interface modeling Mario, the main character of the game, with methods to
 * check whether or not the character is doing certain actions.
 * 
 */
public interface Mario extends DynamicEntity {

    /**
     * Returns whether or not Mario is currently jumping.
     * 
     * @return true if Mario is jumping, false otherwise.
     */
    boolean isJumping();

    /**
     * Returns whether or not Mario is currently moving.
     * 
     * @return true if Mario is moving, false otherwise.
     */
    boolean isMoving();

    /**
     * Tells Mario to stop moving in the dir direction. If Mario is currently not
     * moving, the method does nothing.
     * 
     * @param dir
     *            The dir representing the movement that should be stopped.
     */
    void stopMoving(Movement dir);
}
