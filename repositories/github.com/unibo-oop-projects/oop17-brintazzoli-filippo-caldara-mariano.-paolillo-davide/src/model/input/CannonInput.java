package model.input;

import model.utility.Pair;

/**
 * Model Interface for Cannon's Input.
 * <p>
 * CannonInput memorize the position of cannon in {@link Pair}, where cannon is
 * aiming.
 * 
 * @see InputImpl
 * @see controller.input
 */
public interface CannonInput {
    /**
     * Getter for position where cannon of Tank is aiming.
     * 
     * @return Pair position in double(x,y).
     */
    Pair<Double, Double> getTargetPosition();

    /**
     * Setter for position where cannon is aiming.
     * 
     * @param target
     *            the position in double(x,y);
     */
    void setTarget(Pair<Double, Double> target);
}
