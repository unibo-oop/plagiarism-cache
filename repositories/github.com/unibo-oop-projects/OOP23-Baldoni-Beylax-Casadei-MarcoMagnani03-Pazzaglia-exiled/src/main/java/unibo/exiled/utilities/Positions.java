package unibo.exiled.utilities;

/**
 * Utilities for the positions.
 */
public final class Positions {
    private Positions() {

    }

    /**
     * Sums two positions.
     *
     * @param pos1 The first position.
     * @param pos2 The second position to sum.
     * @return A new position that consists of the sum of the two inputted positions.
     */
    public static Position sum(final Position pos1, final Position pos2) {
        return new Position(pos1.x() + pos2.x(), pos1.y() + pos2.y());
    }
}
