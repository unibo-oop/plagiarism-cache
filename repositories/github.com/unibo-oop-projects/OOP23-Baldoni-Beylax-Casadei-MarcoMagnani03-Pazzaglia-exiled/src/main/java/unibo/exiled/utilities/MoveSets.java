package unibo.exiled.utilities;

import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;

/**
 * Utility class to manage MoveSets.
 */
public final class MoveSets {
    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private MoveSets() {
    }

    /**
     * Creates a copy of a MoveSet.
     *
     * @param moveSet The MoveSet to copy.
     * @return A MoveSet with the same moves of the parameter one.
     */
    public static MoveSet copyOf(final MoveSet moveSet) {
        final MoveSet newMoveSet = new MoveSetImpl();
        for (final MagicMove move : moveSet.getMagicMoves()) {
            newMoveSet.addMagicMove(move);
        }
        return newMoveSet;
    }
}
