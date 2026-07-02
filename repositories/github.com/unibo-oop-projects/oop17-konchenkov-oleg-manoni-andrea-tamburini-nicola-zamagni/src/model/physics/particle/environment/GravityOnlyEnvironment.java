package model.physics.particle.environment;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * Represents the environment where the physical phenomenon takes place. In this
 * environment there is an uniform gravitational field.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class GravityOnlyEnvironment implements Environment {

    private final Vector2D gravitationalAcceleration;

    /**
     *
     * Constructor.
     *
     * @param gravitationalAcceleration
     *            gravitational acceleration, in metres per second squared
     */
    protected GravityOnlyEnvironment(final Vector2D gravitationalAcceleration) {
        this.gravitationalAcceleration = gravitationalAcceleration;
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
     * Environment builder.
     *
     * @author Nicola Zamagni
     *
     */
    public static class Builder {

        private Vector2D gravitationalAcceleration;

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
         * @return environment
         */
        public GravityOnlyEnvironment build() {
            if (gravitationalAcceleration == null) {
                throw new IllegalStateException();
            }
            return new GravityOnlyEnvironment(gravitationalAcceleration);
        }

    }
}
