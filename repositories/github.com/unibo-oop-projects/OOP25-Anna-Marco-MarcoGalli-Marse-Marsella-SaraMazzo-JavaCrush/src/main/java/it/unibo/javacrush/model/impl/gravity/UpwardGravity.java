package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

/**
 * A gravity engine that applies gravity upward.
 */
public class UpwardGravity extends AbstractGravity {

    /**
     * Constructs a UpwardGravity instance.
     */
    public UpwardGravity() {
        super(Direction.UP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyGravity(final Board board) {
        boolean moved = false;
        for (int row = 1; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                final Position current = new Position(col, row);
                final Position target = new Position(col, row - 1);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
    }

}
