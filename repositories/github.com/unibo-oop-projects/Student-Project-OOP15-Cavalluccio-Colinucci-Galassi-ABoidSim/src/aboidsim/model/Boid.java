package aboidsim.model;

import java.util.Set;

import aboidsim.util.Vector;

/**
 *
 * General Boid Interface.
 *
 */

interface Boid {

    /**
     * Increment boid life when eating.
     */
    void incrementLife();

    /**
     * decrement life each loop iteration.
     */
    void decrementLife();
    
    /**
     * decrement life when a boid is eaten.
     */
    void decrementLifeWhenEaten();

    /**
     *
     * @return Is boid hungry or not
     */
    boolean isHungry();

    /**
     *
     * @return if a boid is a predator or not.
     */
    boolean isPredator();

    /**
     *
     * @return if a boid is a tree or not.
     */
    boolean isNotTree();

    /**
     *
     * @return boid level
     */
    int getLevel();

    /**
     *
     * @param boid
     *            boid checked.
     * @return if a boid is colliding with another one.
     */
    boolean isCollidingWith(final Boid boid);

    /**
     *
     * @return boid position
     */
    Vector getPosition();

    /**
     *
     * @return boid life
     */
    int getLife();

    /**
     *
     * @return a Set of closest boids
     */
    Set<Boid> getSameLevelNearBoids();

    /**
     * @return other level near boids.
     */

    Set<Boid> getOtherLevelNearBoids();

    /**
     *
     * @return boid acceleration
     */
    Vector getAcceleration();

    /**
     *
     * @return boid velocity
     */
    Vector getVelocity();

    /**
     *
     * @return boid max speed
     */
    double getMaxSpeed();

    /**
     *
     * @return max Member of a boid group
     */
    int getMaxMembers();

    /**
     *
     * @return boid influence radius
     */
    double getInfluenceRadius();

    /**
     * Calculate rotation angle of a boid.
     *
     * @return Rotation angle.
     */
    double getRotationAngle();
}
