package model.entities;

/**
 * 
 * Represents all the bullets of the game.
 *
 */
public interface Bullet extends Movable {

    /**
     * Get the value of the range of this bullet.
     * @return The range.
     */
    double getRange();
    /**
     * Check if the bullet has gone out of range.
     * 
     * @return A boolean. True if the bullet is out of range, false otherwise.
     */
    boolean isDead();

}