package zombietsunami.model.obstaclemodel.impl;

import zombietsunami.model.obstaclemodel.api.Bomb;

/**
 * Class implementing the Bomb functionalities.
 * 
 * @see zombietsunami.model.obstaclemodel.api.Bomb
 * @see zombietsunami.model.obstaclemodel.api.Obstacle
 */
public class BombImpl implements Bomb {

    private final ObstacleEntity entity = new ObstacleEntity();

    private int damage = 1;

    /**
     * Gets the X coordinate of the Bomb.
     */
    @Override
    public int getX() {
        return this.entity.getX();
    }

    /**
     * Gets the Y coordinate of the Bomb.
     */
    @Override
    public int getY() {
        return this.entity.getY();
    }

    /**
     * Sets the X coordinate of the Bomb.
     */
    @Override
    public void setX(final int x) {
        this.entity.setX(x);
    }

    /**
     * Sets the Y coordinate of the Bomb.
     */
    @Override
    public void setY(final int y) {
        this.entity.setY(y);
    }

    /**
     * Sets the damage of the bomb.
     * @param damage the damage of the bomb.
     */
    @Override
    public void setDamage(final int damage) {
        if (damage > 0) {
            this.damage = damage;
        }
    }

    /**
     * Returns the damage of the bomb.
     * @return the bombs damage.
     */
    @Override
    public int getDamage() {
        return this.damage;
    }
}
