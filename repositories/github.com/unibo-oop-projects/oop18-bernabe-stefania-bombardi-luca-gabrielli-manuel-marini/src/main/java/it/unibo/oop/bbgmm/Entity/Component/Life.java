package it.unibo.oop.bbgmm.Entity.Component;

public interface Life {
    /**
     *
     * @return Maximum amount of life points for the entity.
     */
    int getMaxLifePoints();

    /**
     *
     * @return Current amount of life points.
     */
    int getCurrentLifePoints();

    /**
     *
     * @param damageAmount
     *                      Amount of damage inflicted to the entity
     */
    void damaged(int damageAmount);

    /**
     *
     * @param healAmount
     *                      Amount of heath points to heal
     */
    void healed(int healAmount);

    /**
     *
     * @return True if the entity is still alive
     */
    boolean isAlive();

    /**
     *
     * @return True if the entity is dead
     */
    boolean isDead();
}
