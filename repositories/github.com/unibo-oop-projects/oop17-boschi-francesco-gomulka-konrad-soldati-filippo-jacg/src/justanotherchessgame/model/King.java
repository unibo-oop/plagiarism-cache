package justanotherchessgame.model;

/**
 * Class relative to a King Piece.
 */
public class King extends Piece {

    /**
     * Constructor of the King, same as piece.
     * @param color is the color of the piece.
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece
     */
    public King(final boolean color, final int x, final int y) {
        super(color, x, y);
    }

    @Override
    protected final Moves[] getMoves() {
        return new Moves[] {Moves.UP, Moves.DOWN, Moves.LEFT, Moves.RIGHT, Moves.UP_LEFT, Moves.UP_RIGHT, Moves.DOWN_LEFT, Moves.DOWN_RIGHT};
    }

    @Override
    protected final boolean usesSingleMove() {
        return true;
    }

    @Override
    public final String getName() {
        return "King";
    }
}
