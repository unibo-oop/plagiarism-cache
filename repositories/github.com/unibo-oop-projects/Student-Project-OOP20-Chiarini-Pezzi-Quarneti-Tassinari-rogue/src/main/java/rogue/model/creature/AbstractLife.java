package rogue.model.creature;

import rogue.model.events.AbstractEventPublisher;

/**
 * A generic implementation for a creature {@link Life}.
 */
public abstract class AbstractLife extends AbstractEventPublisher implements Life {

    private int healthPoints;
    private int experience;

    /**
     * Creates a new AbstractLife.
     * @param healthPoints
     *          the health points value
     * @param experience
     *          the experience value
     */
    protected AbstractLife(final int healthPoints, final int experience) {
        this.healthPoints = healthPoints;
        this.experience = experience;
    }

    /**
     * Check if value is negative or not. 
     * @param value
     *          the value to check
     * @return the value given if it is positive, 0 otherwise
     */
    protected int checkNotNegative(final int value) {
        return value < 0 ? 0 : value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hurt(final int damage) {
        this.healthPoints = checkNotNegative(this.healthPoints - damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    /**
     * Set the health points to the given value.
     * @param healthPoints
     *          the healthPoints
     */
    protected void setHealthPoints(final int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getExperience() {
        return this.experience;
    }

    /**
     * Set the experience to the given value.
     * @param experience
     *          the experience
     */
    protected void setExperience(final int experience) {
        this.experience = experience;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.healthPoints == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "AbstractLife [healthPoints=" + healthPoints + ", experience=" + experience + "]";
    }

}
