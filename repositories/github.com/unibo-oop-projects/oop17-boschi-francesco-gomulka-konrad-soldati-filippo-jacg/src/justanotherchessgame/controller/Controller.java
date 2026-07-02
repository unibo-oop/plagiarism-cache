package justanotherchessgame.controller;

import java.io.File;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;
import justanotherchessgame.util.GameResult;
import justanotherchessgame.view.game.BoxView;

/**
 * Interface used to create the controller: the entity used to communicate between localPlayer and view.
 */
public interface Controller {

    /**
     * Function used to create the view of the chessboard when the user click on a log.
     * @param index is the index used to identify the log.
     */
    void logClicked(int index);

    /**
     * Function used to manage the click on a chessboard cell.
     * @param x is the first coordinate of the cell clicked.
     * @param y is the second coordinate of the cell clicked.
     */
    void onSpaceClicked(int x, int y);

    /**
     * Function used to set all the reachable boxes as unreachable, updating the list and their CSS.
     */
    void deselect();

    /**
     * Function used when a Pawn reaches the end of the chessboard and must be promoted.
     * @param p is the pawn that will be promoted.
     * @return the piece chosen by the user.
     */
    Class<? extends Piece> askForPromotion(Piece p);

    /**
     * Function used to save the game.
     * @param file is the output file.
     */
    void saveGame(File file);

    /**
     * Function used to update the view when a piece is taken.
     * @param p is the piece taken.
     */
    void addTakenpiece(Piece p);

    /**
     * Function used to get the clicked space.
     * @return the last clicked space.
     */
    BoxView getClickedSpace();

    /**
     * Function that notifies about a move being done, in order to update the view accordingly.
     * @param m is the move performed.
     */
    void notifyMove(MoveInfoImpl m);

    /**
     * Function used to notify the game end.
     * @param result indicated who is the winner.
     */
    void notifyGameEnd(GameResult result);

    /**
     * Function used to know if the game has been saved  after the last move performed.
     * @return a boolean indicating if the game is already saved.
     */
    boolean isSaved();

    /**
     * Function used to set the game as saved.
     */
    void setSaved();

}
