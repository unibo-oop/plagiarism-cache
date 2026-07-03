package controller;

import utilities.Pair;
import model.utilities.pawns.*;

/**
 * @author Alex Ravaglia
 * Controller Interface.
 */

public interface Controller {


    /**
     * active the mode whit the timer
     */
    void timeCheck();

    /**
     * actives the possibility to see the possible moves for each Pawn selected
     */
    void movesCheck();

    /**
     * active the possibility to see the threat moves
     */
    void threatCheck();

    /**
     * start the view
     */
    void gameStart();

    /**
     * called by the view when there is a click on the cheesboard
     * @param hit : the coordinate on the chessBoard click 
     */
    void onChessboardHit(Pair<Integer,Integer> hit);

    /**
     * do the pawn promotion when the pawn arrive in limit of the chessboard
     * set the promotion in the model and in the view
     * @param type : who will replace the type simplePawn
     */
    void pawnPromotion(PawnTypes type);

    /**
     * reset the game situation to the last move edited.
     */
    void undoMove();

    /**
     * @return seeThreatPawn
     */
    boolean getThreat();

    /**
     * @return seePossibleMove
     */
    boolean getMoves();

    /**
     * @return timeGame
     */
    boolean getTime();

}
