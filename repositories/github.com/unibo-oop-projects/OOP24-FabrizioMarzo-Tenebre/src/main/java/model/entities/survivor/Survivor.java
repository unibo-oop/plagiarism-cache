package model.entities.survivor;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;
import model.armory.weapon.Weapon;
import model.bounding_box.BoundingBox;

/**
 * Interface representing a survivor entity in the domain model
 * It defines the basic actions and state management for a survivor entity.
 */
public interface Survivor {

    /**
     * Applies damage to the survivor, reducing its health.
     *
     * @param dm The amount of damage to apply
     */
    void damageSuffer(final int dm);

    /**
     * Updates the survivor's physics state based on the elapsed time.
     *
     * @param dt The time delta (e.g., in milliseconds)
     */
    void updatePhysics(final int dt);

    /**
     * Sets the survivor's velocity.
     *
     * @param vel A pair (vx, vy) representing the new velocity vector
     */
    void setVelocity(final Pair<Double, Double> vel);

    /**
     * Sets the survivor's position.
     *
     * @param pos A pair (x, y) representing the position coordinates
     */
    void setPosition(final Pair<Double, Double> pos);

    /**
     * Sets the current state of the survivor.
     *
     * @param newState The new {@link SurvivorState} to apply
     */
    void setState(final SurvivorState newState);

    /**
     * Assigns a weapon to the survivor.
     *
     * @param weapon The {@link Weapon} to equip
     */
    void setWeapon(final Weapon weapon);

    /**
     * Returns the current health points of the survivor.
     *
     * @return The current health value
     */
    int getLive();

    /**
     * Returns the maximum health points of the survivor.
     *
     * @return The maximum health value
     */
    int getMaxSurvivorHealth();

    /**
     * Returns the survivor's width in model units.
     *
     * @return The width value
     */
    int getWidth();

    /**
     * Returns the survivor's height in model units.
     *
     * @return The height value
     */
    int getHeight();

    /**
     * Returns the current position of the survivor.
     *
     * @return A pair (x, y) representing the current position
     */
    Pair<Double, Double> getCurrentPos();

    /**
     * Returns the current velocity of the survivor.
     *
     * @return A pair (vx, vy) representing the current velocity
     */
    Pair<Double, Double> getCurrentVel();

    /**
     * Returns the base velocity of the survivor (e.g., default movement speed).
     *
     * @return A pair (vx, vy) representing the base velocity vector
     */
    Pair<Double, Double> getBaseSurvivorVel();

    /**
     * Returns the current state of the survivor.
     *
     * @return The current state
     */
    SurvivorState getState();

    /**
     * Returns the currently equipped weapon.
     *
     * @return The current {@link Weapon}
     */
    Weapon getWeapon();

    /**
     * Returns the bounding box used for collision detection.
     *
     * @return The survivor's {@link BoundingBox}
     */
    BoundingBox getBBox();

    /**
     * Executes a shooting action based on the time elapsed since last shot.
     *
     * @param deltaTime Time elapsed since the last shooting action
     * @return A list of {@link Munition} representing the projectiles fired
     */
    List<Munition> shoot(final double deltaTime);

    /**
     * Returns whether the survivor is dead (health has reached zero).
     *
     * @return {@code true} if the survivor is dead, {@code false} otherwise
     */
    boolean isSurvivorDead();
}
