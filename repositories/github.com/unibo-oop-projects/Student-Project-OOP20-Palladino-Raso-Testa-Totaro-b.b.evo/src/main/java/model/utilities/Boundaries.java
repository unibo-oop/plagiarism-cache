
package model.utilities;

import java.util.Arrays;
import java.util.List;

/**
 * Returns the side of the collision between two objects.
 */
public enum Boundaries {
    /**
     * Represents the upper bound.
     */
    UPPER,
    /**
     * Represents the lower bound.
     */
    LOWER,
    /**
     * Represents the left bound.
     */
    SIDE_LEFT,
    /**
     * Represents the right bound.
     */
    SIDE_RIGHT;

    /**
     * Used to know all the possible boundaries sides of a gameObject.
     * 
     * @return the list of this {@link Boundaries}
     */
    public static List<Boundaries> getBoundariesList() {
        return Arrays.asList(Boundaries.values());
    }
}
