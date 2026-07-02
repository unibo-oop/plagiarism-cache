package zombietsunami.model.obstaclemodel.impl;

import zombietsunami.model.obstaclemodel.api.Breakable;

/**
 * Class implementing the Bomb functionalities.
 * 
 * @see zombietsunami.model.obstaclemodel.api.Breakable
 * @see zombietsunami.model.obstaclemodel.api.Obstacle
 */
public class BreakableImpl extends ObstacleEntity implements Breakable {

    private final ObstacleEntity entity = new ObstacleEntity();

    private final int minforce;

    /**
     * Constructor that sets the default values for the Breakable.
     * 
     * @param minforce the minimum force to break the Breakable.
     */
    public BreakableImpl(final int minforce) {
        this.minforce = minforce;
    }

    /**
     * Gets the X coordinate of the Breakable.
     */
    @Override
    public int getX() {
        return this.entity.getX();
    }

    /**
     * Gets the Y coordinate of the Breakable.
     */
    @Override
    public int getY() {
        return this.entity.getY();
    }

    /**
     * Sets the X coordinate of the Breakable.
     */
    @Override
    public void setX(final int x) {
        this.entity.setX(x);
    }

    /**
     * Sets the Y coordinate of the Breakable.
     */
    @Override
    public void setY(final int y) {
        this.entity.setY(y);
    }

    /**
     * 
     */
    @Override
    public int getMinForce() {
        return this.minforce;
    }

    /**
     * Method that checks if the zombie can break the obstacle.
     * @return true if the zombies force is greater than the obstacle one
     * false otherwise.
     * @param zombieForce the force of the zombie.
     */
    @Override
    public boolean canBreakObstacle(final int zombieForce) {
        return this.getMinForce() <= zombieForce;
    }
}
