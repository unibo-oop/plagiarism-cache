package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

/**
 * Interface representing a zombie entity in the domain model.
 * <p>
 * Defines core behaviors and state management for a zombie,
 * including movement, health, attack capabilities, and physics movement.
 */
public interface Zombie {

    /**
     * Performs an attack action and returns the damage dealt.
     *
     * @return the amount of damage inflicted by the zombie
     */
    int attack();

    /**
     * Applies damage to the zombie, reducing its health.
     *
     * @param dm the amount of damage taken
     */
    void damageSuffer(final int dm);

    /**
     * Updates the physics state of the zombie based on elapsed time.
     *
     * @param dt time delta (e.g., in milliseconds)
     */
    void updatePhysics(final int dt);

    /**
     * Sets the zombie's position in the model space.
     *
     * @param pos a pair (x, y) representing the new position
     */
    void setPosition(final Pair<Double, Double> pos);

    /**
     * Sets the zombie's current velocity.
     *
     * @param vel a pair (vx, vy) representing the new velocity
     */
    void setVelocity(final Pair<Double, Double> vel);

    /**
     * Sets the current state of the zombie.
     *
     * @param newState the {@link ZombieState} to apply
     */
    void setState(final ZombieState newState);

    /**
     * Returns the width of the zombie in model units.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Returns the height of the zombie in model units.
     *
     * @return the height
     */
    int getHeight();

    /**
     * Returns the current health points of the zombie.
     *
     * @return the current health
     */
    int getLive();

    /**
     * Returns the maximum health points of the zombie.
     *
     * @return the maximum health
     */
    int getMaxZombieHealth();

    /**
     * Returns the current position of the zombie.
     *
     * @return a pair (x, y) representing the current position
     */
    Pair<Double, Double> getCurrentPos();

    /**
     * Returns the current velocity of the zombie.
     *
     * @return a pair (vx, vy) representing the current velocity
     */
    Pair<Double, Double> getCurrentVel();

    /**
     * Returns the base (default) velocity of the zombie.
     *
     * @return a pair (vx, vy) representing the base velocity
     */
    Pair<Double, Double> getBaseZombieVel();

    /**
     * Returns the current state of the zombie.
     *
     * @return the current {@link ZombieState}
     */
    ZombieState getState();

    /**
     * Returns the bounding box of the zombie, used for collision detection.
     *
     * @return the {@link BoundingBox} associated with this zombie
     */
    BoundingBox getBBox();

    /**
     * Returns whether the zombie is dead (health has reached zero).
     *
     * @return {@code true} if the zombie is dead, {@code false} otherwise
     */
    boolean isZombieDead();
}
