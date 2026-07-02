package it.unibo.modularcheckers.checkers.model.piece;

import java.util.stream.Stream;

import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.piece.PieceType;

/**
 * The King Piece class.
 */
public class King extends Man {

    /**
     * Initialize a King.
     * @param color the color of the King.
     */
    public King(final Color color) {
        super(color);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Coordinate> getMoveSet() {
        return Stream.concat(super.getMoveSet(), Stream.of(new Coordinate(1, -1), new Coordinate(-1, -1)));
    }

}
