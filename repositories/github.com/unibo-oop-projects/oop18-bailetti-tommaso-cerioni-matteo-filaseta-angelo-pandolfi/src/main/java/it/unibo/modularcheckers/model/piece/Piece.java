package it.unibo.modularcheckers.model.piece;

import java.io.Serializable;
import java.util.stream.Stream;

import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;

/**
 * Interface for the Piece class.
 */
public interface Piece extends Serializable {

    /**
     * Get the type of the piece.
     * 
     * @return type of the piece.
     */
    PieceType getType();

    /**
     * Get the color of the piece.
     * 
     * @return color of the piece.
     */
    Color getColor();

    /**
     * Get the relative moveSet of the piece.
     * 
     * @return the relative moveSet.
     */
    Stream<Coordinate> getMoveSet();
}
