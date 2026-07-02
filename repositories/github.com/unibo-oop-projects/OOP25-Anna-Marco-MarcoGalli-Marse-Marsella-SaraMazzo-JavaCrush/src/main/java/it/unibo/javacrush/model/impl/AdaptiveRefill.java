package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.RefillEngine;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Implementation of the {@link RefillEngine} interface that adapts to the gravity direction.
 */
public class AdaptiveRefill implements RefillEngine {

    private final GravityEngine gravity;

    /**
     * Constructs a new {@link AdaptiveRefill} instance with the specified gravity engine.
     *
     * @param gravity the gravity engine
     */
    public AdaptiveRefill(final GravityEngine gravity) {
        this.gravity = gravity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean refill(final Board board) {
        final Direction dir = gravity.getDirection();
        final int rows = board.getRows();
        final int cols = board.getCols();

        final int range = dir.isVertical() ? cols : rows;
        final int fixedCoord = dir.getEntryPoint(rows, cols);

        final long addedCount = IntStream.range(0, range)
                .mapToObj(i -> createEntryPoint(i, fixedCoord, dir))
                .filter(pos -> board.getCellAt(pos).isEmpty())
                .peek(pos -> board.setCell(pos, Optional.of(new CellImpl(CellType.getRandomType()))))
                .count();

        return addedCount > 0;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public void refillAll(final Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                final Position pos = new Position(c, r);
                if (board.getCellAt(pos).isEmpty()) {
                    board.setCell(pos, Optional.of(new CellImpl(CellType.getRandomType())));
                }
            }
        }
    }

    private Position createEntryPoint(final int index, final int fixed, final Direction dir) {
        return dir.isVertical() ? new Position(index, fixed) : new Position(fixed, index);
    }

}
