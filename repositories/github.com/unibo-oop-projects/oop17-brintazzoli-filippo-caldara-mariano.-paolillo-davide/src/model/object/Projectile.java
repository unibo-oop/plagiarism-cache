package model.object;

import model.utility.Direction;
import model.utility.Pair;

/**
 * Model Interface of a Projectile.
 */
public interface Projectile {
    /**
     * Getter for position.
     * 
     * @return the position in x and y in Double
     * @see Pair
     */
    Pair<Double, Double> getPosition();

    /**
     * Bounce a projectile in opposite direction.
     * 
     * @param dir
     *            the direction of the border where projectile bounce
     * @see Direction
     */
    void bounce(Direction dir);
    /**
     * Set dead projectile.
     */
    void setDead();
    /**
     * Getter for {@link #isAlive}.
     * 
     * @return a boolean, true if is alive, false if this projectile have collided
     */
    boolean isAlive();
    /**
     * Get projectile's dimension.
     * 
     * @return {@link Pair} according to {@link #dimension} of Projectile
     */
    Pair<Double, Double> getBounds();
    /**
     * update position of projectile according to speed.
     */
    void update();
}
