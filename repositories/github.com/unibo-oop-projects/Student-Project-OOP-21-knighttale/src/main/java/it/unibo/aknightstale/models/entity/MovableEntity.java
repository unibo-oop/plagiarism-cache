package it.unibo.aknightstale.models.entity;

public interface MovableEntity extends Entity {
    /**
     * Gets the entity speed.
     * 
     * @return the entity speed.
     */
    double getSpeed();

    /**
     * Sets the entity speed.
     * 
     * @param speed the new entity speed.
     */
    void setSpeed(double speed);

    /**
     * Gets the entity direction.
     * 
     * @return the entity direction.
     */
    Direction getDirection();

    /**
     * Sets the entity direction.
     * 
     * @param dir the new direction.
     */
    void setDirection(Direction dir);

    /**
     * Moves up the entity.
     */
    void goUp();

    /**
     * Moves down the entity.
     */
    void goDown();

    /**
     * Moves left the entity.
     */
    void goLeft();

    /**
     * Moves right the entity.
     */
    void goRight();
}
