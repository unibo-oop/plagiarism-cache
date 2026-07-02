package model.entities;

/**
 * Models all entities in the game environment.
 */
public interface Entity {

    /**
     * Allows to move forward.
     */
    void moveUp();

    /**
     * Allows to move backwards.
     */
    void moveDown();

    /**
     * Allows to move left.
     */
    void moveLeft();

    /**
     * Allows to move right.
     */
    void moveRight();

    /**
     * @return The height of the entity
     */
    double getHeight();

    /**
     * @return The width of the entity
     */
    double getWidth();

    /**
     * @return The entity's position in the environment
     */
    Position getPosition();

    /**
     * @return The entity's environment
     */
    Environment getEnvironment();

}
