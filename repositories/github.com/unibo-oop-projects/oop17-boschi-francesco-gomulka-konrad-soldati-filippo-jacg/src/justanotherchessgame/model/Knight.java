package justanotherchessgame.model;

/**
 * Class relative to a Knight Piece.
 */
public class Knight extends Piece {

    /**
     * Constructor of the Knight, same as piece.
     * @param color is the color of the piece.
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece
     */
    public Knight(final boolean color, final int x, final int y) {
        super(color, x, y);
    }

    @Override
    protected final Moves[] getMoves() {
        return new Moves[]{ Moves.KNIGHT_UPL, Moves.KNIGHT_UPR, Moves.KNIGHT_LEFTU, Moves.KNIGHT_LEFTD, Moves.KNIGHT_RIGHTU, Moves.KNIGHT_RIGHTD, Moves.KNIGHT_DOWNL, Moves.KNIGHT_DOWNR};
    }

    @Override
    protected final boolean usesSingleMove() {
        return true;
    }

    @Override
    public final String getName() {
        return "Knight";
    }
}
