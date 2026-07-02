package justanotherchessgame.model;

/**
 * Class relative to a Bishop Piece.
 */
public class Bishop extends Piece {
    /**
     * Constructor of the bishop, same as piece.
     * @param color is the color of the piece.
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece.
     */
    public Bishop(final boolean color, final int x, final int y) {
        super(color, x, y);
    }

    /**
     *Function used to get the list of moves.
     *@return the list of possible moves.
     */
    protected final Moves[] getMoves() {
        return new Moves[] {Moves.UP_RIGHT, Moves.DOWN_RIGHT, Moves.UP_LEFT, Moves.DOWN_LEFT};
    }

    /**
     * Function used to know if the piece make a single move.
     * @return false because it makes multiple moves.
     */
    protected final boolean usesSingleMove() {
        return false;
    }

    /**
     * Function used to get the piece name.
     * @return the piece name.
     */
    public final String getName() {
        return "Bishop";
    }
}
