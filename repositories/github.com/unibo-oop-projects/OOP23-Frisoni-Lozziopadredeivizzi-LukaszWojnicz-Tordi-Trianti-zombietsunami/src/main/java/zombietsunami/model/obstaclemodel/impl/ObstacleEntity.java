package zombietsunami.model.obstaclemodel.impl;

/**
 * Defines the entity object of an Obstacle.
 */
public class ObstacleEntity {

    private int x, y;

    /**
     * Gets the X coordinate of the Entity.
     * @return the X coordinate of the Entity
    */
    public int getX() {
        return x;
    }

    /**
     * Sets the X coordinate of the Entity.
     * @param x the X coordinate that should be set.
    */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate of the Entity.
     * @return the Y coordinate of the Entity
    */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate of the Entity.
     * @param y the Y coordinate that should be set.
    */
    public void setY(final int y) {
        this.y = y;
    }

}
