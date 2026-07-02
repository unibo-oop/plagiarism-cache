package justanotherchessgame.view.game;

import javafx.scene.control.ListView;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;

/**
 * Class used to create and manage all the game lists.
 */
public interface TakenListView {

    /**
     * Function used to get the list containing the game log.
     * @return the log list.
     */
    ListView<ListMove> getLogList();

    /**
     * Function used to get the list of the white taken pieces.
     * @return the white piece taken list.
     */
    ListView<ChessPiece> getWhiteList();

    /**
     * Function used to get the list of the black taken pieces.
     * @return the black piece taken list.
     */
    ListView<ChessPiece> getBlackList();

    /**
     * Function used to add a piece to one of the two taken pieces list. The color is determined by the piece.
     * @param p is the piece that will be added.
     */
    void addPiece(Piece p);

    /**
     * Function used to add a move to the log list.
     * @param p is the piece moved.
     * @param m is the move performed.
     */
    void addLog(Piece p, MoveInfoImpl m);
}
