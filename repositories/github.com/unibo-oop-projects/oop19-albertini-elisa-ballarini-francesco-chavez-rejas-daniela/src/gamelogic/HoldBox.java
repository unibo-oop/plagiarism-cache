package gamelogic;

import piece.Piece;

/**
 * This interface models the hold box, it keeps track of what is currently hold and if the hold is available.
 *
 */
public interface HoldBox {

    /**
     * This method hold the current piece and return the last hold one.
     * 
     * @return the hold piece
     */
    Piece hold();

    /**
     * @return true if a piece is hold, otherwise return false
     */
    boolean isHolding();

    /**
     * @return true if the hold is available, otherwise return false
     */
    boolean canHold();

    /**
     * This method sets the canHold flag.
     * 
     * @param bool , true if now the hold is avaiable, otherwise false
     */
    void setCanHold(boolean bool);

    /**
     * This method is called only for the first hold, it holds the current piece and
     * sets the next piece as new current.
     */
    void firstHold();

}
