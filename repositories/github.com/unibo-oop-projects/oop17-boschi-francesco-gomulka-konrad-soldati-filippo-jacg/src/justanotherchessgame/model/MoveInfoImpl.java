package justanotherchessgame.model;

import justanotherchessgame.util.Point;

/**
 * Class representing a move on the chessboard.
 */
public class MoveInfoImpl implements MoveInfo {
    private Point from, to;
    private Class<? extends Piece> promotedPiece;
    //TODO: embed moved Pieces. This is import for both Moves reversing and move displaying...

    /**
     * Contructor of the move.
     * @param from is the starting point of the move.
     * @param to is the ending point of the move.
     */
    public MoveInfoImpl(final Point from, final Point to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Contructor of the move which includes a pawn promotion.
     * @param from is the starting point of the move.
     * @param to is the ending point of the move.
     * @param promotion is the new class of the pawn.
     */
    public MoveInfoImpl(final Point from, final Point to, final Class<? extends Piece> promotion) {
        this(from, to);
        promotedPiece = promotion;
        }

    public final void setPromotion(final Class<? extends Piece> type) {
        // TODO: throw if setting a non null move?
        if (promotedPiece != null) {
            return;
        }
        promotedPiece = type;
    }
    public final Class<? extends Piece> getPromotion() {
        return promotedPiece;
    }

    @Override
    public final String toString() {
        return (getCharLabel(from.getX() + 1) + (from.getY() + 1) + " to " + getCharLabel(to.getX() + 1) + (to.getY() + 1)) + (promotedPiece != null ? " promotes to " + promotedPiece.getName() : ""); 
    }

    //CANNOT STAY IN INTERFACE BECAUSE OFF FINAL MODIFIER. IS IS NECESSARY?
    /**
     * Getter of the from point.
     * @return staring point of the move.
     */
    public final Point getFrom() {
        return from;
    }

    /**
     * Getter of the to point.
     * @return end point of the move.
     */
    public final Point getTo() {
        return to;
    }

    /**
     * Function used to get the string corresponding to a number.
     * @param i is the number to convert.
     * @return converted string.
     */
    private String getCharLabel(final int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

    //TODO: convert to some standard formats
}
