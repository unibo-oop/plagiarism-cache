package vg.model.mystery_box.data_round;

import vg.utils.V2D;

/**
 * This interface represents the data of the round for mystery box.
 */
public interface DataRound {
    /**
     * This method returns the position of the box.
     * @return the position of the box.
     */
    V2D getPosition();
    /**
     * This method returns if the box is blinking.
     * @return true if the box is blinking, false otherwise.
     */
    boolean isBlinking();
}
