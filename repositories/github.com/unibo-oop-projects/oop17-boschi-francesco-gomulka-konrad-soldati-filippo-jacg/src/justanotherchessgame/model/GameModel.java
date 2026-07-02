package justanotherchessgame.model;

import java.util.List;

import justanotherchessgame.util.Point;

/**
 * Interface used to represent the state of a game and all the usefull functions.
 */
public interface GameModel {
    /**
     * Function which returns the color of the next expected player.
     * @return a boolean representing the next expected player.
     */
    boolean nextColor();
    /**
     * Function which requests a certain move on the chessboard.
     * @param m is the move which is requested.
     */
    void requestMove(MoveInfoImpl m);
    /**
     * Function which returns a list of possible moves given a certain point.
     * @param p is the point from which the moves will be calculated.
     * @return a list of all the possible moves from the given point.
     */
    List<MoveInfoImpl> getValidMoves(Point p);
    /**
     * Function which returns the currently used board.
     * @return the currently used board.
     */
    ChessboardModel getCurrentBoard();
    /**
     * Function which returns all the performed moves during this game.
     * @return the list of all performed moves.
     */
    List<MoveInfoImpl> getMoveHistory();
    /**
     * Function which loads a game from a list of moves.
     * @param moves is the list of all moves which have ti be applied.
     */
    void loadGame(List<MoveInfoImpl> moves);
    /**
     * Function which returns all the performed moves during this game until a certain point.
     * @param index is the index of the last move which has to be returned.
     * @return a list of all the performed moves during this game until a certain point.
     */
    List<MoveInfoImpl> getHistoryTill(int index);
    /**
     * Function which returns the number of all performed moves during this game.
     * @return an integer representing the number of performed moves during current game.
     */
    int getMovesCount();
}
