package justanotherchessgame.view.game;

import justanotherchessgame.model.Piece;
import justanotherchessgame.view.ResizableGraphicComponent;

/**
 * Interface used to draw and manage every box of the chessboard.
 */
public interface BoxView extends ResizableGraphicComponent {

    /**
     * Function used to know if a box contains or not a piece.
     * @return true if the box contains a piece, false otherwise.
     */
    boolean hasPiece();
    /**
     * Function used to get the piece contained in the box.
     * @return the piece contained in the box, null if is empty.
     */
    Piece getPiece();
    /**
     * Function used to apply the reachable CSS to the box and mark it as reachable.
     */
    void reachableBox();
    /**
     * Function used to remove the reachable CSS to the box and mark it as not reachable.
     */
    void notReachableBox();
    /**
     * Function used to apply the select CSS to the box and mark it as selected.
     */
    void selectBox();
    /**
     * Function used to remove the CSS to the box and mark it as not selected.
     */
    void deselectBox();
    /**
     * Function used to draw a piece in the box.
     * @param piece is the piece to draw.
     */
    void drawPiece(Piece piece);
    /**
     * Function used to get the first coordinate of the box.
     * @return the first coordinate of the box.
     */
    int getX();
    /**
     * Function used to get the second coordinate of the box.
     * @return the second coordinate of the box.
     */
    int getY();
}
