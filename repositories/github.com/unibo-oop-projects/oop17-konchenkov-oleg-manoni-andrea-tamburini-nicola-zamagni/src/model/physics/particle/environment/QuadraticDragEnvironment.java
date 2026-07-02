package model.physics.particle.environment;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * Represents the environment where the physical phenomenon takes place. In this
 * environment there is a fluid and an uniform gravitational field.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class QuadraticDragEnvironment implements Environment {

    private final Vector2D gravitationalAcceleration;
    private final double fluidDensity;
    private final Vector2D fluidVelocity;

    /**
     *
     * Constructor.
     *
     * @param gravitationalAcceleration
     *            gravitational acceleration, in metres per second squared
     * @param fluidDensity
     *            fluid density, in kilograms per cubic metre
     * @param fluidVelocity
     *            fluid velocity, in metres per second
     * @throws IllegalArgumentException
     *             fluid density must be positive
     */
    protected QuadraticDragEnvironment(final Vector2D gravitationalAcceleration, final double fluidDensity,
            final Vector2D fluidVelocity) throws IllegalArgumentException {
        this.gravitationalAcceleration = gravitationalAcceleration;
        this.fluidDensity = fluidDensity;
        this.fluidVelocity = fluidVelocity;
        if (this.fluidDensity <= 0.0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * Returns the gravitational acceleration.
     *
     * @return gravitational acceleration, in metres per second squared
     */
    public Vector2D getGravitationalAcceleration() {
        return gravitationalAcceleration;
    }

    /**
     *
     * Returns the fluid density.
     *
     * @return fluid density, in kilograms per cubic metre
     */
    public double getFluidDensity() {
        return fluidDensity;
    }

    /**
     *
     * Returns the fluid velocity.
     *
     * @return fluid velocity, in metres per second
     */
    public Vector2D getFluidVelocity() {
        return fluidVelocity;
    }

    /**
     *
     * Environment builder.
     *
     * @author Nicola Zamagni
     *
     */
    public static class Builder {

        private Vector2D gravitationalAcceleration;
        private double fluidDensity;
        private Vector2D fluidVelocity;

        /**
         *
         * Sets the environment gravitational acceleration.
         *
         * @param gravitationalAcceleration
         *            gravitational acceleration, in metres per second squared
         * @return basic builder
         */
        public Builder setGravitationalAcceleration(final Vector2D gravitationalAcceleration) {
            this.gravitationalAcceleration = gravitationalAcceleration;
            return this;
        }

        /**
         *
         * Sets the environment fluid density.
         *
         * @param fluidDensity
         *            fluid density, in kilograms per cubic metre
         * @return basic builder
         */
        public Builder setFluidDensity(final double fluidDensity) {
            this.fluidDensity = fluidDensity;
            return this;
        }

        /**
         *
         * Sets the environment fluid velocity.
         *
         * @param fluidVelocity
         *            fluid velocity, in metres per second
         * @return basic builder
         */
        public Builder setFluidVelocity(final Vector2D fluidVelocity) {
            this.fluidVelocity = fluidVelocity;
            return this;
        }

        /**
         *
         * @return environment
         */
        public QuadraticDragEnvironment build() {
            if (gravitationalAcceleration == null || fluidVelocity == null) {
                throw new IllegalStateException();
            }
            return new QuadraticDragEnvironment(gravitationalAcceleration, fluidDensity, fluidVelocity);
        }

    }

}
