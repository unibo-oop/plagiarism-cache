package it.unibo.aknightstale.models.entity;

public interface LifeEntity extends Entity, DefenseEntity {
    /**
     * Sets the entity health.
     *
     * @param health the new entity health.
     */
    void setHealth(double health);

    /**
     * Gets the entity current health.
     *
     * @return the entity current health.
     */
    double getHealth();

    /**
     * Gets the entity maximum health.
     *
     * @return the entity maximum health.
     */
    double getMaxHealth();

    /**
     * Checks if the entity is died or not.
     *
     * @return true if it's died, false if it's alive.
     */
    boolean isDead();
}
