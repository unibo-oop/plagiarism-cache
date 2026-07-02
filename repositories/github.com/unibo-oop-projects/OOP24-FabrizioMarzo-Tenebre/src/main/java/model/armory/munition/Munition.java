package model.armory.munition;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;

/**
 * Interface representing a munition (ammo) entity in the model.
 * <p>
 * Defines behaviors related to damage, position, movement, and shooting state.
 */
public interface Munition {

    /**
     * Returns the damage value inflicted by this munition.
     * 
     * @return the damage amount
     */
    int getDamage();

    /**
     * Returns the bounding box used for collision detection.
     * 
     * @return the bounding box of the munition
     */
    BoundingBox getBBox();

    /**
     * Sets the shooting state and initial parameters of the munition.
     * 
     * @param dirShoot the direction vector of the shot (x, y)
     * @param posShoot the starting position of the shot (x, y)
     */
    void setShoot(final Pair<Double, Double> dirShoot, final Pair<Double, Double> posShoot);

    /**
     * Updates the munition's position based on the elapsed time.
     * 
     * @param dt time delta in milliseconds
     */
    void moveShoot(final int dt);

    /**
     * Returns whether the munition has been shot (is in motion).
     * 
     * @return {@code true} if the munition is currently shot, {@code false}
     *         otherwise
     */
    boolean isShoot();

    /**
     * Sets the position of the munition.
     * 
     * @param nextPos the new position (x, y)
     */
    void setPos(final Pair<Double, Double> nextPos);

    /**
     * Returns the current position of the munition.
     * 
     * @return the current position (x, y)
     */
    Pair<Double, Double> getCurrentPos();

    /**
     * Returns the width of the munition in model units.
     * 
     * @return the width
     */
    int getWidth();
}
