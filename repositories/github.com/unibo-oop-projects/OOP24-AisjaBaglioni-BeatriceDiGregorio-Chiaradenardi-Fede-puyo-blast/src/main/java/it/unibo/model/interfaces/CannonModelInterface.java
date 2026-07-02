package it.unibo.model.interfaces;

/**
 * Interface representing a model for controlling the cannon in the game.
 * Provides methods to move the cannon, change its angle, and get its position and orientation.
 */
public interface CannonModelInterface {

    /**
     * Moves the cannon to the left.
     * This will update the position of the cannon along the x-axis.
     */
    void moveLeft();

    /**
     * Moves the cannon to the right.
     * This will update the position of the cannon along the x-axis.
     */
    void moveRight();

    /**
     * Gets the current x-coordinate of the cannon.
     * 
     * @return The x-coordinate of the cannon.
     */
    double getX();

    /**
     * Aims the cannon upwards, adjusting its angle.
     * This will increase the cannon's aim angle, moving it in the upward direction.
     */
    void aimUp();

    /**
     * Aims the cannon downwards, adjusting its angle.
     * This will decrease the cannon's aim angle, moving it in the downward direction.
     */
    void aimDown();

    /**
     * Gets the current angle at which the cannon is aimed.
     * 
     * @return The angle of the cannon in degrees.
     */
    double getAngle();
}
