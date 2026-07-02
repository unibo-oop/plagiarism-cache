package zombietsunami.model.obstaclemodel.api;

/**
 * Defines the Breakable object.
 */
public interface Breakable {

    /**
     * Sets the X coordinate of the breakable.
     * @param x the X coordinate.
     */
    void setX(int x);

    /**
     * Sets the Y coordinate of the breakable.
     * @param y the Y coordinate.
     */
    void setY(int y);

    /**
     * Gets the X coordinate of the Breakable.
     * @return the X coordinate of the Breakable.
     */
    int getX();

    /**
     * Gets the Y coordinate of the Breakable.
     * @return the Y coordinate of the Breakable.
     */
    int getY();

    /**
     * Returns the minimum force to break the obstacle.
     * @return the minimum force to break it the breakable.
     */
    int getMinForce();

    /**
     * Method that checks if the zombie can break the obstacle.
     * @return true if the zombies force is greater than the obstacle one
     * false otherwise.
     * @param zombieForce the force of the zombie.
     */
    boolean canBreakObstacle(int zombieForce);
}
