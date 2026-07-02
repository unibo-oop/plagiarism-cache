package fargoal.model.entity.commons.impl;

import fargoal.model.entity.commons.api.Health;

/**
 * Health class, it contains some essential features.
 */
public class HealthImpl implements Health {

    private Integer maxHealth;
    private Integer currentHealth;

    /**
     * Creates a new instance of Health, the initial health value
     * equals maxHealth.
     * 
     * @param amount - the maximum health value
     */
    public HealthImpl(final Integer amount) {
        this.maxHealth = amount;
        this.currentHealth = amount;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getCurrentHealth() {
        return currentHealth;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getMaxHealth() {
        return this.maxHealth;
    }

    /** {@inheritDoc} */
    @Override
    public void decreaseHealth(final Integer amount) {
        this.currentHealth = this.currentHealth - amount;
    }

    /** {@inheritDoc} */
    @Override
    public void increaseHealth(final Integer amount) {
        this.currentHealth = this.currentHealth + amount;
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setHealth(final Integer amount) {
        this.currentHealth = amount;
    }

    /**{@inheritDoc} */
    @Override
    public void setMaxHealth(final Integer amount) {
        this.maxHealth = amount;
    }

    /** {@inheritDoc} */
    @Override
    public void setToMaxHealth() {
        this.currentHealth = maxHealth;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isHealthy() {
        return this.currentHealth.equals(this.maxHealth);
    }
}
