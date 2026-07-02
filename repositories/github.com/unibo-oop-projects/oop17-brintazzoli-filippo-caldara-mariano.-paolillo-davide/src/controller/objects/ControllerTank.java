package controller.objects;

import model.utility.Direction;
import model.utility.Pair;

/**
 * Interface to control {@link Tank}.
 */
public interface ControllerTank {

    /**
     * Modify the player {@link Input} according to the keyboard input.
     * 
     * @param direction
     *            the {@link Direction}.
     * @param b
     *            a {@link Boolean}. It's true if the key is pressed, false
     *            otherwise.
     */
    void movePlayerTank(Direction direction, boolean b);

    /**
     * Update the two {@link Tank} and check the collisions.
     */
    void updateTank();

    /**
     * Rotate the player cannon in the position targeted by mouse.
     * 
     * @param target
     *            the target position.
     */
    void movePlayerCannon(Pair<Double, Double> target);

}
