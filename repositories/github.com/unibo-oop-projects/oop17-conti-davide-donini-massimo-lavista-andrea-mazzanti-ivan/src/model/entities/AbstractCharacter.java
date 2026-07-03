package model.entities;

import javafx.scene.shape.Shape;
import model.entities.properties.Velocity;

/**
 * This class factories the common methods between Spaceship's class and enemy's
 * class.
 * 
 */
public abstract class AbstractCharacter extends AbstractEntity implements Character {

    private static final double MIN_LIFE = 0.0;

    private double life;
    private final double maxLife;
    private final double colissionDamage;
    private int counterElapsed;

    /**
     * @param shape
     *            initial entity's shape.
     * @param velocity
     *            initial entity's velocity.
     * @param life
     *            initial entity's life.
     * @param maxLife
     *            maximum life that the entity can take on.
     * @param collisionDamage
     *            damage issued from the collision with other entity.
     */
    public AbstractCharacter(final Shape shape, final Velocity velocity, final double life, final double maxLife,
            final double collisionDamage) {
        super(shape, velocity);
        this.life = life;
        this.maxLife = maxLife;
        this.colissionDamage = collisionDamage;
        this.counterElapsed = 0;
    }

    /**
     * Get the health of this entity.
     * 
     * @return the health
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * Get the max health of this entity.
     * 
     * @return the max health
     */
    @Override
    public double getMaxLife() {
        return this.maxLife;
    }

    /**
     * The method called when this entity has been damaged.
     * 
     * @param decrease
     *            The quantity of life to decrease
     */
    @Override
    public void decreaseLife(final double decrease) {
        final double updateLife = this.life - decrease;
        this.life = updateLife > MIN_LIFE ? updateLife : MIN_LIFE;
    }

    /**
     * The method called when this entity regenerate the health.
     * 
     * @param increment
     *            The quantity of life to increment
     */
    @Override
    public void incrementLife(final double increment) {
        final double updateLife = this.life + increment;
        this.life = updateLife < this.maxLife ? updateLife : this.maxLife;
    }

    /**
     * 
     * @return the damage issued from the collision with other entity.
     */
    @Override
    public double getCollisionDamage() {
        return this.colissionDamage;
    }

    /**
     * 
     * @return the sum of the elapsed.
     */
    @Override
    public int getCounterElapsed() {
        return this.counterElapsed;
    }

    /**
     * Set the variable 'counterElapsed'.
     * @param counterElapsed 
     *                  to set
     */
    @Override
    public void setCounterElapsed(final int counterElapsed) {
        this.counterElapsed = counterElapsed;
    }
}
