package it.unibo.oop.utilities;

import it.unibo.oop.model.Entity;

/**
 * Represents the speed and the acceleration of an {@link Entity}.
 */
public class Velocity {

    private final double stillVelocity = 0;

    private final double maxVelocity;
    private final double minVelocity;
    private final double velocityScale;

    public Velocity(final double min, final double max, final double accelerationTime) {
        this.minVelocity = min;
        this.maxVelocity = max;
        this.velocityScale = (this.maxVelocity - this.minVelocity) / accelerationTime;
    }

    /**
     * Accelerates the currentVelocity to his new velocity basing on the
     * velocityScale.
     */
    public double slow(final double currentVelocity) {
        final double newVelocity = currentVelocity - velocityScale;
        return newVelocity < minVelocity ? stillVelocity : newVelocity;
    }

    /**
     * Decelerates the currentVelocity to his new velocity basing on the
     * velocityScale.
     */
    public double accelerate(final double currentVelocity) {
        double newVelocity = currentVelocity + velocityScale;
        // If it goes over the top speed it caps to it
        newVelocity = newVelocity > maxVelocity ? maxVelocity : newVelocity;
        // If it's unde the min speed it starts form the min speed
        newVelocity = newVelocity < minVelocity ? minVelocity : newVelocity;
        return newVelocity;
    }

    /**
     * Getter for the maxVelocity of the {@link Entity}.
     * 
     * @return
     */
    public double getMaxVelocity() {
        return this.maxVelocity;
    }

    /**
     * Getter for the minVelocity of the {@link Entity}.
     * 
     * @return
     */
    public double getMinVelocity() {
        return this.minVelocity;
    }
}
