package casim.utils;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * Enumeration representing the possible directions a Ant can face.
 */
public enum Direction {

    /**
     * The north direction.
     */
    NORTH(CoordinatesUtil.of(0, 1)),
    /**
     * The East direction.
     */
    EAST(CoordinatesUtil.of(1, 0)),
    /**
     * The South direction.
     */
    SOUTH(CoordinatesUtil.of(0, -1)),
    /**
     * The West direction.
     */
    WEST(CoordinatesUtil.of(-1, 0));

    private Coordinates2D<Integer> moveInfo;

    /**
     * Constructs a new {@link Direction}.
     * 
     * @param moveInfo a {@link Coordinates2D} representing 
     * the incremental changes to be added to the Ant's position to move towards the current {@link Direction}.
     */
    Direction(final Coordinates2D<Integer> moveInfo) {
        this.moveInfo = moveInfo;
    }

    /**
     * Returns the movement info of the {@link Direction}.
     * 
     * @return a {@link Coordinates2D} of {@link Integer} describing the changes to be made to the X and Y coordinates
     * to move the Ant in the correct {@link Direction}
     */
    public Coordinates2D<Integer> getMoveInfo() {
        return this.moveInfo;
    }
}
