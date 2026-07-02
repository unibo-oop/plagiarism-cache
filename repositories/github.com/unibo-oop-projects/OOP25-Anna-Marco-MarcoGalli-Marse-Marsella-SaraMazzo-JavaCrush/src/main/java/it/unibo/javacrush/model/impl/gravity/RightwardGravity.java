package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

/**
 * A gravity engine that applies gravity rightward.
 */
public class RightwardGravity extends AbstractGravity {

    /**
     * Constructs a RightwardGravity instance.
     */
    public RightwardGravity() {
        super(Direction.RIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyGravity(final Board board) {
        boolean moved = false;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = board.getCols() - 2; col >= 0; col--) {
                final Position current = new Position(col, row);
                final Position target = new Position(col + 1, row);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
    }
}
