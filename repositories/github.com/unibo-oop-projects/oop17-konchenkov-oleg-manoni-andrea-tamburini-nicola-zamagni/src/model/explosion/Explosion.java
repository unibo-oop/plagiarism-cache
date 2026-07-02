package model.explosion;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Represents an explosion.
 *
 * @author Oleg
 *
 */
public interface Explosion {

    /**
     *
     * @param timeStep
     *            time step
     * @throws IllegalArgumentException
     *             time step must be non negative
     */
    void update(double timeStep) throws IllegalArgumentException;

    /**
     *
     * @return explosion time.
     */
    double getExplosionTime();

    /**
     *
     * @return blast radius.
     */
    double getBlastRadius();

    /**
     *
     * @return explosion position.
     */
    Vector2D getPosition();

    /**
     *
     * @return current blast radius
     */
    double getCurrentBlastRadius();

    /**
     *
     * @param timeStep
     *            time step
     * @return true if the apex of the explosion is reached in the current time
     *         step, false otherwise.
     */
    boolean isApexTime(double timeStep);

    /**
     *
     * @return true if the explosion is ended, false otherwise.
     */
    boolean isExploded();

    /**
     *
     * @return true if the apex of the explosion is reached, false otherwise.
     */
    boolean isApexReached();

}
