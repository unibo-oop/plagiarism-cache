package it.unibo.javacrush.model.impl.gravity;

import java.util.Optional;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.GravityEngine;

/**
 * Abstract implementation of the {@link GravityEngine} interface.
 */
public abstract class AbstractGravity implements GravityEngine {

    private final Direction direction;

    /**
     * Constructs a new {@link AbstractGravity} instance with the specified direction.
     *
     * @param direction the direction of gravity
     */
    protected AbstractGravity(final Direction direction) {
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean applyGravity(Board board);

    /**
     * Checks if the given position is within the bounds of the board.
     *
     * @param board the game board
     * @param pos the position to check
     * @return true if the position is within bounds, false otherwise
     */
    protected boolean isInBound(final Board board, final Position pos) {
        return pos.x() >= 0 && pos.x() < board.getCols() 
        && pos.y() >= 0 && pos.y() < board.getRows();
    }

    /**
     * Attempts to move a cell from the current position to the target position.
     *
     * @param board the game board
     * @param current the current position
     * @param target the target position
     * @return true if the move was successful, false otherwise
     */
    protected boolean tryMove(final Board board, final Position current, final Position target) {
        if (!isInBound(board, current) || !isInBound(board, target)) {
            return false;
        }

        final Optional<Cell> currentCell = board.getCellAt(current);
        final Optional<Cell> targetCell = board.getCellAt(target);

        if (currentCell.isPresent() && targetCell.isEmpty()) {
            board.setCell(target, currentCell);
            board.setCell(current, Optional.empty());
            return true;
        }
        return false;
    }

}
