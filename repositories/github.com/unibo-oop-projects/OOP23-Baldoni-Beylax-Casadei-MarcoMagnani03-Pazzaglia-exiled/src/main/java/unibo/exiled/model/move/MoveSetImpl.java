package unibo.exiled.model.move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The implementation of the MoveSet interface.
 */
public final class MoveSetImpl implements MoveSet {
    /**
     * A set of moves that compose the move set.
     */
    private final Set<MagicMove> magicMoves;

    /**
     * The constructor of the move set.
     */
    public MoveSetImpl() {
        magicMoves = new HashSet<>();
    }

    @Override
    public Set<MagicMove> getMagicMoves() {
        return Collections.unmodifiableSet(magicMoves);
    }

    @Override
    public boolean changeMove(final MagicMove oldMove, final MagicMove newMove) {
        if (magicMoves.contains(oldMove)) {
            magicMoves.remove(oldMove);
            magicMoves.add(newMove);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addMagicMove(final MagicMove newMove) {
        magicMoves.add(newMove);
    }

}
