package gamelogic;

import java.util.Set;

import piece.Piece;
import pair.Pair;

/**
 * This interface models the logic of the game, it's responsible of the game over's 
 * control, the management of the randomization of the pieces and the checks for collisions.
 */
public interface GameLogic {

    /**
     * @return the current piece
     */
    Piece getCurrent();

    /**
     * @return the next piece
     */
    Piece getNext();

    /**
     * @return the object board
     */
    Board getBoard();

    /**
     * @return the object score
     */
    Score getScore();

    /**
     * @return the object holdBox
     */
    HoldBox getHoldBox();

    /**
     * @return true if the game is Over, otherwise return false
     */
    boolean isOver();

    /**
     * If the hold is available this method takes the current piece apart, if it's
     * the first call sets the next piece as new current, otherwise sets the last
     * hold as new current.
     */
    void hold();

    /**
     * This method sets the current piece as a new obstacle on the board.
     */
    void placePiece();

    /**
     * @param piece to check
     * @return true if the position is legal, otherwise return false
     */
    boolean isLegalPosition(Set<Pair<Integer, Integer>> piece);

    /**
     * @return true if the current piece is on a legal position, otherwise return
     *         false
     */
    boolean isCurrentLegal();

    /**
     * This method change the current piece with the piece passed in input.
     * 
     * @param piece to set as new current
     */
    void setCurrent(Piece piece);

}
