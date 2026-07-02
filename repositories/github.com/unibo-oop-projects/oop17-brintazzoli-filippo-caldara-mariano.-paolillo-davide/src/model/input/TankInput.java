package model.input;

import java.util.Map;
import model.utility.Direction;

/**
 * Model Interface for movements' input.
 * <p>
 * Memorize next movement of Tank. Directions are memorized in a {@link Map},
 * where keys are {@link Direction} and values are {@link Boolean}. Values
 * become true where tank need to be moved.
 * 
 * @see InputImpl
 * @see TankImpl
 */
public interface TankInput {
    /**
     * Setter for next movement.
     * 
     * @param movement
     *            map with directions and boolean.
     */
    void setMovement(Map<Direction, Boolean> movement);

    /**
     * Getter the next movement.
     * 
     * @return the next movement.
     */
    Map<Direction, Boolean> getMovement();
}
