package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;

/**
 * A gravity engine that applies gravity downward.
 */
public class DownwardGravity extends AbstractGravity {

    /**
     * Constructs a DownwardGravity instance.
     */
    public DownwardGravity() {
        super(Direction.DOWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyGravity(final Board board) {
        boolean moved = false;
        for (int row = board.getRows() - 2; row >= 0; row--) {
            for (int col = 0; col < board.getCols(); col++) {
                final Position current = new Position(col, row);
                final Position target = new Position(col, row + 1);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
    }

}
