package justanotherchessgame.view.game;

import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.Piece;
import justanotherchessgame.view.ResizableGraphicComponent;

/**
 * Class used to create the view of the chessboard.
 */
public interface ChessboardView extends ResizableGraphicComponent {
    /**
     * Function used to define the typical start position of all the pieces in a chessboard.
     */
    void defineStartPositions();
    /**
     * Function used to disable all spaces.
     */
    void disabelAllSpaces();
    /**
     * Function used to perform a move on the chessboard.
     * @param move is the move that is performed.
     */
    void drawMove(MoveInfoImpl move);
    /**
     * Function used to add a piece to the chessboard.
     * @param x is the first coordinate of the piece.
     * @param y is the second coordinate of the piece.
     * @param p is the piece.
     */
    void addPiece(int x, int y, Piece p);
    /**
     * Function used to get the matrix containing the chessboard elements.
     * @return the chessboard matrix.
     */
    BoxView[][] getTable();
}
