package piece;

import java.util.Set;

import pair.Pair;
import java.awt.Color;

/**
 * This interface models the pieces, it draws it, keeps track of his position and changes it.
 *
 */
public interface Piece {

    /**
     * This static variable is the size of the side of the square of the drawing
     * matrix.
     */
    int MATRIXDIMENSION = 4;

    /**
     * @return the coordinates referred to the drawing matrix
     */
    Set<Pair<Integer, Integer>> getCoordinates();

    /**
     * @return the coordinates referred to the board
     */
    Set<Pair<Integer, Integer>> getPosition();

    /**
     * This method sets the distance of the piece from the top of the board.
     * 
     * @param top : the distance of the piece from the top of the board
     */
    void setTop(int top);

    /**
     * This method sets the distance of the piece from the left side of the board.
     * 
     * @param left : the distance of the piece from the left side of the board 
     */
    void setLeft(int left);

    /**
     * This method sets the new coordinates of the center of the piece referred to
     * the drawing matrix.
     * 
     * @param center of the piece in the drawing matrix
     */
    void setCenter(Pair<Integer, Integer> center);

    /**
     * This method redesigns the piece in the drawing matrix.
     * 
     * @param coordinates of the piece in the drawing matrix
     */
    void setCoordinates(Set<Pair<Integer, Integer>> coordinates);

    /**
     * @return the coordinates of the center of the piece
     */
    Pair<Integer, Integer> getCenter();

    /**
     * @return the distance of the piece from the top side of the board
     */
    int getTop();

    /**
     * @return the distance of the piece from the left side of the board
     */
    int getLeft();

    /**
     * @return the color of the piece
     */
    Color getColor();

    /**
     * @return the enum type of the piece if it's a standard one, otherwise it
     *         returns the custom type
     */
    Type getType();

    /**
     * This method brings back the piece to the starting position.
     */
    void resetPiece();

    /**
     * @return a clone of the piece
     */
    Piece duplicate();

}
