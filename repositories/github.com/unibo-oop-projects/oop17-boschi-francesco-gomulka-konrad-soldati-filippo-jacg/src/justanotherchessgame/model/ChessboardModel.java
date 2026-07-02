package justanotherchessgame.model;

import java.util.List;

import justanotherchessgame.util.Point;

/**
 * Interface used to manage and create a chessboard.
 */
public interface ChessboardModel {

    /**
     * Method which returns the chessboard matrix.
     * @return the chessboard created.
     */
    Piece[][] getBoard();

    /**
     * Method which returns the king piece of a given color.
     * @param color is the color of the requested king.
     * @return the King Piece of a given color.
     */
    King getKing(boolean color);

    /**
     * Function which returns a list of all the pieces on board.
     * @return the list of all Pieces on board.
     */
    List<Piece> getPieceOnBoard();

    /**
     * Function which applies the move if its possible.
     * @param move is the move that might be applied.
     * @return a boolean indicating if the move has been applied.
     */
    boolean move(MoveInfoImpl move);

    /**
     * Function which returns the last applied move.
     * @return the last applied move.
     */
    MoveInfoImpl getLast();

    /**
     * Function that returns a certain square on the chessboard.
     * @param p is the point of the chessboard.
     * @return the piece (or null) located in the given point on the chessboard.
     */
    Piece getSquare(Point p);

    /**
     * Function that returns simulates a given move on the chessboard.
     * @param move is the move which will be performed.
     * @return the eaten piece (or null).
     */
    Piece simulateMove(MoveInfoImpl move);

    /**
     * Function that returns undo a given move on the chessboard.
     * @param move is the move which will be performed.
     * @param eaten is the piece which has been eaten (or null).
     * @param previousMove is the move to undo
     */
    void undoMove(MoveInfoImpl move, Piece eaten, MoveInfoImpl previousMove);
}
