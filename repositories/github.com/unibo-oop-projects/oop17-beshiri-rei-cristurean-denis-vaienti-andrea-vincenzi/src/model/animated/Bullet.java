package model.animated;

/**
 * Interface that provides methods common to all bullets.
 */
public interface Bullet extends Animated {

    /**
     * Checks if the bullet has gone out of range.
     * @return True if has gone out of range, false otherwise.
     */
    boolean isDead();

    /**
     * Getter for bullet damage.
     * @return bullet damage.
     */
    int getDamage();
}
