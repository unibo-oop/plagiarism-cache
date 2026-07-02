package utility;

import javafx.util.Pair;

/**
 * Class used to save the position where the user want to build a new construction.
 */
public final class PositionControllerHelper {

    private static Pair<Integer, Integer> position;
    private static boolean isValid;

    /**
     * Set the new position for the new building.
     * @param pos
     *          the position of last building built
     */
    public static void setPosition(final Pair<Integer, Integer> pos) {
        position = pos;
        isValid = true;
    }

    /**
     * Set the position to default value, where isn't allow the costrunction.
     */
    public static void setDefault() {
        position = new Pair<Integer, Integer>(-1, -1);
        isValid = false;
    }

    /**
     * Get the position where the user want to build a new construction.
     * @return
     *          the position of the new construction.
     */
    public static Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Check if the currently position saved position is valid.
     * @return
     *          true:   if the position is valid (row and column in their range)
     *          false:  if the position is not valid (the user had close the PositionBuilding window and then there isn't a valid position where build the new building
     */
    public static boolean isValidPosition() {
        return position.getKey() != -1 && isValid;
    }

    private PositionControllerHelper() {
    }
}
