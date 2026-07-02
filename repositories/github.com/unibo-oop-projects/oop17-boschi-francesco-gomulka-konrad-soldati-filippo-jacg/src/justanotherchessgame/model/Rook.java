package justanotherchessgame.model;

/**
 * Class relative to a Rook Piece.
 */
public class Rook extends Piece {

    /**
     * Constructor of the Rook, same as piece.
     * @param color is the color of the piece.
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece
     */
    public Rook(final boolean color, final int x, final int y) {
        super(color, x, y);
    }

    @Override
    protected final Moves[] getMoves() {
        return new Moves[]{ Moves.UP, Moves.DOWN, Moves.LEFT, Moves.RIGHT};
    }

    @Override
    protected final boolean usesSingleMove() {
        return false;
    }

    @Override
    public final String getName() {
        return "Rook";
    }
}
