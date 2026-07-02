package it.unibo.modularcheckers.model.move;

import com.google.common.base.Optional;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.piece.Piece;

/**
 * The single step of a move.
 */
public interface Step {

    /**
     * Get the coordinate of the piece in the step. Its position if the step is the first, 
     * where to move in the next steps.
     * @return the coordinate of the piece in the step.
     */
    Coordinate getCoordinate();

    /**
     * Get the coordinate and the piece killed in the step.
     * @return the dead piece of the move.
     */
    Optional<Pair<Coordinate, Piece>> getDeadPiece();

}
