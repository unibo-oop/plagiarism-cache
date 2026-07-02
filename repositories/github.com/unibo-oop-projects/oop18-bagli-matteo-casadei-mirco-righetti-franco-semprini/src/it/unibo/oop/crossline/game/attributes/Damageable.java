package it.unibo.oop.crossline.game.attributes;

/**
 * This interface represents an entity that can be damaged.
 */
public interface Damageable {

    /**
     * Get the current health.
     * @return the amount of remaining health
     */
    float getHealth();

    /**
     * Apply the specified damage.
     * @param damage the damage to apply
     */
    void applyDamage(float damage);

}
