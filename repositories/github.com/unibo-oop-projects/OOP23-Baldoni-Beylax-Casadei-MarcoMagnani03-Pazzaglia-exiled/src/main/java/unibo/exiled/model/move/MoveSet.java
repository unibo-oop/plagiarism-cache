package unibo.exiled.model.move;

import java.util.Set;

/**
 * Represents a set of magical moves that a character can perform.
 */
public interface MoveSet {

    /**
     * Gets the set of magical moves in this MoveSet.
     *
     * @return The set of magical moves.
     */
    Set<MagicMove> getMagicMoves();

    /**
     * Changes a magical move in the set by replacing the old move with the new move.
     *
     * @param oldMove The old magical move to be replaced.
     * @param newMove The new magical move to replace the old move.
     * @return Return false if the old move is not found in the set.
     */
    boolean changeMove(MagicMove oldMove, MagicMove newMove);

    /**
     * Adds a new magical move to the set if the number of moves is below a specified limit.
     *
     * @param newMove The new magical move to be added.
     */
    void addMagicMove(MagicMove newMove);
}
