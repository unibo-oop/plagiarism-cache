package it.unibo.modularcheckers.checkers.model.piece;

import java.util.stream.Stream;

import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.piece.AbstractPiece;
import it.unibo.modularcheckers.model.piece.PieceType;

/**
 * The Man Piece class.
 */
public class Man extends AbstractPiece {

    /**
     * Initialize a Man.
     * @param color the color of the Man.
     */
    public Man(final Color color) {
        super(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PieceType getType() {
        return PieceType.MAN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<Coordinate> getMoveSet() {
        return Stream.of(new Coordinate(1, 1), new Coordinate(-1, 1));
    }

}
